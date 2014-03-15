package sped.negocio.LNSF.IR;

import javax.ejb.Remote;

@Remote
public interface LN_T_SFUtilsRemote {
    void cambiarALeidoNotificacion_LN(String tabla,Integer nidUsuario);
}
