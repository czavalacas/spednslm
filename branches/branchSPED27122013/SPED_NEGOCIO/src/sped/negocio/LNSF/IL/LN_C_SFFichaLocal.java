package sped.negocio.LNSF.IL;

import java.util.List;

import javax.ejb.Local;

import sped.negocio.entidades.beans.BeanFicha;

@Local
public interface LN_C_SFFichaLocal {
    List<BeanFicha> getLstFichasByAttr_LN();
}
