package sped.vista.beans.horarios;

import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import javax.ejb.EJB;

import oracle.adf.view.rich.component.rich.input.RichSelectOneChoice;

import sped.negocio.LNSF.IR.LN_C_SFSedeRemote;

import sped.negocio.entidades.beans.BeanUsuario;

import sped.vista.Utils.Utils;

public class bConfiguracionHorario {
    
    private bSessionConfiguracionHorario sessionConfiguracionHorario;
    private List listaSedesChoice;
    
    @EJB
    private LN_C_SFSedeRemote ln_C_SFSedeRemote;
    private RichSelectOneChoice choiceSede;

    public bConfiguracionHorario() {

    }
    
    @PostConstruct
    public void methodInvokeOncedOnPageLoad() {   
        llenarCombos();
    }   
    
    public void llenarCombos(){
        this.setListaSedesChoice(Utils.llenarCombo(ln_C_SFSedeRemote.getAllSedes()));
    }

    public void setSessionConfiguracionHorario(bSessionConfiguracionHorario sessionConfiguracionHorario) {
        this.sessionConfiguracionHorario = sessionConfiguracionHorario;
    }

    public bSessionConfiguracionHorario getSessionConfiguracionHorario() {
        return sessionConfiguracionHorario;
    }

    public void setListaSedesChoice(List listaSedesChoice) {
        this.listaSedesChoice = listaSedesChoice;
    }

    public List getListaSedesChoice() {
        return listaSedesChoice;
    }

    public void setChoiceSede(RichSelectOneChoice choiceSede) {
        this.choiceSede = choiceSede;
    }

    public RichSelectOneChoice getChoiceSede() {
        return choiceSede;
    }
}
