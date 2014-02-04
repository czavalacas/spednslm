package sped.negocio.LNSF.IR;

import java.util.List;

import javax.ejb.Remote;

import sped.negocio.entidades.beans.BeanPermiso;

@Remote
public interface LN_C_SFPermisosRemote {
    
    List<BeanPermiso> getCrearArbolNuevo(int nidRol);
    List<BeanPermiso> getPermisos_WS(int nidRol,
                                     int nidUsuario);
}
