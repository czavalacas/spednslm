package sped.negocio.entidades.beans;

import java.io.Serializable;

import java.util.Date;

public class BeanEvaluacionPlani implements Serializable{
    @SuppressWarnings("compatibility:8618753358761845928")
    private static final long serialVersionUID = 1L;
    
    private String descripcion; 
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
    ///////////////////////////////////////////////////////////////////////////
    private double resultado;
    private String colorResultado;
    private String descProblema;
    private String descArea;
    private String dniDocente;
    private String descRoPlanificador;
    private String comentarioProfesor;
    private String descAula;
    private Integer nidPlanificador;
    private String tipoVisita;
    private String flgEvaluar;
    private String flgAnular;
    private String notificacionEvaluadorComentarioProfesor;
    private Integer cantEjecutado;
    private Integer cantPendiente;
    private Integer cantNoEjecutado;
    private Integer cantJustificado; //antes cantNoJEjecutado
    private Integer cantPorJustificar;
    private Integer cantInjustificado;
    private Integer cantProblema;
    private BeanUsuario usuario;
    private double porcentajeDesempeno;

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

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

    public void setDniDocente(String dniDocente) {
        this.dniDocente = dniDocente;
}

    public String getDniDocente() {
        return dniDocente;
    }

    public void setDescRoPlanificador(String descRoPlanificador) {
        this.descRoPlanificador = descRoPlanificador;
    }

    public String getDescRoPlanificador() {
        return descRoPlanificador;
    }

    public void setComentarioProfesor(String comentarioProfesor) {
        this.comentarioProfesor = comentarioProfesor;
    }

    public String getComentarioProfesor() {
        return comentarioProfesor;
    }

    public void setDescAula(String descAula) {
        this.descAula = descAula;
    }

    public String getDescAula() {
        return descAula;
    }

    public void setNidPlanificador(Integer nidPlanificador) {
        this.nidPlanificador = nidPlanificador;
    }

    public Integer getNidPlanificador() {
        return nidPlanificador;
    }

    public void setTipoVisita(String tipoVisita) {
        this.tipoVisita = tipoVisita;
    }

    public String getTipoVisita() {
        return tipoVisita;
    }

    public void setFlgEvaluar(String flgEvaluar) {
        this.flgEvaluar = flgEvaluar;
    }

    public String getFlgEvaluar() {
        return flgEvaluar;
    }

    public void setFlgAnular(String flgAnular) {
        this.flgAnular = flgAnular;
    }

    public String getFlgAnular() {
        return flgAnular;
    }

    public void setNotificacionEvaluadorComentarioProfesor(String notificacionEvaluadorComentarioProfesor) {
        this.notificacionEvaluadorComentarioProfesor = notificacionEvaluadorComentarioProfesor;
    }

    public String getNotificacionEvaluadorComentarioProfesor() {
        return notificacionEvaluadorComentarioProfesor;
    }

    public void setCantEjecutado(Integer cantEjecutado) {
        this.cantEjecutado = cantEjecutado;
    }

    public Integer getCantEjecutado() {
        return cantEjecutado;
    }

    public void setCantPendiente(Integer cantPendiente) {
        this.cantPendiente = cantPendiente;
    }

    public Integer getCantPendiente() {
        return cantPendiente;
    }

    public void setCantNoEjecutado(Integer cantNoEjecutado) {
        this.cantNoEjecutado = cantNoEjecutado;
    }

    public Integer getCantNoEjecutado() {
        return cantNoEjecutado;
    }

    public void setCantJustificado(Integer cantJustificado) {
        this.cantJustificado = cantJustificado;
    }

    public Integer getCantJustificado() {
        return cantJustificado;
    }

    public void setCantPorJustificar(Integer cantPorJustificar) {
        this.cantPorJustificar = cantPorJustificar;
    }

    public Integer getCantPorJustificar() {
        return cantPorJustificar;
    }

    public void setCantInjustificado(Integer cantInjustificado) {
        this.cantInjustificado = cantInjustificado;
    }

    public Integer getCantInjustificado() {
        return cantInjustificado;
    }

    public void setUsuario(BeanUsuario usuario) {
        this.usuario = usuario;
    }

    public BeanUsuario getUsuario() {
        return usuario;
    }

    public void setPorcentajeDesempeno(double porcentajeDesempeno) {
        this.porcentajeDesempeno = porcentajeDesempeno;
    }

    public double getPorcentajeDesempeno() {
        return porcentajeDesempeno;
    }

    public void setCantProblema(Integer cantProblema) {
        this.cantProblema = cantProblema;
    }

    public Integer getCantProblema() {
        return cantProblema;
    }
}
