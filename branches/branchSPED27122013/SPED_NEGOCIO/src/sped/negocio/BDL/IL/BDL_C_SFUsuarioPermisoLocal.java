package sped.negocio.BDL.IL;

import java.util.List;

import javax.ejb.Local;

import sped.negocio.entidades.admin.Usuario;
import sped.negocio.entidades.sist.RolPermiso;
import sped.negocio.entidades.sist.UsuarioPermiso;

@Local
public interface BDL_C_SFUsuarioPermisoLocal {
    List<UsuarioPermiso> getUsuarioPermisoByUsuario(Usuario u);
    UsuarioPermiso findConstrainById(int id);
}
