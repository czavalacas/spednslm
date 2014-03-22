package sped.negocio.BDL.IR;

import javax.ejb.Remote;

import sped.negocio.entidades.admin.Profesor;

@Remote
public interface BDL_T_SFProfesorRemoto {
    Profesor persistProfesor(Profesor profesor);

    Profesor mergeProfesor(Profesor profesor);

    void removeProfesor(Profesor profesor);
}
