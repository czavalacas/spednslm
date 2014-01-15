package sped.negocio.BDL.IL;

import javax.ejb.Local;

import sped.negocio.entidades.eval.Criterio;

@Local
public interface BDL_T_SFCriterioLocal {
    Criterio persistCriterio(Criterio criterio);

    Criterio mergeCriterio(Criterio criterio);

    void removeCriterio(Criterio criterio);
}
