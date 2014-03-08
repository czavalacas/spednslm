package sped.negocio.BDL.IR;

import javax.ejb.Remote;

@Remote
public interface BDL_C_SFNotificacionRemote {
    int getCantidadNotificacionesEvaluaciones_BDL(int nidUsuario);
    int getCantidadNotificacionesParteOcurrencia_BDL(int nidUsuario);
}
