package sped.negocio.Pruebas;

import java.util.Hashtable;

import java.util.List;

import javax.naming.CommunicationException;
import javax.naming.Context;
import javax.naming.InitialContext;

import javax.naming.NamingException;

import sped.negocio.BDL.IR.BDL_C_SFCriterioRemote;
import sped.negocio.entidades.beans.BeanCriterio;
import sped.negocio.entidades.eval.Criterio;

public class BDL_C_SFCriterioRemoteClient {
    public static void main(String[] args) {
        try {
            final Context context = getInitialContext();
            BDL_C_SFCriterioRemote bDL_C_SFCriterioRemote = (BDL_C_SFCriterioRemote) context.lookup("mapBDL_C_SFCriterio#sped.negocio.BDL.IR.BDL_C_SFCriterioRemote");
            BeanCriterio beanCriterio = new BeanCriterio();
            beanCriterio.setDescripcionCriterio("a");
            List<Criterio> lstCrits = bDL_C_SFCriterioRemote.getCriteriosByAttr_BDL(beanCriterio);
            for(Criterio cri : lstCrits){
                System.out.println("nid: "+cri.getNidCriterio());
                System.out.println("desc: "+cri.getDescripcionCriterio());
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
