package sped.vista.beans.evaluacion.consulta;

import oracle.adf.view.rich.component.rich.data.RichTable;

public class bConsultaPlanificacion {
    
    private bSessionConsultarPlanificacion sessionConsultarPlanificacion;
    private RichTable tbPlanificacion;

    public bConsultaPlanificacion() {
        System.out.println("DIEGO ES GAY");
    }


    public void setSessionConsultarPlanificacion(bSessionConsultarPlanificacion sessionConsultarPlanificacion) {
        this.sessionConsultarPlanificacion = sessionConsultarPlanificacion;
    }

    public bSessionConsultarPlanificacion getSessionConsultarPlanificacion() {
        return sessionConsultarPlanificacion;
    }


    public void setTbPlanificacion(RichTable tbPlanificacion) {
        this.tbPlanificacion = tbPlanificacion;
    }

    public RichTable getTbPlanificacion() {
        return tbPlanificacion;
    }
}
