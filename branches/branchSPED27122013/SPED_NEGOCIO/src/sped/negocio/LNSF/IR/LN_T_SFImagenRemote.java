package sped.negocio.LNSF.IR;

import javax.ejb.Remote;

@Remote
public interface LN_T_SFImagenRemote {
    void guardarImagen(String rutaImg);
}
