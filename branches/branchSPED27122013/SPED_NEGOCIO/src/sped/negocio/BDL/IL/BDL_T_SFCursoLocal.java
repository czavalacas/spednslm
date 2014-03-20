package sped.negocio.BDL.IL;

import javax.ejb.Local;

import sped.negocio.entidades.admin.Curso;

@Local
public interface BDL_T_SFCursoLocal {
    Curso persistCurso(Curso curso);

    Curso mergeCurso(Curso curso);

    void removeCurso(Curso curso);
}
