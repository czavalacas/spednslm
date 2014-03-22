package sped.negocio.BDL.IL;

import javax.ejb.Local;

import sped.negocio.entidades.admin.AreaAcademica;

@Local
public interface BDL_T_SFAreaAcademicaLocal {
    AreaAcademica persistAreaAcademica(AreaAcademica areaAcademica);

    AreaAcademica mergeAreaAcademica(AreaAcademica areaAcademica);

    void removeAreaAcademica(AreaAcademica areaAcademica);
}
