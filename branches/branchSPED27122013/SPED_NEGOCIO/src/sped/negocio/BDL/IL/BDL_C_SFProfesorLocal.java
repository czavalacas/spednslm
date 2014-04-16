package sped.negocio.BDL.IL;

import java.util.List;

import javax.ejb.Local;

import sped.negocio.entidades.admin.Profesor;

@Local
public interface BDL_C_SFProfesorLocal {
    List<Profesor> getProfesorFindAll();
    Profesor getProfesorBydni(String dni);
    List<Profesor> findProfesorPorSede_ByOrden(String nidSede, String nidArea, String nidCurso, String nidNivel, String nidGrado); 
    List<Profesor>  getProfesores();
    int existeDni(String dni);
    List getNombreProfesor();
    String getDniProfe(String nombreCompleto);
    List<Profesor> getProfesoresPorSedeNivelYArea(String nidSede, String nidNivel, Integer nidAreaAcademica);
}
