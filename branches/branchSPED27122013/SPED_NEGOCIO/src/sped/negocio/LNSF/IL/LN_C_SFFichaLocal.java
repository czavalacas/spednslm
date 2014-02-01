package sped.negocio.LNSF.IL;

import java.util.List;

import javax.ejb.Local;

import sped.negocio.entidades.beans.BeanFicha;

@Local
public interface LN_C_SFFichaLocal {
    List<BeanFicha> getLstFichasByAttr_LN();
    String getNextVersionFichaByAttr_LN(int year,
                                        int mes,
                                        String tipFicha,
                                        String tipFichaCurso);
    BeanFicha checkSiSePuedeActivar(String tipFicha,
                                    String tipCursoFicha);
}
