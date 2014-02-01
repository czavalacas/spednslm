package sped.negocio.BDL.IL;

import javax.ejb.Local;

import sped.negocio.entidades.eval.FichaCriterio;

@Local
public interface BDL_T_SFFichaCriterioLocal {
    FichaCriterio persistFichaCriterio(FichaCriterio fichaCriterio);

    FichaCriterio mergeFichaCriterio(FichaCriterio fichaCriterio);

    void removeFichaCriterio(FichaCriterio fichaCriterio);
}
