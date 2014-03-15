package sped.negocio.LNSF.IL;

import javax.ejb.Local;

@Local
public interface LN_T_SFUtilsLocal {
    void cambiarALeidoNotificacion_LN(String tabla,Integer nidUsuario);
}
