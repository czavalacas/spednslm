package sped.negocio.entidades.beans;

import java.io.Serializable;

import java.sql.Timestamp;

public class BeanFicha implements Serializable,Cloneable {
    @SuppressWarnings("compatibility:-6254141762681500770")
    private static final long serialVersionUID = 1L;
    
    private String descripcionVersion;
    private String estadoFicha;
    private int nidFicha;
    private String tipoFicha;
    private String tipoFichaCurso;
    private String descripcionTipoFicha;//Auxiliares tabla:admcons
    private String descripcionTipoFichaCurso;//Auxiliares tabla:admcons
    private String descripcionEstadoFicha;//Auxiliares tabla:admcons
    private Timestamp fechaFicha;
    private BeanError beanError = new BeanError();
    private int cantidadValores;

    public Object clone() {
        Object clone = null;
        try {
            clone = super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return clone;
    }

    public void setCantidadValores(int cantidadValores) {
        this.cantidadValores = cantidadValores;
    }

    public int getCantidadValores() {
        return cantidadValores;
    }

    public void setBeanError(BeanError beanError) {
        this.beanError = beanError;
    }

    public BeanError getBeanError() {
        return beanError;
    }

    public void setFechaFicha(Timestamp fechaFicha) {
        this.fechaFicha = fechaFicha;
    }

    public Timestamp getFechaFicha() {
        return fechaFicha;
    }

    public void setDescripcionVersion(String descripcionVersion) {
        this.descripcionVersion = descripcionVersion;
    }

    public String getDescripcionVersion() {
        return descripcionVersion;
    }

    public void setEstadoFicha(String estadoFicha) {
        this.estadoFicha = estadoFicha;
    }

    public String getEstadoFicha() {
        return estadoFicha;
    }

    public void setNidFicha(int nidFicha) {
        this.nidFicha = nidFicha;
    }

    public int getNidFicha() {
        return nidFicha;
    }

    public void setTipoFicha(String tipoFicha) {
        this.tipoFicha = tipoFicha;
    }

    public String getTipoFicha() {
        return tipoFicha;
    }

    public void setTipoFichaCurso(String tipoFichaCurso) {
        this.tipoFichaCurso = tipoFichaCurso;
    }

    public String getTipoFichaCurso() {
        return tipoFichaCurso;
    }

    public void setDescripcionTipoFicha(String descripcionTipoFicha) {
        this.descripcionTipoFicha = descripcionTipoFicha;
    }

    public String getDescripcionTipoFicha() {
        return descripcionTipoFicha;
    }

    public void setDescripcionTipoFichaCurso(String descripcionTipoFichaCurso) {
        this.descripcionTipoFichaCurso = descripcionTipoFichaCurso;
    }

    public String getDescripcionTipoFichaCurso() {
        return descripcionTipoFichaCurso;
    }

    public void setDescripcionEstadoFicha(String descripcionEstadoFicha) {
        this.descripcionEstadoFicha = descripcionEstadoFicha;
    }

    public String getDescripcionEstadoFicha() {
        return descripcionEstadoFicha;
    }
}
