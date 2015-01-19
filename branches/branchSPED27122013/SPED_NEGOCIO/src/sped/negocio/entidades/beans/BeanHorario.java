package sped.negocio.entidades.beans;

import java.io.Serializable;

import java.sql.Time;

import java.util.List;

public class BeanHorario implements Serializable {
    @SuppressWarnings("compatibility:-7442992646229351967")
    private static final long serialVersionUID = 1L;

    private int posicion;
    private String titulo;
    private String codigo;
    private String color;
    private BeanMain horario[][];
    private List<BeanDia> lstDias;
    private int horasLibres;
    private int horasLibres_aux;

    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }

    public int getPosicion() {
        return posicion;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setHorario(BeanMain[][] horario) {
        this.horario = horario;
    }

    public BeanMain[][] getHorario() {
        return horario;
    }

    public void setLstDias(List<BeanDia> lstDias) {
        this.lstDias = lstDias;
    }

    public List<BeanDia> getLstDias() {
        return lstDias;
    }

    public void setHorasLibres(int horasLibres) {
        this.horasLibres = horasLibres;
    }

    public int getHorasLibres() {
        return horasLibres;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public void setHorasLibres_aux(int horasLibres_aux) {
        this.horasLibres_aux = horasLibres_aux;
    }

    public int getHorasLibres_aux() {
        return horasLibres_aux;
    }

}
