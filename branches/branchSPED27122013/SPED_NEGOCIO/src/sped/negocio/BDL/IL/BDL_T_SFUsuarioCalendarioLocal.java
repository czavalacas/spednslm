package sped.negocio.BDL.IL;

import javax.ejb.Local;

import sped.negocio.entidades.admin.UsuarioCalendario;

@Local
public interface BDL_T_SFUsuarioCalendarioLocal {
    UsuarioCalendario persistUsuarioCalendario(UsuarioCalendario usuarioCalendario);

    UsuarioCalendario mergeUsuarioCalendario(UsuarioCalendario usuarioCalendario);

    void removeUsuarioCalendario(UsuarioCalendario usuarioCalendario);
}
