package sped.vista.beans.evaluacion.ficha;

import javax.faces.event.ActionEvent;

public class bRegistrarFicha {
    
    private bSessionRegistrarFicha sessionRegistrarFicha;
    private String holaLocal;
    public bRegistrarFicha() {
        System.out.println("local: "+holaLocal);
       // System.out.println("sesion: "+bSessionRegistrarFicha.getHola());
    }


    public void setSessionRegistrarFicha(bSessionRegistrarFicha sessionRegistrarFicha) {
        this.sessionRegistrarFicha = sessionRegistrarFicha;
    }

    public bSessionRegistrarFicha getSessionRegistrarFicha() {
        return sessionRegistrarFicha;
    }

    public void setHolaLocal(String holaLocal) {
        this.holaLocal = holaLocal;
    }

    public String getHolaLocal() {
        return holaLocal;
    }

    public void mandarParams(ActionEvent actionEvent) {
        System.out.println(">>>local: "+holaLocal);
        System.out.println(">>sesion: "+sessionRegistrarFicha.getHola());
    }
}
