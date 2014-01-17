package sped.negocio.BDL.IR;

import java.util.List;

import javax.ejb.Remote;

import sped.negocio.entidades.admin.SedeNivel;

@Remote
public interface BDL_C_SFSedeNivelRemote {
    List<SedeNivel> getSedeNivelFindAll();
    List<SedeNivel> getSedeNivelbyNidSedeBDL(int nidSede);
    SedeNivel findSedeNivelById(int nidSede, 
                                int nidNivel);
}
