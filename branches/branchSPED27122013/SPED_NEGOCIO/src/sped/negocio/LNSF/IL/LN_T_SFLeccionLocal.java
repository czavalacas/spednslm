package sped.negocio.LNSF.IL;

import java.util.List;

import javax.ejb.Local;

import sped.negocio.entidades.beans.BeanLeccion;

@Local
public interface LN_T_SFLeccionLocal {
    List<BeanLeccion> gestionarLecciones(boolean valida, List<BeanLeccion> lst);
    void eliminarLeccion(BeanLeccion lec);
    void eliminarLecciones(List<BeanLeccion> lst);
}
