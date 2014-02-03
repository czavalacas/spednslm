package sped.negocio.LNSF.IR;

import java.util.List;

import javax.ejb.Remote;

import sped.negocio.entidades.beans.BeanResultado;
import sped.negocio.entidades.eval.Resultado;

@Remote
public interface LN_C_SFResultadoRemote {
    boolean fichaUsadaEnEvaluacion_LN(int nidFicha);
    List<BeanResultado> transformLstResultado(List<Resultado> lstResultado);
}
