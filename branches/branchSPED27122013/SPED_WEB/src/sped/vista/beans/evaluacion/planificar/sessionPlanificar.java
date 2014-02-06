package sped.vista.beans.evaluacion.planificar;



import java.util.Date;
import java.util.List;

import sped.negocio.entidades.admin.AreaAcademica;
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
    private List listAreaAcademica;
    private List<BeanUsuario> listBeanUsua;
    private String nombreEvaluador;
    private String areaEvaluador;
    private String nidUsuario;
    private String nidAreaFiltro;
    private int nidAreaAcademica;
    private int nidEvaluador;
    private int nidSedeEvaluador;
    private String nidCurso;
    private String dniProfesor;
    private String nidSede;
    private String nidGrado;
    private String nidNivel;
    private String nidAreaAcademicaChoice;
    private List listaProfesores;
    private List listaEvaluadores;
    private List listaCursos;
    private List listaSedes;
    private List listaGrados;
    private List listaNiveles;
    private List listAreasAcade;
    private List listatipoVisita;
    private String diaDeLaSemana;
    private Date fechaInicioSeleccionada;   
    private Date fechaInicioEvaluacion;
    private Date fechaFinEvaluacion;
    private Integer nidEvaluacionDelet;
    private Integer nidPlanificador;
    private Integer nidRol;
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
    private boolean estadoChoiceEvaluadores;
    private boolean estadoBotonEliminarEvaluacion;
    private String styleClass;
    private boolean estadoAsignarEvaluacion=true;
    private Date horaPartidaInicio;  
    private String ValorTipoVisita;
    private String nombrePlanificador;
    private String tipoEvaluacion;
    private String rolPlanificador;
    private boolean estadoBtnBloq1=false;
    private boolean estadoBtnBloq2=false;
    private boolean estadoOut1=false;
    private boolean estadoOut2=false;
    private boolean estadoDisableChoiceSede=false;
    private boolean estadoDisableChoiceArea=false;
    private Integer nidRolUsuarioEnSession;
                                    
                                    
    

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


    public void setNidCurso(String nidCurso) {
        this.nidCurso = nidCurso;
    }

    public String getNidCurso() {
        return nidCurso;
    }

    public void setDniProfesor(String dniProfesor) {
        this.dniProfesor = dniProfesor;
    }

    public String getDniProfesor() {
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


    public void setNidUsuario(String nidUsuario) {
        this.nidUsuario = nidUsuario;
    }

    public String getNidUsuario() {
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

    public void setListaCursos(List listaCursos) {
        this.listaCursos = listaCursos;
    }

    public List getListaCursos() {
        return listaCursos;
    }



    public void setEstadoChoiceEvaluadores(boolean estadoChoiceEvaluadores) {
        this.estadoChoiceEvaluadores = estadoChoiceEvaluadores;
    }

    public boolean isEstadoChoiceEvaluadores() {
        return estadoChoiceEvaluadores;
    }

    public void setListBeanUsua(List<BeanUsuario> listBeanUsua) {
        this.listBeanUsua = listBeanUsua;
    }

    public List<BeanUsuario> getListBeanUsua() {
        return listBeanUsua;
    }

    public void setNombreEvaluador(String nombreEvaluador) {
        this.nombreEvaluador = nombreEvaluador;
    }

    public String getNombreEvaluador() {
        return nombreEvaluador;
    }

    public void setAreaEvaluador(String areaEvaluador) {
        this.areaEvaluador = areaEvaluador;
    }

    public String getAreaEvaluador() {
        return areaEvaluador;
    }

    public void setNidEvaluacionDelet(Integer nidEvaluacionDelet) {
        this.nidEvaluacionDelet = nidEvaluacionDelet;
    }

    public Integer getNidEvaluacionDelet() {
        return nidEvaluacionDelet;
    }

    public void setNidPlanificador(Integer nidPlanificador) {
        this.nidPlanificador = nidPlanificador;
    }

    public Integer getNidPlanificador() {
        return nidPlanificador;
    }

    public void setEstadoBotonEliminarEvaluacion(boolean estadoBotonEliminarEvaluacion) {
        this.estadoBotonEliminarEvaluacion = estadoBotonEliminarEvaluacion;
    }

    public boolean isEstadoBotonEliminarEvaluacion() {
        return estadoBotonEliminarEvaluacion;
    }

    public void setNidRol(Integer nidRol) {
        this.nidRol = nidRol;
    }

    public Integer getNidRol() {
        return nidRol;
    }


    public void setListAreaAcademica(List listAreaAcademica) {
        this.listAreaAcademica = listAreaAcademica;
    }

    public List getListAreaAcademica() {
        return listAreaAcademica;
    }

    public void setNidAreaFiltro(String nidAreaFiltro) {
        this.nidAreaFiltro = nidAreaFiltro;
    }

    public String getNidAreaFiltro() {
        return nidAreaFiltro;
    }

    public void setStyleClass(String styleClass) {
        this.styleClass = styleClass;
    }

    public String getStyleClass() {
        return styleClass;
    }

    public void setEstadoAsignarEvaluacion(boolean estadoAsignarEvaluacion) {
        this.estadoAsignarEvaluacion = estadoAsignarEvaluacion;
    }

    public boolean isEstadoAsignarEvaluacion() {
        return estadoAsignarEvaluacion;
    }

    public void setNidSede(String nidSede) {
        this.nidSede = nidSede;
    }

    public String getNidSede() {
        return nidSede;
    }

    public void setListaSedes(List listaSedes) {
        this.listaSedes = listaSedes;
    }

    public List getListaSedes() {
        return listaSedes;
    }

    public void setNidGrado(String nidGrado) {
        this.nidGrado = nidGrado;
    }

    public String getNidGrado() {
        return nidGrado;
    }

    public void setListaGrados(List listaGrados) {
        this.listaGrados = listaGrados;
    }

    public List getListaGrados() {
        return listaGrados;
    }

    public void setNidNivel(String nidNivel) {
        this.nidNivel = nidNivel;
    }

    public String getNidNivel() {
        return nidNivel;
    }

    public void setListaNiveles(List listaNiveles) {
        this.listaNiveles = listaNiveles;
    }

    public List getListaNiveles() {
        return listaNiveles;
    }

    public void setHoraPartidaInicio(Date horaPartidaInicio) {
        this.horaPartidaInicio = horaPartidaInicio;
    }

    public Date getHoraPartidaInicio() {
        return horaPartidaInicio;
    }

    public void setListatipoVisita(List listatipoVisita) {
        this.listatipoVisita = listatipoVisita;
    }

    public List getListatipoVisita() {
        return listatipoVisita;
    }

    public void setValorTipoVisita(String ValorTipoVisita) {
        this.ValorTipoVisita = ValorTipoVisita;
    }

    public String getValorTipoVisita() {
        return ValorTipoVisita;
    }

    public void setNombrePlanificador(String nombrePlanificador) {
        this.nombrePlanificador = nombrePlanificador;
    }

    public String getNombrePlanificador() {
        return nombrePlanificador;
    }

    public void setTipoEvaluacion(String tipoEvaluacion) {
        this.tipoEvaluacion = tipoEvaluacion;
    }

    public String getTipoEvaluacion() {
        return tipoEvaluacion;
    }


    public void setEstadoBtnBloq1(boolean estadoBtnBloq1) {
        this.estadoBtnBloq1 = estadoBtnBloq1;
    }

    public boolean isEstadoBtnBloq1() {
        return estadoBtnBloq1;
    }

    public void setEstadoBtnBloq2(boolean estadoBtnBloq2) {
        this.estadoBtnBloq2 = estadoBtnBloq2;
    }

    public boolean isEstadoBtnBloq2() {
        return estadoBtnBloq2;
    }

    public void setEstadoOut1(boolean estadoOut1) {
        this.estadoOut1 = estadoOut1;
    }

    public boolean isEstadoOut1() {
        return estadoOut1;
    }

    public void setEstadoOut2(boolean estadoOut2) {
        this.estadoOut2 = estadoOut2;
    }

    public boolean isEstadoOut2() {
        return estadoOut2;
    }

    public void setRolPlanificador(String rolPlanificador) {
        this.rolPlanificador = rolPlanificador;
    }

    public String getRolPlanificador() {
        return rolPlanificador;
    }

    public void setNidSedeEvaluador(int nidSedeEvaluador) {
        this.nidSedeEvaluador = nidSedeEvaluador;
    }

    public int getNidSedeEvaluador() {
        return nidSedeEvaluador;
    }

    public void setEstadoDisableChoiceSede(boolean estadoDisableChoiceSede) {
        this.estadoDisableChoiceSede = estadoDisableChoiceSede;
    }

    public boolean isEstadoDisableChoiceSede() {
        return estadoDisableChoiceSede;
    }

    public void setNidRolUsuarioEnSession(Integer nidRolUsuarioEnSession) {
        this.nidRolUsuarioEnSession = nidRolUsuarioEnSession;
    }

    public Integer getNidRolUsuarioEnSession() {
        return nidRolUsuarioEnSession;
    }

    public void setNidAreaAcademicaChoice(String nidAreaAcademicaChoice) {
        this.nidAreaAcademicaChoice = nidAreaAcademicaChoice;
    }

    public String getNidAreaAcademicaChoice() {
        return nidAreaAcademicaChoice;
    }

    public void setListAreasAcade(List listAreasAcade) {
        this.listAreasAcade = listAreasAcade;
    }

    public List getListAreasAcade() {
        return listAreasAcade;
    }

    public void setEstadoDisableChoiceArea(boolean estadoDisableChoiceArea) {
        this.estadoDisableChoiceArea = estadoDisableChoiceArea;
    }

    public boolean isEstadoDisableChoiceArea() {
        return estadoDisableChoiceArea;
    }
}
