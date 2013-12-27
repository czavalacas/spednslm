package sped.negocio.entidades.sist;

import java.io.Serializable;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@NamedQueries({ @NamedQuery(name = "Log.findAll", query = "select o from Log o") })
@Table(name = "\"stdlogg\"")
public class Log implements Serializable {
    private static final long serialVersionUID = -5822713550569127075L;
    @Column(name = "detalle")
    private String detalle;
    @Column(name = "fecha_conexion")
    private Timestamp fechaConexion;
    @Column(name = "fecha_desconexion")
    private Timestamp fechaDesconexion;
    @Column(name = "fecha_evento")
    private Timestamp fechaEvento;
    @Column(name = "ip_privada")
    private String ipPrivada;
    @Column(name = "ip_publica")
    private String ipPublica;
    @Column(name = "navegador_web")
    private String navegadorWeb;
    @Id
    @Column(name = "nid_log", nullable = false)
    private int nidLog;
    @Column(name = "sistema_operativo")
    private String sistemaOperativo;
    @Column(name = "tipo_registro")
    private String tipoRegistro;
    @ManyToOne
    @JoinColumn(name = "nid_evento")
    private Evento evento;

    public Log() {
    }

    public Log(String detalle, Timestamp fechaConexion, Timestamp fechaDesconexion, Timestamp fechaEvento,
               String ipPrivada, String ipPublica, String navegadorWeb, int nidLog, Evento evento,
               String sistemaOperativo, String tipoRegistro) {
        this.detalle = detalle;
        this.fechaConexion = fechaConexion;
        this.fechaDesconexion = fechaDesconexion;
        this.fechaEvento = fechaEvento;
        this.ipPrivada = ipPrivada;
        this.ipPublica = ipPublica;
        this.navegadorWeb = navegadorWeb;
        this.nidLog = nidLog;
        this.evento = evento;
        this.sistemaOperativo = sistemaOperativo;
        this.tipoRegistro = tipoRegistro;
    }


    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public Timestamp getFechaConexion() {
        return fechaConexion;
    }

    public void setFechaConexion(Timestamp fechaConexion) {
        this.fechaConexion = fechaConexion;
    }

    public Timestamp getFechaDesconexion() {
        return fechaDesconexion;
    }

    public void setFechaDesconexion(Timestamp fechaDesconexion) {
        this.fechaDesconexion = fechaDesconexion;
    }

    public Timestamp getFechaEvento() {
        return fechaEvento;
    }

    public void setFechaEvento(Timestamp fechaEvento) {
        this.fechaEvento = fechaEvento;
    }

    public String getIpPrivada() {
        return ipPrivada;
    }

    public void setIpPrivada(String ipPrivada) {
        this.ipPrivada = ipPrivada;
    }

    public String getIpPublica() {
        return ipPublica;
    }

    public void setIpPublica(String ipPublica) {
        this.ipPublica = ipPublica;
    }

    public String getNavegadorWeb() {
        return navegadorWeb;
    }

    public void setNavegadorWeb(String navegadorWeb) {
        this.navegadorWeb = navegadorWeb;
    }

    public int getNidLog() {
        return nidLog;
    }

    public void setNidLog(int nidLog) {
        this.nidLog = nidLog;
    }


    public String getSistemaOperativo() {
        return sistemaOperativo;
    }

    public void setSistemaOperativo(String sistemaOperativo) {
        this.sistemaOperativo = sistemaOperativo;
    }

    public String getTipoRegistro() {
        return tipoRegistro;
    }

    public void setTipoRegistro(String tipoRegistro) {
        this.tipoRegistro = tipoRegistro;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append(getClass().getName() + "@" + Integer.toHexString(hashCode()));
        buffer.append('[');
        buffer.append("detalle=");
        buffer.append(getDetalle());
        buffer.append(',');
        buffer.append("fechaConexion=");
        buffer.append(getFechaConexion());
        buffer.append(',');
        buffer.append("fechaDesconexion=");
        buffer.append(getFechaDesconexion());
        buffer.append(',');
        buffer.append("fechaEvento=");
        buffer.append(getFechaEvento());
        buffer.append(',');
        buffer.append("ipPrivada=");
        buffer.append(getIpPrivada());
        buffer.append(',');
        buffer.append("ipPublica=");
        buffer.append(getIpPublica());
        buffer.append(',');
        buffer.append("navegadorWeb=");
        buffer.append(getNavegadorWeb());
        buffer.append(',');
        buffer.append("nidLog=");
        buffer.append(getNidLog());
        buffer.append(',');
        buffer.append("sistemaOperativo=");
        buffer.append(getSistemaOperativo());
        buffer.append(',');
        buffer.append("tipoRegistro=");
        buffer.append(getTipoRegistro());
        buffer.append(']');
        return buffer.toString();
    }
}
