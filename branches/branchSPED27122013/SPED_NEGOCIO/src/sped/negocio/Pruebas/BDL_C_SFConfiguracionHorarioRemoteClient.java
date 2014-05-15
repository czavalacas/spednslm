package sped.negocio.Pruebas;

import java.sql.Time;

import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.Hashtable;

import javax.naming.CommunicationException;
import javax.naming.Context;
import javax.naming.InitialContext;

import javax.naming.NamingException;

import sped.negocio.BDL.IR.BDL_C_SFConfiguracionHorarioRemote;
import sped.negocio.entidades.sist.ConfiguracionHorario;

public class BDL_C_SFConfiguracionHorarioRemoteClient {
    public static void main(String[] args) {
        try {
            final Context context = getInitialContext();
            BDL_C_SFConfiguracionHorarioRemote bDL_C_SFConfiguracionHorarioRemote =
                (BDL_C_SFConfiguracionHorarioRemote) context.lookup("mapBDL_C_SFConfiguracionHorario#sped.negocio.BDL.IR.BDL_C_SFConfiguracionHorarioRemote");
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
            Date fechaActual = Time.valueOf("08:00:00");
            String fechaConFormato = sdf.format(fechaActual);
            System.out.println(fechaConFormato);
   
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
