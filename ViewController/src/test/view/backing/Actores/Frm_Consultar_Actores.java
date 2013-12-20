package test.view.backing.Actores;

import java.util.List;

import javax.naming.Context;

import javax.naming.InitialContext;

import oracle.adf.view.rich.component.rich.RichDocument;
import oracle.adf.view.rich.component.rich.RichForm;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.input.RichInputDate;
import oracle.adf.view.rich.component.rich.layout.RichGridCell;
import oracle.adf.view.rich.component.rich.layout.RichGridRow;
import oracle.adf.view.rich.component.rich.layout.RichPanelGridLayout;

import oracle.adf.view.rich.component.rich.output.RichMessages;

import test.negocio.BDL.IR.BDL_C_SFActorRemote;
import test.negocio.entidades.Actor;

public class Frm_Consultar_Actores {
    private RichGridCell gc1;
    private RichGridRow gr1;
    private RichPanelGridLayout pgl1;
    private RichForm f1;
    private RichDocument d1;
    private BDL_C_SFActorRemote bdL_C_SFActorRemote;
    private final static String LOOKUP_ACTORES = "mapBDL_C_SFActor#test.negocio.BDL.IR.BDL_C_SFActorRemote";
    List<Actor> lstActores;
    private RichTable t1;
    private RichTable t2;
    private RichInputDate id1;
    private RichMessages m1;

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
}
