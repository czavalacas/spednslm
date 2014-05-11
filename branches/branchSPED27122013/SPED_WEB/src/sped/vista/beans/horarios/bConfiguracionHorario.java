package sped.vista.beans.horarios;

import java.sql.Time;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import javax.ejb.EJB;

import javax.faces.event.ValueChangeEvent;

import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.input.RichInputNumberSpinbox;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.input.RichSelectOneChoice;

import oracle.adf.view.rich.component.rich.nav.RichButton;

import sped.negocio.LNSF.IR.LN_C_SFConfiguracionEventoHorarioRemoto;
import sped.negocio.LNSF.IR.LN_C_SFConfiguracionHorarioRemote;
import sped.negocio.LNSF.IR.LN_C_SFDuracionHorarioRemote;
import sped.negocio.LNSF.IR.LN_C_SFNivelRemote;
import sped.negocio.LNSF.IR.LN_C_SFSedeRemote;

import sped.negocio.LNSF.IR.LN_T_SFConfiguracionHorarioRemoto;
import sped.negocio.LNSF.IR.LN_T_SFDuracionHorarioRemoto;
import sped.negocio.entidades.beans.BeanConfiguracionEventoHorario;
import sped.negocio.entidades.beans.BeanConfiguracionHorario;
import sped.negocio.entidades.beans.BeanDuracionHorario;
import sped.negocio.entidades.beans.BeanUsuario;

import sped.vista.Utils.Utils;

public class bConfiguracionHorario {
    
    private bSessionConfiguracionHorario sessionConfiguracionHorario;
    
    @EJB
    private LN_C_SFSedeRemote ln_C_SFSedeRemote;
    @EJB
    private LN_C_SFNivelRemote ln_C_SFNivelRemote;
    @EJB
    private LN_C_SFConfiguracionEventoHorarioRemoto ln_C_SFConfiguracionEventoHorarioRemoto;
    @EJB
    private LN_T_SFConfiguracionHorarioRemoto ln_T_SFConfiguracionHorarioRemoto;
    @EJB
    private LN_T_SFDuracionHorarioRemoto ln_T_SFDuracionHorarioRemoto;
    @EJB
    private LN_C_SFDuracionHorarioRemote ln_C_SFDuracionHorarioRemote;
    @EJB
    private LN_C_SFConfiguracionHorarioRemote ln_C_SFConfiguracionHorarioRemote;
    
    private RichSelectOneChoice choiceSede;
    private RichSelectOneChoice choiceNivel;
    private RichButton btnAgregarEventoHorario;
    private RichTable tbEventoHorario;
    private RichSelectOneChoice choiceEventoHorario;
    private RichButton btnGuardar;
    private RichInputText inputHoraInicioRestriccion;
    private RichInputText inputHoraFinRestriccion;
    private RichButton btnRemoverEventoHorario;
    private RichInputText inputHoraInicioClases;
    private RichInputNumberSpinbox inputNumeroBloques;
    private RichInputText inputDuracionXBloque;
    private RichInputNumberSpinbox inputMaxBloquesXCurs;
    private RichPopup popupConfigurarNuevaRestriccion;
    private RichButton btnEditarRestriccion;

    public bConfiguracionHorario() {

    }
    
    @PostConstruct
    public void methodInvokeOncedOnPageLoad() {   
        if(sessionConfiguracionHorario.getExec() == 0){    
            sessionConfiguracionHorario.setExec(1);     
        llenarCombos();
        }        
    }   
    
    
    public void getNivelesBySede(ValueChangeEvent valueChangeEvent) {
      if(choiceSede!=null){
       sessionConfiguracionHorario.setListaNivelesChoice(Utils.llenarCombo(ln_C_SFNivelRemote.getAllNivelesBySedes(choiceSede.getValue().toString())));    
       sessionConfiguracionHorario.setEstadoDisableChoiceNive(false);
      }
       Utils.addTarget(choiceNivel);
       
    }
    
    public String guardarConfiguracionDeHorario() {
        for(int i=0; i<sessionConfiguracionHorario.getListaEventosHorarioTabla().size(); i++){
            ln_T_SFConfiguracionHorarioRemoto.registrarConfiguracionHorario_LN(Integer.parseInt(sessionConfiguracionHorario.getNidSedeChoice()), 
                                                                               Integer.parseInt(sessionConfiguracionHorario.getNidNivelChoice()), 
                                                                               Time.valueOf(sessionConfiguracionHorario.getListaEventosHorarioTabla().get(i).getHoraInicio()+":00"), 
                                                                               Time.valueOf(sessionConfiguracionHorario.getListaEventosHorarioTabla().get(i).getHoraFin()+":00"), 
                                                                               sessionConfiguracionHorario.getListaEventosHorarioTabla().get(i).getStmconfev().getNidConfev());
        }
        
        ln_T_SFDuracionHorarioRemoto.registrarDuracionHorario_LN(Integer.parseInt(sessionConfiguracionHorario.getNidSedeChoice()), 
                                                                 Integer.parseInt(sessionConfiguracionHorario.getNidNivelChoice()), 
                                                                 Time.valueOf(sessionConfiguracionHorario.getInputHoraInicioClases()+":00"),
                                                                 Time.valueOf(sessionConfiguracionHorario.getDuracionPorBloque()+":00"), 
                                                                 sessionConfiguracionHorario.getMaxBloquesPorCurso(), 
                                                                 sessionConfiguracionHorario.getNumeroDeBloques());
        return null;
    }
    
    public String agregarEventoHorario() throws ParseException {
        if(choiceEventoHorario.getValue()!=null){
        BeanConfiguracionEventoHorario beanConfEv=ln_C_SFConfiguracionEventoHorarioRemoto.getEventoHorarioByID(Integer.parseInt(choiceEventoHorario.getValue().toString()));
        BeanConfiguracionHorario beanConHora=new BeanConfiguracionHorario();
     /*   beanConfEv.setHoraInicio();  
        beanConfEv.setHoraFin();*/
            beanConHora.setStmconfev(beanConfEv);
            beanConHora.setHoraInicio(sessionConfiguracionHorario.getInputHoraInicioEventoHorario());
            beanConHora.setHoraFin(sessionConfiguracionHorario.getInputHoraFinEventoHorario());
        sessionConfiguracionHorario.setNidEventoHorario(null);
        sessionConfiguracionHorario.setInputHoraInicioEventoHorario(null);
        sessionConfiguracionHorario.setInputHoraFinEventoHorario(null);
        sessionConfiguracionHorario.getListaEventosHorarioTabla().add(beanConHora);
            sessionConfiguracionHorario.setEstadoinPutHoraInicioRestriccion(true);
            sessionConfiguracionHorario.setEstadoinPutHoraFinRestriccion(true);    
            estadosPanel2(false);
        }
        Utils.addTargetMany(choiceEventoHorario,inputHoraInicioRestriccion,inputHoraFinRestriccion,tbEventoHorario);
        return null;
    }
    
    public void llenarCombos(){
        sessionConfiguracionHorario.setListaSedesChoice(Utils.llenarCombo(ln_C_SFSedeRemote.getAllSedes()));
        
        sessionConfiguracionHorario.setListaEventosHorariosChoice(Utils.llenarCombo(ln_C_SFConfiguracionEventoHorarioRemoto.getAllEventosDeHorario()));
    }

    public void setSessionConfiguracionHorario(bSessionConfiguracionHorario sessionConfiguracionHorario) {
        this.sessionConfiguracionHorario = sessionConfiguracionHorario;
    }

    public bSessionConfiguracionHorario getSessionConfiguracionHorario() {
        return sessionConfiguracionHorario;
    }

    public void setChoiceSede(RichSelectOneChoice choiceSede) {
        this.choiceSede = choiceSede;
    }

    public RichSelectOneChoice getChoiceSede() {
        return choiceSede;
    }
    
    public void setLn_C_SFSedeRemote(LN_C_SFSedeRemote ln_C_SFSedeRemote) {
        this.ln_C_SFSedeRemote = ln_C_SFSedeRemote;
    }

    public LN_C_SFSedeRemote getLn_C_SFSedeRemote() {
        return ln_C_SFSedeRemote;
    }

    public void setChoiceNivel(RichSelectOneChoice choiceNivel) {
        this.choiceNivel = choiceNivel;
    }

    public RichSelectOneChoice getChoiceNivel() {
        return choiceNivel;
    }

    public void setBtnAgregarEventoHorario(RichButton btnAgregarEventoHorario) {
        this.btnAgregarEventoHorario = btnAgregarEventoHorario;
    }

    public RichButton getBtnAgregarEventoHorario() {
        return btnAgregarEventoHorario;
    }

    public void setTbEventoHorario(RichTable tbEventoHorario) {
        this.tbEventoHorario = tbEventoHorario;
    }

    public RichTable getTbEventoHorario() {
        return tbEventoHorario;
    }

    public void setChoiceEventoHorario(RichSelectOneChoice choiceEventoHorario) {
        this.choiceEventoHorario = choiceEventoHorario;
    }

    public RichSelectOneChoice getChoiceEventoHorario() {
        return choiceEventoHorario;
    }

    public void setBtnGuardar(RichButton btnGuardar) {
        this.btnGuardar = btnGuardar;
    }

    public RichButton getBtnGuardar() {
        return btnGuardar;
    }

    public void setInputHoraInicioRestriccion(RichInputText inputHoraInicioRestriccion) {
        this.inputHoraInicioRestriccion = inputHoraInicioRestriccion;
    }

    public RichInputText getInputHoraInicioRestriccion() {
        return inputHoraInicioRestriccion;
    }

    public void setInputHoraFinRestriccion(RichInputText inputHoraFinRestriccion) {
        this.inputHoraFinRestriccion = inputHoraFinRestriccion;
    }

    public RichInputText getInputHoraFinRestriccion() {
        return inputHoraFinRestriccion;
    }

    public void setBtnRemoverEventoHorario(RichButton btnRemoverEventoHorario) {
        this.btnRemoverEventoHorario = btnRemoverEventoHorario;
    }

    public RichButton getBtnRemoverEventoHorario() {
        return btnRemoverEventoHorario;
    }

    public void setInputHoraInicioClases(RichInputText inputHoraInicioClases) {
        this.inputHoraInicioClases = inputHoraInicioClases;
    }

    public RichInputText getInputHoraInicioClases() {
        return inputHoraInicioClases;
    }

    public void setInputNumeroBloques(RichInputNumberSpinbox inputNumeroBloques) {
        this.inputNumeroBloques = inputNumeroBloques;
    }

    public RichInputNumberSpinbox getInputNumeroBloques() {
        return inputNumeroBloques;
    }

    public void setInputDuracionXBloque(RichInputText inputDuracionXBloque) {
        this.inputDuracionXBloque = inputDuracionXBloque;
    }

    public RichInputText getInputDuracionXBloque() {
        return inputDuracionXBloque;
    }

    public void setInputMaxBloquesXCurs(RichInputNumberSpinbox inputMaxBloquesXCurs) {
        this.inputMaxBloquesXCurs = inputMaxBloquesXCurs;
    }

    public RichInputNumberSpinbox getInputMaxBloquesXCurs() {
        return inputMaxBloquesXCurs;
    }

    public String realizarNuevaRestriccion() {
        sessionConfiguracionHorario.setEstadoDisableChoiceRestriccion(false);     
        Utils.addTargetMany(choiceEventoHorario);
        /**Estado remover restriccion disablear al seleccionar una restriccion de la tabla*/
      //  estadosPanel2(true);      
        return null;
    }
    
    public String estadosPanel1(boolean valor){
        sessionConfiguracionHorario.setEstadoinPutHoraInicioRestriccion(valor);
        sessionConfiguracionHorario.setEstadoinPutHoraFinRestriccion(valor);  
        sessionConfiguracionHorario.setEstadoBtnAgregarRestriccion(valor);
        Utils.addTargetMany(inputHoraInicioRestriccion,inputHoraFinRestriccion,btnAgregarEventoHorario);
        return null;
    }
    
    public String estadosPanel2(boolean valor){
        sessionConfiguracionHorario.setEstadoHoraInicioClases(valor);
        sessionConfiguracionHorario.setEstadoNumBloques(valor);
        sessionConfiguracionHorario.setEstadoDuracionPorBloque(valor);
        sessionConfiguracionHorario.setEstadoMaxBloquesXCurso(valor);
        sessionConfiguracionHorario.setEstadoDisableBtnGuardar(valor);
        Utils.addTargetMany(inputHoraInicioClases,inputNumeroBloques,inputDuracionXBloque,inputMaxBloquesXCurs,btnGuardar);
        return null;
    }
    
    public String limpiarTodosLosCampos(){
        sessionConfiguracionHorario.setNidEventoHorario(null);
        sessionConfiguracionHorario.setInputHoraInicioEventoHorario(null);
        sessionConfiguracionHorario.setInputHoraFinEventoHorario(null);
        sessionConfiguracionHorario.getListaEventosHorarioTabla().clear();
        sessionConfiguracionHorario.setInputHoraInicioClases(null);
        sessionConfiguracionHorario.setNumeroDeBloques(0);
        sessionConfiguracionHorario.setDuracionPorBloque(null);
        sessionConfiguracionHorario.setMaxBloquesPorCurso(0);
        Utils.addTargetMany(choiceEventoHorario,inputDuracionXBloque,inputHoraFinRestriccion,inputHoraInicioClases,inputHoraInicioRestriccion,inputNumeroBloques,tbEventoHorario,inputMaxBloquesXCurs);
        return null;
    }
    public String cancelarNuevaRestriccion() {
        popupConfigurarNuevaRestriccion.hide();
        return null;
    }

    public String realizarBusquedaDeRestricciones() {
        BeanDuracionHorario beanDura= ln_C_SFDuracionHorarioRemote.getDuracionHorarioBySedeNivel(Integer.parseInt(sessionConfiguracionHorario.getNidSedeChoice()), Integer.parseInt(sessionConfiguracionHorario.getNidNivelChoice()));
         if(beanDura!=null){
             List<BeanConfiguracionHorario> listBeanConf=ln_C_SFConfiguracionHorarioRemote.getConfiguracionBySedeNivel(Integer.parseInt(sessionConfiguracionHorario.getNidSedeChoice()), Integer.parseInt(sessionConfiguracionHorario.getNidNivelChoice()));
            for(int i=0; i<listBeanConf.size(); i++){                
               /**AL NO SER DATETIME  NO PUEDO DARLE UN CONVERTDATETIME EN LA VISTA POR LO QUE HAY QUE FORMATEAR LAS FECHAS A HH:MM PARA 
                 * LA VISUALIZACION CORRECTA**/
                listBeanConf.get(i).setHoraInicio(formatearTime(listBeanConf.get(i).getHora_inicio()));
                listBeanConf.get(i).setHoraFin(formatearTime(listBeanConf.get(i).getHora_fin()));     
            }
              sessionConfiguracionHorario.setListaEventosHorarioTabla(listBeanConf);              
              sessionConfiguracionHorario.setInputHoraInicioClases(formatearTime(beanDura.getHora_inicio()));
              sessionConfiguracionHorario.setMaxBloquesPorCurso(beanDura.getMax_bloque());
              sessionConfiguracionHorario.setNumeroDeBloques(beanDura.getNro_bloque());
              sessionConfiguracionHorario.setDuracionPorBloque(formatearTime(beanDura.getDuracion()));
             
              sessionConfiguracionHorario.setEstadoBtnEditarRestriccion(false);
             Utils.addTargetMany(tbEventoHorario,inputDuracionXBloque,inputHoraInicioClases,inputMaxBloquesXCurs,inputNumeroBloques,btnEditarRestriccion);
        }else{
            Utils.showPopUpMIDDLE(popupConfigurarNuevaRestriccion);
        }
        return null;
    }
    public String formatearTime(Time hora){
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        Date fechaActual = hora;
        String fechaConFormato = sdf.format(fechaActual);     
        return fechaConFormato;
    }

    public void setPopupConfigurarNuevaRestriccion(RichPopup popupConfigurarNuevaRestriccion) {
        this.popupConfigurarNuevaRestriccion = popupConfigurarNuevaRestriccion;
    }

    public RichPopup getPopupConfigurarNuevaRestriccion() {
        return popupConfigurarNuevaRestriccion;
    }

    public void seleccionaRestriccion(ValueChangeEvent valueChangeEvent) {
        System.out.println(valueChangeEvent.getNewValue());
        if(valueChangeEvent.getNewValue()!=null){
            estadosPanel1(false);
            estadosPanel2(true);    
              }
        if(valueChangeEvent.getNewValue()==null){
           estadosPanel1(true);
           estadosPanel2(false);
              }
    }
  
    public void eventoChoiceNivel(ValueChangeEvent valueChangeEvent) {
       limpiarTodosLosCampos();
       estadosPanel2(true);
       estadosPanel1(true);
       sessionConfiguracionHorario.setEstadoDisableChoiceRestriccion(true);
       sessionConfiguracionHorario.setEstadoBtnEditarRestriccion(true);
       Utils.addTargetMany(choiceEventoHorario,btnEditarRestriccion);
    }

    public String editarConfiguracion() {
       sessionConfiguracionHorario.setEstadoDisableChoiceRestriccion(false);
       estadosPanel2(false);
       Utils.addTarget(choiceEventoHorario);
        return null;
    }

    public void setBtnEditarRestriccion(RichButton btnEditarRestriccion) {
        this.btnEditarRestriccion = btnEditarRestriccion;
    }

    public RichButton getBtnEditarRestriccion() {
        return btnEditarRestriccion;
    }
}
