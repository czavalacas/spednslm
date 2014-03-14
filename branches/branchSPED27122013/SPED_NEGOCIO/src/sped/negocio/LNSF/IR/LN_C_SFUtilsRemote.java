package sped.negocio.LNSF.IR;

import java.util.List;

import javax.ejb.Remote;

import sped.negocio.entidades.beans.BeanCombo;
import sped.negocio.entidades.beans.BeanComboString;
import sped.negocio.entidades.beans.BeanConstraint;

@Remote
public interface LN_C_SFUtilsRemote {
    List<BeanConstraint> getListaConstraintsLN(String nombreCampo,
                                               String nombreTabla);
    List<BeanCombo> getPlanificadores_LN_WS();
    List<BeanCombo> getEvaluadores_LN_WS();
    List<BeanComboString> getTipoVisitaFromConstraint();
    List<BeanCombo> getAreas_LN_WS();
    List<BeanCombo> getProblemas_LN_WS();
    List<BeanCombo> getUsuarios_LN_WS();
    int findCountByProperty(String correo, 
                            boolean changeCase, 
                            boolean isUpdate);
    List<BeanCombo> getSedes_LN();
    List<BeanCombo> getNiveles_LN();
}
