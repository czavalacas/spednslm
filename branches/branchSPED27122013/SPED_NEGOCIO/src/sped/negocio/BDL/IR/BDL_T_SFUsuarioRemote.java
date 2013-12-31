package sped.negocio.BDL.IR;

import javax.ejb.Remote;

import sped.negocio.entidades.admin.Usuario;

@Remote
public interface BDL_T_SFUsuarioRemote {
    Usuario persistUsuario(Usuario usuario);

    Usuario mergeUsuario(Usuario usuario);
}
