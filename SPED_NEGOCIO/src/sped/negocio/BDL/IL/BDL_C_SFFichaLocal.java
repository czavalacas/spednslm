package sped.negocio.BDL.IL;

import java.util.List;

import javax.ejb.Local;

import sped.negocio.entidades.eval.Ficha;

@Local
public interface BDL_C_SFFichaLocal {
    List<Ficha> getFichaFindAll();
    List<Ficha> getFichaByAttr_BDL();
    Object[] getLastestFichaVersionByAttr_BDL(int year,
                                            int mes,
                                            String tipFicha,
                                            String tipFichaCurso);
}
