package test.negocio.testing;

import java.util.Date;
import java.util.Hashtable;
import java.util.List;

import javax.naming.CommunicationException;
import javax.naming.Context;
import javax.naming.InitialContext;

import javax.naming.NamingException;

import test.negocio.BDL.IR.BDL_C_SFActorRemote;
import test.negocio.entidades.Actor;

public class BDL_C_SFActorRemoteClient {
    public static void main(String[] args) {
        try {
            final Context context = getInitialContext();
            BDL_C_SFActorRemote bDL_C_SFActorRemote = (BDL_C_SFActorRemote) context.lookup("mapBDL_C_SFActor#test.negocio.BDL.IR.BDL_C_SFActorRemote");
         /*   for (Actor actor : (List<Actor>) bDL_C_SFActorRemote.getActoresByNombre("SA")) {
                printActor(actor);
            }*/
         short id = 203;
         bDL_C_SFActorRemote.registrarActor(3,"JUAN LEONARDO","PEREZ QUISPE",new Date(),id);
        } catch (CommunicationException ex) {
            System.out.println(ex.getClass().getName());
            System.out.println(ex.getRootCause().getLocalizedMessage());
            System.out.println("\n*** A CommunicationException was raised.  This typically\n*** occurs when the target WebLogic server is not running.\n");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void printActor(Actor actor) {
        /*System.out.println("actor_id = " + actor.getActor_id());
        System.out.println("first_name = " + actor.getFirst_name());
        System.out.println("last_name = " + actor.getLast_name());
        System.out.println("last_update = " + actor.getLast_update());
        System.out.println("filmActorList = " + actor.getFilmActorList());*/
        System.out.println("ACTOR> "+actor.toString());
    }

    private static Context getInitialContext() throws NamingException {
        Hashtable env = new Hashtable();
        // WebLogic Server 10.x/12.x connection details
        env.put(Context.INITIAL_CONTEXT_FACTORY, "weblogic.jndi.WLInitialContextFactory");
        env.put(Context.PROVIDER_URL, "t3://127.0.0.1:7101");
        return new InitialContext(env);
    }
}
