package sped.negocio.LNSF.IR;

import java.util.List;

import javax.ejb.Remote;

import sped.negocio.entidades.beans.BeanLeccion;

@Remote
public interface LN_C_SFLeccionRemote {
    List<BeanLeccion> getLeccionesbyCodigoVista(boolean vista, String codigo, int nidSede, int nidNivel);
}
