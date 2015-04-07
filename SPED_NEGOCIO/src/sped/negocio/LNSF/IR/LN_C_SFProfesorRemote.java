package sped.negocio.LNSF.IR;

import java.util.List;

import javax.ejb.Remote;

import sped.negocio.entidades.admin.Profesor;
import sped.negocio.entidades.beans.BeanComboString;
import sped.negocio.entidades.beans.BeanProfesor;

@Remote
public interface LN_C_SFProfesorRemote {
    List<BeanComboString> getProfesoresLN();
    List<BeanProfesor> getProfesoresLN2();
    BeanProfesor findConstrainByDni(String dni);
    List<BeanComboString> getProfesoresLN_PorSede_ByOrden(Object nidSede, Object nidArea, Object nidCurso, Object nidNivel, Object nidGrado);
    boolean exiteDni_LN(String dni);
    List getNombreProfesor_LN();
    String getDniProfesorPorNombreCompleto(String nombreCompleto);
    List<BeanComboString> getPRofesorPorSedeYNivel(String nidSede, String nidNivel, int nidAreaAcademica);
    List<BeanProfesor> getProfesoresDistintoLista(List<String> lst_dni, BeanProfesor profesor);
    String colorProfesor(String dni);
    Profesor getProfesorByDNI(String dni);
}
