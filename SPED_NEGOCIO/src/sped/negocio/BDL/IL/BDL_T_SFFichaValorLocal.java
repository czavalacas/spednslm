package sped.negocio.BDL.IL;

import javax.ejb.Local;

import sped.negocio.entidades.eval.FichaValor;

@Local
public interface BDL_T_SFFichaValorLocal {
    FichaValor persistFichaValor(FichaValor fichaValor);

    FichaValor mergeFichaValor(FichaValor fichaValor);

    void removeFichaValor(FichaValor fichaValor);
}
