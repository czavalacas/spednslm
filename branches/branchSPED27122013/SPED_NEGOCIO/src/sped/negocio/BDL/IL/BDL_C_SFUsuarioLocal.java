package sped.negocio.BDL.IL;

import java.util.List;

import java.util.Map;

import javax.ejb.Local;

import sped.negocio.entidades.admin.Usuario;

@Local
public interface BDL_C_SFUsuarioLocal {
    List<Usuario> getUsuarioFindAll();
    Map autenticarUsuarioBDL(String user, String clave);
    List<Usuario> getEvaluadores();
}
