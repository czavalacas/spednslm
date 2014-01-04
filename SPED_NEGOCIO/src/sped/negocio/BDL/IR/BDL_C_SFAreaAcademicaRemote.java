package sped.negocio.BDL.IR;

import java.util.List;

import javax.ejb.Remote;

import sped.negocio.entidades.admin.AreaAcademica;

@Remote
public interface BDL_C_SFAreaAcademicaRemote {
    List<AreaAcademica> getAreaAcademicaFindAll();
    AreaAcademica findEvaluadorById(int id);
}
