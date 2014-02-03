package sped.negocio.LNSF.IR;

import java.util.List;

import javax.ejb.Remote;

import sped.negocio.entidades.beans.BeanCriterioIndicador;
import sped.negocio.entidades.eval.CriterioIndicador;

@Remote
public interface LN_C_SFCriterioIndicadorRemote {
    List<BeanCriterioIndicador> transformLstCriterioIndicador(List<CriterioIndicador> lstCrIn);
}
