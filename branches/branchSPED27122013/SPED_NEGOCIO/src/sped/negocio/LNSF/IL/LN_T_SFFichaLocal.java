package sped.negocio.LNSF.IL;

import java.util.List;

import javax.ejb.Local;

import sped.negocio.entidades.beans.BeanCriterio;
import sped.negocio.entidades.beans.BeanFicha;

@Local
public interface LN_T_SFFichaLocal {
    
    BeanFicha registrarFicha(String tipFicha,
                             String tipFichaCurso,
                             String version,
                             int numVal,
                             List<BeanCriterio> listaCritsIndis,
                             int evento,
                             int nidFicha);
    void eliminarFichaCriterio(int nidFicha);
    BeanFicha desactivarActivarFicha(int nidFicha,String actDesac);
    void reactivarFichaYDesactivarElResto(String tipFicha,
                                          String tipCursoFicha,
                                          int nidFicha);
}
