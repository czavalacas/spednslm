package sped.negocio.BDL.IR;

import javax.ejb.Remote;

import sped.negocio.entidades.sist.Leccion;

@Remote
public interface BDL_T_SFLeccionBeanRemote {
    Leccion persistLecciones(Leccion lecciones);

    Leccion mergeLecciones(Leccion lecciones);

    void removeLecciones(Leccion lecciones);
}
