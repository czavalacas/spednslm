package sped.vista.beans.horarios;


import java.sql.Time;

import java.util.ArrayList;
import java.util.Calendar;

import java.util.GregorianCalendar;

import java.util.List;

import javax.annotation.PostConstruct;

import javax.ejb.EJB;

import javax.faces.component.UISelectItems;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import javax.faces.model.SelectItem;

import oracle.adf.view.rich.component.rich.RichPopup;

import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.input.RichSelectOneChoice;

import oracle.adf.view.rich.component.rich.layout.RichPanelFormLayout;

import sped.negocio.LNSF.IR.LN_C_SFHorarioRemote;
import sped.negocio.LNSF.IR.LN_C_SFMainRemote;
import sped.negocio.LNSF.IR.LN_C_SFUtilsRemote;

import sped.negocio.entidades.beans.BeanHorario;

import sped.negocio.entidades.beans.BeanMain;

import sped.vista.Utils.Utils;

public class bGestionarHorario {    
    @EJB
    private LN_C_SFUtilsRemote ln_C_SFUtilsRemote;
    @EJB
    private LN_C_SFHorarioRemote ln_C_SFHorarioRemote;
    @EJB
    private LN_C_SFMainRemote ln_C_SFMainRemote;
    private bSessionGestionarHorario sessionGestionarHorario;
    FacesContext ctx = FacesContext.getCurrentInstance();
    private RichSelectOneChoice choiceSede;
    private UISelectItems si1;
    private RichSelectOneChoice choiceNivel;
    private UISelectItems si2;
    private RichSelectOneChoice choiceAula;
    private UISelectItems si3;
    private RichPanelFormLayout pfCombox;
    private RichPopup popGHor;
    private RichSelectOneChoice choiceProfesor;
    private UISelectItems si4;
    private RichSelectOneChoice choiceCurso;
    private UISelectItems si5;
    private RichSelectOneChoice choiceArea;
    private UISelectItems si6;
    private RichPanelFormLayout pfGHor;
    private int nroHoras;
    private RichInputText itHor;
    private BeanMain beanMain;

    public bGestionarHorario() {        
    }

    @PostConstruct
    public void methodInvokeOncedOnPageLoad() {
        if(sessionGestionarHorario.getExec() == 0){
            sessionGestionarHorario.setExec(1);
            sessionGestionarHorario.setLstSede(Utils.llenarCombo(ln_C_SFUtilsRemote.getSedes_LN()));
            sessionGestionarHorario.setLstNivel(Utils.llenarCombo(ln_C_SFUtilsRemote.getNiveles_LN())); 
            sessionGestionarHorario.setLstArea(Utils.llenarCombo(ln_C_SFUtilsRemote.getAreas_LN_WS()));
            sessionGestionarHorario.setLstProfesor(Utils.llenarComboString(ln_C_SFUtilsRemote.getProfesor_LN()));
        }
    } 
    
    public String abrirPopGenerarHorario() {
        Utils.showPopUpMIDDLE(popGHor);        
        sessionGestionarHorario.setLstBeanMain(new ArrayList());
        llenarVectorHoras();
        validaHorario();
        return null;
    }
    
    public void validaHorario(){
        BeanMain horario[][] = new BeanMain[sessionGestionarHorario.getNroBloque()][5];
        int horas_libres = sessionGestionarHorario.getNroBloque()*5;
        List<BeanMain> lstLecciones = ln_C_SFMainRemote.getLstMainByAttr_LN(sessionGestionarHorario.getNidAula());
        if(lstLecciones != null){
            for(BeanMain main : lstLecciones){
                int dia = main.getNDia();
                int hora = encuentraHora(main.getHoraInicio());
                if(hora != -1){
                    horario[hora][dia] = main;
                    horas_libres --;
                }                
            }
        }
        Utils.putSession("Horas", horas_libres);
        sessionGestionarHorario.setHorario(horario);
        //////////////imprimo para ver como va
        for(int i = 0 ; i < sessionGestionarHorario.getNroBloque(); i++){
            for(int j = 0; j < 5 ; j++){
                if(horario[i][j] != null){
                    System.out.print("X ");
                }else{
                    System.out.print("O ");
                }
            }
            System.out.println("");
        }
    }
    
    public int encuentraHora(Time time){
        for(int i = 0; i < sessionGestionarHorario.getHoras().length; i++){
            if(time.toString().compareTo(sessionGestionarHorario.getHoras()[i]) == 0){
                return i;
            }
        }
        return -1;
    }
   
    /**
     * Se almacena las lecciones para luego generar el horario
     * @return
     */
    public String agregarLeccion() {
        BeanMain main = new BeanMain();
        main.setDniProfesor(sessionGestionarHorario.getNidProfesor());
        main.setNidCurso(Integer.parseInt(sessionGestionarHorario.getNidCurso()));
        main.setNroHoras(nroHoras);
        main.setNombreProfesor(sessionGestionarHorario.getNombreProfesor());
        main.setNombreCurso(sessionGestionarHorario.getNombreCurso());
        main.setNombreArea(sessionGestionarHorario.getNombreArea());
        if(sessionGestionarHorario.getLstBeanMain().size() != 0){
            for(BeanMain m : sessionGestionarHorario.getLstBeanMain()){
                if(m.getNidAula() == main.getNidAula() &&
                   m.getNidCurso() == main.getNidCurso() &&
                   m.getDniProfesor() == main.getDniProfesor()){
                    Utils.mostrarMensaje(ctx,"La leccion agregada ya existe", null, 2);
                    main = null;
                    break;
                }
            }
        }
        if(main != null){
            sessionGestionarHorario.getLstBeanMain().add(main);
            int horas_libres = Integer.parseInt(Utils.getSession("Horas").toString()) - main.getNroHoras();        
            Utils.putSession("Horas", horas_libres);
        }                
        Utils.addTarget(pfGHor);
        return null;
    }
    
    /**
     * Se elimina la leccion de la lista BeanMain que se encuentra en sesssion
     * @return
     */
    public String eliminarLeccion() {
        int horas_libres = Integer.parseInt(Utils.getSession("Horas").toString()) + beanMain.getNroHoras();        
        Utils.putSession("Horas", horas_libres);
        sessionGestionarHorario.getLstBeanMain().remove(beanMain);
        Utils.addTarget(pfGHor);
        return null;
    }
    
    
    public void generarHorario(ActionEvent actionEvent) {
        BeanMain horario[][] = sessionGestionarHorario.getHorario();
        List<BeanMain> lst = sessionGestionarHorario.getLstBeanMain();
        for(int i = 0; i < lst.size(); i++){
            encuentraEspacio(horario, lst.get(i));
        }
        for(int i = 0 ; i < sessionGestionarHorario.getNroBloque(); i++){
            for(int j = 0; j < 5 ; j++){
                if(horario[i][j] != null){
                    System.out.print("X ");
                }else{
                    System.out.print("O ");
                }
            }
            System.out.println("");
        }
    }
    
    public void encuentraEspacio(BeanMain horario[][], BeanMain main){
        int hora = (int) Math.round((Math.random()*7)); 
        int leccion = (int) Math.round((Math.random()*4)); 
        if(horario[hora][leccion] == null){
            if(hora +1 % 2 == 0){
                grabarEspacio(horario, main, hora, leccion);
            }
        }else{
            encuentraEspacio(horario, main);
        }
    }
    
    public void grabarEspacio(BeanMain horario[][], BeanMain main, int hora, int leccion){
        int maximo = 2;
        if(main.getNroHoras() > 0){
            if(main.getNroHoras() >= 2){
                if(horario[hora-1][leccion] == null){
                    horario[hora-1][leccion] = main;
                    horario[hora][leccion] = main;
                    main.setNroHoras(main.getNroHoras()-2);
                }else{
                    encuentraEspacio(horario, main);
                }               
            }
            else if(main.getNroHoras() == 1){
                if(horario[hora][leccion] == null){
                    horario[hora][leccion] = main;
                }else{
                    encuentraEspacio(horario, main);
                }  
            }else{
                encuentraEspacio(horario, main);
            }
        }
    }
    
    /**
     * Este metodo llena un vector string con las horas inicio de cada bloque, para luego ser comprado mas rapido
     */
    public void llenarVectorHoras(){
        BeanHorario horario = ln_C_SFHorarioRemote.getHorario();
        Utils.putSession("maxHoras", (horario.getMaxBloque()*5));
        sessionGestionarHorario.setNroBloque(horario.getNroBloque());
        Calendar inicio = new GregorianCalendar();
        inicio.setTimeInMillis(horario.getHora_ini().getTime());
        Time suma = new Time(inicio.getTimeInMillis());
        Time duracion = new Time(horario.getDuracion().getTime());
        String horas[] = new String[horario.getNroBloque()];
        for(int i = 0 ; i < horario.getNroBloque(); i++){
            if(i != 0){
                inicio.add(Calendar.HOUR, duracion.getHours());
                inicio.add(Calendar.MINUTE, duracion.getMinutes());
                suma.setTime(inicio.getTimeInMillis());
            }
            horas[i] = suma.toString();
        }
        sessionGestionarHorario.setHoras(horas);
    }
    
    public void resetValoresPopGenerarHorario(){
        sessionGestionarHorario.setNidProfesor(null);
        sessionGestionarHorario.setNidArea(null);
        sessionGestionarHorario.setNidCurso(null);
        itHor.resetValue();
        Utils.addTarget(pfGHor);
    }
    
    /**
     * Metodo changeListener para mostrar el aula segun sede y nivel
     * @param valueChangeEvent
     */
    public void changeListenerSede(ValueChangeEvent valueChangeEvent) {        
        if(valueChangeEvent.getNewValue() != null){
            choiceSede.setValue(valueChangeEvent.getNewValue());
            if(sessionGestionarHorario.getNidNivel() != null){
                sessionGestionarHorario.setLstAula(Utils.llenarCombo(
                            ln_C_SFUtilsRemote.getAulaByNidSedeNivel(Integer.parseInt(valueChangeEvent.getNewValue().toString()), 
                                                                     Integer.parseInt(sessionGestionarHorario.getNidNivel()))));
                Utils.addTarget(choiceAula);
            }
        }
    }

    /**
     * Metodo changeListener para mostrar el aula segun sede y nivel
     * @param valueChangeEvent
     */
    public void changeListenerNivel(ValueChangeEvent valueChangeEvent) {
        if(valueChangeEvent.getNewValue() != null){
            if(sessionGestionarHorario.getNidSede() != null){
                sessionGestionarHorario.setLstAula(Utils.llenarCombo(
                            ln_C_SFUtilsRemote.getAulaByNidSedeNivel(Integer.parseInt(sessionGestionarHorario.getNidSede()), 
                                                                     Integer.parseInt(valueChangeEvent.getNewValue().toString()))));
                Utils.addTarget(choiceAula);
            }
        }
    }
    
    /**
     * Metodo changeListener para mostrar los cursos segun el area
     * @param valueChangeEvent
     */
    public void changeListenerArea(ValueChangeEvent valueChangeEvent) {
        if(valueChangeEvent.getNewValue() != null){
            sessionGestionarHorario.setLstCurso(Utils.llenarCombo(
                ln_C_SFUtilsRemote.getCursosByArea_LN(Integer.parseInt(valueChangeEvent.getNewValue().toString()))));
            sessionGestionarHorario.setNombreArea(Utils.getChoiceLabel(valueChangeEvent));
            Utils.addTarget(pfGHor);
        }
    }
    
    /**
     * Metodo para obtener el value selecionado
     * @param valueChangeEvent
     */
    public void changeListenerCurso(ValueChangeEvent valueChangeEvent) {
        if(valueChangeEvent.getNewValue() != null){
            sessionGestionarHorario.setNombreCurso(Utils.getChoiceLabel(valueChangeEvent));
        }
    }

    /**
     * Metodo para obtener el value selecionado
     * @param valueChangeEvent
     */
    public void changeListenerProfesor(ValueChangeEvent valueChangeEvent) {
        if(valueChangeEvent.getNewValue() != null){
            sessionGestionarHorario.setNombreProfesor(Utils.getChoiceLabel(valueChangeEvent));
        }
    }
    
    public void setSessionGestionarHorario(bSessionGestionarHorario sessionGestionarHorario) {
        this.sessionGestionarHorario = sessionGestionarHorario;
    }

    public bSessionGestionarHorario getSessionGestionarHorario() {
        return sessionGestionarHorario;
    }

    public void setChoiceSede(RichSelectOneChoice choiceSede) {
        this.choiceSede = choiceSede;
    }

    public RichSelectOneChoice getChoiceSede() {
        return choiceSede;
    }

    public void setSi1(UISelectItems si1) {
        this.si1 = si1;
    }

    public UISelectItems getSi1() {
        return si1;
    }

    public void setChoiceNivel(RichSelectOneChoice choiceNivel) {
        this.choiceNivel = choiceNivel;
    }

    public RichSelectOneChoice getChoiceNivel() {
        return choiceNivel;
    }

    public void setSi2(UISelectItems si2) {
        this.si2 = si2;
    }

    public UISelectItems getSi2() {
        return si2;
    }

    public void setChoiceAula(RichSelectOneChoice choiceAula) {
        this.choiceAula = choiceAula;
    }

    public RichSelectOneChoice getChoiceAula() {
        return choiceAula;
    }

    public void setSi3(UISelectItems si3) {
        this.si3 = si3;
    }

    public UISelectItems getSi3() {
        return si3;
    }

    public void setPfCombox(RichPanelFormLayout pfCombox) {
        this.pfCombox = pfCombox;
    }

    public RichPanelFormLayout getPfCombox() {
        return pfCombox;
    }

    public void setPopGHor(RichPopup popGHor) {
        this.popGHor = popGHor;
    }

    public RichPopup getPopGHor() {
        return popGHor;
    }

    public void setChoiceProfesor(RichSelectOneChoice choiceProfesor) {
        this.choiceProfesor = choiceProfesor;
    }

    public RichSelectOneChoice getChoiceProfesor() {
        return choiceProfesor;
    }

    public void setSi4(UISelectItems si4) {
        this.si4 = si4;
    }

    public UISelectItems getSi4() {
        return si4;
    }

    public void setChoiceCurso(RichSelectOneChoice choiceCurso) {
        this.choiceCurso = choiceCurso;
    }

    public RichSelectOneChoice getChoiceCurso() {
        return choiceCurso;
    }

    public void setSi5(UISelectItems si5) {
        this.si5 = si5;
    }

    public UISelectItems getSi5() {
        return si5;
    }

    public void setChoiceArea(RichSelectOneChoice choiceArea) {
        this.choiceArea = choiceArea;
    }

    public RichSelectOneChoice getChoiceArea() {
        return choiceArea;
    }

    public void setSi6(UISelectItems si6) {
        this.si6 = si6;
    }

    public UISelectItems getSi6() {
        return si6;
    }

    public void setPfGHor(RichPanelFormLayout pfGHor) {
        this.pfGHor = pfGHor;
    }

    public RichPanelFormLayout getPfGHor() {
        return pfGHor;
    }

    public void setNroHoras(int nroHoras) {
        this.nroHoras = nroHoras;
    }

    public int getNroHoras() {
        return nroHoras;
    }

    public void setItHor(RichInputText itHor) {
        this.itHor = itHor;
    }

    public RichInputText getItHor() {
        return itHor;
    }

    public void setBeanMain(BeanMain beanMain) {
        this.beanMain = beanMain;
    }

    public BeanMain getBeanMain() {
        return beanMain;
    }
}