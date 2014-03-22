package sped.negocio.BDL.IR;

import javax.ejb.Remote;

import sped.negocio.entidades.admin.AreaAcademica;

@Remote
public interface BDL_T_SFAreaAcademicaRemoto {
    AreaAcademica persistAreaAcademica(AreaAcademica areaAcademica);

    AreaAcademica mergeAreaAcademica(AreaAcademica areaAcademica);

    void removeAreaAcademica(AreaAcademica areaAcademica);
}
