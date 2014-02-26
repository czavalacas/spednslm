package sped.negocio.BDL.IR;

import java.util.List;

import javax.ejb.Remote;

import sped.negocio.entidades.admin.Grado;
import sped.negocio.entidades.admin.Nivel;

@Remote
public interface BDL_C_SFGradoRemote {
    List<Grado> getGradoFindAll();
    List<Grado> findGradpPorAreaAcademica(Integer nidAreaAcademica, String dia);
    Grado findGradoById(int id);
    List<Grado> findGradosPorNivel_ByOrden(String nidNivel);
    
}
