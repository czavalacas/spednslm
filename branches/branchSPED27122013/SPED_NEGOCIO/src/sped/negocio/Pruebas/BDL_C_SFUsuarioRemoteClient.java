package sped.negocio.Pruebas;

import java.util.Hashtable;
import java.util.List;

import javax.naming.CommunicationException;
import javax.naming.Context;
import javax.naming.InitialContext;

import javax.naming.NamingException;

import sped.negocio.BDL.IR.BDL_C_SFUsuarioRemote;
import sped.negocio.entidades.admin.Usuario;

public class BDL_C_SFUsuarioRemoteClient {
    public static void main(String[] args) {
        try {
            final Context context = getInitialContext();
            BDL_C_SFUsuarioRemote bDL_C_SFUsuarioRemote =
                (BDL_C_SFUsuarioRemote) context.lookup("mapBDL_C_SFUsuario#sped.negocio.BDL.IR.BDL_C_SFUsuarioRemote");
            for (Usuario usuario : (List<Usuario>) bDL_C_SFUsuarioRemote.getUsuarioFindAll()) {
                printUsuario(usuario);
            }
        } catch (CommunicationException ex) {
            System.out.println(ex.getClass().getName());
            System.out.println(ex.getRootCause().getLocalizedMessage());
            System.out.println("\n*** A CommunicationException was raised.  This typically\n*** occurs when the target WebLogic server is not running.\n");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void printUsuario(Usuario usuario) {
        System.out.println("clave         = " + usuario.getClave());
        System.out.println("dni           = " + usuario.getDni());
        System.out.println("estadoUsuario = " + usuario.getEstadoUsuario());
        System.out.println("foto          = " + usuario.getFoto());
        System.out.println("nidUsuario    = " + usuario.getNidUsuario());
        System.out.println("nombres       = " + usuario.getNombres());
        System.out.println("usuario       = " + usuario.getUsuario());
        System.out.println("rol           = " + usuario.getRol().getDescripcionRol());
        System.out.println("Nivel         = " + usuario.getSedeNivel().getNivel().getDescripcionNivel());
        System.out.println("Sede          = " + usuario.getSedeNivel().getSede().getDescripcionSede());
        System.out.println("areaAcademica = " + usuario.getAreaAcademica().getDescripcionAreaAcademica());
    }

    private static Context getInitialContext() throws NamingException {
        Hashtable env = new Hashtable();
        // WebLogic Server 10.x/12.x connection details
        env.put(Context.INITIAL_CONTEXT_FACTORY, "weblogic.jndi.WLInitialContextFactory");
        env.put(Context.PROVIDER_URL, "t3://127.0.0.1:7101");
        return new InitialContext(env);
    }
}
