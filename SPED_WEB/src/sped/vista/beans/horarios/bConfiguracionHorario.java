package sped.vista.beans.horarios;

import java.sql.Time;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import javax.ejb.EJB;

import javax.faces.event.ValueChangeEvent;

import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.input.RichSelectOneChoice;

import oracle.adf.view.rich.component.rich.nav.RichButton;

import sped.negocio.LNSF.IR.LN_C_SFConfiguracionEventoHorarioRemoto;
import sped.negocio.LNSF.IR.LN_C_SFNivelRemote;
import sped.negocio.LNSF.IR.LN_C_SFSedeRemote;

import sped.negocio.LNSF.IR.LN_T_SFConfiguracionHorarioRemoto;
import sped.negocio.LNSF.IR.LN_T_SFDuracionHorarioRemoto;
import sped.negocio.entidades.beans.BeanConfiguracionEventoHorario;
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
    
    private RichSelectOneChoice choiceSede;
    private RichSelectOneChoice choiceNivel;
    private RichButton btnAgregarEventoHorario;
    private RichTable tbEventoHorario;
    private RichSelectOneChoice choiceEventoHorario;
    private RichButton btnGuardar;

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
                                                                               sessionConfiguracionHorario.getListaEventosHorarioTabla().get(i).getNidConfev());
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
        beanConfEv.setHoraInicio(sessionConfiguracionHorario.getInputHoraInicioEventoHorario());  
        beanConfEv.setHoraFin(sessionConfiguracionHorario.getInputHoraFinEventoHorario());
        sessionConfiguracionHorario.getListaEventosHorarioTabla().add(beanConfEv);
        }
        Utils.addTarget(tbEventoHorario);
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
}
