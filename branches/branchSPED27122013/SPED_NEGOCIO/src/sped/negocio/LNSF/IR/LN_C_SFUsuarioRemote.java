package sped.negocio.LNSF.IR;

import java.util.List;

import javax.ejb.Remote;

import sped.negocio.entidades.beans.BeanUsuario;

@Remote
public interface LN_C_SFUsuarioRemote {
    
    BeanUsuario autenticarUsuarioLN(String usuario,String clave);    
    List<BeanUsuario> getUsuarioByEstadoLN(String estado);
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
}
