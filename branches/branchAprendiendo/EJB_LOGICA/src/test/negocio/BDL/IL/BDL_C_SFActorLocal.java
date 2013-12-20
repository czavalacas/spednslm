package test.negocio.BDL.IL;

import java.util.List;

import javax.ejb.Local;

import test.negocio.entidades.Actor;

@Local
public interface BDL_C_SFActorLocal {
    List<Actor> getActorFindAll();
    List<Actor> getActoresByNombre(String nombre);
}
