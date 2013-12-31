package sped.negocio.BDL.IR;

import java.util.List;

import java.util.Map;

import javax.ejb.Remote;

import sped.negocio.entidades.admin.Usuario;

@Remote
public interface BDL_C_SFUsuarioRemote {
    List<Usuario> getUsuarioFindAll();
    Map autenticarUsuarioBDL(String user, String clave);
    List<Usuario> getEvaluadores();
}
