package sped.negocio.BDL.IR;

import javax.ejb.Remote;

import sped.negocio.entidades.eval.FichaCriterio;

@Remote
public interface BDL_T_SFFichaCriterioRemote {
    FichaCriterio persistFichaCriterio(FichaCriterio fichaCriterio);

    FichaCriterio mergeFichaCriterio(FichaCriterio fichaCriterio);

    void removeFichaCriterio(FichaCriterio fichaCriterio);
}
