package sped.negocio.LNSF.IR;

import java.util.List;

import javax.ejb.Remote;

import sped.negocio.entidades.admin.Curso;
import sped.negocio.entidades.beans.BeanCurso;

@Remote
public interface LN_C_SFCursoRemoto {   
    List<BeanCurso> findCursosPorAreaAcademica(Integer nidAreaAcademica, String dia);
    List<BeanCurso>  getlistaCursos();
    BeanCurso findConstrainByIdLN(int id);
    List<BeanCurso> findCursosPorAreaAcademica_ByOrden(String nidAreaAcademica, String nidSede);
    int getNidCursoByDescripcion_LN(String descripcion);
}
