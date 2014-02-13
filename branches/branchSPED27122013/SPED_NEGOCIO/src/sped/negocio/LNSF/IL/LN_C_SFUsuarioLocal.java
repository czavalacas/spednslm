package sped.negocio.LNSF.IL;

import java.util.List;

import javax.ejb.Local;

import sped.negocio.entidades.beans.BeanUsuario;

@Local
public interface LN_C_SFUsuarioLocal {    
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
}
