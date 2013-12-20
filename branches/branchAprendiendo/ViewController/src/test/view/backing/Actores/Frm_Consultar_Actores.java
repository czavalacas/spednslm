package test.view.backing.Actores;

import java.util.List;

import javax.naming.Context;

import javax.naming.InitialContext;

import oracle.adf.view.rich.component.rich.RichDialog;
import oracle.adf.view.rich.component.rich.RichDocument;
import oracle.adf.view.rich.component.rich.RichForm;
import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.input.RichInputDate;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.layout.RichGridCell;
import oracle.adf.view.rich.component.rich.layout.RichGridRow;
import oracle.adf.view.rich.component.rich.layout.RichPanelGridLayout;

import oracle.adf.view.rich.component.rich.output.RichMessages;

import org.apache.myfaces.trinidad.event.SelectionEvent;

import test.negocio.BDL.IR.BDL_C_SFActorRemote;
import test.negocio.entidades.Actor;

import test.view.backing.Utils;

public class Frm_Consultar_Actores {
    private RichGridCell gc1;
    private RichGridRow gr1;
    private RichPanelGridLayout pgl1;
    private RichForm f1;
    private RichDocument d1;
    private RichPopup popActor;
    private RichInputText it1;
    private RichInputText it2;
    private RichInputDate id2;
    private RichDialog d3;
    private RichTable t1;
    private RichTable t2;
    private RichInputDate id1;
    private RichMessages m1;
    private BDL_C_SFActorRemote bdL_C_SFActorRemote;
    private final static String LOOKUP_ACTORES = "mapBDL_C_SFActor#test.negocio.BDL.IR.BDL_C_SFActorRemote";
    private List<Actor> lstActores;
    private SessionScopedBeanConsultarActores beanConsultarActores;

    public Frm_Consultar_Actores(){
        try{
            final Context ctx;
            ctx = new InitialContext();
            bdL_C_SFActorRemote = (BDL_C_SFActorRemote) ctx.lookup(LOOKUP_ACTORES);
            this.setLstActores(bdL_C_SFActorRemote.getActorFindAll());
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void cambioActores(SelectionEvent se) {
        Utils.depurar("SELECCIONASTE LA FILA! ");
        RichTable t = (RichTable) se.getSource();
        Actor actor = (Actor) t.getSelectedRowData();
        beanConsultarActores.setActor(actor);
        beanConsultarActores.setFirst_name(actor.getFirst_name());
        beanConsultarActores.setLast_name(actor.getLast_name());
        beanConsultarActores.setLast_update(actor.getLast_update());
        /*if(it1 != null){
            Utils.addTargetMany(it1,it2,id2);
        }*/
        Utils.showPopUpMIDDLE(popActor);
    }
    
    public void setLstActores(List lstActores) {
        this.lstActores = lstActores;
    }

    public List getLstActores() {
        return lstActores;
    }

    public void setGc1(RichGridCell gc1) {
        this.gc1 = gc1;
    }

    public RichGridCell getGc1() {
        return gc1;
    }

    public void setGr1(RichGridRow gr1) {
        this.gr1 = gr1;
    }

    public RichGridRow getGr1() {
        return gr1;
    }

    public void setPgl1(RichPanelGridLayout pgl1) {
        this.pgl1 = pgl1;
    }

    public RichPanelGridLayout getPgl1() {
        return pgl1;
    }

    public void setF1(RichForm f1) {
        this.f1 = f1;
    }

    public RichForm getF1() {
        return f1;
    }

    public void setD1(RichDocument d1) {
        this.d1 = d1;
    }

    public RichDocument getD1() {
        return d1;
    }

    public void setT1(RichTable t1) {
        this.t1 = t1;
    }

    public RichTable getT1() {
        return t1;
    }

    public void setT2(RichTable t2) {
        this.t2 = t2;
    }

    public RichTable getT2() {
        return t2;
    }

    public void setId1(RichInputDate id1) {
        this.id1 = id1;
    }

    public RichInputDate getId1() {
        return id1;
    }

    public void setM1(RichMessages m1) {
        this.m1 = m1;
    }

    public RichMessages getM1() {
        return m1;
    }

    public void setBeanConsultarActores(SessionScopedBeanConsultarActores beanConsultarActores) {
        this.beanConsultarActores = beanConsultarActores;
    }

    public SessionScopedBeanConsultarActores getBeanConsultarActores() {
        return beanConsultarActores;
    }

    public void setPopActor(RichPopup popActor) {
        this.popActor = popActor;
    }

    public RichPopup getPopActor() {
        return popActor;
    }

    public void setIt1(RichInputText it1) {
        this.it1 = it1;
    }

    public RichInputText getIt1() {
        return it1;
    }

    public void setIt2(RichInputText it2) {
        this.it2 = it2;
    }

    public RichInputText getIt2() {
        return it2;
    }

    public void setId2(RichInputDate id2) {
        this.id2 = id2;
    }

    public RichInputDate getId2() {
        return id2;
    }

    public void setD3(RichDialog d3) {
        this.d3 = d3;
    }

    public RichDialog getD3() {
        return d3;
    }
}
