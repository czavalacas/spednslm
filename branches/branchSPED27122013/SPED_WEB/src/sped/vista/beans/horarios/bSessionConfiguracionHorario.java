package sped.vista.beans.horarios;

import java.io.Serializable;

import java.sql.Time;

import java.util.ArrayList;
import java.util.List;

import sped.negocio.entidades.beans.BeanConfiguracionEventoHorario;

public class bSessionConfiguracionHorario implements Serializable {
    @SuppressWarnings("compatibility:-6373971498140473254")
    private static final long serialVersionUID = 1L;
    private List listaSedesChoice;
    private String nidSedeChoice;
    private List listaNivelesChoice;
    private String nidNivelChoice;
    private List listaEventosHorariosChoice;
    private String nidEventoHorario;
    private List<BeanConfiguracionEventoHorario> listaEventosHorarioTabla=new ArrayList<BeanConfiguracionEventoHorario>();
    private String inputHoraInicioEventoHorario;
    private String inputHoraFinEventoHorario;
    private String inputHoraInicioClases;
    private String duracionPorBloque;
    private int numeroDeBloques=0;
    private int maxBloquesPorCurso=0;    
    public int exec=0;
    
    private boolean estadoDisableChoiceNive=true;
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
    
    public void setListaEventosHorarioTabla(List<BeanConfiguracionEventoHorario> listaEventosHorarioTabla) {
        this.listaEventosHorarioTabla = listaEventosHorarioTabla;
    }
    
    public List<BeanConfiguracionEventoHorario> getListaEventosHorarioTabla() {
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
}
