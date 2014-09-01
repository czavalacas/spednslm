package sped.negocio.entidades.beans;

import java.io.Serializable;
import java.util.Date;

public class BeanCalendario implements Serializable {
    @SuppressWarnings("compatibility:3993178117218979793")
    private static final long serialVersionUID = 1L;
    
    private int cuartil;
    private String descripcionDia;
    private String diaNombre;
    private int diaNumero;
    private int diaSemanaNumero;
    private int esDiaSemana;
    private int esFeriado;
    private int esLaborable;
    private String estado;
    private String mesNombre;
    private int mesNumero;
    private Date nidFecha;
    private int semana;
    private int year;
    private String estilo;
    private String tipoFalta;//de usuarioCalendario
    
    public BeanCalendario() {
    }

    public BeanCalendario(int cuartil, String descripcionDia, String diaNombre, int diaNumero, int diaSemanaNumero,
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
    
    public BeanCalendario(int cuartil, String descripcionDia, String diaNombre, int diaNumero, int diaSemanaNumero,
                          int esDiaSemana, int esFeriado, int esLaborable, String estado, String mesNombre, int mesNumero,
                          Date nidFecha, int semana, int year,String estilo,String tipoFalta) {
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
        this.tipoFalta = tipoFalta;
    }

    public void setTipoFalta(String tipoFalta) {
        this.tipoFalta = tipoFalta;
    }

    public String getTipoFalta() {
        return tipoFalta;
    }

    public void setEstilo(String estilo) {
        this.estilo = estilo;
    }

    public String getEstilo() {
        return estilo;
    }

    public void setCuartil(int cuartil) {
        this.cuartil = cuartil;
    }

    public int getCuartil() {
        return cuartil;
    }

    public void setDescripcionDia(String descripcionDia) {
        this.descripcionDia = descripcionDia;
    }

    public String getDescripcionDia() {
        return descripcionDia;
    }

    public void setDiaNombre(String diaNombre) {
        this.diaNombre = diaNombre;
    }

    public String getDiaNombre() {
        return diaNombre;
    }

    public void setDiaNumero(int diaNumero) {
        this.diaNumero = diaNumero;
    }

    public int getDiaNumero() {
        return diaNumero;
    }

    public void setDiaSemanaNumero(int diaSemanaNumero) {
        this.diaSemanaNumero = diaSemanaNumero;
    }

    public int getDiaSemanaNumero() {
        return diaSemanaNumero;
    }

    public void setEsDiaSemana(int esDiaSemana) {
        this.esDiaSemana = esDiaSemana;
    }

    public int getEsDiaSemana() {
        return esDiaSemana;
    }

    public void setEsFeriado(int esFeriado) {
        this.esFeriado = esFeriado;
    }

    public int getEsFeriado() {
        return esFeriado;
    }

    public void setEsLaborable(int esLaborable) {
        this.esLaborable = esLaborable;
    }

    public int getEsLaborable() {
        return esLaborable;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getEstado() {
        return estado;
    }

    public void setMesNombre(String mesNombre) {
        this.mesNombre = mesNombre;
    }

    public String getMesNombre() {
        return mesNombre;
    }

    public void setMesNumero(int mesNumero) {
        this.mesNumero = mesNumero;
    }

    public int getMesNumero() {
        return mesNumero;
    }

    public void setNidFecha(Date nidFecha) {
        this.nidFecha = nidFecha;
    }

    public Date getNidFecha() {
        return nidFecha;
    }

    public void setSemana(int semana) {
        this.semana = semana;
    }

    public int getSemana() {
        return semana;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getYear() {
        return year;
    }
}
