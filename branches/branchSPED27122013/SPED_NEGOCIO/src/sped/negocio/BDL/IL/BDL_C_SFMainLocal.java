package sped.negocio.BDL.IL;

import java.util.List;

import javax.ejb.Local;

import sped.negocio.entidades.admin.Main;
import sped.negocio.entidades.beans.BeanMain;

@Local
public interface BDL_C_SFMainLocal {
    List<Main> getMainFindAll();
    List<Main> findHorariosByAttributes(BeanMain beanMain);
}
