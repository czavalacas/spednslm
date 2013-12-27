package sped.negocio.LNSF.IL;

import javax.ejb.Local;

import sped.negocio.entidades.beans.BeanError;

@Local
public interface LN_C_SFErrorLocal {
    
    BeanError getCatalogoErrores(String cidError);
}
