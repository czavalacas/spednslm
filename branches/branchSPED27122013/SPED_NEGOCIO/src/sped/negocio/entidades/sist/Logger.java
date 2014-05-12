package sped.negocio.entidades.sist;

import java.io.Serializable;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
/**
 * Entidad que graba los errores del sistema
 * @author dfloresgonz
 * @since 12.05.2014
 */
@Entity
@Table(name = "\"stmlogger\"")
public class Logger implements Serializable {
    private static final long serialVersionUID = -9132473858779911705L;
    @Column(name = "clase_java")
    private String clase_java;
    @Column(name = "comentario")
    private String comentario;
    @Column(name = "fecha_error", nullable = false)
    private Timestamp fecha_error;
    @Column(name = "metodo_java")
    private String metodo_java;
    @Id
    @TableGenerator( name = "stmcodi.stmlogger", table = "stmcodi", pkColumnName = "APP_SEQ_NAME", pkColumnValue = "stmlogger.nid_log", valueColumnName = "APP_SEQ_VALUE", initialValue = 1, allocationSize = 1 )
    @GeneratedValue( strategy = GenerationType.TABLE, generator = "stmcodi.stmlogger" )    
    @Column(name = "nid_error", nullable = false)
    private long nid_error;
    @Column(name = "nid_log", nullable = false)
    private int nid_log;
    @Column(name = "stacktrace")
    private String stacktrace;
    @Column(name = "tipo_error", nullable = false)
    private String tipo_error;

    public Logger() {
    }

    public Logger(String clase_java, String comentario, Timestamp fecha_error, String metodo_java, long nid_error,
                     int nid_log, String stacktrace, String tipo_error) {
        this.clase_java = clase_java;
        this.comentario = comentario;
        this.fecha_error = fecha_error;
        this.metodo_java = metodo_java;
        this.nid_error = nid_error;
        this.nid_log = nid_log;
        this.stacktrace = stacktrace;
        this.tipo_error = tipo_error;
    }

    public String getClase_java() {
        return clase_java;
    }

    public void setClase_java(String clase_java) {
        this.clase_java = clase_java;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Timestamp getFecha_error() {
        return fecha_error;
    }

    public void setFecha_error(Timestamp fecha_error) {
        this.fecha_error = fecha_error;
    }

    public String getMetodo_java() {
        return metodo_java;
    }

    public void setMetodo_java(String metodo_java) {
        this.metodo_java = metodo_java;
    }

    public long getNid_error() {
        return nid_error;
    }

    public void setNid_error(long nid_error) {
        this.nid_error = nid_error;
    }

    public int getNid_log() {
        return nid_log;
    }

    public void setNid_log(int nid_log) {
        this.nid_log = nid_log;
    }

    public String getStacktrace() {
        return stacktrace;
    }

    public void setStacktrace(String stacktrace) {
        this.stacktrace = stacktrace;
    }

    public String getTipo_error() {
        return tipo_error;
    }

    public void setTipo_error(String tipo_error) {
        this.tipo_error = tipo_error;
    }
}
