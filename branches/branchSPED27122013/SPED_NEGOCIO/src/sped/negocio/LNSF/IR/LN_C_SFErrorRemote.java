package sped.negocio.LNSF.IR;

import javax.ejb.Remote;

import sped.negocio.entidades.beans.BeanError;

@Remote
public interface LN_C_SFErrorRemote {
    
    BeanError getCatalogoErrores(String cidError);
}
