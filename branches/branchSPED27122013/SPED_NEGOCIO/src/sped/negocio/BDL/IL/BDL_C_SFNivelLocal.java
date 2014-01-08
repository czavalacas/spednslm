package sped.negocio.BDL.IL;

import java.util.List;

import javax.ejb.Local;

import sped.negocio.entidades.admin.Nivel;

@Local
public interface BDL_C_SFNivelLocal {
    List<Nivel> getNivelFindAll();
}
