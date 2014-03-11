package sped.negocio.LNSF.SFBean;

import java.text.SimpleDateFormat;

import java.util.Calendar;

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
import java.util.GregorianCalendar;
import java.util.Properties;

import javax.ejb.EJB;

import javax.mail.MessagingException;

import sped.negocio.BDL.IL.BDL_C_SFUsuarioLocal;
import sped.negocio.LNSF.IL.LN_C_SFCorreoLocal;
import sped.negocio.LNSF.IR.LN_C_SFCorreoRemote;
import sped.negocio.Utils.Utiles;
import sped.negocio.entidades.admin.Usuario;

@Stateless(name = "LN_C_SFCorreo", mappedName = "mapLN_C_SFCorreo")
public class LN_C_SFCorreoBean implements LN_C_SFCorreoRemote, 
                                             LN_C_SFCorreoLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SPED_NEGOCIO")
    private EntityManager em;
    @EJB
    private BDL_C_SFUsuarioLocal bd_C_SFUsuarioLocal;
    
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
        String CLAVE = "taller20134";//taller2013
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
    
    public boolean enviarCorreoHTML(String data[]) {
        boolean valida = false;
      try {
          String PUERTO = "587";
          String HOST = "smtp.gmail.com";
          String CLAVE = data[6];
          String EMAIL_QUE_ENVIA = data[5];          
          Multipart multipart = new MimeMultipart();
          BodyPart messageBodyPart = new MimeBodyPart();
          Properties props = new Properties();
          props.setProperty("mail.smtp.host", HOST);
          props.setProperty("mail.smtp.starttls.enable", "true");
          props.setProperty("mail.smtp.starttls.required", "true");
          props.setProperty("mail.smtp.user", EMAIL_QUE_ENVIA);
          props.setProperty("mail.smtp.auth", "true");
          props.setProperty("mail.smtp.port", PUERTO);
          Session session = Session.getDefaultInstance(props);
          MimeMessage message = new MimeMessage(session);
          message.setFrom(new InternetAddress(EMAIL_QUE_ENVIA));
          String correos = data[3];
          String[] vecCorreos = correos.split(";");
          for (int i = 0; i < vecCorreos.length; i++) {
              message.addRecipient(Message.RecipientType.TO, new InternetAddress(vecCorreos[i]));
          }
          message.setSubject(data[2]);
          String contenido = "";
          if(data[7].toString().compareTo("1") == 0){
              contenido = contenidoHTML2(data);
          }else{
              contenido = contenidoHTML(data);
          }
          messageBodyPart.setContent(contenido, "text/html");
          multipart.addBodyPart(messageBodyPart);          
          if(data[7].toString().compareTo("1") == 0){
              BodyPart messageBodyPart2 = new MimeBodyPart();
              FileDataSource source = new FileDataSource(data[1]);
              messageBodyPart2.setDataHandler(new DataHandler(source));
              messageBodyPart2.setFileName(source.getName());
              multipart.addBodyPart(messageBodyPart2);
          }          
          message.setContent(multipart);
          Transport t = session.getTransport("smtp");
          t.connect(HOST, EMAIL_QUE_ENVIA, CLAVE);
          t.sendMessage(message, message.getAllRecipients());
          valida = true;
          t.close();
      }catch (MessagingException ex){
          ex.printStackTrace();
          return valida;
      }
      return valida;
    }
    
    public String contenidoHTML2(String data[]){
        String rutaimg = "https://fbcdn-sphotos-c-a.akamaihd.net/hphotos-ak-frc3/t31/1932635_10203231740543500_681028106_o.jpg";
      String contenido = "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"width: 500px;\">"
                      .concat("<tbody>")
                      .concat("<p style=\"text-align: center;\">")
                      .concat("<a><img alt=\"\" src=\""+rutaimg+"\" style=\"width: 690px; height: 61px;\" /></a></p>")
                      .concat("<p style=\"text-align: right;\">")
                      .concat("<strong>Fecha:</strong> "+data[0]+"</p>")
                      .concat("<p>"+data[4]+"</p>")
                      .concat("</tbody>")
                      .concat("</table>");
      return contenido;
    }
    
    public String contenidoHTML(String data[]){
      String rutaimg = "https://fbcdn-sphotos-c-a.akamaihd.net/hphotos-ak-frc3/t31/1932635_10203231740543500_681028106_o.jpg";
      String contenido = "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"width: 500px;\">"
                      .concat("<tbody>")
                      .concat("<p style=\"text-align: center;\">")
                      .concat("<a><img alt=\"\" src=\""+rutaimg+"\" style=\"width: 690px; height: 61px;\" /></a></p>")
                      .concat("<p style=\"text-align: right;\">")
                      .concat("<strong>Fecha:</strong> "+data[0]+"</p>")
                      .concat("<p><h2>\nHola "+data[1]+"</h2>\n<p>")
                      .concat("Recibimos tu solicitud para recuperar tu cuenta.</p>\n")
                      .concat("<p style=\"margin-left: 40px;\">\n<strong>Usuario : "+data[8]+"</strong></p>")
                      .concat("<p style=\"margin-left: 40px;\"><strong>Clave : "+data[9]+"</strong></p></p>")
                      .concat("</tbody>")
                      .concat("</table>");
      return contenido;
    }
    
    public String recuperarClave(String correo){
        if(bd_C_SFUsuarioLocal.countCorreoBDL(correo) != 0){
            Usuario u = bd_C_SFUsuarioLocal.getUsuarioByCorreoBDL(correo);
            if(u != null){
                SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
                Calendar cal= new GregorianCalendar();
                String[] data = new String[10];
                data[0] = formato.format(cal.getTime()); //fecha
                data[1] = u.getNombres(); //pdf - nombres
                data[2] = "Recuperar Clave"; //asunto            
                data[3] = correo; //correos
                data[4] = null; //mensaje
                data[5] = "siatod2013@gmail.com";//correo del que envia
                data[6] = "taller2013";//contraseña del que envia
                data[7] = "0";//tipo de correo
                data[8] = u.getUsuario();
                data[9] = u.getClave();
                enviarCorreoHTML(data);
                return "000";
            }            
        }
        return "001";
    }
    
}