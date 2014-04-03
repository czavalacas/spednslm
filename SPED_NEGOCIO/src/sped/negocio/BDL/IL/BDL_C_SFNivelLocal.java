package sped.negocio.BDL.IL;

import java.util.List;

import javax.ejb.Local;

import sped.negocio.entidades.admin.Nivel;

@Local
public interface BDL_C_SFNivelLocal {
    List<Nivel> getNivelFindAll();
    List<Nivel> findGradpPorAreaAcademica(Integer nidAreaAcademica, String dia);
    Nivel findNivelById(int id);
    List<Nivel> findNivelesPorSede_ByOrden(String nidSede, String nidArea, String nidCurso);
    /** Temporal getNivelesBySede */
    List<Nivel> getNivelesBySede(String nidSede);
}
