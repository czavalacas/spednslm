package sped.negocio.BDL.IL;

import java.util.List;

import javax.ejb.Local;

import sped.negocio.entidades.admin.SedeNivel;

@Local
public interface BDL_C_SFSedeNivelLocal {
    List<SedeNivel> getSedeNivelFindAll();
    List<SedeNivel> getSedeNivelbyNidSedeBDL(int nidSede);
    SedeNivel findSedeNivelById(int nidSede, 
                                int nidNivel);
}
