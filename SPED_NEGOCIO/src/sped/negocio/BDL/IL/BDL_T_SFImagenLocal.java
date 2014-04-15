package sped.negocio.BDL.IL;

import javax.ejb.Local;

import sped.negocio.entidades.sist.Imagen;

@Local
public interface BDL_T_SFImagenLocal {
    Imagen persistImagen(Imagen imagen);

    Imagen mergeImagen(Imagen imagen);
}
