package sped.negocio.Pruebas;

import java.util.Hashtable;

import java.util.List;

import javax.naming.CommunicationException;
import javax.naming.Context;
import javax.naming.InitialContext;

import javax.naming.NamingException;

import sped.negocio.LNSF.IR.LN_C_SFUsuarioRemote;
import sped.negocio.entidades.admin.Usuario;
import sped.negocio.entidades.beans.BeanUsuario;

public class LN_C_SFUsuarioRemoteClient {
    public static void main(String[] args) {
        try {
            final Context context = getInitialContext();
            LN_C_SFUsuarioRemote lN_C_SFUsuarioRemote =
                (LN_C_SFUsuarioRemote) context.lookup("mapLN_C_SFUsuario#sped.negocio.LNSF.IR.LN_C_SFUsuarioRemote");
            for (BeanUsuario usuario : (List<BeanUsuario>) lN_C_SFUsuarioRemote.getEvaluadores("2")) {
                System.out.println(usuario.getNombres()+" - "+usuario.getAreaAcademica().getDescripcionAreaAcademica());
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
