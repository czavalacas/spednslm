package sped.negocio.LNSF.IL;

import java.util.List;

import javax.ejb.Local;

import sped.negocio.entidades.beans.BeanSedeNivel;

@Local
public interface LN_C_SFSedeNivelLocal {
    List<BeanSedeNivel> getSedeNivelbyNidSedeLN(int nidSede);
}
