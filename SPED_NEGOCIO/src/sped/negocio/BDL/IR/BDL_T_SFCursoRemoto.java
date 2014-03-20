package sped.negocio.BDL.IR;

import javax.ejb.Remote;

import sped.negocio.entidades.admin.Curso;

@Remote
public interface BDL_T_SFCursoRemoto {
    Curso persistCurso(Curso curso);

    Curso mergeCurso(Curso curso);

    void removeCurso(Curso curso);
}
