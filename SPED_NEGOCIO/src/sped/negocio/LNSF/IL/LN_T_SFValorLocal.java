package sped.negocio.LNSF.IL;

import javax.ejb.Local;

@Local
public interface LN_T_SFValorLocal {
    String registrarNuevoValor_LN(Double valor);
}
