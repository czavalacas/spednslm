package sped.negocio.BDL.IR;

import java.util.List;

import javax.ejb.Remote;

import sped.negocio.entidades.admin.AreaAcademica;

@Remote
public interface BDL_C_SFAreaAcademicaRemote {
    List<AreaAcademica> getAreaAcademicaFindAll();
    AreaAcademica findEvaluadorById(int id);
    List<AreaAcademica> findAreasPorSede_ByOrden(String nidSede);
    List<AreaAcademica> getAreaNativasByArea(int opc) ;
}
