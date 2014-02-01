package sped.negocio.LNSF.IR;

import java.util.List;

import javax.ejb.Remote;

import sped.negocio.entidades.beans.BeanCriterio;
import sped.negocio.entidades.beans.BeanFicha;

@Remote
public interface LN_T_SFFichaRemote {
    
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
