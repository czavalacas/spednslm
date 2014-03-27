package sped.negocio.LNSF.IL;

import java.util.List;

import javax.ejb.Local;

import sped.negocio.entidades.beans.BeanAreaAcademica;
import sped.negocio.entidades.beans.BeanCombo;

@Local
public interface LN_C_SFAreaAcademicaLocal {
    List<BeanAreaAcademica> getAreaAcademicaLN();
    BeanAreaAcademica findConstrainByIdLN(int id);
    List<BeanCombo> getAreaAcademicaLNPorSede_byOrden(String nidSede);
}
