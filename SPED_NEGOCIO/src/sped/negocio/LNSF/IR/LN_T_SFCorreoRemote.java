package sped.negocio.LNSF.IR;

import javax.ejb.Remote;

import sped.negocio.entidades.beans.BeanMail;

@Remote
public interface LN_T_SFCorreoRemote {
    void guardarParametros(String correo,
                           String clave,
                           String host,
                           String puerto);
}
