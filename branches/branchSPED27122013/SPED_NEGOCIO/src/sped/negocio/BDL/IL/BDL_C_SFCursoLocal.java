package sped.negocio.BDL.IL;

import java.util.List;

import javax.ejb.Local;

import sped.negocio.entidades.admin.Curso;

@Local
public interface BDL_C_SFCursoLocal {
    List<Curso> getCursoFindAll();
}
