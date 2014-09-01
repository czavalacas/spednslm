package sped.negocio.BDL.IR;

import javax.ejb.Remote;

import sped.negocio.entidades.admin.UsuarioCalendario;

@Remote
public interface BDL_T_SFUsuarioCalendarioRemote {
    UsuarioCalendario persistUsuarioCalendario(UsuarioCalendario usuarioCalendario);

    UsuarioCalendario mergeUsuarioCalendario(UsuarioCalendario usuarioCalendario);

    void removeUsuarioCalendario(UsuarioCalendario usuarioCalendario);
}
