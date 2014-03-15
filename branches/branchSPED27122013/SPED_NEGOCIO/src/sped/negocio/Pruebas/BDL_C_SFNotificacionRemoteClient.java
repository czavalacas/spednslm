package sped.negocio.Pruebas;

import java.util.Hashtable;

import java.util.List;

import javax.naming.CommunicationException;
import javax.naming.Context;
import javax.naming.InitialContext;

import javax.naming.NamingException;

import sped.negocio.BDL.IR.BDL_C_SFNotificacionRemote;
import sped.negocio.entidades.beans.BeanNotificacionEvaluacion;

public class BDL_C_SFNotificacionRemoteClient {
    public static void main(String[] args) {
        try {
            final Context context = getInitialContext();
            BDL_C_SFNotificacionRemote bdL_C_SFNotificacionRemote = (BDL_C_SFNotificacionRemote) context.lookup("mapBDL_C_SFNotificacion#sped.negocio.BDL.IR.BDL_C_SFNotificacionRemote");
            List<BeanNotificacionEvaluacion> lst = bdL_C_SFNotificacionRemote.getListaNotificacionesByAttr_BDL(null, null, null,null,null,null,null);
            for(BeanNotificacionEvaluacion n : lst){
                System.out.println("getIndicador:"+n.getIndicador());
                System.out.println("getFechaEvaluacion:"+n.getFechaEvaluacion());
                System.out.println("getVersionFicha:"+n.getVersionFicha());
                System.out.println("getCurso:"+n.getCurso());
                System.out.println("getEvaluador:"+n.getEvaluador());
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
