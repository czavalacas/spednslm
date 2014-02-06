package sped.negocio.LNSF.IR;

import java.util.List;

import javax.ejb.Remote;

import sped.negocio.entidades.admin.Usuario;
import sped.negocio.entidades.sist.Rol;
import sped.negocio.entidades.sist.RolPermiso;

@Remote
public interface LN_C_SFUsuarioPermisoRemote {
    void insertUsuarioPermisobyUsuario(Usuario usuario, 
                                       List<RolPermiso> lstRolPermiso);
    void updateUsuarioPermisobyUsuario(Usuario usuario);
}
