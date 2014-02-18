package sped.negocio.BDL.IL;

import java.util.List;

import javax.ejb.Local;

import sped.negocio.entidades.admin.Constraint;
import sped.negocio.entidades.admin.ConstraintPK;
import sped.negocio.entidades.beans.BeanCombo;
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
}
