package sped.negocio.BDL.IR;

import java.util.List;

import javax.ejb.Remote;

import sped.negocio.entidades.eval.CriterioIndicador;

@Remote
public interface BDL_C_SFCriterioIndicadorRemote {
    List<CriterioIndicador> getCriterioIndicadorFindAll();
    List<CriterioIndicador> getLstIndicadoresByFichaCriterio_BDL_WS(int nidFicha, int nidCriterio);
}
