package sped.negocio.LNSF.IL;

import javax.ejb.Local;

@Local
public interface LN_C_SFNotificacionLocal {
    int[] getCantidadAMostrarNotificaciones(int nidUsuario);
}
