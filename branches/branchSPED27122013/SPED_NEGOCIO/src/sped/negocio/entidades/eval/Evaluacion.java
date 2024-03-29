package sped.negocio.entidades.eval;

import java.io.Serializable;

import java.sql.Timestamp;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import javax.persistence.TableGenerator;

import sped.negocio.entidades.admin.Main;

@Entity
@NamedQueries({ @NamedQuery(name = "Evaluacion.findAll", query = "select o from Evaluacion o") })
@Table(name = "\"evmeval\"")
public class Evaluacion implements Serializable {
    private static final long serialVersionUID = 240657949826477782L;
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "end_Date", nullable = false)
    private Timestamp endDate;
    @Column(name = "estado_evaluacion")
    private String estadoEvaluacion;
    @Column(name = "nidDate", nullable = false)    
    private String nidDate;
    @Id
    @Column(name = "nidEvaluacion", nullable = false)
    @TableGenerator( name = "stmcodi", table = "stmcodi", pkColumnName = "APP_SEQ_NAME", pkColumnValue = "evmeval.nidEvaluacion", valueColumnName = "APP_SEQ_VALUE", initialValue = 50, allocationSize = 1 )
    @GeneratedValue( strategy = GenerationType.TABLE, generator = "stmcodi" )
    private int nidEvaluacion;
    @Column(name = "nid_evaluador", nullable = false)
    private int nidEvaluador;    
    @Column(name = "nid_planificador", nullable = false)
    private int nidPlanificador;
    @Column(name = "start_Date", nullable = false)
    private Timestamp startDate;
    @ManyToOne
    @JoinColumn(name = "nidMain")
    private Main main;
    @OneToMany(mappedBy = "evaluacion", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private List<Resultado> resultadoLista;
    @Column(name = "fecha_planificacion", nullable = false)
    private Timestamp fechaPlanificacion;
    @Column(name = "comentario")
    private String comentarioEvaluador;//Descripcion textual del evaluador cuando justifica, aqui detalla que problema le ocurrio este campo solo puede estar lleno junto con el nidProblema
    @OneToMany(mappedBy = "evaluacion", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private List<ResultadoCriterio> resultadoCriterioList;
    @Column(name = "tipo_visita", nullable = false)    
    private String tipoVisita;
    @Column(name = "nid_usuario_ws")    
    private Integer nid_usuario_ws;
    @Column(name = "nidLog")    
    private Integer nidLog;
    @Column(name = "nidProblema")
    private Integer nidProblema;
    @Column(name = "comentario_evaluador")  
    private String comentario_evaluador;//ESTE COMENTARIO SE INGRESA CUANDO SE EVALUA A UN PROFESOR
    @Column(name = "comentario_profesor")  
    private String comentario_profesor;
    @Column(name = "notif_evaluador_commtProf")
    private String notificacionEvaluadorComentarioProfesor;
    @Column(name = "fecha_evaluacion")
    private Timestamp fechaEvaluacion;
    @Column(name = "modo_evaluacion")
    private String modoEvaluacion;
    /** Nuevos Flags **/
    @Column(name = "flg_evaluar")
    private String flgEvaluar;
    @Column(name = "flg_anular")
    private String flgAnular;
    @Column(name = "flg_justificar")
    private String flgJustificar;
    @Column(name = "flg_parcial")
    private String flgParcial;//Cuando se grabo una evaluacion parcialmente, aun no se termina
    @Column(name = "tema")
    private String temaEvaluacion;
    
    public Evaluacion() {
    }

    public Evaluacion(String descripcion, Timestamp endDate, String estadoEvaluacion, String nidDate, int nidEvaluacion,
                      int nidEvaluador, int nidPlanificador, Main main, Timestamp startDate, Timestamp fechaPlanificacion, String tipoVisita,
                      String comentarioEvaluador, Timestamp fechaEvaluacion) {

        this.descripcion = descripcion;
        this.endDate = endDate;
        this.estadoEvaluacion = estadoEvaluacion;
        this.nidDate = nidDate;
        this.nidEvaluacion = nidEvaluacion;
        this.nidEvaluador = nidEvaluador;
        this.nidPlanificador = nidPlanificador;
        this.main = main;
        this.startDate = startDate;
        this.fechaPlanificacion = fechaPlanificacion;
        this.tipoVisita=tipoVisita;
        this.comentarioEvaluador = comentarioEvaluador;
        this.fechaEvaluacion = fechaEvaluacion;
    }

    public void setTemaEvaluacion(String temaEvaluacion) {
        this.temaEvaluacion = temaEvaluacion;
    }

    public String getTemaEvaluacion() {
        return temaEvaluacion;
    }

    public void setFlgParcial(String flgParcial) {
        this.flgParcial = flgParcial;
    }

    public String getFlgParcial() {
        return flgParcial;
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

    public void setModoEvaluacion(String modoEvaluacion) {
        this.modoEvaluacion = modoEvaluacion;
    }

    public String getModoEvaluacion() {
        return modoEvaluacion;
    }

    public void setFechaEvaluacion(Timestamp fechaEvaluacion) {
        this.fechaEvaluacion = fechaEvaluacion;
    }

    public Timestamp getFechaEvaluacion() {
        return fechaEvaluacion;
    }

    public void setNotificacionEvaluadorComentarioProfesor(String notificacionEvaluadorComentarioProfesor) {
        this.notificacionEvaluadorComentarioProfesor = notificacionEvaluadorComentarioProfesor;
    }

    public String getNotificacionEvaluadorComentarioProfesor() {
        return notificacionEvaluadorComentarioProfesor;
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

    public void setNidLog(Integer nidLog) {
        this.nidLog = nidLog;
    }

    public Integer getNidLog() {
        return nidLog;
    }

    public void setNid_usuario_ws(Integer nid_usuario_ws) {
        this.nid_usuario_ws = nid_usuario_ws;
    }

    public Integer getNid_usuario_ws() {
        return nid_usuario_ws;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Timestamp getEndDate() {
        return endDate;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }

    public String getEstadoEvaluacion() {
        return estadoEvaluacion;
    }

    public void setEstadoEvaluacion(String estadoEvaluacion) {
        this.estadoEvaluacion = estadoEvaluacion;
    }

    public String getNidDate() {
        return nidDate;
    }

    public void setNidDate(String nidDate) {
        this.nidDate = nidDate;
    }

    public int getNidEvaluacion() {
        return nidEvaluacion;
    }

    public void setNidEvaluacion(int nidEvaluacion) {
        this.nidEvaluacion = nidEvaluacion;
    }

    public int getNidEvaluador() {
        return nidEvaluador;
    }

    public void setNidEvaluador(int nidEvaluador) {
        this.nidEvaluador = nidEvaluador;
    }


    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public List<Resultado> getResultadoLista() {
        return resultadoLista;
    }

    public void setResultadoLista(List<Resultado> resultadoLista) {
        this.resultadoLista = resultadoLista;
    }

    public void setNidPlanificador(int nidPlanificador) {
        this.nidPlanificador = nidPlanificador;
    }

    public int getNidPlanificador() {
        return nidPlanificador;
    }

    public void setFechaPlanificacion(Timestamp fechaPlanificacion) {
        this.fechaPlanificacion = fechaPlanificacion;
    }

    public Timestamp getFechaPlanificacion() {
        return fechaPlanificacion;
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

    public void setResultadoCriterioList(List<ResultadoCriterio> resultadoCriterioList) {
        this.resultadoCriterioList = resultadoCriterioList;
    }

    public List<ResultadoCriterio> getResultadoCriterioList() {
        return resultadoCriterioList;
    }


    public Resultado addResultado(Resultado resultado) {
        getResultadoLista().add(resultado);
        resultado.setEvaluacion(this);
        return resultado;
    }

    public Resultado removeResultado(Resultado resultado) {
        getResultadoLista().remove(resultado);
        resultado.setEvaluacion(null);
        return resultado;
    }

    public void setNidProblema(Integer nidProblema) {
        this.nidProblema = nidProblema;
    }

    public Integer getNidProblema() {
        return nidProblema;
    }

    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append(getClass().getName() + "@" + Integer.toHexString(hashCode()));
        buffer.append('[');
        buffer.append("descripcion=");
        buffer.append(getDescripcion());
        buffer.append(',');
        buffer.append("endDate=");
        buffer.append(getEndDate());
        buffer.append(',');
        buffer.append("estadoEvaluacion=");
        buffer.append(getEstadoEvaluacion());
        buffer.append(',');
        buffer.append("nidDate=");
        buffer.append(getNidDate());
        buffer.append(',');
        buffer.append("nidEvaluacion=");
        buffer.append(getNidEvaluacion());
        buffer.append(',');
        buffer.append("nidEvaluador=");
        buffer.append(getNidEvaluador());
        buffer.append(',');
        buffer.append("startDate=");
        buffer.append(getStartDate());
        buffer.append(']');
        return buffer.toString();
    }
}
