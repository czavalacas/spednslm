package sped.vista.beans.evaluacion.grafico_reporte;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.annotation.PostConstruct;

import javax.ejb.EJB;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.FacesEvent;
import javax.faces.model.SelectItem;

import jxl.write.DateTime;

import oracle.adf.view.faces.bi.event.ClickEvent;
import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.RichSubform;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.input.RichSelectManyChoice;

import oracle.adf.view.rich.component.rich.input.RichTextEditor;
import oracle.adf.view.rich.component.rich.layout.RichPanelDashboard;
import oracle.adf.view.rich.component.rich.layout.RichPanelFormLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.component.rich.layout.RichShowDetail;

import oracle.adf.view.rich.event.DialogEvent;

import oracle.dss.dataView.Attributes;
import oracle.dss.dataView.ComponentHandle;

import oracle.dss.dataView.DataComponentHandle;

import oracle.dss.dataView.SeriesComponentHandle;

import sped.negocio.LNSF.IL.LN_C_SFEvaluacionLocal;
import sped.negocio.LNSF.IL.LN_C_SFUsuarioLocal;
import sped.negocio.LNSF.IR.LN_C_SFAreaAcademicaRemote;
import sped.negocio.LNSF.IR.LN_C_SFCorreoRemote;
import sped.negocio.LNSF.IR.LN_C_SFRolRemote;
import sped.negocio.LNSF.IR.LN_C_SFSedeRemote;
import sped.negocio.LNSF.IR.LN_C_SFUsuarioRemote;
import sped.negocio.Utils.Utiles;
import sped.negocio.entidades.beans.BeanAreaAcademica;
import sped.negocio.entidades.beans.BeanEvaluacion;
import sped.negocio.entidades.beans.BeanRol;
import sped.negocio.entidades.beans.BeanSede;
import sped.negocio.entidades.beans.BeanUsuario;

import sped.vista.Utils.Utils;

public class bDesempenoEvaluador {
    private bSessionDesempenoEvaluador sessionDesempenoEvaluador;
    private RichSelectManyChoice choiceFSede;
    @EJB
    private LN_C_SFSedeRemote ln_C_SFSedeRemote;
    @EJB
    private LN_C_SFAreaAcademicaRemote ln_C_SFAreaAcademicaRemote;
    @EJB
    private LN_C_SFRolRemote ln_C_SFRolRemote;
    @EJB
    private LN_C_SFUsuarioLocal ln_C_SFUsuarioLocal;
    @EJB
    private LN_C_SFEvaluacionLocal ln_C_SFEvaluacionLocal;
    @EJB
    private LN_C_SFCorreoRemote ln_C_SFCorreoRemote;
    FacesContext ctx = FacesContext.getCurrentInstance();
    private BeanUsuario beanUsuario = (BeanUsuario) Utils.getSession("USER");
    BeanUsuario beanUsu;
    private String nombreUsuario;
    private RichSelectManyChoice choiceFArea;
    private RichSelectManyChoice choiceFRol;
    private RichSelectManyChoice choiceFEva;
    private RichShowDetail sd1;
    private RichShowDetail sd2;
    private RichSubform s2;
    private RichPanelFormLayout pfl1;
    private RichPanelDashboard pdash1;
    private RichPopup popDetalle;
    private RichPopup popDetPie;
    private RichPopup popCorreo;
    private RichPanelGroupLayout pgl2;
    Calendar cal= new GregorianCalendar();
    private RichTextEditor rte1;
    private String mensaje;

    public bDesempenoEvaluador() {
        nombreUsuario = beanUsuario.getNombres();
    }
    
    @PostConstruct
    public void methodInvokeOncedOnPageLoad() {
        if(sessionDesempenoEvaluador.getExec() == 0) {
            sessionDesempenoEvaluador.setLstRol(llenarComboRol());
            sessionDesempenoEvaluador.setLstEvaArea(llenarComboEvaArea());
            sessionDesempenoEvaluador.setLstEvaSede(llenarComboEvaSede());
            sessionDesempenoEvaluador.setLstEvaGeneral(llenarComboEvaGeneral());
            sessionDesempenoEvaluador.setLstSede(llenarComboSede());
            sessionDesempenoEvaluador.setLstArea(llenarComboAreaA());                        
            try{                
                SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
                String resultado = formato.format(cal.getTime()); 
                sessionDesempenoEvaluador.setFechaActual(formato.parse(resultado));
                cal.add(Calendar.MONTH, -1);
                resultado = formato.format(cal.getTime()); 
                sessionDesempenoEvaluador.setFechaAnterior(formato.parse(resultado));  
            }catch(Exception e){
                e.printStackTrace();
            }                      
            sessionDesempenoEvaluador.setFechaEI(sessionDesempenoEvaluador.getFechaAnterior());
            sessionDesempenoEvaluador.setFechaEI_aux(sessionDesempenoEvaluador.getFechaAnterior());
            sessionDesempenoEvaluador.setFechaEF(sessionDesempenoEvaluador.getFechaActual());
            sessionDesempenoEvaluador.setFechaEF_aux(sessionDesempenoEvaluador.getFechaActual());
            setListEvaFiltro_aux();            
            sessionDesempenoEvaluador.setExec(1);
        }        
    }
    
    private ArrayList llenarComboRol(){
        ArrayList unItems = new ArrayList();
        List<BeanRol> lstbean = ln_C_SFRolRemote.getListRolbyNombreLN("Evaluador");
        for(BeanRol r : lstbean){
            unItems.add(new SelectItem(r.getNidRol(),
                                       r.getDescripcionRol().toString()));
        }
        return unItems;
    }
    
    private ArrayList llenarComboEvaArea(){
        ArrayList unItems = new ArrayList();
        List<BeanUsuario> lstbean = ln_C_SFUsuarioLocal.getUsuariobyNidRolLN(2);
        for(BeanUsuario u : lstbean){
            unItems.add(new SelectItem(u.getNidUsuario(),
                                       u.getNombres().toString()));
        }
        return unItems;
    }
    
    private ArrayList llenarComboEvaSede(){
        ArrayList unItems = new ArrayList();
        List<BeanUsuario> lstbean = ln_C_SFUsuarioLocal.getUsuariobyNidRolLN(4);
        for(BeanUsuario u : lstbean){
            unItems.add(new SelectItem(u.getNidUsuario(),
                                       u.getNombres().toString()));
        }
        return unItems;
    }
    
    private ArrayList llenarComboEvaGeneral(){
        ArrayList unItems = new ArrayList();
        List<BeanUsuario> lstbean = ln_C_SFUsuarioLocal.getUsuariobyNidRolLN(5);
        for(BeanUsuario u : lstbean){
            unItems.add(new SelectItem(u.getNidUsuario(),
                                       u.getNombres().toString()));
        }
        return unItems;
    }
    
    private ArrayList llenarComboSede(){
        ArrayList unItems = new ArrayList();
        List<BeanSede> lstbean = ln_C_SFSedeRemote.getSedeLN();
        for(BeanSede b : lstbean){
            unItems.add(new SelectItem(b.getNidSede(),
                                       b.getDescripcionSede().toString()));
        }
        return unItems;
    }
    
    private ArrayList llenarComboAreaA() {
        ArrayList unItems = new ArrayList();
        List<BeanAreaAcademica> beanAreaA = ln_C_SFAreaAcademicaRemote.getAreaAcademicaLN();
        for(BeanAreaAcademica a : beanAreaA){
            SelectItem se = new SelectItem(a.getNidAreaAcademica(), 
                                           a.getDescripcionAreaAcademica().toString());
            unItems.add(se);
        }
        return unItems;
    }
    
    public void limpiarFiltro(ActionEvent actionEvent) {
        sessionDesempenoEvaluador.setFechaEF(sessionDesempenoEvaluador.getFechaActual());
        sessionDesempenoEvaluador.setFechaEI(sessionDesempenoEvaluador.getFechaAnterior());
        sessionDesempenoEvaluador.setFechaPF(null);
        sessionDesempenoEvaluador.setFechaPI(null);
        sessionDesempenoEvaluador.setSelectedArea(null);
        sessionDesempenoEvaluador.setSelectedEvaluador(null);
        sessionDesempenoEvaluador.setSelectedRol(null);
        sessionDesempenoEvaluador.setSelectedSede(null);
        //valores auxiliares//
        sessionDesempenoEvaluador.setSelectedRol_aux(null);
        sessionDesempenoEvaluador.setSelectedEvaluador_aux(null);
        sessionDesempenoEvaluador.setSelectedSede_aux(null);
        sessionDesempenoEvaluador.setSelectedArea_aux(null);
        sessionDesempenoEvaluador.setFechaPI_axu(null);
        sessionDesempenoEvaluador.setFechaPF_aux(null);
        sessionDesempenoEvaluador.setFechaEI_aux(sessionDesempenoEvaluador.getFechaAnterior());
        sessionDesempenoEvaluador.setFechaEF_aux(sessionDesempenoEvaluador.getFechaActual());
        setListEvaFiltro_aux();
        Utils.addTargetMany(pfl1,sd1,sd2,pgl2); 
    }
    
    public void buscarByFiltro(ActionEvent actionEvent) {
        setListEvaFiltro_aux();
        sessionDesempenoEvaluador.setSelectedRol_aux(sessionDesempenoEvaluador.getSelectedRol());
        sessionDesempenoEvaluador.setSelectedEvaluador_aux(sessionDesempenoEvaluador.getSelectedEvaluador());
        sessionDesempenoEvaluador.setSelectedSede_aux(sessionDesempenoEvaluador.getSelectedSede());
        sessionDesempenoEvaluador.setSelectedArea_aux(sessionDesempenoEvaluador.getSelectedArea());
        sessionDesempenoEvaluador.setFechaPI_axu(sessionDesempenoEvaluador.getFechaPI());
        sessionDesempenoEvaluador.setFechaPF_aux(sessionDesempenoEvaluador.getFechaPF());
        sessionDesempenoEvaluador.setFechaEI_aux(sessionDesempenoEvaluador.getFechaEI());
        sessionDesempenoEvaluador.setFechaEF_aux(sessionDesempenoEvaluador.getFechaEF());
    }
    
    public void setListEvaFiltro_aux(){
        List <BeanEvaluacion> lst = desempenoFiltro(1, null, null, null,null);
        List <BeanEvaluacion> lstDate = desempenoFiltro(3, null, null, null,null);
        List <BeanEvaluacion> lstPie = desempenoFiltro(4, null, null, null,null);
        List <BeanEvaluacion> lstBarRol = desempenoFiltro(5, null, null, null,null);
        sessionDesempenoEvaluador.setLstEvaTable(lst);
        setListEvabarChart(lst);
        setListLinegraph(lstDate);
        setListPiegraph(lstPie);
        setListEvabarChartRol(lstBarRol);
        if(pgl2 != null){
            Utils.addTargetMany(pgl2);
        }        
    }
    
    public List<BeanEvaluacion> desempenoFiltro(int tipoEvento,
                                                String nombre,
                                                String estado,
                                                String desProb,
                                                String desRol){      
       return ln_C_SFEvaluacionLocal.getDesempenoEvaluacionbyFiltroLN(tipoEvento,nombre,estado,desProb, desRol,
                                                                      sessionDesempenoEvaluador.getSelectedRol(),
                                                                      sessionDesempenoEvaluador.getSelectedEvaluador(),
                                                                      sessionDesempenoEvaluador.getSelectedSede(),
                                                                      sessionDesempenoEvaluador.getSelectedArea(),
                                                                      sessionDesempenoEvaluador.getFechaPI(),
                                                                      sessionDesempenoEvaluador.getFechaPF(),
                                                                      sessionDesempenoEvaluador.getFechaEI(),
                                                                      sessionDesempenoEvaluador.getFechaEF());     
    }
    
    public List<BeanEvaluacion> desempenoFiltro_Aux(int tipoEvento,
                                                    String nombre,
                                                    String estado,
                                                    String desProb,
                                                    String desRol){
        return ln_C_SFEvaluacionLocal.getDesempenoEvaluacionbyFiltroLN(tipoEvento,nombre,estado,desProb,desRol,
                                                                       sessionDesempenoEvaluador.getSelectedRol_aux(),
                                                                       sessionDesempenoEvaluador.getSelectedEvaluador_aux(),
                                                                       sessionDesempenoEvaluador.getSelectedSede_aux(),
                                                                       sessionDesempenoEvaluador.getSelectedArea_aux(),
                                                                       sessionDesempenoEvaluador.getFechaPI_axu(),
                                                                       sessionDesempenoEvaluador.getFechaPF_aux(),
                                                                       sessionDesempenoEvaluador.getFechaEI_aux(),
                                                                       sessionDesempenoEvaluador.getFechaEF_aux());
    }
    
    public void setListEvabarChart(List <BeanEvaluacion> lst){
        List<Object[]> lstEva = new ArrayList();
        String nombreEvaluador;
        int contEjecutados, contPendiente, contNoEje, contNoEjeJ;
        for(int i=0; i<lst.size(); i++){            
            nombreEvaluador = lst.get(i).getNombreEvaluador();
            contEjecutados = lst.get(i).getCantEjecutado();
            contPendiente = lst.get(i).getCantPendiente();
            contNoEje = lst.get(i).getCantNoEjecutado();
            contNoEjeJ = lst.get(i).getCantNoJEjecutado();
            Object[] obj1 = { nombreEvaluador, "Ejecutado", contEjecutados};
            Object[] obj2 = { nombreEvaluador, "Pendiente", contPendiente};
            Object[] obj3 = { nombreEvaluador, "No ejecutado", contNoEje};
            Object[] obj4 = { nombreEvaluador, "No Justifico", contNoEjeJ};
            lstEva.add(obj1);
            lstEva.add(obj2);
            lstEva.add(obj3);
            lstEva.add(obj3);
            lstEva.add(obj4);
        }
        sessionDesempenoEvaluador.setLstEvaBarChart(lstEva);        
    }
    
    public void setListLinegraph(List <BeanEvaluacion> lst){
        List<Object[]> lstEva = new ArrayList();
        int contEjecutados, contPendiente, contNoEje, contNoEjeJ;
        for(int i=0; i<lst.size(); i++){
            Date date = lst.get(i).getEndDate();
            contEjecutados = lst.get(i).getCantEjecutado();
            contPendiente = lst.get(i).getCantPendiente();
            contNoEje = lst.get(i).getCantNoEjecutado();
            contNoEjeJ = lst.get(i).getCantNoJEjecutado();
            Object[] obj1 = { date, "Ejecutado", contEjecutados};
            Object[] obj2 = { date, "Pendiente", contPendiente};
            Object[] obj3 = { date, "No ejecutado", contNoEje};
            Object[] obj4 = { date, "No Justifico", contNoEjeJ};
            lstEva.add(obj1);
            lstEva.add(obj2);
            lstEva.add(obj3);
            lstEva.add(obj3);
            lstEva.add(obj4);
        }
        sessionDesempenoEvaluador.setLstEvaLineG(lstEva);
    }
    
    public void setListEvabarChartRol(List <BeanEvaluacion> lst){
        List<Object[]> lstEva = new ArrayList();
        String descripcionRol;
        int contEjecutados, contPendiente, contNoEje, contNoEjeJ;
        for(int i=0; i<lst.size(); i++){            
            descripcionRol = lst.get(i).getDescripcion();
            contEjecutados = lst.get(i).getCantEjecutado();
            contPendiente = lst.get(i).getCantPendiente();
            contNoEje = lst.get(i).getCantNoEjecutado();
            contNoEjeJ = lst.get(i).getCantNoJEjecutado();
            Object[] obj1 = { descripcionRol, "Ejecutado", contEjecutados};
            Object[] obj2 = { descripcionRol, "Pendiente", contPendiente};
            Object[] obj3 = { descripcionRol, "No ejecutado", contNoEje};
            Object[] obj4 = { descripcionRol, "No Justifico", contNoEjeJ};
            lstEva.add(obj1);
            lstEva.add(obj2);
            lstEva.add(obj3);
            lstEva.add(obj3);
            lstEva.add(obj4);
        }
        sessionDesempenoEvaluador.setLstEvaBarChartRol(lstEva);        
    }
    
    public void setListPiegraph(List <BeanEvaluacion> lst){
        List<Object[]> lstEva = new ArrayList();
        for(int i=0; i<lst.size(); i++){
            BeanEvaluacion eva = lst.get(i);
            Object[] obj1 = { "Problemas frecuentes", eva.getDescProblema(), eva.getCantProblema()};
            lstEva.add(obj1);
        }
        sessionDesempenoEvaluador.setLstEvaPieG(lstEva);
    }
    
    public void clickListenerGraph1(ClickEvent clickEvent) {  
        ComponentHandle handle = clickEvent.getComponentHandle();
        String nombre = null;
        String estado = null;
        if (handle instanceof DataComponentHandle) {
            DataComponentHandle dhandle = (DataComponentHandle)handle;
            Attributes[] groupInfo = dhandle.getGroupAttributes();
            Attributes[] seriesInfo = dhandle.getSeriesAttributes();
            if (groupInfo != null) {
              for (Attributes attrs : groupInfo) {
                nombre = (String)attrs.getValue(Attributes.LABEL_VALUE);
              }
              for (Attributes attrs : seriesInfo) {
                  estado = (String)attrs.getValue(Attributes.LABEL_VALUE);
              }
            }
        }
        if(nombre != null && estado != null){
            List <BeanEvaluacion> lst = desempenoFiltro_Aux(2, nombre, estado, null, null);
            sessionDesempenoEvaluador.setLstEvaDetalle(lst);
            beanUsu = ln_C_SFUsuarioLocal.findConstrainByIdLN(lst.get(0).getNidEvaluador());            
            sessionDesempenoEvaluador.setEvaluador(beanUsu);
            sessionDesempenoEvaluador.setEstado(estado);
            estadoEvaluacion(estado);            
            renderRol(beanUsu.getRol().getNidRol());
            renderMensaje(estado);
            Utils.showPopUpMIDDLE(popDetalle); 
        }        
    }
    
    public void clickListenerHBar(ClickEvent clickEvent) {           
        ComponentHandle handle = clickEvent.getComponentHandle();
        String rol = null;
        String estado = null;
        if (handle instanceof DataComponentHandle) {
            DataComponentHandle dhandle = (DataComponentHandle)handle;
            Attributes[] groupInfo = dhandle.getGroupAttributes();
            Attributes[] seriesInfo = dhandle.getSeriesAttributes();
            if (groupInfo != null) {
              for (Attributes attrs : groupInfo) {
                rol = (String)attrs.getValue(Attributes.LABEL_VALUE);
              }
              for (Attributes attrs : seriesInfo) {
                estado = (String)attrs.getValue(Attributes.LABEL_VALUE);
              }
            }
        }
        if(rol != null && estado != null){
            List <BeanEvaluacion> lst = desempenoFiltro_Aux(2, null, estado, null, rol);
            if(lst.size() > 0){
                renderRol(lst.get(0).getUsuario().getRol().getNidRol());
                estadoEvaluacion(estado);
                sessionDesempenoEvaluador.setLstEvaDetallePie(lst);
                sessionDesempenoEvaluador.setTitleDialog("Detalle "+rol+" - "+estado);
                Utils.showPopUpMIDDLE(popDetPie);
            }            
        }
    }
    
    public void bSendM(ActionEvent actionEvent) {
        Utils.showPopUpMIDDLE(popCorreo);
    }
    
    public void confirmarCorreo(DialogEvent dialogEvent) {
        DialogEvent.Outcome outcome = dialogEvent.getOutcome();
        if(outcome == DialogEvent.Outcome.ok){
            //String root = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/");
            //String fpath = root+"/recursos/reportes/"+getSessionBeanConsultarPregrado().getTiempoFile()+"_reporte.pdf";
            //Utiles.sysout("fpath: "+fpath);
            String[] data = new String[4];
            data[0] = "kaka";
            data[1] = "C:/Users/David/Desktop/Scan.jpg";
            data[2] = mensaje;
            data[3] = "davidangeleshuaman@gmail.com";
            String msj = ln_C_SFCorreoRemote.enviarCorreo(data);
            //throwError(ctx,msj);
            popCorreo.hide();
        }
    }
    
    public void clickListenerGraphPie(ClickEvent clickEvent) {
        ComponentHandle handle = clickEvent.getComponentHandle();
        String serie = null;
        if (handle instanceof DataComponentHandle) {
            DataComponentHandle dhandle = (DataComponentHandle)handle;
            Attributes[] seriesInfo = dhandle.getSeriesAttributes();
            if (seriesInfo != null) {
              for (Attributes attrs : seriesInfo) {
                serie = (String)attrs.getValue(Attributes.LABEL_VALUE);
              }
            }
        }
        if(serie != null){
            List <BeanEvaluacion> lst = desempenoFiltro_Aux(2, null, null, serie,null);
            sessionDesempenoEvaluador.setLstEvaDetallePie(lst);
            sessionDesempenoEvaluador.setTitleDialog("Problema "+serie);
            renderRol(0);
            sessionDesempenoEvaluador.setRenderProblema(false);
            sessionDesempenoEvaluador.setRenderComentario(true);
            Utils.showPopUpMIDDLE(popDetPie);
        }        
    }
    
    public void clickListenerGraphLine(ClickEvent event) {
        ComponentHandle handle = event.getComponentHandle();
        String serie = null;
        if (handle instanceof SeriesComponentHandle) {
            Attributes [] seriesInfo = ((SeriesComponentHandle)handle).getSeriesAttributes();            
            if(seriesInfo != null) {
                for(Attributes attrs: seriesInfo) {
                    serie = (String)attrs.getValue(Attributes.LABEL_VALUE);
                }
            }
        }
        if(serie != null){
            List <BeanEvaluacion> lst = desempenoFiltro_Aux(2, null, serie, null,null);
            sessionDesempenoEvaluador.setLstEvaDetallePie(lst);
            estadoEvaluacion(serie);
            renderRol(0);
            sessionDesempenoEvaluador.setTitleDialog("Evaluacion(s) "+serie);
            Utils.showPopUpMIDDLE(popDetPie);
        }
    }

    
    public void estadoEvaluacion(String estado){
        sessionDesempenoEvaluador.setRenderProblema(false);
        sessionDesempenoEvaluador.setRenderComentario(false);
        if(estado.compareTo("No ejecutado") == 0){
            sessionDesempenoEvaluador.setRenderProblema(true);
            sessionDesempenoEvaluador.setRenderComentario(true);
        }
    }
    
    public void renderRol(int nidRol){
        sessionDesempenoEvaluador.setRenderNivel(true);
        sessionDesempenoEvaluador.setRenderSede(true);
        sessionDesempenoEvaluador.setRenderArea(true);
        if(nidRol == 4){
            sessionDesempenoEvaluador.setRenderArea(false);            
        }
        if(nidRol == 2){
            sessionDesempenoEvaluador.setRenderNivel(false);
            sessionDesempenoEvaluador.setRenderSede(false);
        }        
    }
    
    public void renderMensaje(String estado){
        if(beanUsuario.getCorreo() != null &&  beanUsu.getCorreo() != null && estado.compareTo("No Justifico") == 0){
            sessionDesempenoEvaluador.setRenderMensaje(true);
        }else{
            sessionDesempenoEvaluador.setRenderMensaje(false);
        }
    }
    
    public void setSessionDesempenoEvaluador(bSessionDesempenoEvaluador sessionDesempenoEvaluador) {
        this.sessionDesempenoEvaluador = sessionDesempenoEvaluador;
    }

    public bSessionDesempenoEvaluador getSessionDesempenoEvaluador() {
        return sessionDesempenoEvaluador;
    }

    public void setChoiceFSede(RichSelectManyChoice choiceFSede) {
        this.choiceFSede = choiceFSede;
    }

    public RichSelectManyChoice getChoiceFSede() {
        return choiceFSede;
    }

    public void setChoiceFArea(RichSelectManyChoice choiceFArea) {
        this.choiceFArea = choiceFArea;
    }

    public RichSelectManyChoice getChoiceFArea() {
        return choiceFArea;
    }

    public void setChoiceFRol(RichSelectManyChoice choiceFRol) {
        this.choiceFRol = choiceFRol;
    }

    public RichSelectManyChoice getChoiceFRol() {
        return choiceFRol;
    }

    public void setChoiceFEva(RichSelectManyChoice choiceFEva) {
        this.choiceFEva = choiceFEva;
    }

    public RichSelectManyChoice getChoiceFEva() {
        return choiceFEva;
    }

    public void setSd1(RichShowDetail sd1) {
        this.sd1 = sd1;
    }

    public RichShowDetail getSd1() {
        return sd1;
    }

    public void setSd2(RichShowDetail sd2) {
        this.sd2 = sd2;
    }

    public RichShowDetail getSd2() {
        return sd2;
    }

    public void setS2(RichSubform s2) {
        this.s2 = s2;
    }

    public RichSubform getS2() {
        return s2;
    }

    public void setPfl1(RichPanelFormLayout pfl1) {
        this.pfl1 = pfl1;
    }

    public RichPanelFormLayout getPfl1() {
        return pfl1;
    }

    public void setPdash1(RichPanelDashboard pdash1) {
        this.pdash1 = pdash1;
    }

    public RichPanelDashboard getPdash1() {
        return pdash1;
    }

    public void setPopDetalle(RichPopup popDetalle) {
        this.popDetalle = popDetalle;
    }

    public RichPopup getPopDetalle() {
        return popDetalle;
    }

    public void setPopDetPie(RichPopup popDetPie) {
        this.popDetPie = popDetPie;
    }

    public RichPopup getPopDetPie() {
        return popDetPie;
    }

    public void setPopCorreo(RichPopup popCorreo) {
        this.popCorreo = popCorreo;
    }

    public RichPopup getPopCorreo() {
        return popCorreo;
    }    

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setPgl2(RichPanelGroupLayout pgl2) {
        this.pgl2 = pgl2;
    }

    public RichPanelGroupLayout getPgl2() {
        return pgl2;
    }

    public void setBeanUsu(BeanUsuario beanUsu) {
        this.beanUsu = beanUsu;
    }

    public BeanUsuario getBeanUsu() {
        return beanUsu;
    }

    public void setRte1(RichTextEditor rte1) {
        this.rte1 = rte1;
    }

    public RichTextEditor getRte1() {
        return rte1;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getMensaje() {
        return mensaje;
    }
}
