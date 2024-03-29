package sped.vista.beans.horarios;

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
import java.util.Random;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.component.UISelectItems;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.RichSubform;
import oracle.adf.view.rich.component.rich.data.RichCalendar;
import oracle.adf.view.rich.component.rich.data.RichColumn;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.input.RichSelectManyChoice;
import oracle.adf.view.rich.component.rich.input.RichSelectOneChoice;
import oracle.adf.view.rich.component.rich.layout.RichPanelBox;
import oracle.adf.view.rich.component.rich.layout.RichPanelFormLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.component.rich.nav.RichButton;
import oracle.adf.view.rich.event.DialogEvent;

import sped.negocio.LNSF.IL.LN_T_SFLoggerLocal;
import sped.negocio.LNSF.IR.LN_C_SFConfiguracionHorarioRemote;
import sped.negocio.LNSF.IR.LN_C_SFDuracionHorarioRemote;
import sped.negocio.LNSF.IR.LN_C_SFMainRemote;
import sped.negocio.LNSF.IR.LN_C_SFProfesorRemote;
import sped.negocio.LNSF.IR.LN_C_SFRestriccionHorarioRemote;
import sped.negocio.LNSF.IR.LN_C_SFUtilsRemote;
import sped.negocio.LNSF.IR.LN_T_SFCursoRemoto;
import sped.negocio.LNSF.IR.LN_T_SFMainRemote;
import sped.negocio.entidades.beans.BeanConfiguracionHorario;
import sped.negocio.entidades.beans.BeanDia;
import sped.negocio.entidades.beans.BeanDuracionHorario;
import sped.negocio.entidades.beans.BeanHorario;
import sped.negocio.entidades.beans.BeanMain;

import sped.negocio.entidades.beans.BeanRestriccionHorario;
import sped.negocio.entidades.beans.BeanUsuario;

import sped.vista.Utils.Utils;

public class bGestionarHorario {    
    
    private RichSelectOneChoice choiceSede;
    private UISelectItems si1;
    private RichSelectOneChoice choiceNivel;
    private UISelectItems si2;
    private RichSelectOneChoice choiceAulaProfesor;
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
    private RichInputText itHor;
    private BeanMain beanMain;
    private RichButton bgenerar;
    private RichTable thoras;
    private RichCalendar c1;
    private RichTable thor;
    private RichPopup popColor;      
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
    private RichColumn res;
    @EJB
    private LN_C_SFRestriccionHorarioRemote ln_C_SFRestriccionHorarioRemote;
    @EJB
    private LN_C_SFUtilsRemote ln_C_SFUtilsRemote;
    @EJB
    private LN_C_SFProfesorRemote ln_C_SFProfesorRemote;
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
    @EJB
    private LN_T_SFLoggerLocal ln_T_SFLoggerLocal;
    private BeanUsuario beanUsuario = (BeanUsuario) Utils.getSession("USER");
    private static final String CLASE = "sped.vista.beans.horarios.bGestionarHorario";
    private bSessionGestionarHorario sessionGestionarHorario;
    FacesContext ctx = FacesContext.getCurrentInstance();
    private int nDia;
    private int nLeccion;
    private int nroHoras;

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
            llenarTipoVista(); 
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
            sessionGestionarHorario.setNidSede_aux(Integer.parseInt(sessionGestionarHorario.getNidSede()));
            sessionGestionarHorario.setNidNivel_aux(Integer.parseInt(sessionGestionarHorario.getNidNivel()));
            sessionGestionarHorario.setNidAulaProfesor_aux(sessionGestionarHorario.getNidAulaProfesor());
            sessionGestionarHorario.setNombreAula_aux(sessionGestionarHorario.getNombreAula());
            if(!llenarHorario()){
                Utils.mostrarMensaje(ctx, "El horario registrado no coincide con la configuracion del horario", "Error", 2); 
                return null;
            }            
            sessionGestionarHorario.setLstBeanMain(new ArrayList());
            sessionGestionarHorario.setRenderHorario(true);            
        }   
        Utils.addTarget(pgl1);
        return null;
    }
    
    public String abrirPopGenerarHorario() {
        if(sessionGestionarHorario.isRenderGenerario()){
            sessionGestionarHorario.setRenderGenerario(false);
            List<BeanMain> lstBeanMain = new ArrayList();
            sessionGestionarHorario.setLstBeanMain(lstBeanMain);
        }
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
                            h_inicio[cont] = new Time(inicio.getTimeInMillis());      
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
            sessionGestionarHorario.setHoras(h_inicio);
            sessionGestionarHorario.llenarDuracionHoras();
            horasRandom(duracion.getMax_bloque(), duracion.getNro_bloque());
            return true;  
        }catch(Exception e){            
            e.printStackTrace();
            ln_T_SFLoggerLocal.registrarLogErroresSistema(beanUsuario.getNidUsuario(), "LOG", CLASE, 
                                                          "validaConfiguracionHorario(BeanDuracionHorario duracion, List<BeanConfiguracionHorario> lstConfHorario)", 
                                                          "Error al validar la configuracion del Horario", Utils.getStack(e));
            return false;
        }
    }  
    
    /**
     * Metodo que busca las leciones del salon y lo ingresa en un vector para luego ser validado
     * @return
     */
    public boolean llenarHorario(){
        try{
            BeanMain horario[][] = new BeanMain[sessionGestionarHorario.getNroBloque()][5];//5 si es solo 5 dias a la semana
            int horasFree[] = new int[1];
            horasFree[0] = sessionGestionarHorario.getNroBloque()*5;
            List<BeanMain> lstLecciones = cargarleccionProfesorAula(sessionGestionarHorario.getNidAulaProfesor_aux(), true);
            //List<BeanMain> lstLecciones = ln_C_SFMainRemote.getLstMainByAttr_LN_Aula(sessionGestionarHorario.getNidAulaProfesor_aux());// reemplazar el "2" por sessionGestionarHorario.getNidAula()
            List<BeanMain> lst_defecto = new ArrayList();
            llenarLstDias(5); //llena una lista auxiliar, se usara en generarHorario
            for(BeanMain main : lstLecciones){
                int hora = encuentraHora(main.getHoraInicio());
                if(hora > -1){
                    horario[hora][main.getNDia()] = main;
                    modicarHorasBeanDias(main.getNDia(), 1);//resto a los dias las horas que se encuentran grabadas
                    horasFree[0]--;
                }else{
                    lst_defecto.add(main);
                }
            }
            Time t_inicio[] = sessionGestionarHorario.getHoras();
            Time t_fin[] = sessionGestionarHorario.getHoras_fin();
            
            // public boolean restriccionHoras(String vista, String codigo, Time t_inicio[], Time t_fin[], BeanMain horario[][], int horasFree[]){ 
            
            if(!restriccionHoras(sessionGestionarHorario.getNidTipoVista(), sessionGestionarHorario.getNidAulaProfesor_aux(), 
                                t_inicio, t_fin, horario, horasFree) || !horarioOcupado(sessionGestionarHorario.getNidAulaProfesor_aux(), 
                                t_inicio, t_fin, horario, horasFree)){
                return false;
            }
            if(lst_defecto.size() > 0){
                return false;
            }else{
                Utils.putSession("Horas", horasFree);
                sessionGestionarHorario.setHorario(horario);
                eliminarDiasOcupados();
                return true;
            }  
        }catch(Exception e){
            e.printStackTrace();
            ln_T_SFLoggerLocal.registrarLogErroresSistema(beanUsuario.getNidUsuario(), "LOG", CLASE, "llenarHorario()", 
                                                          "Error al llenar el Horario", Utils.getStack(e));
            return false;
        }        
    }
    
    /**
     * Se almacena las lecciones para luego generar el horario
     * @return
     */
    public String agregarLeccion() {
        if(sessionGestionarHorario.isRenderGenerario()){
            sessionGestionarHorario.setRenderGenerario(false);
            List<BeanMain> lstBeanMain = new ArrayList();
            sessionGestionarHorario.setLstBeanMain(lstBeanMain);
        }
        BeanMain main = new BeanMain();
        main.setDniProfesor(sessionGestionarHorario.getNidProfesor());
        main.setNidCurso(Integer.parseInt(sessionGestionarHorario.getNidCurso()));
        main.setNroHoras(nroHoras);
        main.setNroHorasReal(nroHoras);
        main.setNombreProfesor(sessionGestionarHorario.getNombreProfesor());
        main.setNombreCurso(sessionGestionarHorario.getNombreCurso());
        main.setNombreArea(sessionGestionarHorario.getNombreArea());
        if(verificarLeccion(main)){            
            if(!verificarLeccionIgual(main)){
                main.setEstado("1");
                sessionGestionarHorario.getLstBeanMain().add(main);
            }            
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
                cont = cont - m.getNroHoras();
            }
        }
        if(main.getNroHoras() > cont){
            cont = cont < 0 ? 0 : cont;
            Utils.mostrarMensaje(ctx,"Quedan "+cont+" horas por asignar al curso "+main.getNombreCurso(), null, 2);
            return false;
        }
        return true;
    }
    
    public boolean verificarLeccionIgual(BeanMain main){
        for(BeanMain m : sessionGestionarHorario.getLstBeanMain()){
            if(m.getNidCurso() == main.getNidCurso() && 
               m.getDniProfesor().compareTo(main.getDniProfesor()) == 0){
                int horas = m.getNroHoras() + main.getNroHoras(); 
                main.setNroHoras(horas);
                main.setNroHorasReal(horas);
                return true;
            }
        }
        return false;
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
    
    ////////////////// XXX /////////////////////////////////////////////////////////////
   
    public void generarHorario(ActionEvent actionEvent) {
        try{
            BeanMain horario[][] = sessionGestionarHorario.getHorario();
            List<BeanMain> lst = sessionGestionarHorario.getLstBeanMain();
            int maxBloque = sessionGestionarHorario.getMaxBloque();
            for(BeanMain main : lst){
                List<BeanDia> dias = ordenarLstDiasByHoras();
                quitarDiasIngresados(dias, main);
                ubicaMain(main, horario, dias, maxBloque);
            }
            guardarGenerarHorario();
            sessionGestionarHorario.setRenderGenerario(true);
            llenarHorario();
            Utils.addTargetMany(thor, thoras);
        }catch(Exception e){
            ln_T_SFLoggerLocal.registrarLogErroresSistema(beanUsuario.getNidUsuario(), "LOG", CLASE, 
                                                          "generarHorario(ActionEvent actionEvent)", 
                                                          "Error al guardar el horario Generado", 
                                                          Utils.getStack(e));
            e.printStackTrace();
        }        
    }

    public void ubicaMain(BeanMain main, BeanMain horario[][], List<BeanDia> dias, int maxBloque) {
        if(main.getNroHoras() <= 0 || dias.size() == 0){
            return;
        }
        if(main.getNroHoras() % maxBloque == 0){
            encuentraEspacio(horario, main, dias, randomHoras(), maxBloque);
        }else{
            //1SD56A1A56S1AS56S
            BeanDia dia = encontrarDiaNoDivisible(dias, maxBloque);
            encuentraEspacioImpar(horario, main, dias, main.getNroHoras() % maxBloque, dia);
            ubicaMain(main, horario, dias, sessionGestionarHorario.getMaxBloque());
        }        
    }
    
    public void encuentraEspacioImpar(BeanMain horario[][], BeanMain main, List<BeanDia> dias, int maxBloque, BeanDia dia){
        try{
            if(dia == null){
                int posicion = main.getNroHoras() / sessionGestionarHorario.getMaxBloque();
                dia = dias.get(posicion < dias.size() ? posicion : (dias.size() - 1));        
                if(!ingresarSecuencial(main, dia.getNDia(), maxBloque, dias)){
                    main.setNroHoras(main.getNroHoras() - maxBloque);
                }
            }else{
                int cont = 0 ;
                for(int i = 0 ; i < sessionGestionarHorario.getNroBloque(); i++){
                    if(horario[i][dia.getNDia()] != null){
                        cont++;                    
                    }
                    if((i+1) % sessionGestionarHorario.getMaxBloque() == 0 && cont != 0 && cont != sessionGestionarHorario.getMaxBloque()){
                        cont = i - cont;
                        break;
                    }
                    if((i+1) % sessionGestionarHorario.getMaxBloque() == 0){
                        cont = 0;
                    }
                }
                boolean valida = false;
                for(int i = cont; i < cont + sessionGestionarHorario.getMaxBloque(); i++){
                    valida = validarRango(horario, main, i, dia.getNDia(), maxBloque);
                    if(valida){
                        validarRango_aux(main, dia.getNDia(), dias, maxBloque);
                        break;
                    }
                }
                if(!valida){
                    encuentraEspacioImpar(horario, main, dias, maxBloque, null);
                }
            }   
        }catch(Exception e){
            ln_T_SFLoggerLocal.registrarLogErroresSistema(beanUsuario.getNidUsuario(), "LOG", CLASE, 
                                                          "encuentraEspacioImpar(...)", 
                                                          "Error al encontrar un espacio para una leccion menor al maximo", 
                                                          Utils.getStack(e));
        }                
    }
    
    public void encuentraEspacio(BeanMain horario[][], BeanMain main, List<BeanDia> dias, List<Integer> horas, int maxBloque){
        try{
            if(horas.size() == 0){
                dias.remove(dias.get(0));
                ubicaMain(main, horario, dias, maxBloque);
                return;
            }         
            if(validarRango(horario, main, horas.get(0), dias.get(0).getNDia(), maxBloque)){
                validarRango_aux(main, dias.get(0).getNDia(), dias, maxBloque);
                ubicaMain(main, horario, dias, maxBloque);
            }else{
                horas.remove(horas.get(0));
                encuentraEspacio(horario, main, dias, horas, maxBloque);
            } 
        }catch(Exception e){
            e.printStackTrace();
            ln_T_SFLoggerLocal.registrarLogErroresSistema(beanUsuario.getNidUsuario(), "LOG", CLASE, 
                                                          "encuentraEspacio(...)", 
                                                          "Error al encontrar un espacio para una leccion maximo de horas", 
                                                          Utils.getStack(e));
        }
    }
    
    /**
     * Metodo que valida las horas disponibles en un rango de n horas (max Bloque)
     * @author david
     * @param horario
     * @param main
     * @param hora
     * @param dia
     * @param maxBloque
     * @return
     */
    public boolean validarRango(BeanMain horario[][], BeanMain main,int hora, int dia, int maxBloque){
        try{
            for(int i = hora ; i < hora + maxBloque; i++){  
                if(horario[i][dia] != null || !validarCruce(1, dia, i, main.getDniProfesor(), 0)){
                    return false;
                }
            }
            /////si paso significa que esos dias esas horas estan libres
            for(int i = hora ; i < hora + maxBloque; i++){
                horario[i][dia] = main;
            }
            return true;
        }catch(Exception e){
            ln_T_SFLoggerLocal.registrarLogErroresSistema(beanUsuario.getNidUsuario(), "LOG", CLASE, 
                                                          "validarRango(...)", 
                                                          "Error al validar las horas disponibles", 
                                                          Utils.getStack(e));
            return false;
        }        
    }
    
    /**
     * Metodo que guarda el horario generado
     * @author david
     */
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
            ln_T_SFLoggerLocal.registrarLogErroresSistema(beanUsuario.getNidUsuario(), "LOG", CLASE, 
                                                          "guardarGenerarHorario()", 
                                                          "Error al guardar el Horario Generado", 
                                                          Utils.getStack(e));
            e.printStackTrace();
        }        
    }
    
    /**
     * Metodo que guarda, modifca las lecciones de Main
     * @author david
     * @param evento
     * @param main
     * @param dia
     * @param leccion
     * @param dniProfesor
     */
    public void gestionarMain_aux(int evento, BeanMain main, int dia, int leccion, String dniProfesor){
        try{
            Time inicio[] = sessionGestionarHorario.getHoras();
            Time fin[] = sessionGestionarHorario.getHoras_fin();
            if(evento == 1 || evento == 2){
                boolean valida = (evento == 1 ? true : validarCruce(2, dia, leccion, dniProfesor, main.getNidMain()));
                if(valida){
                    /* ln_T_SFMainRemote.gestionarMain_LN(evento, 
                                                       main.getNidMain(),
                                                       dniProfesor, 
                                                       Integer.parseInt(sessionGestionarHorario.getNidAulaProfesor_aux()), 
                                                       main.getNidCurso(), 
                                                       dia, 
                                                       inicio[leccion], 
                                                       fin[leccion]);   */
                }                      
            }        
            if(evento == 3){
                ln_T_SFMainRemote.eliminarMain_LN(main.getNidMain());
            }
            main = null;      
        }catch(Exception e){
            ln_T_SFLoggerLocal.registrarLogErroresSistema_nidEvento(beanUsuario.getNidUsuario(), "LOG", CLASE, 
                                                          "gestionarMain_aux(int evento, BeanMain main, int dia, int leccion, String dniProfesor)", 
                                                          "Error al gestionar Horario", 
                                                          Utils.getStack(e), evento);
            e.printStackTrace();
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
    
    /**
     * Llena una lista con el nro de bloques por dia para luego ser validado rapidamente
     * @author david
     * @param dias
     */
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
    
    /**
     * Elimina los dias que ya estan ocupados
     * @author david
     */
    public void eliminarDiasOcupados(){
        try{
            List<BeanDia> lstDias = sessionGestionarHorario.getLstDia();
            List<BeanDia> lstAux = new ArrayList();
            for(BeanDia dia : lstDias){
                if(dia.getHoras() == 0){
                    lstAux.add(dia);
                }
            }
            for(BeanDia dia : lstAux){
                lstDias.remove(dia);
            }
        }catch(Exception e){
            ln_T_SFLoggerLocal.registrarLogErroresSistema(beanUsuario.getNidUsuario(), "LOG", CLASE, 
                                                          "eliminarDiasOcupados()", 
                                                          "Error al eliminar los dias ocupados", 
                                                          Utils.getStack(e));
            e.printStackTrace();
        }        
    }
    
    /**
     * Modifica la lista dias al ingresar una leccion
     * @param ndia
     * @param hora
     */
    public void modicarHorasBeanDias(int ndia, int hora){           
        for(BeanDia dia : sessionGestionarHorario.getLstDia()){
            if(dia.getNDia() == ndia){
                dia.setHoras(dia.getHoras() - hora);  
            }
        }
    }
    
    /**
     * Ordena el vector Dias segun las horas disponibles de mayor a menor
     * @return
     */
    public List<BeanDia> ordenarLstDiasByHoras(){
        List<BeanDia> lst_aux = new ArrayList<>(sessionGestionarHorario.getLstDia());
        Random rndm = new Random();
        Collections.shuffle(lst_aux, rndm);
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
     * Valida los cursos ya ingresados y si esta en su maximo las elimina
     * @param lst
     * @param main
     */
    public void quitarDiasIngresados(List<BeanDia> lst, BeanMain main){        
        try{
            BeanMain horario[][] = sessionGestionarHorario.getHorario();
            List<BeanDia> lstDia = sessionGestionarHorario.getLstDia();
            for(BeanDia dia : lstDia){
                int d = dia.getNDia();
                if(dia.getHoras() != sessionGestionarHorario.getMaxBloque()*5){
                    int cont = validarHorasMaximoPorDia(horario, d, main.getNidCurso());
                    if(cont == sessionGestionarHorario.getMaxBloque()){
                        eliminarDias_aux(lst, d);
                    }
                    int cantidad = sessionGestionarHorario.getMaxBloque() - cont;
                    if(cont < sessionGestionarHorario.getMaxBloque() && cont != 0){                        
                        int exec = 0;
                        for(int i = 0 ; i < sessionGestionarHorario.getNroBloque(); i++){
                            if(horario[i][d] != null && horario[i][d].getNidCurso() == main.getNidCurso()){
                                if(exec == 0){
                                    exec = 1;
                                    if((i - cantidad) >= 0){
                                        if(validarRango(horario, main, i - cantidad, d, cantidad)){
                                            validarRango_aux(main, d, lst, cantidad);
                                            break;
                                        }
                                    }                                
                                }
                                if((i + cantidad) < sessionGestionarHorario.getNroBloque()){
                                    if(validarRango(horario, main, i + 1, d, cantidad)){
                                        validarRango_aux(main, d, lst, cantidad);
                                        break;
                                    }
                                }                            
                            }
                        }                    
                    }
                    //ingresarSecuencial(main, d, cantidad, lst);
                }
                
            }       
        }catch(Exception e){
            ln_T_SFLoggerLocal.registrarLogErroresSistema(beanUsuario.getNidUsuario(), "LOG", CLASE, 
                                                          "quitarDiasIngresados(List<BeanDia> lst, BeanMain main)", 
                                                          "Error al quitar dias para los cursos que ya estaban ingresados", 
                                                          Utils.getStack(e));
            e.printStackTrace();
        }
    }
    
    public boolean ingresarSecuencial(BeanMain main, int dia, int cantidad, List<BeanDia> lst){
        BeanMain horario[][] = sessionGestionarHorario.getHorario();
        for(int i = 0 ; i < sessionGestionarHorario.getNroBloque(); i++){
            if((i + cantidad) <= sessionGestionarHorario.getNroBloque() && validarRango(horario, main, i, dia, cantidad)){
                validarRango_aux(main, i, lst, cantidad);
                return true;
            }
        }   
        return false;
    }
    
    public void validarRango_aux(BeanMain main, int dia, List<BeanDia> lstDia, int cantidad){
        main.setNroHoras(main.getNroHoras() - cantidad);
        main.setNroHoras_aux(main.getNroHoras_aux() + cantidad);
        modicarHorasBeanDias(dia, sessionGestionarHorario.getMaxBloque());
        eliminarDias_aux(lstDia, dia);
    }
    
    public void eliminarDias_aux(List<BeanDia> lstDia, int dia){
        for(BeanDia bean : lstDia){
            if(bean.getNDia() == dia){
                lstDia.remove(bean);
                break;
            }
        }
    }
    
    public List<Integer> randomHoras(){
        List<Integer> horas = new ArrayList<>(sessionGestionarHorario.getHorasRandom());
        Random rndm = new Random();
        Collections.shuffle(horas, rndm);
        return horas;
    }
    
    /**
     * Encuentra el metodo que tenga horarios disparejos
     * @param main
     * @param lst
     * @param maxBloque
     * @return
     */
    public BeanDia encontrarDiaNoDivisible(List<BeanDia> lst, int maxBloque){
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
    
    public void horasRandom(int maxBloque, int nroBloque){
        List<Integer> horas = new ArrayList();
        for(int i = 0 ; i < nroBloque; i = i + maxBloque ){
            horas.add(i);
        }
        sessionGestionarHorario.setHorasRandom(horas);
    }
    
   
   /// METODOS EN EL CALENDARIO ///
    /**
     * Metodo que carga los datos de la leccion selecionada en session y abre el popColor
     * @param actionEvent
     */
    public void cambiarColor(ActionEvent actionEvent) {
        sessionGestionarHorario.cargarDatosSelec();
        sessionGestionarHorario.cargarDatosColor();
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
    
    /**
     * Elimina los dias seleccionados en la vista
     * @param evento
     */
    public void eliminarDiasSelecionados(int evento){
        try{
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
        }catch(Exception e){
            e.printStackTrace();
        }
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
            if(validarCruce(2, nDia, nLeccion, sessionGestionarHorario.getNidDni_aux(), 0)){
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
    public boolean validarCruce(int tipo, int dia, int leccion, String dni, int nidMain){
        Time inicio[] = sessionGestionarHorario.getHoras();
        Time fin[] = sessionGestionarHorario.getHoras_fin();        
        List<BeanMain> listBean = ln_C_SFMainRemote.CruceLecionByProfesor(dni, dia, 
                                                                          inicio[leccion], 
                                                                          fin[leccion], 
                                                                          nidMain);
        List<BeanRestriccionHorario> lstRHP = ln_C_SFRestriccionHorarioRemote.countCruceRestriccionHorario(dni, 
                                                                                                           dia, 
                                                                                                           inicio[leccion], 
                                                                                                           fin[leccion]);
        if(tipo == 2){
            for(BeanMain main : listBean){
                Utils.mostrarMensaje(ctx,"Disponibilidad del profesor "+main.getNombreProfesor()+" ocupada en el aula "+main.getNombreAula(), null, 2);
            }
            for(BeanRestriccionHorario res : lstRHP){
                Format formatter = new SimpleDateFormat("hh:mm");
                String msj = "Se restringio la hora de " + formatter.format(res.getHora_ini())+ " a "+ formatter.format(res.getHora_fin()) 
                      + " de los dias " + getNomDia(dia);
                Utils.mostrarMensaje(ctx,msj, null, 2);
            }
        }   
        return listBean.size() == 0 || lstRHP.size() == 0;
    }
    
    public String getNomDia(int dia){
        return sessionGestionarHorario.getDia(dia);
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
                List listComString = new ArrayList();
                if(tipoVistaAula()){            
                    listComString = Utils.llenarCombo(ln_C_SFUtilsRemote.getAulaByNidSedeNivel(Integer.parseInt(valueChangeEvent.getNewValue().toString()), 
                                                                         Integer.parseInt(sessionGestionarHorario.getNidNivel())));
                }else{
                    listComString = Utils.llenarComboString(ln_C_SFProfesorRemote.getPRofesorPorSedeYNivel(valueChangeEvent.getNewValue().toString(), 
                                                                                                           sessionGestionarHorario.getNidNivel(), 
                                                                                                           0));
                }   
                sessionGestionarHorario.setLstAulaProfesor(listComString);
                Utils.addTarget(choiceAulaProfesor); 
            }             
        }
    }

    /**
     * Metodo changeListener para mostrar el aula segun sede y nivel
     * @param valueChangeEvent
     */
    public void changeListenerNivel(ValueChangeEvent valueChangeEvent) {
        if(valueChangeEvent.getNewValue() != null){
            choiceNivel.setValue(valueChangeEvent.getNewValue());
            if(sessionGestionarHorario.getNidSede() != null){
                List listComString = new ArrayList();
                System.out.println(tipoVistaAula());
                if(tipoVistaAula()){            
                    System.out.println("Aula");
                    listComString = Utils.llenarCombo(ln_C_SFUtilsRemote.getAulaByNidSedeNivel(Integer.parseInt(sessionGestionarHorario.getNidSede()), 
                                                                         Integer.parseInt(valueChangeEvent.getNewValue().toString())));
                }else{
                    System.out.println("Profesor");
                    listComString = Utils.llenarComboString(ln_C_SFProfesorRemote.getPRofesorPorSedeYNivel(sessionGestionarHorario.getNidSede(), 
                                                                                                           valueChangeEvent.getNewValue().toString(), 
                                                                                                           0));
                }   
                sessionGestionarHorario.setLstAulaProfesor(listComString);
                Utils.addTarget(choiceAulaProfesor);              
            }
        }
    }
    
    /**
     * Metodo changeListener para obtener el nombre del aula
     * @param valueChangeEvent
     */
    public void changeListenerAula(ValueChangeEvent valueChangeEvent) {
        if(valueChangeEvent.getNewValue() != null){
            sessionGestionarHorario.setNidAulaProfesor(valueChangeEvent.getNewValue().toString());
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
            sessionGestionarHorario.setRenderCurso(true);
            Utils.addTarget(pfGHor);
        }
    }

    public void changeListenerArea_aux(ValueChangeEvent valueChangeEvent) {
        if(valueChangeEvent.getNewValue() != null){
            sessionGestionarHorario.setLstCurso_aux(Utils.llenarCombo(
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
    
    /// ULTIMOS METODOS PARA ACABAR ESTA COSA ////
    /**
     * Metodo para llenar los tipos de vista
     */
    public void llenarTipoVista(){
        String vec[] = {"Salon", "Profesor"};
        List vista = new ArrayList();
        for(int i = 0; i < vec.length; i++){
            SelectItem si = new SelectItem();
            si.setValue(i+"");
            si.setLabel(vec[i]);
            vista.add(si);
        }
        sessionGestionarHorario.setListaTipoVista(vista);
    }
    
    public boolean tipoVistaAula(){
        return !sessionGestionarHorario.isBooleanTipoVista();
    }
    
    public void changeTipoVista(ValueChangeEvent valueChangeEvent) {
        if (valueChangeEvent.getNewValue() != null) {            
            sessionGestionarHorario.setNidTipoVista(valueChangeEvent.getNewValue().toString());
            sessionGestionarHorario.setBooleanTipoVista(valueChangeEvent.getNewValue().toString().compareTo("1") == 0);
            if(sessionGestionarHorario.getNidSede() != null && sessionGestionarHorario.getNidNivel() != null){
                List listComString = new ArrayList();
                if(tipoVistaAula()){            
                    listComString = Utils.llenarCombo(ln_C_SFUtilsRemote.getAulaByNidSedeNivel(Integer.parseInt(sessionGestionarHorario.getNidSede()), 
                                                                         Integer.parseInt(sessionGestionarHorario.getNidNivel())));
                }else{
                    listComString = Utils.llenarComboString(ln_C_SFProfesorRemote.getPRofesorPorSedeYNivel(sessionGestionarHorario.getNidSede(), 
                                                                                                           sessionGestionarHorario.getNidNivel(), 
                                                                                                           0));
                }   
                sessionGestionarHorario.setLstAulaProfesor(listComString);
                Utils.addTarget(choiceAulaProfesor); 
            }
        }
    }
    
    public List<BeanMain> cargarleccionProfesorAula(String dato, boolean concidencia){
        if(tipoVistaAula()){
            return ln_C_SFMainRemote.getLstMainByAttr_LN_Aula(dato);
        }else{
            return ln_C_SFMainRemote.getLstMainByAttr_LN_Profesor(dato, 
                                                                  sessionGestionarHorario.getNidSede_aux(), 
                                                                  sessionGestionarHorario.getNidNivel_aux(), 
                                                                  concidencia);
        }        
    }
    
    public boolean restriccionHoras(String vista, String codigo, Time t_inicio[], Time t_fin[], BeanMain horario[][], int horasFree[]){  
        boolean valida = true;
        List<BeanRestriccionHorario> restricion = ln_C_SFRestriccionHorarioRemote.listRestriccionHorarioByNidExcepcion(vista, codigo);
        for(BeanRestriccionHorario res : restricion){
            for(Integer lec : encuentrarestriccionHoras(res, t_inicio, t_fin)){
                if(horario[lec][res.getNDia()] == null){
                    horario[lec][res.getNDia()] = new BeanMain();
                    horario[lec][res.getNDia()].setNidMain(-1);
                    horario[lec][res.getNDia()].setRestric(res);
                    modicarHorasBeanDias(res.getNDia(), 1);//resto a los dias las horas que se encuentran grabadas
                    horasFree[0]--;
                }else{
                    valida = false;
                }
            }
        } 
        return valida;
    }
    
    public boolean horarioOcupado(String codigo, Time t_inicio[], Time t_fin[], BeanMain horario[][], int horasFree[]){
        boolean valida = true;
        if(!tipoVistaAula()){
            List<BeanMain> lstLecciones = cargarleccionProfesorAula(codigo, false);            
            for(BeanMain main : lstLecciones){
                for(Integer lec : encuentraOcupadoHoras(main, t_inicio, t_fin)){
                    if(horario[lec][main.getNDia()] == null){
                        horario[lec][main.getNDia()] = new BeanMain();
                        main.setNidMain(-2);
                        horario[lec][main.getNDia()] = main;
                        modicarHorasBeanDias(main.getNDia(), 1);//resto a los dias las horas que se encuentran grabadas
                        horasFree[0]--;
                    }else{
                        valida = false;
                    }
                }
            }
        }       
        return valida;
    }
    
    public List<Integer> encuentrarestriccionHoras(BeanRestriccionHorario res, Time t_inicio[], Time t_fin[]){         
        return encuentraHoras(res.getHora_ini(), res.getHora_fin(), t_inicio, t_fin);
    }
    
    public List<Integer> encuentraOcupadoHoras(BeanMain main, Time t_inicio[], Time t_fin[]){
        return encuentraHoras(main.getHoraInicio(), main.getHoraFin(), t_inicio, t_fin);
    }
    
    public List<Integer> encuentraHoras(Time inicio, Time fin, Time t_inicio[], Time t_fin[]){
        List<Integer> horas = new ArrayList();
        for(int i = 0 ; i < t_inicio.length; i++){        
            if((t_inicio[i].before(inicio) && t_fin[i].after(fin)) || 
               (t_inicio[i].after(inicio) && t_inicio[i].before(fin)) ||
               (t_fin[i].after(inicio) && t_fin[i].before(fin)) || 
               (t_inicio[i].equals(inicio) && t_fin[i].equals(fin))){
                horas.add(i);
            }
        }
        return horas;
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

    public void setChoiceAulaProfesor(RichSelectOneChoice choiceAulaProfesor) {
        this.choiceAulaProfesor = choiceAulaProfesor;
    }

    public RichSelectOneChoice getChoiceAulaProfesor() {
        return choiceAulaProfesor;
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

    public void setRes(RichColumn res) {
        this.res = res;
    }

    public RichColumn getRes() {
        return res;
    }

}
