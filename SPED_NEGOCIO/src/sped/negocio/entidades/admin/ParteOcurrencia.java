package sped.negocio.entidades.admin;

import java.io.Serializable;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@NamedQueries({ @NamedQuery(name = "ParteOcurrencia.findAll", query = "select o from ParteOcurrencia o") })
@Table(name = "\"admpaoc\"")
public class ParteOcurrencia implements Serializable {
    private static final long serialVersionUID = 1369414763227231548L;
    @Column(name = "comentario")
    private String comentario;
    @Column(name = "fecha_registro", nullable = false)
    private Timestamp fechaRegistro;
    @Column(name = "nidMain", nullable = false)
    private int nidMain;
    @Id
    @Column(name = "nidParte", nullable = false)
    private long nidParte;
    @Column(name = "nidProblema", nullable = false)
    private int nidProblema;
    @Column(name = "nidUsuario", nullable = false)
    private int nidUsuario;

    public ParteOcurrencia() {
    }

    public ParteOcurrencia(String comentario, Timestamp fechaRegistro, int nidMain, long nidParte, int nidProblema,
                           int nidUsuario) {
        this.comentario = comentario;
        this.fechaRegistro = fechaRegistro;
        this.nidMain = nidMain;
        this.nidParte = nidParte;
        this.nidProblema = nidProblema;
        this.nidUsuario = nidUsuario;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Timestamp getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Timestamp fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public int getNidMain() {
        return nidMain;
    }

    public void setNidMain(int nidMain) {
        this.nidMain = nidMain;
    }

    public long getNidParte() {
        return nidParte;
    }

    public void setNidParte(long nidParte) {
        this.nidParte = nidParte;
    }

    public int getNidProblema() {
        return nidProblema;
    }

    public void setNidProblema(int nidProblema) {
        this.nidProblema = nidProblema;
    }

    public int getNidUsuario() {
        return nidUsuario;
    }

    public void setNidUsuario(int nidUsuario) {
        this.nidUsuario = nidUsuario;
    }
}
