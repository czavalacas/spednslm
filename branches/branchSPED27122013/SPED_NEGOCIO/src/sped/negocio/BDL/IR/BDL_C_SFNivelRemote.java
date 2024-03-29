package sped.negocio.BDL.IR;

import java.util.List;

import javax.ejb.Remote;

import sped.negocio.entidades.admin.Nivel;

@Remote
public interface BDL_C_SFNivelRemote {
    List<Nivel> getNivelFindAll();
    List<Nivel> findGradpPorAreaAcademica(Integer nidAreaAcademica, String dia);
    Nivel findNivelById(int id);
    List<Nivel> findNivelesPorSede_ByOrden(String nidSede, String nidArea, String nidCurso);
    /** Temporal getNivelesBySede */
    List<Nivel> getNivelesBySede(String nidSede);
}
