package sped.vista.beans.evaluacion.planificar;



import java.util.Date;
import java.util.List;
import sped.negocio.entidades.beans.BeanMain;
import sped.negocio.entidades.beans.BeanUsuario;

/** Clase de Sesion del Bean BeanMain.java
 * @author czavalacas
 * @since 29.12.2013
 */
public class sessionPlanificar {
   
    private List<BeanMain> listaHorarios;
    private BeanMain beanHorario;
    private int nidAula;   
    private BeanUsuario beanUsuario;
    private int nidUsuario;
    private int nidAreaAcademica;
    private int nidEvaluador;
    private Integer dniProfesor;
    private List listaProfesores;
    private List listaEvaluadores;
    private String diaDeLaSemana;
    private Date fechaInicioSeleccionada;   
    private Date fechaInicioEvaluacion;
    private Date fechaFinEvaluacion;
    //atributos para popup
    private Date fechaEvaluacionPopup;
    private Date horaEvaluacionPopup;
    private String sedeEvaluacion;
    private String aulaEvaluacion;
    private String cursoEvaluacion;
    private String gradoEvaluacion;
    private String nivelEvaluacion;
    private String docenteEvaluacion;
    private String dniDocenteEvaluacion;

    public void setListaHorarios(List<BeanMain> listaHorarios) {
        this.listaHorarios = listaHorarios;
    }

    public List<BeanMain> getListaHorarios() {
        return listaHorarios;
    }

    public void setBeanHorario(BeanMain beanHorario) {
        this.beanHorario = beanHorario;
    }

    public BeanMain getBeanHorario() {
        return beanHorario;
    }

    public void setNidAula(int nidAula) {
        this.nidAula = nidAula;
    }

    public int getNidAula() {
        return nidAula;
    }


    public void setListaEvaluadores(List listaEvaluadores) {
        this.listaEvaluadores = listaEvaluadores;
    }

    public List getListaEvaluadores() {
        return listaEvaluadores;
    }

    public void setNidAreaAcademica(int nidAreaAcademica) {
        this.nidAreaAcademica = nidAreaAcademica;
    }

    public int getNidAreaAcademica() {
        return nidAreaAcademica;
    }

    public void setNidEvaluador(int nidEvaluador) {
        this.nidEvaluador = nidEvaluador;
    }

    public int getNidEvaluador() {
        return nidEvaluador;
    }

    public void setBeanUsuario(BeanUsuario beanUsuario) {
        this.beanUsuario = beanUsuario;
    }

    public BeanUsuario getBeanUsuario() {
        return beanUsuario;
    }

    public void setDniProfesor(Integer dniProfesor) {
        this.dniProfesor = dniProfesor;
    }

    public Integer getDniProfesor() {
        return dniProfesor;
    }

    public void setListaProfesores(List listaProfesores) {
        this.listaProfesores = listaProfesores;
    }

    public List getListaProfesores() {
        return listaProfesores;
    }

    public void setDiaDeLaSemana(String diaDeLaSemana) {
        this.diaDeLaSemana = diaDeLaSemana;
    }

    public String getDiaDeLaSemana() {
        return diaDeLaSemana;
    }

    public void setNidUsuario(int nidUsuario) {
        this.nidUsuario = nidUsuario;
    }

    public int getNidUsuario() {
        return nidUsuario;
    }


    public void setFechaInicioSeleccionada(Date fechaInicioSeleccionada) {
        this.fechaInicioSeleccionada = fechaInicioSeleccionada;
    }

    public Date getFechaInicioSeleccionada() {
        return fechaInicioSeleccionada;
    }

    public void setFechaInicioEvaluacion(Date fechaInicioEvaluacion) {
        this.fechaInicioEvaluacion = fechaInicioEvaluacion;
    }

    public Date getFechaInicioEvaluacion() {
        return fechaInicioEvaluacion;
    }

    public void setFechaFinEvaluacion(Date fechaFinEvaluacion) {
        this.fechaFinEvaluacion = fechaFinEvaluacion;
    }

    public Date getFechaFinEvaluacion() {
        return fechaFinEvaluacion;
    }

    public void setFechaEvaluacionPopup(Date fechaEvaluacionPopup) {
        this.fechaEvaluacionPopup = fechaEvaluacionPopup;
    }

    public Date getFechaEvaluacionPopup() {
        return fechaEvaluacionPopup;
    }

    public void setHoraEvaluacionPopup(Date horaEvaluacionPopup) {
        this.horaEvaluacionPopup = horaEvaluacionPopup;
    }

    public Date getHoraEvaluacionPopup() {
        return horaEvaluacionPopup;
    }

    public void setSedeEvaluacion(String sedeEvaluacion) {
        this.sedeEvaluacion = sedeEvaluacion;
    }

    public String getSedeEvaluacion() {
        return sedeEvaluacion;
    }

    public void setAulaEvaluacion(String aulaEvaluacion) {
        this.aulaEvaluacion = aulaEvaluacion;
    }

    public String getAulaEvaluacion() {
        return aulaEvaluacion;
    }

    public void setCursoEvaluacion(String cursoEvaluacion) {
        this.cursoEvaluacion = cursoEvaluacion;
    }

    public String getCursoEvaluacion() {
        return cursoEvaluacion;
    }

    public void setGradoEvaluacion(String gradoEvaluacion) {
        this.gradoEvaluacion = gradoEvaluacion;
    }

    public String getGradoEvaluacion() {
        return gradoEvaluacion;
    }

    public void setNivelEvaluacion(String nivelEvaluacion) {
        this.nivelEvaluacion = nivelEvaluacion;
    }

    public String getNivelEvaluacion() {
        return nivelEvaluacion;
    }

    public void setDocenteEvaluacion(String docenteEvaluacion) {
        this.docenteEvaluacion = docenteEvaluacion;
    }

    public String getDocenteEvaluacion() {
        return docenteEvaluacion;
    }

    public void setDniDocenteEvaluacion(String dniDocenteEvaluacion) {
        this.dniDocenteEvaluacion = dniDocenteEvaluacion;
    }

    public String getDniDocenteEvaluacion() {
        return dniDocenteEvaluacion;
    }
}
