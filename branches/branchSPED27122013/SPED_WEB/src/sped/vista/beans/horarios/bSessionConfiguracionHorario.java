package sped.vista.beans.horarios;

import java.io.Serializable;

public class bSessionConfiguracionHorario implements Serializable {
    @SuppressWarnings("compatibility:-6373971498140473254")
    private static final long serialVersionUID = 1L;
    private String nidSedeChoice;
    public bSessionConfiguracionHorario() {
    }


    public void setNidSedeChoice(String nidSedeChoice) {
        this.nidSedeChoice = nidSedeChoice;
    }

    public String getNidSedeChoice() {
        return nidSedeChoice;
    }
}
