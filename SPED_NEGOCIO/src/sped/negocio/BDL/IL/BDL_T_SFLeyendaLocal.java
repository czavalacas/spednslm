package sped.negocio.BDL.IL;

import javax.ejb.Local;

import sped.negocio.entidades.eval.Leyenda;

@Local
public interface BDL_T_SFLeyendaLocal {
    Leyenda persistLeyenda(Leyenda leyenda);

    Leyenda mergeLeyenda(Leyenda leyenda);

    void removeLeyenda(Leyenda leyenda);
}
