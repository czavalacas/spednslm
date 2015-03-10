package sped.vista.beans.horarios;

import java.awt.Color;

import java.io.Serializable;

import java.sql.Time;

import java.text.Format;
import java.text.SimpleDateFormat;

import java.util.List;

import org.apache.myfaces.trinidad.model.ChildPropertyTreeModel;

import sped.negocio.entidades.beans.BeanAula;
import sped.negocio.entidades.beans.BeanConfiguracionHorario;
import sped.negocio.entidades.beans.BeanCurso;
import sped.negocio.entidades.beans.BeanHorario;
import sped.negocio.entidades.beans.BeanLeccion;
import sped.negocio.entidades.beans.BeanMain;
import sped.negocio.entidades.beans.BeanProfesor;
import sped.negocio.entidades.beans.BeanRestriccionHorario;

public class bSessionbGestionarHorarios implements Serializable {
    @SuppressWarnings("compatibility:-8451409057410167524")
    private static final long serialVersionUID = 1L;
    
    private static final Format formatter = new SimpleDateFormat("hh:mm");

    private int exec;
    private int exec2;
    private int nroBloque;
    private int maxBloque;
    private int nidSede_aux;
    private int nidNivel_aux;
    private int posicionProfSalon;
    private int posicionLecc;
    private int posicionDia;
    private int posDiaLecHD;
    private int cont_curso;
    private int eventoEliminarModificar;
    //// valores para la eliminacion//
    private int lecc;
    private int nDia;
    private int nCurso;
    
    private String nidSede;
    private String nidNivel; 
    private String nidCurso;
    private String nidArea;
    //private String nidProfesor_Aula;    
    private String nomSedeNivel;
    private String nomSede;
    private String nomNivel;
    private String nidTipoVista = "0";
    private String titlePopDis;
    private String titleDiaLecHD;
    private String tituloEliminarModificar;
    private String nidProfesor;
    
    private String vectorDias[];
    
    private Color color;
    
    private List listaSedesChoice;
    private List listaNivelChoice;
    private List listaAreaChoice;
    private List listaCursoChoice;
    private List listaTipoVista;
    private List listaDuracion;
    private List listaItems;
    private List listaItems_aux;
    private List listaItemsSelect;
    private List selectedDiaLecHD;
    private List listAC_ProfSalon;
    private List listAC_Area;
    private List listAC_Curso;
    private List lstProfesor;
    private List lstSelecDias;
    private List lstDiasSelec;
    
    private List nidAC_ProfSalon;
    private List nidAC_Area;
    private List nidAC_Curso;
    
    private List lstDiaLecHD;
    
    private List<BeanHorario> listaHorario;
    private List<BeanProfesor> listaProfesor;
    private List<BeanLeccion> lstLecciones;
    private List<BeanLeccion> lstLecciones_aux;
    private List<BeanLeccion> lstLeccionesTotal;
    private List<BeanConfiguracionHorario> lstConfHorario;
    private List<Integer> horasRandom;
    private BeanLeccion leccion;
    private BeanLeccion leccion_aux;
    private BeanMain horario[][];
    private BeanProfesor profesor;
    private BeanProfesor profesor_aux;
    private BeanProfesor profesor_aux2;
    
    private BeanCurso curso;
    private BeanAula aula;
    private BeanProfesor dni;     // lo utilizo para agregar las lecciones
    
    private boolean disableChoiceNivel;
    private boolean renderHorario;
    private boolean renderImprimir;
    private boolean booleanTipoVista;
    private boolean booleanColor;
    private boolean booleanTipoColor;
    private boolean renderGenerarHorario;
    private boolean renderGenerarHorarioTotal;
    private boolean validarEliminacionLecciones;
    private boolean volverAGenerar;
    private boolean booDiaLecHD;
    private boolean renderAgregarCurso;
    private boolean renderEliminarModificar;
    
    private transient ChildPropertyTreeModel leccionesTree;
    
         
    private Time horas[];
    private Time horas_fin[];   
    
    private List<String> rango_horas;
    
    ///**** Metodos auxiliares ***///
    
    public void cargarDatosColor(){
        try{
            if(listaHorario.get(posicionProfSalon).getColor().length() == 6){
                color = Color.decode("#"+listaHorario.get(posicionProfSalon).getColor());
            }            
        }catch(Exception e){
            e.printStackTrace();
        } 
    }
    
    public String getCodigo_selc(){
        return listaHorario.get(posicionProfSalon).getCodigo();
    }
    
    public BeanHorario horarioSelect(){
        return listaHorario.get(posicionProfSalon);
    }
    
    public String getDia(int nDia){
        return vectorDias[nDia];
    }
    
    public String duracion(int nLeccion){
        return rango_horas.get(nLeccion);
    }
    
    /**
     * Metodo para saber si la leecion esta basia o no
     * @param n
     * @param leccion
     * @param dia
     * @return
     */
    public boolean renderLeccion(int n, int leccion, int dia){
        BeanMain main = getBeanMain(n, leccion, dia);
        return main != null && main.getNidMain() > 0;
    }
    
    public boolean renderRestricion(int n, int leccion, int dia){
        BeanMain main = getBeanMain(n, leccion, dia);
        return main != null && main.getNidMain() == -1;
    }
    
    public boolean renderOcupado(int n, int leccion, int dia){
        BeanMain main = getBeanMain(n, leccion, dia);
        //System.out.println(main != null && main.getNidMain() == -2);
        return main != null && main.getNidMain() == -2;
    }  
    
    public String descripcionOcupado(int n, int leccion, int dia){
        BeanMain main = getBeanMain(n, leccion, dia);
        return "El profesor esta ocupado en el aula: "+main.getNombreAula() +" de " + 
               formatter.format(main.getHoraInicio()) + " - " + formatter.format(main.getHoraFin());
    }
    
    public BeanMain getBeanMain(int n, int leccion, int dia){
        return listaHorario.get(n).getHorario()[leccion][dia];
    }
    
    public String descripcionCurso(int n, int leccion, int dia){        
        return getBeanMain(n, leccion, dia).getNombreCurso();
    }
    
    public String descripcionProfSalon(int n, int leccion, int dia){            
        if(!tipoVistaAula()){
            return listaHorario.get(n).getHorario()[leccion][dia].getNombreProfesor();
        }
        return listaHorario.get(n).getHorario()[leccion][dia].getNombreAula();
    }
    
    /**
     * Se optiene el color del curso
     * @param nLeccion
     * @param nDia
     * @return
     */
    public String nomColor(int n, int leccion, int dia){
        //t_color ? h[i][j].getColor_prof() : h[i][j].getColor()
        if(booleanTipoColor){
            return "#" + listaHorario.get(n).getHorario()[leccion][dia].getColor_prof();
        }else{
            return "#" + listaHorario.get(n).getHorario()[leccion][dia].getColor();   
        }        
    }
    
    public boolean tipoVistaAula(){
        return booleanTipoVista;
    }
    
    public String titileListaHorarioByPosicion(){
        return listaHorario.get(posicionProfSalon).getTitulo();
    }
    
    public String codigoListaHorarioByPosicion(int posicion){
        return listaHorario.get(posicion).getCodigo();
    }    
    
    ///**** Metodos auxiliares para ver los cursos en la ventana emergente ***///
    public BeanMain getBeanMain_aux(int l, int d){
        return horario[l][d];
    }
   
    public boolean renderLeccion_aux(int l, int d){
        return  horario[l][d]!= null && horario[l][d].getNidMain() > 0;
    }
    
    public String descripcionCurso_aux(int l, int d){      
        return horario[l][d].getNombreCurso();
    }
    
    public String descripcionProfSalon_aux(int l, int d){   
        if(!tipoVistaAula()){
            return horario[l][d].getNombreProfesor();
        }
        return horario[l][d].getNombreAula();
    }    
    
    public String nomColor_aux(int l, int d){
        return "#" + horario[l][d].getColor();
    }
    /////////////////////////// CAMBIO DE ESTADO A HORAS DISPONIBLES //////////////////////
    
    public void cambiarHorasDisponibles(){
        BeanMain main = horario[posicionLecc][posicionDia];
        if(main == null){
            main = new BeanMain();        
        }
        if(main.getRestric() == null){
            main.setRestric(new BeanRestriccionHorario());
        }                
        ///////CAMBIAMOS EL ESTADO SI ES "1" INADECUADO Y "0" ADECUADO
        if(main.getRestric().getEstado() == null){
            main.getRestric().setEstado("1");
        }else{
            main.getRestric().setEstado(main.getRestric().getEstado().compareTo("1") == 0 ? "0" : "1");   
        }                
        horario[posicionLecc][posicionDia] = main;
    }  
    
    public boolean horasDisponibles(int l, int d){
        BeanMain main = horario[l][d];
        return  main != null && main.getRestric() != null && 
                main.getRestric().getEstado() != null && main.getRestric().getEstado().compareTo("1") == 0;
    }
    
    public void horario_auxiliar(){
        horario = listaHorario.get(posicionProfSalon).getHorario();
    }
    
    public String titleExportacion(){
        String title = nomSedeNivel.replace(' ', '_');
        return "Horario_" + title + ".docx";
    }
    
    /////////////////////////// GET AND SET ///////////////////////

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

    public void setListaSedesChoice(List listaSedesChoice) {
        this.listaSedesChoice = listaSedesChoice;
    }

    public List getListaSedesChoice() {
        return listaSedesChoice;
    }

    public void setListaNivelChoice(List listaNivelChoice) {
        this.listaNivelChoice = listaNivelChoice;
    }

    public List getListaNivelChoice() {
        return listaNivelChoice;
    }

    public void setDisableChoiceNivel(boolean disableChoiceNivel) {
        this.disableChoiceNivel = disableChoiceNivel;
    }

    public boolean isDisableChoiceNivel() {
        return disableChoiceNivel;
    }

    public void setHoras(Time[] horas) {
        this.horas = horas;
    }

    public Time[] getHoras() {
        return horas;
    }

    public void setHoras_fin(Time[] horas_fin) {
        this.horas_fin = horas_fin;
    }

    public Time[] getHoras_fin() {
        return horas_fin;
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

    public void setRango_horas(List<String> rango_horas) {
        this.rango_horas = rango_horas;
    }

    public List<String> getRango_horas() {
        return rango_horas;
    }
    

    public void setRenderHorario(boolean renderHorario) {
        this.renderHorario = renderHorario;
    }

    public boolean isRenderHorario() {
        return renderHorario;
    }

    public void setNomSede(String nomSede) {
        this.nomSede = nomSede;
    }

    public String getNomSede() {
        return nomSede;
    }

    public void setNomNivel(String nomNivel) {
        this.nomNivel = nomNivel;
    }

    public String getNomNivel() {
        return nomNivel;
    }


    public void setListaTipoVista(List listaTipoVista) {
        this.listaTipoVista = listaTipoVista;
    }

    public List getListaTipoVista() {
        return listaTipoVista;
    }

    public void setNidTipoVista(String nidTipoVista) {
        this.nidTipoVista = nidTipoVista;
    }

    public String getNidTipoVista() {
        return nidTipoVista;
    }

    public void setListaHorario(List<BeanHorario> listaHorario) {
        this.listaHorario = listaHorario;
    }

    public List<BeanHorario> getListaHorario() {
        return listaHorario;
    }

    public void setVectorDias(String[] vectorDias) {
        this.vectorDias = vectorDias;
    }

    public String[] getVectorDias() {
        return vectorDias;
    }

    public void setNidSede_aux(int nidSede_aux) {
        this.nidSede_aux = nidSede_aux;
    }

    public int getNidSede_aux() {
        return nidSede_aux;
    }

    public void setNidNivel_aux(int nidNivel_aux) {
        this.nidNivel_aux = nidNivel_aux;
    }

    public int getNidNivel_aux() {
        return nidNivel_aux;
    }

    public void setListaItems(List listaItems) {
        this.listaItems = listaItems;
    }

    public List getListaItems() {
        return listaItems;
    }

    public void setListaItems_aux(List listaItems_aux) {
        this.listaItems_aux = listaItems_aux;
    }

    public List getListaItems_aux() {
        return listaItems_aux;
    }

    public void setTitlePopDis(String titlePopDis) {
        this.titlePopDis = titlePopDis;
    }

    public String getTitlePopDis() {
        return titlePopDis;
    }

    public void setPosicionProfSalon(int posicionProfSalon) {
        this.posicionProfSalon = posicionProfSalon;
    }

    public int getPosicionProfSalon() {
        return posicionProfSalon;
    }

    public void setRenderImprimir(boolean renderImprimir) {
        this.renderImprimir = renderImprimir;
    }

    public boolean isRenderImprimir() {
        return renderImprimir;
    }

    public void setPosicionLecc(int posicionLecc) {
        this.posicionLecc = posicionLecc;
    }

    public int getPosicionLecc() {
        return posicionLecc;
    }

    public void setPosicionDia(int posicionDia) {
        this.posicionDia = posicionDia;
    }

    public int getPosicionDia() {
        return posicionDia;
    }

    public void setHorario(BeanMain[][] horario) {
        this.horario = horario;
    }

    public BeanMain[][] getHorario() {
        return horario;
    }

    public void setBooleanTipoVista(boolean booleanTipoVista) {
        this.booleanTipoVista = booleanTipoVista;
    }

    public boolean isBooleanTipoVista() {
        return booleanTipoVista;
    }

    public void setListaProfesor(List<BeanProfesor> listaProfesor) {
        this.listaProfesor = listaProfesor;
    }

    public List<BeanProfesor> getListaProfesor() {
        return listaProfesor;
    }

    public void setProfesor(BeanProfesor profesor) {
        this.profesor = profesor;
    }

    public BeanProfesor getProfesor() {
        return profesor;
    }

    public void setProfesor_aux(BeanProfesor profesor_aux) {
        this.profesor_aux = profesor_aux;
    }

    public BeanProfesor getProfesor_aux() {
        return profesor_aux;
    }

    public void setExec2(int exec2) {
        this.exec2 = exec2;
    }

    public int getExec2() {
        return exec2;
    }

    public void setListaAreaChoice(List listaAreaChoice) {
        this.listaAreaChoice = listaAreaChoice;
    }

    public List getListaAreaChoice() {
        return listaAreaChoice;
    }

    public void setListaCursoChoice(List listaCursoChoice) {
        this.listaCursoChoice = listaCursoChoice;
    }

    public List getListaCursoChoice() {
        return listaCursoChoice;
    }

    public void setNidArea(String nidArea) {
        this.nidArea = nidArea;
    }

    public String getNidArea() {
        return nidArea;
    }

    public void setLstLecciones(List<BeanLeccion> lstLecciones) {
        this.lstLecciones = lstLecciones;
    }

    public List<BeanLeccion> getLstLecciones() {
        return lstLecciones;
    }

    public void setNomSedeNivel(String nomSedeNivel) {
        this.nomSedeNivel = nomSedeNivel;
    }

    public String getNomSedeNivel() {
        return nomSedeNivel;
    }

    public void setLeccion(BeanLeccion leccion) {
        this.leccion = leccion;
    }

    public BeanLeccion getLeccion() {
        return leccion;
    }

    public void setListaDuracion(List listaDuracion) {
        this.listaDuracion = listaDuracion;
    }

    public List getListaDuracion() {
        return listaDuracion;
    }

    public void setBooleanColor(boolean booleanColor) {
        this.booleanColor = booleanColor;
    }

    public boolean isBooleanColor() {
        return booleanColor;
    }

    public void setBooleanTipoColor(boolean booleanTipoColor) {
        this.booleanTipoColor = booleanTipoColor;
    }

    public boolean isBooleanTipoColor() {
        return booleanTipoColor;
    }

    public void setListaItemsSelect(List listaItemsSelect) {
        this.listaItemsSelect = listaItemsSelect;
    }

    public List getListaItemsSelect() {
        return listaItemsSelect;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public void setLstConfHorario(List<BeanConfiguracionHorario> lstConfHorario) {
        this.lstConfHorario = lstConfHorario;
    }

    public List<BeanConfiguracionHorario> getLstConfHorario() {
        return lstConfHorario;
    }

    public void setLstLeccionesTotal(List<BeanLeccion> lstLeccionesTotal) {
        this.lstLeccionesTotal = lstLeccionesTotal;
    }

    public List<BeanLeccion> getLstLeccionesTotal() {
        return lstLeccionesTotal;
    }

    public void setHorasRandom(List<Integer> horasRandom) {
        this.horasRandom = horasRandom;
    }

    public List<Integer> getHorasRandom() {
        return horasRandom;
    }

    public void setRenderGenerarHorario(boolean renderGenerarHorario) {
        this.renderGenerarHorario = renderGenerarHorario;
    }

    public boolean isRenderGenerarHorario() {
        return renderGenerarHorario;
    }

    public void setLeccionesTree(ChildPropertyTreeModel leccionesTree) {
        this.leccionesTree = leccionesTree;
    }

    public ChildPropertyTreeModel getLeccionesTree() {
        return leccionesTree;
    }

    public void setRenderGenerarHorarioTotal(boolean renderGenerarHorarioTotal) {
        this.renderGenerarHorarioTotal = renderGenerarHorarioTotal;
    }

    public boolean isRenderGenerarHorarioTotal() {
        return renderGenerarHorarioTotal;
    }

    public void setCurso(BeanCurso curso) {
        this.curso = curso;
    }

    public BeanCurso getCurso() {
        return curso;
    }

    public void setAula(BeanAula aula) {
        this.aula = aula;
    }

    public BeanAula getAula() {
        return aula;
    }

    public void setDni(BeanProfesor dni) {
        this.dni = dni;
    }

    public BeanProfesor getDni() {
        return dni;
    }

    public void setValidarEliminacionLecciones(boolean validarEliminacionLecciones) {
        this.validarEliminacionLecciones = validarEliminacionLecciones;
    }

    public boolean isValidarEliminacionLecciones() {
        return validarEliminacionLecciones;
    }

    public void setVolverAGenerar(boolean volverAGenerar) {
        this.volverAGenerar = volverAGenerar;
    }

    public boolean isVolverAGenerar() {
        return volverAGenerar;
    }

    public void setPosDiaLecHD(int posDiaLecHD) {
        this.posDiaLecHD = posDiaLecHD;
    }

    public int getPosDiaLecHD() {
        return posDiaLecHD;
    }

    public void setTitleDiaLecHD(String titleDiaLecHD) {
        this.titleDiaLecHD = titleDiaLecHD;
    }

    public String getTitleDiaLecHD() {
        return titleDiaLecHD;
    }

    public void setSelectedDiaLecHD(List selectedDiaLecHD) {
        this.selectedDiaLecHD = selectedDiaLecHD;
    }

    public List getSelectedDiaLecHD() {
        return selectedDiaLecHD;
    }

    public void setLstDiaLecHD(List lstDiaLecHD) {
        this.lstDiaLecHD = lstDiaLecHD;
    }

    public List getLstDiaLecHD() {
        return lstDiaLecHD;
    }

    public void setBooDiaLecHD(boolean booDiaLecHD) {
        this.booDiaLecHD = booDiaLecHD;
    }

    public boolean isBooDiaLecHD() {
        return booDiaLecHD;
    }

    public void setLstLecciones_aux(List<BeanLeccion> lstLecciones_aux) {
        this.lstLecciones_aux = lstLecciones_aux;
    }

    public List<BeanLeccion> getLstLecciones_aux() {
        return lstLecciones_aux;
    }

    public void setRenderAgregarCurso(boolean renderAgregarCurso) {
        this.renderAgregarCurso = renderAgregarCurso;
    }

    public boolean isRenderAgregarCurso() {
        return renderAgregarCurso;
    }
    
    public boolean isRenderSelecionarCurso(int l, int d){
        return renderAgregarCurso && horario[l][d] == null && nidCurso != null && dni != null;
    }
    
    public boolean isRenderSelecionarCurso_aux(int l, int d){
        return renderAgregarCurso && horario[l][d]!= null && horario[l][d].getNidMain() == 0;
    }

    public void setListAC_ProfSalon(List listAC_ProfSalon) {
        this.listAC_ProfSalon = listAC_ProfSalon;
    }

    public List getListAC_ProfSalon() {
        return listAC_ProfSalon;
    }

    public void setListAC_Area(List listAC_Area) {
        this.listAC_Area = listAC_Area;
    }

    public List getListAC_Area() {
        return listAC_Area;
    }

    public void setListAC_Curso(List listAC_Curso) {
        this.listAC_Curso = listAC_Curso;
    }

    public List getListAC_Curso() {
        return listAC_Curso;
    }

    public void setNidAC_ProfSalon(List nidAC_ProfSalon) {
        this.nidAC_ProfSalon = nidAC_ProfSalon;
    }

    public List getNidAC_ProfSalon() {
        return nidAC_ProfSalon;
    }

    public void setNidAC_Area(List nidAC_Area) {
        this.nidAC_Area = nidAC_Area;
    }

    public List getNidAC_Area() {
        return nidAC_Area;
    }

    public void setNidAC_Curso(List nidAC_Curso) {
        this.nidAC_Curso = nidAC_Curso;
    }

    public List getNidAC_Curso() {
        return nidAC_Curso;
    }

    public void setLeccion_aux(BeanLeccion leccion_aux) {
        this.leccion_aux = leccion_aux;
    }

    public BeanLeccion getLeccion_aux() {
        return leccion_aux;
    }

    public void setCont_curso(int cont_curso) {
        this.cont_curso = cont_curso;
    }

    public int getCont_curso() {
        return cont_curso;
    }

    public void setTituloEliminarModificar(String tituloEliminarModificar) {
        this.tituloEliminarModificar = tituloEliminarModificar;
    }

    public String getTituloEliminarModificar() {
        return tituloEliminarModificar;
    }

    public void setNidProfesor(String nidProfesor) {
        this.nidProfesor = nidProfesor;
    }

    public String getNidProfesor() {
        return nidProfesor;
    }

    public void setProfesor_aux2(BeanProfesor profesor_aux2) {
        this.profesor_aux2 = profesor_aux2;
    }

    public BeanProfesor getProfesor_aux2() {
        return profesor_aux2;
    }

    public void setRenderEliminarModificar(boolean renderEliminarModificar) {
        this.renderEliminarModificar = renderEliminarModificar;
    }

    public boolean isRenderEliminarModificar() {
        return renderEliminarModificar;
    }

    public void setLstProfesor(List lstProfesor) {
        this.lstProfesor = lstProfesor;
    }

    public List getLstProfesor() {
        return lstProfesor;
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

    public void setEventoEliminarModificar(int eventoEliminarModificar) {
        this.eventoEliminarModificar = eventoEliminarModificar;
    }

    public int getEventoEliminarModificar() {
        return eventoEliminarModificar;
    }

    public void setLecc(int lecc) {
        this.lecc = lecc;
    }

    public int getLecc() {
        return lecc;
    }

    public void setNCurso(int nCurso) {
        this.nCurso = nCurso;
    }

    public int getNCurso() {
        return nCurso;
    }

    public void setNDia(int nDia) {
        this.nDia = nDia;
    }

    public int getNDia() {
        return nDia;
    }
}
