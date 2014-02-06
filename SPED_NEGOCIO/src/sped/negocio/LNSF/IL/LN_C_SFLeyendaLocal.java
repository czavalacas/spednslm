package sped.negocio.LNSF.IL;

import javax.ejb.Local;

import sped.negocio.entidades.beans.BeanLeyenda;
import sped.negocio.entidades.eval.CriterioIndicador;

@Local
public interface LN_C_SFLeyendaLocal {
    BeanLeyenda getLeyendabyEvaluacion(CriterioIndicador cri,int nidFicha);
}
