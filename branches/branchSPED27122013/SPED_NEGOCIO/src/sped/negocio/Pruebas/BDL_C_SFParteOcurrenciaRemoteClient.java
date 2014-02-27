package sped.negocio.Pruebas;

import java.util.Date;
import java.util.Hashtable;
import java.util.List;

import javax.naming.CommunicationException;
import javax.naming.Context;
import javax.naming.InitialContext;

import javax.naming.NamingException;

import sped.negocio.BDL.IR.BDL_C_SFParteOcurrenciaRemote;
import sped.negocio.entidades.admin.ParteOcurrencia;
import sped.negocio.entidades.beans.BeanParteOcurrencia;

public class BDL_C_SFParteOcurrenciaRemoteClient {
    public static void main(String[] args) {
        try {
            final Context context = getInitialContext();
            BDL_C_SFParteOcurrenciaRemote bDL_C_SFParteOcurrenciaRemote = (BDL_C_SFParteOcurrenciaRemote) context.lookup("mapBDL_C_SFParteOcurrencia#sped.negocio.BDL.IR.BDL_C_SFParteOcurrenciaRemote");
            Date fechaMin = null;
            Date fechaMax = null;
            Integer nidProblema = null;
            String nombreProfesor = null;
            Integer nidSede = null;
            Integer nidUsuario = null;
            for (BeanParteOcurrencia parteocurrencia : (List<BeanParteOcurrencia>) bDL_C_SFParteOcurrenciaRemote.getListaPartesOcurrencia_BDL(fechaMin,fechaMax,nidProblema,nombreProfesor,nidSede,nidUsuario)) {
                printParteOcurrencia(parteocurrencia);
            }
        } catch (CommunicationException ex) {
            System.out.println(ex.getClass().getName());
            System.out.println(ex.getRootCause().getLocalizedMessage());
            System.out.println("\n*** A CommunicationException was raised.  This typically\n*** occurs when the target WebLogic server is not running.\n");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void printParteOcurrencia(BeanParteOcurrencia parteocurrencia) {
        System.out.println("comentario = " + parteocurrencia.getComentario());
        System.out.println("fechaRegistro = " + parteocurrencia.getFechaRegistro());
        System.out.println("nidMain = " + parteocurrencia.getNidMain());
        System.out.println("nidParte = " + parteocurrencia.getNidParte());
        System.out.println("nidProblema = " + parteocurrencia.getNidProblema());
        System.out.println("nidUsuario = " + parteocurrencia.getNidUsuario());
        System.out.println("getProfesor = " + parteocurrencia.getProfesor());
        System.out.println("getSede = " + parteocurrencia.getSede());
        System.out.println("getDescProblema = " + parteocurrencia.getDescProblema());
        System.out.println("getCurso = " + parteocurrencia.getCurso());
    }

    private static Context getInitialContext() throws NamingException {
        Hashtable env = new Hashtable();
        // WebLogic Server 10.x/12.x connection details
        env.put(Context.INITIAL_CONTEXT_FACTORY, "weblogic.jndi.WLInitialContextFactory");
        env.put(Context.PROVIDER_URL, "t3://127.0.0.1:7101");
        return new InitialContext(env);
    }
}
