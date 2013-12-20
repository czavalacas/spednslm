package test.negocio.BDL.IR;

import java.util.List;

import javax.ejb.Remote;

import test.negocio.entidades.Actor;

@Remote
public interface BDL_C_SFActorRemote {
    List<Actor> getActorFindAll();
    List<Actor> getActoresByNombre(String nombre);
}
