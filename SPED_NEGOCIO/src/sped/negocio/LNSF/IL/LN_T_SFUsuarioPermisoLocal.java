package sped.negocio.LNSF.IL;

import javax.ejb.Local;

import sped.negocio.entidades.beans.BeanPermiso;

@Local
public interface LN_T_SFUsuarioPermisoLocal {
    void gestionPermisoLN(BeanPermiso permiso);
}
