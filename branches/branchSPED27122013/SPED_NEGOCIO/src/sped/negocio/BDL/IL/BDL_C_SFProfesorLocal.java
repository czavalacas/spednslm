package sped.negocio.BDL.IL;

import java.util.List;

import javax.ejb.Local;

import sped.negocio.entidades.admin.Profesor;

@Local
public interface BDL_C_SFProfesorLocal {
    List<Profesor> getProfesorFindAll();
    Profesor getProfesorBydni(String dni);
}
