package sped.negocio.LNSF.SFBean;

import javax.annotation.Resource;

import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import javax.activation.FileDataSource;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import javax.activation.DataHandler;

import java.util.Date;
import java.util.Properties;

import sped.negocio.LNSF.IL.LN_C_SFCorreoLocal;
import sped.negocio.LNSF.IR.LN_C_SFCorreoRemote;
import sped.negocio.Utils.Utiles;

@Stateless(name = "LN_C_SFCorreo", mappedName = "mapLN_C_SFCorreo")
public class LN_C_SFCorreoBean implements LN_C_SFCorreoRemote, 
                                             LN_C_SFCorreoLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SPED_NEGOCIO")
    private EntityManager em;

    public LN_C_SFCorreoBean() {
    }

    /**
     * @author dfloresgonz
     * @param data -> String[] con datos basicos que se incluiran en el msj
     */
    public String enviarCorreo(String data[]) {
        String PUERTO = "587";
        String HOST = "smtp.gmail.com";
        String USUARIO = "siatod2013";//"siatod2013";
        String CLAVE = "taller2013";//taller2013
        String DOMINIO = "@gmail.com";
        String EMAIL_QUE_ENVIA = USUARIO + DOMINIO;
        try {
            Multipart multipart = new MimeMultipart();
            BodyPart messageBodyPart = new MimeBodyPart();
            // Propiedades de la conexion
            Properties props = new Properties();
            props.setProperty("mail.smtp.host", HOST);
            props.setProperty("mail.smtp.starttls.enable", "true");
            props.setProperty("mail.smtp.starttls.required", "true");
            props.setProperty("mail.smtp.user", EMAIL_QUE_ENVIA);
            props.setProperty("mail.smtp.auth", "true");
            props.setProperty("mail.smtp.port", PUERTO);
            
            // Preparamos la sesion
            Session session = Session.getDefaultInstance(props);
            // Construimos el mensaje
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(EMAIL_QUE_ENVIA));
            String correos = data[3];
            String[] vecCorreos = correos.split(";");
            for (int i = 0; i < vecCorreos.length; i++) {
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(vecCorreos[i]));
            }
            message.setSubject(data[0]);
            String contenido = data[2];
            messageBodyPart.setContent(contenido, "text/html");
            multipart.addBodyPart(messageBodyPart);
           
            /*SE ADJUNTA EL REPORTE*/
            BodyPart messageBodyPart2 = new MimeBodyPart();
            FileDataSource source = new FileDataSource(data[1]);
            messageBodyPart2.setDataHandler(new DataHandler(source));
            messageBodyPart2.setFileName(source.getName());
            multipart.addBodyPart(messageBodyPart2);
            // Lo enviamos.
            message.setContent(multipart);
            message.setSentDate(new Date());
            Transport t = session.getTransport("smtp");
            t.connect(USUARIO, CLAVE);
            t.sendMessage(message, message.getAllRecipients());
            System.out.println("\n********************Se envio el correo Electronico!************\n");
            t.close();
            return "Se envio el correo electronico a " + data[3];
        } catch (Exception e) {
            e.printStackTrace();
            return "No se pudo enviar el correo electronico a " + data[3];
        }
    }
    
    public void enviarCorreoHTML(String data[]) {
      try {
          String PUERTO = "587";
          String HOST = "smtp.gmail.com";
          String USUARIO = "siatod2013";//"siatod2013";
          String CLAVE = "taller2013";//taller2013
          String DOMINIO = "@gmail.com";
          String EMAIL_QUE_ENVIA = USUARIO + DOMINIO;
          
          Multipart multipart = new MimeMultipart();
          BodyPart messageBodyPart = new MimeBodyPart();
          // Propiedades de la conexión
          Properties props = new Properties();
          props.setProperty("mail.smtp.host", HOST);
          props.setProperty("mail.smtp.starttls.enable", "true");
          props.setProperty("mail.smtp.starttls.required", "true");
          props.setProperty("mail.smtp.user", EMAIL_QUE_ENVIA);
          props.setProperty("mail.smtp.auth", "true");
          props.setProperty("mail.smtp.port", PUERTO);
          // Preparamos la sesion
          Session session = Session.getDefaultInstance(props);
          // Construimos el mensaje
          MimeMessage message = new MimeMessage(session);
          message.setFrom(new InternetAddress(EMAIL_QUE_ENVIA));
          String correos = data[5];
          String[] vecCorreos = correos.split(";");
          for (int i = 0; i < vecCorreos.length; i++) {
              message.addRecipient(Message.RecipientType.TO, new InternetAddress(vecCorreos[i]));
          }
          message.setSubject("INFO-MOBILE URP Alarma.");
          String contenido = contenidoHTML(data);
          messageBodyPart.setContent(contenido, "text/html");
          multipart.addBodyPart(messageBodyPart);
          // Lo enviamos.
          message.setContent(multipart);
          Transport t = session.getTransport("smtp");
          t.connect(HOST, USUARIO, CLAVE);
          t.sendMessage(message, message.getAllRecipients());
          System.out.println("Se envio el correo Electrónico!");
          // Cierre.
          t.close();
      } catch (Exception e) {
          e.printStackTrace();
      }
    }
    
    //falta una validacion que en la descripcion no se admita codigo HTML.
    public String contenidoHTML(String data[]){
      String contenido = "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"width: 500px;\">"
                      .concat("<tbody>")
                      .concat("<tr>")
                      .concat("<td colspan=\"2\" style = \"vertical-align:middle;\">")
                      .concat("<span style=\"font-size:10px;\"><a href=\"\"><img alt=\"LOGO\" src=\"http://img109.imageshack.us/img109/6844/iconpequeno.jpg\" style=\"height: 50px; width: 50px;\" /></a>&nbsp;<strong><span style=\"font-family:arial,helvetica,sans-serif;\"><span style=\"font-size: 12px;\"><span style=\"color: rgb(0, 128, 0);\">INFO MOBILE</span></span></span></strong></span></td>")
                      .concat("<td colspan=\"2\">")
                      .concat("<span style=\"color:#008000;\">Fecha:</span>&nbsp; "+data[0]+"</td>")
                      .concat("</tr>")
                      .concat("<tr>")
                      .concat("<td>")
                      .concat("<span style=\"color:#008000;\">Alarma enviada :</span>"+data[1]+"</td>")
                      .concat("<td>")
                      .concat("<span style=\"color:#008000;\">Alumno:</span>&nbsp;"+data[2]+"</td>")
                      .concat("<td>")
                      .concat("<span style=\"color:#008000;\">Evento:</span>&nbsp;"+data[3]+"</td>")
                      .concat("</tr>")
                      .concat("<tr>")
                      .concat("<td colspan=\"3\" rowspan=\"1\">")
                      .concat("<span style=\"color:#008000;\">Nota:</span>&nbsp;"+data[4]+"</td>")
                      .concat("</tr>")
                      .concat("</tbody>")
                      .concat("</table>");
      return contenido;
    }
}