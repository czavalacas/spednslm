package sped.negocio.BDL.IR;

import java.util.List;

import javax.ejb.Remote;

import sped.negocio.entidades.admin.Aula;
import sped.negocio.entidades.admin.Profesor;
import sped.negocio.entidades.sist.Leccion;

@Remote
public interface BDL_C_SFLeccionRemote {
    Leccion findLeccionById(int nidLeccion);
    List<Leccion> getLeccionesbyAula(Aula nidAula);
    List<Leccion> getLeccionesbyDNI(Profesor nidDni, int nidSede, int nidNivel);
}
