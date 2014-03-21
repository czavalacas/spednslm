package sped.negocio.LNSF.IR;

import java.util.List;

import javax.ejb.Remote;

import sped.negocio.entidades.beans.BeanProfesor;

@Remote
public interface LN_C_SFProfesorRemote {
    public List<BeanProfesor> getProfesoresLN();
    BeanProfesor findConstrainByDni(String dni);
    List<BeanProfesor> getProfesoresLN_PorSede_ByOrden(Object nidSede, Object nidArea, Object nidCurso, Object nidNivel, Object nidGrado);
    boolean exiteDni_LN(String dni);
}
