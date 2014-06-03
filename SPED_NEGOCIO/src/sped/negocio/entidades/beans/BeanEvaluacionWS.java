package sped.negocio.entidades.beans;

import java.io.Serializable;

import java.util.Date;

public class BeanEvaluacionWS implements Serializable {
    @SuppressWarnings("compatibility:-5235138836280251799")
    private static final long serialVersionUID = 1L;

    private Integer nidEvaluacion;    
    private Integer nidEvaluador;
    private String profesor;
    private String evaluador;
    private String planificador;
    private String curso;
    private String tipoFichaCurso;
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
    private String comentarioEvaluador;//comentario ...Comentario del problema deberia ser
    private String comentarioProfesor;
    private BeanCriterioWS[] criterios;
    private String comentario_evaluador;//Comentario de la evaluacion
    private String flgParcial;//Cuando se grabo una evaluacion parcialmente, aun no se termina
    private String temaEvaluacion;

    public void setTemaEvaluacion(String temaEvaluacion) {
        this.temaEvaluacion = temaEvaluacion;
    }

    public String getTemaEvaluacion() {
        return temaEvaluacion;
    }
    
    public void setComentario_evaluador(String comentario_evaluador) {
        this.comentario_evaluador = comentario_evaluador;
    }

    public String getComentario_evaluador() {
        return comentario_evaluador;
    }

    public void setFlgParcial(String flgParcial) {
        this.flgParcial = flgParcial;
    }

    public String getFlgParcial() {
        return flgParcial;
    }

    public void setTipoFichaCurso(String tipoFichaCurso) {
        this.tipoFichaCurso = tipoFichaCurso;
    }

    public String getTipoFichaCurso() {
        return tipoFichaCurso;
    }

    public void setCriterios(BeanCriterioWS[] criterios) {
        this.criterios = criterios;
    }

    public BeanCriterioWS[] getCriterios() {
        return criterios;
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

    public void setAula(String aula) {
        this.aula = aula;
    }

    public String getAula() {
        return aula;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getEndDate() {
        return endDate;
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

    public void setNotaFinal(Double notaFinal) {
        this.notaFinal = notaFinal;
    }

    public Double getNotaFinal() {
        return notaFinal;
    }
}
