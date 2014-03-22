package sped.negocio.LNSF.IL;

import java.util.List;

import javax.ejb.Local;

import sped.negocio.entidades.beans.BeanAreaAcademica;

@Local
public interface LN_T_SFAreaAcademicaLocal {
    String grabarAreasNuevas(List<BeanAreaAcademica> listaAreas);
    String grabarArea(BeanAreaAcademica areas);
}
