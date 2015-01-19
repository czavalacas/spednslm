package sped.negocio.BDL.IL;

import javax.ejb.Local;

import sped.negocio.entidades.sist.Leccion;

@Local
public interface BDL_T_SFLeccionBeanLocal {
    Leccion persistLecciones(Leccion lecciones);

    Leccion mergeLecciones(Leccion lecciones);

    void removeLecciones(Leccion lecciones);
}
