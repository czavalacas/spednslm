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
        private String nidEstadoEvaluacion;
        private String nidDate;
        private Integer nidEvaluacion;    
        private Integer nidEvaluador;
        private String resultado;
        private String nombreEvaluador;
        private String nombrePLanificador;
        private String apellidosDocentes;
        private Date startDate;      
        private BeanMain main;
        private Integer nidPlanificador;       
        private List<BeanResultado> resultadoLista;
        private String comentarioEvaluador;
        ///atributos para la busqueda filtro///
        private Integer nidSede;
        private Integer nidNivel;
        private Integer nidArea;
        private Integer nidCurso;
        private Integer nidGrado;
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

    public void setNidEvaluacion(Integer nidEvaluacion) {
        this.nidEvaluacion = nidEvaluacion;
    }

    public Integer getNidEvaluacion() {
        return nidEvaluacion;
    }

    public void setNidEvaluador(Integer nidEvaluador) {
        this.nidEvaluador = nidEvaluador;
    }

    public Integer getNidEvaluador() {
        return nidEvaluador;
    }

    public void setNidPlanificador(Integer nidPlanificador) {
        this.nidPlanificador = nidPlanificador;
    }

    public Integer getNidPlanificador() {
        return nidPlanificador;
    }

    public void setNidSede(Integer nidSede) {
        this.nidSede = nidSede;
    }

    public Integer getNidSede() {
        return nidSede;
    }

    public void setNidNivel(Integer nidNivel) {
        this.nidNivel = nidNivel;
    }

    public Integer getNidNivel() {
        return nidNivel;
    }

    public void setNidArea(Integer nidArea) {
        this.nidArea = nidArea;
    }

    public Integer getNidArea() {
        return nidArea;
    }

    public void setNidCurso(Integer nidCurso) {
        this.nidCurso = nidCurso;
    }

    public Integer getNidCurso() {
        return nidCurso;
    }

    public void setApellidosDocentes(String apellidosDocentes) {
        this.apellidosDocentes = apellidosDocentes;
    }

    public String getApellidosDocentes() {
        return apellidosDocentes;
    }

    public void setNidGrado(Integer nidGrado) {
        this.nidGrado = nidGrado;
}

    public Integer getNidGrado() {
        return nidGrado;
    }

    public void setNidEstadoEvaluacion(String nidEstadoEvaluacion) {
        this.nidEstadoEvaluacion = nidEstadoEvaluacion;
    }

    public String getNidEstadoEvaluacion() {
        return nidEstadoEvaluacion;
    }

    public void setComentarioEvaluador(String comentarioEvaluador) {
        this.comentarioEvaluador = comentarioEvaluador;
    }

    public String getComentarioEvaluador() {
        return comentarioEvaluador;
    }
}
