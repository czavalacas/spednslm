package test.testing;

import java.sql.Timestamp;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;

import javax.naming.CommunicationException;
import javax.naming.Context;
import javax.naming.InitialContext;

import javax.naming.NamingException;

import test._SFPrueba.SFTestRemoto;

import test.entidades.Evmeval;

public class SFTestRemotoClient {
    public static void main(String[] args) {
        try {
            final Context context = getInitialContext();
            SFTestRemoto sFTestRemoto = (SFTestRemoto)context.lookup("mapSFTest#test._SFPrueba.SFTestRemoto");
            /*for (Evmeval evmeval : (List<Evmeval>)sFTestRemoto.getEvmevalFindAll()) {
                printEvmeval(evmeval);
            }*/
            Evmeval evmeval = new Evmeval();
            evmeval.setEstado_evaluacion("1");
            evmeval.setFecha_hora(toTimestamp(fechaHoy()));
            evmeval.setNidMain(1);
            sFTestRemoto.persistEvmeval(evmeval);
            System.out.println("registro!!");
        } catch (CommunicationException ex) {
            System.out.println(ex.getClass().getName());
            System.out.println(ex.getRootCause().getLocalizedMessage());
            System.out.println("\n*** A CommunicationException was raised.  This typically\n*** occurs when the target WebLogic server is not running.\n");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static Timestamp toTimestamp(String fecha) {
        try {
            DateFormat formatter;
            Date date;
            formatter = new SimpleDateFormat("dd-MM-yy");
            date = formatter.parse(fecha);
            Timestamp fechaTS = new Timestamp(date.getTime());
            return fechaTS;
        } catch (ParseException e) {
            System.out.println(e);
            return null;
        }
    }

    public static final String DATE_FORMAT_NOW = "dd-MM-yy";

    public static String fechaHoy() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
        return sdf.format(cal.getTime());
    }

    private static void printEvmeval(Evmeval evmeval) {
        System.out.println("estado_evaluacion = " + evmeval.getEstado_evaluacion());
        System.out.println("fecha_hora = " + evmeval.getFecha_hora());
        System.out.println("nidEvaluacion = " + evmeval.getNidEvaluacion());
        System.out.println("nidMain = " + evmeval.getNidMain());
    }

    private static Context getInitialContext() throws NamingException {
        Hashtable env = new Hashtable();
        // WebLogic Server 10.x connection details
        env.put(Context.INITIAL_CONTEXT_FACTORY, "weblogic.jndi.WLInitialContextFactory");
        env.put(Context.PROVIDER_URL, "t3://127.0.0.1:7101");
        return new InitialContext(env);
    }
}
