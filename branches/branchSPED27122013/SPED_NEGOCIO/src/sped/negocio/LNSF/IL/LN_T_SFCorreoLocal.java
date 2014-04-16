package sped.negocio.LNSF.IL;

import javax.ejb.Local;

import sped.negocio.entidades.beans.BeanMail;

@Local
public interface LN_T_SFCorreoLocal {
    void guardarParametros(String correo,
                           String clave,
                           String host,
                           String puerto);
}
