package sped.negocio.Pruebas;

import java.util.Hashtable;

import javax.naming.CommunicationException;
import javax.naming.Context;
import javax.naming.InitialContext;

import javax.naming.NamingException;

import sped.negocio.LNSF.IR.LN_C_SFUtilsRemote;

public class LN_C_SFUtilsRemoteClient {
    public static void main(String[] args) {
        try {
            final Context context = getInitialContext();
            LN_C_SFUtilsRemote lN_C_SFUtilsRemote = (LN_C_SFUtilsRemote) context.lookup("SPED_APP-SPED_NEGOCIO-LN_C_SFUtils#sped.negocio.LNSF.IR.LN_C_SFUtilsRemote");
            for(int i = 0; i < lN_C_SFUtilsRemote.getEvaluadores_LN_WS().size(); i++){
                System.out.println(" id: "+lN_C_SFUtilsRemote.getEvaluadores_LN_WS().get(i).getId()+ " desc: "+lN_C_SFUtilsRemote.getEvaluadores_LN_WS().get(i).getDescripcion());
            }
            for(int i = 0; i < lN_C_SFUtilsRemote.getPlanificadores_LN_WS().size(); i++){
                System.out.println(" ---id: "+lN_C_SFUtilsRemote.getPlanificadores_LN_WS().get(i).getId()+" ---desc: "+lN_C_SFUtilsRemote.getPlanificadores_LN_WS().get(i).getDescripcion());
            }
            for(int i = 0; i < lN_C_SFUtilsRemote.getTipoVisitaFromConstraint().size(); i++){
                System.out.println(" ---id: "+lN_C_SFUtilsRemote.getTipoVisitaFromConstraint().get(i).getId()+" ---desc: "+lN_C_SFUtilsRemote.getTipoVisitaFromConstraint().get(i).getDescripcion());
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
