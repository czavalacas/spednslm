package sped.negocio.BDL.IL;

import java.util.List;

import javax.ejb.Local;

import sped.negocio.entidades.eval.CriterioIndicador;

@Local
public interface BDL_C_SFCriterioIndicadorLocal {
    List<CriterioIndicador> getCriterioIndicadorFindAll();
    CriterioIndicador findCriterioIndicadorById(int id);
    List<CriterioIndicador> getLstIndicadoresByFichaCriterio_BDL_WS(int nidFicha, int nidCriterio);
    int cantidadIndicadoresByCriterio_BDL(int nidCriterio,int nidFicha);
}
