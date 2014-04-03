package sped.negocio.BDL.IL;

import java.util.List;

import javax.ejb.Local;

import sped.negocio.entidades.admin.Aula;
import sped.negocio.entidades.beans.BeanAula;

@Local
public interface BDL_C_SFAulaLocal {
    List<Aula> getAulaFindAll();
    int getAulaByDescripcion(BeanAula beanAula);
    List<Aula> getAulaPorSedeNivelYGrado(String nidSede, String nidGrado, String nidNivel);
}
