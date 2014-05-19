package sped.negocio.LNSF.IR;

import java.util.List;

import javax.ejb.Remote;

import sped.negocio.entidades.beans.BeanCurso;

@Remote
public interface LN_T_SFCursoRemoto {
    String grabarCursosNuevos(List<BeanCurso> listaCursos);
    String grabarCurso(BeanCurso curso);
    void modificarColor(int nidCurso, String color);
}
