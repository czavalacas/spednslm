package sped.negocio.LNSF.IL;

import java.util.List;

import javax.ejb.Local;

import sped.negocio.entidades.beans.BeanConstraint;

@Local
public interface LN_T_SFUtilsLocal {
    void cambiarALeidoNotificacion_LN(String tabla,Integer nidUsuario);
    String actualizarConstraintConfigEvasXDia(List<BeanConstraint> lstConstraConfig);
}
