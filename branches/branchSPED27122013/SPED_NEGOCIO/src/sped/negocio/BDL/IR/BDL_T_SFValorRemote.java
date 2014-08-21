package sped.negocio.BDL.IR;

import javax.ejb.Remote;

import sped.negocio.entidades.eval.Valor;

@Remote
public interface BDL_T_SFValorRemote {
    Valor persistValor(Valor valor);

    Valor mergeValor(Valor valor);

    void removeValor(Valor valor);
}
