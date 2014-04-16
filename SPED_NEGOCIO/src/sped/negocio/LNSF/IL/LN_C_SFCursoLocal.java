package sped.negocio.LNSF.IL;

import java.util.List;

import javax.ejb.Local;

import sped.negocio.entidades.admin.Curso;
import sped.negocio.entidades.beans.BeanCombo;
import sped.negocio.entidades.beans.BeanCurso;

@Local
public interface LN_C_SFCursoLocal {   
    List<BeanCombo> findCursosPorAreaAcademica(Integer nidAreaAcademica, String dia);
    List<BeanCurso>  getlistaCursos();
    BeanCurso findConstrainByIdLN(int id);
    List<BeanCombo> findCursosPorAreaAcademica_ByOrden(String nidAreaAcademica, String nidSede);
    int getNidCursoByDescripcion_LN(String descripcion);
    List<BeanCombo> findCursosByArea(String nidAreaAcademica);
    List<BeanCombo> getCursoPorSedeNivelyPofesor(String nidSede, String nidNivel, String dniProfesor, int nidAreaAcademica);
}
