package sped.vista.beans.horarios;

public class bConfiguracionHorario {
    
    private bSessionConfiguracionHorario sessionConfiguracionHorario;
    
    public bConfiguracionHorario() {
    }

    public void setSessionConfiguracionHorario(bSessionConfiguracionHorario sessionConfiguracionHorario) {
        this.sessionConfiguracionHorario = sessionConfiguracionHorario;
    }

    public bSessionConfiguracionHorario getSessionConfiguracionHorario() {
        return sessionConfiguracionHorario;
    }

    public String prueba() {
        System.out.println(sessionConfiguracionHorario.getPruebita());
        return null;
    }
}
