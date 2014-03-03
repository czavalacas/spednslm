package sped.negocio.Pruebas;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Hashtable;

import java.util.List;

import javax.naming.CommunicationException;
import javax.naming.Context;
import javax.naming.InitialContext;

import javax.naming.NamingException;

import sped.negocio.LNSF.IR.LN_C_SFParteOcurrenciaRenote;
import sped.negocio.entidades.beans.BeanParteOcurrencia;

public class LN_C_SFParteOcurrenciaRenoteClient {
    public static void main(String[] args) {
        try {
            final Context context = getInitialContext();
            LN_C_SFParteOcurrenciaRenote lN_C_SFParteOcurrenciaRenote = (LN_C_SFParteOcurrenciaRenote) context.lookup("mapLN_C_SFParteOcurrencia#sped.negocio.LNSF.IR.LN_C_SFParteOcurrenciaRenote");
            Calendar calendar = new GregorianCalendar(2014,1,1,0,0,0);
            Calendar calendar2 = new GregorianCalendar(2014,2,1,0,0,0);
            System.out.println("calendar:"+calendar.getTime());
            List<BeanParteOcurrencia> pos = lN_C_SFParteOcurrenciaRenote.getListaPartesOcurrencia_LN(calendar.getTime(),calendar2.getTime(), null, null,null,null);
            int i = 1;
            for(BeanParteOcurrencia po : pos){
                System.out.println("prob: "+po.getDescProblema());
                if(pos.size() == i){System.out.println("last:"+pos.get(i-1).getLstPies());
                    for(int j = 0; j < pos.get(i-1).getLstPies().length;j++){System.out.println("2222222");
                        System.out.println("pie:"+pos.get(i-1).getLstPies()[j]);
                    }
                }
                i++;
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
