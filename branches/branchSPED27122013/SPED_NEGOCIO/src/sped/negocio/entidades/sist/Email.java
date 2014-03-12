package sped.negocio.entidades.sist;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@NamedQueries({ @NamedQuery(name = "Email.findAll", query = "select o from Email o") })
@Table(name = "\"stemail\"")
public class Email implements Serializable {
    private static final long serialVersionUID = 3715260949504773710L;
    @Column(name = "clave", nullable = false)
    private String clave;
    @Column(name = "correo", nullable = false)
    private String correo;
    @Column(name = "host", nullable = false)
    private String host;
    @Id
    @Column(name = "nidEmail", nullable = false)
    private int nidEmail;
    @Column(name = "puerto", nullable = false)
    private String puerto;

    public Email() {
    }

    public Email(String clave, String correo, String host, int nidEmail, String puerto) {
        this.clave = clave;
        this.correo = correo;
        this.host = host;
        this.nidEmail = nidEmail;
        this.puerto = puerto;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getNidEmail() {
        return nidEmail;
    }

    public void setNidEmail(int nidEmail) {
        this.nidEmail = nidEmail;
    }

    public String getPuerto() {
        return puerto;
    }

    public void setPuerto(String puerto) {
        this.puerto = puerto;
    }
}
