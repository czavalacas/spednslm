package sped.negocio.LNSF.IL;

import java.util.List;

import javax.ejb.Local;

import sped.negocio.entidades.beans.BeanCurso;

@Local
public interface LN_C_SFCursoLocal {
    List<BeanCurso> getlistaCursos();
}
