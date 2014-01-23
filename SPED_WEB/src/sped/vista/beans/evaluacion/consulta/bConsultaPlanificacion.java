package sped.vista.beans.evaluacion.consulta;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import javax.ejb.EJB;

import javax.faces.model.SelectItem;

import javax.naming.Context;
import javax.naming.InitialContext;

import oracle.adf.view.rich.component.rich.data.RichTable;

import oracle.adf.view.rich.component.rich.input.RichSelectOneChoice;

import sped.negocio.LNSF.IR.LN_C_SFNivelRemote;
import sped.negocio.LNSF.IR.LN_C_SFSedeRemote;
import sped.negocio.LNSF.IR.LN_C_SFUsuarioRemote;
import sped.negocio.entidades.beans.BeanCurso;
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
    private bSessionConsultarPlanificacion sessionConsultarPlanificacion;
    
    private List listaEvaludaoresChoice;
    private List listaSedesChoice;
    private List listaNvelesChoice;
    private BeanUsuario usuarioEnSesion;
    
    
    
    private RichTable tbPlanificacion;
    private RichSelectOneChoice choiceEvaluadores;
    private RichSelectOneChoice choiceSedes;


    public bConsultaPlanificacion() {
        try {
        
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @PostConstruct
    public void methodInvokeOncedOnPageLoad() {
        usuarioEnSesion = (BeanUsuario) Utils.getSession("USER");
        this.setListaEvaludaoresChoice(llenarEvaluadores());
        this.setListaSedesChoice(llenarSedes());
        this.setListaNvelesChoice(llenarNiveles());
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
}
