package sped.negocio.LNSF.IR;

import java.util.List;

import javax.ejb.Remote;

import sped.negocio.entidades.beans.BeanMain;
import sped.negocio.entidades.beans.BeanProfesor;

@Remote
public interface LN_C_SFMainRemote {
    List<BeanMain> llenarHorario(BeanMain beanMain);
    List<BeanProfesor> findProfesoresPorAreaAcademica_LN(Integer nidAreaAcademica, String dia);
}
