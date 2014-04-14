package sped.vista.beans.evaluacion.evaluar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.data.RichTreeTable;
import oracle.adf.view.rich.component.rich.nav.RichButton;
import oracle.adf.view.rich.component.rich.output.RichMessages;
import org.apache.myfaces.trinidad.event.SelectionEvent;
import org.apache.myfaces.trinidad.model.ChildPropertyTreeModel;
import sped.negocio.LNSF.IL.LN_C_SFFichaCriterioLocal;
import sped.negocio.LNSF.IL.LN_C_SFFichaLocal;
import sped.negocio.LNSF.IR.LN_C_SFEvaluacionRemote;
import sped.negocio.entidades.beans.BeanCriterio;
import sped.negocio.entidades.beans.BeanEvaluacionWS;
import sped.negocio.entidades.beans.BeanUsuario;

import sped.vista.Utils.Utils;

public class bEvaluar {
    
    private bSessionEvaluar sessionEvaluar;
    @EJB
    private LN_C_SFEvaluacionRemote ln_C_SFEvaluacionRemote;
    @EJB
    private LN_C_SFFichaCriterioLocal ln_C_SFFichaCriterioLocal;
    @EJB
    private LN_C_SFFichaLocal ln_C_SFFichaLocal;
    private BeanUsuario usuario = (BeanUsuario) Utils.getSession("USER");
    private RichTable tbPlan;
    private RichTreeTable trFich;
    private ChildPropertyTreeModel permisosTree;
    private RichButton btnRegistrar;
    private RichButton btnGrabar;
    private RichMessages msjGen;
    FacesContext ctx = FacesContext.getCurrentInstance();

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
                Utils.addTarget(tbPlan);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void selectPlanificacion(SelectionEvent se) {
        BeanEvaluacionWS planif = (BeanEvaluacionWS) Utils.getRowTable(se);
        sessionEvaluar.setPlanifSelect(planif);
        btnRegistrar.setDisabled(false);
        Utils.addTarget(btnRegistrar);
    }
    
    public void registrarEvaluacion(ActionEvent actionEvent) {
        int valoresFicha[] = ln_C_SFFichaLocal.getFichaToEvaluar("E",sessionEvaluar.getPlanifSelect().getTipoFichaCurso());
        if(valoresFicha != null){
            if(valoresFicha[0] != 0){
                sessionEvaluar.setMaxValor(valoresFicha[1]);
                sessionEvaluar.setVisiblePanelBoxPanelBoxFicha(true);
                sessionEvaluar.setLstCriteriosMultiples(ln_C_SFFichaCriterioLocal.getListaCriteriosByFicha(valoresFicha[0]));       
                buildTree();
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
    
    public void cambiarValor(ValueChangeEvent vce) {
        try {
            Integer param = (Integer) vce.getComponent().getAttributes().get("idcrit");
            Integer paramPapa = (Integer) vce.getComponent().getAttributes().get("idcritPapa");
            ChildPropertyTreeModel permisosTree = sessionEvaluar.getPermisosTree();
            List<BeanCriterio> lstBeanCriterio = (List<BeanCriterio>) permisosTree.getWrappedData();
            Iterator it = lstBeanCriterio.get(0).getLstIndicadores().iterator(); //sessionEvaluar.getLstCriteriosMultiples().iterator();
            int sizeCrits = lstBeanCriterio.get(0).getLstIndicadores().size();//sessionEvaluar.getLstCriteriosMultiples().size();
            double notaFinal = 0.0;
            double notaProm = 0.0;
            while(it.hasNext()){
                BeanCriterio crit = (BeanCriterio) it.next();
                if(crit.getNidCriterio().compareTo(paramPapa) == 0){
                    List<BeanCriterio> hijos = crit.getLstIndicadores();
                    Iterator itH = hijos.iterator();
                    String valInput = "";
                    int hijosSize = hijos.size();
                    int sumVal = 0;
                    int maxVal = sessionEvaluar.getMaxValor() * hijosSize;
                    while(itH.hasNext()){
                        BeanCriterio indi = (BeanCriterio) itH.next();
                        //Utils.sysout("hijo: "+indi.getDescripcionCriterio());
                        if(indi.getNidCriterio().compareTo(param) == 0){
                            indi.setValorSpinBox((Integer) vce.getNewValue());
                        }
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
                }
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
        } catch (Exception nfe) {
            nfe.printStackTrace();
        }
    }
    
    public void grabarEvaluacion(ActionEvent actionEvent) {
        int severidad = 0;
        String desc = "";
        String titulo = "";
        if(this.isOK()){
            
        }else{
            severidad = 1;
        }
        Utils.mostrarMensaje(ctx,desc,titulo,severidad);
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
}
