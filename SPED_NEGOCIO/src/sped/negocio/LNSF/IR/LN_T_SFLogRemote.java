package sped.negocio.LNSF.IR;

import javax.ejb.Remote;

@Remote
public interface LN_T_SFLogRemote {
    Integer grabarLogLogInWS_LN(String cadenaPhoneData,Integer nidUsuario);
}
