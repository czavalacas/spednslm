package sped.negocio.LNSF.IR;

import java.util.List;

import javax.ejb.Remote;

import sped.negocio.entidades.beans.BeanCriterio;

@Remote
public interface LN_C_SFFichaCriterioRemote {
    List<BeanCriterio> getListaCriteriosByFicha(int nidFicha);
}
