package sped.negocio.BDL.IR;

import java.util.List;

import javax.ejb.Remote;

import sped.negocio.entidades.admin.Main;
import sped.negocio.entidades.admin.Profesor;
import sped.negocio.entidades.beans.BeanMain;

@Remote
public interface BDL_C_SFMainRemote {
    List<Main> getMainFindAll();
    List<Main> findHorariosByAttributes(BeanMain beanMain);
    List<Profesor> findProfesoresPorAreaAcademica(Integer nidAreaAcademica, String dia);
}
