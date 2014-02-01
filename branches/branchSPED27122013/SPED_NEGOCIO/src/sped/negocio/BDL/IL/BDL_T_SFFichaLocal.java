package sped.negocio.BDL.IL;

import javax.ejb.Local;

import sped.negocio.entidades.eval.Ficha;

@Local
public interface BDL_T_SFFichaLocal {
    Ficha persistFicha(Ficha ficha);

    Ficha mergeFicha(Ficha ficha);

    void removeFicha(Ficha ficha);
    void reactivarFichaYDesactivarElResto(String tipFicha,
                                          String tipCursoFicha,
                                          int nidFicha);
}
