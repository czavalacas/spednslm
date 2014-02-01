package sped.negocio.BDL.IR;

import javax.ejb.Remote;

import sped.negocio.entidades.eval.FichaValor;

@Remote
public interface BDL_T_SFFichaValorRemote {
    FichaValor persistFichaValor(FichaValor fichaValor);

    FichaValor mergeFichaValor(FichaValor fichaValor);

    void removeFichaValor(FichaValor fichaValor);
}
