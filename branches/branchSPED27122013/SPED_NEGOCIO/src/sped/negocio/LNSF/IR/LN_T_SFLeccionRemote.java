package sped.negocio.LNSF.IR;

import java.util.List;

import javax.ejb.Remote;

import sped.negocio.entidades.beans.BeanLeccion;

@Remote
public interface LN_T_SFLeccionRemote {
    List<BeanLeccion> gestionarLecciones(boolean valida, List<BeanLeccion> lst);
    BeanLeccion gestionarLeccion(boolean valida, BeanLeccion l);
    void eliminarLeccion(BeanLeccion lec);
    void eliminarLecciones(List<BeanLeccion> lst);
    void removeLeccion(int nidLeccion);
}
