package sped.vista.beans.evaluacion.ficha;

import java.io.Serializable;

/** Clase de Sesion del Bean bRegistrarFicha
 * @author dfloresgonz
 * @since 27.12.2013
 */
public class bSessionRegistrarFicha implements Serializable {
    
    private String hola;
    public bSessionRegistrarFicha() {
    }

    public void setHola(String hola) {
        this.hola = hola;
    }

    public String getHola() {
        return hola;
    }
}
