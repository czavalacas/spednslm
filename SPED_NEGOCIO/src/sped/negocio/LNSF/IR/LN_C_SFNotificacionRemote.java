package sped.negocio.LNSF.IR;

import javax.ejb.Remote;

@Remote
public interface LN_C_SFNotificacionRemote {
    int[] getCantidadAMostrarNotificaciones(int nidUsuario);
}
