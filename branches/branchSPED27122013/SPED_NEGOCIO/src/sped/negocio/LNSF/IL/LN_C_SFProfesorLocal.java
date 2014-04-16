package sped.negocio.LNSF.IL;

import java.util.List;

import javax.ejb.Local;

import sped.negocio.entidades.beans.BeanComboString;
import sped.negocio.entidades.beans.BeanProfesor;

@Local
public interface LN_C_SFProfesorLocal {
    List<BeanComboString> getProfesoresLN();
    List<BeanProfesor> getProfesoresLN2();
    BeanProfesor findConstrainByDni(String dni);
    List<BeanComboString> getProfesoresLN_PorSede_ByOrden(Object nidSede, Object nidArea, Object nidCurso, Object nidNivel, Object nidGrado);
    boolean exiteDni_LN(String dni);
    List getNombreProfesor_LN();
    String getDniProfesorPorNombreCompleto(String nombreCompleto);
    List<BeanComboString> getPRofesorPorSedeYNivel(String nidSede, String nidNivel, int nidAreaAcademica);
}
