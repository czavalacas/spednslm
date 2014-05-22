package sped.negocio.LNSF.IR;

import java.util.List;

import javax.ejb.Remote;

import sped.negocio.entidades.beans.BeanCriterio;
import sped.negocio.entidades.beans.BeanCriterioWS;
import sped.negocio.entidades.beans.BeanFichaCriterio;

@Remote
public interface LN_C_SFFichaCriterioRemote {
    List<BeanCriterio> getListaCriteriosByFicha(int nidFicha);
    List<BeanCriterio> getListaCriteriosByFichaConValores(int nidFicha,int nidEvaluacion);
    List<BeanFichaCriterio> getLstFichaCriterioByEvaluacion(int nidEvaluacion);
    List<BeanCriterioWS> getListaCriteriosByFicha_WS(int nidFicha);
}
