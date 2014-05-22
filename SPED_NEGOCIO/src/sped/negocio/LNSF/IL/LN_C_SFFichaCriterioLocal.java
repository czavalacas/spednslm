package sped.negocio.LNSF.IL;

import java.util.List;

import javax.ejb.Local;

import sped.negocio.entidades.beans.BeanCriterio;
import sped.negocio.entidades.beans.BeanCriterioWS;
import sped.negocio.entidades.beans.BeanFichaCriterio;

@Local
public interface LN_C_SFFichaCriterioLocal {
    List<BeanCriterio> getListaCriteriosByFicha(int nidFicha);
    List<BeanCriterio> getListaCriteriosByFichaConValores(int nidFicha,int nidEvaluacion);
    List<BeanFichaCriterio> getLstFichaCriterioByEvaluacion(int nidEvaluacion);
    List<BeanCriterioWS> getListaCriteriosByFicha_WS(int nidFicha);
}
