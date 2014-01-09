package sped.negocio.BDL.IR;

import javax.ejb.Remote;

import sped.negocio.entidades.beans.BeanConstraint;

@Remote
public interface BDL_C_SFUtilsRemote {
    
    BeanConstraint getCatalogoConstraints(String nombreCampo, 
                                          String nombreTabla, 
                                          String valorCampo);
}
