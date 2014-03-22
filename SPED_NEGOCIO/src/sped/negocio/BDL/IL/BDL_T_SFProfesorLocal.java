package sped.negocio.BDL.IL;

import javax.ejb.Local;

import sped.negocio.entidades.admin.Profesor;

@Local
public interface BDL_T_SFProfesorLocal {
    Profesor persistProfesor(Profesor profesor);

    Profesor mergeProfesor(Profesor profesor);

    void removeProfesor(Profesor profesor);
}
