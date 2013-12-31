package sped.negocio.BDL.IL;

import java.util.List;

import javax.ejb.Local;

import sped.negocio.entidades.admin.AreaAcademica;

@Local
public interface BDL_C_SFAreaAcademicaLocal {
    List<AreaAcademica> getAreaAcademicaFindAll();
}
