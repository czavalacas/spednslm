package sped.vista.beans.evaluacion.consulta;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;

import javax.ejb.EJB;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import javax.naming.Context;
import javax.naming.InitialContext;

import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.data.RichTable;

import oracle.adf.view.rich.component.rich.input.RichInputDate;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.input.RichSelectOneChoice;

import oracle.adf.view.rich.component.rich.nav.RichButton;

import oracle.adf.view.rich.event.DialogEvent;

import sped.negocio.BDL.IR.BDL_C_SFUtilsRemote;
import sped.negocio.LNSF.IR.LN_C_SFAreaAcademicaRemote;
import sped.negocio.LNSF.IR.LN_C_SFEvaluacionRemote;
import sped.negocio.LNSF.IR.LN_C_SFNivelRemote;
import sped.negocio.LNSF.IR.LN_C_SFSedeRemote;
import sped.negocio.LNSF.IR.LN_C_SFUsuarioRemote;
import sped.negocio.LNSF.IR.LN_C_SFUtilsRemote;
import sped.negocio.LNSF.IR.LN_T_SFEvaluacionRemote;
import sped.negocio.entidades.admin.Constraint;
import sped.negocio.entidades.admin.Profesor;
import sped.negocio.entidades.beans.BeanAreaAcademica;
import sped.negocio.entidades.beans.BeanCombo;
import sped.negocio.entidades.beans.BeanComboString;
import sped.negocio.entidades.beans.BeanConstraint;
import sped.negocio.entidades.beans.BeanCurso;
import sped.negocio.entidades.beans.BeanEvaluacion;
import sped.negocio.entidades.beans.BeanMain;
import sped.negocio.entidades.beans.BeanNivel;
import sped.negocio.entidades.beans.BeanProfesor;
import sped.negocio.entidades.beans.BeanSede;
import sped.negocio.entidades.beans.BeanUsuario;

import sped.vista.Utils.Utils;

/** Clase de Respaldo bConsultarPlanificacion.java
 * @author czavalacas
 * @since 15.01.2014
 */
public class bConsultaPlanificacion {
    private List listaEvaludaoresChoice;
    private List listaSedesChoice;
    private List listaNvelesChoice;   
    private List listaAreasChoice;
    private List listaEstadosChoice;
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
    private RichButton btnExp;
    private RichPopup popProb;
    @EJB
    private LN_C_SFUsuarioRemote ln_C_SFUsuarioRemote;
    @EJB
    private LN_C_SFEvaluacionRemote ln_C_SFEvaluacionRemote;
    @EJB
    private LN_C_SFUtilsRemote ln_C_SFUtilsRemote;
    private bSessionConsultarPlanificacion sessionConsultarPlanificacion;
    private BeanUsuario usuarioEnSesion = (BeanUsuario) Utils.getSession("USER");
    private String descripcionProblema;
    @EJB
    private LN_T_SFEvaluacionRemote ln_T_SFEvaluacionRemote;
    FacesContext ctx = FacesContext.getCurrentInstance();

    public bConsultaPlanificacion() {
    }
    
    @PostConstruct
    public void methodInvokeOncedOnPageLoad() {
        llenarCombos();
        if (sessionConsultarPlanificacion.getExec() == 0) {
            sessionConsultarPlanificacion.setExec(1);
            sessionConsultarPlanificacion.setNidEstadoPlanificacion("PENDIENTE");
            if (usuarioEnSesion.getRol().getNidRol() == 2 || usuarioEnSesion.getRol().getNidRol() == 4) {
                sessionConsultarPlanificacion.setNidEvaluadorChoice("" + usuarioEnSesion.getNidUsuario());
                sessionConsultarPlanificacion.setEstadoChoiceEvaluador(true);
            }
            if (usuarioEnSesion.getRol().getNidRol() == 3) {//Profesor consulta
                Date hoy = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date fechaActual = hoy;
                String fechaConFormato = sdf.format(fechaActual);
                sessionConsultarPlanificacion.setFechaHoy(fechaConFormato);
                sessionConsultarPlanificacion.setDniProfesor(usuarioEnSesion.getDni());
                sessionConsultarPlanificacion.setColumnProfesor(false);
            }
            buscarPlani();
        }
    }

    public String buscarPlani() {
        BeanEvaluacion beanEvaluacion = new BeanEvaluacion();
        beanEvaluacion.setFechaMaxPlanificacion(sessionConsultarPlanificacion.getFechaMaxPlanificacion());
        beanEvaluacion.setFechaMinPlanificacion(sessionConsultarPlanificacion.getFechaMinPlanificacion());
        beanEvaluacion.setNidEstadoEvaluacion(sessionConsultarPlanificacion.getNidEstadoPlanificacion());
        beanEvaluacion.setApellidosDocentes(sessionConsultarPlanificacion.getApellidosDocente());
        beanEvaluacion.setNidRol(usuarioEnSesion.getRol().getNidRol());
        if(usuarioEnSesion.getAreaAcademica() != null){
            if(usuarioEnSesion.getAreaAcademica().getNidAreaAcademica() != null){
                beanEvaluacion.setNidAreaUsuario(usuarioEnSesion.getAreaAcademica().getNidAreaAcademica());
            }
        }
        if (usuarioEnSesion.getRol().getNidRol() == 2 || usuarioEnSesion.getRol().getNidRol() == 4) {
            beanEvaluacion.setNidEvaluador(usuarioEnSesion.getNidUsuario());
        }
        if (sessionConsultarPlanificacion.getNidEvaluadorChoice() != null) {
            beanEvaluacion.setNidEvaluador(Integer.parseInt(sessionConsultarPlanificacion.getNidEvaluadorChoice()));
        }
        if (sessionConsultarPlanificacion.getNidSedeChoice() != null) {
            beanEvaluacion.setNidSede(Integer.parseInt(sessionConsultarPlanificacion.getNidSedeChoice()));
        }
        if (sessionConsultarPlanificacion.getNidNivelChoice() != null) {
            beanEvaluacion.setNidNivel(Integer.parseInt(sessionConsultarPlanificacion.getNidNivelChoice()));
        }
        if (sessionConsultarPlanificacion.getNidAreaAcademicaChoice() != null) {
            beanEvaluacion.setNidArea(Integer.parseInt(sessionConsultarPlanificacion.getNidAreaAcademicaChoice()));
        }
        if (btnExp != null) {
            Utils.addTarget(btnExp);
        }
        if (tbPlanificacion != null) {
            Utils.addTarget(tbPlanificacion);
        }
        sessionConsultarPlanificacion.setListaPlanificaciones(ln_C_SFEvaluacionRemote.getPlanificacion(beanEvaluacion));
        return null;
    }

    public String llenarCombos(){
        this.setListaEvaludaoresChoice(Utils.llenarCombo(ln_C_SFUtilsRemote.getEvaluadores_LN_WS()));
        this.setListaSedesChoice(Utils.llenarCombo(ln_C_SFUtilsRemote.getSedes_LN()));
        this.setListaNvelesChoice(Utils.llenarCombo(ln_C_SFUtilsRemote.getNiveles_LN()));    
        this.setListaAreasChoice(Utils.llenarCombo(ln_C_SFUtilsRemote.getAreas_LN_WS()));
        this.setListaEstadosChoice(Utils.llenarComboString(ln_C_SFUtilsRemote.getListaEstados("estado_evaluacion", "evmeval")));
        if(usuarioEnSesion.getRol().getNidRol() == 4){
            sessionConsultarPlanificacion.setNidEvaluadorChoice(""+usuarioEnSesion.getNidUsuario());
            sessionConsultarPlanificacion.setEstadoChoiceEvaluador(true);
            sessionConsultarPlanificacion.setNidSedeChoice(""+usuarioEnSesion.getSede().getNidSede());
            sessionConsultarPlanificacion.setEstadoChoiceSede(true);
        }
        if(usuarioEnSesion.getRol().getNidRol() == 2 && "0".equals(usuarioEnSesion.getIsSupervisor())){
            sessionConsultarPlanificacion.setNidEvaluadorChoice(""+usuarioEnSesion.getNidUsuario());
            sessionConsultarPlanificacion.setEstadoChoiceEvaluador(true);
            sessionConsultarPlanificacion.setNidAreaAcademicaChoice(""+usuarioEnSesion.getAreaAcademica().getNidAreaAcademica());
            sessionConsultarPlanificacion.setEstadoChoiceArea(true);
        }
        if(usuarioEnSesion.getRol().getNidRol() == 2 && "1".equals(usuarioEnSesion.getIsSupervisor())){
            sessionConsultarPlanificacion.setNidEvaluadorChoice(""+usuarioEnSesion.getNidUsuario());
            sessionConsultarPlanificacion.setEstadoChoiceEvaluador(true);
            sessionConsultarPlanificacion.setNidAreaAcademicaChoice(null);
            sessionConsultarPlanificacion.setEstadoChoiceArea(false);
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

    public void abrirPopUpProblema(ActionEvent actionEvent) {
        sessionConsultarPlanificacion.setNidProblema(String.valueOf(sessionConsultarPlanificacion.getI_nidProblema()));
        sessionConsultarPlanificacion.setListaProblemas(Utils.llenarCombo(ln_C_SFUtilsRemote.getProblemas_LN_WS()));
        Utils.showPopUpMIDDLE(popProb);
    }
    
    public void diagRegistrarProblema(DialogEvent de) {
        if(usuarioEnSesion.getNidUsuario().compareTo(sessionConsultarPlanificacion.getEvaSelect().getNidEvaluador()) == 0 &&
           usuarioEnSesion.getRol().getNidRol() != 3){//SOLO EL MISMO EVALUADOR PUEDE EDITAR SU COMENTARIO
            DialogEvent.Outcome outcome = de.getOutcome();        
            String detalle = "Error";
            String error = "Operacion Incorrecta, vuelva intentarlo";
            int severidad = 2;
            if(outcome == DialogEvent.Outcome.ok){            
                if(sessionConsultarPlanificacion.getEvaSelect() != null){
                    error = ln_T_SFEvaluacionRemote.updateEvaluacionProblemaEvaluador(sessionConsultarPlanificacion.getEvaSelect().getNidEvaluacion(),
                                                                                      sessionConsultarPlanificacion.getI_nidProblema(),
                                                                                      descripcionProblema);
                    if("000".compareTo(error) == 0){
                        detalle = "Se registro el comentario";
                        error = "Operacion Realizada Correctamente";
                        severidad = 3;
                        buscarPlani();
                    }                
                }
                Utils.mostrarMensaje(ctx,error,detalle,severidad);
            }
        }
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

    public void setDescripcionProblema(String descripcionProblema) {
        this.descripcionProblema = descripcionProblema;
    }

    public String getDescripcionProblema() {
        return descripcionProblema;
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

    public void buscarPlanificacion(ActionEvent actionEvent) {
       // buscarPlani();     
    }

    public void setBtnBuscar(RichButton btnBuscar) {
        this.btnBuscar = btnBuscar;
    }

    public RichButton getBtnBuscar() {
        return btnBuscar;
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

    public void setBtnExp(RichButton btnExp) {
        this.btnExp = btnExp;
    }

    public RichButton getBtnExp() {
        return btnExp;
    }

    public void setPopProb(RichPopup popProb) {
        this.popProb = popProb;
    }

    public RichPopup getPopProb() {
        return popProb;
    }
}
