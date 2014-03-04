package sped.negocio.LNSF.IR;

import javax.ejb.Remote;

@Remote
public interface LN_C_SFCorreoRemote {
    String enviarCorreo(String data[]);
    void enviarCorreoHTML(String data[]);
}
