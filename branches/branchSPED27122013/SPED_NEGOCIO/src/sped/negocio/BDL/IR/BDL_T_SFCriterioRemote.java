package sped.negocio.BDL.IR;

import javax.ejb.Remote;

import sped.negocio.entidades.eval.Criterio;

@Remote
public interface BDL_T_SFCriterioRemote {
    Criterio persistCriterio(Criterio criterio);

    Criterio mergeCriterio(Criterio criterio);

    void removeCriterio(Criterio criterio);
}
