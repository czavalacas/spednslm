package sped.negocio.BDL.IR;

import javax.ejb.Remote;

import sped.negocio.entidades.eval.Ficha;

@Remote
public interface BDL_T_SFFichaRemote {
    Ficha persistFicha(Ficha ficha);

    Ficha mergeFicha(Ficha ficha);

    void removeFicha(Ficha ficha);
    void reactivarFichaYDesactivarElResto(String tipFicha,
                                          String tipCursoFicha,
                                          int nidFicha);
}
