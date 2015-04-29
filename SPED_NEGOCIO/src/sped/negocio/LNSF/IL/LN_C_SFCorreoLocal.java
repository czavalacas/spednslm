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
    /**
     * Enviar correo al profesor evaluado 
     * @param data[] 0= profesor, 1=fecha, 2=rol+evaluador, 3=curso, 4=aula, 5=sede, 6=grado, 7=Correo 
     * @author dfloresgonz
     * @since 20.05.2014
     */
    void enviarCorreoNotificacionProfesorEvaluado(String data[]);
    String recuperarClaveConUsuarioYCorreo(String correo, int evento, String ruta,String usuario);
    void enviarNotifCreacionUsuarioDocente(String data[]);
}