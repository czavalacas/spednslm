package sped.negocio.BDL.IL;

import javax.ejb.Local;

import sped.negocio.entidades.sist.Imagen;

@Local
public interface BDL_C_SFImagenLocal {
    int countImagen();
    Imagen findConstrainById(int id);
}
