package sped.vista.beans.evaluacion.evaluar;

import java.util.ArrayList;
import java.util.List;

import org.apache.myfaces.trinidad.model.ChildPropertyTreeModel;

import sped.negocio.entidades.beans.BeanCriterio;
import sped.negocio.entidades.beans.BeanEvaluacionWS;

public class bSessionEvaluar {

    private int exec = 0;
    private List<BeanEvaluacionWS> lstPlanificacionesXEvaluar = new ArrayList<BeanEvaluacionWS>();
    private boolean visiblePanelBoxPanelBoxFicha = false;
    BeanEvaluacionWS planifSelect = new BeanEvaluacionWS();
    private List<BeanCriterio> lstCriteriosMultiples = new ArrayList<BeanCriterio>();
    private ChildPropertyTreeModel permisosTree;
    private int maxValor;
    private double notaFinal;
    private String estiloFinal;
    private String comentarioEvaluador;
    private String temaEvaluacion;

    public void setTemaEvaluacion(String temaEvaluacion) {
        this.temaEvaluacion = temaEvaluacion;
    }

    public String getTemaEvaluacion() {
        return temaEvaluacion;
    }

    public void setComentarioEvaluador(String comentarioEvaluador) {
        this.comentarioEvaluador = comentarioEvaluador;
    }

    public String getComentarioEvaluador() {
        return comentarioEvaluador;
    }

    public void setNotaFinal(double notaFinal) {
        this.notaFinal = notaFinal;
    }

    public double getNotaFinal() {
        return notaFinal;
    }

    public void setEstiloFinal(String estiloFinal) {
        this.estiloFinal = estiloFinal;
    }

    public String getEstiloFinal() {
        return estiloFinal;
    }

    public void setMaxValor(int maxValor) {
        this.maxValor = maxValor;
    }

    public int getMaxValor() {
        return maxValor;
    }

    public void setPermisosTree(ChildPropertyTreeModel permisosTree) {
        this.permisosTree = permisosTree;
    }

    public ChildPropertyTreeModel getPermisosTree() {
        return permisosTree;
    }

    public void setLstCriteriosMultiples(List<BeanCriterio> lstCriteriosMultiples) {
        this.lstCriteriosMultiples = lstCriteriosMultiples;
    }

    public List<BeanCriterio> getLstCriteriosMultiples() {
        return lstCriteriosMultiples;
    }

    public void setExec(int exec) {
        this.exec = exec;
    }

    public int getExec() {
        return exec;
    }

    public void setLstPlanificacionesXEvaluar(List<BeanEvaluacionWS> lstPlanificacionesXEvaluar) {
        this.lstPlanificacionesXEvaluar = lstPlanificacionesXEvaluar;
    }

    public List<BeanEvaluacionWS> getLstPlanificacionesXEvaluar() {
        return lstPlanificacionesXEvaluar;
    }

    public void setVisiblePanelBoxPanelBoxFicha(boolean visiblePanelBoxPanelBoxFicha) {
        this.visiblePanelBoxPanelBoxFicha = visiblePanelBoxPanelBoxFicha;
    }

    public boolean isVisiblePanelBoxPanelBoxFicha() {
        return visiblePanelBoxPanelBoxFicha;
    }

    public void setPlanifSelect(BeanEvaluacionWS planifSelect) {
        this.planifSelect = planifSelect;
    }

    public BeanEvaluacionWS getPlanifSelect() {
        return planifSelect;
    }
}
