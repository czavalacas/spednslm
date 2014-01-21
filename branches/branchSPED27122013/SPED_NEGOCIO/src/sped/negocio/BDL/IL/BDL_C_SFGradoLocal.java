package sped.negocio.BDL.IL;

import java.util.List;

import javax.ejb.Local;

import sped.negocio.entidades.admin.Grado;

@Local
public interface BDL_C_SFGradoLocal {
    List<Grado> getGradoFindAll();
    List<Grado> findGradpPorAreaAcademica(Integer nidAreaAcademica, String dia);
}
