package sped.negocio.entidades.beans;

import java.io.Serializable;

import java.util.Date;
import java.util.List;

public class BeanEvaluacion implements Serializable {
    @SuppressWarnings("compatibility:-9034418511481595866")
    private static final long serialVersionUID = 1L;
    private String descripcion;    
        private Date endDate;    
        private String estadoEvaluacion; 
        private String nidDate;
        private int nidEvaluacion;    
        private int nidEvaluador;
        private String resultado;
        private String nombreEvaluador;
        private String nombrePLanificador;
        private Date startDate;      
        private BeanMain main;
        private int nidPlanificador;       
        private List<BeanResultado> resultadoLista;
        ///atributos para la busqueda filtro///
        private int nidSede;
        private int nidNivel;
        private int nidArea;
        private int nidCurso;
        private Date fechaPlanificacion; 
        private Date fechaMinEvaluacion;
        private Date fechaMaxEvaluacion;
        private Date fechaMinPlanificacion;
        private Date fechaMaxPlanificacion;

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public void setFechaPlanificacion(Date fechaPlanificacion) {
        this.fechaPlanificacion = fechaPlanificacion;
    }

    public Date getFechaPlanificacion() {
        return fechaPlanificacion;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEstadoEvaluacion(String estadoEvaluacion) {
        this.estadoEvaluacion = estadoEvaluacion;
    }

    public String getEstadoEvaluacion() {
        return estadoEvaluacion;
    }

    public void setNidDate(String nidDate) {
        this.nidDate = nidDate;
    }

    public String getNidDate() {
        return nidDate;
    }

    public void setNidEvaluacion(int nidEvaluacion) {
        this.nidEvaluacion = nidEvaluacion;
    }

    public int getNidEvaluacion() {
        return nidEvaluacion;
    }

    public void setNidEvaluador(int nidEvaluador) {
        this.nidEvaluador = nidEvaluador;
    }

    public int getNidEvaluador() {
        return nidEvaluador;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public String getResultado() {
        return resultado;
    }

    public void setNombreEvaluador(String nombreEvaluador) {
        this.nombreEvaluador = nombreEvaluador;
    }

    public String getNombreEvaluador() {
        return nombreEvaluador;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setMain(BeanMain main) {
        this.main = main;
    }

    public BeanMain getMain() {
        return main;
    }

    public void setResultadoLista(List<BeanResultado> resultadoLista) {
        this.resultadoLista = resultadoLista;
    }

    public List<BeanResultado> getResultadoLista() {
        return resultadoLista;
    }

    public void setNidPlanificador(int nidPlanificador) {
        this.nidPlanificador = nidPlanificador;
    }

    public int getNidPlanificador() {
        return nidPlanificador;
    }

    public void setNidSede(int nidSede) {
        this.nidSede = nidSede;
    }

    public int getNidSede() {
        return nidSede;
    }

    public void setNidNivel(int nidNivel) {
        this.nidNivel = nidNivel;
    }

    public int getNidNivel() {
        return nidNivel;
    }

    public void setNidArea(int nidArea) {
        this.nidArea = nidArea;
    }

    public int getNidArea() {
        return nidArea;
    }

    public void setNidCurso(int nidCurso) {
        this.nidCurso = nidCurso;
    }

    public int getNidCurso() {
        return nidCurso;
    }

    public void setNombrePLanificador(String nombrePLanificador) {
        this.nombrePLanificador = nombrePLanificador;
    }

    public String getNombrePLanificador() {
        return nombrePLanificador;
    }

    public void setFechaMinEvaluacion(Date fechaMinEvaluacion) {
        this.fechaMinEvaluacion = fechaMinEvaluacion;
    }

    public Date getFechaMinEvaluacion() {
        return fechaMinEvaluacion;
    }

    public void setFechaMaxEvaluacion(Date fechaMaxEvaluacion) {
        this.fechaMaxEvaluacion = fechaMaxEvaluacion;
    }

    public Date getFechaMaxEvaluacion() {
        return fechaMaxEvaluacion;
    }

    public void setFechaMinPlanificacion(Date fechaMinPlanificacion) {
        this.fechaMinPlanificacion = fechaMinPlanificacion;
    }

    public Date getFechaMinPlanificacion() {
        return fechaMinPlanificacion;
    }

    public void setFechaMaxPlanificacion(Date fechaMaxPlanificacion) {
        this.fechaMaxPlanificacion = fechaMaxPlanificacion;
    }

    public Date getFechaMaxPlanificacion() {
        return fechaMaxPlanificacion;
    }

}
