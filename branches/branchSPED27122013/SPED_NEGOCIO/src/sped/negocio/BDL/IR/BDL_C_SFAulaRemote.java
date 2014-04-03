package sped.negocio.BDL.IR;

import java.util.List;

import javax.ejb.Remote;

import sped.negocio.entidades.admin.Aula;
import sped.negocio.entidades.beans.BeanAula;

@Remote
public interface BDL_C_SFAulaRemote {
    List<Aula> getAulaFindAll();
    int getAulaByDescripcion(BeanAula beanAula);
    List<Aula> getAulaPorSedeNivelYGrado(String nidSede, String nidGrado, String nidNivel);
}
