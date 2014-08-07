package sped.negocio.LNSF.IL;

import javax.ejb.Local;

@Local
public interface LN_C_SFValorLocal {
    String getRangoValorByFicha(int nidFicha);
    String getValoresPosiblesByCriterio(int nidCriterio,int nidFicha);
}
