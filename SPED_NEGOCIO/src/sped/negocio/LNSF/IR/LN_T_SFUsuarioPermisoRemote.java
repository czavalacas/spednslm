package sped.negocio.LNSF.IR;

import javax.ejb.Remote;

import sped.negocio.entidades.beans.BeanPermiso;

@Remote
public interface LN_T_SFUsuarioPermisoRemote {
    void gestionPermisoLN(BeanPermiso permiso, int nidUsuario);
}
