package sped.negocio.LNSF.IL;

import java.util.List;

import javax.ejb.Local;

import sped.negocio.entidades.beans.BeanComboString;
import sped.negocio.entidades.beans.BeanProfesor;

@Local
public interface LN_C_SFProfesorLocal {
    public List<BeanComboString> getProfesoresLN();
    BeanProfesor findConstrainByDni(String dni);
    List<BeanComboString> getProfesoresLN_PorSede_ByOrden(Object nidSede, Object nidArea, Object nidCurso, Object nidNivel, Object nidGrado);
    boolean exiteDni_LN(String dni);
    List getNombreProfesor_LN();
}