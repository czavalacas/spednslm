package sped.vista.beans.horarios;

import java.awt.Color;

import java.io.Serializable;

import java.sql.Time;

import java.text.Format;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.List;

import sped.negocio.entidades.beans.BeanCombo;
import sped.negocio.entidades.beans.BeanDia;
import sped.negocio.entidades.beans.BeanHorario;
import sped.negocio.entidades.beans.BeanMain;

import sped.vista.Utils.Utils;

public class bSessionGestionarHorario implements Serializable {
    @SuppressWarnings("compatibility:204896294902743203")
    private static final long serialVersionUID = 1L;

    private int exec;
    private String nidSede;
    private String nidNivel; 
    private String nidCurso;
    private String nidAula;
    private String nidProfesor;
    private String nidArea;
    private List lstSede;
    private List lstNivel;
    private List lstAula;
    private List lstProfesor;
    private List lstCurso;
    private List lstCurso_aux;
    private List lstArea;
    private Time horas[];
    private Time horas_fin[];
    private List<BeanMain> lstBeanMain;
    private String dias[];
    private List<BeanHorario> lstHorario;
    private List<BeanDia> lstDia;
    private List<Integer> horasRandom;
    private List<String> duracion;
    BeanMain horario[][];    
    private int nroBloque;
    private int maxBloque;
    private String nombreProfesor;
    private String nombreCurso;
    private String nombreArea;
    private String nombreAula;
    Format formatter = new SimpleDateFormat("hh:mm");
    private int nDia;
    private int nLeccion;
    private String selecNombreCurso;
    private String selecColor;
    private int selecNidCurso;
    private String selecProfesor;
    private List lstSelecDias; //lista selecionable
    private List lstDiasSelec; // lista selecionados
    private Color color;
    private String tituloEliminarModificar;
    private int eventoEliminarModificar;
    private boolean renderEliminarModificar;
    private boolean renderHorario;
    private String nombreAula_aux;
    private String nidAula_aux;
    private String nidDni_aux;
    private String nidArea_aux;
    private String nidCurso_aux;
    private boolean renderCurso_aux;
    private boolean renderCurso;
    private boolean renderAgregar;
    private boolean renderAgregar_aux;
    private boolean renderGenerario;
    private boolean checkTrue = true; //se mantendra siempre en true
    private boolean checkFalse = false;// se mantendra siempre en false
    
    ///**** Metodos auxiliares ***///
    public String getDia(int nDia){
        return dias[nDia];
    }
    
    public void agregarMain(int nLeccion, int nDia, BeanMain main){
        horario[nLeccion][nDia] = main;
    }
    
    public void cargarDatosSelec(){
        selecNidCurso = horario[nLeccion][nDia].getNidCurso();
        selecNombreCurso = horario[nLeccion][nDia].getNombreCurso();                  
    }
    
    public void cargarDatosColor(){
        try{
            if(horario[nLeccion][nDia].getColor().length() == 6){
                color = Color.decode("#"+horario[nLeccion][nDia].getColor());
            }            
        }catch(Exception e){
            e.printStackTrace();
        } 
    }
    
    public void llenarDuracionHoras(){
        duracion = new ArrayList();
        for(int i = 0; i < horas.length; i++){
            duracion.add(formatter.format(horas[i]) + " - " + formatter.format(horas_fin[i]));
        }
    }
    
    public String duracion(int nLeccion){
        return duracion.get(nLeccion);
    }
    
    public String nomCurso(int nLeccion, int nDia){
        return horario[nLeccion][nDia].getNombreCurso();
    }
    
    public String nomCurso_aux(int nLeccion, int nDia){
        return horario[nLeccion][nDia] == null ? "" : horario[nLeccion][nDia].getNombreCurso();
    }
    
    public String nomProfesor(int nLeccion, int nDia){
        return horario[nLeccion][nDia].getNombreProfesor();
    }
    
    public String nomColor(int nLeccion, int nDia){
        return "#"+horario[nLeccion][nDia].getColor();
    }
    
    public boolean renderLeccion_aux(int nLeccion, int nDia){
        return  horario[nLeccion][nDia] != null && horario[nLeccion][nDia].getNidMain() == 0 ? true : false;
    }
    
    public boolean existeLeccion(int nLeccion, int nDia){
        return  horario[nLeccion][nDia] != null ? true : false;
    }
    
    public boolean renderLeccion(int nLeccion, int nDia){
        return  horario[nLeccion][nDia] != null && horario[nLeccion][nDia].getNidMain() != 0 ? true : false;
    }
    
    /**
     * Metodo que busca los dias que se dicta una leccion
     */
    public void encontrarDiaLecccion(){
        List<BeanCombo> lista = new ArrayList();
        int cont = 0;
        llenarLstComboString(9, "Lecci\u00f3n selecionada", lista);
        for(int j = 0; j < 5; j++){
            for(int i = 0; i < nroBloque; i++){
                if(horario[i][j] != null && 
                   horario[i][j].getNidCurso() == selecNidCurso){
                    if(nDia == j){
                        cont++;
                    }else{
                        llenarLstComboString(j, dias[j], lista);
                        i = nroBloque;
                    }                        
                }
            }
            if(cont > 1){
                llenarLstComboString(nDia, dias[nDia], lista);
                cont = 0;
            }
        }
        this.setLstSelecDias(Utils.llenarCombo(lista));
        ///seleciono por default el dia que se seleciono
        List lst = new ArrayList();
        lst.add(9+"");
        this.setLstDiasSelec(lst);
    }
    
    public void llenarLstComboString(int id, String descripcion, List<BeanCombo> lista){
        BeanCombo combo = new BeanCombo();
        combo.setId(id);
        combo.setDescripcion(descripcion);
        lista.add(combo);
    }
    
    /// FIN ///
    
    public void setExec(int exec) {
        this.exec = exec;
    }

    public int getExec() {
        return exec;
    }

    public void setNidSede(String nidSede) {
        this.nidSede = nidSede;
    }

    public String getNidSede() {
        return nidSede;
    }

    public void setNidNivel(String nidNivel) {
        this.nidNivel = nidNivel;
    }

    public String getNidNivel() {
        return nidNivel;
    }

    public void setNidCurso(String nidCurso) {
        this.nidCurso = nidCurso;
    }

    public String getNidCurso() {
        return nidCurso;
    }

    public void setNidAula(String nidAula) {
        this.nidAula = nidAula;
    }

    public String getNidAula() {
        return nidAula;
    }

    public void setNidProfesor(String nidProfesor) {
        this.nidProfesor = nidProfesor;
    }

    public String getNidProfesor() {
        return nidProfesor;
    }

    public void setNidArea(String nidArea) {
        this.nidArea = nidArea;
    }

    public String getNidArea() {
        return nidArea;
    }

    public void setLstSede(List lstSede) {
        this.lstSede = lstSede;
    }

    public List getLstSede() {
        return lstSede;
    }

    public void setLstNivel(List lstNivel) {
        this.lstNivel = lstNivel;
    }

    public List getLstNivel() {
        return lstNivel;
    }

    public void setLstCurso(List lstCurso) {
        this.lstCurso = lstCurso;
    }

    public List getLstCurso() {
        return lstCurso;
    }

    public void setLstAula(List lstAula) {
        this.lstAula = lstAula;
    }

    public List getLstAula() {
        return lstAula;
    }

    public void setLstProfesor(List lstProfesor) {
        this.lstProfesor = lstProfesor;
    }

    public List getLstProfesor() {
        return lstProfesor;
    }

    public void setLstArea(List lstArea) {
        this.lstArea = lstArea;
    }

    public List getLstArea() {
        return lstArea;
    }

    public void setHoras(Time[] horas) {
        this.horas = horas;
    }

    public Time[] getHoras() {
        return horas;
    }

    public void setLstBeanMain(List<BeanMain> lstBeanMain) {
        this.lstBeanMain = lstBeanMain;
    }

    public List<BeanMain> getLstBeanMain() {
        return lstBeanMain;
    }

    public void setHorario(BeanMain[][] horario) {
        this.horario = horario;
    }

    public BeanMain[][] getHorario() {
        return horario;
    }

    public void setNroBloque(int nroBloque) {
        this.nroBloque = nroBloque;
    }

    public int getNroBloque() {
        return nroBloque;
    }

    public void setNombreProfesor(String nombreProfesor) {
        this.nombreProfesor = nombreProfesor;
    }

    public String getNombreProfesor() {
        return nombreProfesor;
    }

    public void setNombreCurso(String nombreCurso) {
        this.nombreCurso = nombreCurso;
    }

    public String getNombreCurso() {
        return nombreCurso;
    }

    public void setNombreArea(String nombreArea) {
        this.nombreArea = nombreArea;
    }

    public String getNombreArea() {
        return nombreArea;
    }

    public void setLstDia(List<BeanDia> lstDia) {
        this.lstDia = lstDia;
    }

    public List<BeanDia> getLstDia() {
        return lstDia;
    }

    public void setMaxBloque(int maxBloque) {
        this.maxBloque = maxBloque;
    }

    public int getMaxBloque() {
        return maxBloque;
    }

    public void setHoras_fin(Time[] horas_fin) {
        this.horas_fin = horas_fin;
    }

    public Time[] getHoras_fin() {
        return horas_fin;
    }

    public void setLstHorario(List<BeanHorario> lstHorario) {
        this.lstHorario = lstHorario;
    }

    public List<BeanHorario> getLstHorario() {
        return lstHorario;
    }

    public void setDias(String[] dias) {
        this.dias = dias;
    }

    public String[] getDias() {
        return dias;
    }

    public void setNDia(int nDia) {
        this.nDia = nDia;
    }

    public int getNDia() {
        return nDia;
    }

    public void setNLeccion(int nLeccion) {
        this.nLeccion = nLeccion;
    }

    public int getNLeccion() {
        return nLeccion;
    }

    public void setSelecNombreCurso(String selecNombreCurso) {
        this.selecNombreCurso = selecNombreCurso;
    }

    public String getSelecNombreCurso() {
        return selecNombreCurso;
    }

    public void setSelecNidCurso(int selecNidCurso) {
        this.selecNidCurso = selecNidCurso;
    }

    public int getSelecNidCurso() {
        return selecNidCurso;
    }

    public void setSelecColor(String selecColor) {
        this.selecColor = selecColor;
    }

    public String getSelecColor() {
        return selecColor;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public void setSelecProfesor(String selecProfesor) {
        this.selecProfesor = selecProfesor;
    }

    public String getSelecProfesor() {
        return selecProfesor;
    }

    public void setLstSelecDias(List lstSelecDias) {
        this.lstSelecDias = lstSelecDias;
    }

    public List getLstSelecDias() {
        return lstSelecDias;
    }

    public void setLstDiasSelec(List lstDiasSelec) {
        this.lstDiasSelec = lstDiasSelec;
    }

    public List getLstDiasSelec() {
        return lstDiasSelec;
    }

    public void setTituloEliminarModificar(String tituloEliminarModificar) {
        this.tituloEliminarModificar = tituloEliminarModificar;
    }

    public String getTituloEliminarModificar() {
        return tituloEliminarModificar;
    }

    public void setEventoEliminarModificar(int eventoEliminarModificar) {
        this.eventoEliminarModificar = eventoEliminarModificar;
    }

    public int getEventoEliminarModificar() {
        return eventoEliminarModificar;
    }

    public void setRenderEliminarModificar(boolean renderEliminarModificar) {
        this.renderEliminarModificar = renderEliminarModificar;
    }

    public boolean isRenderEliminarModificar() {
        return renderEliminarModificar;
    }

    public void setRenderHorario(boolean renderHorario) {
        this.renderHorario = renderHorario;
    }

    public boolean isRenderHorario() {
        return renderHorario;
    }

    public void setNombreAula_aux(String nombreAula_aux) {
        this.nombreAula_aux = nombreAula_aux;
    }

    public String getNombreAula_aux() {
        return nombreAula_aux;
    }

    public void setNidAula_aux(String nidAula_aux) {
        this.nidAula_aux = nidAula_aux;
    }

    public String getNidAula_aux() {
        return nidAula_aux;
    }

    public void setNombreAula(String nombreAula) {
        this.nombreAula = nombreAula;
    }

    public String getNombreAula() {
        return nombreAula;
    }

    public void setNidDni_aux(String nidDni_aux) {
        this.nidDni_aux = nidDni_aux;
    }

    public String getNidDni_aux() {
        return nidDni_aux;
    }

    public void setNidCurso_aux(String nidCurso_aux) {
        this.nidCurso_aux = nidCurso_aux;
    }

    public String getNidCurso_aux() {
        return nidCurso_aux;
    }

    public void setNidArea_aux(String nidArea_aux) {
        this.nidArea_aux = nidArea_aux;
    }

    public String getNidArea_aux() {
        return nidArea_aux;
    }

    public void setRenderCurso_aux(boolean renderCurso_aux) {
        this.renderCurso_aux = renderCurso_aux;
    }

    public boolean isRenderCurso_aux() {
        return renderCurso_aux;
    }

    public void setRenderCurso(boolean renderCurso) {
        this.renderCurso = renderCurso;
    }

    public boolean isRenderCurso() {
        return renderCurso;
    }

    public void setRenderAgregar(boolean renderAgregar) {
        this.renderAgregar = renderAgregar;
    }

    public boolean isRenderAgregar() {
        return renderAgregar;
    }

    public void setCheckTrue(boolean checkTrue) {
        this.checkTrue = checkTrue;
    }

    public boolean isCheckTrue() {
        return checkTrue;
    }

    public void setCheckFalse(boolean checkFalse) {
        this.checkFalse = checkFalse;
    }

    public boolean isCheckFalse() {
        return checkFalse;
    }

    public void setRenderAgregar_aux(boolean renderAgregar_aux) {
        this.renderAgregar_aux = renderAgregar_aux;
    }

    public boolean isRenderAgregar_aux() {
        return renderAgregar_aux;
    }

    public void setHorasRandom(List<Integer> horasRandom) {
        this.horasRandom = horasRandom;
    }

    public List<Integer> getHorasRandom() {
        return horasRandom;
    }

    public void setDuracion(List<String> duracion) {
        this.duracion = duracion;
    }

    public List<String> getDuracion() {
        return duracion;
    }

    public void setLstCurso_aux(List lstCurso_aux) {
        this.lstCurso_aux = lstCurso_aux;
    }

    public List getLstCurso_aux() {
        return lstCurso_aux;
    }

    public void setRenderGenerario(boolean renderGenerario) {
        this.renderGenerario = renderGenerario;
    }

    public boolean isRenderGenerario() {
        return renderGenerario;
    }

}
