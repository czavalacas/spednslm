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
    private double resultado;
    private String colorResultado;
    private String nombreEvaluador;
    private String nombrePLanificador;
    private String apellidosDocentes;
    private String tipoVisita;
    private Date startDate;      
    private BeanMain main;
    private Integer nidPlanificador;       
    private List<BeanResultado> resultadoLista;
    private String comentarioEvaluador;
    private int nidProblema;
    private String comentario_evaluador; 
    private String comentario_profesor;
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
    private String estadoUsuario;
    private List<BeanResultadoCriterio> resultadoCriterioList;
    //valores para el desempeno evaluador
    private Integer cantEjecutado;
    private Integer cantPendiente;
    private Integer cantNoEjecutado;
    private Integer cantJustificado; //antes cantNoJEjecutado
    private Integer cantPorJustificar;
    private Integer cantInjustificado;
    private Integer cantProblema;
    private String descProblema;
    private String descRol;
    private double porcentajeDesempeno;
    private BeanUsuario usuario;
    //dfloresgonz 04.05.2014 agregado para notificaciones
    private String notificacionEvaluadorComentarioProfesor;
    //dfloresgonz 21.05.2014 agregado para la nueva logica de estados
    private String flgEvaluar;
    private String flgAnular;
    private String flgJustificar;
    private String styleColor;
    private String flgParcial;//Cuando se grabo una evaluacion parcialmente, aun no se termina
    private Integer nidRol;//Usado para el metodo BDL_C_SFEvaluacionBean.getPlanificaciones
    private Integer nidAreaUsuario;
    private String temaEvaluacion;
    //dfloresgonz 18.06.2014 nuevo filtro para que evas de area y subs puedan verse las evaluaciones
    private String tipEvaFiltro;

    public void setTipEvaFiltro(String tipEvaFiltro) {
        this.tipEvaFiltro = tipEvaFiltro;
    }

    public String getTipEvaFiltro() {
        return tipEvaFiltro;
    }

    public void setTemaEvaluacion(String temaEvaluacion) {
        this.temaEvaluacion = temaEvaluacion;
    }

    public String getTemaEvaluacion() {
        return temaEvaluacion;
    }

    public void setNidAreaUsuario(Integer nidAreaUsuario) {
        this.nidAreaUsuario = nidAreaUsuario;
    }

    public Integer getNidAreaUsuario() {
        return nidAreaUsuario;
    }

    public void setNidRol(Integer nidRol) {
        this.nidRol = nidRol;
    }

    public Integer getNidRol() {
        return nidRol;
    }

    public void setFlgParcial(String flgParcial) {
        this.flgParcial = flgParcial;
    }

    public String getFlgParcial() {
        return flgParcial;
    }

    public void setStyleColor(String styleColor) {
        this.styleColor = styleColor;
    }

    public String getStyleColor() {
        return styleColor;
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

    public void setFlgJustificar(String flgJustificar) {
        this.flgJustificar = flgJustificar;
    }

    public String getFlgJustificar() {
        return flgJustificar;
    }

    public void setNotificacionEvaluadorComentarioProfesor(String notificacionEvaluadorComentarioProfesor) {
        this.notificacionEvaluadorComentarioProfesor = notificacionEvaluadorComentarioProfesor;
    }

    public String getNotificacionEvaluadorComentarioProfesor() {
        return notificacionEvaluadorComentarioProfesor;
    }

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

    public void setResultado(double resultado) {
        this.resultado = resultado;
    }

    public double getResultado() {
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

    public void setTipoVisita(String tipoVisita) {
        this.tipoVisita = tipoVisita;
    }

    public String getTipoVisita() {
        return tipoVisita;
    }

    public void setComentarioEvaluador(String comentarioEvaluador) {
        this.comentarioEvaluador = comentarioEvaluador;
    }

    public String getComentarioEvaluador() {
        return comentarioEvaluador;
    }

    public void setResultadoCriterioList(List<BeanResultadoCriterio> resultadoCriterioList) {
        this.resultadoCriterioList = resultadoCriterioList;
    }

    public List<BeanResultadoCriterio> getResultadoCriterioList() {
        return resultadoCriterioList;
    }

    public void setColorResultado(String colorResultado) {
        this.colorResultado = colorResultado;
    }

    public String getColorResultado() {
        return colorResultado;
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

    public void setNidProblema(int nidProblema) {
        this.nidProblema = nidProblema;
    }

    public int getNidProblema() {
        return nidProblema;
    }

    public void setCantProblema(Integer cantProblema) {
        this.cantProblema = cantProblema;
    }

    public Integer getCantProblema() {
        return cantProblema;
    }

    public void setDescProblema(String descProblema) {
        this.descProblema = descProblema;
    }

    public String getDescProblema() {
        return descProblema;
    }

    public void setUsuario(BeanUsuario usuario) {
        this.usuario = usuario;
    }

    public BeanUsuario getUsuario() {
        return usuario;
    }

    public void setDescRol(String descRol) {
        this.descRol = descRol;
    }

    public String getDescRol() {
        return descRol;
    }

    public void setPorcentajeDesempeno(double porcentajeDesempeno) {
        this.porcentajeDesempeno = porcentajeDesempeno;
    }

    public double getPorcentajeDesempeno() {
        return porcentajeDesempeno;
    }

    public void setComentario_evaluador(String comentario_evaluador) {
        this.comentario_evaluador = comentario_evaluador;
    }

    public String getComentario_evaluador() {
        return comentario_evaluador;
    }

    public void setComentario_profesor(String comentario_profesor) {
        this.comentario_profesor = comentario_profesor;
    }

    public String getComentario_profesor() {
        return comentario_profesor;
    }

    public void setEstadoUsuario(String estadoUsuario) {
        this.estadoUsuario = estadoUsuario;
    }

    public String getEstadoUsuario() {
        return estadoUsuario;
    }

}
