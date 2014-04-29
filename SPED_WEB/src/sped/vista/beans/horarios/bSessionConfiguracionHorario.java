package sped.vista.beans.horarios;

import java.io.Serializable;

public class bSessionConfiguracionHorario implements Serializable {
    @SuppressWarnings("compatibility:-6373971498140473254")
    private static final long serialVersionUID = 1L;
    private String pruebita="algo";
    public bSessionConfiguracionHorario() {
    }

    public void setPruebita(String pruebita) {
        this.pruebita = pruebita;
    }

    public String getPruebita() {
        return pruebita;
    }
}
