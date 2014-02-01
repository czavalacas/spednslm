package sped.negocio.LNSF.IL;

import java.util.List;

import javax.ejb.Local;

import sped.negocio.entidades.beans.BeanCriterio;

@Local
public interface LN_C_SFFichaCriterioLocal {
    List<BeanCriterio> getListaCriteriosByFicha(int nidFicha);
}
