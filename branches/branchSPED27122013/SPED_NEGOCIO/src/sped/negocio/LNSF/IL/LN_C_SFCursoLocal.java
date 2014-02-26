package sped.negocio.LNSF.IL;

import java.util.List;

import javax.ejb.Local;

import sped.negocio.entidades.admin.Curso;
import sped.negocio.entidades.beans.BeanCurso;

@Local
public interface LN_C_SFCursoLocal {   
    List<BeanCurso> findCursosPorAreaAcademica(Integer nidAreaAcademica, String dia);
    List<BeanCurso>  getlistaCursos();
    BeanCurso findConstrainByIdLN(int id);
    List<BeanCurso> findCursosPorAreaAcademica_ByOrden(String nidAreaAcademica, String nidSede);
}
