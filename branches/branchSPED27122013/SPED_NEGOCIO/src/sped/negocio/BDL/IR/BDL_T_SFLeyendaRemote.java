package sped.negocio.BDL.IR;

import javax.ejb.Remote;

import sped.negocio.entidades.eval.Leyenda;

@Remote
public interface BDL_T_SFLeyendaRemote {
    Leyenda persistLeyenda(Leyenda leyenda);

    Leyenda mergeLeyenda(Leyenda leyenda);

    void removeLeyenda(Leyenda leyenda);
}
