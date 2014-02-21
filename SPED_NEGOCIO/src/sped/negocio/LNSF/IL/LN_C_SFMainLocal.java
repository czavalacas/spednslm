package sped.negocio.LNSF.IL;

import java.util.List;

import javax.ejb.Local;

import sped.negocio.entidades.beans.BeanMain;
import sped.negocio.entidades.beans.BeanProfesor;

@Local
public interface LN_C_SFMainLocal {
    List<BeanMain> llenarHorario(BeanMain beanMain);
    List<BeanProfesor> findProfesoresPorAreaAcademica_LN(Integer nidAreaAcademica, String dia);
}
