package sped.vista.beans.migracion;

import java.util.ArrayList;

import java.util.List;

import javax.annotation.PostConstruct;

import javax.ejb.EJB;

import javax.faces.event.ActionEvent;

import javax.faces.model.SelectItem;

import oracle.adf.view.rich.component.rich.input.RichSelectOneChoice;

import sped.negocio.LNSF.IR.LN_C_SFUtilsRemote;
import sped.negocio.entidades.beans.BeanCombo;

import sun.org.mozilla.javascript.internal.ast.ArrayLiteral;

public class bMigrarHorarios {
    bSessionMigrarHorarios sessionMigrarHorarios;
    @EJB
    private LN_C_SFUtilsRemote ln_C_SFUtilsRemote;
    private RichSelectOneChoice soc1;

    public bMigrarHorarios() {
    }
    
    @PostConstruct
    public void methodInvokeOncedOnPageLoad() {
        if(sessionMigrarHorarios.getExec() == 0){
            sessionMigrarHorarios.setLstsedes(llenarComboSede());
            sessionMigrarHorarios.setExec(1);
        }
    }
    
    private ArrayList llenarComboSede() {
        List<BeanCombo> lstsedes = ln_C_SFUtilsRemote.getSedes_LN();
        return transformLstSelectItem(lstsedes);
    }
    
    private ArrayList llenarComboNivel(){        
        List<BeanCombo> lstnivel = ln_C_SFUtilsRemote.getNiveles_LN();
        return transformLstSelectItem(lstnivel);
    }
    
    private ArrayList transformLstSelectItem(List<BeanCombo> lstCombo){
        ArrayList unItems = new ArrayList();
        for(BeanCombo c : lstCombo){
            unItems.add(new SelectItem(c.getId(),
                                       c.getDescripcion().toString()));
        }
        return unItems;
    }

    public void setSessionMigrarHorarios(bSessionMigrarHorarios sessionMigrarHorarios) {
        this.sessionMigrarHorarios = sessionMigrarHorarios;
    }

    public bSessionMigrarHorarios getSessionMigrarHorarios() {
        return sessionMigrarHorarios;
    }

    public void setSoc1(RichSelectOneChoice soc1) {
        this.soc1 = soc1;
    }

    public RichSelectOneChoice getSoc1() {
        return soc1;
    }
}
