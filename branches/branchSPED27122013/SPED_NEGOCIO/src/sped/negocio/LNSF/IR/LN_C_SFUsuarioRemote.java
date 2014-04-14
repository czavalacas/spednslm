package sped.negocio.LNSF.IR;

import java.util.List;

import javax.ejb.Remote;

import sped.negocio.entidades.beans.BeanUsuario;

@Remote
public interface LN_C_SFUsuarioRemote {
    
    BeanUsuario autenticarUsuarioLN(String usuario,String clave);    
    List<BeanUsuario> getUsuarioByEstadoLN();
    List<BeanUsuario> getEvaluadores(String nidAreaAcademica);
    boolean countUsuarioByDniLN(String dni);
    boolean countUsuarioByNomUsuarioLN(String usuario);
    List<BeanUsuario> getUsuariobyByAttrLN(String nombres,
                                           String usuario,
                                           String dni,
                                           int nidAreaAcademica,
                                           int nidRol,
                                           int estadoUsuario,
                                           int nidSede,
                                           int nidNivel);
    List<BeanUsuario> getUsuariobyNidRolLN(int nidRol);
    BeanUsuario autenticarUsuarioLN_WS(String usuario,String clave,String cadenaPhoneData);
    BeanUsuario findConstrainByIdLN(int id);
    String getNombresUsuarioByNidUsuario_LN(int nidUsuario);
    List<BeanUsuario> getListUsuarioNoAdminLN(String nombre, String usuario, String nidRol);
    List getEvaluadores_LN();
    List getDniUsuarios_LN();
    List getNombresUsuarios_LN(int nidArea);
    List getUsuarioUsuarios_LN(int nidArea);
}
