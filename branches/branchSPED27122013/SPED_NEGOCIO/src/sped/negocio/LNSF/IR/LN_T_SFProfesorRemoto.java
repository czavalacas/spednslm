package sped.negocio.LNSF.IR;

import java.util.List;

import javax.ejb.Remote;

import sped.negocio.entidades.beans.BeanProfesor;

@Remote
public interface LN_T_SFProfesorRemoto {
    String grabarProfesoresNuevos(List<BeanProfesor> listaProfesores);
    String grabarProfesor(BeanProfesor profesor);
    String grabarColorProfesor(String dni, String color);
}
