package sped.negocio.LNSF.IR;

import java.util.List;

import javax.ejb.Remote;

import sped.negocio.entidades.beans.BeanProblema;

@Remote
public interface LN_C_SFProblemaRemote {
    List<BeanProblema> getLstProblemaAllLN();
    boolean existeProblema(String descripcion);
}
