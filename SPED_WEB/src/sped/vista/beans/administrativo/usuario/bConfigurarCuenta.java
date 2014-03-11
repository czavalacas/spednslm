package sped.vista.beans.administrativo.usuario;

import javax.faces.event.ActionEvent;

import sped.negocio.entidades.beans.BeanUsuario;

import sped.vista.Utils.Utils;

public class bConfigurarCuenta {
    private bSessionConfigurarCuenta sessionConfigurarCuenta;
    private BeanUsuario beanUsuario = (BeanUsuario) Utils.getSession("USER");
    
    public bConfigurarCuenta() {
    }

    public void setSessionConfigurarCuenta(bSessionConfigurarCuenta sessionConfigurarCuenta) {
        this.sessionConfigurarCuenta = sessionConfigurarCuenta;
    }

    public bSessionConfigurarCuenta getSessionConfigurarCuenta() {
        return sessionConfigurarCuenta;
    }
}
