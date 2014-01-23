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
                             List<BeanCriterio> listaCritsIndis);
}
