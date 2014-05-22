package sped.vista.beans.horarios;


import java.awt.Color;

import java.sql.Time;

import java.text.Format;
import java.text.SimpleDateFormat;

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
import javax.faces.event.ComponentSystemEvent;
import javax.faces.event.ValueChangeEvent;

import oracle.adf.view.rich.component.rich.RichPopup;

import oracle.adf.view.rich.component.rich.RichSubform;
import oracle.adf.view.rich.component.rich.data.RichCalendar;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.input.RichSelectBooleanCheckbox;
import oracle.adf.view.rich.component.rich.input.RichSelectManyChoice;
import oracle.adf.view.rich.component.rich.input.RichSelectOneChoice;

import oracle.adf.view.rich.component.rich.layout.RichPanelBox;
import oracle.adf.view.rich.component.rich.layout.RichPanelFormLayout;

import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.component.rich.nav.RichButton;

import oracle.adf.view.rich.event.DialogEvent;

import sped.negocio.LNSF.IR.LN_C_SFConfiguracionHorarioRemote;
import sped.negocio.LNSF.IR.LN_C_SFDuracionHorarioRemote;
import sped.negocio.LNSF.IR.LN_C_SFMainRemote;
import sped.negocio.LNSF.IR.LN_C_SFUtilsRemote;

import sped.negocio.LNSF.IR.LN_T_SFCursoRemoto;
import sped.negocio.LNSF.IR.LN_T_SFMainRemote;
import sped.negocio.entidades.beans.BeanCombo;
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
    private LN_C_SFMainRemote ln_C_SFMainRemote;
    @EJB
    private LN_C_SFConfiguracionHorarioRemote ln_C_SFConfiguracionHorarioRemote;
    @EJB
    private LN_C_SFDuracionHorarioRemote ln_C_SFDuracionHorarioRemote;
    @EJB
    private LN_T_SFMainRemote ln_T_SFMainRemote;
    @EJB
    private LN_T_SFCursoRemoto ln_T_SFCursoRemoto;
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
    private RichTable thoras;
    private RichCalendar c1;
    private RichTable thor;
    private RichPopup popColor;   
    private int nDia;
    private int nLeccion;
    private RichPopup popEM;
    private RichSelectManyChoice choiceDia;
    private RichSelectOneChoice chProf;
    private RichButton bcargar;
    private RichPanelGroupLayout panelHora;
    private RichPanelGroupLayout pgl1;
    private RichSelectOneChoice choiceC2;
    private RichPanelFormLayout panelM;
    private RichSubform subAgr;
    private RichPanelBox pbHor;

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
            llenarVectorDias();
        }
    } 
    
    /**
     * Metodo que valida para cargar el horario
     * @return
     */
    public String cargarHorario() {
        sessionGestionarHorario.setRenderHorario(false);
        if(verificarConfiguracionHorario(Integer.parseInt(sessionGestionarHorario.getNidSede()), 
                                         Integer.parseInt(sessionGestionarHorario.getNidNivel()))){
            if(!llenarHorario()){
                Utils.mostrarMensaje(ctx, "El horario registrado no coincide con la configuracion del horario", "Error", 2); 
                return null;
            }
            sessionGestionarHorario.setLstBeanMain(new ArrayList());
            sessionGestionarHorario.setRenderHorario(true);
            sessionGestionarHorario.setNombreAula_aux(sessionGestionarHorario.getNombreAula());
        }   
        Utils.addTarget(pgl1);
        return null;
    }
    
    public String abrirPopGenerarHorario() {
        Utils.showPopUpMIDDLE(popGHor);           
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
            Utils.mostrarMensaje(ctx, "Configure las restriciones en el horario", "Error", 2);
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
            Time h_inicio[] = new Time[duracion.getNro_bloque()];
            Calendar inicio = new GregorianCalendar();
            inicio.setTime(duracion.getHora_inicio());
            Time time_aux = new Time(inicio.getTimeInMillis());            
            int cont = 1;
            boolean restr = false;
            h_inicio[0] = new Time(time_aux.getTime());
            while(cont < duracion.getNro_bloque()){
                if(lstConfHorario.size() > 0){
                    for(BeanConfiguracionHorario configuracionH : lstConfHorario){                       
                        if(configuracionH.getHora_inicio().equals(time_aux)){
                            cont--;     
                            inicio.setTime(configuracionH.getHora_fin());
                            h_inicio[cont] = new Time(time_aux.getTime());      
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
                    h_inicio[cont] = new Time(time_aux.getTime());               
                    cont++;
                }                     
            }    // fin de la validacion   
            if(lstConfHorario.size() != 0){//valida si se validaron todas las restriciones
                return false;
            }
            llenarHorasFin(duracion.getDuracion(), h_inicio);            
            Time algo[] = sessionGestionarHorario.getHoras_fin();
            for(int i = 0 ; i < h_inicio.length; i++){
                System.out.println(h_inicio[i]+"    "+algo[i]);
            }
            sessionGestionarHorario.setHoras(h_inicio);
            return true;  
        }catch(Exception e){            
            e.printStackTrace();
            return false;
        }
    }  
    
    /**
     * Metodo que busca las leciones del salon y lo ingresa en un vector para luego ser validado
     * @return
     */
    public boolean llenarHorario(){
        BeanMain horario[][] = new BeanMain[sessionGestionarHorario.getNroBloque()][5];//5 si es solo 5 dias a la semana
        int horasFree = sessionGestionarHorario.getNroBloque()*5;
        List<BeanMain> lstLecciones = ln_C_SFMainRemote.getLstMainByAttr_LN(sessionGestionarHorario.getNidAula_aux());// reemplazar el "2" por sessionGestionarHorario.getNidAula()
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
        if(!verificarLeccion(main)){
            main = null;
        }
        if(main != null){
            main.setEstado("1");
            sessionGestionarHorario.getLstBeanMain().add(main);
            int horas_libres = Integer.parseInt(Utils.getSession("Horas").toString()) - main.getNroHoras();        
            Utils.putSession("Horas", horas_libres);
            Utils.addTarget(thoras);
        }
        return null;
    }
    
    /**
     * valida las horas que quedan por asignar al curso
     * @param main
     * @return
     */
    public boolean verificarLeccion(BeanMain main){
        BeanMain horario[][] = sessionGestionarHorario.getHorario();
        int cont = 5 * sessionGestionarHorario.getMaxBloque(); //horas maximo a la semana
        for(int i = 0 ; i < 5 ; i ++){
            cont = cont - validarHorasMaximoPorDia(horario, i, main.getNidCurso());
        }
        for(BeanMain m : sessionGestionarHorario.getLstBeanMain()){
            if(m.getNidCurso() == main.getNidCurso()){
                cont = cont - main.getNroHoras();
            }
        }
        if(main.getNroHoras() > cont){
            cont = cont < 0 ? 0 : cont;
            Utils.mostrarMensaje(ctx,"Quedan "+cont+" horas por asignar al curso "+main.getNombreCurso(), null, 2);
            return false;
        }
        return true;
    }
    
    /**
     * Se elimina la leccion de la lista BeanMain que se encuentra en sesssion
     * @return
     */
    public String eliminarLeccion() {
        int horas_libres = Integer.parseInt(Utils.getSession("Horas").toString()) + beanMain.getNroHoras();        
        Utils.putSession("Horas", horas_libres);
        sessionGestionarHorario.getLstBeanMain().remove(beanMain);
        Utils.addTarget(thoras);
        return null;
    }
   
    public void generarHorario(ActionEvent actionEvent) {
        BeanMain horario[][] = sessionGestionarHorario.getHorario();
        List<BeanMain> lst = sessionGestionarHorario.getLstBeanMain();
        int maxBloque = sessionGestionarHorario.getMaxBloque();
        for(BeanMain main : lst){
            ubicaMain(main, horario, null, maxBloque);
        }
        metodoProbarVector();
        guardarGenerarHorario();
        llenarHorario();
        Utils.addTarget(thor);
    }

    public void ubicaMain(BeanMain main, BeanMain horario[][], List<BeanDia> dias, int maxBloque) {
        if(dias == null){
            dias = ordenarLstDiasByHoras();
        }
        if(main.getNroHoras() <= 0){
            return;
        }
        if(main.getNroHoras() % maxBloque == 0){
            encuentraEspacio(horario, main, dias, maxBloque);
        }else{      
            BeanDia dia = encontrarDiaNoDivisible(main, dias, maxBloque);
            encuentraEspacioImpar(horario, main, dias, main.getNroHoras() % maxBloque, dia);
            main.setNroHoras(main.getNroHoras() - maxBloque);
            ubicaMain(main, horario, dias, sessionGestionarHorario.getMaxBloque());
        }        
    }
    
    public void encuentraEspacioImpar(BeanMain horario[][], BeanMain main, List<BeanDia> dias, int maxBloque, BeanDia dia){
        if(dia == null){
            int posicion = main.getNroHoras() / maxBloque;
            dia = dias.get(posicion);
            int hora = (int) Math.round((Math.random()*((sessionGestionarHorario.getNroBloque() / maxBloque) - 1)));///2123123
            boolean valida = validarRango(horario, main, hora, dia.getNDia(), maxBloque);
            if(!valida){
                dia = null;
                encuentraEspacioImpar(horario, main, dias, maxBloque, null);
            }
        }else{
            int cont = 0 ;
            for(int i = 0 ; i < sessionGestionarHorario.getNroBloque(); i++){
                if(horario[i][dia.getNDia()] != null){
                    cont++;                    
                }
                if((i+1) % maxBloque == 0 && cont != 0 && cont != maxBloque){
                    cont = i - cont;
                    break;
                }
                if((i+1) % maxBloque == 0){
                    cont = 0;
                }
            }
            boolean valida = false;
            for(int i = cont; i < cont + sessionGestionarHorario.getMaxBloque(); i++){
                valida = validarRango(horario, main, i, dia.getNDia(), maxBloque);
                if(valida){
                    break;
                }
            }
            if(!valida){
                encuentraEspacioImpar(horario, main, dias, maxBloque, null);
            }
        }           
    }
    
    public void encuentraEspacio(BeanMain horario[][], BeanMain main, List<BeanDia> dias, int maxBloque){
        try{
            int hora = (int) Math.round((Math.random()*((sessionGestionarHorario.getNroBloque() / maxBloque) - 1)));
            if(validarRango(horario, main, (hora * maxBloque), dias.get(0).getNDia(), maxBloque)){
                main.setNroHoras(main.getNroHoras() - maxBloque);
                modicarHorasBeanDias(dias.get(0).getNDia(), maxBloque);
                dias.remove(dias.get(0));
                ubicaMain(main, horario, dias, maxBloque);
            }else{
               encuentraEspacio(horario, main, dias, maxBloque);
            } 
        }catch(Exception e){
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
    

    public void guardarGenerarHorario(){
        try{
            BeanMain horario[][] = sessionGestionarHorario.getHorario();            
            for(int i = 0 ; i < sessionGestionarHorario.getNroBloque(); i++){
                for(int j = 0; j < 5 ; j++){                   
                    if(horario[i][j] != null && 
                       horario[i][j].getEstado() != null && 
                       horario[i][j].getEstado().compareTo("1") == 0){
                        gestionarMain_aux(1, horario[i][j], j, i, horario[i][j].getDniProfesor());
                    }
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        
    }
    
    public void gestionarMain_aux(int evento, BeanMain main, int dia, int leccion, String dniProfesor){
        Time inicio[] = sessionGestionarHorario.getHoras();
        Time fin[] = sessionGestionarHorario.getHoras_fin();
        if(evento == 1 || evento == 2){
            ln_T_SFMainRemote.gestionarMain_LN(evento, 
                                               main.getNidMain(),
                                               dniProfesor, 
                                               Integer.parseInt(sessionGestionarHorario.getNidAula()), 
                                               main.getNidCurso(), 
                                               dia, 
                                               inicio[leccion], 
                                               fin[leccion]);            
        }
        if(evento == 3){
            ln_T_SFMainRemote.eliminarMain_LN(main.getNidMain());
        }
        main = null;        
    }
          
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
    
    //-------- Metodos auxiliares ------///
    
    /**
     * Metodo que valida las horas dependiendo de los paramtros dia y curso
     * @param horario
     * @param dia
     * @param nidCurso
     * @return
     */
    public int validarHorasMaximoPorDia(BeanMain horario[][], 
                                            int dia, 
                                            int nidCurso){   
        int cont = 0;
        for(int i = 0 ; i < sessionGestionarHorario.getNroBloque(); i++){
            if(horario[i][dia] != null && horario[i][dia].getNidCurso() == nidCurso){                
                cont++;
            }
        }
        return cont;
    }
    
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
    
    /**
     * Encuentra el metodo que tenga horarios disparejos
     * @param main
     * @param lst
     * @param maxBloque
     * @return
     */
    public BeanDia encontrarDiaNoDivisible(BeanMain main, List<BeanDia> lst, int maxBloque){
        for(BeanDia dia : lst){
            if(dia.getHoras() % maxBloque != 0){
                return dia;
            }
        }        
        return null;
    }
    
    /**
     * Valida la hora de inicio de una leccion con un vector tipo string
     * @param time
     * @return
     */
    public int encuentraHora(Time time){
        for(int i = 0; i < sessionGestionarHorario.getHoras().length; i++){
            if(time.equals(sessionGestionarHorario.getHoras()[i])){
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
    
    /**
     * Metodo que llena las horas fin de vada bloque de clases
     * @param duracion
     * @param h_inicio
     */
    public void llenarHorasFin(Time duracion, Time[] h_inicio){
        Time[] horas_fin = new Time[sessionGestionarHorario.getNroBloque()];
        Calendar c = new GregorianCalendar();
        for(int i = 0; i < horas_fin.length; i++){
            c.setTime(h_inicio[i]);
            horas_fin[i] = new Time(sumaHoras(c, duracion).getTime());
        }
        sessionGestionarHorario.setHoras_fin(horas_fin);
    }
    
    /**
     * Metodo que llena el vector dias para luego visualizarse en la vista horario
     */
    public void llenarVectorDias(){
        String dias[] = new String[5];
        dias[0] = "Lunes";
        dias[1] = "Martes";
        dias[2] = "Miercoles";
        dias[3] = "Jueves";
        dias[4] = "Viernes";
        sessionGestionarHorario.setDias(dias);
    }
    
    //////////////metodo que luego sera pasado a borrar//////////////////////////
    public void mostrarListDias(){
        for(BeanDia dia : sessionGestionarHorario.getLstDia()){
            System.out.println("Dia: "+dia.getNDia()+"   Horas: "+dia.getHoras());
        }
    }
    
    //-------- FIN Metodos auxiliares ------///
   
   /// METODOS EN EL CALENDARIO ///
    /**
     * Metodo que carga los datos de la leccion selecionada en session y abre el popColor
     * @param actionEvent
     */
    public void cambiarColor(ActionEvent actionEvent) {
        sessionGestionarHorario.cargarDatosSelec();
        Utils.showPopUpMIDDLE(popColor);
    }       
    
    /**
     * Metodo que carga los datos de la leccion selecionada en session y abre el popEM(ELIMINAR MODIFICAR)
     * @param actionEvent
     */
    public void eliminarLecciones(ActionEvent actionEvent) {
        modificarEliminarLecciones_aux();
        sessionGestionarHorario.setEventoEliminarModificar(3);
        sessionGestionarHorario.setRenderEliminarModificar(false);
    }       
    
    /**
     * Metodo que carga los datos de la leccion selecionada en session y abre el popEM(MODIFICAR)
     * @param actionEvent
     */
    public void modificarLecciones(ActionEvent actionEvent) {
        modificarEliminarLecciones_aux();
        sessionGestionarHorario.setEventoEliminarModificar(2);
        sessionGestionarHorario.setRenderEliminarModificar(true);
    }
    
    public void modificarEliminarLecciones_aux(){
        sessionGestionarHorario.cargarDatosSelec();
        sessionGestionarHorario.encontrarDiaLecccion();  
        sessionGestionarHorario.setTituloEliminarModificar("Eliminar lecciones del curso "+sessionGestionarHorario.getSelecNombreCurso());
        Utils.showPopUpMIDDLE(popEM);
    }
    
    public void obtenerposicion(ActionEvent actionEvent) {
        sessionGestionarHorario.setNDia(nDia);
        sessionGestionarHorario.setNLeccion(nLeccion);
    }
    
    public void confirmarCambioColor(DialogEvent dialogEvent) {
        DialogEvent.Outcome outcome = dialogEvent.getOutcome();
        if(outcome == DialogEvent.Outcome.ok){            
            String rgb = Integer.toHexString(sessionGestionarHorario.getColor().getRGB()).substring(2);
            ln_T_SFCursoRemoto.modificarColor(sessionGestionarHorario.getSelecNidCurso(), 
                                              rgb);
            llenarHorario(); ///llena nuevamente el vector
            Utils.addTarget(thor);
        }
    }
    
    public void modificarEliminarLecciones(DialogEvent dialogEvent) {
        DialogEvent.Outcome outcome = dialogEvent.getOutcome();
        if(outcome == DialogEvent.Outcome.ok){            
            eliminarDiasSelecionados(sessionGestionarHorario.getEventoEliminarModificar());
        }
    }
    
    public void eliminarDiasSelecionados(int evento){
        BeanMain main[][] = sessionGestionarHorario.getHorario(); 
        for(Object o : sessionGestionarHorario.getLstDiasSelec()){
            int dia = Integer.parseInt(o.toString());
            if(dia == 9){
                gestionarMain_aux(evento, 
                                  main[sessionGestionarHorario.getNLeccion()][sessionGestionarHorario.getNDia()], 
                                  sessionGestionarHorario.getNDia(), 
                                  sessionGestionarHorario.getNLeccion(), 
                                  sessionGestionarHorario.getNidProfesor());
                main[sessionGestionarHorario.getNLeccion()][sessionGestionarHorario.getNDia()] = null;
            }else{
                for(int i = 0; i < sessionGestionarHorario.getNroBloque(); i++){
                    if(main[i][dia] != null && 
                       main[i][dia].getNidCurso() == sessionGestionarHorario.getSelecNidCurso()){
                        gestionarMain_aux(evento, 
                                          main[i][dia], 
                                          dia, 
                                          i, 
                                          sessionGestionarHorario.getNidProfesor());
                    }
                }
            }
        }      
        llenarHorario(); ///llena nuevamente el vector
        Utils.addTarget(thor);
    }
    
    /**
     * Evento de selecion del checkbox
     * @param vce
     */
    public void horarioChecked(ValueChangeEvent vce) {
        try{
            Object val = vce.getNewValue();
            if((Boolean)val){
                int nDia = (Integer) vce.getComponent().getAttributes().get("nDia");
                int nLeccion = (Integer) vce.getComponent().getAttributes().get("nLec");
                BeanMain beanMain = new BeanMain();
                beanMain.setDniProfesor(sessionGestionarHorario.getNidDni_aux());
                beanMain.setNidCurso(Integer.parseInt(sessionGestionarHorario.getNidCurso_aux()));
                beanMain.setNombreProfesor(sessionGestionarHorario.getNombreProfesor());
                beanMain.setNombreCurso(sessionGestionarHorario.getNombreCurso());
                sessionGestionarHorario.agregarMain(nLeccion, nDia, beanMain);
            }else{
                sessionGestionarHorario.agregarMain(nLeccion, nDia, null);
            }
            Utils.addTarget(thor);
        }catch(Exception e){
            e.printStackTrace();
        }
    }  
    
    public void horarioChecked(ActionEvent actionEvent) {
        int nDia = (Integer) actionEvent.getComponent().getAttributes().get("nDia");
        int nLeccion = (Integer) actionEvent.getComponent().getAttributes().get("nLec");
        int horas = validarHorasMaximoPorDia(sessionGestionarHorario.getHorario(), 
                                             nDia, 
                                             Integer.parseInt(sessionGestionarHorario.getNidCurso_aux()));
        if(horas < sessionGestionarHorario.getMaxBloque()){
            if(validarCruce(2, nDia, nLeccion, sessionGestionarHorario.getNidDni_aux())){
                BeanMain beanMain = new BeanMain();
                beanMain.setEstado("1");
                beanMain.setDniProfesor(sessionGestionarHorario.getNidDni_aux());
                beanMain.setNidCurso(Integer.parseInt(sessionGestionarHorario.getNidCurso_aux()));
                beanMain.setNombreProfesor(sessionGestionarHorario.getNombreProfesor());
                beanMain.setNombreCurso(sessionGestionarHorario.getNombreCurso());
                sessionGestionarHorario.agregarMain(nLeccion, nDia, beanMain);
                Utils.addTarget(thor);
            }            
        }else{
            Utils.mostrarMensaje(ctx,"Maximo numero de horas para el curso "+sessionGestionarHorario.getNombreCurso(), null, 2);
        }        
    }
    
    public void horarioCancel(ActionEvent actionEvent) {
        int nDia = (Integer) actionEvent.getComponent().getAttributes().get("nDia");
        int nLeccion = (Integer) actionEvent.getComponent().getAttributes().get("nLec");
        sessionGestionarHorario.agregarMain(nLeccion, nDia, null);
        Utils.addTarget(thor);
    }
    
    public String guardarHorarioManual() {
        guardarGenerarHorario();
        llenarHorario();
        Utils.addTarget(thor);
        return null;
    }
    
    public String actionAgregarCurso() {
        sessionGestionarHorario.setRenderAgregar(true);
        renderAgregar_aux();
        Utils.addTarget(pbHor);
        return null;
    }
    
    public String actionCancelar() {
        sessionGestionarHorario.setRenderAgregar(false);
        sessionGestionarHorario.setRenderAgregar_aux(false);
        llenarHorario();
        Utils.addTarget(pbHor);
        return null;
    }
    
    /**
     * Metodo que se utiliza para validar cruze del profesor con otra sede. Tipo 1 validacion simple, 2 para mostrar los cruces
     * @param tipo
     * @param dia
     * @param leccion
     * @param dni
     * @return
     */
    public boolean validarCruce(int tipo, int dia, int leccion, String dni){
        Time inicio[] = sessionGestionarHorario.getHoras();
        Time fin[] = sessionGestionarHorario.getHoras_fin();        
        List<BeanMain> listBean = ln_C_SFMainRemote.CruceLecionByProfesor(dni, dia, inicio[leccion], fin[leccion]);
        if(tipo == 2){
            for(BeanMain main : listBean){
                Utils.mostrarMensaje(ctx,"Disponibilidad del profesor "+sessionGestionarHorario.getNombreProfesor()+" ocupada en el aula "+main.getNombreAula(), null, 2);
            }
        }   
        return listBean.size() == 0;
    }
    
   //////FIN------------------------------------------------------/////////////////////
   
   
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
     * Metodo changeListener para obtener el nombre del aula
     * @param valueChangeEvent
     */
    public void changeListenerAula(ValueChangeEvent valueChangeEvent) {
        if(valueChangeEvent.getNewValue() != null){
            sessionGestionarHorario.setNidAula_aux(valueChangeEvent.getNewValue().toString());
            sessionGestionarHorario.setNombreAula(Utils.getChoiceLabel(valueChangeEvent));
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
            choiceCurso.setRendered(true);
            Utils.addTarget(pfGHor);
        }
    }

    public void changeListenerArea_aux(ValueChangeEvent valueChangeEvent) {
        if(valueChangeEvent.getNewValue() != null){
            sessionGestionarHorario.setLstCurso(Utils.llenarCombo(
                ln_C_SFUtilsRemote.getCursosByArea_LN(Integer.parseInt(valueChangeEvent.getNewValue().toString()))));
            sessionGestionarHorario.setRenderCurso_aux(true);
            sessionGestionarHorario.setNidCurso_aux(null); 
            choiceC2.resetValue();
            Utils.addTarget(panelM);
            renderAgregar_aux();
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
    
    public void changeListenerCurso_aux(ValueChangeEvent valueChangeEvent) {
        if(valueChangeEvent.getNewValue() != null){
            sessionGestionarHorario.setNombreCurso(Utils.getChoiceLabel(valueChangeEvent));
            sessionGestionarHorario.setNidCurso_aux(valueChangeEvent.getNewValue().toString());
            renderAgregar_aux();
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
    
    public void changeListenerProfesor_aux(ValueChangeEvent valueChangeEvent) {
        if(valueChangeEvent.getNewValue() != null){
            sessionGestionarHorario.setNombreProfesor(Utils.getChoiceLabel(valueChangeEvent));
            System.out.println(valueChangeEvent.getNewValue());
            sessionGestionarHorario.setNidDni_aux(valueChangeEvent.getNewValue().toString());
            renderAgregar_aux();
        }
    }
    
    public void renderAgregar_aux(){
        if(sessionGestionarHorario.getNidDni_aux() != null & 
           sessionGestionarHorario.getNidCurso_aux() != null){
            sessionGestionarHorario.setRenderAgregar_aux(true);
        }else{
            sessionGestionarHorario.setRenderAgregar_aux(false);
        }
        Utils.addTarget(thor);
    }
    
    //----------------------------//////get and set//////--------------------------------------------
    
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

    public void setThoras(RichTable thoras) {
        this.thoras = thoras;
    }

    public RichTable getThoras() {
        return thoras;
    }

    public void setC1(RichCalendar c1) {
        this.c1 = c1;
    }

    public RichCalendar getC1() {
        return c1;
    }

    public void setThor(RichTable thor) {
        this.thor = thor;
    }

    public RichTable getThor() {
        return thor;
    }

    public void setPopColor(RichPopup popColor) {
        this.popColor = popColor;
    }

    public RichPopup getPopColor() {
        return popColor;
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

    public void setPopEM(RichPopup popEM) {
        this.popEM = popEM;
    }

    public RichPopup getPopEM() {
        return popEM;
    }

    public void setChoiceDia(RichSelectManyChoice choiceDia) {
        this.choiceDia = choiceDia;
    }

    public RichSelectManyChoice getChoiceDia() {
        return choiceDia;
    }

    public void setChProf(RichSelectOneChoice chProf) {
        this.chProf = chProf;
    }

    public RichSelectOneChoice getChProf() {
        return chProf;
    }

    public void setBcargar(RichButton bcargar) {
        this.bcargar = bcargar;
    }

    public RichButton getBcargar() {
        return bcargar;
    }

    public void setPanelHora(RichPanelGroupLayout panelHora) {
        this.panelHora = panelHora;
    }

    public RichPanelGroupLayout getPanelHora() {
        return panelHora;
    }

    public void setPgl1(RichPanelGroupLayout pgl1) {
        this.pgl1 = pgl1;
    }

    public RichPanelGroupLayout getPgl1() {
        return pgl1;
    }

    public void setChoiceC2(RichSelectOneChoice choiceC2) {
        this.choiceC2 = choiceC2;
    }

    public RichSelectOneChoice getChoiceC2() {
        return choiceC2;
    }

    public void setPanelM(RichPanelFormLayout panelM) {
        this.panelM = panelM;
    }

    public RichPanelFormLayout getPanelM() {
        return panelM;
    }

    public void setSubAgr(RichSubform subAgr) {
        this.subAgr = subAgr;
    }

    public RichSubform getSubAgr() {
        return subAgr;
    }

    public void setPbHor(RichPanelBox pbHor) {
        this.pbHor = pbHor;
    }

    public RichPanelBox getPbHor() {
        return pbHor;
    }    
}
