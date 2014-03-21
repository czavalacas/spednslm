package sped.negocio.BDL.IR;

import java.util.List;

import javax.ejb.Remote;

import sped.negocio.entidades.admin.Profesor;

@Remote
public interface BDL_C_SFProfesorRemote {
    List<Profesor> getProfesorFindAll();
    Profesor getProfesorBydni(String dni);
    List<Profesor> findProfesorPorSede_ByOrden(String nidSede, String nidArea, String nidCurso, String nidNivel, String nidGrado); 
    List<Profesor>  getProfesores();
    int existeDni(String dni);
}
