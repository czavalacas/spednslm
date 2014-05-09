package sped.vista.beans.horarios;


import java.io.Serializable;

import java.sql.Time;

import java.util.ArrayList;
import java.util.Calendar;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
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

import oracle.adf.view.rich.component.rich.nav.RichButton;

import sped.negocio.LNSF.IR.LN_C_SFConfiguracionHorarioRemote;
import sped.negocio.LNSF.IR.LN_C_SFDuracionHorarioRemote;
import sped.negocio.LNSF.IR.LN_C_SFHorarioRemote;
import sped.negocio.LNSF.IR.LN_C_SFMainRemote;
import sped.negocio.LNSF.IR.LN_C_SFUtilsRemote;

import sped.negocio.entidades.beans.BeanConfiguracionHorario;
import sped.negocio.entidades.beans.BeanDia;
import sped.negocio.entidades.beans.BeanDuracionHorario;
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
    @EJB
    private LN_C_SFConfiguracionHorarioRemote ln_C_SFConfiguracionHorarioRemote;
    @EJB
    private LN_C_SFDuracionHorarioRemote ln_C_SFDuracionHorarioRemote;
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
    private RichButton bgenerar;


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
        if(verificarConfiguracionHorario(2, 2)){//debe ir del nidsede y nidnivel
            if(!llenarHorario()){
                Utils.mostrarMensaje(ctx, "El horario registrado no coincide con la configuracion del horario", "Error", 2); 
                return null;
            }
            Utils.showPopUpMIDDLE(popGHor);        
            sessionGestionarHorario.setLstBeanMain(new ArrayList());
        }else{
            Utils.mostrarMensaje(ctx, "Las restriciones no coinciden con el horario", "Error", 2);
        }        
        return null;
    }
    
    /**
     * verifica que exista restriciones para la configuracion de  horario
     * @param nidSede
     * @param nidNivel
     * @return
     */
    public boolean verificarConfiguracionHorario(int nidSede, int nidNivel){
        boolean valida = false;
        BeanDuracionHorario duracion = ln_C_SFDuracionHorarioRemote.getDuracionHorarioBySedeNivel(nidSede, nidNivel);
        List<BeanConfiguracionHorario> lstCH = ln_C_SFConfiguracionHorarioRemote.getConfiguracionBySedeNivel(nidSede, nidNivel);
        if(duracion == null){
            Utils.mostrarMensaje(ctx, "Configure los parametros del horario", "Error", 2);
        }else if(lstCH.size() == 0){
            Utils.mostrarMensaje(ctx, "Configure llas restriciones en el horario", "Error", 2);
        }else{
            valida = validaConfiguracionHorario(duracion, lstCH);
        }
        return valida;
    }
    
    /**
     * Metodo para verificar la configuracion del horario
     * @param duracion
     * @param lstConfHorario
     * @return
     */
    public boolean validaConfiguracionHorario(BeanDuracionHorario duracion, List<BeanConfiguracionHorario> lstConfHorario){
        try{
            Utils.putSession("maxHoras", (duracion.getMax_bloque()*5));//grabamos el maximo permitido por curso
            sessionGestionarHorario.setNroBloque(duracion.getNro_bloque());//guardo el numero de bloques al dia permitido
            sessionGestionarHorario.setMaxBloque(duracion.getMax_bloque());
            String horas[] = new String[duracion.getNro_bloque()];
            Calendar inicio = new GregorianCalendar();
            inicio.setTime(duracion.getHora_inicio());
            Time time_aux = new Time(inicio.getTimeInMillis());
            int cont = 1;
            boolean restr = false;
            horas[0] = time_aux.toString();
            while(cont < duracion.getNro_bloque()){
                if(lstConfHorario.size() > 0){
                    for(BeanConfiguracionHorario configuracionH : lstConfHorario){                       
                        if(configuracionH.getHora_inicio().equals(time_aux)){
                            cont--;     
                            inicio.setTime(configuracionH.getHora_fin());
                            horas[cont] = time_aux.toString();         
                            lstConfHorario.remove(configuracionH);
                            cont++;     
                            restr = lstConfHorario.size() ==  0 && cont < duracion.getNro_bloque() ? false : true;
                            break;
                        }
                        if(configuracionH.getHora_inicio().after(time_aux)){ 
                            restr = false;
                            break;
                        }
                    }
                }
                if(!restr){
                    time_aux.setTime(sumaHoras(inicio, duracion.getDuracion()).getTime());
                    horas[cont] = time_aux.toString();                
                    cont++;
                }                     
            }    // fin de la validacion
            if(lstConfHorario.size() != 0){//valida si se validaron todas las restriciones
                return false;
            }
            sessionGestionarHorario.setHoras(horas);
            return true;  
        }catch(Exception e){            
            e.printStackTrace();
            return false;
        }
    }  
    
    public boolean llenarHorario(){
        BeanMain horario[][] = new BeanMain[sessionGestionarHorario.getNroBloque()][5];//5 si es solo 5 dias a la semana
        int horasFree = sessionGestionarHorario.getNroBloque()*5;
        List<BeanMain> lstLecciones = ln_C_SFMainRemote.getLstMainByAttr_LN("2");// reemplazar el "2" por sessionGestionarHorario.getNidAula()
        List<BeanMain> lst_defecto = new ArrayList();
        llenarLstDias(5); //llena una lista auxiliar, se usara en generarHorario
        for(BeanMain main : lstLecciones){
            int hora = encuentraHora(main.getHoraInicio());
            if(hora > -1){
                horario[hora][main.getNDia()] = main;
                modicarHorasBeanDias(main.getNDia(), 1);//resto a los dias las horas que se encuentran grabadas
                horasFree--;
            }else{
                lst_defecto.add(main);
            }
        }
        if(lst_defecto.size() > 0){
            return false;
        }else{
            Utils.putSession("Horas", horasFree);
            sessionGestionarHorario.setHorario(horario);
            metodoProbarVector();///////////////////////////////////////////////////////borar al final
            return true;
        }  
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
    
    ///////////////// DE AQUI PARA ARRIBA QUEDA /////////////// LUEGO BORRARE ESTA LINEA ... SI ES QUE SE JUNTA CON LA DE ABAJO XD
   
    public void generarHorario(ActionEvent actionEvent) {
        BeanMain horario[][] = sessionGestionarHorario.getHorario();
        List<BeanMain> lst = sessionGestionarHorario.getLstBeanMain();
        int maxBloque = sessionGestionarHorario.getMaxBloque();
        for(BeanMain main : lst){
            ubicaMain(main, horario, null, maxBloque);
        }
        metodoProbarVector();
    }

    public void ubicaMain(BeanMain main, BeanMain horario[][], List<BeanDia> dias, int maxBloque) {
        if(dias == null){
            dias = ordenarLstDiasByHoras();
        }
        if(main.getNroHoras() == 0){
            return;
        }
        if(main.getNroHoras() % maxBloque == 0){
            encuentraEspacio(horario, main, dias, maxBloque);
        }else{            
            //ubicaMain(main, horario, dias, maxBloque);
        }
        
    }
    
    public void encuentraEspacio(BeanMain horario[][], BeanMain main, List<BeanDia> dias, int maxBloque){
        int hora = -1;
        try{
            hora = (int) Math.round((Math.random()*((sessionGestionarHorario.getNroBloque() / maxBloque) - 1)));
            if(validarRango(horario, main, (hora * maxBloque), dias.get(0).getNDia(), maxBloque)){
                main.setNroHoras(main.getNroHoras() - maxBloque);
                modicarHorasBeanDias(dias.get(0).getNDia(), maxBloque);
                dias.remove(dias.get(0));
                ubicaMain(main, horario, dias, maxBloque);
            }else{
               encuentraEspacio(horario, main, dias, maxBloque);
            } 
        }catch(Exception e){
            System.out.println(hora);
            e.printStackTrace();
        }
    }
    
    public boolean validarRango(BeanMain horario[][], BeanMain main,int hora, int dia, int maxBloque){
        for(int i = hora ; i < hora + maxBloque; i++){            
            if(horario[i][dia] != null){
                return false;
            }
        }
        /////si paso significa que esos dias esas horas estan libres
        for(int i = hora ; i < hora + maxBloque; i++){
            horario[i][dia] = main;
        }
        return true;
    }
    
    
    /* public void generarHorario(ActionEvent actionEvent) {
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
    } */
    
    /*  public void encuentraEspacio(BeanMain horario[][], BeanMain main){
        int hora = (int) Math.round((Math.random()*7)); 
        int leccion = (int) Math.round((Math.random()*4));
        System.out.println(main.getNombreCurso()+"   -   "+main.getNroHoras());
        if(main.getNroHoras() > 1){
            System.out.println("Horas mayor de 1 horas: "+hora +" leccion: "+leccion);
            if(horario[hora][leccion] == null){
                System.out.println("encontro nulo");
                if(leccion + 1  % 2 == 0){
                    if(horario[hora - 1][leccion] == null){
                        horario[hora][leccion] =  main;
                        horario[hora - 1][leccion] =  main;
                        main.setNroHoras(main.getNroHoras()-2);
                        encuentraEspacio(horario, main);
                    }                    
                }else if(leccion + 1 % 2 == 1){
                    if(horario[hora + 1][leccion] == null){
                        horario[hora][leccion] =  main;
                        horario[hora + 1][leccion] =  main;
                        main.setNroHoras(main.getNroHoras()-2);
                        encuentraEspacio(horario, main);
                    }  
                }else{
                    System.out.println("Esta lleno");
                    encuentraEspacio(horario, main);
                }
            }else{
                encuentraEspacio(horario, main);
            }            
        }
        if(main.getNroHoras() == 1){
            System.out.println("Horas = a 1 horas: "+hora +" leccion: "+leccion);
            if(horario[hora][leccion] == null){
                horario[hora][leccion] =  main;
                main.setNroHoras(0);
            }else{
                encuentraEspacio(horario, main);
            }
        }
    } */
    
    public void grabarEspacio(BeanMain horario[][], BeanMain main, int leccion, int... hora){
        
    }
    
    /* public void encuentraEspacio(BeanMain horario[][], BeanMain main){
        int hora = (int) Math.round((Math.random()*7)); 
        int leccion = (int) Math.round((Math.random()*4)); 
        if(horario[hora][leccion] == null){
            if(hora +1 % 2 == 0){
                grabarEspacio(horario, main, hora, leccion);
            }
        }else{
            encuentraEspacio(horario, main);
        }
    } */
    
    /* public void grabarEspacio(BeanMain horario[][], BeanMain main, int hora, int leccion){
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
    } */
    
    public void metodoProbarVector(){
        BeanMain horario[][] = sessionGestionarHorario.getHorario();
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
////////////////////////////////METODOS QUE QUEDAN//////////////    --Luego borrare esta linea si se junta con la de arriba XD
    
    //-------- Metodos auxiliares ------///
    
    public void llenarLstDias(int dias){
        List<BeanDia> lstDias = new ArrayList();
        int nro_bloque = sessionGestionarHorario.getNroBloque();
        for(int i = 0 ; i < dias; i++){
            BeanDia dia = new BeanDia();
            dia.setNDia(i);
            dia.setHoras(nro_bloque);
            lstDias.add(dia);
        }
        sessionGestionarHorario.setLstDia(lstDias);        
    }
    
    public void modicarHorasBeanDias(int ndia, int hora){
        int nHora = sessionGestionarHorario.getLstDia().get(ndia).getHoras();
        sessionGestionarHorario.getLstDia().get(ndia).setHoras(nHora - hora);        
    }
    
    public List<BeanDia> ordenarLstDiasByHoras(){
        List<BeanDia> lst_aux = new ArrayList<>(sessionGestionarHorario.getLstDia());
        Collections.sort(lst_aux, new Comparator(){
            @Override
            public int compare(Object object, Object object2) {
                BeanDia dia1 = (BeanDia) object;
                BeanDia dia2 = (BeanDia) object2;
                return dia2.getHoras().compareTo(dia1.getHoras());
            }
        });
        return lst_aux;
    }
    
    //////////////metodo que luego sera pasado a borrar//////////////////////////
    public void mostrarListDias(){
        for(BeanDia dia : sessionGestionarHorario.getLstDia()){
            System.out.println("Dia: "+dia.getNDia()+"   Horas: "+dia.getHoras());
        }
    }
    
    //-------- FIN Metodos auxiliares ------///
    
    /**
     * Valida la hora de inicio de una leccion con un vector tipo string
     * @param time
     * @return
     */
    public int encuentraHora(Time time){
        for(int i = 0; i < sessionGestionarHorario.getHoras().length; i++){
            if(time.toString().compareTo(sessionGestionarHorario.getHoras()[i]) == 0){
                return i;
            }
        }
        return -1;
    }
    
    /**
     * Metodo para agregar tiempo a una fecha
     * @param inicio
     * @param agregar
     * @return
     */
    public Date sumaHoras(Calendar inicio, Time agregar){
        inicio.add(Calendar.HOUR, agregar.getHours());
        inicio.add(Calendar.MINUTE, agregar.getMinutes());
        return inicio.getTime();
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

    public String metodoPrueba() {
        System.out.println("funciono");
        return null;
    }

    public void setBgenerar(RichButton bgenerar) {
        this.bgenerar = bgenerar;
    }

    public RichButton getBgenerar() {
        return bgenerar;
    }    
}
