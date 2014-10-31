package sped.negocio.entidades.beans;

import java.io.Serializable;

public class BeanConstraint implements Serializable {
    @SuppressWarnings("compatibility:-4236224317352537616")
    private static final long serialVersionUID = 1L;
    
    private String descripcionAMostrar;
    private String nombreCampo;
    private String nombreTabla;
    private String valorCampo;
    private int nidRole;

    public void setNidRole(int nidRole) {
        this.nidRole = nidRole;
    }

    public int getNidRole() {
        return nidRole;
    }

    public void setDescripcionAMostrar(String descripcionAMostrar) {
        this.descripcionAMostrar = descripcionAMostrar;
    }

    public String getDescripcionAMostrar() {
        return descripcionAMostrar;
    }

    public void setNombreCampo(String nombreCampo) {
        this.nombreCampo = nombreCampo;
    }

    public String getNombreCampo() {
        return nombreCampo;
    }

    public void setNombreTabla(String nombreTabla) {
        this.nombreTabla = nombreTabla;
    }

    public String getNombreTabla() {
        return nombreTabla;
    }

    public void setValorCampo(String valorCampo) {
        this.valorCampo = valorCampo;
    }

    public String getValorCampo() {
        return valorCampo;
    }
}
