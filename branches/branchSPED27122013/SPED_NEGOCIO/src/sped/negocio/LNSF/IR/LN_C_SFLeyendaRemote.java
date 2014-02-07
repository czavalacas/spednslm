package sped.negocio.LNSF.IR;

import javax.ejb.Remote;

import sped.negocio.entidades.beans.BeanLeyenda;
import sped.negocio.entidades.eval.CriterioIndicador;

@Remote
public interface LN_C_SFLeyendaRemote {
    BeanLeyenda getLeyendabyEvaluacion(CriterioIndicador cri,
                                       int nidFicha, 
                                       int valor);
}
