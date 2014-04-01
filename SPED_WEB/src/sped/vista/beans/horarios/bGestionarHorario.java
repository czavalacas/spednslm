package sped.vista.beans.horarios;

import javax.annotation.PostConstruct;

import javax.ejb.EJB;

import javax.faces.component.UISelectItems;

import javax.faces.event.ValueChangeEvent;

import oracle.adf.view.rich.component.rich.input.RichSelectManyChoice;

import oracle.adf.view.rich.component.rich.input.RichSelectOneChoice;

import oracle.adf.view.rich.component.rich.layout.RichPanelFormLayout;

import sped.negocio.LNSF.IR.LN_C_SFUtilsRemote;

import sped.vista.Utils.Utils;

public class bGestionarHorario {    
    @EJB
    private LN_C_SFUtilsRemote ln_C_SFUtilsRemote;
    private bSessionGestionarHorario sessionGestionarHorario;
    private RichSelectOneChoice choiceSede;
    private UISelectItems si1;
    private RichSelectOneChoice choiceNivel;
    private UISelectItems si2;
    private RichSelectOneChoice choiceAula;
    private UISelectItems si3;
    private RichPanelFormLayout pfCombox;


    public bGestionarHorario() {
    }

    @PostConstruct
    public void methodInvokeOncedOnPageLoad() {
        if(sessionGestionarHorario.getExec() == 0){
            sessionGestionarHorario.setExec(1);
            sessionGestionarHorario.setLstSede(Utils.llenarCombo(ln_C_SFUtilsRemote.getSedes_LN()));
            sessionGestionarHorario.setLstNivel(Utils.llenarCombo(ln_C_SFUtilsRemote.getNiveles_LN()));           
        }
    }
    
    public void changeListenerSede(ValueChangeEvent valueChangeEvent) {
        if(valueChangeEvent.getNewValue() != null){
            if(sessionGestionarHorario.getNidNivel() != 0){
                sessionGestionarHorario.setLstAula(Utils.llenarCombo(
                            ln_C_SFUtilsRemote.getAulaByNidSedeNivel(Integer.parseInt(valueChangeEvent.getNewValue().toString()), 
                                                                     sessionGestionarHorario.getNidNivel())));
                Utils.addTarget(choiceAula);
            }
        }
    }

    public void changeListenerNivel(ValueChangeEvent valueChangeEvent) {
        if(valueChangeEvent.getNewValue() != null){
            if(sessionGestionarHorario.getNidSede() != 0){
                sessionGestionarHorario.setLstAula(Utils.llenarCombo(
                            ln_C_SFUtilsRemote.getAulaByNidSedeNivel(sessionGestionarHorario.getNidSede(), 
                                                                     Integer.parseInt(valueChangeEvent.getNewValue().toString()))));
                Utils.addTarget(choiceAula);
            }
        }
    }

    public void setSessionGestionarHorario(bSessionGestionarHorario sessionGestionarHorario) {
        this.sessionGestionarHorario = sessionGestionarHorario;
    }

    public bSessionGestionarHorario getSessionGestionarHorario() {
        return sessionGestionarHorario;
    }

    public void setChoiceSede(RichSelectOneChoice choiceSede) {
        this.choiceSede = choiceSede;
    }

    public RichSelectOneChoice getChoiceSede() {
        return choiceSede;
    }

    public void setSi1(UISelectItems si1) {
        this.si1 = si1;
    }

    public UISelectItems getSi1() {
        return si1;
    }

    public void setChoiceNivel(RichSelectOneChoice choiceNivel) {
        this.choiceNivel = choiceNivel;
    }

    public RichSelectOneChoice getChoiceNivel() {
        return choiceNivel;
    }

    public void setSi2(UISelectItems si2) {
        this.si2 = si2;
    }

    public UISelectItems getSi2() {
        return si2;
    }

    public void setChoiceAula(RichSelectOneChoice choiceAula) {
        this.choiceAula = choiceAula;
    }

    public RichSelectOneChoice getChoiceAula() {
        return choiceAula;
    }

    public void setSi3(UISelectItems si3) {
        this.si3 = si3;
    }

    public UISelectItems getSi3() {
        return si3;
    }

    public void setPfCombox(RichPanelFormLayout pfCombox) {
        this.pfCombox = pfCombox;
    }

    public RichPanelFormLayout getPfCombox() {
        return pfCombox;
    }
}
