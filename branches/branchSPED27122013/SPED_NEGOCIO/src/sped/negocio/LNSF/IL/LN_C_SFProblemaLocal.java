package sped.negocio.LNSF.IL;

import java.util.List;

import javax.ejb.Local;

import sped.negocio.entidades.beans.BeanProblema;

@Local
public interface LN_C_SFProblemaLocal {
    List<BeanProblema> getLstProblemaAllLN();
    boolean existeProblema(String descripcion);
}
