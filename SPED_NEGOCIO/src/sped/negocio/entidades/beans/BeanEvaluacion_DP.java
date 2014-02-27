package sped.negocio.entidades.beans;

import java.io.Serializable;

import java.util.Date;
import java.util.List;

import sped.negocio.entidades.eval.Resultado;

public class BeanEvaluacion_DP implements Serializable {
    @SuppressWarnings("compatibility:6355159031492367751")
    private static final long serialVersionUID = 1L;
    
    private Integer nidEvaluacion;    
    private Integer nidEvaluador;
    private String profesor;
    private String evaluador;
    private String planificador;
    private String curso;
    private Date startDate;
    private String sede;
    private String areaAcademica;
    private String tipoVisita;
    private Date endDate;
    private String aula;
    private Double notaFinal;
    //AUXILIARES PARA LOS GRAFICOS EN EL MOVIL
    private double notaMin;
    private double notaProm;
    private double notaMax;
    private String ceroDiez;
    private String onceQuince;
    private String resto;
    private int ceroDiezCant;
    private int onceQuinceCant;
    private int restoCant;
    //AUXILIARES PARA CONSULTAR EVALUACION INDIVIDUAL
    private String valores;
    private String comentarioEvaluador;
    private String comentarioProfesor;
    private BeanCriterioWS[] criterios;
    private Double NotaFinalIndicador;
    private List<BeanResultado> resultadoLista;

    public void setResultadoLista(List<BeanResultado> resultadoLista) {
        this.resultadoLista = resultadoLista;
    }

    public List<BeanResultado> getResultadoLista() {
        return resultadoLista;
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

    public void setProfesor(String profesor) {
        this.profesor = profesor;
    }

    public String getProfesor() {
        return profesor;
    }

    public void setEvaluador(String evaluador) {
        this.evaluador = evaluador;
    }

    public String getEvaluador() {
        return evaluador;
    }

    public void setPlanificador(String planificador) {
        this.planificador = planificador;
    }

    public String getPlanificador() {
        return planificador;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getCurso() {
        return curso;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setSede(String sede) {
        this.sede = sede;
    }

    public String getSede() {
        return sede;
    }

    public void setAreaAcademica(String areaAcademica) {
        this.areaAcademica = areaAcademica;
    }

    public String getAreaAcademica() {
        return areaAcademica;
    }

    public void setTipoVisita(String tipoVisita) {
        this.tipoVisita = tipoVisita;
    }

    public String getTipoVisita() {
        return tipoVisita;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setAula(String aula) {
        this.aula = aula;
    }

    public String getAula() {
        return aula;
    }

    public void setNotaFinal(Double notaFinal) {
        this.notaFinal = notaFinal;
    }

    public Double getNotaFinal() {
        return notaFinal;
    }

    public void setNotaMin(double notaMin) {
        this.notaMin = notaMin;
    }

    public double getNotaMin() {
        return notaMin;
    }

    public void setNotaProm(double notaProm) {
        this.notaProm = notaProm;
    }

    public double getNotaProm() {
        return notaProm;
    }

    public void setNotaMax(double notaMax) {
        this.notaMax = notaMax;
    }

    public double getNotaMax() {
        return notaMax;
    }

    public void setCeroDiez(String ceroDiez) {
        this.ceroDiez = ceroDiez;
    }

    public String getCeroDiez() {
        return ceroDiez;
    }

    public void setOnceQuince(String onceQuince) {
        this.onceQuince = onceQuince;
    }

    public String getOnceQuince() {
        return onceQuince;
    }

    public void setResto(String resto) {
        this.resto = resto;
    }

    public String getResto() {
        return resto;
    }

    public void setCeroDiezCant(int ceroDiezCant) {
        this.ceroDiezCant = ceroDiezCant;
    }

    public int getCeroDiezCant() {
        return ceroDiezCant;
    }

    public void setOnceQuinceCant(int onceQuinceCant) {
        this.onceQuinceCant = onceQuinceCant;
    }

    public int getOnceQuinceCant() {
        return onceQuinceCant;
    }

    public void setRestoCant(int restoCant) {
        this.restoCant = restoCant;
    }

    public int getRestoCant() {
        return restoCant;
    }

    public void setValores(String valores) {
        this.valores = valores;
    }

    public String getValores() {
        return valores;
    }

    public void setComentarioEvaluador(String comentarioEvaluador) {
        this.comentarioEvaluador = comentarioEvaluador;
    }

    public String getComentarioEvaluador() {
        return comentarioEvaluador;
    }

    public void setComentarioProfesor(String comentarioProfesor) {
        this.comentarioProfesor = comentarioProfesor;
    }

    public String getComentarioProfesor() {
        return comentarioProfesor;
    }

    public void setCriterios(BeanCriterioWS[] criterios) {
        this.criterios = criterios;
    }

    public BeanCriterioWS[] getCriterios() {
        return criterios;
    }

    public void setNotaFinalIndicador(Double NotaFinalIndicador) {
        this.NotaFinalIndicador = NotaFinalIndicador;
    }

    public Double getNotaFinalIndicador() {
        return NotaFinalIndicador;
    }
}
