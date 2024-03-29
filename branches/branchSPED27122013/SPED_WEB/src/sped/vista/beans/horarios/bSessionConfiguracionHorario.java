package sped.vista.beans.horarios;

import java.io.Serializable;

import java.sql.Time;

import java.util.ArrayList;
import java.util.List;

import oracle.adf.view.rich.component.rich.RichPopup;

import sped.negocio.entidades.beans.BeanCalendario;
import sped.negocio.entidades.beans.BeanComboString;
import sped.negocio.entidades.beans.BeanConfiguracionEventoHorario;
import sped.negocio.entidades.beans.BeanConfiguracionHorario;
import sped.negocio.entidades.beans.BeanConstraint;
import sped.negocio.entidades.beans.BeanDuracionHorario;

public class bSessionConfiguracionHorario implements Serializable {
    @SuppressWarnings("compatibility:-6373971498140473254")
    private static final long serialVersionUID = 1L;
    private List listaSedesChoice;
    private String nidSedeChoice;
    private List listaNivelesChoice;
    private String nidNivelChoice;
    private List listaEventosHorariosChoice;
    private String nidEventoHorario;
    private List<BeanConfiguracionHorario> listaEventosHorarioTabla = new ArrayList<BeanConfiguracionHorario>();
    private String inputHoraInicioEventoHorario;
    private String inputHoraFinEventoHorario;
    private String inputHoraInicioClases;
    private String duracionPorBloque;
    private int numeroDeBloques = 0;
    private int maxBloquesPorCurso = 0;
    public int exec = 0;
    private boolean estadoDisableChoiceRestriccion = true;
    private boolean estadoinPutHoraInicioRestriccion = true;
    private boolean estadoinPutHoraFinRestriccion = true;
    private boolean estadoBtnAgregarRestriccion = true;
    private boolean estadobBtnRemoverRestriccion = true;
    private boolean estadoHoraInicioClases = true;
    private boolean estadoNumBloques = true;
    private boolean estadoDuracionPorBloque = true;
    private boolean estadoMaxBloquesXCurso = true;
    private boolean estadoDisableChoiceNive = true;
    private boolean estadoDisableBtnGuardar = true;
    private boolean estadoDisableChoiceSede = false;
    private boolean estadoBtnEditarRestriccion = true;
    private int accionPersist = 0;
    private int seleccionTable = 0;
    private BeanDuracionHorario beanDuracionHorario = new BeanDuracionHorario();  
    private BeanConfiguracionHorario beanconfiguracionHorario = new BeanConfiguracionHorario();  
    private int nroBloque;
    private int maxBloque;
    private Time horas[];
    private String horaFinClases;
    //Calendario dfloresgonz 26.08.2014
    private List<BeanCalendario> lstCalendario = new ArrayList<BeanCalendario>();
    private String cidMes;
    private BeanCalendario calenSelected = new BeanCalendario();
    private String descDia;
    //dfloresgonz 01.09.2014 configuracion de dias min MAX x dia 
    private List<BeanConstraint> lstBeanCons = new ArrayList<BeanConstraint>();
    private BeanConstraint editCons = new BeanConstraint();
    //dfloresgonz 28.01.2015 Combo year
    private List<BeanComboString> lstYears = new ArrayList<BeanComboString>();
    private String year;

    public void setYear(String year) {
        this.year = year;
    }

    public String getYear() {
        return year;
    }

    public void setLstYears(List<BeanComboString> lstYears) {
        this.lstYears = lstYears;
    }

    public List<BeanComboString> getLstYears() {
        return lstYears;
    }

    public void setEditCons(BeanConstraint editCons) {
        this.editCons = editCons;
    }

    public BeanConstraint getEditCons() {
        return editCons;
    }

    public void setLstBeanCons(List<BeanConstraint> lstBeanCons) {
        this.lstBeanCons = lstBeanCons;
    }

    public List<BeanConstraint> getLstBeanCons() {
        return lstBeanCons;
    }

    public void setDescDia(String descDia) {
        this.descDia = descDia;
    }

    public String getDescDia() {
        return descDia;
    }

    public void setCalenSelected(BeanCalendario calenSelected) {
        this.calenSelected = calenSelected;
    }

    public BeanCalendario getCalenSelected() {
        return calenSelected;
    }

    public void setCidMes(String cidMes) {
        this.cidMes = cidMes;
    }

    public String getCidMes() {
        return cidMes;
    }

    public void setLstCalendario(List<BeanCalendario> lstCalendario) {
        this.lstCalendario = lstCalendario;
    }

    public List<BeanCalendario> getLstCalendario() {
        return lstCalendario;
    }

    public void setNroBloque(int nroBloque) {
        this.nroBloque = nroBloque;
    }

    public int getNroBloque() {
        return nroBloque;
    }

    public void setMaxBloque(int maxBloque) {
        this.maxBloque = maxBloque;
    }

    public int getMaxBloque() {
        return maxBloque;
    }

    public void setHoras(Time[] horas) {
        this.horas = horas;
    }

    public Time[] getHoras() {
        return horas;
    }

    public bSessionConfiguracionHorario() {
    }


    public void setNidSedeChoice(String nidSedeChoice) {
        this.nidSedeChoice = nidSedeChoice;
    }

    public void setNidNivelChoice(String nidNivelChoice) {
        this.nidNivelChoice = nidNivelChoice;
    }

    public String getNidNivelChoice() {
        return nidNivelChoice;
    }

    public String getNidSedeChoice() {
        return nidSedeChoice;
    }

    public void setEstadoDisableChoiceNive(boolean estadoDisableChoiceNive) {
        this.estadoDisableChoiceNive = estadoDisableChoiceNive;
    }

    public boolean isEstadoDisableChoiceNive() {
        return estadoDisableChoiceNive;
    }

    public void setListaSedesChoice(List listaSedesChoice) {
        this.listaSedesChoice = listaSedesChoice;
    }

    public void setExec(int exec) {
        this.exec = exec;
    }

    public int getExec() {
        return exec;
    }

    public List getListaSedesChoice() {
        return listaSedesChoice;
    }

    public void setListaNivelesChoice(List listaNivelesChoice) {
        this.listaNivelesChoice = listaNivelesChoice;
    }

    public List getListaNivelesChoice() {
        return listaNivelesChoice;
    }

    public void setListaEventosHorariosChoice(List listaEventosHorariosChoice) {
        this.listaEventosHorariosChoice = listaEventosHorariosChoice;
    }

    public List getListaEventosHorariosChoice() {
        return listaEventosHorariosChoice;
    }

    public void setNidEventoHorario(String nidEventoHorario) {
        this.nidEventoHorario = nidEventoHorario;
    }

    public String getNidEventoHorario() {
        return nidEventoHorario;
    }

    public void setListaEventosHorarioTabla(List<BeanConfiguracionHorario> listaEventosHorarioTabla) {
        this.listaEventosHorarioTabla = listaEventosHorarioTabla;
    }

    public List<BeanConfiguracionHorario> getListaEventosHorarioTabla() {
        return listaEventosHorarioTabla;
    }

    public void setInputHoraInicioEventoHorario(String inputHoraInicioEventoHorario) {
        this.inputHoraInicioEventoHorario = inputHoraInicioEventoHorario;
    }

    public String getInputHoraInicioEventoHorario() {
        return inputHoraInicioEventoHorario;
    }

    public void setInputHoraFinEventoHorario(String inputHoraFinEventoHorario) {
        this.inputHoraFinEventoHorario = inputHoraFinEventoHorario;
    }

    public String getInputHoraFinEventoHorario() {
        return inputHoraFinEventoHorario;
    }

    public void setInputHoraInicioClases(String inputHoraInicioClases) {
        this.inputHoraInicioClases = inputHoraInicioClases;
    }

    public String getInputHoraInicioClases() {
        return inputHoraInicioClases;
    }

    public void setDuracionPorBloque(String duracionPorBloque) {
        this.duracionPorBloque = duracionPorBloque;
    }

    public String getDuracionPorBloque() {
        return duracionPorBloque;
    }

    public void setNumeroDeBloques(int numeroDeBloques) {
        this.numeroDeBloques = numeroDeBloques;
    }

    public int getNumeroDeBloques() {
        return numeroDeBloques;
    }

    public void setMaxBloquesPorCurso(int maxBloquesPorCurso) {
        this.maxBloquesPorCurso = maxBloquesPorCurso;
    }

    public int getMaxBloquesPorCurso() {
        return maxBloquesPorCurso;
    }

    public void setEstadoDisableChoiceRestriccion(boolean estadoDisableChoiceRestriccion) {
        this.estadoDisableChoiceRestriccion = estadoDisableChoiceRestriccion;
    }

    public boolean isEstadoDisableChoiceRestriccion() {
        return estadoDisableChoiceRestriccion;
    }

    public void setEstadoinPutHoraInicioRestriccion(boolean estadoinPutHoraInicioRestriccion) {
        this.estadoinPutHoraInicioRestriccion = estadoinPutHoraInicioRestriccion;
    }

    public boolean isEstadoinPutHoraInicioRestriccion() {
        return estadoinPutHoraInicioRestriccion;
    }

    public void setEstadoinPutHoraFinRestriccion(boolean estadoinPutHoraFinRestriccion) {
        this.estadoinPutHoraFinRestriccion = estadoinPutHoraFinRestriccion;
    }

    public boolean isEstadoinPutHoraFinRestriccion() {
        return estadoinPutHoraFinRestriccion;
    }

    public void setEstadoBtnAgregarRestriccion(boolean estadoBtnAgregarRestriccion) {
        this.estadoBtnAgregarRestriccion = estadoBtnAgregarRestriccion;
    }

    public boolean isEstadoBtnAgregarRestriccion() {
        return estadoBtnAgregarRestriccion;
    }

    public void setEstadobBtnRemoverRestriccion(boolean estadobBtnRemoverRestriccion) {
        this.estadobBtnRemoverRestriccion = estadobBtnRemoverRestriccion;
    }

    public boolean isEstadobBtnRemoverRestriccion() {
        return estadobBtnRemoverRestriccion;
    }

    public void setEstadoHoraInicioClases(boolean estadoHoraInicioClases) {
        this.estadoHoraInicioClases = estadoHoraInicioClases;
    }

    public boolean isEstadoHoraInicioClases() {
        return estadoHoraInicioClases;
    }

    public void setEstadoNumBloques(boolean estadoNumBloques) {
        this.estadoNumBloques = estadoNumBloques;
    }

    public boolean isEstadoNumBloques() {
        return estadoNumBloques;
    }

    public void setEstadoDuracionPorBloque(boolean estadoDuracionPorBloque) {
        this.estadoDuracionPorBloque = estadoDuracionPorBloque;
    }

    public boolean isEstadoDuracionPorBloque() {
        return estadoDuracionPorBloque;
    }

    public void setEstadoMaxBloquesXCurso(boolean estadoMaxBloquesXCurso) {
        this.estadoMaxBloquesXCurso = estadoMaxBloquesXCurso;
    }

    public boolean isEstadoMaxBloquesXCurso() {
        return estadoMaxBloquesXCurso;
    }

    public void setEstadoDisableBtnGuardar(boolean estadoDisableBtnGuardar) {
        this.estadoDisableBtnGuardar = estadoDisableBtnGuardar;
    }

    public boolean isEstadoDisableBtnGuardar() {
        return estadoDisableBtnGuardar;
    }

    public void setEstadoDisableChoiceSede(boolean estadoDisableChoiceSede) {
        this.estadoDisableChoiceSede = estadoDisableChoiceSede;
    }

    public boolean isEstadoDisableChoiceSede() {
        return estadoDisableChoiceSede;
    }

    public void setEstadoBtnEditarRestriccion(boolean estadoBtnEditarRestriccion) {
        this.estadoBtnEditarRestriccion = estadoBtnEditarRestriccion;
    }

    public boolean isEstadoBtnEditarRestriccion() {
        return estadoBtnEditarRestriccion;
    }

    public void setAccionPersist(int accionPersist) {
        this.accionPersist = accionPersist;
    }

    public int getAccionPersist() {
        return accionPersist;
    }

    public void setBeanDuracionHorario(BeanDuracionHorario beanDuracionHorario) {
        this.beanDuracionHorario = beanDuracionHorario;
    }

    public BeanDuracionHorario getBeanDuracionHorario() {
        return beanDuracionHorario;
    }

    public void setBeanconfiguracionHorario(BeanConfiguracionHorario beanconfiguracionHorario) {
        this.beanconfiguracionHorario = beanconfiguracionHorario;
    }

    public BeanConfiguracionHorario getBeanconfiguracionHorario() {
        return beanconfiguracionHorario;
    }

    public void setSeleccionTable(int seleccionTable) {
        this.seleccionTable = seleccionTable;
    }

    public int getSeleccionTable() {
        return seleccionTable;
    }

    public void setHoraFinClases(String horaFinClases) {
        this.horaFinClases = horaFinClases;
    }

    public String getHoraFinClases() {
        return horaFinClases;
    }

}
