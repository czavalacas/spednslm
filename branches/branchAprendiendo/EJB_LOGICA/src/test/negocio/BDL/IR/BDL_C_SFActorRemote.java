package test.negocio.BDL.IR;

import java.util.Date;
import java.util.List;

import javax.ejb.Remote;

import test.negocio.entidades.Actor;

@Remote
public interface BDL_C_SFActorRemote {
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
