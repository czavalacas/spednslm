package sped.vista.beans.evaluacion.consulta;

import java.util.ArrayList;

import java.util.List;

import javax.annotation.PostConstruct;

import javax.ejb.EJB;

import javax.faces.component.UISelectItems;
import javax.faces.event.ActionEvent;

import javax.faces.model.SelectItem;

import oracle.adf.view.rich.component.rich.data.RichTable;

import oracle.adf.view.rich.component.rich.input.RichInputDate;
import oracle.adf.view.rich.component.rich.input.RichSelectOneChoice;

import oracle.adf.view.rich.component.rich.layout.RichPanelGridLayout;

import sped.negocio.LNSF.IR.LN_C_SFAreaAcademicaRemote;
import sped.negocio.LNSF.IR.LN_C_SFCursoRemoto;
import sped.negocio.LNSF.IR.LN_C_SFEvaluacionRemote;
import sped.negocio.LNSF.IR.LN_C_SFGradoRemote;
import sped.negocio.LNSF.IR.LN_C_SFNivelRemote;
import sped.negocio.LNSF.IR.LN_C_SFSedeNivelRemote;
import sped.negocio.LNSF.IR.LN_C_SFSedeRemote;
import sped.negocio.entidades.beans.BeanAreaAcademica;
import sped.negocio.entidades.beans.BeanCurso;
import sped.negocio.entidades.beans.BeanEvaluacion;
import sped.negocio.entidades.beans.BeanGrado;
import sped.negocio.entidades.beans.BeanNivel;
import sped.negocio.entidades.beans.BeanSede;
import sped.negocio.entidades.beans.BeanSedeNivel;
import sped.negocio.entidades.beans.BeanUsuario;

import sped.vista.Utils.Utils;

public class bConsultarEvaluacion {
    
    private bSessionConsultarEvaluacion sessionConsultarEvaluacion;
    private BeanUsuario beanUsuario = (BeanUsuario) Utils.getSession("USER");    
    private RichTable t1;
    private RichSelectOneChoice choiceFSede;
    private UISelectItems si1;
    private RichSelectOneChoice choiceFNivel;
    private RichSelectOneChoice choiceFArea;
    private UISelectItems si2;
    private UISelectItems si3;
    private RichSelectOneChoice choiceFCurso;
    private RichSelectOneChoice choiceFGrado;
    @EJB
    private LN_C_SFEvaluacionRemote ln_C_SFEvaluacionRemote;
    @EJB
    private LN_C_SFAreaAcademicaRemote ln_C_SFAreaAcademicaRemote;
    @EJB
    private LN_C_SFSedeRemote ln_C_SFSedeRemote;
    @EJB
    private LN_C_SFNivelRemote ln_C_SFNivelRemote;
    @EJB
    private LN_C_SFCursoRemoto ln_C_SFCursoRemoto;
    @EJB
    private LN_C_SFGradoRemote ln_C_SFGradoRemote;
    private RichPanelGridLayout pgl1;
    private RichInputDate idFechaInicio;
    private RichInputDate idfechaFin;

    public bConsultarEvaluacion() {
        
    }
    
    @PostConstruct
    public void methodInvokeOncedOnPageLoad() {
        if(sessionConsultarEvaluacion.getExec() == 0){
            sessionConsultarEvaluacion.setExec(1);
            renderColumns(beanUsuario.getRol().getDescripcionRol());
            sessionConsultarEvaluacion.setLstSede(llenarComboSede());
            sessionConsultarEvaluacion.setLstNivel(llenarComboNivel());
            sessionConsultarEvaluacion.setLstArea(llenarComboAreaA());
            sessionConsultarEvaluacion.setLstCurso(llenarComboCurso());
            llenarTabla();
        }
    }
    
    public void renderColumns(String descripcionRol){
        if(descripcionRol.toUpperCase().compareTo("SUBDIRECTOR") == 0){
            sessionConsultarEvaluacion.setColumnSede(false);
            sessionConsultarEvaluacion.setColumnNivel(false);
        }
        if(descripcionRol.toUpperCase().compareTo("EVALUADOR") == 0){
            sessionConsultarEvaluacion.setColumnEvaluador(false);
            sessionConsultarEvaluacion.setColumnArea(false);
        }
        if(descripcionRol.toUpperCase().compareTo("PROFESOR") == 0){
            sessionConsultarEvaluacion.setColumnProfesor(false);
        }        
    }
    
    private ArrayList llenarComboAreaA() {
        ArrayList unItems = new ArrayList();
        List<BeanAreaAcademica> beanAreaA = ln_C_SFAreaAcademicaRemote.getAreaAcademicaLN();
        for(BeanAreaAcademica a : beanAreaA){
            unItems.add(new SelectItem(a.getNidAreaAcademica(), 
                                       a.getDescripcionAreaAcademica().toString()));
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
    
    private ArrayList llenarComboNivel(){
        ArrayList unItems = new ArrayList();
        List<BeanNivel> lstSedeNivel = ln_C_SFNivelRemote.getNivelLN();
        for(BeanNivel n : lstSedeNivel){
            unItems.add(new SelectItem(n.getNidNivel(),
                                       n.getDescripcionNivel().toString()));
        }
        return unItems;
    }
    
    private ArrayList llenarComboCurso(){
        ArrayList unItems = new ArrayList();
        List<BeanCurso> lstBeanCurso = ln_C_SFCursoRemoto.getlistaCursos();
        for(BeanCurso c : lstBeanCurso){
            unItems.add(new SelectItem(c.getNidCurso(),
                                       c.getDescripcionCurso().toString()));
        }
        return unItems;
    }
    
    public void buscarByFiltro(ActionEvent actionEvent) {
        llenarTabla();
    }

    public void resetFiltro(ActionEvent actionEvent) {
        idFechaInicio.resetValue();
        idfechaFin.resetValue();
        sessionConsultarEvaluacion.setFechaP(null);
        sessionConsultarEvaluacion.setFechaF(null);
        sessionConsultarEvaluacion.setNombreProfesor("");
        sessionConsultarEvaluacion.setNidSede(0);
        sessionConsultarEvaluacion.setNidNivel(0);
        sessionConsultarEvaluacion.setNidArea(0);
        sessionConsultarEvaluacion.setNidCurso(0);
        Utils.addTarget(pgl1);
        llenarTabla();
    }
    
    public void llenarTabla(){
        sessionConsultarEvaluacion.setLstBeanEvaluacion(
             ln_C_SFEvaluacionRemote.getEvaluacionesByUsuarioLN(beanUsuario, 
                                                                sessionConsultarEvaluacion.getNidSede(), 
                                                                sessionConsultarEvaluacion.getNidNivel(), 
                                                                sessionConsultarEvaluacion.getNidArea(), 
                                                                sessionConsultarEvaluacion.getNidCurso(), 
                                                                sessionConsultarEvaluacion.getNombreProfesor(),
                                                                sessionConsultarEvaluacion.getFechaP(),
                                                                sessionConsultarEvaluacion.getFechaF()));
        if(t1 != null){
            Utils.addTarget(t1);
        }        
    }

    public void setSessionConsultarEvaluacion(bSessionConsultarEvaluacion sessionConsultarEvaluacion) {
        this.sessionConsultarEvaluacion = sessionConsultarEvaluacion;
    }

    public bSessionConsultarEvaluacion getSessionConsultarEvaluacion() {
        return sessionConsultarEvaluacion;
    }

    public void setT1(RichTable t1) {
        this.t1 = t1;
    }

    public RichTable getT1() {
        return t1;
    }

    public void setChoiceFSede(RichSelectOneChoice choiceFSede) {
        this.choiceFSede = choiceFSede;
    }

    public RichSelectOneChoice getChoiceFSede() {
        return choiceFSede;
    }

    public void setSi1(UISelectItems si1) {
        this.si1 = si1;
    }

    public UISelectItems getSi1() {
        return si1;
    }

    public void setChoiceFNivel(RichSelectOneChoice choiceFNivel) {
        this.choiceFNivel = choiceFNivel;
    }

    public RichSelectOneChoice getChoiceFNivel() {
        return choiceFNivel;
    }

    public void setChoiceFArea(RichSelectOneChoice choiceFArea) {
        this.choiceFArea = choiceFArea;
    }

    public RichSelectOneChoice getChoiceFArea() {
        return choiceFArea;
    }

    public void setSi2(UISelectItems si2) {
        this.si2 = si2;
    }

    public UISelectItems getSi2() {
        return si2;
    }

    public void setSi3(UISelectItems si3) {
        this.si3 = si3;
    }

    public UISelectItems getSi3() {
        return si3;
    }

    public void setChoiceFCurso(RichSelectOneChoice choiceFCurso) {
        this.choiceFCurso = choiceFCurso;
    }

    public RichSelectOneChoice getChoiceFCurso() {
        return choiceFCurso;
    }

    public void setChoiceFGrado(RichSelectOneChoice choiceFGrado) {
        this.choiceFGrado = choiceFGrado;
    }

    public RichSelectOneChoice getChoiceFGrado() {
        return choiceFGrado;
    }

    public void setPgl1(RichPanelGridLayout pgl1) {
        this.pgl1 = pgl1;
    }

    public RichPanelGridLayout getPgl1() {
        return pgl1;
    }

    public void setIdFechaInicio(RichInputDate idFechaInicio) {
        this.idFechaInicio = idFechaInicio;
    }

    public RichInputDate getIdFechaInicio() {
        return idFechaInicio;
    }

    public void setIdfechaFin(RichInputDate idfechaFin) {
        this.idfechaFin = idfechaFin;
    }

    public RichInputDate getIdfechaFin() {
        return idfechaFin;
    }
}
