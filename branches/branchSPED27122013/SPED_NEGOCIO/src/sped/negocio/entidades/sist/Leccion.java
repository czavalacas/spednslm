package sped.negocio.entidades.sist;

import java.io.Serializable;

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

import sped.negocio.entidades.admin.Aula;
import sped.negocio.entidades.admin.Curso;
import sped.negocio.entidades.admin.Profesor;

@Entity
@NamedQueries({ @NamedQuery(name = "Leccion.findAll", query = "select o from Leccion o") })
@Table(name = "\"stmlecc\"")
public class Leccion implements Serializable {
    @SuppressWarnings("compatibility:-2091426971980888196")
    private static final long serialVersionUID = -2307714934842364273L;
    @ManyToOne
    @JoinColumn(name = "nidAula")
    private Aula aula;
    @ManyToOne
    @JoinColumn(name = "nidCurso")
    private Curso curso;
    @ManyToOne
    @JoinColumn(name = "nidDni")
    private Profesor profesor;    
    @Id
    @Column(name = "nidLecc", nullable = false)
    @TableGenerator( name = "stmcodi_stmlecc", table = "stmcodi", pkColumnName = "APP_SEQ_NAME", pkColumnValue = "stmlecc.nidLecc", valueColumnName = "APP_SEQ_VALUE", initialValue = 1, allocationSize = 1 )
    @GeneratedValue( strategy = GenerationType.TABLE, generator = "stmcodi_stmlecc" ) 
    private int nidLecc;
    @Column(name = "nroDuracion", nullable = false)
    private int nroDuracion;
    @Column(name = "nroHoras", nullable = false)
    private int nroHoras;
    @Column(name = "nroHoras_aux", nullable = false)
    private int nroHoras_aux;
    @Column(name = "estado", nullable = false)
    private String estado;

    public Leccion() {
    }

    /* public int getNidAula() {
        return nidAula;
    }

    public void setNidAula(int nidAula) {
        this.nidAula = nidAula;
    }

    public int getNidCurso() {
        return nidCurso;
    }

    public void setNidCurso(int nidCurso) {
        this.nidCurso = nidCurso;
    }

    public String getNidDni() {
        return nidDni;
    }

    public void setNidDni(String nidDni) {
        this.nidDni = nidDni;
    } */


    public void setAula(Aula aula) {
        this.aula = aula;
    }

    public Aula getAula() {
        return aula;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public int getNidLecc() {
        return nidLecc;
    }

    public void setNidLecc(int nidLecc) {
        this.nidLecc = nidLecc;
    }

    public int getNroDuracion() {
        return nroDuracion;
    }

    public void setNroDuracion(int nroDuracion) {
        this.nroDuracion = nroDuracion;
    }

    public int getNroHoras() {
        return nroHoras;
    }

    public void setNroHoras(int nroHoras) {
        this.nroHoras = nroHoras;
    }

    public int getNroHoras_aux() {
        return nroHoras_aux;
    }

    public void setNroHoras_aux(int nroHoras_aux) {
        this.nroHoras_aux = nroHoras_aux;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getEstado() {
        return estado;
    }
}
