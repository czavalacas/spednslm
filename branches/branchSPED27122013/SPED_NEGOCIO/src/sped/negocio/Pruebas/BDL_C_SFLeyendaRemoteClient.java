package sped.negocio.Pruebas;

import java.util.Hashtable;

import javax.naming.CommunicationException;
import javax.naming.Context;
import javax.naming.InitialContext;

import javax.naming.NamingException;

import sped.negocio.BDL.IR.BDL_C_SFLeyendaRemote;
import sped.negocio.entidades.eval.CriterioIndicador;

public class BDL_C_SFLeyendaRemoteClient {
    public static void main(String[] args) {
        try {
            final Context context = getInitialContext();
            BDL_C_SFLeyendaRemote bdL_C_SFLeyendaRemote = (BDL_C_SFLeyendaRemote) context.lookup("mapBDL_C_SFLeyenda#sped.negocio.BDL.IR.BDL_C_SFLeyendaRemote");
            //String s = bdL_C_SFLeyendaRemote.getLeyendabyEvaluacion_BDL(3,9,4,5);
          //  System.out.println("s:"+s);
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
