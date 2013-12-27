package sped.vista.beans.evaluacion.ficha;

import javax.faces.event.ActionEvent;

/** Clase de Respaldo de Frm_Registrar_Ficha.jsff
 * @author dfloresgonz
 * @since 27.12.2013
 */
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

    public String mostrarTest() {
        System.out.println("___>>>local: "+holaLocal);
        System.out.println("___>>sesion: "+sessionRegistrarFicha.getHola());
        return null;
    }
}
