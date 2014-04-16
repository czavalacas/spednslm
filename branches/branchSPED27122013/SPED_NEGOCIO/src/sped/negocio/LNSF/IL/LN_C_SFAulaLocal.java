package sped.negocio.LNSF.IL;

import java.util.List;

import javax.ejb.Local;

import sped.negocio.entidades.beans.BeanAula;
import sped.negocio.entidades.beans.BeanCombo;

@Local
public interface LN_C_SFAulaLocal {
    int getAulaByDescripcion_LN(int nidSede, 
                                int nidNivel, 
                                String descripcion);
    List<BeanAula> getAreaAulaLN();
    List<BeanCombo> getAulaPorSedeNivelYGrado(String nidSede, String nidGrado, String nidNivel);
    List<BeanCombo> getAulaPorSedeNivelProfesorYCurso(String nidSede, String nidNivel, String dniProfesor, int nidAreaAcademica, String nidCurso);
}
