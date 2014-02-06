package sped.negocio.LNSF.IL;

import java.util.List;

import javax.ejb.Local;

import sped.negocio.entidades.admin.Usuario;
import sped.negocio.entidades.sist.Rol;
import sped.negocio.entidades.sist.RolPermiso;

@Local
public interface LN_C_SFUsuarioPermisoLocal {
    void insertUsuarioPermisobyUsuario(Usuario usuario, 
                                       List<RolPermiso> lstRolPermiso);
    void updateUsuarioPermisobyUsuario(Usuario usuario);
}
