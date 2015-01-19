package sped.negocio.BDL.IL;

import java.util.List;

import javax.ejb.Local;

import sped.negocio.entidades.admin.Aula;
import sped.negocio.entidades.admin.Profesor;
import sped.negocio.entidades.sist.Leccion;

@Local
public interface BDL_C_SFLeccionLocal {
    Leccion findLeccionById(int nidLeccion);
    List<Leccion> getLeccionesbyAula(Aula nidAula);
    List<Leccion> getLeccionesbyDNI(Profesor nidDni, int nidSede, int nidNivel);
}
