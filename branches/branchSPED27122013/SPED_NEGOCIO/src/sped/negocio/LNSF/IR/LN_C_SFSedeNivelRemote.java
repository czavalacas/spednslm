package sped.negocio.LNSF.IR;

import java.util.List;

import javax.ejb.Remote;

import sped.negocio.entidades.beans.BeanSedeNivel;

@Remote
public interface LN_C_SFSedeNivelRemote {
    List<BeanSedeNivel> getSedeNivelbyNidSedeLN(int nidSede);
}
