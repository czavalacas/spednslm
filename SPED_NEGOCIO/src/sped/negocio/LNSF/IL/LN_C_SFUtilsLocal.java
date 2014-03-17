package sped.negocio.LNSF.IL;

import java.util.List;

import javax.ejb.Local;

import sped.negocio.entidades.beans.BeanCombo;
import sped.negocio.entidades.beans.BeanComboString;
import sped.negocio.entidades.beans.BeanConstraint;

@Local
public interface LN_C_SFUtilsLocal {
    List<BeanConstraint> getListaConstraintsLN(String nombreCampo,
                                               String nombreTabla);
    List<BeanCombo> getPlanificadores_LN_WS();
    List<BeanCombo> getEvaluadores_LN_WS();
    List<BeanComboString> getTipoVisitaFromConstraint();
    List<BeanCombo> getAreas_LN_WS();
    List<BeanCombo> getProblemas_LN_WS();
    List<BeanCombo> getUsuarios_LN_WS();
    List<BeanCombo> getSedes_LN();
    int findCountByProperty(String correo, 
                            boolean changeCase, 
                            boolean isUpdate);
    List<BeanCombo> getNiveles_LN();
}