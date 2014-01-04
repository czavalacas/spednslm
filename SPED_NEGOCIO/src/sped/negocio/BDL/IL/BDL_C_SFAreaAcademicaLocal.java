package sped.negocio.BDL.IL;

import java.util.List;

import javax.ejb.Local;

import sped.negocio.entidades.admin.AreaAcademica;
import sped.negocio.entidades.admin.Usuario;

@Local
public interface BDL_C_SFAreaAcademicaLocal {
    List<AreaAcademica> getAreaAcademicaFindAll();
    Usuario findEvaluadorById(int id);
}
