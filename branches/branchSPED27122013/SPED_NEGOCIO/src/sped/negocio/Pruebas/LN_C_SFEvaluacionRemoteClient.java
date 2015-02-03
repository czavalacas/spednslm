package sped.negocio.Pruebas;

import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.Hashtable;

import java.util.List;

import javax.naming.CommunicationException;
import javax.naming.Context;
import javax.naming.InitialContext;

import javax.naming.NamingException;

import sped.negocio.LNSF.IR.LN_C_SFEvaluacionRemote;
import sped.negocio.entidades.beans.BeanConsDesem;
import sped.negocio.entidades.beans.BeanEvaluacion;

public class LN_C_SFEvaluacionRemoteClient {
    public static void main(String[] args) {
        try {
            final Context context = getInitialContext();
            LN_C_SFEvaluacionRemote lN_C_SFEvaluacionRemote = (LN_C_SFEvaluacionRemote) context.lookup("SPED_APP-SPED_NEGOCIO-LN_C_SFEvaluacion#sped.negocio.LNSF.IR.LN_C_SFEvaluacionRemote");
            String strFecMin = "01/01/2013";String strFecMax = "31/02/2015";
            Date fecMin = new SimpleDateFormat("dd/MM/yyyy").parse(strFecMin);
            Date fecMax = new SimpleDateFormat("dd/MM/yyyy").parse(strFecMax);
            List<BeanConsDesem> lst = lN_C_SFEvaluacionRemote.getConsultaDesempenoValores_LN(1,null, null, null, fecMin, fecMax);
            for(BeanConsDesem e : lst){
                System.out.println(e.getNidFicha()+" | "+e.getDescFicha());
                for(int i = 0 ; i < e.getLstVals().size(); i++){
                    Object[] o = e.getLstVals().get(i);
                    System.out.println(o[1]+" - "+o[2]);
                }
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
