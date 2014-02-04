package sped.negocio.Pruebas;

import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

import javax.naming.CommunicationException;
import javax.naming.Context;
import javax.naming.InitialContext;

import javax.naming.NamingException;

import sped.negocio.BDL.IR.BDL_C_SFEvaluacionRemoto;
import sped.negocio.entidades.eval.Evaluacion;

import utils.system;

public class BDL_C_SFEvaluacionRemotoClient {
    public static void main(String[] args) {
        try {
            final Context context = getInitialContext();
            BDL_C_SFEvaluacionRemoto bDL_C_SFEvaluacionRemoto =
                (BDL_C_SFEvaluacionRemoto) context.lookup("map-BDL_C_SFEvaluacion#sped.negocio.BDL.IR.BDL_C_SFEvaluacionRemoto");
       /* Evaluacion eva=bDL_C_SFEvaluacionRemoto.getEvaluacionById("4316-BBHG-GJGC-QWSP-9224");
            System.out.println(" ENTIDA"+eva.getNidEvaluacion()+" - "+eva.getStartDate()+" - "+eva.getEndDate());*/
            
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
            Date fechaActual = new Date();           
            String fechaConFormato = sdf.format(fechaActual);        
           List<Evaluacion> listEva=bDL_C_SFEvaluacionRemoto.getEvaluaciones(fechaConFormato, 2,2,"1","1",1);
            Iterator it= listEva.iterator();
            while(it.hasNext()){
                Evaluacion entida=(Evaluacion)it.next();
                System.out.println(entida.getMain().getNidMain());  
            }
             System.out.println();
       
        } catch (CommunicationException ex) {
            System.out.println(ex.getClass().getName());
            System.out.println(ex.getRootCause().getLocalizedMessage());
            System.out.println("\n*** A CommunicationException was raised.  This typically\n*** occurs when the target WebLogic server is not running.\n");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void printEvaluacion(Evaluacion evaluacion) {
        System.out.println("descripcion = " + evaluacion.getDescripcion());
        System.out.println("endDate = " + evaluacion.getEndDate());
        System.out.println("estadoEvaluacion = " + evaluacion.getEstadoEvaluacion());
        System.out.println("nidDate = " + evaluacion.getNidDate());
        System.out.println("nidEvaluacion = " + evaluacion.getNidEvaluacion());
        System.out.println("nidEvaluador = " + evaluacion.getNidEvaluador());
        System.out.println("startDate = " + evaluacion.getStartDate());
        System.out.println("main = " + evaluacion.getMain());
        System.out.println("resultadoLista = " + evaluacion.getResultadoLista());
    }

    private static Context getInitialContext() throws NamingException {
        Hashtable env = new Hashtable();
        // WebLogic Server 10.x/12.x connection details
        env.put(Context.INITIAL_CONTEXT_FACTORY, "weblogic.jndi.WLInitialContextFactory");
        env.put(Context.PROVIDER_URL, "t3://127.0.0.1:7101");
        return new InitialContext(env);
    }
}
