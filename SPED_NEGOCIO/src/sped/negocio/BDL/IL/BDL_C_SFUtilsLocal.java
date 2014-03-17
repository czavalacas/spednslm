package sped.negocio.BDL.IL;

import java.util.List;

import javax.ejb.Local;

import sped.negocio.entidades.admin.Constraint;
import sped.negocio.entidades.admin.ConstraintPK;
import sped.negocio.entidades.beans.BeanCombo;
import sped.negocio.entidades.beans.BeanComboString;
import sped.negocio.entidades.beans.BeanConstraint;

@Local
public interface BDL_C_SFUtilsLocal {
    
    BeanConstraint getCatalogoConstraints(String nombreCampo, 
                                          String nombreTabla, 
                                          String valorCampo);
    List<Constraint> getListaConstraintsBDL(String nombreCampo, 
                                         String nombreTabla);
    int findCountByProperty(String atributoDesc, 
                            Object atributoValor, 
                            String entidad, 
                            boolean changeCase,
                            boolean isUpdate);
    List<BeanCombo> getPlanificadores_WS(String id, String desc);
    List<BeanCombo> getEvaluadores_WS(String id, String desc);
    List<BeanComboString> getTipoVisita(String id,String desc);
    List<BeanCombo> getAreas_WS(String id, String desc);
    List<BeanCombo> getProblemas_WS(String id, String desc);
    List<BeanCombo> getUsuarios_WS(String id, String desc);
    List<BeanCombo> getSedes(String id, String desc);
    List<BeanCombo> getNiveles(String id, String desc);
    List<BeanCombo> getRolEvaluadores(String id, String desc);
    List<BeanCombo> getEvaluadoresByRol(String id, String desc, int nidRol);
}
