package sped.negocio.BDL.IR;

import java.util.List;

import javax.ejb.Remote;

import sped.negocio.entidades.eval.CriterioIndicador;

@Remote
public interface BDL_C_SFCriterioIndicadorRemote {
    List<CriterioIndicador> getCriterioIndicadorFindAll();
    CriterioIndicador findCriterioIndicadorById(int id);
    List<CriterioIndicador> getLstIndicadoresByFichaCriterio_BDL_WS(int nidFicha, int nidCriterio);
    int cantidadIndicadoresByCriterio_BDL(int nidCriterio,int nidFicha);
}
