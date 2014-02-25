package sped.negocio.Pruebas;

import java.util.Hashtable;

import java.util.List;

import javax.naming.CommunicationException;
import javax.naming.Context;
import javax.naming.InitialContext;

import javax.naming.NamingException;

import sped.negocio.LNSF.IR.LN_C_SFMainRemote;
import sped.negocio.entidades.beans.BeanMainWS;

public class LN_C_SFMainRemoteClient {
    public static void main(String[] args) {
        try {
            final Context context = getInitialContext();
            LN_C_SFMainRemote ln_C_SFMainRemote = (LN_C_SFMainRemote) context.lookup("SPED_APP-SPED_NEGOCIO-LN_C_SFMain#sped.negocio.LNSF.IR.LN_C_SFMainRemote");
            List<BeanMainWS> getMainByAttr_LN_WS = ln_C_SFMainRemote.getMainByAttr_LN_WS(new Integer(1),
                                                                                         null,
                                                                                         null,
                                                                                         null);
            for(BeanMainWS e : getMainByAttr_LN_WS){
                System.out.println("nidMain: "+e.getNidMain());
                System.out.println("prof: "+e.getProfesor());
                System.out.println("===================================");
            }
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
