package sped.negocio.BDL.IL;

import javax.ejb.Local;

@Local
public interface BDL_T_SFUtilsLocal {
    void cambiarALeidoNotificacion(String tabla,Integer nidUsuario);
}
