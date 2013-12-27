package sped.negocio.LNSF.IL;

import javax.ejb.Local;

import sped.negocio.entidades.beans.BeanUsuario;

@Local
public interface LN_C_SFUsuarioLocal {
    
    BeanUsuario autenticarUsuarioLN(String usuario,String clave);
}
