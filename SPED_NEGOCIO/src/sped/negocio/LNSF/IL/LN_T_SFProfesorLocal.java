package sped.negocio.LNSF.IL;

import java.util.List;

import javax.ejb.Local;

import sped.negocio.entidades.beans.BeanProfesor;

@Local
public interface LN_T_SFProfesorLocal {
    String grabarProfesoresNuevos(List<BeanProfesor> listaProfesores);
    String grabarProfesor(BeanProfesor profesor);
    String grabarColorProfesor(String dni, String color);
}
