package sped.negocio.LNSF.IR;

import java.util.List;

import javax.ejb.Remote;

import sped.negocio.entidades.beans.BeanIndicadorValorWS;

@Remote
public interface LN_T_SFEvaluacionRemote {
    String registrarEvaluacion_LN_WS(List<BeanIndicadorValorWS> lstBeanIndiVal, Integer nidEvaluacion,Integer nidUsuario,Integer nidLog);
}
