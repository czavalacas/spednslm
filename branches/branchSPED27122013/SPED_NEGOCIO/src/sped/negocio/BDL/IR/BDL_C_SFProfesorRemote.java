package sped.negocio.BDL.IR;

import java.util.List;

import javax.ejb.Remote;

import sped.negocio.entidades.admin.Profesor;

@Remote
public interface BDL_C_SFProfesorRemote {
    List<Profesor> getProfesorFindAll();
    Profesor getProfesorBydni(String dni);
}
