package sped.negocio.BDL.IL;

import java.util.List;

import javax.ejb.Local;

import sped.negocio.entidades.admin.Constraint;
import sped.negocio.entidades.admin.ConstraintPK;
import sped.negocio.entidades.beans.BeanCombo;
import sped.negocio.entidades.beans.BeanComboDouble;
import sped.negocio.entidades.beans.BeanComboDouble2;
import sped.negocio.entidades.beans.BeanComboString;
import sped.negocio.entidades.beans.BeanConstraint;
import sped.negocio.entidades.sist.Log;
import sped.negocio.entidades.sist.Logger;

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
    List<BeanCombo> getCursos(String id, String desc);
    List<BeanCombo> getGrados(String id, String desc);
    List<BeanComboString> getEstadosEvaluacion(String id, String desc);
    List<BeanCombo> getAulaByNidSedeNivel(String id, String desc, int nidSede, int nidNivel);
    List<BeanCombo> getCursosByArea(String id, String desc, int nidArea);
    List<BeanComboString> getProfesor(String id, String desc);
    List<BeanCombo> getRolNoAdmin(String id, String desc);
    List<BeanCombo> getEvaluadoresByArea(String id, String desc, int nidArea);
    List<BeanCombo> getEvaluadoresByAreaByEstado(String id, String desc, int nidArea, boolean estado);
    List<BeanCombo> getEvaluadoresByEstado(String id, String desc, boolean estado);
    Log findLogById(int id);
    List<BeanComboDouble> getPosibleListaValoresIndicador(int nidCritIndi);
    List<BeanComboDouble2> getListaValores();
    List<BeanComboString> getTiposFalta(String id, String desc);
    Integer[] getMinMaxEvasPorDiaConstraint_LN(int nidRole);
    List<Constraint> getMinMaxEvasPorDiaConfigConstraint_LN();
    List<Constraint> getConstraintByAttr_LN(String tabla,String campo,String desc);
    List<BeanComboString> getCursosActivos();
    List<BeanComboString> getAulaActivas(int nidSede);
    List<BeanComboString> getSedesString();
    List<BeanComboString> getProfesorActivos();
}