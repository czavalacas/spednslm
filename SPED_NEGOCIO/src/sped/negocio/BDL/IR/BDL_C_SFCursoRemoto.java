package sped.negocio.BDL.IR;

import java.util.List;

import javax.ejb.Remote;

import sped.negocio.entidades.admin.Curso;

@Remote
public interface BDL_C_SFCursoRemoto {
    List<Curso> getCursoFindAll();
}
