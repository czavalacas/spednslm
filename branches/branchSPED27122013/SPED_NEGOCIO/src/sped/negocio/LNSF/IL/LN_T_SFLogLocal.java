package sped.negocio.LNSF.IL;

import javax.ejb.Local;

@Local
public interface LN_T_SFLogLocal {
    Integer grabarLogLogInWS_LN(String cadenaPhoneData,Integer nidUsuario);
}
