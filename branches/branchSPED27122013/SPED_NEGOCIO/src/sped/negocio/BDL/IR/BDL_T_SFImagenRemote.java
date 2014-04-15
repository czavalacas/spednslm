package sped.negocio.BDL.IR;

import javax.ejb.Remote;

import sped.negocio.entidades.sist.Imagen;

@Remote
public interface BDL_T_SFImagenRemote {
    Imagen persistImagen(Imagen imagen);

    Imagen mergeImagen(Imagen imagen);
}
