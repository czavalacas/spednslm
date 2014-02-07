package sped.negocio.BDL.IL;

import java.util.List;

import javax.ejb.Local;

import sped.negocio.entidades.eval.CriterioIndicador;

@Local
public interface BDL_C_SFCriterioIndicadorLocal {
    List<CriterioIndicador> getCriterioIndicadorFindAll();
    List<CriterioIndicador> getLstIndicadoresByFichaCriterio_BDL_WS(int nidFicha, int nidCriterio);
}
