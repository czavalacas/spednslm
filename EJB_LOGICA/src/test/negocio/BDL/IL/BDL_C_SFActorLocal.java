package test.negocio.BDL.IL;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import test.negocio.entidades.Actor;

@Local
public interface BDL_C_SFActorLocal {
    List<Actor> getActorFindAll();

    List<Actor> getActoresByNombre(String nombre);

    Actor persistActor(Actor actor);

    Actor mergeActor(Actor actor);

    void removeActor(Actor actor);
    void registrarActor(int tipoEvento,
                        String nombres,
                        String apellidos,
                        Date fecha,
                        short id);
}
