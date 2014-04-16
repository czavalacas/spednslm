package sped.negocio.entidades.beans;

import java.io.Serializable;

public class BeanMail implements Serializable {
    @SuppressWarnings("compatibility:5255361914689632897")
    private static final long serialVersionUID = 1L;
    private String clave;
    private String correo;
    private String host;
    private int nidEmail;
    private String puerto;


    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getClave() {
        return clave;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getCorreo() {
        return correo;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getHost() {
        return host;
    }

    public void setNidEmail(int nidEmail) {
        this.nidEmail = nidEmail;
    }

    public int getNidEmail() {
        return nidEmail;
    }

    public void setPuerto(String puerto) {
        this.puerto = puerto;
    }

    public String getPuerto() {
        return puerto;
    }
}
