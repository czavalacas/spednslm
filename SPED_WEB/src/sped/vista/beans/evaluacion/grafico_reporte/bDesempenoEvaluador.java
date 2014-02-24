package sped.vista.beans.evaluacion.grafico_reporte;

import java.util.ArrayList;
import java.util.Date;
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
import oracle.adf.view.rich.component.rich.input.RichSelectManyChoice;

import oracle.adf.view.rich.component.rich.layout.RichPanelDashboard;
import oracle.adf.view.rich.component.rich.layout.RichPanelFormLayout;
import oracle.adf.view.rich.component.rich.layout.RichShowDetail;

import oracle.adf.view.rich.event.DialogEvent;

import oracle.dss.dataView.Attributes;
import oracle.dss.dataView.ComponentHandle;

import oracle.dss.dataView.DataComponentHandle;

import oracle.dss.dataView.SeriesComponentHandle;

import sped.negocio.LNSF.IL.LN_C_SFEvaluacionLocal;
import sped.negocio.LNSF.IL.LN_C_SFUsuarioLocal;
import sped.negocio.LNSF.IR.LN_C_SFAreaAcademicaRemote;
import sped.negocio.LNSF.IR.LN_C_SFRolRemote;
import sped.negocio.LNSF.IR.LN_C_SFSedeRemote;
import sped.negocio.LNSF.IR.LN_C_SFUsuarioRemote;
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
    private BeanUsuario beanUsuario = (BeanUsuario) Utils.getSession("USER");
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
        sessionDesempenoEvaluador.setFechaEF(null);
        sessionDesempenoEvaluador.setFechaEI(null);
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
        sessionDesempenoEvaluador.setFechaEI_aux(null);
        sessionDesempenoEvaluador.setFechaEF_aux(null);
        Utils.addTargetMany(pfl1,s2);
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
        if(pdash1 != null){
            Utils.addTargetMany(pdash1);
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
        sessionDesempenoEvaluador.setRenderNivel(true);
        sessionDesempenoEvaluador.setRenderSede(true);
        sessionDesempenoEvaluador.setRenderArea(true);  
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
            BeanUsuario beanUsu = ln_C_SFUsuarioLocal.findConstrainByIdLN(lst.get(0).getNidEvaluador());
            if(beanUsu.getRol().getNidRol() == 4){
                sessionDesempenoEvaluador.setRenderArea(false);            
            }
            if(beanUsu.getRol().getNidRol() == 2){
                sessionDesempenoEvaluador.setRenderNivel(false);
                sessionDesempenoEvaluador.setRenderSede(false);
            }
            sessionDesempenoEvaluador.setEvaluador(beanUsu);
            sessionDesempenoEvaluador.setEstado(estado);
            estadoEvaluacion(estado);
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
            List <BeanEvaluacion> lst = desempenoFiltro_Aux(6, null, estado, null, rol);
            sessionDesempenoEvaluador.setLstEvaDetallePie(lst);
            sessionDesempenoEvaluador.setTitleDialog("Detalle "+rol);
            Utils.showPopUpMIDDLE(popDetPie);
        }
    }
    
    public void bSendM(ActionEvent actionEvent) {
        Utils.showPopUpMIDDLE(popCorreo);
    }
    
    public void confirmarCorreo(DialogEvent dialogEvent) {
        DialogEvent.Outcome outcome = dialogEvent.getOutcome();
        if(outcome == DialogEvent.Outcome.ok){
            System.out.println("ENVIAR AL CORREO");
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
            List <BeanEvaluacion> lst = desempenoFiltro_Aux(6, null, null, serie,null);
            sessionDesempenoEvaluador.setLstEvaDetallePie(lst);
            sessionDesempenoEvaluador.setTitleDialog("Problema "+serie);
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
            List <BeanEvaluacion> lst = desempenoFiltro_Aux(6, null, serie, null,null);
            sessionDesempenoEvaluador.setLstEvaDetallePie(lst);
            sessionDesempenoEvaluador.setTitleDialog("Problema "+serie);
            Utils.showPopUpMIDDLE(popDetPie);
        }
    }

    
    public void estadoEvaluacion(String estado){
        if(estado.compareTo("No ejecutado") == 0){
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

}
