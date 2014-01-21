package sped.vista.beans.evaluacion.consulta;

import java.util.List;

import sped.negocio.entidades.beans.BeanEvaluacion;

public class bSessionConsultarEvaluacion {
    private int exec;
    private List<BeanEvaluacion> lstBeanEvaluacion;
    public bSessionConsultarEvaluacion() {
    }


    public void setExec(int exec) {
        this.exec = exec;
    }

    public int getExec() {
        return exec;
    }

    public void setLstBeanEvaluacion(List<BeanEvaluacion> lstBeanEvaluacion) {
        this.lstBeanEvaluacion = lstBeanEvaluacion;
    }

    public List<BeanEvaluacion> getLstBeanEvaluacion() {
        return lstBeanEvaluacion;
    }
}
