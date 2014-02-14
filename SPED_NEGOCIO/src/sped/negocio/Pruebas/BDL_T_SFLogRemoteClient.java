package sped.negocio.Pruebas;

import java.util.Hashtable;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import javax.naming.CommunicationException;
import javax.naming.Context;
import javax.naming.InitialContext;

import javax.naming.NamingException;

import sped.negocio.BDL.IL.BDL_T_SFLogRemote;
import sped.negocio.entidades.sist.Log;

public class BDL_T_SFLogRemoteClient {
    
  
    public static void main(String[] args) {
        try {
            final Context context = getInitialContext();
            BDL_T_SFLogRemote bDL_T_SFLogRemote =
                (BDL_T_SFLogRemote) context.lookup("mapBDL_T_SFLog#sped.negocio.BDL.IL.BDL_T_SFLogRemote");
            Log log = new Log();
            log.setNid_evento(new Integer(1));
            log.setDetalle("TEST");
            bDL_T_SFLogRemote.persistLog(log);
            
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
