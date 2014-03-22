package sped.negocio.LNSF.IL;

import java.util.List;

import javax.ejb.Local;

import sped.negocio.entidades.beans.BeanCurso;

@Local
public interface LN_T_SFCursoLocal {
    String grabarCursosNuevos(List<BeanCurso> listaCursos);
    String grabarCurso(BeanCurso curso);
}
