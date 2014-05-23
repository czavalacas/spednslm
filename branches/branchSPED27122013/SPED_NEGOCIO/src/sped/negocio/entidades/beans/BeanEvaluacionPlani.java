package sped.negocio.entidades.beans;

import java.io.Serializable;

import java.util.Date;

public class BeanEvaluacionPlani implements Serializable{
    @SuppressWarnings("compatibility:8618753358761845928")
    private static final long serialVersionUID = 1L;
    
    private Date startDate;
    private Date endDate; 
    private Date fechaPlanificacion;     
    private String flgParcial;
    private String nombreEvaluador;
    private String nombrePLanificador;
    private String nombreCompletoProfesor;
    private String descCurso;
    private String descGrado;
    private String descNivel;
    private String descSede;
    private String styleColor;
    private String nidEstadoEvaluacion;
    private String estadoEvaluacion; 
    private Integer nidEvaluacion; 
    private String comentarioEvaluador; 
    private int nidProblema;
    private String flgJustificar;
    private Integer nidEvaluador;
    private String comentarioProblema;
    
    private double resultado;
    private String colorResultado;
    private String descProblema;
    private String descArea;
    private String descAula;
    private String notificacionEvaluadorComentarioProfesor;
    private String comentarioProfesor;
    
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setNidEstadoEvaluacion(String nidEstadoEvaluacion) {
        this.nidEstadoEvaluacion = nidEstadoEvaluacion;
    }

    public String getNidEstadoEvaluacion() {
        return nidEstadoEvaluacion;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setFechaPlanificacion(Date fechaPlanificacion) {
        this.fechaPlanificacion = fechaPlanificacion;
    }

    public Date getFechaPlanificacion() {
        return fechaPlanificacion;
    }

    public void setFlgParcial(String flgParcial) {
        this.flgParcial = flgParcial;
    }

    public String getFlgParcial() {
        return flgParcial;
    }

    public void setNombreEvaluador(String nombreEvaluador) {
        this.nombreEvaluador = nombreEvaluador;
    }

    public String getNombreEvaluador() {
        return nombreEvaluador;
    }

    public void setNombrePLanificador(String nombrePLanificador) {
        this.nombrePLanificador = nombrePLanificador;
    }

    public String getNombrePLanificador() {
        return nombrePLanificador;
    }

    public void setNombreCompletoProfesor(String nombreCompletoProfesor) {
        this.nombreCompletoProfesor = nombreCompletoProfesor;
    }

    public String getNombreCompletoProfesor() {
        return nombreCompletoProfesor;
    }

    public void setDescCurso(String descCurso) {
        this.descCurso = descCurso;
    }

    public String getDescCurso() {
        return descCurso;
    }

    public void setDescGrado(String descGrado) {
        this.descGrado = descGrado;
    }

    public String getDescGrado() {
        return descGrado;
    }

    public void setDescNivel(String descNivel) {
        this.descNivel = descNivel;
    }

    public String getDescNivel() {
        return descNivel;
    }

    public void setDescSede(String descSede) {
        this.descSede = descSede;
    }

    public String getDescSede() {
        return descSede;
    }

    public void setStyleColor(String styleColor) {
        this.styleColor = styleColor;
    }

    public String getStyleColor() {
        return styleColor;
    }

    public void setEstadoEvaluacion(String estadoEvaluacion) {
        this.estadoEvaluacion = estadoEvaluacion;
    }

    public String getEstadoEvaluacion() {
        return estadoEvaluacion;
    }

    public void setNidEvaluacion(Integer nidEvaluacion) {
        this.nidEvaluacion = nidEvaluacion;
    }

    public Integer getNidEvaluacion() {
        return nidEvaluacion;
    }

    public void setComentarioEvaluador(String comentarioEvaluador) {
        this.comentarioEvaluador = comentarioEvaluador;
    }

    public String getComentarioEvaluador() {
        return comentarioEvaluador;
    }

    public void setNidProblema(int nidProblema) {
        this.nidProblema = nidProblema;
    }

    public int getNidProblema() {
        return nidProblema;
    }

    public void setFlgJustificar(String flgJustificar) {
        this.flgJustificar = flgJustificar;
    }

    public String getFlgJustificar() {
        return flgJustificar;
    }

    public void setNidEvaluador(Integer nidEvaluador) {
        this.nidEvaluador = nidEvaluador;
    }

    public Integer getNidEvaluador() {
        return nidEvaluador;
    }

    public void setResultado(double resultado) {
        this.resultado = resultado;
    }

    public double getResultado() {
        return resultado;
    }

    public void setColorResultado(String colorResultado) {
        this.colorResultado = colorResultado;
    }

    public String getColorResultado() {
        return colorResultado;
    }

    public void setDescProblema(String descProblema) {
        this.descProblema = descProblema;
    }

    public String getDescProblema() {
        return descProblema;
    }

    public void setComentarioProblema(String comentarioProblema) {
        this.comentarioProblema = comentarioProblema;
    }

    public String getComentarioProblema() {
        return comentarioProblema;
    }

    public void setDescArea(String descArea) {
        this.descArea = descArea;
    }

    public String getDescArea() {
        return descArea;
    }

    public void setDescAula(String descAula) {
        this.descAula = descAula;
    }

    public String getDescAula() {
        return descAula;
    }

    public void setNotificacionEvaluadorComentarioProfesor(String notificacionEvaluadorComentarioProfesor) {
        this.notificacionEvaluadorComentarioProfesor = notificacionEvaluadorComentarioProfesor;
    }

    public String getNotificacionEvaluadorComentarioProfesor() {
        return notificacionEvaluadorComentarioProfesor;
    }

    public void setComentarioProfesor(String comentarioProfesor) {
        this.comentarioProfesor = comentarioProfesor;
    }

    public String getComentarioProfesor() {
        return comentarioProfesor;
    }
}
