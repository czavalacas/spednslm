package sped.negocio.LNSF.SFBean;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
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
import javax.activation.DataSource;
import javax.ejb.EJB;
import javax.mail.MessagingException;
import net.sf.dozer.util.mapping.DozerBeanMapper;
import net.sf.dozer.util.mapping.MapperIF;
import sped.negocio.BDL.IL.BDL_C_SFUsuarioLocal;
import sped.negocio.BDL.IL.BDL_T_SFUsuarioLocal;
import sped.negocio.BDL.IR.BDL_C_SFEmailRemote;
import sped.negocio.LNSF.IL.LN_C_SFCorreoLocal;
import sped.negocio.LNSF.IL.LN_T_SFLoggerLocal;
import sped.negocio.LNSF.IR.LN_C_SFCorreoRemote;
import sped.negocio.Utils.Utiles;
import sped.negocio.entidades.admin.Usuario;
import sped.negocio.entidades.beans.BeanMail;
import sped.negocio.entidades.sist.Email;

@Stateless(name = "LN_C_SFCorreo", mappedName = "mapLN_C_SFCorreo")
public class LN_C_SFCorreoBean implements LN_C_SFCorreoRemote, 
                                          LN_C_SFCorreoLocal {
    @Resource
    SessionContext sessionContext;
    @EJB
    private BDL_C_SFUsuarioLocal bd_C_SFUsuarioLocal;
    @EJB
    private BDL_C_SFEmailRemote bd_C_SFEmailRemote;
    @EJB
    private BDL_T_SFUsuarioLocal bdL_T_SFUsuarioLocal;
    @EJB
    private LN_T_SFLoggerLocal ln_T_SFLoggerLocal;
    private static final String CLASE = "sped.negocio.LNSF.SFBean.LN_C_SFCorreoBean";
    private MapperIF mapper = new DozerBeanMapper();
    
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
        String correos = null;
        try {
            Email email = bd_C_SFEmailRemote.getEmail();
            String PUERTO = email.getPuerto();
            String HOST = email.getHost();
            String CLAVE = "";
            String EMAIL_QUE_ENVIA = "";
            if (data[7].toString().compareTo("1") == 0) {
                CLAVE = data[6];
                EMAIL_QUE_ENVIA = data[5];
            } else {
                CLAVE = email.getClave();
                EMAIL_QUE_ENVIA = email.getCorreo();
            }
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
            correos = data[3];
            String[] vecCorreos = correos.split(";");
            for (int i = 0; i < vecCorreos.length; i++) {
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(vecCorreos[i]));
            }
            message.setSubject(data[2]);
            String contenido = "";
            if (data[7].toString().compareTo("1") == 0) {
                contenido = contenidoHTML2(data);
            }
            if (data[7].toString().compareTo("0") == 0) {
                contenido = contenidoHTML(data);
            }
            if (data[7].toString().compareTo("2") == 0) {
                contenido = contenidoHTMLPrueba(data);
            }
            messageBodyPart.setContent(contenido, "text/html");
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);
            if (data[7].toString().compareTo("1") == 0) {
                BodyPart messageBodyPart2 = new MimeBodyPart();
                FileDataSource source = new FileDataSource(data[1]);
                messageBodyPart2.setDataHandler(new DataHandler(source));
                messageBodyPart2.setFileName(source.getName());
                multipart.addBodyPart(messageBodyPart2);
            }
            if (data[7].toString().compareTo("0") == 0) {
                addCID("img01", data[8] + "recucontr.png", multipart);
            }
            message.setContent(multipart);
            Transport t = session.getTransport("smtp");
            t.connect(HOST, EMAIL_QUE_ENVIA, CLAVE);
            t.sendMessage(message, message.getAllRecipients());
            valida = true;
            t.close();
        } catch (Exception ex) {
            ln_T_SFLoggerLocal.registrarLogErroresSistema(0, "OTR", CLASE, "boolean enviarCorreoHTML(String data[])",
                                                          "Error al ejecutar enviarCorreoHTML correos: " + correos,
                                                          Utiles.getStack(ex));
            return valida;
        }
        return valida;
    }
    
    /**
     * Enviar correo al profesor evaluado 
     * @param data[] 0= profesor, 1=fecha, 2=rol+evaluador, 3=curso, 4=aula, 5=sede, 6=grado, 7=Correo 
     * @author dfloresgonz
     * @since 20.05.2014
     */
    public void enviarCorreoNotificacionProfesorEvaluado(String data[]) {
      try {
          Email email = bd_C_SFEmailRemote.getEmail();
          String PUERTO = email.getPuerto();
          String HOST = email.getHost();
          String CLAVE = email.getClave();
          String EMAIL_QUE_ENVIA = email.getCorreo();
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
          String correoProfesor = data[7];
          message.addRecipient(Message.RecipientType.TO, new InternetAddress(correoProfesor));
          message.setSubject("Se le ha registrado una evaluacion");
          String contenido = this.contenidoHTML_NotificacionProfesorEvaluacion(data);
          messageBodyPart.setContent(contenido, "text/html");
          Multipart multipart = new MimeMultipart();
          multipart.addBodyPart(messageBodyPart);                 
          message.setContent(multipart);
          Transport t = session.getTransport("smtp");
          t.connect(HOST, EMAIL_QUE_ENVIA, CLAVE);
          t.sendMessage(message, message.getAllRecipients());
          t.close();
      }catch (Exception ex){
          ln_T_SFLoggerLocal.registrarLogErroresSistema(new Integer(data[8]), 
                                                        "OTR",
                                                        CLASE, 
                                                        "void enviarCorreoNotificacionProfesorEvaluado(String data[])",
                                                        "Error al enviar el correo de notificacion de evaluacion al docente",Utiles.getStack(ex));
      }
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
      String contenido = "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"width: 500px;\">"
                      .concat("<tbody>")
                      .concat("<p style=\"text-align: center;\">")
                      .concat("<a><img alt=\"\" src='cid:img01' style=\"width: 690px; height: 61px;\" /></a></p>")
                      .concat("<p style=\"text-align: right;\">")
                      .concat("<strong>Fecha:</strong> "+data[0]+"</p>")
                      .concat("<p><h2>\nHola "+data[1]+"</h2>")
                      .concat(data[4])
                      .concat("<p style=\"margin-left: 40px;\">\n<strong>Usuario : "+data[5]+"</strong></p>")
                      .concat("<p style=\"margin-left: 40px;\"><strong>Clave : "+data[6]+"</strong></p></p>")
                      .concat("</tbody>")
                      .concat("</table>");      
      return contenido;
    }
    
    /**
     * Se genera el contenido que enviara el correo, en formato HTML con la informacion basica de la evaluacion
     * @author dfloresgonz
     * @since 20.05.2014
     * @param data[] 0= profesor, 1=fecha, 2=rol+evaluador, 3=curso, 4=aula, 5=sede, 6=grado, 7=Correo
     * @return String con el HTML a adjuntar en el correo
     */
    public String contenidoHTML_NotificacionProfesorEvaluacion(String data[]){        
      String contenido = "<p>Profesor: <strong>"+data[0]+"</strong>:</p>"
        .concat("<p>Ud. fue evaluado el dia "+data[1]+" por el <u>"+data[2]+",</u></p>")
        .concat("<table border=\"1\" cellpadding=\"1\" cellspacing=\"1\" style=\"width: 500px;\">")
        .concat("<tbody><tr><td style=\"text-align: center;\">")
        .concat("<strong>Curso</strong></td><td style=\"text-align: center;\">")
        .concat("<strong>Aula</strong></td><td style=\"text-align: center;\">")
        .concat("<strong>Sede</strong></td><td style=\"text-align: center;\">")
        .concat("<strong>Grado</strong></td></tr><tr><td>"+data[3]+"</td><td>"+data[4]+"</td>")
        .concat("<td>"+data[5]+"</td><td>"+data[6]+"</td></tr></tbody></table><p>")
        .concat("Puede consultar sus resultados en la opcion de Consultar Evaluaciones en el sistema SPED.</p>");
      return contenido;
    }
    
    public String contenidoHTMLPrueba(String data[]){        
      String rutaimg = "https://fbcdn-sphotos-c-a.akamaihd.net/hphotos-ak-frc3/t31/1932635_10203231740543500_681028106_o.jpg";
      String contenido = "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"width: 500px;\">"
                      .concat("<tbody>")
                      .concat("<p style=\"text-align: center;\">")
                      .concat("<a><img alt=\"\" src=\""+rutaimg+"\" style=\"width: 690px; height: 61px;\" /></a></p>")
                      .concat("<p style=\"text-align: right;\">")
                      .concat("<strong>Fecha:</strong> "+data[0]+"</p>")
                      .concat("<p><h2>"+data[4]+"</h2>")
                      .concat("</tbody>")
                      .concat("</table>");      
      return contenido;
    }
    
    /**
     * Metodo para añadir imagen desde un directoria al correo
     * @author angeles
     * @param cidname
     * @param pathname
     * @param multipart
     */
    public void addCID(String cidname,String pathname, Multipart multipart){
        try{
            DataSource fds = new FileDataSource(pathname);
            BodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setDataHandler(new DataHandler(fds));
            messageBodyPart.setHeader("Content-ID","<"+cidname+">");
            multipart.addBodyPart(messageBodyPart);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    /**
     * Metodo para enviar recuperar la cuenta del usuario(evento 0) y para enviar mensaje al usuario resgitrado por
     * primera ves (evento 1)
     * @param correo
     * @param evento 0 o 1
     * @return
     */
    public String recuperarClave(String correo, int evento, String ruta){
        if(bd_C_SFUsuarioLocal.countCorreoBDL(correo) != 0){
            Usuario u = bd_C_SFUsuarioLocal.getUsuarioByCorreoBDL(correo);
            if(u != null){
                String clave = Utiles.getRandomClave();
                u.setClave(clave);
                u.setIsNuevo("1");
                bdL_T_SFUsuarioLocal.mergeUsuario(u);
                SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
                Calendar cal= new GregorianCalendar();
                String[] data = new String[10];
                data[0] = formato.format(cal.getTime()); //fecha
                data[1] = u.getNombres(); //pdf - nombres
                data[2] = "Recuperar Clave"; //asunto            
                data[3] = correo;
                if(evento == 0){
                    data[4] = "<p>Recibimos tu solicitud para recuperar tu cuenta. Se genero una nueva clave, tendra que cambiarla cuando entre al sistema.</p>";
                }else{
                    data[4] = "<p>Bienvenido a AVANTGARD Sistema de Evaluacion para docentes.\n Le proporcianamos " +
                              "los siguientes datos. Se genero una nueva clave, tendra que cambiarla cuando entre al sistema. </p>";
                }                
                data[5] = u.getUsuario();
                data[6] = clave;
                data[7] = "0";
                data[8] = ruta;
                enviarCorreoHTML(data);
                ln_T_SFLoggerLocal.registrarLogErroresSistema(0, 
                                                              "OTR",
                                                              CLASE, 
                                                              "String recuperarClave(String correo, int evento, String ruta)",
                                                              "Notificacion de solicitud de Clave correo: "+correo+" usuario: "+u.getUsuario()+" Nombres: "+u.getNombres(),null);
                return "000";
            }            
        }
        return "001";
    }   
    
    public boolean correoPrueba(){
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        Email email = bd_C_SFEmailRemote.getEmail();
        Calendar cal= new GregorianCalendar();
        String[] data = new String[10];
        data[0] = formato.format(cal.getTime()); //fecha
        data[2] = "Correo de Prueba"; //asunto            
        data[3] = email.getCorreo();
        data[4] = "<p>Este es un correo de Prueba. Se configuro correctamente el Sistema de Evaluacion para docentes</p>";
        data[7] = "2";   
        return enviarCorreoHTML(data);
    }
    
    public BeanMail getMail(){
        return (BeanMail) mapper.map(bd_C_SFEmailRemote.getEmail(), BeanMail.class);
    }
        
}