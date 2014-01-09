package sped.negocio.BDL.IL;

import javax.ejb.Local;

import sped.negocio.entidades.beans.BeanConstraint;

@Local
public interface BDL_C_SFUtilsLocal {
    
    BeanConstraint getCatalogoConstraints(String nombreCampo, 
                                          String nombreTabla, 
                                          String valorCampo);
}
