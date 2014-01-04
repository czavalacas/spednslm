package sped.negocio.Pruebas;

import java.util.Date;
import java.util.Hashtable;

import javax.naming.CommunicationException;
import javax.naming.Context;
import javax.naming.InitialContext;

import javax.naming.NamingException;

import sped.negocio.BDL.IR.BDL_T_SFEvaluacionRemoto;
import sped.negocio.entidades.admin.Main;
import sped.negocio.entidades.eval.Evaluacion;

public class BDL_T_SFEvaluacionRemotoClient {
    public static void main(String[] args) {
        try {
            final Context context = getInitialContext();
            BDL_T_SFEvaluacionRemoto bDL_T_SFEvaluacionRemoto =
                (BDL_T_SFEvaluacionRemoto) context.lookup("map-BDL_T_SFEvaluacion#sped.negocio.BDL.IR.BDL_T_SFEvaluacionRemoto");
          System.out.println("HOLA");
          
          
          /* Evaluacion evaluacion=new Evaluacion();
            Main main=new Main();
            main.setNidMain(2);            
            evaluacion.setMain(main);
            evaluacion.setNidEvaluador(3);            
            evaluacion.setStartDate(2013-12-30);
            evaluacion.setEndDate(endDate);
            evaluacion.setEstadoEvaluacion("PENDIENTE");
            evaluacion.setDescripcion("PRUEBA");
            bDL_T_SFEvaluacionRemoto.persistEvaluacion(evaluacion);*/
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
