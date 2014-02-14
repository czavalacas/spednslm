package sped.negocio.entidades.sist;

import java.io.Serializable;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

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
    @TableGenerator( name = "stmcodi.stdlogg", table = "stmcodi", pkColumnName = "APP_SEQ_NAME", pkColumnValue = "stdlogg.nidLog", valueColumnName = "APP_SEQ_VALUE", initialValue = 1, allocationSize = 1 )
    @GeneratedValue( strategy = GenerationType.TABLE, generator = "stmcodi.stdlogg" )    
    @Column(name = "nid_log", nullable = false)
    private int nidLog;
    @Column(name = "sistema_operativo")
    private String sistemaOperativo;
    @Column(name = "tipo_registro")
    private String tipoRegistro;
    //@ManyToOne
   // @JoinColumn(name = "nid_evento")
   @Column(name = "nid_evento")
    private Integer nid_evento;
    //WEBSERVICE - DISPOSITIVO MOVIL
    @Column(name = "device_name")
    private String device_name;
    @Column(name = "device_platform")
    private String device_platform;
    @Column(name = "device_version")
    private String device_version;
    @Column(name = "device_os")
    private String device_os;
    @Column(name = "device_model")
    private String device_model;
    @Column(name = "device_geolocation")
    private String device_geolocation;
    @Column(name = "device_networkstatus")
    private String device_networkstatus;
    @Column(name = "device_screenwidth")
    private String device_screenwidth;
    @Column(name = "device_screenheight")
    private String device_screenheight;
    @Column(name = "device_avai_scrwidth")
    private String device_avai_scrwidth;
    @Column(name = "device_avai_scrheight")
    private String device_avai_scrheight;
    @Column(name = "device_screendpi")
    private String device_screendpi;
    @Column(name = "device_diagonalsize")
    private String device_diagonalsize;
    @Column(name = "nid_usuario")
    private Integer nid_usuario;
    
    public Log() {
    }

    public Log(String detalle, Timestamp fechaConexion, Timestamp fechaDesconexion, Timestamp fechaEvento,
               String ipPrivada, String ipPublica, String navegadorWeb, int nidLog, /*Evento evento,*/
               String sistemaOperativo, String tipoRegistro) {
        this.detalle = detalle;
        this.fechaConexion = fechaConexion;
        this.fechaDesconexion = fechaDesconexion;
        this.fechaEvento = fechaEvento;
        this.ipPrivada = ipPrivada;
        this.ipPublica = ipPublica;
        this.navegadorWeb = navegadorWeb;
        this.nidLog = nidLog;
    //    this.evento = evento;
        this.sistemaOperativo = sistemaOperativo;
        this.tipoRegistro = tipoRegistro;
    }

    public void setNid_usuario(Integer nid_usuario) {
        this.nid_usuario = nid_usuario;
    }

    public Integer getNid_usuario() {
        return nid_usuario;
    }

    public void setDevice_name(String device_name) {
        this.device_name = device_name;
    }

    public String getDevice_name() {
        return device_name;
    }

    public void setDevice_platform(String device_platform) {
        this.device_platform = device_platform;
    }

    public String getDevice_platform() {
        return device_platform;
    }

    public void setDevice_version(String device_version) {
        this.device_version = device_version;
    }

    public String getDevice_version() {
        return device_version;
    }

    public void setDevice_os(String device_os) {
        this.device_os = device_os;
    }

    public String getDevice_os() {
        return device_os;
    }

    public void setDevice_model(String device_model) {
        this.device_model = device_model;
    }

    public String getDevice_model() {
        return device_model;
    }

    public void setDevice_geolocation(String device_geolocation) {
        this.device_geolocation = device_geolocation;
    }

    public String getDevice_geolocation() {
        return device_geolocation;
    }

    public void setDevice_networkstatus(String device_networkstatus) {
        this.device_networkstatus = device_networkstatus;
    }

    public String getDevice_networkstatus() {
        return device_networkstatus;
    }

    public void setDevice_screenwidth(String device_screenwidth) {
        this.device_screenwidth = device_screenwidth;
    }

    public String getDevice_screenwidth() {
        return device_screenwidth;
    }

    public void setDevice_screenheight(String device_screenheight) {
        this.device_screenheight = device_screenheight;
    }

    public String getDevice_screenheight() {
        return device_screenheight;
    }

    public void setDevice_avai_scrwidth(String device_avai_scrwidth) {
        this.device_avai_scrwidth = device_avai_scrwidth;
    }

    public String getDevice_avai_scrwidth() {
        return device_avai_scrwidth;
    }

    public void setDevice_avai_scrheight(String device_avai_scrheight) {
        this.device_avai_scrheight = device_avai_scrheight;
    }

    public String getDevice_avai_scrheight() {
        return device_avai_scrheight;
    }

    public void setDevice_screendpi(String device_screendpi) {
        this.device_screendpi = device_screendpi;
    }

    public String getDevice_screendpi() {
        return device_screendpi;
    }

    public void setDevice_diagonalsize(String device_diagonalsize) {
        this.device_diagonalsize = device_diagonalsize;
    }

    public String getDevice_diagonalsize() {
        return device_diagonalsize;
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

    public void setNid_evento(Integer nid_evento) {
        this.nid_evento = nid_evento;
    }

    public Integer getNid_evento() {
        return nid_evento;
    }
    /*
    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }
*/
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
