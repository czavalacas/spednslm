package sped.negocio.LNSF.IR;

import java.util.List;

import javax.ejb.Remote;

import sped.negocio.entidades.beans.BeanFicha;

@Remote
public interface LN_C_SFFichaRemote {
    List<BeanFicha> getLstFichasByAttr_LN();
    String getNextVersionFichaByAttr_LN(int year,
                                        int mes,
                                        String tipFicha,
                                        String tipFichaCurso);
}
