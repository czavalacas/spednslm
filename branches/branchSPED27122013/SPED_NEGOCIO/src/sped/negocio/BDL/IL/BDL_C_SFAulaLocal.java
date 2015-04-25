package sped.negocio.BDL.IL;

import java.util.List;

import javax.ejb.Local;

import sped.negocio.entidades.admin.Aula;
import sped.negocio.entidades.beans.BeanAula;

@Local
public interface BDL_C_SFAulaLocal {
    List<Aula> getAulaFindAll();
    Aula findAulaById(int nidAula);
    int getAulaByDescripcion(BeanAula beanAula);
    List<Aula> getAulaPorSedeNivelYGrado(String nidSede, String nidGrado, String nidNivel);
    List<Aula> getAulaPorSedeNivelProfesorYArea(String nidSede, String nidNivel, String dniProfesor, Integer nidAreaAcademica, String nidCurso);
    List<Aula> getAulaBySedeNivelYGrado(String nidSede, String nidGrado, String nidNivel);
}
