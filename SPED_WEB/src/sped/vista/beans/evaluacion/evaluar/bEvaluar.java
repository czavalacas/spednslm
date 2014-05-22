package sped.vista.beans.evaluacion.evaluar;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.data.RichTreeTable;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.nav.RichButton;
import oracle.adf.view.rich.component.rich.output.RichActiveOutputText;
import oracle.adf.view.rich.component.rich.output.RichMessages;
import oracle.adf.view.rich.render.ClientEvent;
import org.apache.myfaces.trinidad.event.SelectionEvent;
import org.apache.myfaces.trinidad.model.ChildPropertyTreeModel;
import sped.negocio.LNSF.IL.LN_C_SFFichaCriterioLocal;
import sped.negocio.LNSF.IL.LN_C_SFFichaLocal;
import sped.negocio.LNSF.IL.LN_T_SFEvaluacionLocal;
import sped.negocio.LNSF.IL.LN_T_SFLoggerLocal;
import sped.negocio.LNSF.IR.LN_C_SFEvaluacionRemote;
import sped.negocio.entidades.beans.BeanCriterio;
import sped.negocio.entidades.beans.BeanError;
import sped.negocio.entidades.beans.BeanEvaluacionWS;
import sped.negocio.entidades.beans.BeanUsuario;
import sped.vista.Utils.Utils;

public class bEvaluar {

    private RichTable tbPlan;
    private RichTreeTable trFich;
    private RichInputText itCmmt;
    private RichButton btnCalc;
    private RichPopup popCmt;
    private RichButton btnCmt;
    private RichPopup popMsj;
    private RichActiveOutputText otError;
    private ChildPropertyTreeModel permisosTree;
    private RichButton btnRegistrar;
    private RichButton btnGrabar;
    private RichMessages msjGen;
    FacesContext ctx = FacesContext.getCurrentInstance();
    private String _error;
    private bSessionEvaluar sessionEvaluar;
    @EJB
    private LN_C_SFEvaluacionRemote ln_C_SFEvaluacionRemote;
    @EJB
    private LN_C_SFFichaCriterioLocal ln_C_SFFichaCriterioLocal;
    @EJB
    private LN_T_SFEvaluacionLocal ln_T_SFEvaluacionLocal;
    @EJB
    private LN_C_SFFichaLocal ln_C_SFFichaLocal;
    @EJB
    private LN_T_SFLoggerLocal ln_T_SFLoggerLocal;
    private static final String CLASE = "sped.vista.beans.evaluacion.evaluar.bEvaluar";
    private BeanUsuario usuario = (BeanUsuario) Utils.getSession("USER");

    public bEvaluar() {
    
    }

    @PostConstruct
    public void methodInvokeOncedOnPageLoad(){
        if(sessionEvaluar.getExec() == 0){
            sessionEvaluar.setExec(1);
            mostrarPlanificacionesParaHoy();
            /*  sessionRegistrarFicha.setLstFichas(ln_C_SFFichaRemote.getLstFichasByAttr_LN());
            mostrarCuadre();
            sessionR egistrarFicha.setBtnRegistrarFicha("Nueva Ficha");*/
        }else{
          //  mostrarCuadre();
            //Utils.depurar("POST CONSTRUCT otras veces");
        }
    }
    
    private void mostrarPlanificacionesParaHoy(){
        try {
            sessionEvaluar.setLstPlanificacionesXEvaluar(ln_C_SFEvaluacionRemote.getPlanificaciones_LN_WS(usuario.getRol().getNidRol(),
                                                                                                          usuario.getRol().getNidRol() == 4 ? usuario.getSede().getNidSede() : 0,
                                                                                                          usuario.getRol().getNidRol() == 2 ? usuario.getAreaAcademica().getNidAreaAcademica() : 0,
                                                                                                          usuario.getNidUsuario(),
                                                                                                          null, 
                                                                                                          null,
                                                                                                          0,
                                                                                                          0));
            if(tbPlan != null){
                tbPlan.setValue(sessionEvaluar.getLstPlanificacionesXEvaluar());
                Utils.unselectFilas(tbPlan);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void refrescarTablaPlanif(ActionEvent actionEvent) {
        refrescarTablaPlanifAux();
    }
    
    public void refrescarTablaPlanifAux(){
        mostrarPlanificacionesParaHoy();
        resetearAfterGrabar();
    }
    
    public void selectPlanificacion(SelectionEvent se) {
        /** dfloresgonz 16.05.2014 Se comenta porque no se reestringira por rango de horas al evaluar **/
        BeanEvaluacionWS planif = (BeanEvaluacionWS) Utils.getRowTable(se);
        Timestamp hoy = new Timestamp(new Date().getTime());
        if(hoy.after(planif.getStartDate())){
            sessionEvaluar.setPlanifSelect(planif);
            btnRegistrar.setDisabled(false);
            Utils.addTarget(btnRegistrar);
        }else{
            this.setError("Aun no es hora de evaluar, espero que llegue la hora de Inicio");
            Utils.showPopUpMIDDLE(popMsj);
            Utils.unselectFilas(tbPlan);
        }
      /*  boolean isBetween = hoy.after(planif.getStartDate()) && hoy.before(planif.getEndDate());
        Utils.sysout("La fecha actual esta entre la fecha de planificacion: "+isBetween);
        Utils.sysout("hoy: "+hoy+" planif.getStartDate(): "+planif.getStartDate()+" planif.getEndDate(): "+planif.getEndDate());
        if(isBetween){*/
           
       /* }else{
            this.setError("La hora actual no es la indicada para realizar esta evaluacion, o se paso la hora o aun no llega");
            Utils.showPopUpMIDDLE(popMsj);
            Utils.unselectFilas(tbPlan);
        }*/
    }
    
    public void registrarEvaluacion(ActionEvent actionEvent) {
        String tipoFicha = getUsuario().getRol().getNidRol() == 4 ? "S" : getUsuario().getRol().getNidRol() == 2 ? "E" : "";
        int valoresFicha[] = ln_C_SFFichaLocal.getFichaToEvaluar(tipoFicha,sessionEvaluar.getPlanifSelect().getTipoFichaCurso());
        if(valoresFicha != null){
            if(valoresFicha[0] != 0){
                sessionEvaluar.setMaxValor(valoresFicha[1]);
                btnGrabar.setVisible(true);
                btnCmt.setVisible(true);
                btnCalc.setVisible(true);
                sessionEvaluar.setVisiblePanelBoxPanelBoxFicha(true);
                trFich.setVisible(true);
                if("1".equalsIgnoreCase(sessionEvaluar.getPlanifSelect().getFlgParcial())){
                    sessionEvaluar.setLstCriteriosMultiples(ln_C_SFFichaCriterioLocal.getListaCriteriosByFichaConValores(valoresFicha[0],sessionEvaluar.getPlanifSelect().getNidEvaluacion()));
                }else{
                    sessionEvaluar.setLstCriteriosMultiples(ln_C_SFFichaCriterioLocal.getListaCriteriosByFicha(valoresFicha[0]));
                }
                buildTree();
                Utils.addTargetMany(btnGrabar,btnCalc,btnCmt);
            }
        }
    }
   
    private String buildTree() {
        sessionEvaluar.setPermisosTree(null);
        BeanCriterio b = new BeanCriterio();
        b.setDescripcionCriterio("::: Ficha de Evaluacion :::");
        b.setIsRaiz("1");
        b.setDisplayInput("true");
        b.setDisplaySpinBox("false");
        List<BeanCriterio> lstBeanCriterio = new ArrayList<BeanCriterio>();
        Collections.sort(sessionEvaluar.getLstCriteriosMultiples(), new Comparator<BeanCriterio>() {
            public int compare(BeanCriterio s1,BeanCriterio s2) {
                return s1.getOrden().compareTo(s2.getOrden());
            }
        });
        b.setLstIndicadores(sessionEvaluar.getLstCriteriosMultiples());
        lstBeanCriterio.add(b);
        permisosTree = new ChildPropertyTreeModel(lstBeanCriterio,"lstIndicadores");
        sessionEvaluar.setPermisosTree(permisosTree);
        if(trFich != null){
            trFich.setValue(permisosTree);
            Utils.addTarget(trFich);
        }
        return null;
    }
    
    public void calcularNota(ActionEvent actionEvent) {
        ChildPropertyTreeModel permisosTree = sessionEvaluar.getPermisosTree();
        List<BeanCriterio> lstBeanCriterio = (List<BeanCriterio>) permisosTree.getWrappedData();
        Iterator it = lstBeanCriterio.get(0).getLstIndicadores().iterator();
        int sizeCrits = lstBeanCriterio.get(0).getLstIndicadores().size();
        double notaFinal = 0.0;
        double notaProm = 0.0;
        while(it.hasNext()){
            BeanCriterio crit = (BeanCriterio) it.next();
            List<BeanCriterio> hijos = crit.getLstIndicadores();
            Iterator itH = hijos.iterator();
            String valInput = "";
            int hijosSize = hijos.size();
            int sumVal = 0;
            int maxVal = sessionEvaluar.getMaxValor() * hijosSize;
            while(itH.hasNext()){
                BeanCriterio indi = (BeanCriterio) itH.next();
                sumVal = sumVal + indi.getValorSpinBox();
            }
            int pro = sumVal / hijosSize;
            double vigecimal = (sumVal * 20) / maxVal;
            String estilo = "";
            if(vigecimal <= 10.49){
                estilo = "rojo";
            }else if(vigecimal >= 10.50 && vigecimal <= 15.49){
                estilo = "amarillo";
            }else{
                estilo = "verde";
            }
            crit.setEstilo(estilo);
            crit.setNotaVige(vigecimal);
            valInput = "  "+pro + " / "+ vigecimal+"  ";
            crit.setValorInput(valInput);
            notaProm = notaProm + crit.getNotaVige();
        }
        int r = (int) Math.round( (notaProm/sizeCrits) * 100);
        notaFinal = r / 100.0;
        String estilo = "";
        if(notaFinal <= 10.49){
            estilo = "rojo";
        }else if(notaFinal >= 10.50 && notaFinal <= 15.49){
            estilo = "amarillo";
        }else{
            estilo = "verde";
        }
        sessionEvaluar.setNotaFinal(notaFinal);
        sessionEvaluar.setEstiloFinal(estilo);
        lstBeanCriterio.get(0).setEstilo(estilo);
        lstBeanCriterio.get(0).setValorInput(notaFinal+"");
        if(trFich != null){
            trFich.setValue(sessionEvaluar.getPermisosTree());
            Utils.addTarget(trFich);
        }
    }
    
    public void cambiarValor(ValueChangeEvent vce) {
        try {
            Integer param = (Integer) vce.getComponent().getAttributes().get("idcrit");
            Integer paramPapa = (Integer) vce.getComponent().getAttributes().get("idcritPapa");
            ChildPropertyTreeModel permisosTree = sessionEvaluar.getPermisosTree();
            List<BeanCriterio> lstBeanCriterio = (List<BeanCriterio>) permisosTree.getWrappedData();
            Iterator it = lstBeanCriterio.get(0).getLstIndicadores().iterator(); //sessionEvaluar.getLstCriteriosMultiples().iterator();
            while(it.hasNext()){
                BeanCriterio crit = (BeanCriterio) it.next();
                if(crit.getNidCriterio().compareTo(paramPapa) == 0){
                    List<BeanCriterio> hijos = crit.getLstIndicadores();
                    Iterator itH = hijos.iterator();
                    int hijosSize = hijos.size();
                    int sumVal = 0;
                    int maxVal = sessionEvaluar.getMaxValor() * hijosSize;
                    while(itH.hasNext()){
                        BeanCriterio indi = (BeanCriterio) itH.next();
                        if(indi.getNidCriterio().compareTo(param) == 0){
                            indi.setValorSpinBox((Integer) vce.getNewValue());
                        }
                        sumVal = sumVal + indi.getValorSpinBox();
                    }
                    double vigecimal = (sumVal * 20) / maxVal;
                    crit.setNotaVige(vigecimal);
                }
            }
        } catch (Exception nfe) {
            nfe.printStackTrace();
        }
    }
    
    public void grabarEvaluacion(ActionEvent actionEvent) {
        boolean reset = true;
        try {
            int severidad = 0;
            BeanError error = new BeanError();
            if (this.isOK()) {
                error = ln_T_SFEvaluacionLocal.registrarEvaluacion_LN_Web(sessionEvaluar.getLstCriteriosMultiples(),
                                                                          sessionEvaluar.getPlanifSelect().getNidEvaluacion(),
                                                                          usuario.getNidUsuario(),
                                                                          sessionEvaluar.getComentarioEvaluador(),
                                                                          usuario.getNidLog());
                if("000".equalsIgnoreCase(error.getCidError())){
                    severidad = 3;
                }else{
                    severidad = 1;
                    reset = false;
                }
            } else {
                reset = false;
                severidad = 1;
                error.setTituloError("Faltan campos por llenar");
                error.setDescripcionError("Asigne un valor mayor a cero para todos los indicadores");
            }
            msjGen.setText(error.getTituloError());
            Utils.addTarget(msjGen);
            Utils.mostrarMensaje(ctx,error.getDescripcionError(),error.getTituloError(), severidad);
        } catch (Exception e) {
            e.printStackTrace();
            msjGen.setText("Error del sistema");
            Utils.addTarget(msjGen);
            Utils.mostrarMensaje(ctx,"Error del sistema, comuniquese con el administrador o intente nuevamente","Error del sistema",2);
            ln_T_SFLoggerLocal.registrarLogErroresSistema(usuario.getNidLog(), 
                                                          "BAC",
                                                          CLASE, 
                                                          "void grabarEvaluacion(ActionEvent actionEvent)",
                                                          "Error en el backing al registrar la Evaluacion ",Utils.getStack(e));
        } finally {
            if(reset){
                resetearAfterGrabar();   
            }
        }
    }
    
    public void grabarEvaluacionParcial(ActionEvent actionEvent) {
        try {
            int severidad = 0;
            BeanError error = new BeanError();
            if (this.isOKParcial()) {
                error = ln_T_SFEvaluacionLocal.registrarEvaluacion_Parcial_LN_Web(sessionEvaluar.getLstCriteriosMultiples(),
                                                                                  sessionEvaluar.getPlanifSelect().getNidEvaluacion(),
                                                                                  usuario.getNidUsuario(),
                                                                                  sessionEvaluar.getComentarioEvaluador(),
                                                                                  usuario.getNidLog(),
                                                                                  "0".equals(sessionEvaluar.getPlanifSelect().getFlgParcial()) ? true : false);
                if("000".equalsIgnoreCase(error.getCidError())){
                    severidad = 3;
                    sessionEvaluar.getPlanifSelect().setFlgParcial("1");//Se le cambia a uno para saber que ya se grabo parcial y en las siguientes borre los valores grabados
                }else{
                    severidad = 1;
                }
            } else {
                severidad = 1;
                error.setTituloError("Debe llenar al menos 5 indicadores");
                error.setDescripcionError("Asigne un valor mayor a cero para los indicadores, minimo 5");
            }
            msjGen.setText(error.getTituloError());
            Utils.addTarget(msjGen);
            Utils.mostrarMensaje(ctx,error.getDescripcionError(),error.getTituloError(), severidad);
        } catch (Exception e) {
            e.printStackTrace();
            msjGen.setText("Error del sistema");
            Utils.addTarget(msjGen);
            Utils.mostrarMensaje(ctx,"Error del sistema, comuniquese con el administrador o intente nuevamente","Error del sistema",2);
            ln_T_SFLoggerLocal.registrarLogErroresSistema(usuario.getNidLog(), 
                                                          "BAC",
                                                          CLASE, 
                                                          "void grabarEvaluacionParcial(ActionEvent actionEvent)",
                                                          "Error en el backing al registrar la Evaluacion Parcial",Utils.getStack(e));
        }
    }
    
    private boolean isOK(){
        Iterator it = sessionEvaluar.getLstCriteriosMultiples().iterator();
        while(it.hasNext()){
            BeanCriterio crit = (BeanCriterio) it.next();
            List<BeanCriterio> hijos = crit.getLstIndicadores();
            Iterator itH = hijos.iterator();
            while(itH.hasNext()){
                BeanCriterio indi = (BeanCriterio) itH.next();
                if(indi.getValorSpinBox() <= 0){
                    return false;
                }
            }
        }
        return true;
    }
    
    private boolean isOKParcial(){
        Iterator it = sessionEvaluar.getLstCriteriosMultiples().iterator();
        int cant = 0;
        while(it.hasNext()){
            BeanCriterio crit = (BeanCriterio) it.next();
            List<BeanCriterio> hijos = crit.getLstIndicadores();
            Iterator itH = hijos.iterator();
            while(itH.hasNext()){
                BeanCriterio indi = (BeanCriterio) itH.next();
                if(indi.getValorSpinBox() > 0){
                    cant++;
                    if(cant >= 5){
                        return true;
                    }
                }
            }
        }
        if(cant < 5){
            return false;
        }else{
            return true;
        }
    }
    
    public void resetearAfterGrabar(){
        sessionEvaluar.setVisiblePanelBoxPanelBoxFicha(false);
        sessionEvaluar.setPlanifSelect(null);
        sessionEvaluar.setEstiloFinal(null);
        sessionEvaluar.setNotaFinal(0.0);
        sessionEvaluar.setComentarioEvaluador(null);
        btnGrabar.setVisible(false);
        btnCalc.setVisible(false);
        btnCmt.setVisible(false);
        trFich.setVisible(false);
        btnRegistrar.setDisabled(true);
        try {
            List<UIComponent> children = null;
            children = this.trFich.getChildren();
            if (children != null) {
                Iterator it = children.iterator();
                while(it.hasNext()){
                    UIComponent child = (UIComponent) it.next();
                    it.remove();   
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Utils.addTargetMany(btnGrabar,trFich,btnRegistrar,btnCalc,btnCmt);
        mostrarPlanificacionesParaHoy();
    }
    
    public void abrirPopCommt(ActionEvent actionEvent) {//btnCmt
        Utils.showPopUpMIDDLE(popCmt);
    }
    
    public void cancelarDialogComment(ClientEvent clientEvent) {
        sessionEvaluar.setComentarioEvaluador(null);
        itCmmt.resetValue();
        Utils.addTarget(itCmmt);
        FacesContext fctx = FacesContext.getCurrentInstance();
        fctx.renderResponse();
        popCmt.hide();
    }
    
    public void setSessionEvaluar(bSessionEvaluar sessionEvaluar) {
        this.sessionEvaluar = sessionEvaluar;
    }

    public bSessionEvaluar getSessionEvaluar() {
        return sessionEvaluar;
    }

    public void setUsuario(BeanUsuario usuario) {
        this.usuario = usuario;
    }

    public BeanUsuario getUsuario() {
        return usuario;
    }

    public void setTbPlan(RichTable tbPlan) {
        this.tbPlan = tbPlan;
    }

    public RichTable getTbPlan() {
        return tbPlan;
    }

    public void setTrFich(RichTreeTable trFich) {
        this.trFich = trFich;
    }

    public RichTreeTable getTrFich() {
        return trFich;
    }

    public void setPermisosTree(ChildPropertyTreeModel permisosTree) {
        this.permisosTree = permisosTree;
    }

    public ChildPropertyTreeModel getPermisosTree() {
        return permisosTree;
    }

    public void setBtnRegistrar(RichButton btnRegistrar) {
        this.btnRegistrar = btnRegistrar;
    }

    public RichButton getBtnRegistrar() {
        return btnRegistrar;
    }

    public void setBtnGrabar(RichButton btnGrabar) {
        this.btnGrabar = btnGrabar;
    }

    public RichButton getBtnGrabar() {
        return btnGrabar;
    }

    public void setMsjGen(RichMessages msjGen) {
        this.msjGen = msjGen;
    }

    public RichMessages getMsjGen() {
        return msjGen;
    }

    public void setItCmmt(RichInputText itCmmt) {
        this.itCmmt = itCmmt;
    }

    public RichInputText getItCmmt() {
        return itCmmt;
    }

    public void setBtnCalc(RichButton btnCalc) {
        this.btnCalc = btnCalc;
    }

    public RichButton getBtnCalc() {
        return btnCalc;
    }

    public void setPopCmt(RichPopup popCmt) {
        this.popCmt = popCmt;
    }

    public RichPopup getPopCmt() {
        return popCmt;
    }

    public void setBtnCmt(RichButton btnCmt) {
        this.btnCmt = btnCmt;
    }

    public RichButton getBtnCmt() {
        return btnCmt;
    }

    public void setPopMsj(RichPopup popMsj) {
        this.popMsj = popMsj;
    }

    public RichPopup getPopMsj() {
        return popMsj;
    }

    public void setOtError(RichActiveOutputText otError) {
        this.otError = otError;
    }

    public RichActiveOutputText getOtError() {
        return otError;
    }

    public void setError(String _error) {
        this._error = _error;
    }

    public String getError() {
        return _error;
    }
}
