package sped.negocio.LNSF.IR;

import java.util.List;

import javax.ejb.Remote;

import sped.negocio.entidades.beans.BeanAula;
import sped.negocio.entidades.beans.BeanCombo;

@Remote
public interface LN_C_SFAulaRemote {
    int getAulaByDescripcion_LN(int nidSede, 
                                int nidNivel, 
                                String descripcion);
    List<BeanAula> getAreaAulaLN();
    List<BeanCombo> getAulaPorSedeNivelYGrado(String nidSede, String nidGrado, String nidNivel);
    List<BeanCombo> getAulaPorSedeNivelProfesorYCurso(String nidSede, String nidNivel, String dniProfesor, int nidAreaAcademica, String nidCurso);
    List<BeanAula> getAulasBySedeGradoYNivelMigracion(String nidSede, String nidGrado, String nidNivel);
}
