package sped.vista.beans.evaluacion.grafico_reporte;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import javax.ejb.EJB;

import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import oracle.adf.view.rich.component.rich.RichSubform;
import oracle.adf.view.rich.component.rich.input.RichSelectManyChoice;

import oracle.adf.view.rich.component.rich.layout.RichPanelDashboard;
import oracle.adf.view.rich.component.rich.layout.RichPanelFormLayout;
import oracle.adf.view.rich.component.rich.layout.RichShowDetail;

import sped.negocio.LNSF.IL.LN_C_SFEvaluacionLocal;
import sped.negocio.LNSF.IL.LN_C_SFUsuarioLocal;
import sped.negocio.LNSF.IR.LN_C_SFAreaAcademicaRemote;
import sped.negocio.LNSF.IR.LN_C_SFRolRemote;
import sped.negocio.LNSF.IR.LN_C_SFSedeRemote;
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
    private RichSelectManyChoice choiceFArea;
    private RichSelectManyChoice choiceFRol;
    private RichSelectManyChoice choiceFEva;
    private RichShowDetail sd1;
    private RichShowDetail sd2;
    private RichSubform s2;
    private RichPanelFormLayout pfl1;
    private RichPanelDashboard pdash1;

    public bDesempenoEvaluador() {
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
        Utils.addTargetMany(pfl1,s2);
    }
    
    public void buscarByFiltro(ActionEvent actionEvent) {
        setListEvaFiltro_aux();
    }
    
    public void setListEvaFiltro_aux(){
        List <BeanEvaluacion> lst = ln_C_SFEvaluacionLocal.getDesempenoEvaluacionbyFiltroLN(sessionDesempenoEvaluador.getSelectedRol(),
                                                                                            sessionDesempenoEvaluador.getSelectedEvaluador(),
                                                                                            sessionDesempenoEvaluador.getSelectedSede(),
                                                                                            sessionDesempenoEvaluador.getSelectedArea(),
                                                                                            sessionDesempenoEvaluador.getFechaPI(),
                                                                                            sessionDesempenoEvaluador.getFechaPF(),
                                                                                            sessionDesempenoEvaluador.getFechaEI(),
                                                                                            sessionDesempenoEvaluador.getFechaEF());        
        sessionDesempenoEvaluador.setLstEvaTable(lst);
        setListEvabarChart(lst);
        if(pdash1 != null){
            Utils.addTargetMany(pdash1);
        }
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
            Object[] obj1 = { nombreEvaluador, "EJECUTADOS", contEjecutados};
            Object[] obj2 = { nombreEvaluador, "PENDIENTES", contPendiente};
            Object[] obj3 = { nombreEvaluador, "NO EJECUTADOS", contNoEje};
            Object[] obj4 = { nombreEvaluador, "SIN JUSTIFICACION", contNoEjeJ};
            lstEva.add(obj1);
            lstEva.add(obj2);
            lstEva.add(obj3);
            lstEva.add(obj3);
            lstEva.add(obj4);
        }
        sessionDesempenoEvaluador.setLstEvaBarChart(lstEva);
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
}
