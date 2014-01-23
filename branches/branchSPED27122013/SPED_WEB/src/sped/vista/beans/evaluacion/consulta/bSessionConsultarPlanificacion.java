package sped.vista.beans.evaluacion.consulta;

import java.io.Serializable;

public class bSessionConsultarPlanificacion {
    private String nidEvaluadorChoice;
    private String nidSedeChoice;
    private String nidNivelChoice;
    public bSessionConsultarPlanificacion() {
    }


    public void setNidEvaluadorChoice(String nidEvaluadorChoice) {
        this.nidEvaluadorChoice = nidEvaluadorChoice;
    }

    public String getNidEvaluadorChoice() {
        return nidEvaluadorChoice;
    }

    public void setNidSedeChoice(String nidSedeChoice) {
        this.nidSedeChoice = nidSedeChoice;
    }

    public String getNidSedeChoice() {
        return nidSedeChoice;
    }

    public void setNidNivelChoice(String nidNivelChoice) {
        this.nidNivelChoice = nidNivelChoice;
    }

    public String getNidNivelChoice() {
        return nidNivelChoice;
    }
}
