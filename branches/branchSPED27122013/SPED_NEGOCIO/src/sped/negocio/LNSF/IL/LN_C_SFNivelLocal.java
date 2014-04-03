package sped.negocio.LNSF.IL;

import java.util.List;

import javax.ejb.Local;

import sped.negocio.entidades.beans.BeanCombo;
import sped.negocio.entidades.beans.BeanNivel;

@Local
public interface LN_C_SFNivelLocal {
    List<BeanNivel> getNivelLN();
    List<BeanCombo> findNivelPorAreaAcademica(Integer nidAreaAcademica, String dia);
    BeanNivel findConstrainByIdLN(int id);
    List<BeanCombo> getNivelLNPorSede_ByOrden(Object nidSede, Object nidArea, Object nidCurso);
    /**Temporal getAllNivelesBySedes*/
    List<BeanCombo> getAllNivelesBySedes(String nidSede);
}
