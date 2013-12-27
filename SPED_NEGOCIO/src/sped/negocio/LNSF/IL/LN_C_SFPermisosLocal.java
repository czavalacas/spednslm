package sped.negocio.LNSF.IL;

import java.util.List;

import javax.ejb.Local;

import sped.negocio.entidades.beans.BeanPermiso;

@Local
public interface LN_C_SFPermisosLocal {
    
    List<BeanPermiso> getCrearArbolNuevo(int nidRol);
}
