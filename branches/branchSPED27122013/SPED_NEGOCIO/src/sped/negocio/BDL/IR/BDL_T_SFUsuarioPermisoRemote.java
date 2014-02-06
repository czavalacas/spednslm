package sped.negocio.BDL.IR;

import javax.ejb.Remote;

import sped.negocio.entidades.sist.UsuarioPermiso;

@Remote
public interface BDL_T_SFUsuarioPermisoRemote {
    UsuarioPermiso persistUsuarioPermiso(UsuarioPermiso usuarioPermiso);

    UsuarioPermiso mergeUsuarioPermiso(UsuarioPermiso usuarioPermiso);

    void removeUsuarioPermiso(UsuarioPermiso usuarioPermiso);
}
