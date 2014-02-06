package sped.negocio.BDL.IR;

import javax.ejb.Remote;

import sped.negocio.entidades.eval.CriterioIndicador;
import sped.negocio.entidades.eval.Leyenda;

@Remote
public interface BDL_C_SFLeyendaRemote {
    Leyenda getLeyendabyEvaluacion(CriterioIndicador ci,
                                   int nidFicha);
}
