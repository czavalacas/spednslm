package sped.negocio.LNSF.IR;

import javax.ejb.Remote;

@Remote
public interface LN_T_SFValorRemote {
    String registrarNuevoValor_LN(Double valor);
}
