package sped.negocio.LNSF.IL;

import java.util.List;

import javax.ejb.Local;

import sped.negocio.entidades.beans.BeanLeccion;

@Local
public interface LN_C_SFLeccionLocal {
    List<BeanLeccion> getLeccionesbyCodigoVista(boolean vista, String codigo, int nidSede, int nidNivel);
}
