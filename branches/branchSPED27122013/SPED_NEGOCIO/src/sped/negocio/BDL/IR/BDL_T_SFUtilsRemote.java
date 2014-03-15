package sped.negocio.BDL.IR;

import javax.ejb.Remote;

@Remote
public interface BDL_T_SFUtilsRemote {
    void cambiarALeidoNotificacion(String tabla,Integer nidUsuario);
}
