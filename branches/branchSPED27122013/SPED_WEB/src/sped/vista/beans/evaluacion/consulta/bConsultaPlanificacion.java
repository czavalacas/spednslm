package sped.vista.beans.evaluacion.consulta;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import javax.ejb.EJB;

import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import javax.naming.Context;
import javax.naming.InitialContext;

import oracle.adf.view.rich.component.rich.data.RichTable;

import oracle.adf.view.rich.component.rich.input.RichInputDate;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.input.RichSelectOneChoice;

import oracle.adf.view.rich.component.rich.nav.RichButton;

import sped.negocio.BDL.IR.BDL_C_SFUtilsRemote;
import sped.negocio.LNSF.IR.LN_C_SFAreaAcademicaRemote;
import sped.negocio.LNSF.IR.LN_C_SFEvaluacionRemote;
import sped.negocio.LNSF.IR.LN_C_SFNivelRemote;
import sped.negocio.LNSF.IR.LN_C_SFSedeRemote;
import sped.negocio.LNSF.IR.LN_C_SFUsuarioRemote;
import sped.negocio.LNSF.IR.LN_C_SFUtilsRemote;
import sped.negocio.entidades.beans.BeanAreaAcademica;
import sped.negocio.entidades.beans.BeanConstraint;
import sped.negocio.entidades.beans.BeanCurso;
import sped.negocio.entidades.beans.BeanEvaluacion;
import sped.negocio.entidades.beans.BeanNivel;
import sped.negocio.entidades.beans.BeanSede;
import sped.negocio.entidades.beans.BeanUsuario;

import sped.vista.Utils.Utils;

public class bConsultaPlanificacion {
    
    @EJB
    private LN_C_SFUsuarioRemote ln_C_SFUsuarioRemote;
    @EJB
    private LN_C_SFSedeRemote ln_C_SFSedeRemote;
    @EJB
    private LN_C_SFNivelRemote ln_C_SFNivelRemote;
    @EJB
    private LN_C_SFEvaluacionRemote ln_C_SFEvaluacionRemote;
    @EJB
    private LN_C_SFAreaAcademicaRemote ln_C_SFAreaAcademicaRemote;
    @EJB
    private LN_C_SFUtilsRemote ln_C_SFUtilsRemote;
    
    private bSessionConsultarPlanificacion sessionConsultarPlanificacion;
    
    private List listaEvaludaoresChoice;
    private List listaSedesChoice;
    private List listaNvelesChoice;   
    private List listaAreasChoice;
    private List listaEstadosChoice;
    private BeanUsuario usuarioEnSesion;
    
    
    
    private RichTable tbPlanificacion;
    private RichSelectOneChoice choiceEvaluadores;
    private RichSelectOneChoice choiceSedes;
    private RichButton btnBuscar;
    private RichInputDate inputFechaMin;
    private RichInputText inputProfesor;
    private RichSelectOneChoice choiceNivel;
    private RichInputDate inputFechaMax;
    private RichSelectOneChoice choiceAreaAcademica;
    private RichSelectOneChoice choiceEstado;


    public bConsultaPlanificacion() {
        try {
        
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @PostConstruct
    public void methodInvokeOncedOnPageLoad() {   
        llenarCombos();        
        if(sessionConsultarPlanificacion.getExec()==0){
           sessionConsultarPlanificacion.setExec(1); 
           sessionConsultarPlanificacion.setNidEstadoPlanificacion("PENDIENTE");
            buscarPlani();
            System.out.println("Se Ejecuto el post");
        }        
    }    
    
    public ArrayList llenarAreasAcademicas() {
        ArrayList unItems = new ArrayList();
        List<BeanAreaAcademica> roles = ln_C_SFAreaAcademicaRemote.getAreaAcademicaLN();
        System.out.println("TAMAÑO AREAS " + roles.size());
        for (BeanAreaAcademica r : roles) {          
            unItems.add(new SelectItem(r.getNidAreaAcademica().toString(), r.getDescripcionAreaAcademica().toString()));
        }
        return unItems;
    }
    public ArrayList llenarEstadosEvalu() {
        ArrayList unItems = new ArrayList();
        List<BeanConstraint> roles = ln_C_SFUtilsRemote.getListaConstraintsLN("estado_evaluacion", "evmeval");
        
        for (BeanConstraint r : roles) {          
            unItems.add(new SelectItem(r.getDescripcionAMostrar().toString(), r.getDescripcionAMostrar().toString()));
        }
        return unItems;
    }
    
    public ArrayList llenarEvaluadores() {
        ArrayList unItems = new ArrayList();
        List<BeanUsuario> roles = ln_C_SFUsuarioRemote.getEvaluadores(null);
        for (BeanUsuario r : roles) {      
            unItems.add(new SelectItem(r.getNidUsuario().toString(), r.getNombres().toString()));
        }
        return unItems;
    }
    public ArrayList llenarSedes() {
        ArrayList unItems = new ArrayList();
        List<BeanSede> roles = ln_C_SFSedeRemote.getSedeLN();
        for (BeanSede r : roles) {
            unItems.add(new SelectItem(r.getNidSede().toString(), r.getDescripcionSede().toString()));
        }
        return unItems;
    }
    public ArrayList llenarNiveles() {
        ArrayList unItems = new ArrayList();
        List<BeanNivel> roles = ln_C_SFNivelRemote.getNivelLN();
        for (BeanNivel r : roles) {
            unItems.add(new SelectItem(r.getNidNivel().toString(), r.getDescripcionNivel().toString()));
        }
        return unItems;
    }
    

    public void setSessionConsultarPlanificacion(bSessionConsultarPlanificacion sessionConsultarPlanificacion) {
        this.sessionConsultarPlanificacion = sessionConsultarPlanificacion;
    }

    public bSessionConsultarPlanificacion getSessionConsultarPlanificacion() {
        return sessionConsultarPlanificacion;
    }


    public void setTbPlanificacion(RichTable tbPlanificacion) {
        this.tbPlanificacion = tbPlanificacion;
    }

    public RichTable getTbPlanificacion() {
        return tbPlanificacion;
    }

    public void setLn_C_SFUsuarioRemote(LN_C_SFUsuarioRemote ln_C_SFUsuarioRemote) {
        this.ln_C_SFUsuarioRemote = ln_C_SFUsuarioRemote;
    }

    public LN_C_SFUsuarioRemote getLn_C_SFUsuarioRemote() {
        return ln_C_SFUsuarioRemote;
    }

    public void setListaEvaludaoresChoice(List listaEvaludaoresChoice) {
        this.listaEvaludaoresChoice = listaEvaludaoresChoice;
    }

    public List getListaEvaludaoresChoice() {
        return listaEvaludaoresChoice;
    }

    public void setUsuarioEnSesion(BeanUsuario usuarioEnSesion) {
        this.usuarioEnSesion = usuarioEnSesion;
    }

    public BeanUsuario getUsuarioEnSesion() {
        return usuarioEnSesion;
    } 

    public void setListaSedesChoice(List listaSedesChoice) {
        this.listaSedesChoice = listaSedesChoice;
    }

    public List getListaSedesChoice() {
        return listaSedesChoice;
    }

    public void setChoiceEvaluadores(RichSelectOneChoice choiceEvaluadores) {
        this.choiceEvaluadores = choiceEvaluadores;
    }

    public RichSelectOneChoice getChoiceEvaluadores() {
        return choiceEvaluadores;
    }

    public void setChoiceSedes(RichSelectOneChoice choiceSedes) {
        this.choiceSedes = choiceSedes;
    }

    public RichSelectOneChoice getChoiceSedes() {
        return choiceSedes;
    }

    public void setListaNvelesChoice(List listaNvelesChoice) {
        this.listaNvelesChoice = listaNvelesChoice;
    }

    public List getListaNvelesChoice() {
        return listaNvelesChoice;
    }
    
    public String buscarPlani(){
        BeanEvaluacion beanEvaluacion=new BeanEvaluacion();
        if(sessionConsultarPlanificacion.getFechaMaxPlanificacion()!=null){
            System.out.println("FECHA MAX" + sessionConsultarPlanificacion.getFechaMaxPlanificacion());
            beanEvaluacion.setFechaMaxPlanificacion(sessionConsultarPlanificacion.getFechaMaxPlanificacion());
        }
        if(sessionConsultarPlanificacion.getFechaMinPlanificacion()!=null){
            System.out.println("FECHA MIN" + sessionConsultarPlanificacion.getFechaMinPlanificacion());
            beanEvaluacion.setFechaMinPlanificacion(sessionConsultarPlanificacion.getFechaMinPlanificacion());
        }       
        if(sessionConsultarPlanificacion.getNidEvaluadorChoice()!=null){
            System.out.println("NID EVALUADOR "+sessionConsultarPlanificacion.getNidEvaluadorChoice());
            beanEvaluacion.setNidEvaluador(Integer.parseInt(sessionConsultarPlanificacion.getNidEvaluadorChoice()));
        }
        if(sessionConsultarPlanificacion.getNidSedeChoice()!=null){
            System.out.println("NID SEDE NO ES NULO");
            beanEvaluacion.setNidSede(Integer.parseInt(sessionConsultarPlanificacion.getNidSedeChoice()));
        }
        if(sessionConsultarPlanificacion.getNidNivelChoice()!=null){
            beanEvaluacion.setNidNivel(Integer.parseInt(sessionConsultarPlanificacion.getNidNivelChoice()));
        }
        if(sessionConsultarPlanificacion.getApellidosDocente()!=null){
            beanEvaluacion.setApellidosDocentes(sessionConsultarPlanificacion.getApellidosDocente());
        }
        if(sessionConsultarPlanificacion.getNidAreaAcademicaChoice()!=null){
            beanEvaluacion.setNidArea(Integer.parseInt(sessionConsultarPlanificacion.getNidAreaAcademicaChoice()));
        }
        if(sessionConsultarPlanificacion.getNidEstadoPlanificacion()!=null){
            beanEvaluacion.setNidEstadoEvaluacion(sessionConsultarPlanificacion.getNidEstadoPlanificacion());
        }
      
        sessionConsultarPlanificacion.setListaPlanificaciones(ln_C_SFEvaluacionRemote.getPlanificacion(beanEvaluacion));
        if(tbPlanificacion!=null){
            Utils.addTarget(tbPlanificacion);          
        }
        return null;
    }

    public void buscarPlanificacion(ActionEvent actionEvent) {
       // buscarPlani();
        
     //   tbPlanificacion.setValue(sessionConsultarPlanificacion.getListaPlanificaciones());
       
    }
    
    
    

    public void setBtnBuscar(RichButton btnBuscar) {
        this.btnBuscar = btnBuscar;
    }

    public RichButton getBtnBuscar() {
        return btnBuscar;
    }

    public String llenarCombos(){
        
        usuarioEnSesion = (BeanUsuario) Utils.getSession("USER");
        this.setListaEvaludaoresChoice(llenarEvaluadores());
        this.setListaSedesChoice(llenarSedes());
        this.setListaNvelesChoice(llenarNiveles());    
        this.setListaAreasChoice(llenarAreasAcademicas());
        this.setListaEstadosChoice(llenarEstadosEvalu());
        if(usuarioEnSesion.getRol().getNidRol()==5){
            sessionConsultarPlanificacion.setNidEvaluadorChoice(""+usuarioEnSesion.getNidUsuario());
            sessionConsultarPlanificacion.setEstadoChoiceEvaluador(true);
        }
        if(usuarioEnSesion.getRol().getNidRol()==4){
            sessionConsultarPlanificacion.setNidEvaluadorChoice(""+usuarioEnSesion.getNidUsuario());
            sessionConsultarPlanificacion.setEstadoChoiceEvaluador(true);
            sessionConsultarPlanificacion.setNidSedeChoice(""+usuarioEnSesion.getSede().getNidSede());
            sessionConsultarPlanificacion.setEstadoChoiceSede(true);
        }
        if(usuarioEnSesion.getRol().getNidRol()==2){
            sessionConsultarPlanificacion.setNidEvaluadorChoice(""+usuarioEnSesion.getNidUsuario());
            sessionConsultarPlanificacion.setEstadoChoiceEvaluador(true);
            System.out.println("NID AREA ACADEMICA USUARIO"+usuarioEnSesion.getAreaAcademica().getNidAreaAcademica());
            sessionConsultarPlanificacion.setNidAreaAcademicaChoice(""+usuarioEnSesion.getAreaAcademica().getNidAreaAcademica());
            sessionConsultarPlanificacion.setEstadoChoiceArea(true);
        }
     return null;   
    }
    
    public void limpiarPlanificacion(ActionEvent actionEvent) {
      sessionConsultarPlanificacion.setApellidosDocente(null);
      sessionConsultarPlanificacion.setFechaMaxPlanificacion(null);
      sessionConsultarPlanificacion.setFechaMinPlanificacion(null);
      sessionConsultarPlanificacion.setNidEvaluadorChoice(null);
      sessionConsultarPlanificacion.setNidNivelChoice(null);
      sessionConsultarPlanificacion.setNidSedeChoice(null);
      sessionConsultarPlanificacion.setNidAreaAcademicaChoice(null);
      sessionConsultarPlanificacion.setNidEstadoPlanificacion(null);       
      llenarCombos();
      Utils.addTargetMany(choiceEvaluadores, choiceAreaAcademica,choiceEstado,choiceNivel,choiceSedes,inputFechaMax,inputFechaMin,inputProfesor);
      buscarPlani();
    }

    public void setInputFechaMin(RichInputDate inputFechaMin) {
        this.inputFechaMin = inputFechaMin;
    }

    public RichInputDate getInputFechaMin() {
        return inputFechaMin;
    }

    public void setInputProfesor(RichInputText inputProfesor) {
        this.inputProfesor = inputProfesor;
    }

    public RichInputText getInputProfesor() {
        return inputProfesor;
    }

    public void setChoiceNivel(RichSelectOneChoice choiceNivel) {
        this.choiceNivel = choiceNivel;
    }

    public RichSelectOneChoice getChoiceNivel() {
        return choiceNivel;
    }

    public void setInputFechaMax(RichInputDate inputFechaMax) {
        this.inputFechaMax = inputFechaMax;
    }

    public RichInputDate getInputFechaMax() {
        return inputFechaMax;
    }

    public void setChoiceAreaAcademica(RichSelectOneChoice choiceAreaAcademica) {
        this.choiceAreaAcademica = choiceAreaAcademica;
    }

    public RichSelectOneChoice getChoiceAreaAcademica() {
        return choiceAreaAcademica;
    }

    public void setListaAreasChoice(List listaAreasChoice) {
        this.listaAreasChoice = listaAreasChoice;
    }

    public List getListaAreasChoice() {
        return listaAreasChoice;
    }

    public void setChoiceEstado(RichSelectOneChoice choiceEstado) {
        this.choiceEstado = choiceEstado;
    }

    public RichSelectOneChoice getChoiceEstado() {
        return choiceEstado;
    }

    public void setListaEstadosChoice(List listaEstadosChoice) {
        this.listaEstadosChoice = listaEstadosChoice;
    }

    public List getListaEstadosChoice() {
        return listaEstadosChoice;
    }
}
