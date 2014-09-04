package sped.negocio.LNSF.IL;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Local;

import sped.negocio.entidades.beans.BeanCombo;
import sped.negocio.entidades.beans.BeanComboDouble2;
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
    List<BeanCombo> getRolEvaluadores_LN();
    List<BeanCombo> getCursos_LN();
    List<BeanCombo> getGrados_LN();
    List<BeanComboString> getEstadoEvaluacionFromConstraint();    
    List<BeanComboString> getListaEstados(String nombreCampo, String nombreTabla);
    List<BeanCombo> getAulaByNidSedeNivel(int nidSede, int nidNivel);
    List<BeanCombo> getCursosByArea_LN(int nidArea);
    List<BeanComboString> getProfesor_LN();
    List<BeanCombo> getRol_LN();
    List<BeanCombo> getEvaluadoresByArea_LN(int nidArea);
    List<BeanCombo> getEvaluadoresByAreaByEstado_LN(int nidArea, boolean estado);
    List<BeanCombo> getEvaluadoresByEstado_LN(boolean estado);
    List<BeanComboDouble2> getListaValores_LN();
    List<BeanComboString> getTiposFalta_LN();
    /**
     * Metodo que retorna el estado del evaluador
     * @author dfloresgonz
     * @param cantEjecutados = cantidad de evaluaciones que ha ejecutado el usuario
     * @param cantMaxEvasPosib = cantidad de evaluaciones maximas posibles (cantConfiguradaMaxEnConstraint * cantDiasLaborablesUsuario)
     * @param cantMaxDiaConstraint = valor de la tabla constraint cantMAX configuracion
     * @param cantMinConstraint = valor de la tabla constraint cantMin configuracion
     * @return nombre del estado para usarlo en el grafico 2 del dash
     */
    String getEstadoEvaluadorByDias(int cantEjecutados,int cantMaxEvasPosib, 
                                    int cantMaxDiaConstraint,int cantMinConstraint);
    int getCantidadEvasMinimoOptimo(int cantMaxEvasPosib,int cantMaxDiaConstraint, int cantMinConstraint);
    List<BeanConstraint> getMinMaxEvasPorDiaConfigConstraint_LN();
}
