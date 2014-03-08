package sped.negocio.entidades.sist;

import java.io.Serializable;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@NamedQueries({
              @NamedQuery(name = "NotificacionParteOcurrencia.findAll",
                          query = "select o from NotificacionParteOcurrencia o") })
@Table(name = "\"stmnopo\"")
public class NotificacionParteOcurrencia implements Serializable {
    private static final long serialVersionUID = 6767762404648682889L;
    @Id
    @Column(name = "cidNotificacion", nullable = false)
    private String cidNotificacion;
    @Column(name = "fechaLeido")
    private Timestamp fechaLeido;
    @Column(name = "fechaRegistro", nullable = false)
    private Timestamp fechaRegistro;
    @Column(name = "leido", nullable = false)
    private String leido;
    @Column(name = "nidParte", nullable = false)
    private long nidParte;
    @Column(name = "nidUsuario", nullable = false)
    private int nidUsuario;

    public NotificacionParteOcurrencia() {
    }

    public NotificacionParteOcurrencia(String cidNotificacion, Timestamp fechaLeido, Timestamp fechaRegistro,
                                       String leido, long nidParte, int nidUsuario) {
        this.cidNotificacion = cidNotificacion;
        this.fechaLeido = fechaLeido;
        this.fechaRegistro = fechaRegistro;
        this.leido = leido;
        this.nidParte = nidParte;
        this.nidUsuario = nidUsuario;
    }

    public String getCidNotificacion() {
        return cidNotificacion;
    }

    public void setCidNotificacion(String cidNotificacion) {
        this.cidNotificacion = cidNotificacion;
    }

    public Timestamp getFechaLeido() {
        return fechaLeido;
    }

    public void setFechaLeido(Timestamp fechaLeido) {
        this.fechaLeido = fechaLeido;
    }

    public Timestamp getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Timestamp fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getLeido() {
        return leido;
    }

    public void setLeido(String leido) {
        this.leido = leido;
    }

    public long getNidParte() {
        return nidParte;
    }

    public void setNidParte(long nidParte) {
        this.nidParte = nidParte;
    }

    public int getNidUsuario() {
        return nidUsuario;
    }

    public void setNidUsuario(int nidUsuario) {
        this.nidUsuario = nidUsuario;
    }
}
