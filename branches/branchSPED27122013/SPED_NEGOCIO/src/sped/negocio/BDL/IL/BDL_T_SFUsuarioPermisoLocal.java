package sped.negocio.BDL.IL;

import javax.ejb.Local;

import sped.negocio.entidades.sist.UsuarioPermiso;

@Local
public interface BDL_T_SFUsuarioPermisoLocal {
    UsuarioPermiso persistUsuarioPermiso(UsuarioPermiso usuarioPermiso);

    UsuarioPermiso mergeUsuarioPermiso(UsuarioPermiso usuarioPermiso);

    void removeUsuarioPermiso(UsuarioPermiso usuarioPermiso);
}
