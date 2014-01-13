package sped.negocio.LNSF.IR;

import java.util.List;

import javax.ejb.Remote;

import sped.negocio.entidades.beans.BeanConstraint;

@Remote
public interface LN_C_SFUtilsRemote {
    List<BeanConstraint> getListaConstraintsLN(String nombreCampo,
                                               String nombreTabla);
}
