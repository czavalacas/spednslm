package sped.negocio.BDL.IL;

import javax.ejb.Local;

import sped.negocio.entidades.admin.Usuario;

@Local
public interface BDL_T_SFUsuarioLocal {
    Usuario persistUsuario(Usuario usuario);

    Usuario mergeUsuario(Usuario usuario);
}
