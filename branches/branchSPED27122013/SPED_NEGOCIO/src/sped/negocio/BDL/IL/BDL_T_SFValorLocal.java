package sped.negocio.BDL.IL;

import javax.ejb.Local;

import sped.negocio.entidades.eval.Valor;

@Local
public interface BDL_T_SFValorLocal {
    Valor persistValor(Valor valor);

    Valor mergeValor(Valor valor);

    void removeValor(Valor valor);
}
