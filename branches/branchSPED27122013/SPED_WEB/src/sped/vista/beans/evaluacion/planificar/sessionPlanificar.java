package sped.vista.beans.evaluacion.planificar;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import java.util.Set;

import javax.faces.model.SelectItem;

import oracle.adf.view.rich.util.InstanceStyles;

import sped.negocio.entidades.admin.AreaAcademica;
import sped.negocio.entidades.beans.BeanEvaluacion;
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
    private boolean estadoBotonEliminarEvaluacion=true;
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
    private String comentarioProfesor;
    private String comentarioEvaluador;
    private String justificacionProfesor;
    private List listaProblemas;
    private boolean estadoBoxComentarios=false;
    private boolean estadoBoxJustificacion=false;
    private String nidProblema;
    private boolean estadoDisableChoiceProblema=false;
    private boolean estadoDinputJustificacion=false;
    private boolean estadoDinputcomentarioProfesor=true;
    private boolean estadoDinputcomentarioEvaluador=false;
    private boolean estadoBtnSaveComentEvalu=false;
    private boolean estadoDinputJustificacionVisible=false;
    private boolean estadoBtnSaveJustificaEvalu=false;
    private String estadoDeEvaluacion;
    private String calendaryActivityID;
    private HashMap activityStyles= new HashMap<Set<String>, InstanceStyles>();       
    ///////////////////////////////////////////////////
    private List listaAulasTemporal;
    private String nidAulaTemporal;
    private Date fechaMinTemporal;
    private Date fehcaMaxTemporal;
    private Date FechaYhoraInicialTemporal;
    private Date FechaYhoraFinTemporal;
    private List<SelectItem> itemNombreProferos;
    private Integer exec=0;
    private String fNombres;
    private String tipoFichaCurs;
    private List lstTiposFichaCurso;
    private boolean estadoChoiceTemporalNivel=true;
    private boolean estadoChoiceTemporalDocente=true;
    private boolean estadoChoiceTemporalCurso=true;
    private boolean estadoChoiceTemporalAula=true;
    private boolean estadoOutDatosEva1=false;
    private boolean estadoOutDatosEva2=false;
    private boolean estadoVisibleComboAreaacademica=true;
    private int nidMainPlanificacion;
    private List<BeanEvaluacion> listaPlanificacionesExistentes=new ArrayList<BeanEvaluacion>();

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

    public void setComentarioProfesor(String comentarioProfesor) {
        this.comentarioProfesor = comentarioProfesor;
    }

    public String getComentarioProfesor() {
        return comentarioProfesor;
    }

    public void setComentarioEvaluador(String comentarioEvaluador) {
        this.comentarioEvaluador = comentarioEvaluador;
    }

    public String getComentarioEvaluador() {
        return comentarioEvaluador;
    }

    public void setJustificacionProfesor(String justificacionProfesor) {
        this.justificacionProfesor = justificacionProfesor;
    }

    public String getJustificacionProfesor() {
        return justificacionProfesor;
    }

    public void setListaProblemas(List listaProblemas) {
        this.listaProblemas = listaProblemas;
    }

    public List getListaProblemas() {
        return listaProblemas;
    }

    public void setEstadoBoxComentarios(boolean estadoBoxComentarios) {
        this.estadoBoxComentarios = estadoBoxComentarios;
    }

    public boolean isEstadoBoxComentarios() {
        return estadoBoxComentarios;
    }

    public void setEstadoBoxJustificacion(boolean estadoBoxJustificacion) {
        this.estadoBoxJustificacion = estadoBoxJustificacion;
    }

    public boolean isEstadoBoxJustificacion() {
        return estadoBoxJustificacion;
    }

    public void setNidProblema(String nidProblema) {
        this.nidProblema = nidProblema;
    }

    public String getNidProblema() {
        return nidProblema;
    }

    public void setEstadoDisableChoiceProblema(boolean estadoDisableChoiceProblema) {
        this.estadoDisableChoiceProblema = estadoDisableChoiceProblema;
    }

    public boolean isEstadoDisableChoiceProblema() {
        return estadoDisableChoiceProblema;
    }

    public void setEstadoDinputJustificacion(boolean estadoDinputJustificacion) {
        this.estadoDinputJustificacion = estadoDinputJustificacion;
    }

    public boolean isEstadoDinputJustificacion() {
        return estadoDinputJustificacion;
    }

    public void setEstadoDinputcomentarioProfesor(boolean estadoDinputcomentarioProfesor) {
        this.estadoDinputcomentarioProfesor = estadoDinputcomentarioProfesor;
    }

    public boolean isEstadoDinputcomentarioProfesor() {
        return estadoDinputcomentarioProfesor;
    }

    public void setEstadoDinputcomentarioEvaluador(boolean estadoDinputcomentarioEvaluador) {
        this.estadoDinputcomentarioEvaluador = estadoDinputcomentarioEvaluador;
    }

    public boolean isEstadoDinputcomentarioEvaluador() {
        return estadoDinputcomentarioEvaluador;
    }

    public void setEstadoBtnSaveComentEvalu(boolean estadoBtnSaveComentEvalu) {
        this.estadoBtnSaveComentEvalu = estadoBtnSaveComentEvalu;
    }

    public boolean isEstadoBtnSaveComentEvalu() {
        return estadoBtnSaveComentEvalu;
    }

    public void setEstadoDinputJustificacionVisible(boolean estadoDinputJustificacionVisible) {
        this.estadoDinputJustificacionVisible = estadoDinputJustificacionVisible;
    }

    public boolean isEstadoDinputJustificacionVisible() {
        return estadoDinputJustificacionVisible;
    }

    public void setEstadoBtnSaveJustificaEvalu(boolean estadoBtnSaveJustificaEvalu) {
        this.estadoBtnSaveJustificaEvalu = estadoBtnSaveJustificaEvalu;
    }

    public boolean isEstadoBtnSaveJustificaEvalu() {
        return estadoBtnSaveJustificaEvalu;
    }

    public void setEstadoDeEvaluacion(String estadoDeEvaluacion) {
        this.estadoDeEvaluacion = estadoDeEvaluacion;
    }

    public String getEstadoDeEvaluacion() {
        return estadoDeEvaluacion;
    }

    public void setCalendaryActivityID(String calendaryActivityID) {
        this.calendaryActivityID = calendaryActivityID;
    }

    public String getCalendaryActivityID() {
        return calendaryActivityID;
    }

    public void setActivityStyles(HashMap activityStyles) {
        this.activityStyles = activityStyles;
    }

    public HashMap getActivityStyles() {
        return activityStyles;
    }

    public void setListaAulasTemporal(List listaAulasTemporal) {
        this.listaAulasTemporal = listaAulasTemporal;
    }

    public List getListaAulasTemporal() {
        return listaAulasTemporal;
    }

    public void setNidAulaTemporal(String nidAulaTemporal) {
        this.nidAulaTemporal = nidAulaTemporal;
    }

    public String getNidAulaTemporal() {
        return nidAulaTemporal;
    }

    public void setFechaMinTemporal(Date fechaMinTemporal) {
        this.fechaMinTemporal = fechaMinTemporal;
    }

    public Date getFechaMinTemporal() {
        return fechaMinTemporal;
    }

    public void setFehcaMaxTemporal(Date fehcaMaxTemporal) {
        this.fehcaMaxTemporal = fehcaMaxTemporal;
    }

    public Date getFehcaMaxTemporal() {
        return fehcaMaxTemporal;
    }

    public void setFechaYhoraInicialTemporal(Date FechaYhoraInicialTemporal) {
        this.FechaYhoraInicialTemporal = FechaYhoraInicialTemporal;
    }

    public Date getFechaYhoraInicialTemporal() {
        return FechaYhoraInicialTemporal;
    }

    public void setFechaYhoraFinTemporal(Date FechaYhoraFinTemporal) {
        this.FechaYhoraFinTemporal = FechaYhoraFinTemporal;
    }

    public Date getFechaYhoraFinTemporal() {
        return FechaYhoraFinTemporal;
    }

    public void setExec(Integer exec) {
        this.exec = exec;
    }

    public Integer getExec() {
        return exec;
    }

    public void setItemNombreProferos(List<SelectItem> itemNombreProferos) {
        this.itemNombreProferos = itemNombreProferos;
    }

    public List<SelectItem> getItemNombreProferos() {
        return itemNombreProferos;
    }

    public void setFNombres(String fNombres) {
        this.fNombres = fNombres;
    }

    public String getFNombres() {
        return fNombres;
    }

    public void setTipoFichaCurs(String tipoFichaCurs) {
        this.tipoFichaCurs = tipoFichaCurs;
    }

    public String getTipoFichaCurs() {
        return tipoFichaCurs;
    }

    public void setLstTiposFichaCurso(List lstTiposFichaCurso) {
        this.lstTiposFichaCurso = lstTiposFichaCurso;
    }

    public List getLstTiposFichaCurso() {
        return lstTiposFichaCurso;
    }

    public void setEstadoChoiceTemporalNivel(boolean estadoChoiceTemporalNivel) {
        this.estadoChoiceTemporalNivel = estadoChoiceTemporalNivel;
    }

    public boolean isEstadoChoiceTemporalNivel() {
        return estadoChoiceTemporalNivel;
    }

    public void setEstadoChoiceTemporalDocente(boolean estadoChoiceTemporalDocente) {
        this.estadoChoiceTemporalDocente = estadoChoiceTemporalDocente;
    }

    public boolean isEstadoChoiceTemporalDocente() {
        return estadoChoiceTemporalDocente;
    }

    public void setEstadoChoiceTemporalCurso(boolean estadoChoiceTemporalCurso) {
        this.estadoChoiceTemporalCurso = estadoChoiceTemporalCurso;
    }

    public boolean isEstadoChoiceTemporalCurso() {
        return estadoChoiceTemporalCurso;
    }

    public void setEstadoChoiceTemporalAula(boolean estadoChoiceTemporalAula) {
        this.estadoChoiceTemporalAula = estadoChoiceTemporalAula;
    }

    public boolean isEstadoChoiceTemporalAula() {
        return estadoChoiceTemporalAula;
    }

    public void setEstadoOutDatosEva1(boolean estadoOutDatosEva1) {
        this.estadoOutDatosEva1 = estadoOutDatosEva1;
    }

    public boolean isEstadoOutDatosEva1() {
        return estadoOutDatosEva1;
    }

    public void setEstadoOutDatosEva2(boolean estadoOutDatosEva2) {
        this.estadoOutDatosEva2 = estadoOutDatosEva2;
    }

    public boolean isEstadoOutDatosEva2() {
        return estadoOutDatosEva2;
    }

    public void setEstadoVisibleComboAreaacademica(boolean estadoVisibleComboAreaacademica) {
        this.estadoVisibleComboAreaacademica = estadoVisibleComboAreaacademica;
    }

    public boolean isEstadoVisibleComboAreaacademica() {
        return estadoVisibleComboAreaacademica;
    }

    public void setNidMainPlanificacion(int nidMainPlanificacion) {
        this.nidMainPlanificacion = nidMainPlanificacion;
    }

    public int getNidMainPlanificacion() {
        return nidMainPlanificacion;
    }

    public void setListaPlanificacionesExistentes(List<BeanEvaluacion> listaPlanificacionesExistentes) {
        this.listaPlanificacionesExistentes = listaPlanificacionesExistentes;
    }

    public List<BeanEvaluacion> getListaPlanificacionesExistentes() {
        return listaPlanificacionesExistentes;
    }
}
