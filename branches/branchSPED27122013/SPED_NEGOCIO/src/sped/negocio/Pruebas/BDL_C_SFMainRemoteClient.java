package sped.negocio.Pruebas;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

import javax.naming.CommunicationException;
import javax.naming.Context;
import javax.naming.InitialContext;

import javax.naming.NamingException;

import sped.negocio.BDL.IR.BDL_C_SFMainRemote;
import sped.negocio.entidades.admin.Main;
import sped.negocio.entidades.beans.BeanMain;

public class BDL_C_SFMainRemoteClient {
    public static void main(String[] args) {
        try {
            final Context context = getInitialContext();
            BDL_C_SFMainRemote bDL_C_SFMainRemote =
                (BDL_C_SFMainRemote) context.lookup("map-BDL_C_SFMain#sped.negocio.BDL.IR.BDL_C_SFMainRemote");
       /*     BeanMain beanMain=new BeanMain();
            beanMain.setNidMain(1);
            List<Main> lista=bDL_C_SFMainRemote.findHorariosByAttributes(beanMain);
            Iterator it=lista.iterator();
            while(it.hasNext()){
                Main entida= (Main)it.next();
                System.out.println(entida.getProfesor().getApellidos()+" "+entida.getCurso().getDescripcionCurso());
            }*/
           List <Main> hola=bDL_C_SFMainRemote.getMainFindAll(); 
          Iterator it=hola.iterator();
            while(it.hasNext()){
                Main entida = (Main)it.next();
                System.out.println("HORA INICIO "+entida.getHoraInicio()+" - HORA FIN "+entida.getHoraFin());
            }
                        
        } catch (CommunicationException ex) {
            System.out.println(ex.getClass().getName());
            System.out.println(ex.getRootCause().getLocalizedMessage());
            System.out.println("\n*** A CommunicationException was raised.  This typically\n*** occurs when the target WebLogic server is not running.\n");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void printMain(Main main) {
        System.out.println("dia = " + main.getDia());
        System.out.println("estado = " + main.getEstado());
        System.out.println("horaFin = " + main.getHoraFin());
        System.out.println("horaInicio = " + main.getHoraInicio());
        System.out.println("nidMain = " + main.getNidMain());
        System.out.println("evaluacionLista = " + main.getEvaluacionLista());
        System.out.println("aula = " + main.getAula());
        System.out.println("curso = " + main.getCurso());
        System.out.println("profesor = " + main.getProfesor());
    }

    private static Context getInitialContext() throws NamingException {
        Hashtable env = new Hashtable();
        // WebLogic Server 10.x/12.x connection details
        env.put(Context.INITIAL_CONTEXT_FACTORY, "weblogic.jndi.WLInitialContextFactory");
        env.put(Context.PROVIDER_URL, "t3://127.0.0.1:7101");
        return new InitialContext(env);
    }
}
