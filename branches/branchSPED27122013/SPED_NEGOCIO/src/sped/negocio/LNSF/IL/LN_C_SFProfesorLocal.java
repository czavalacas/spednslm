package sped.negocio.LNSF.IL;

import java.util.List;

import javax.ejb.Local;

import sped.negocio.entidades.beans.BeanProfesor;

@Local
public interface LN_C_SFProfesorLocal {
    public List<BeanProfesor> getProfesoresLN();
    BeanProfesor findConstrainByDni(String dni);
}
