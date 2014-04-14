package sped.vista.beans.administrativo.problemas;

import javax.annotation.PostConstruct;

import javax.ejb.EJB;

import javax.faces.context.FacesContext;

import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.data.RichTable;

import oracle.adf.view.rich.component.rich.input.RichInputText;

import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.event.DialogEvent;

import org.apache.myfaces.trinidad.event.SelectionEvent;

import sped.negocio.LNSF.IR.LN_C_SFProblemaRemote;
import sped.negocio.LNSF.IR.LN_T_SFProblemaRemote;
import sped.negocio.entidades.beans.BeanProblema;

import sped.vista.Utils.Utils;

public class bGestionarProblema {
    private bSessionGestionarProblema sessionGestionarProblema;
    @EJB
    private LN_C_SFProblemaRemote ln_C_SFProblemaRemote;
    @EJB
    private LN_T_SFProblemaRemote ln_T_SFProblemaRemote;
    private RichTable tpermi;
    private RichInputText itDesc;
    private RichPopup popNew;
    private RichPopup popDel;
    private RichPanelGroupLayout pgl4;
    private RichPanelGroupLayout pgl3;
    FacesContext ctx = FacesContext.getCurrentInstance();

    public bGestionarProblema() {
    }
    
    @PostConstruct
    public void methodInvokeOncedOnPageLoad() {
        if(sessionGestionarProblema.getExec() == 0){
            getProblemaAll();
            sessionGestionarProblema.setExec(1);
        }
    }
    
    public void selecionarPermiso(SelectionEvent se) {
        RichTable t = (RichTable) se.getSource();
        BeanProblema beanProblema = (BeanProblema) t.getSelectedRowData();
        sessionGestionarProblema.setNidProblema(beanProblema.getNidProblema());
        sessionGestionarProblema.setDescripcion(beanProblema.getDesc_problema());
        Utils.addTarget(pgl4);
    }
    
    public String insertarProblema() {
        limpiarCampos();
        Utils.unselectFilas(tpermi);
        Utils.addTargetMany(pgl4, pgl3);
        sessionGestionarProblema.setEvento(1);
        Utils.showPopUpMIDDLE(popNew);
        return null;
    }

    public String eliminarProblema() {
        sessionGestionarProblema.setEvento(2);
        Utils.showPopUpMIDDLE(popDel);
        return null;
    }
    
    public void confirmarEliminacion(DialogEvent dialogEvent) {
        DialogEvent.Outcome outcome = dialogEvent.getOutcome();
        if(outcome == DialogEvent.Outcome.ok){
            gestionarProblema();
            getProblemaAll();            
        }
    }
    
    public void confirmarRegistro(DialogEvent dialogEvent) {
        DialogEvent.Outcome outcome = dialogEvent.getOutcome();
        if(outcome == DialogEvent.Outcome.ok){
            gestionarProblema();
            getProblemaAll();
        }
    }
    
    public void limpiarCampos(){
        sessionGestionarProblema.setNidProblema(0);
        if(itDesc != null){
            itDesc.resetValue();   
        }        
        sessionGestionarProblema.setDescripcion(null);
    }
    
    public void gestionarProblema(){
        int nid = 3;
        if(sessionGestionarProblema.getEvento() == 1){
            sessionGestionarProblema.setDescripcion(Utils.quitaEspacios(sessionGestionarProblema.getDescripcion()));
        }
        String msj = ln_T_SFProblemaRemote.gestionarProblema(sessionGestionarProblema.getEvento(), 
                                                             sessionGestionarProblema.getNidProblema(), 
                                                             sessionGestionarProblema.getDescripcion());
        if(msj == null){
            if(sessionGestionarProblema.getEvento() == 1){
                msj = "Se registro correctamente el problema "+sessionGestionarProblema.getDescripcion();
            }else{
                msj = "Se elimino correctamente el problema "+sessionGestionarProblema.getDescripcion();
            }
        }else{
            nid = 2;
        }        
        limpiarCampos();
        Utils.mostrarMensaje(ctx, 
                             msj, 
                             "Mensaje", 
                             nid);
    }
    
    public void getProblemaAll(){
        sessionGestionarProblema.setLstBeanProblema(ln_C_SFProblemaRemote.getLstProblemaAllLN());
        if(pgl3 != null){
            Utils.addTarget(pgl3);
        }        
    }
    
    public void setSessionGestionarProblema(bSessionGestionarProblema sessionGestionarProblema) {
        this.sessionGestionarProblema = sessionGestionarProblema;
    }

    public bSessionGestionarProblema getSessionGestionarProblema() {
        return sessionGestionarProblema;
    }

    public void setTpermi(RichTable tpermi) {
        this.tpermi = tpermi;
    }

    public RichTable getTpermi() {
        return tpermi;
    }

    public void setItDesc(RichInputText itDesc) {
        this.itDesc = itDesc;
    }

    public RichInputText getItDesc() {
        return itDesc;
    }

    public void setPopNew(RichPopup popNew) {
        this.popNew = popNew;
    }

    public RichPopup getPopNew() {
        return popNew;
    }

    public void setPopDel(RichPopup popDel) {
        this.popDel = popDel;
    }

    public RichPopup getPopDel() {
        return popDel;
    }

    public void setPgl4(RichPanelGroupLayout pgl4) {
        this.pgl4 = pgl4;
    }

    public RichPanelGroupLayout getPgl4() {
        return pgl4;
    }

    public void setPgl3(RichPanelGroupLayout pgl3) {
        this.pgl3 = pgl3;
    }

    public RichPanelGroupLayout getPgl3() {
        return pgl3;
    }
}
