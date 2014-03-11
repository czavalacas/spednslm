package sped.negocio.Pruebas;

import java.util.Hashtable;

import javax.naming.CommunicationException;
import javax.naming.Context;
import javax.naming.InitialContext;

import javax.naming.NamingException;

import sped.negocio.LNSF.IR.LN_C_SFCorreoRemote;

public class LN_C_SFCorreoRemoteClient {
    public static void main(String[] args) {
        try {
            final Context context = getInitialContext();
            LN_C_SFCorreoRemote lN_C_SFCorreoRemote =
                (LN_C_SFCorreoRemote) context.lookup("mapLN_C_SFCorreo#sped.negocio.LNSF.IR.LN_C_SFCorreoRemote");
            //String root = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/");
            //String fpath = root+"/recursos/reportes/"+getSessionBeanConsultarPregrado().getTiempoFile()+"_reporte.pdf";Utiles.sysout("fpath: "+fpath);
           /* String[] data = new String[4];
            data[0] = "kaka";
            data[1] = "/home/diego/Pictures/popupalignment.png";
            data[2] = " jkljk";
            data[3] = "dfloresgonz@gmail.com";
            //lN_C_SFCorreoRemote.enviarCorreo2();
            String msj = lN_C_SFCorreoRemote.enviarCorreo(data);*/

          /*  String[] data = new String[5];
            data[0] = "03/03/2014";
            data[1] = "Se jodio todo!!!";
            data[2] = "diego flores gonzales";
            data[3] = "evento loco";
            data[4] = "  todo esta jodido csm!!";*/
           // lN_C_SFCorreoRemote.enviarCorreo("dfloresgonz@gmail.com", data);
            String pdf = "C:/Users/David/AppData/Roaming/JDeveloper/system12.1.2.0.40.66.68/o.j2ee/drs/SPED_APP/SPED_WEBWebApp.war/recursos/img/usuarios/prueba.pdf";
            String[] data = new String[8];
            data[0] = "03/03/2014"; //fecha
            data[1] = pdf; //pdf
            data[2] = "diego flores gonzales"; //asunto            
            data[3] = "davidangeleshuaman@gmail.com"; //correos
            data[4] = "evento loco"; //mensaje
            data[5] = "siatod2013@gmail.com";//correo
            data[6] = "taller2013";//contraseña
            data[7] = "1";//tipo de correo
            System.out.println(lN_C_SFCorreoRemote.enviarCorreoHTML(data));
           // System.out.println(msj);
        } catch (CommunicationException ex) {
            System.out.println(ex.getClass().getName());
            System.out.println(ex.getRootCause().getLocalizedMessage());
            System.out.println("\n*** A CommunicationException was raised.  This typically\n*** occurs when the target WebLogic server is not running.\n");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static Context getInitialContext() throws NamingException {
        Hashtable env = new Hashtable();
        // WebLogic Server 10.x/12.x connection details
        env.put(Context.INITIAL_CONTEXT_FACTORY, "weblogic.jndi.WLInitialContextFactory");
        env.put(Context.PROVIDER_URL, "t3://127.0.0.1:7101");
        return new InitialContext(env);
    }
}
