package sped.negocio.LNSF.IL;

import javax.ejb.Local;

@Local
public interface LN_C_SFCorreoLocal {
    String enviarCorreo(String data[]);
    void enviarCorreoHTML(String data[]);
}
