package sped.negocio.BDL.IR;

import java.util.List;

import javax.ejb.Remote;

import sped.negocio.entidades.admin.AreaAcademica;
import sped.negocio.entidades.admin.Usuario;

@Remote
public interface BDL_C_SFAreaAcademicaRemote {
    List<AreaAcademica> getAreaAcademicaFindAll();
    Usuario findEvaluadorById(int id);
}
