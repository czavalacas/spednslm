package sped.negocio.LNSF.IL;

import javax.ejb.Local;

import sped.negocio.entidades.beans.BeanMail;

@Local
public interface LN_C_SFCorreoLocal {
    String enviarCorreo(String data[]);
    boolean enviarCorreoHTML(String data[]);
    String recuperarClave(String correo,
                          int evento,
                          String direccion);
    BeanMail getMail();
    boolean correoPrueba();
}
