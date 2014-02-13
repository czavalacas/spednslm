package sped.negocio.BDL.IL;

import java.util.List;

import java.util.Map;

import javax.ejb.Local;

import sped.negocio.entidades.admin.Usuario;
import sped.negocio.entidades.beans.BeanUsuario;

@Local
public interface BDL_C_SFUsuarioLocal {
    List<Usuario> getUsuarioFindAll();
    Map autenticarUsuarioBDL(String user, String clave);
    List<Usuario> getEvaluadores(String nidAreaAcademica);
    List<Usuario> getUsuarioByEstadoBDL(String estado);
    Usuario findConstrainById(int id);
    int countUsuarioByDniBDL(String dni);
    int countUsuarioByNomUsuarioBDL(String usuario);
    String getNombresUsuarioByNidUsuario(int nidUsuario);
    List<Usuario> getUsuariobyByAttrBDL(BeanUsuario beanUsuario);
    List<Usuario> getUsuariobyNidRolBDL(int nidRol);
}
