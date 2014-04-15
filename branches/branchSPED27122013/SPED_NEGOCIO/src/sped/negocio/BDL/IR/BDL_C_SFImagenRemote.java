package sped.negocio.BDL.IR;

import javax.ejb.Remote;

import sped.negocio.entidades.sist.Imagen;

@Remote
public interface BDL_C_SFImagenRemote {
    int countImagen();
    Imagen findConstrainById(int id);
}
