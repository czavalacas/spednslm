package sped.negocio.LNSF.IR;

import javax.ejb.Remote;

import sped.negocio.entidades.beans.BeanMail;

@Remote
public interface LN_C_SFCorreoRemote {
    String enviarCorreo(String data[]);
    boolean enviarCorreoHTML(String data[]);
    String recuperarClave(String correo,
                          int evento,
                          String direccion);
    BeanMail getMail();
    boolean correoPrueba();    
}
