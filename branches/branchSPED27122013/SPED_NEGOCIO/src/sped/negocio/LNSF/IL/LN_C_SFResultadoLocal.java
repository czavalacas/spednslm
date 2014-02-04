package sped.negocio.LNSF.IL;

import java.util.List;

import javax.ejb.Local;

import sped.negocio.entidades.beans.BeanResultado;
import sped.negocio.entidades.eval.Resultado;

@Local
public interface LN_C_SFResultadoLocal {
    boolean fichaUsadaEnEvaluacion_LN(int nidFicha);
    BeanResultado findResultadoByIdLN(int criterioIndicador,
                                      int evaluacion);
}
