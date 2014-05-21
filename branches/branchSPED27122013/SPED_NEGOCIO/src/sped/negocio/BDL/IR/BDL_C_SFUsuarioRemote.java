package sped.negocio.BDL.IR;

import java.util.List;

import java.util.Map;

import javax.ejb.Remote;

import sped.negocio.entidades.admin.Usuario;
import sped.negocio.entidades.beans.BeanUsuario;

@Remote
public interface BDL_C_SFUsuarioRemote {
    List<Usuario> getUsuarioFindAll();
    Map autenticarUsuarioBDL(String user, String clave);
    List<Usuario> getEvaluadores(String nidAreaAcademica);
    List<Usuario> getUsuarioByEstadoBDL();
    Usuario findConstrainById(int id);
    int countUsuarioByDniBDL(String dni);
    int countUsuarioByNomUsuarioBDL(String usuario);
    String getNombresUsuarioByNidUsuario(int nidUsuario);
    List<Usuario> getUsuariobyByAttrBDL(BeanUsuario beanUsuario);
    List<Usuario> getUsuariobyNidRolBDL(int nidRol);
    List<Usuario> getListUsuarioNoAdmin(BeanUsuario usu);
    int countCorreoBDL(String correo);
    Usuario getUsuarioByCorreoBDL(String correo);
    List getEvaluadores();
    List getDniUsuarios(int nidArea, int nidRol);
    List getNombresUsuarios(int nidArea, int nidRol);
    List getUsuarioUsuarios(int nidArea, int nidRol);
    List<Usuario> getUsuarioTipoProfesor();
    boolean testClave(int nidUsuario,
                      String clave);
    boolean getIsSupervisor(int nidUsuario);
    /**
     * Metodo que trae el correo del usuario segun su ID.
     * @param dni - DNI del usuario
     * @author dfloresgonz
     * @since 20.05.2014
     * @return el correo del usuario
     */
    String getCorreoByNidUsuario_BDL(String dni);
    /**
     * Trae el rol y el nombre del usuario concatenado
     * @author dfloresgonz
     * @since 20.05.2014
     * @param nidUsuario PK de admusua
     * @return String que contiene el rol y el nombre del usuario segun el nidUsuario
     */
    String getRolNombreUsuario_BDL(int nidUsuario);
}
