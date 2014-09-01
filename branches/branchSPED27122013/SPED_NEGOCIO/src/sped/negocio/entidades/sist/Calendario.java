package sped.negocio.entidades.sist;

import java.io.Serializable;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "\"calendario\"")
public class Calendario implements Serializable {
    private static final long serialVersionUID = 1824808468806902780L;
    @Column(name = "cuartil")
    private int cuartil;
    @Column(name = "descripcionDia")
    private String descripcionDia;
    @Column(name = "diaNombre")
    private String diaNombre;
    @Column(name = "dia")
    private int diaNumero;
    @Column(name = "diaSemana")
    private int diaSemanaNumero;
    @Column(name = "esDiaSemana")
    private int esDiaSemana;
    @Column(name = "esFeriado")
    private int esFeriado;
    @Column(name = "esLaborable", nullable = false)
    private int esLaborable;
    @Column(name = "estado", nullable = false)
    private String estado;
    @Column(name = "mesNombre")
    private String mesNombre;
    @Column(name = "mes")
    private int mesNumero;
    @Id
    @Temporal(TemporalType.DATE)
    @Column(name = "pk_fecha", nullable = false)
    private Date nidFecha;
    @Column(name = "semana")
    private int semana;
    @Column(name = "year")
    private int year;
    @Column(name = "estilo")
    private String estilo;

    public Calendario() {
    }

    public Calendario(int cuartil, String descripcionDia, String diaNombre, int diaNumero, int diaSemanaNumero,
                      int esDiaSemana, int esFeriado, int esLaborable, String estado, String mesNombre, int mesNumero,
                      Date nidFecha, int semana, int year,String estilo) {
        this.cuartil = cuartil;
        this.descripcionDia = descripcionDia;
        this.diaNombre = diaNombre;
        this.diaNumero = diaNumero;
        this.diaSemanaNumero = diaSemanaNumero;
        this.esDiaSemana = esDiaSemana;
        this.esFeriado = esFeriado;
        this.esLaborable = esLaborable;
        this.estado = estado;
        this.mesNombre = mesNombre;
        this.mesNumero = mesNumero;
        this.nidFecha = nidFecha;
        this.semana = semana;
        this.year = year;
        this.estilo = estilo;
    }

    public void setEstilo(String estilo) {
        this.estilo = estilo;
    }

    public String getEstilo() {
        return estilo;
    }

    public int getCuartil() {
        return cuartil;
    }

    public void setCuartil(int cuartil) {
        this.cuartil = cuartil;
    }

    public String getDescripcionDia() {
        return descripcionDia;
    }

    public void setDescripcionDia(String descripcionDia) {
        this.descripcionDia = descripcionDia;
    }

    public String getDiaNombre() {
        return diaNombre;
    }

    public void setDiaNombre(String diaNombre) {
        this.diaNombre = diaNombre;
    }

    public int getDiaNumero() {
        return diaNumero;
    }

    public void setDiaNumero(int diaNumero) {
        this.diaNumero = diaNumero;
    }

    public int getDiaSemanaNumero() {
        return diaSemanaNumero;
    }

    public void setDiaSemanaNumero(int diaSemanaNumero) {
        this.diaSemanaNumero = diaSemanaNumero;
    }

    public int getEsDiaSemana() {
        return esDiaSemana;
    }

    public void setEsDiaSemana(int esDiaSemana) {
        this.esDiaSemana = esDiaSemana;
    }

    public int getEsFeriado() {
        return esFeriado;
    }

    public void setEsFeriado(int esFeriado) {
        this.esFeriado = esFeriado;
    }

    public int getEsLaborable() {
        return esLaborable;
    }

    public void setEsLaborable(int esLaborable) {
        this.esLaborable = esLaborable;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getMesNombre() {
        return mesNombre;
    }

    public void setMesNombre(String mesNombre) {
        this.mesNombre = mesNombre;
    }

    public int getMesNumero() {
        return mesNumero;
    }

    public void setMesNumero(int mesNumero) {
        this.mesNumero = mesNumero;
    }

    public Date getNidFecha() {
        return nidFecha;
    }

    public void setNidFecha(Date nidFecha) {
        this.nidFecha = nidFecha;
    }

    public int getSemana() {
        return semana;
    }

    public void setSemana(int semana) {
        this.semana = semana;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
