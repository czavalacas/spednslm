package sped.negocio.BDL.IR;

import java.util.List;

import javax.ejb.Remote;

import sped.negocio.entidades.eval.FichaCriterio;

@Remote
public interface BDL_C_SFFichaCriterioRemote {
    List<FichaCriterio> getFichaCriterioFindAll();
    List<FichaCriterio> getFichaCriteriosByFicha(int nidFicha);
    FichaCriterio findFichaCriterioById(FichaCriterio fichaCriterio);
    List<FichaCriterio> getLstFichaCriteriosByEvaluacion(int nidEvaluacion);
}
