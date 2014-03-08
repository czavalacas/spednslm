package sped.negocio.BDL.IR;

import java.util.List;

import javax.ejb.Remote;

import sped.negocio.entidades.admin.Usuario;
import sped.negocio.entidades.sist.UsuarioPermiso;

@Remote
public interface BDL_C_SFUsuarioPermisoRemote {
    List<UsuarioPermiso> getUsuarioPermisoByUsuario(Usuario u);
    UsuarioPermiso findConstrainById(int id);
}
