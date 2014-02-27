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
        String PUERTO = "465";
        String HOST = "smtp.gmail.com";
        String USUARIO = "siatod2013";
        String CLAVE = "taller2013";
        String DOMINIO = "@gmail.com";
        String EMAIL_QUE_ENVIA = USUARIO + DOMINIO; //tiene que ser institucional
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
            // Cierre.
            t.close();
            return "Se envio el correo electronico a " + data[3];
        } catch (Exception e) {
            e.printStackTrace();
            return "No se pudo enviar el correo electronico a " + data[3];
        }
    }
}