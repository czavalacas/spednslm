package sped.negocio.LNSF.IR;

import java.util.List;

import javax.ejb.Remote;

import sped.negocio.entidades.beans.BeanConstraint;

@Remote
public interface LN_T_SFUtilsRemote {
    void cambiarALeidoNotificacion_LN(String tabla,Integer nidUsuario);
    String actualizarConstraintConfigEvasXDia(List<BeanConstraint> lstConstraConfig);
}
