package test.view.backing;

import java.util.List;

import javax.ejb.EJB;

import test.negocio.BDL.IR.BDL_C_SFActorRemote;
import test.negocio.entidades.Actor;

public class BBConsultar {
    
    @EJB
    private BDL_C_SFActorRemote bdL_C_SFActorRemote;
    private List<Actor> lstActores;
    
    public BBConsultar(){
        super();
    }

    public void setLstActores(List<Actor> lstActores) {
        this.lstActores = lstActores;
    }

    public List<Actor> getLstActores() {
        return bdL_C_SFActorRemote.getActorFindAll();
    }
}
