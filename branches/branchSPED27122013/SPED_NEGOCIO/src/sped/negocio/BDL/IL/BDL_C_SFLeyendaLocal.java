package sped.negocio.BDL.IL;

import javax.ejb.Local;

import sped.negocio.entidades.eval.CriterioIndicador;
import sped.negocio.entidades.eval.Leyenda;

@Local
public interface BDL_C_SFLeyendaLocal {
    Leyenda getLeyendabyEvaluacion(CriterioIndicador ci,
                                   int nidFicha);
}
