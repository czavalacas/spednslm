package sped.negocio.LNSF.IR;

import java.util.List;

import javax.ejb.Remote;

import sped.negocio.entidades.beans.BeanUsuario;

@Remote
public interface LN_C_SFUsuarioRemote {
    
    BeanUsuario autenticarUsuarioLN(String usuario,String clave);    
    List<BeanUsuario> getUsuarioByEstadoLN(String estado);
    List<BeanUsuario> getEvaluadores();
}
