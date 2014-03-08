package sped.negocio.BDL.IL;

import javax.ejb.Local;

@Local
public interface BDL_C_SFNotificacionLocal {
    int getCantidadNotificacionesEvaluaciones_BDL(int nidUsuario);
    int getCantidadNotificacionesParteOcurrencia_BDL(int nidUsuario);
}
