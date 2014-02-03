package sped.negocio.BDL.IL;

import java.util.List;

import javax.ejb.Local;

import sped.negocio.entidades.eval.FichaCriterio;

@Local
public interface BDL_C_SFFichaCriterioLocal {
    List<FichaCriterio> getFichaCriterioFindAll();
    List<FichaCriterio> getFichaCriteriosByFicha(int nidFicha);
    FichaCriterio findFichaCriterioById(FichaCriterio fichaCriterio);
    List<FichaCriterio> getLstFichaCriteriosByEvaluacion(int nidEvaluacion);
}
