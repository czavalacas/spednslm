package sped.vista.beans.horarios;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.io.InputStream;
import java.io.OutputStream;

import java.math.BigInteger;

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

import javax.servlet.ServletContext;

import jxl.write.WriteException;

import oracle.adf.view.rich.component.rich.RichDialog;
import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.RichSubform;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.data.RichTreeTable;
import oracle.adf.view.rich.component.rich.input.RichInputNumberSpinbox;
import oracle.adf.view.rich.component.rich.input.RichSelectBooleanCheckbox;
import oracle.adf.view.rich.component.rich.input.RichSelectManyChoice;
import oracle.adf.view.rich.component.rich.input.RichSelectOneChoice;

import oracle.adf.view.rich.component.rich.layout.RichPanelBox;

import oracle.adf.view.rich.component.rich.layout.RichPanelFormLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;

import oracle.adf.view.rich.component.rich.nav.RichButton;
import oracle.adf.view.rich.event.DialogEvent;
import oracle.adf.view.rich.event.PopupCanceledEvent;
import oracle.adf.view.rich.render.ClientEvent;

import org.apache.myfaces.trinidad.event.AttributeChangeEvent;
import org.apache.myfaces.trinidad.model.ChildPropertyTreeModel;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;

import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import sped.negocio.LNSF.IL.LN_T_SFLoggerLocal;
import sped.negocio.LNSF.IR.LN_C_SFConfiguracionHorarioRemote;
import sped.negocio.LNSF.IR.LN_C_SFDuracionHorarioRemote;
import sped.negocio.LNSF.IR.LN_C_SFLeccionRemote;
import sped.negocio.LNSF.IR.LN_C_SFMainRemote;
import sped.negocio.LNSF.IR.LN_C_SFNivelRemote;
import sped.negocio.LNSF.IR.LN_C_SFProfesorRemote;
import sped.negocio.LNSF.IR.LN_C_SFRestriccionHorarioRemote;
import sped.negocio.LNSF.IR.LN_C_SFSedeRemote;
import sped.negocio.LNSF.IR.LN_C_SFUtilsRemote;
import sped.negocio.LNSF.IR.LN_T_SFCursoRemoto;
import sped.negocio.LNSF.IR.LN_T_SFLeccionRemote;
import sped.negocio.LNSF.IR.LN_T_SFMainRemote;
import sped.negocio.LNSF.IR.LN_T_SFProfesorRemoto;
import sped.negocio.LNSF.IR.LN_T_SFRestriccionHorarioRemote;
import sped.negocio.LNSF.SFBean.LN_C_SFLeccionBean;
import sped.negocio.entidades.beans.BeanAula;
import sped.negocio.entidades.beans.BeanCombo;
import sped.negocio.entidades.beans.BeanComboString;
import sped.negocio.entidades.beans.BeanConfiguracionHorario;
import sped.negocio.entidades.beans.BeanCurso;
import sped.negocio.entidades.beans.BeanDia;
import sped.negocio.entidades.beans.BeanDuracionHorario;
import sped.negocio.entidades.beans.BeanHorario;
import sped.negocio.entidades.beans.BeanLeccion;
import sped.negocio.entidades.beans.BeanMain;
import sped.negocio.entidades.beans.BeanProfesor;
import sped.negocio.entidades.beans.BeanRestriccionHorario;
import sped.negocio.entidades.beans.BeanUsuario;

import sped.vista.Utils.Utils;

import utils.system;

public class bGestionarHorarios {

    @EJB
    private LN_C_SFSedeRemote ln_C_SFSedeRemote;
    @EJB
    private LN_C_SFNivelRemote ln_C_SFNivelRemote;
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
    @EJB
    private LN_T_SFLoggerLocal ln_T_SFLoggerLocal;
    @EJB
    private LN_C_SFProfesorRemote ln_C_SFProfesorRemote;
    @EJB
    private LN_T_SFProfesorRemoto ln_T_SFProfesorRemoto;
    @EJB
    private LN_T_SFRestriccionHorarioRemote ln_T_SFRestriccionHorarioRemote;
    @EJB
    private LN_C_SFRestriccionHorarioRemote ln_C_SFRestriccionHorarioRemote;
    @EJB
    private LN_T_SFLeccionRemote ln_T_SFLeccionRemote;
    @EJB
    private LN_C_SFLeccionRemote ln_C_SFLeccionRemote;
    
    private bSessionbGestionarHorarios sessionbGestionarHorarios;
    private BeanUsuario beanUsuario = (BeanUsuario) Utils.getSession("USER");
    private static final String CLASE = "sped.vista.beans.horarios.bGestionarHorario";
    FacesContext ctx = FacesContext.getCurrentInstance();
    private UISelectItems si1;
    private RichSelectOneChoice chSede;
    private RichSelectOneChoice chNivel;
    private UISelectItems si2;
    private RichPanelBox pbHor;
    private RichPanelGroupLayout pgl1;
    private RichSelectOneChoice soc1;
    private RichSelectManyChoice smc1;
    private RichPopup popDis;
    private RichTable tdis;
    private RichTable t1;
    private RichPopup popVer;
    private RichTable t2;
    private RichButton bagrPr;
    private RichPopup popAProf;
    private RichDialog d3;
    private RichPanelFormLayout pfl1;
    private RichTable t3;
    private RichPopup popAlec;
    private RichDialog d4;
    private RichSelectOneChoice chAulaProfesor;
    private RichSelectOneChoice chCurso;
    private RichSelectOneChoice chArea;
    private RichInputNumberSpinbox cant;
    private RichSelectOneChoice chDuracion;
    private RichTable t4;
    private RichButton bexp1;
    private RichSelectBooleanCheckbox sbc1;
    private RichSelectBooleanCheckbox sbc2;
    private RichPanelFormLayout pfl6;
    private RichPopup popCo;
    private RichButton bexp2;
    private RichPanelFormLayout pfl7;
    private RichPopup popColor;
    private RichPanelBox pb1;
    private RichTreeTable treeLec;
    private ChildPropertyTreeModel leccionesTree;
    private RichPopup popDis_aux;
    private RichSubform subAgr;
    private RichPanelFormLayout pfl8;
    private RichPanelBox pb2;
    private RichSelectOneChoice chAulaProfesor2;
    private RichSelectOneChoice chArea2;
    private RichSelectOneChoice chCurso2;
    private RichPopup popEM;
    private RichSelectOneChoice chProf;
    private RichSelectManyChoice choiceDia;

    public bGestionarHorarios() {
    }

    @PostConstruct
    public void methodInvokeOncedOnPageLoad() {
        if (sessionbGestionarHorarios.getExec() == 0) {
            sessionbGestionarHorarios.setProfesor(new BeanProfesor());
            sessionbGestionarHorarios.setListaSedesChoice(Utils.llenarCombo(ln_C_SFUtilsRemote.getSedes_LN()));
            sessionbGestionarHorarios.setNidTipoVista("0");
            sessionbGestionarHorarios.setExec(1);
            llenarVectorDias();
            llenarTipoVista();             
            //// inicializando entidades para generar las lecciones///
            sessionbGestionarHorarios.setAula(new BeanAula());
            sessionbGestionarHorarios.setDni(new BeanProfesor());
            sessionbGestionarHorarios.setCurso(new BeanCurso());            
        }
    }

    ///////////////////////// VERIFICA CONFIGURACION DEL HORARIO  ///////////////////////////////////////////

    /**
     * Metodo que valida para cargar el horario
     * @return
     */
    public String cargarHorario() {
        sessionbGestionarHorarios.setRenderHorario(false);
        if (verificarConfiguracionHorario(Integer.parseInt(sessionbGestionarHorarios.getNidSede()),
                                          Integer.parseInt(sessionbGestionarHorarios.getNidNivel()))) {
            sessionbGestionarHorarios.setNidSede_aux(Integer.parseInt(sessionbGestionarHorarios.getNidSede()));
            sessionbGestionarHorarios.setNidNivel_aux(Integer.parseInt(sessionbGestionarHorarios.getNidNivel()));             
            sessionbGestionarHorarios.setNomSedeNivel(sessionbGestionarHorarios.getNomSede().toUpperCase() 
                                                      + " NIVEL " +
                                                      sessionbGestionarHorarios.getNomNivel() .toUpperCase());                       
            cargarDatosVista();
            cargarDatosHorarios();
            llenarTipoDuracion();
            sessionbGestionarHorarios.setRenderHorario(true);                       
        }
        Utils.addTarget(pgl1);
        return null;
    }
    
    /**
     * verifica que exista restriciones para la configuracion de  horario
     * @param nidSede
     * @param nidNivel
     * @return
     */
    public boolean verificarConfiguracionHorario(int nidSede, int nidNivel) {
        boolean valida = false;
        BeanDuracionHorario duracion = ln_C_SFDuracionHorarioRemote.getDuracionHorarioBySedeNivel(nidSede, nidNivel);
        List<BeanConfiguracionHorario> lstCH = ln_C_SFConfiguracionHorarioRemote.getConfiguracionBySedeNivel(nidSede, nidNivel);
        if (duracion == null) {
            Utils.mostrarMensaje(ctx, "Configure los parametros del horario", "Error", 2);
        } else if (lstCH.size() == 0) {
            Utils.mostrarMensaje(ctx, "Configure las restriciones en el horario", "Error", 2);
        } else {
            valida = validaConfiguracionHorario(duracion, lstCH);
        }
        return valida;
    }

    /**
     * Metodo para verificar la configuracion del horario Y llena el rango de horas.
     * @param duracion
     * @param lstConfHorario
     * @return
     */
    public boolean validaConfiguracionHorario(BeanDuracionHorario duracion,
                                              List<BeanConfiguracionHorario> lstConfHorario) {
        try {
            Utils.putSession("maxHoras", (duracion.getMax_bloque() * 5)); //grabamos el maximo permitido por curso
            sessionbGestionarHorarios.setNroBloque(duracion.getNro_bloque()); //guardo el numero de bloques al dia permitido
            sessionbGestionarHorarios.setMaxBloque(duracion.getMax_bloque());
            Time h_inicio[] = new Time[duracion.getNro_bloque()];
            Calendar inicio = new GregorianCalendar();
            inicio.setTime(duracion.getHora_inicio());
            Time time_aux = new Time(inicio.getTimeInMillis());
            int cont = 1;
            boolean restr = false;
            h_inicio[0] = new Time(time_aux.getTime());
            while (cont < duracion.getNro_bloque()) {
                if (lstConfHorario.size() > 0) {
                    for (BeanConfiguracionHorario configuracionH : lstConfHorario) {
                        if (configuracionH.getHora_inicio().equals(time_aux)) {
                            cont--;
                            inicio.setTime(configuracionH.getHora_fin());
                            h_inicio[cont] = new Time(inicio.getTimeInMillis());
                            lstConfHorario.remove(configuracionH);
                            cont++;
                            restr = lstConfHorario.size() == 0 && cont < duracion.getNro_bloque() ? false : true;
                            break;
                        }
                        if (configuracionH.getHora_inicio().after(time_aux)) {
                            restr = false;
                            break;
                        }
                    }
                }
                if (!restr) {
                    time_aux.setTime(sumaHoras(inicio, duracion.getDuracion()).getTime());
                    h_inicio[cont] = new Time(time_aux.getTime());
                    cont++;
                }
            } // fin de la validacion
            if (lstConfHorario.size() != 0) { //valida si se validaron todas las restriciones
                return false;
            }
            llenarHorasFin(duracion.getDuracion(), h_inicio);
            sessionbGestionarHorarios.setHoras(h_inicio);
            llenarRangoHoras();
            ////// SE TRAE DE NUEVO LAS RESTRICCIONES Y SE GUARDA PARA MOSTRAR EN EL EXPORTAR /////////////////////
            sessionbGestionarHorarios.setLstConfHorario
                (ln_C_SFConfiguracionHorarioRemote.getConfiguracionBySedeNivel(Integer.parseInt(sessionbGestionarHorarios.getNidSede()),
                                                                               Integer.parseInt(sessionbGestionarHorarios.getNidNivel())));
            horasRandom(duracion.getMax_bloque(), duracion.getNro_bloque());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            ln_T_SFLoggerLocal.registrarLogErroresSistema(beanUsuario.getNidUsuario(), "LOG", CLASE,
                                                          "validaConfiguracionHorario(BeanDuracionHorario duracion, List<BeanConfiguracionHorario> lstConfHorario)",
                                                          "Error al validar la configuracion del Horario",
                                                          Utils.getStack(e));
            return false;
        }
    }
    
    ////////////////////////// TRAER DATOS DEL HORARIO ///////////////////////////////////////    
    /**
     * Carga los datos de los profesores o aulas segun sea el caso
     */
    public void cargarDatosVista(){
        List listComString = new ArrayList();
        if(tipoVistaAula()){            
            listComString = Utils.llenarCombo(ln_C_SFUtilsRemote.getAulaByNidSedeNivel(sessionbGestionarHorarios.getNidSede_aux(), 
                                                                                       sessionbGestionarHorarios.getNidNivel_aux()));            
        }else{
            listComString = Utils.llenarComboString(ln_C_SFProfesorRemote.getPRofesorPorSedeYNivel(sessionbGestionarHorarios.getNidSede_aux()+"", 
                                                                                                   sessionbGestionarHorarios.getNidNivel_aux()+"", 
                                                                                                   0));
        }   
        sessionbGestionarHorarios.setListaItems(listComString);
        if(smc1 != null){
            Utils.addTarget(smc1);
        }
    }
    
    public void cargarDatosVista_aux(){
        List listComString = new ArrayList();
        if(!tipoVistaAula()){            
            listComString = Utils.llenarCombo(ln_C_SFUtilsRemote.getAulaByNidSedeNivel(sessionbGestionarHorarios.getNidSede_aux(), 
                                                                                       sessionbGestionarHorarios.getNidNivel_aux()));
        }else{            
            listComString = Utils.llenarComboString(ln_C_SFProfesorRemote.getProfesoresLN());
        }   
        sessionbGestionarHorarios.setListaItems_aux(listComString);
    }
    
    public void cargarDatosVista_aux2(){
        List listComString = new ArrayList();
        if(!tipoVistaAula()){            
            listComString = Utils.llenarCombo(ln_C_SFUtilsRemote.getAulaByNidSedeNivel(sessionbGestionarHorarios.getNidSede_aux(), 
                                                                                       sessionbGestionarHorarios.getNidNivel_aux()));
        }else{            
            listComString = Utils.llenarComboString(ln_C_SFUtilsRemote.getProfesor_LN());
        }   
        sessionbGestionarHorarios.setListAC_ProfSalon(listComString);
    }
    
    public void cargarDatosHorarios(){
        try{
            List<BeanHorario> listaHorario = new ArrayList();              
            List<String> errores = new ArrayList();
            int nro_bloque = sessionbGestionarHorarios.getNroBloque(); 
            int i = 0;
            boolean tipoVista = tipoVistaAula();
            sessionbGestionarHorarios.setLstLeccionesTotal(new ArrayList());
            for (Object o : sessionbGestionarHorarios.getListaItems()) {
                SelectItem si = (SelectItem) o;
                Time t_inicio[] = sessionbGestionarHorarios.getHoras();
                Time t_fin[] = sessionbGestionarHorarios.getHoras_fin();
                List<BeanMain> lstLecciones = cargarleccionProfesorAula(si.getValue().toString(), true);
                BeanHorario new_horario = new BeanHorario(); 
                new_horario.setPosicion(i);
                new_horario.setTitulo(si.getLabel());
                new_horario.setCodigo(si.getValue().toString());
                if(!llenarHorario(listaHorario, new_horario,lstLecciones, nro_bloque, t_inicio, t_fin)){
                    errores.add("Error al cargar los datos "+(tipoVistaAula() ? "Aula" : "Profesor")+" "+si.getLabel());
                }else{
                    if(!tipoVistaAula() && new_horario.getColor() == null){
                        new_horario.setColor(ln_C_SFProfesorRemote.colorProfesor(new_horario.getCodigo()));
                    }                    
                    List<BeanLeccion> lstLec = ln_C_SFLeccionRemote.getLeccionesbyCodigoVista(tipoVista,  
                                                                                              new_horario.getCodigo(), 
                                                                                              sessionbGestionarHorarios.getNidSede_aux(), 
                                                                                              sessionbGestionarHorarios.getNidNivel_aux());
                    if(lstLec.size() > 0){
                        BeanLeccion lec = new BeanLeccion();
                        lec.setCodigoDniAula(new_horario.getCodigo());
                        lec.setTitulo(new_horario.getTitulo());
                        lec.setLstBeanLeccion(lstLec);
                        sessionbGestionarHorarios.getLstLeccionesTotal().add(lec);
                        for(BeanLeccion l : lstLec){
                            new_horario.setHorasLibres_aux(new_horario.getHorasLibres_aux() - ( l.getNroHoras() * l.getNroDuracion() ) );
                        }
                    }                    
                    i++;
                }     
            }
            for(String error : errores){
                Utils.mostrarMensaje(ctx, error, "Error", 2);
            }
            generarLeccionesTotal();
            sessionbGestionarHorarios.setListaHorario(listaHorario);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    /**
     * Metodo que busca las leciones del salon y lo ingresa en un vector para luego ser validado
     * @return
     */
    public boolean llenarHorario(List<BeanHorario> list, BeanHorario new_horario, List<BeanMain> lstLecciones, int nro_bloque, Time t_inicio[], Time t_fin[]){
        try{       
            BeanMain horario[][] = new BeanMain[nro_bloque][5];//5 si es solo 5 dias a la semana
            int horasFree[] = new int[1];
            int cont = 0;
            horasFree[0] = nro_bloque * 5;    
            llenarLstDias(new_horario, nro_bloque);
            for(BeanMain main : lstLecciones){
                int hora = encuentraHora(main, t_inicio, t_fin);
                if(hora > -1){
                    if(horario[hora][main.getNDia()] == null){
                        if(cont == 0 && !tipoVistaAula()){
                            new_horario.setColor(main.getColor_prof());
                            cont = 1;
                        }
                        horario[hora][main.getNDia()] = main;
                        modicarHorasBeanDias(main.getNDia(), 1, new_horario);//resto a los dias las horas que se encuentran grabadas
                        horasFree[0]--;
                    }else{
                        return false;                        
                    }                    
                }else{
                    return false;
                }
            }
            ///////////////////Restriciones ////////////////////////
            if(!horarioOcupado(new_horario, t_inicio, t_fin, horario, horasFree) || 
               !restriccionHoras(sessionbGestionarHorarios.getNidTipoVista(), new_horario, t_inicio, t_fin, horario, horasFree)){
                return false;
            }  
            ///////////////////FIN Restriciones ////////////////////////
            else{
                new_horario.setHorasLibres(horasFree[0]);
                new_horario.setHorasLibres_aux(horasFree[0]);
                new_horario.setHorario(horario);
                eliminarDiasOcupados(new_horario);
                list.add(new_horario);    
                return true;
            }  
        }catch(Exception e){
            e.printStackTrace();
            ln_T_SFLoggerLocal.registrarLogErroresSistema(beanUsuario.getNidUsuario(), "LOG", CLASE, "llenarHorario(...)", 
                                                          "Error al llenar el Horario", Utils.getStack(e));
            return false;
        }        
    }
    
    public boolean tipoVistaAula(){
        return !sessionbGestionarHorarios.tipoVistaAula();
    }
    
    /**
     * Valida la hora de inicio de una leccion con un vector tipo string
     * @param time
     * @return
     */
    public int encuentraHora(BeanMain main, Time t_inicio[], Time t_fin[]){
        for(int i = 0; i < t_inicio.length; i++){
            if(t_inicio[i].equals(main.getHoraInicio()) && t_fin[i].equals(main.getHoraFin())){
                return i;
            }
        }
        return -1;
    }
    
    ///////////////////////// RESTRICCION DE HORAS ///////////////////////////////////////////
    
       
    public void actionHorasDisponibles(ActionEvent event) {
        sessionbGestionarHorarios.setTitlePopDis("Tiempo Libre - "+ 
                                                 sessionbGestionarHorarios.titileListaHorarioByPosicion());
        horario_auxiliar();
        Utils.showPopUpMIDDLE(popDis);
    }
    
    public void actionVerHorario(ActionEvent event) {
        BeanHorario horario = sessionbGestionarHorarios.horarioSelect();
        sessionbGestionarHorarios.setTitlePopDis("Horario - "+ horario.getTitulo());
        Utils.putSession("horasLibres", horario.getHorasLibres_aux());
        cargarDatosVista_aux();
        if(sessionbGestionarHorarios.getExec2() == 0){
           sessionbGestionarHorarios.setListaAreaChoice(Utils.llenarCombo(ln_C_SFUtilsRemote.getAreas_LN_WS()));
           sessionbGestionarHorarios.setExec2(1);
        }
        sessionbGestionarHorarios.setLeccion(new BeanLeccion());      
        cargarDatosHorario(horario);        
        ///// pasamos el codigo del aula o profesor
        if(tipoVistaAula()){
            BeanAula aula = new BeanAula();
            aula.setNidAula(Utils.transforString(obtenerCodigo()));
            sessionbGestionarHorarios.setAula(aula);
        }else{
            BeanProfesor dni = new BeanProfesor();
            dni.setDniProfesor(obtenerCodigo());
            sessionbGestionarHorarios.setDni(dni);
        }         
        Utils.showPopUpMIDDLE(popVer);
    }
    
    public void cargarDatosHorario(BeanHorario horario){
        ///Cargamos los datos de ese horario ///
        List<BeanHorario> listaHorario = new ArrayList(); 
        Time t_inicio[] = sessionbGestionarHorarios.getHoras();
        Time t_fin[] = sessionbGestionarHorarios.getHoras_fin();
        int nro_bloque = sessionbGestionarHorarios.getNroBloque(); 
        List<BeanMain> lstLecciones = cargarleccionProfesorAula(horario.getCodigo(), true);
        BeanHorario new_horario = new BeanHorario();
        new_horario.setTitulo(horario.getTitulo());
        new_horario.setCodigo(horario.getCodigo());
        llenarHorario(listaHorario, new_horario,lstLecciones, nro_bloque, t_inicio, t_fin);
        sessionbGestionarHorarios.setHorario(listaHorario.get(0).getHorario());
        ///FIN Cargamos los datos de ese horario ///
    }
    
    public void horario_auxiliar(){
        sessionbGestionarHorarios.horario_auxiliar();
    }
    
    public void cambiarHorasDisponibles(ActionEvent event) {
        sessionbGestionarHorarios.cambiarHorasDisponibles();
        Utils.addTarget(tdis);        
    }
    
    public void popUpHorasDisponiblesDia(ActionEvent event) {
        int i = 0;
        List lstDiaLecHD = new ArrayList();
        for(String lec : sessionbGestionarHorarios.getRango_horas()){
            SelectItem si = new SelectItem();
            si.setValue(i);
            si.setLabel(lec);
            lstDiaLecHD.add(si);
            i++;
        }
        
        sessionbGestionarHorarios.setTitleDiaLecHD("Horas NO Disponibles de " + 
                                                   sessionbGestionarHorarios.getVectorDias()[sessionbGestionarHorarios.getPosDiaLecHD()]);
        sessionbGestionarHorarios.setSelectedDiaLecHD(checkearHorasDisponibles_DiaLec(sessionbGestionarHorarios.getPosDiaLecHD(), true, lstDiaLecHD));
        sessionbGestionarHorarios.setLstDiaLecHD(lstDiaLecHD);
        Utils.showPopUpMIDDLE(popDis_aux);
    }
    
    public void popUpHorasDisponiblesLecc(ActionEvent event) {
        int i = 0;
        List lstDiaLecHD = new ArrayList();
        for(String dia : sessionbGestionarHorarios.getVectorDias()){
            SelectItem si = new SelectItem();
            si.setValue(i);
            si.setLabel(dia);
            lstDiaLecHD.add(si);
            i++;
        }
        sessionbGestionarHorarios.setLstDiaLecHD(lstDiaLecHD);
        sessionbGestionarHorarios.setTitleDiaLecHD("Horas NO Disponibles del dia : " + 
                                                   sessionbGestionarHorarios.getRango_horas().get(sessionbGestionarHorarios.getPosDiaLecHD()));
        sessionbGestionarHorarios.setSelectedDiaLecHD(checkearHorasDisponibles_DiaLec(sessionbGestionarHorarios.getPosDiaLecHD(), false, lstDiaLecHD));
        Utils.showPopUpMIDDLE(popDis_aux);
    }
    
    /**
     * Metodo que revisa las horas no disponibles 
     * TRUE ---- DIA / FALSE ---- LECCION
     * @param dia
     * @return
     */
    public List checkearHorasDisponibles_DiaLec(int n, boolean v, List items){
        List lst = new ArrayList();
        BeanMain horario[][] = sessionbGestionarHorarios.getHorario();
        int size = v ? sessionbGestionarHorarios.getRango_horas().size() : sessionbGestionarHorarios.getVectorDias().length;
        sessionbGestionarHorarios.setBooDiaLecHD(v);
        for(int i=0; i < size; i++){
            BeanMain m = horario[ !v ? n : i ][ v ? n : i];
            if(m != null){
                if(m.getRestric() != null && m.getRestric().getEstado() != null && m.getRestric().getEstado().compareTo("1") == 0 ){
                    lst.add(i);
                }
                if(m.getNidMain() > 0){
                    SelectItem si = (SelectItem) items.get(i);
                    si.setDisabled(true);
                    items.set(i, si);
                }
            }
        }            
        return lst;
    }
    
    public void cerrarPopDis_aux(PopupCanceledEvent popupCanceledEvent) {
        BeanMain horario[][] = sessionbGestionarHorarios.getHorario();        
        boolean v = sessionbGestionarHorarios.isBooDiaLecHD();
        int size = v ? sessionbGestionarHorarios.getRango_horas().size() : sessionbGestionarHorarios.getVectorDias().length;
        int n = sessionbGestionarHorarios.getPosDiaLecHD();
        List select = sessionbGestionarHorarios.getSelectedDiaLecHD();
        int intSelec = 0;
        for(int i=0; i < size; i++){
            BeanMain m = horario[ !v ? n : i ][ v ? n : i];
            if(select != null && intSelec < select.size() && i == (Integer) select.get(intSelec)){
                m = ( m == null ? new BeanMain() : m );
                m.setRestric(m.getRestric() == null ? new BeanRestriccionHorario() : m.getRestric());
                m.getRestric().setEstado("1");       
                intSelec++;
            }else{
                if(m != null && m.getRestric() != null){
                    m.getRestric().setEstado("0");
                }
            }
            horario[ !v ? n : i ][ v ? n : i] = m;
        }  
        sessionbGestionarHorarios.setHorario(horario);
        Utils.addTarget(tdis);
    }
    
    public String grabarHorasDisponibles() {
        try{
            int pos = sessionbGestionarHorarios.getPosicionProfSalon();
            String nid = sessionbGestionarHorarios.getListaHorario().get(pos).getCodigo();
            String tipoVista = sessionbGestionarHorarios.getNidTipoVista();
            BeanMain horario[][] = sessionbGestionarHorarios.getHorario();
            List<BeanRestriccionHorario> list = new ArrayList();
            Time horas[] = sessionbGestionarHorarios.getHoras();
            Time horas_fin[] = sessionbGestionarHorarios.getHoras_fin();
            for(int i = 0 ; i < sessionbGestionarHorarios.getNroBloque(); i++){
                for(int j = 0; j < 5 ; j++){ 
                    BeanMain main = horario[i][j];
                    if(main != null){
                        if(main.getRestric() != null){
                            if(main.getRestric().getNidReho() != 0){
                                if(main.getRestric().getEstado().compareTo("0") == 0){
                                    list.add(main.getRestric());
                                }
                            }
                            else{
                                if(main.getRestric().getEstado() != null){
                                    if(main.getRestric().getEstado().compareTo("1") == 0){
                                        BeanRestriccionHorario rest = new BeanRestriccionHorario();
                                        rest.setHora_ini(horas[i]);
                                        rest.setHora_fin(horas_fin[i]);
                                        rest.setNDia(j);
                                        rest.setTipoRestr(tipoVista);
                                        rest.setNid(nid);
                                        list.add(rest);                                            
                                    }
                                }                                    
                            }
                        }
                    }
                }
            }
            ln_T_SFRestriccionHorarioRemote.grabarRestricciones(list);
        }catch(Exception e){
            ln_T_SFLoggerLocal.registrarLogErroresSistema(beanUsuario.getNidUsuario(), "LOG", CLASE, "grabarDisponibilidadHorario(DialogEvent dialogEvent)", 
                                                          "Error al grabar la disponibilidad", Utils.getStack(e));
            e.printStackTrace();
        }
        popDis.cancel();
        cargarDatosHorarios();
        Utils.addTarget(t1);
        return null;
    }

    public String cancelarHorasDisponibles() {
        cargarDatosHorarios();
        popDis.cancel();
        Utils.addTarget(t1);
        return null;
    } 
    
    public boolean restriccionHoras(String vista, BeanHorario new_horario, Time t_inicio[], Time t_fin[], BeanMain horario[][], int horasFree[]){  
        boolean valida = true;
        List<BeanRestriccionHorario> restricion = ln_C_SFRestriccionHorarioRemote.listRestriccionHorarioByNidExcepcion(vista, new_horario.getCodigo());
        for(BeanRestriccionHorario res : restricion){
            for(Integer lec : encuentrarestriccionHoras(res, t_inicio, t_fin)){
                if(horario[lec][res.getNDia()] == null || horario[lec][res.getNDia()].getNidMain() < 0){
                    horario[lec][res.getNDia()] = new BeanMain();
                    horario[lec][res.getNDia()].setNidMain(-1);
                    horario[lec][res.getNDia()].setRestric(res);
                    modicarHorasBeanDias(res.getNDia(), 1, new_horario);//resto a los dias las horas que se encuentran grabadas
                    horasFree[0]--;
                }else{
                    valida = false;
                }
            }
        } 
        return valida;
    }
    
    public boolean horarioOcupado(BeanHorario new_horario, Time t_inicio[], Time t_fin[], BeanMain horario[][], int horasFree[]){
        boolean valida = true;
        if(!tipoVistaAula()){
            List<BeanMain> lstLecciones = cargarleccionProfesorAula(new_horario.getCodigo(), false);            
            for(BeanMain main : lstLecciones){
                for(Integer lec : encuentraOcupadoHoras(main, t_inicio, t_fin)){
                    if(horario[lec][main.getNDia()] == null || horario[lec][main.getNDia()].getNidMain() < 0){
                        horario[lec][main.getNDia()] = new BeanMain();
                        main.setNidMain(-2);
                        horario[lec][main.getNDia()] = main;
                        modicarHorasBeanDias(main.getNDia(), 1, new_horario);//resto a los dias las horas que se encuentran grabadas
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
    
    /////////////////////// AGREGAR LECCCION ///////////////////////////////
    public void actionAgregarLeccion(ActionEvent actionEvent) {
        BeanHorario horario = sessionbGestionarHorarios.horarioSelect();
        sessionbGestionarHorarios.setTitlePopDis("Agregar Lecci\u00F3n. " + (tipoVistaAula() ? "Aula" : "Profesor") +
                                                 " " + horario.getTitulo());
        Utils.putSession("horasLibres", horario.getHorasLibres_aux());
        cargarDatosVista_aux();
        if(sessionbGestionarHorarios.getExec2() == 0){
           sessionbGestionarHorarios.setListaAreaChoice(Utils.llenarCombo(ln_C_SFUtilsRemote.getAreas_LN_WS()));
           sessionbGestionarHorarios.setExec2(1);
        }
        if(sessionbGestionarHorarios.getLstLecciones() == null){
            sessionbGestionarHorarios.setLstLecciones(new ArrayList());            
        }
        sessionbGestionarHorarios.setLeccion(new BeanLeccion());
        if(!existeenHorarioTotal(horario)){
            sessionbGestionarHorarios.setLstLecciones(new ArrayList());
        }
        ///// pasamos el codigo del aula o profesor
        if(tipoVistaAula()){
            BeanAula aula = new BeanAula();
            aula.setNidAula(Utils.transforString(obtenerCodigo()));
            sessionbGestionarHorarios.setAula(aula);
        }else{
            BeanProfesor dni = new BeanProfesor();
            dni.setDniProfesor(obtenerCodigo());
            sessionbGestionarHorarios.setDni(dni);
        } 
        Utils.showPopUpMIDDLE(popAlec);
    }
    
    public boolean existeenHorarioTotal(BeanHorario horario){
        if(sessionbGestionarHorarios.getLstLeccionesTotal() != null){            
            for(BeanLeccion lec : sessionbGestionarHorarios.getLstLeccionesTotal()){
                if(lec.getCodigoDniAula().compareTo(horario.getCodigo()) == 0){
                    sessionbGestionarHorarios.setLstLecciones(lec.getLstBeanLeccion());
                    return true;
                }
            }
        }
        return false;
    }
    
    
    public void AgregarLeccion(ActionEvent actionEvent) {
        BeanLeccion lec = sessionbGestionarHorarios.getLeccion();
        int horasLibres = Utils.transforString(Utils.getSession("horasLibres").toString());
        int resta = horasLibres - (lec.getNroDuracion() * lec.getNroHoras());
        if(sessionbGestionarHorarios.isRenderGenerarHorario()){
            sessionbGestionarHorarios.setRenderGenerarHorario(false);
        }
        if(resta >= 0 ){
            sumarHorasLibres(resta);
            Utils.putSession("horasLibres", resta);            
            lec.setCurso(sessionbGestionarHorarios.getCurso());
            lec.setAula(sessionbGestionarHorarios.getAula());
            lec.setProfesor(sessionbGestionarHorarios.getDni()); 
            boolean vista = tipoVistaAula();
            if(!verificarLeccionIgual(lec, vista)){     
                lec.setNroHoras_aux(lec.getNroHoras()); 
                lec.setTitulo(vista ? lec.getProfesor().getNombreCompleto() : lec.getAula().getDescripcionAula());
                sessionbGestionarHorarios.getLstLecciones().add(lec);
            }
            sessionbGestionarHorarios.setLeccion(pasarDatosLeccion(lec));
            Utils.addTarget(t4);            
        }else{
            int resto = horasLibres / lec.getNroDuracion();
            String msj = " .Se sugiere: Duraci\u00F3n = " + lec.getNroDuracion() +" y Nro Horas : "+ resto;
            Utils.mostrarMensaje(ctx, "Se sobre paso el numero de horas Libre. Horas libre : " + horasLibres + msj, null, 2);  
        }        
    }
    
    public String obtenerCodigo(){
        return sessionbGestionarHorarios.getListaHorario().get(sessionbGestionarHorarios.getPosicionProfSalon()).getCodigo();
    }
    
    public boolean verificarLeccionIgual(BeanLeccion lec, boolean tipoVista){
        for(BeanLeccion l : sessionbGestionarHorarios.getLstLecciones()){
            if(l.getCurso().getNidCurso() == lec.getCurso().getNidCurso() && 
                (tipoVista ? (l.getProfesor().getDniProfesor().compareTo(lec.getProfesor().getDniProfesor()) == 0) : (l.getAula().getNidAula() == lec.getAula().getNidAula()))
               && l.getNroDuracion() == lec.getNroDuracion()){; 
                l.setNroHoras(l.getNroHoras() + lec.getNroHoras());
                l.setNroHoras_aux(l.getNroHoras_aux() + lec.getNroHoras());
                return true;
            }
        }
        return false;
    }    
    
    public void sumarHorasLibres(int cantidad){
        sessionbGestionarHorarios.horarioSelect().setHorasLibres_aux(cantidad);
    }
    
    public void EliminarLeccion(BeanLeccion lec){
        int horasLibres = Utils.transforString(Utils.getSession("horasLibres").toString());
        Utils.putSession("horasLibres", horasLibres + (lec.getNroDuracion() * lec.getNroHoras_aux()));        
        if(lec.getNidLecc() > 0 && (lec.getNroHoras() != lec.getNroHoras_aux())){
            ln_T_SFLeccionRemote.eliminarLeccion(lec);            
            elimiarMainByNidLeccion(lec.getNidLecc());
            sessionbGestionarHorarios.setValidarEliminacionLecciones(true);
        }
        sessionbGestionarHorarios.getLstLecciones().remove(lec);
        if(sessionbGestionarHorarios.getLstLecciones().size() == 0){
            sessionbGestionarHorarios.getLstLeccionesTotal().remove(sessionbGestionarHorarios.getLstLecciones());
        }
        generarLeccionesTotal();
        Utils.addTarget(t4);
    }
    
    public void elimiarMainByNidLeccion(int nidLeccion){
        BeanMain horario[][] = sessionbGestionarHorarios.getListaHorario().get(sessionbGestionarHorarios.getPosicionProfSalon()).getHorario();       
        for(int i = 0 ; i < sessionbGestionarHorarios.getNroBloque(); i++){
            for(int j = 0; j < 5 ; j++){                   
                if(horario[i][j] != null && horario[i][j].getNidLecc() == nidLeccion){
                    horario[i][j] = null;
                }
            }
        }
    }
    
    public boolean disableErrorLeccion(BeanLeccion lec){
        return lec.getLstErrores() != null && lec.getLstErrores().size() > 0 && lec.getNroHoras() > 0;
    }
    
    public void verErrorLeccion(BeanLeccion lec){
        int comienzo = lec.getLstErrores().size() > 3 ? lec.getLstErrores().size() - 3 : 0;
        for(int i = comienzo; i < lec.getLstErrores().size(); i++){
            Utils.mostrarMensaje(ctx, lec.getLstErrores().get(i), null, 2);
        }
    }
    
    public BeanLeccion pasarDatosLeccion(BeanLeccion lec){
        BeanLeccion leccion = new BeanLeccion();
        leccion.setNroHoras(lec.getNroHoras());
        leccion.setNroDuracion(lec.getNroDuracion());
        leccion.setCurso(lec.getCurso());
        leccion.setProfesor(lec.getProfesor());
        leccion.setAula(lec.getAula());
        return leccion;
    }
    
    /////////////////////// FIN LECCCION ///////////////////////////////
 
    /////////////////////// GNERAR HORARIO //////////////////////////////////////////////////////
    
    public void generarHorarioParcial(ActionEvent actionEvent) {
        if(sessionbGestionarHorarios.getLstLecciones().size() > 0){
            BeanHorario horario = sessionbGestionarHorarios.horarioSelect();
            // si quiere volver a generar borro 
            if(sessionbGestionarHorarios.isVolverAGenerar()){
                eliminarHorario(horario, sessionbGestionarHorarios.getLstLecciones());
            }            
            generarHorarioParcial_aux(horario, sessionbGestionarHorarios.getLstLecciones());
            cargarDatosHorarios();    
            sessionbGestionarHorarios.setRenderGenerarHorario(true);
            sessionbGestionarHorarios.setLstLecciones(sessionbGestionarHorarios.getLstLecciones_aux());
            existeHorarioTotal(horario);
            Utils.addTarget(pgl1);
        }        
    }
    
    /**
     * Metodo que elimina el horario de la entidad beanHorario
     * @param horario
     */
    public void eliminarHorario(BeanHorario horario, List<BeanLeccion> lstLecciones){
            BeanMain h[][] = horario.getHorario();    
            /// recorremos la matriz y elimimos en la bd y en el objeto
            for(int i = 0 ; i < sessionbGestionarHorarios.getNroBloque(); i++){
                for(int j = 0; j < 5 ; j++){                   
                    if(h[i][j] != null && h[i][j].getNidMain() > 0){
                        ln_T_SFMainRemote.eliminarMain_LN(h[i][j].getNidMain());
                        h[i][j] = null ;
                    }
                }
            }
            //// x si no se llego a eliminar toddo volvemos a comprobar q este vacio
            ln_T_SFMainRemote.eliminarMainByAulaProfesor(horario.getCodigo(), 
                                                         sessionbGestionarHorarios.getNidSede_aux(), 
                                                         sessionbGestionarHorarios.getNidNivel_aux(), 
                                                         tipoVistaAula());
            
            ////modificamos las lecciones para que vuela a generar
            for(BeanLeccion lec : lstLecciones){
                lec.setNroHoras(lec.getNroHoras_aux());
            }
    }
    
    public void eliminarHorarioAction(ActionEvent actionEvent){
        List<BeanLeccion> lst = sessionbGestionarHorarios.getLstLecciones();        
        for(BeanLeccion lec : lst){
            int horasLibres = Utils.transforString(Utils.getSession("horasLibres").toString());
            Utils.putSession("horasLibres", horasLibres + (lec.getNroDuracion() * lec.getNroHoras_aux()));        
            if(lec.getNidLecc() > 0 && (lec.getNroHoras() != lec.getNroHoras_aux())){
                ln_T_SFLeccionRemote.eliminarLeccion(lec);            
                elimiarMainByNidLeccion(lec.getNidLecc());
                sessionbGestionarHorarios.setValidarEliminacionLecciones(true);
            }
        } 
        sessionbGestionarHorarios.setLstLecciones(new ArrayList());        
        for(BeanLeccion l : sessionbGestionarHorarios.getLstLeccionesTotal()){
            if(l.getCodigoDniAula().compareTo(sessionbGestionarHorarios.horarioSelect().getCodigo()) == 0){
                sessionbGestionarHorarios.getLstLeccionesTotal().remove(l);
                break;
            }
        }
        generarLeccionesTotal();
        Utils.addTarget(t4);
    }    
    
    public void generarHorarioTotal(ActionEvent actionEvent) {
        if(sessionbGestionarHorarios.getLstLeccionesTotal() != null){
            for(BeanLeccion lec : sessionbGestionarHorarios.getLstLeccionesTotal()){
                BeanHorario horario = getHorarioByCodigo(lec.getCodigoDniAula());
                // si quiere volver a generar borro XD
                if(sessionbGestionarHorarios.isVolverAGenerar()){
                    eliminarHorario(horario, sessionbGestionarHorarios.getLstLecciones());
                }         
                generarHorarioParcial_aux(horario, lec.getLstBeanLeccion());
            }
            cargarDatosHorarios();
            sessionbGestionarHorarios.setRenderGenerarHorarioTotal(true);
            generarLeccionesTotal();
            Utils.addTarget(pgl1);
        }
    }
    
    public void generarLeccionesTotal(){
        BeanLeccion lec = new BeanLeccion();
        lec.setTitulo("SPEDD");
        lec.setLstBeanLeccion(sessionbGestionarHorarios.getLstLeccionesTotal());
        leccionesTree = new ChildPropertyTreeModel(lec,"lstBeanLeccion");
        sessionbGestionarHorarios.setLeccionesTree(leccionesTree); 
    }
    
    public void existeHorarioTotal(BeanHorario horario){
        if(sessionbGestionarHorarios.getLstLeccionesTotal() != null){
            for(BeanLeccion lec : sessionbGestionarHorarios.getLstLeccionesTotal()){
                if(lec.getCodigoDniAula().compareTo(horario.getCodigo()) == 1){
                    sessionbGestionarHorarios.getLstLeccionesTotal().remove(lec);
                    Utils.addTarget(pgl1);
                    return;
                }
            }
        }
    }

    public void guardarHorarioParcial(ActionEvent actionEvent) {
        if(sessionbGestionarHorarios.getLstLecciones().size() > 0){
            ///metodo huevo ////    
            sessionbGestionarHorarios.setLstLecciones(ln_T_SFLeccionRemote.gestionarLecciones(true, sessionbGestionarHorarios.getLstLecciones()));
            //// metodo antiguo ///
            if(sessionbGestionarHorarios.isRenderGenerarHorarioTotal()){
                sessionbGestionarHorarios.setRenderGenerarHorarioTotal(false);
                sessionbGestionarHorarios.setLstLeccionesTotal(null);
            }
            if(sessionbGestionarHorarios.getLstLeccionesTotal() == null){
                sessionbGestionarHorarios.setLstLeccionesTotal(new ArrayList());
            }
            BeanHorario horario = sessionbGestionarHorarios.horarioSelect();
            BeanLeccion leccion = new BeanLeccion();
            leccion.setCodigoDniAula(horario.getCodigo());
            leccion.setTitulo(horario.getTitulo());
            leccion.setLstBeanLeccion(sessionbGestionarHorarios.getLstLecciones());
            if(!horarioParcialIgual(leccion)){
                sessionbGestionarHorarios.getLstLeccionesTotal().add(leccion);                
            }   
            generarLeccionesTotal();          
            Utils.addTarget(pbHor);
        }else{
            Utils.mostrarMensaje(ctx, "No hay lecciones para ingresar", null, 2);
        }
    }
    
    public boolean horarioParcialIgual(BeanLeccion leccion){
        for(BeanLeccion l : sessionbGestionarHorarios.getLstLeccionesTotal()){
            if(l.getCodigoDniAula().compareTo(leccion.getCodigoDniAula()) == 0){
                l = leccion;
                return true;
            }
        }        
        return false;
    }
    
    
    public void generarHorarioParcial_aux(BeanHorario h, List<BeanLeccion> lstLecciones){
        try{           
            int maxBloque = sessionbGestionarHorarios.getMaxBloque();
            int nroBloque = sessionbGestionarHorarios.getNroBloque();            
            /////guardamos las lecciones en la base de datos para utilizar el nid
            lstLecciones = ln_T_SFLeccionRemote.gestionarLecciones(false, lstLecciones);
            for(BeanLeccion leccion : lstLecciones){
                leccion.setLstErrores(leccion.getLstErrores() == null ? new ArrayList() : leccion.getLstErrores());
                List<BeanDia> dias = ordenarLstDiasByHoras(h);
                quitarDiasIngresados(h, dias, leccion, maxBloque, nroBloque);
                ubicaMain(h, leccion, dias, maxBloque, nroBloque);                
            }     
            Time ini[] = sessionbGestionarHorarios.getHoras();
            Time fin[] = sessionbGestionarHorarios.getHoras_fin();
            guardarGenerarHorario(h, ini, fin);            
            /////modificamos las lecciones con los datos ingresados
            lstLecciones = ln_T_SFLeccionRemote.gestionarLecciones(true, lstLecciones);
            sessionbGestionarHorarios.setLstLecciones_aux(lstLecciones);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void ubicaMain(BeanHorario h, BeanLeccion leccion, List<BeanDia> dias, int maxBloque, int nroBloque){
        if(leccion.getNroHoras() <= 0 || dias.size() == 0){
            ////////////////// EJECUTAMOS DE NUEVO /////////// - PERO ESTA VEZ NO ELIMINAMOS LOS DIAS 
            if(leccion.getNroHoras() > 0){
                if(leccion.getExec() == 0){
                    leccion.setExec(1);
                    //leccion.setLstErrores(new ArrayList());                    
                    ubicaMain(h, leccion, ordenarLstDiasByHoras(h), maxBloque, nroBloque);
                }else if(leccion.getExec() == 1){
                    leccion.setExec(2);
                    //leccion.setLstErrores(new ArrayList());
                    encuentraEspacioCom(h, leccion, ordenarLstDiasByHoras(h), randomHorasDuracion(leccion.getNroHoras(), nroBloque), maxBloque, nroBloque);
                }                  
            }             
            return;
        }
        if(leccion.getNroDuracion() == maxBloque){
            encuentraEspacioCom(h, leccion, dias, randomHoras(), maxBloque, nroBloque);
        }else{
            /*BeanDia dia = encontrarDiaNoDivisible(dias, maxBloque);
            encuentraEspacioImpar(h, leccion, dias, maxBloque, nroBloque, leccion.getNroDuracion(), dia);             */
            encuentraEspacioCom(h, leccion, dias, randomHorasDuracion(leccion.getNroDuracion(), nroBloque), leccion.getNroDuracion(), nroBloque); 
        }
    }
    
    public void encuentraEspacioImpar(BeanHorario h, BeanLeccion lec, List<BeanDia> dias, int maxBloque, int nroBloque, int duracion, BeanDia dia){
        try{
            BeanMain horario[][] = h.getHorario();
            if(dia == null){     
                encuentraEspacioCom(h, lec, dias, randomHorasDuracion(duracion, nroBloque), maxBloque, nroBloque);                
            }else{
                int cont = 0 ;
                for(int i = 0 ; i < maxBloque; i++){
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
                for(int i = cont; i < cont + maxBloque; i++){
                    valida = validarRango(h, lec, i, dia.getNDia(), nroBloque);
                    if(valida){
                        eliminarLeccionesCom(h, lec, dias, dia.getNDia(), maxBloque);
                        ubicaMain(h, lec, dias, maxBloque, nroBloque);
                        break;
                    }
                }
                if(!valida){
                    encuentraEspacioImpar(h, lec, dias, maxBloque, nroBloque, duracion, null);
                } 
            }
        }catch(Exception e){
            ln_T_SFLoggerLocal.registrarLogErroresSistema(beanUsuario.getNidUsuario(), "LOG", CLASE, 
                                                          "encuentraEspacioImpar(...)", 
                                                          "Error al encontrar un espacio para una leccion menor al maximo", 
                                                          Utils.getStack(e));
        }       
    }
    
    public boolean ingresarSecuencial(BeanHorario h, BeanLeccion lec, int dia, int cantidad, int nroBloque){        
        for(int i = 0 ; i < nroBloque; i++){
            if((i + cantidad) <= nroBloque && validarRango(h, lec, i, dia, nroBloque)){
                return true;
            }
        }   
        return false;
    }
    
    public void encuentraEspacioCom(BeanHorario h, BeanLeccion leccion, List<BeanDia> dias, List<Integer> horas, int maxBloque, int nroBloque){
        try{
            if(horas.size() == 0){
                dias.remove(dias.get(0));
                ubicaMain(h, leccion, dias, maxBloque, nroBloque);
                return ;
            }
            if(validarRango(h, leccion, horas.get(0), dias.get(0).getNDia(), nroBloque)){
                eliminarLeccionesCom(h, leccion, dias, dias.get(0).getNDia() , leccion.getNroDuracion());
                ubicaMain(h, leccion, dias, maxBloque, nroBloque);
            }else{
                horas.remove(horas.get(0));
                encuentraEspacioCom(h, leccion, dias, horas, maxBloque, nroBloque);
            }
        }catch(Exception e){
            e.printStackTrace();
            ln_T_SFLoggerLocal.registrarLogErroresSistema(beanUsuario.getNidUsuario(), "LOG", CLASE, 
                                                          "encuentraEspacioCom(...)", 
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
    public boolean validarRango(BeanHorario h, BeanLeccion lec, int hora, int dia, int nroBloque){
        try{
            BeanMain[][] horario = h.getHorario();
            for(int i = hora ; i < hora + lec.getNroDuracion(); i++){  
                if(horario[i][dia] != null || 
                   !validarCruce(lec, dia, i, 0, 1) || 
                   !validarCursoJunto(horario, lec, nroBloque, dia, i)){
                    return false;
                }
            }
            /////si paso significa que esos dias esas horas estan libres
            BeanMain main = converterLecMain(lec);
            for(int i = hora ; i < hora + lec.getNroDuracion(); i++){
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
    
    public boolean validarCursoJunto(BeanMain[][] horario, BeanLeccion lec, int nroBloque, int dia, int i){
        try{
            boolean valida = true;
            if( (i - 1) > 0 && (i + 1) < nroBloque){
                int nidCurso = lec.getCurso().getNidCurso();
                if(validarCursoJunto_aux(horario, nidCurso, dia, i-1) || validarCursoJunto_aux(horario, nidCurso, dia, i+1) ){
                    valida = false;
                }
            }
            return valida;
        }catch(Exception e){
            ln_T_SFLoggerLocal.registrarLogErroresSistema(beanUsuario.getNidUsuario(), "LOG", CLASE, 
                                                          "validarCursoJunto(...)", 
                                                          "Error al validar curso junto al mismo curso", 
                                                          Utils.getStack(e));
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean validarCursoJunto_aux(BeanMain[][] horario, int nidCurso, int dia, int i){
        if(horario[i][dia] != null && horario[i][dia].getNidCurso() == nidCurso){
            return true;
        }
        return false;
    }
    
    /**
     * Metodo que se utiliza para validar cruze del profesor con otra sede. Tipo 1 validacion simple, 2 para mostrar los cruces
     * @param tipo
     * @param dia
     * @param leccion
     * @param dni
     * @return
     */
    public boolean validarCruce(BeanLeccion lec, int dia, int leccion, int nidMain, int tipo){   
        boolean valida = true;
        if(!tipoVistaAula()){
            return valida;
        }
        Time inicio = getHoraByLeccion(1, leccion);
        Time fin = getHoraByLeccion(2, leccion);
        List<BeanMain> listBean = ln_C_SFMainRemote.CruceLecionByProfesor(lec.getCodigoDniAula(), 
                                                                          dia, 
                                                                          inicio, 
                                                                          fin, 
                                                                          nidMain); 
        List<BeanRestriccionHorario> lstRHP = ln_C_SFRestriccionHorarioRemote.countCruceRestriccionHorario(lec.getCodigoDniAula(), 
                                                                                                           dia, 
                                                                                                           inicio, 
                                                                                                           fin);
        String msj = "";
        if(listBean.size() > 0){
            msj = "Disponibilidad del profesor "+listBean.get(0).getNombreProfesor()+" ocupada en el aula "+listBean.get(0).getNombreAula();
            validarCruceMsj(lec, tipo, msj);
            valida = false;
        }
        if(lstRHP.size() > 0){
            Format formatter = new SimpleDateFormat("hh:mm");
            msj = "Se restringio la hora de " + formatter.format(lstRHP.get(0).getHora_ini())+ " a "+ formatter.format(lstRHP.get(0).getHora_fin()) 
                  + " de los dias " + getNomDia(dia)+ " al "  + (tipoVistaAula() ? "Profesor" : "Aula") +" "+ (tipoVistaAula() ? lec.getProfesor().getNombreCompleto() : lec.getAula().getDescripcionAula());
            validarCruceMsj(lec, tipo, msj);
            valida = false;
        }
        return valida;
    }
    
    public void validarCruceMsj(BeanLeccion lec, int tipo, String msj){
        if(tipo == 1){
            if(lec.getLstErrores() == null){
                lec.setLstErrores(new ArrayList());
            }
            lec.getLstErrores().add(msj);
        }else{
            Utils.mostrarMensaje(ctx, msj, null, 2);   
        }        
    }
    
    public void eliminarLeccionesCom(BeanHorario h, BeanLeccion leccion, List<BeanDia> lstDia, int dia,int cantidad){
        h.setHorasLibres(h.getHorasLibres() - cantidad);
        modicarHorasBeanDias(lstDia.get(0).getNDia(), cantidad, h);
        eliminarDias_aux(lstDia, dia);
        leccion.setNroHoras(leccion.getNroHoras() - 1); 
    }
    
    /**
     * Metodo que guarda el horario generado
     * @author david
     */
    public void guardarGenerarHorario(BeanHorario h, Time ini[],  Time fin[]){
        try{
            BeanMain horario[][] = h.getHorario();          
            for(int i = 0 ; i < sessionbGestionarHorarios.getNroBloque(); i++){
                for(int j = 0; j < 5 ; j++){                   
                    if(horario[i][j] != null && 
                       horario[i][j].getEstado() != null && 
                       horario[i][j].getEstado().compareTo("1") == 0){
                        gestionarMain_aux(1, horario[i][j], j, i, ini, fin);
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
     */
    public void gestionarMain_aux(int evento, BeanMain main, int dia, int leccion, Time ini[],  Time fin[]){
        try{
            if(evento == 1 || evento == 2){                
                BeanLeccion lec = new BeanLeccion();
                lec.setCodigoDniAula(tipoVistaAula() ? (main.getNidAula()+"") : main.getDniProfesor());
                boolean valida = (evento == 1 ? true : validarCruce(lec, dia, leccion, main.getNidMain(), 2));                
                if(valida){
                    ln_T_SFMainRemote.gestionarMain_LN(evento, 
                                                       main.getNidMain(),
                                                       main.getDniProfesor(), 
                                                       main.getNidAula(), 
                                                       main.getNidCurso(), 
                                                       dia, 
                                                       main.getNidLecc(),
                                                       ini[leccion], 
                                                       fin[leccion]);  
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
    
    public void quitarDiasIngresados(BeanHorario h, List<BeanDia> lst, BeanLeccion leccion, int maxBloque, int nroBloque){
        try{
            BeanMain horario[][] = h.getHorario();
            List<BeanDia> lstDia = h.getLstDias();
            for(BeanDia dia : lstDia){
                int d = dia.getNDia();
                if(dia.getHoras() != maxBloque*5){
                    int cont = validarHorasMaximoPorDia(horario, nroBloque, d, leccion.getCurso().getNidCurso());
                    if(cont == maxBloque || (leccion.getNroDuracion() == maxBloque && cont > 0) ){
                        eliminarDias_aux(lst, d);
                    }                    
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
    
    /**
     * Metodo que valida las horas dependiendo de los paramtros dia y curso
     * @param horario
     * @param dia
     * @param nidCurso
     * @return
     */
    public int validarHorasMaximoPorDia(BeanMain horario[][], 
                                        int nroBloque,
                                        int dia, 
                                        int nidCurso){   
        int cont = 0;
        for(int i = 0 ; i < nroBloque; i++){
            if(horario[i][dia] != null && horario[i][dia].getNidCurso() == nidCurso){                
                cont++;
            }
        }
        return cont;
    }
    
    public BeanHorario getHorarioByCodigo(String codigo){
        for(BeanHorario h : sessionbGestionarHorarios.getListaHorario()){
            if(h.getCodigo().compareTo(codigo) == 0){
                return h;
            }
        }
        return null;
    }
    
    public void eliminarDias_aux(List<BeanDia> lstDia, int dia){
        for(BeanDia bean : lstDia){
            if(bean.getNDia() == dia){
                lstDia.remove(bean);
                break;
            }
        }
    }
    
    /**
     * Ordena el vector Dias segun las horas disponibles de mayor a menor
     * @return
     */
    public List<BeanDia> ordenarLstDiasByHoras(BeanHorario h){
        List<BeanDia> lst_aux = new ArrayList<>(h.getLstDias());
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
    
    public List<Integer> randomHoras(){
        List<Integer> horas = new ArrayList<>(sessionbGestionarHorarios.getHorasRandom());
        Random rndm = new Random();
        Collections.shuffle(horas, rndm);
        return horas;
    }
    
    public List<Integer> randomHorasDuracion(int duracion, int nroBloque){     
        List<Integer> horas = new ArrayList();
        for(int i = 0; i < nroBloque ; i++){
            if( (duracion + i) > nroBloque){
                break;
            }
            horas.add(i);
        }
        Random rndm = new Random();
        Collections.shuffle(horas, rndm);
        return horas;
    }
    
    public void horasRandom(int maxBloque, int nroBloque){
        List<Integer> horas = new ArrayList();
        for(int i = 0 ; i < nroBloque; i = i + maxBloque ){
            horas.add(i);
        }
        sessionbGestionarHorarios.setHorasRandom(horas);
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
    
    ///// 1 hora inicio 2 hora fin
    public Time getHoraByLeccion(int tipo, int leccion){        
        if(tipo == 1){
            return sessionbGestionarHorarios.getHoras()[leccion];
        }else if(tipo == 2){
            return sessionbGestionarHorarios.getHoras_fin()[leccion];
        } 
        return null;
    }
    
    public String getNomDia(int dia){
        return sessionbGestionarHorarios.getDia(dia);
    }
    
    public BeanMain converterLecMain(BeanLeccion lec){
        BeanMain main = new BeanMain();
        main.setNidCurso(lec.getCurso().getNidCurso());
        main.setDniProfesor(lec.getProfesor().getDniProfesor());
        main.setNidAula(lec.getAula().getNidAula());
        main.setNidLecc(lec.getNidLecc());
        main.setEstado("1");
        return main;
    }
    
    public BeanMain converterLecMain_aux(BeanLeccion lec){
        BeanMain main = new BeanMain();
        main.setNidCurso(lec.getCurso().getNidCurso());
        main.setNombreCurso(lec.getCurso().getDescripcionCurso());
        main.setDniProfesor(lec.getProfesor().getDniProfesor());
        main.setNombreProfesor(lec.getProfesor().getNombreCompleto());
        main.setNidAula(lec.getAula().getNidAula());
        main.setNombreAula(lec.getAula().getDescripcionAula());
        main.setNidLecc(lec.getNidLecc());
        main.setEstado("1");
        return main;
    }
    
    public void cerrarPopLeccion(PopupCanceledEvent popupCanceledEvent) {
        if(sessionbGestionarHorarios.isValidarEliminacionLecciones()){            
            sessionbGestionarHorarios.setValidarEliminacionLecciones(false);
            Utils.addTarget(t1);            
        }
        generarLeccionesTotal();
        Utils.addTarget(pb1);
    }

    
    /////////////////////// FIN GENERAR HORARIO //////////////////////////////////////////////////////
    

    ///////////////////////// VALUE CHANGE LISTENER ///////////////////////////////////////////

    public void changeListenerSede(ValueChangeEvent valueChangeEvent) {
        if (valueChangeEvent.getNewValue() != null) {            
            chSede.setValue(valueChangeEvent.getNewValue());
            sessionbGestionarHorarios.setListaNivelChoice(Utils.llenarCombo(ln_C_SFNivelRemote.getAllNivelesBySedes(chSede.getValue().toString())));
            sessionbGestionarHorarios.setDisableChoiceNivel(true);
            sessionbGestionarHorarios.setNomSede(Utils.getChoiceLabel(valueChangeEvent));
            Utils.addTarget(chNivel);
        }
    }
    
    public void changeListenerNivel(ValueChangeEvent valueChangeEvent) {
        if (valueChangeEvent.getNewValue() != null) {
            sessionbGestionarHorarios.setNomNivel(Utils.getChoiceLabel(valueChangeEvent));
        }
    }
    
    public void changeTipoVista(ValueChangeEvent valueChangeEvent) {
        if (valueChangeEvent.getNewValue() != null) {
            sessionbGestionarHorarios.setNidTipoVista(valueChangeEvent.getNewValue().toString());
            sessionbGestionarHorarios.setBooleanTipoVista(valueChangeEvent.getNewValue().toString().compareTo("1") == 0);
            cargarDatosVista();
            cargarDatosHorarios();
            Utils.addTarget(pgl1);
        }
    }

    public void changeListenerArea(ValueChangeEvent valueChangeEvent) {
        if (valueChangeEvent.getNewValue() != null) {
            sessionbGestionarHorarios.setListaCursoChoice(Utils.llenarCombo(
                ln_C_SFUtilsRemote.getCursosByArea_LN(Integer.parseInt(valueChangeEvent.getNewValue().toString()))));
            sessionbGestionarHorarios.setNidCurso(null);
            if (chCurso != null){
                Utils.addTarget(chCurso);
            }
            if (chCurso2 != null){
                Utils.addTarget(chCurso2);
            }
        }
    }
    
    public void changeListenerCurso(ValueChangeEvent valueChangeEvent) {
        if (valueChangeEvent.getNewValue() != null) {
            BeanCurso curso = new BeanCurso();
            curso.setNidCurso(Utils.transforString(valueChangeEvent.getNewValue().toString()));
            curso.setDescripcionCurso(Utils.getChoiceLabel(valueChangeEvent));
            sessionbGestionarHorarios.setCurso(curso);
            if(t2 != null && sessionbGestionarHorarios.getCurso() != null && sessionbGestionarHorarios.getDni() != null && sessionbGestionarHorarios.getAula() != null){
                Utils.addTarget(t2);
            }
        }
    }
    
    public void changeListenerProfesorAula(ValueChangeEvent vce) {
        if (vce.getNewValue() != null) {
            String codigo = vce.getNewValue().toString();
            sessionbGestionarHorarios.getLeccion().setCodigoDniAula(codigo);
            if(tipoVistaAula()){
                BeanProfesor dni = new BeanProfesor();
                dni.setDniProfesor(codigo);
                dni.setNombreCompleto(Utils.getChoiceLabel(vce));
                sessionbGestionarHorarios.setDni(dni);
            }else{
                BeanAula aula = new BeanAula();
                aula.setNidAula(Utils.transforString(codigo));
                aula.setDescripcionAula(Utils.getChoiceLabel(vce));
                sessionbGestionarHorarios.setAula(aula);
            }
            if(t2 != null && sessionbGestionarHorarios.getCurso() != null && sessionbGestionarHorarios.getDni() != null && sessionbGestionarHorarios.getAula() != null){
                Utils.addTarget(t2);
            }
        }
    }
    
    public void changeListenerCantidadH(ValueChangeEvent vce) {
        if (vce.getNewValue() != null) {
            Utils.putSession("nroCantidad", vce.getNewValue());
        }
    }

    public void changeListenerDuracionH(ValueChangeEvent vce) {
        if (vce.getNewValue() != null) {
            Utils.putSession("nroDuracion", vce.getNewValue());
        }
    }
    
    ///////////////////////////////////// AGREGAR PROFESOR ///////////////////////////////////////////////
    
    public void agregarProfesor(ActionEvent actionEvent) {
        buscarProfesor_aux();
        Utils.showPopUpMIDDLE(popAProf);
    }
    
    public void buscarProfesorAgregar(ActionEvent actionEvent) {
        agregarProfesor(actionEvent);
    }

    public void LimpiarProfesorAgregar(ActionEvent actionEvent) {
        sessionbGestionarHorarios.setProfesor(new BeanProfesor());
        buscarProfesor_aux();
        Utils.addTarget(pfl1);
    }
    
    public void buscarProfesor_aux(){
        List<String> lst_dni = new ArrayList();
        for (Object o : sessionbGestionarHorarios.getListaItems()) {
            SelectItem si = (SelectItem) o;
            lst_dni.add(si.getValue().toString());
        }
        List<BeanProfesor> lstProfesor = new ArrayList();
        lstProfesor = ln_C_SFProfesorRemote.getProfesoresDistintoLista(lst_dni, sessionbGestionarHorarios.getProfesor());
        sessionbGestionarHorarios.setListaProfesor(lstProfesor);
    }
    
    public void checkAgregarProfesor(ActionEvent actionEvent) {
        SelectItem item = new SelectItem();
        BeanProfesor pro = sessionbGestionarHorarios.getProfesor_aux();
        item.setValue(pro.getDniProfesor());
        item.setLabel(pro.getApellidos()+" "+pro.getNombres());
        sessionbGestionarHorarios.getListaItems().add(item);
        sessionbGestionarHorarios.getListaProfesor().remove(pro);
        cargarDatosHorarios();
        Utils.addTargetMany(t3, t1);
    }
 
    public void cambiarColor(ValueChangeEvent valueChangeEvent) {
        Utils.addTarget(pgl1);
    }
    ///////////////////////////////////////AGREGAR CURSO xxx///////////////////////////////////////////////////
    public void cambiarEstado(ActionEvent actionEvent){
        sessionbGestionarHorarios.setRenderAgregarCurso(true);
        Utils.addTargetMany(pfl6, t2, pfl8);
    }
    
    public void cancelarAgregarCurso(ActionEvent actionEvent){
        sessionbGestionarHorarios.setRenderAgregarCurso(false);
        Utils.addTargetMany(pfl6, t2, pfl8);
    }
    
    public void AgregarCurso(ActionEvent actionEvent){
        int nDia = (Integer) actionEvent.getComponent().getAttributes().get("nDia");
        int nLeccion = (Integer) actionEvent.getComponent().getAttributes().get("nLec");
        AgregarCurso_aux(nDia, nLeccion);        
    }
    
    public void AgregarCurso_aux(int dia, int leccion) {
        BeanLeccion lec = sessionbGestionarHorarios.getLeccion();
        int horasLibres = Utils.transforString(Utils.getSession("horasLibres").toString());
        int resta = horasLibres - 1;
        if(resta >= 0 ){                        
            lec.setCurso(sessionbGestionarHorarios.getCurso());
            lec.setAula(sessionbGestionarHorarios.getAula());
            lec.setProfesor(sessionbGestionarHorarios.getDni());  
            lec.setNroDuracion(1);
            lec.setNroHoras(1);
            lec.setCodigoDniAula(!tipoVistaAula() ? (lec.getAula().getNidAula()+"") : lec.getProfesor().getDniProfesor());
            boolean vista = tipoVistaAula();
            lec.setTitulo(vista ? lec.getProfesor().getNombreCompleto() : lec.getAula().getDescripcionAula());
            sessionbGestionarHorarios.setLeccion(pasarDatosLeccion(lec));
            ////////////////////// validando si la leccion esta ocupada o no //////////////////////////    
            BeanMain[][] m = sessionbGestionarHorarios.getHorario();
            if(m[leccion][dia] != null || !validarCruce(lec, dia, leccion, 0, 2)){              
                return;
            }
            sumarHorasLibres(resta);
            Utils.putSession("horasLibres", resta);
            sessionbGestionarHorarios.getHorario()[leccion][dia] = converterLecMain_aux(lec);
            sessionbGestionarHorarios.setCont_curso(sessionbGestionarHorarios.getCont_curso() + 1);
            Utils.addTarget(t2);
        }else{
            Utils.mostrarMensaje(ctx, "Se sobre paso el numero de horas Libre", null, 2);  
        }        
    }
    
    public void EliminarCurso(ActionEvent actionEvent){
        int nDia = (Integer) actionEvent.getComponent().getAttributes().get("nDia");
        int nLeccion = (Integer) actionEvent.getComponent().getAttributes().get("nLec");
        sessionbGestionarHorarios.getHorario()[nLeccion][nDia] = null;
        sessionbGestionarHorarios.setCont_curso(sessionbGestionarHorarios.getCont_curso() - 1);       
        ////////////////////////////////////////////////////////////////////////
        int horasLibres = Utils.transforString(Utils.getSession("horasLibres").toString());
        int suma = horasLibres + 1;
        sumarHorasLibres(suma);
        Utils.putSession("horasLibres", suma);
        ////////////////////////////////////////
        Utils.addTarget(t2);
    }
    
    public void GuardarCurso(ActionEvent actionEvent){
        if(sessionbGestionarHorarios.getCont_curso() > 0){
            restructurarMain();
        }
    }
    
    public void restructurarMain(){
        if(!existeenHorarioTotal(sessionbGestionarHorarios.horarioSelect())){
            sessionbGestionarHorarios.setLstLecciones(new ArrayList());
        }
        //// calculando la nueva lista de lecciones
        List<BeanLeccion> list = new ArrayList();
        BeanMain[][] m = sessionbGestionarHorarios.getHorario();
        restructurarLecciones(m, list);
        
        List<BeanLeccion> l_old = sessionbGestionarHorarios.getLstLecciones();
        //// eliminamos los main que fueron creados para volver a generarlos
        ln_T_SFMainRemote.eliminarMainByLecc(0, 0); //x si algo salio mal
        for(BeanLeccion l : l_old){
            ln_T_SFMainRemote.eliminarMainByLecc(l.getNidLecc(), l.getNroDuracion()*l.getNroHoras_aux());
        }            
        ////comparamos las lecciones iguales
        for(int i = 0; i < list.size(); i++){
            for(BeanLeccion l : l_old){
                if(list.get(i).getAula().getNidAula().compareTo(l.getAula().getNidAula()) == 0 &&
                   list.get(i).getCurso().getNidCurso().compareTo(l.getCurso().getNidCurso()) == 0 &&
                   list.get(i).getProfesor().getDniProfesor().compareTo(l.getProfesor().getDniProfesor()) == 0 &&
                   list.get(i).getNroDuracion() == l.getNroDuracion() && !list.get(i).isUpdate()){
                    list.get(i).setNroHoras(l.getNroHoras());
                    list.get(i).setNidLecc(l.getNidLecc());
                    list.get(i).setUpdate(true);
                    l_old.remove(l);
                    break;
                }
            }
        }
        
        //// eliminamos de la lista las lecciones que no han sido generadas            
        List<BeanLeccion> l_old_aux = new ArrayList();
        for(BeanLeccion l : l_old){
            if(l.getNroHoras() > 0 && l.getNroHoras_aux() == 0){
                l_old_aux.add(l); //no lo tocamos
            }else if(l.getNroHoras_aux() > 0 && l.getNroHoras() > 0){
                l.setUpdate(true);
                l.setNroHoras_aux(0);
            }
        }
        int size = l_old_aux.size();
        for(int i = 0; i < size; i++){
            l_old.remove(l_old_aux.get(i));
        }
        ///--------------------------------------------------------------
        for(BeanLeccion l : l_old){
            if(l.isUpdate()){
                ln_T_SFLeccionRemote.gestionarLeccion(true, l);                                        
            }else{
                ln_T_SFLeccionRemote.removeLeccion(l.getNidLecc());
            }                
        } 
        ///--------------------------------------------------------------
        for(int i=0; i < list.size(); i++){
            BeanLeccion l = ln_T_SFLeccionRemote.gestionarLeccion(list.get(i).isUpdate(), list.get(i));
            list.get(i).setNidLecc(l.getNidLecc());
        }
        for(int i = 0 ; i < sessionbGestionarHorarios.getNroBloque(); i++){
            for(int j = 0; j < 5 ; j++){                   
                if(m[i][j] != null){
                    for(BeanLeccion l : list){
                        if(m[i][j].getNidLeccRef() == l.getNidLeccRef()){
                            m[i][j].setNidLecc(l.getNidLecc());
                            m[i][j].setEstado("1");
                        }
                    }
                }
            }
        }
        Time ini[] = sessionbGestionarHorarios.getHoras();
        Time fin[] = sessionbGestionarHorarios.getHoras_fin();
        BeanHorario h = new BeanHorario();
        h.setHorario(m);
        guardarGenerarHorario(h, ini, fin);             
        cargarDatosHorarios();   
        Utils.addTarget(t1);
        BeanHorario horario = sessionbGestionarHorarios.horarioSelect();
        cargarDatosHorario(horario);
        Utils.addTargetMany(t2, treeLec);
    }
    
    /////////////////////////////// yyy RECALCULAR LAS LECCIONES -- ESTO BORRA LO QUE NO HAY :( ----------------------
    
    public int sumar(int c, BeanMain main[][], int d) {
        if (c + 1 >= sessionbGestionarHorarios.getNroBloque()) {
            return 0;
        } else {
            if (main[c+1][d] != null && 
                main[c][d].getNidAula() == main[c+1][d].getNidAula() &&
                main[c][d].getNidCurso() == main[c+1][d].getNidCurso() &&
                main[c][d].getDniProfesor() == main[c+1][d].getDniProfesor()) {
                return 1 + sumar(c + 1, main, d);
            } else {
                return 0;
            }
        }
    }
    
    public int buscarLeccion(List<BeanLeccion> list, BeanMain m, int duracion) {
        boolean valida = true;
        int ref = -1;
        for (BeanLeccion lec : list) {
            if (verificarLeccionIgual_aux(lec, m) && lec.getNroDuracion() == duracion) {
                lec.setNroHoras_aux(lec.getNroHoras_aux() + 1);
                ref = lec.getNidLeccRef();
                valida = false;
                break;
            }
        }
        if (valida) {
            BeanLeccion lec = new BeanLeccion();
            lec.setNidLeccRef(list.size() + 1);
            BeanCurso c = new BeanCurso();
            c.setNidCurso(m.getNidCurso());
            lec.setCurso(c);
            BeanAula a = new BeanAula();
            a.setNidAula(m.getNidAula());
            lec.setAula(a);
            BeanProfesor p = new BeanProfesor();
            p.setDniProfesor(m.getDniProfesor());
            lec.setProfesor(p);
            lec.setNroDuracion(duracion);
            lec.setNroHoras_aux(1);
            list.add(lec);
            ref = lec.getNidLeccRef();
        }
        return ref;
    }
    
    public boolean verificarLeccionIgual_aux(BeanLeccion l, BeanMain m){
        if(l.getCurso().getNidCurso() == m.getNidCurso() && (l.getProfesor().getDniProfesor().compareTo(m.getDniProfesor()) == 0)
            && (l.getAula().getNidAula() == m.getNidAula())){ 
            return true;
        }
        return false;
    }
    
    public void restructurarLecciones(BeanMain main[][], List<BeanLeccion> list) {
        for(int i = 0; i < 5 ; i++){
            for (int j = 0; j < sessionbGestionarHorarios.getNroBloque(); j++) {
                if(main[j][i] != null){
                    int suma = sumar(j, main, i);
                    int ref = buscarLeccion(list, main[j][i], suma + 1);
                    if(ref != -1){
                        for (int k = j; k < (j + suma + 1); k++) {                            
                            main[k][i].setNidLeccRef(ref);
                        }                        
                    }
                    j = j + suma;
                }                
            }
        }
    }
    
    ///////////////////ZZZ  ELIMINAR CURSO Y MODIFICAR PROFESOR //////////////////////////////////////////////////////////
    
    public void obtenerposicion(ActionEvent actionEvent) {
        int nDia = (Integer) actionEvent.getComponent().getAttributes().get("nDia");
        int nLeccion = (Integer) actionEvent.getComponent().getAttributes().get("nLec");
        sessionbGestionarHorarios.setLecc(nLeccion);
        sessionbGestionarHorarios.setNDia(nDia);
    }
    
    
    /**
     * Metodo que carga los datos de la leccion selecionada en session y abre el popEM(ELIMINAR MODIFICAR)
     * @param actionEvent
     */
    public void eliminarLecciones(ActionEvent actionEvent) {
        int nDia = sessionbGestionarHorarios.getNDia();
        int nLeccion = sessionbGestionarHorarios.getLecc();
        modificarEliminarLecciones_aux(nDia, nLeccion, 1);
        sessionbGestionarHorarios.setEventoEliminarModificar(3);
        sessionbGestionarHorarios.setRenderEliminarModificar(false);
    }       
    
    /**
     * Metodo que carga los datos de la leccion selecionada en session y abre el popEM(MODIFICAR)
     * @param actionEvent
     */
    public void modificarLecciones(ActionEvent actionEvent) {        
        int nDia = sessionbGestionarHorarios.getNDia();
        int nLeccion = sessionbGestionarHorarios.getLecc();
        modificarEliminarLecciones_aux(nDia, nLeccion, 1);
        sessionbGestionarHorarios.setEventoEliminarModificar(2);
        sessionbGestionarHorarios.setRenderEliminarModificar(true);
    }
    
    public void modificarEliminarLecciones_aux(int dia, int lec, int tipo){
        String codigo = tipo == 1 ? (sessionbGestionarHorarios.getHorario()[lec][dia].getNidCurso()+"") : (sessionbGestionarHorarios.getHorario()[lec][dia].getDniProfesor());
        sessionbGestionarHorarios.setNCurso(sessionbGestionarHorarios.getHorario()[lec][dia].getNidCurso());
        String pre = tipo == 1 ? "Eliminar leccion(s)" : "Modificar Profesor";
        sessionbGestionarHorarios.setTituloEliminarModificar(pre + " del Curso "+sessionbGestionarHorarios.getHorario()[lec][dia].getNombreCurso());
        encontrarDiaLecccion(sessionbGestionarHorarios.getHorario(), codigo, dia, tipo);
        Utils.showPopUpMIDDLE(popEM);
    }
    
    /**
     * Metodo que busca los dias que se dicta una leccion
     * tipo 1 = curso and tipo 2 = profesor
     */
    public void encontrarDiaLecccion(BeanMain horario[][], String codigo, int nDia, int tipo){
        List<BeanCombo> lista = new ArrayList();
        int cont = 0;
        llenarLstComboString(9, "Lecci\u00f3n selecionada", lista);
        for(int j = 0; j < 5; j++){
            for(int i = 0; i < sessionbGestionarHorarios.getNroBloque(); i++){
                if(horario[i][j] != null && 
                  (tipo == 1 ? ((horario[i][j].getNidCurso()+"").compareTo(codigo) == 0) : (horario[i][j].getDniProfesor().compareTo(codigo) == 0))){
                    if(nDia == j){
                        cont++;
                    }else{
                        llenarLstComboString(j, sessionbGestionarHorarios.getDia(j), lista);
                        i = sessionbGestionarHorarios.getNroBloque();
                    }                        
                }
            }
            if(cont > 1){
                llenarLstComboString(nDia, sessionbGestionarHorarios.getDia(j), lista);
                cont = 0;
            }
        }
        sessionbGestionarHorarios.setLstSelecDias(Utils.llenarCombo(lista));
        ///seleciono por default el dia que se seleciono
        List lst = new ArrayList();
        lst.add(9+"");
        sessionbGestionarHorarios.setLstDiasSelec(lst);
    }
    
    public void llenarLstComboString(int id, String descripcion, List<BeanCombo> lista){
        BeanCombo combo = new BeanCombo();
        combo.setId(id);
        combo.setDescripcion(descripcion);
        lista.add(combo);
    }    
    
    public void modificarEliminarLecciones(DialogEvent dialogEvent) {
        DialogEvent.Outcome outcome = dialogEvent.getOutcome();
        if(outcome == DialogEvent.Outcome.ok){            
            eliminarDiasSelecionados(sessionbGestionarHorarios.getEventoEliminarModificar());
        }
    }
    
    /**
     * Elimina los dias seleccionados en la vista
     * @param evento
     */
    public void eliminarDiasSelecionados(int evento){
        try{
            BeanMain main[][] = sessionbGestionarHorarios.getHorario(); 
            Time ini[] = sessionbGestionarHorarios.getHoras();
            Time fin[] = sessionbGestionarHorarios.getHoras_fin();
            for(Object o : sessionbGestionarHorarios.getLstDiasSelec()){
                int dia = Integer.parseInt(o.toString());
                if(dia == 9){
                    int lec = sessionbGestionarHorarios.getLecc();
                    int ndia = sessionbGestionarHorarios.getNDia();
                    gestionarMain_aux(evento, main[lec][ndia], ndia, lec, ini, fin);
                    main[lec][ndia] = null;
                }else{
                    for(int i = 0; i < sessionbGestionarHorarios.getNroBloque(); i++){
                        if(main[i][dia] != null && main[i][dia].getNidMain() > 0 &&
                           main[i][dia].getNidCurso() == sessionbGestionarHorarios.getNCurso()){
                            gestionarMain_aux(evento, main[i][dia], dia, i, ini, fin);
                        }
                    }
                }
            }  
            BeanHorario horario = sessionbGestionarHorarios.horarioSelect();
            cargarDatosHorario(horario);
            restructurarMain();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    
    
    
    /////////////////////////////////////// EXPORTAR DATOS A WORD ////////////////////////////////////////
            
    public void exportDataOne(FacesContext facesContext, OutputStream outputStream) throws IOException,WriteException{
        try{
            String nom_dias[] = sessionbGestionarHorarios.getVectorDias();
            List<String> rango_horas = sessionbGestionarHorarios.getRango_horas();
            XWPFDocument document = new XWPFDocument(new FileInputStream(rutaDocument() + Utils.rutaLocal("doc")+"plantilla.docx"));
            List<BeanConfiguracionHorario> lstConfHorario = sessionbGestionarHorarios.getLstConfHorario();
            Time horas_fin[] = sessionbGestionarHorarios.getHoras_fin();
            formatoExportar(sessionbGestionarHorarios.horarioSelect(), 
                            document, tipoVistaAula(), nom_dias, rango_horas, 
                            lstConfHorario, horas_fin,
                            sessionbGestionarHorarios.isBooleanColor(), 
                            sessionbGestionarHorarios.isBooleanTipoColor());
            document.write(outputStream);
            outputStream.flush();
            outputStream.close();
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void exportDataTwo(FacesContext facesContext, OutputStream outputStream) throws IOException,WriteException{
        try{
            String nom_dias[] = sessionbGestionarHorarios.getVectorDias();
            List<String> rango_horas = sessionbGestionarHorarios.getRango_horas();
            XWPFDocument document = new XWPFDocument(new FileInputStream(rutaDocument() + Utils.rutaLocal("doc")+"plantilla.docx"));
            List<BeanHorario> listaHorario = sessionbGestionarHorarios.getListaHorario();
            List ItemSelect = sessionbGestionarHorarios.getListaItemsSelect();
            List<BeanConfiguracionHorario> lstConfHorario = sessionbGestionarHorarios.getLstConfHorario();
            Time horas_fin[] = sessionbGestionarHorarios.getHoras_fin();
            
            if(ItemSelect != null){
                for(int i = 0; i < ItemSelect.size(); i++){
                    BeanHorario horario = getBeanHorarioByCodigo(listaHorario, ItemSelect.get(i).toString());
                    if(horario != null){
                        formatoExportar(horario, 
                                        document, tipoVistaAula(), nom_dias, rango_horas, 
                                        lstConfHorario, horas_fin,
                                        sessionbGestionarHorarios.isBooleanColor(), 
                                        sessionbGestionarHorarios.isBooleanTipoColor());
                        if(i < (ItemSelect.size() - 1)){
                            XWPFParagraph paragraph = document.createParagraph();
                            paragraph.setPageBreak(true);
                        }                    
                    }                
                }
            }
            document.write(outputStream);
            outputStream.flush();
            outputStream.close();
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }    
    
    public void formatoExportar(BeanHorario horario, XWPFDocument document, boolean vista, String nom_dias[], List<String> rango_horas, List<BeanConfiguracionHorario> lstConfHorario, Time horas_fin[], boolean color, boolean t_color){
        try{            
            crearParrafo(document, true, 13, "SEDE " + sessionbGestionarHorarios.getNomSedeNivel(), 1);
            crearParrafo(document, true, 12, (vista ? "Aula" : "Profesor") + ": " + horario.getTitulo(), 1);
            int cols[] = {1500,2500,2500,2500,2500,2500};
            int tamano = sessionbGestionarHorarios.getHoras().length;
            XWPFTable table = document.createTable();
            table.setInsideVBorder(XWPFTable.XWPFBorderType.DOUBLE, 4, 0, "000000");
            table.setInsideHBorder(XWPFTable.XWPFBorderType.DOUBLE, 4, 0, "000000");          
            ////////////////CREANDO PRIMERA FILA //////////////////////////////////            
            BeanMain h[][] = horario.getHorario();
            XWPFTableRow rowOne = table.getRow(0); 
            for(int i = 0; i < nom_dias.length + 1; i++){                  
                rowOne.getCell(i).getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(cols[i]));
                createParagraphCell(rowOne.getCell(i), (i != 0 ? nom_dias[i-1] : "-"), 1, true, "647687", "ffffff",10);
                if(i < nom_dias.length){
                    rowOne.createCell();
                }
            }
            ////////////////FIN CREANDO PRIMERA FILA //////////////////////////////////   
               
            ////////////////CREANDO OTRAS FILA ////////////////////////////////// 
            int cantRestr = lstConfHorario.size();
            int cont = 0;
            for(int i = 0; i < tamano; i++){      
                XWPFTableRow row = table.createRow();
                createParagraphCell(row.getCell(0), rango_horas.get(i), 1, true, "647687", "ffffff",10);                
                
                for(int j = 0; j < nom_dias.length; j++){                    
                    if(h[i][j] != null && h[i][j].getNidMain() > 0){
                        createParagraphCell_aux(row.getCell(j+1), 
                                                h[i][j].getNombreCurso() , 
                                                sessionbGestionarHorarios.descripcionProfSalon(horario.getPosicion(), i, j), 
                                                1, 
                                                true, 
                                                color ? (t_color ? h[i][j].getColor_prof() : h[i][j].getColor()) : "", 
                                                color ? "000000" : "",
                                                9);
                    }                   
                }
                
                ////// SI EXISTE RESTRICION ///////
                if(cantRestr != cont && horas_fin[i].equals(lstConfHorario.get(cont).getHora_inicio())){                    
                    XWPFTableRow row2 = table.createRow();
                    createParagraphCell(row2.getCell(0), rangoHorasRestr(lstConfHorario.get(cont)), 1, true, "647687", "ffffff",10);
                    for(int j = 0; j < nom_dias.length; j++){
                        createParagraphCell(row2.getCell(j+1), lstConfHorario.get(cont).getStmconfev().getDescripcion(), 
                                            1, true, "647687", "ffffff",10);               
                    }
                    cont++;
                }  
                ////// FIN SI EXISTE RESTRICION ///////
                
            }                    
            ////////////////FIN CREANDO OTRAS FILA //////////////////////////////////             
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void crearParrafo(XWPFDocument document, boolean Bold, int size, String texto, int alignment){
        XWPFParagraph paragraphOne = document.createParagraph();
        paragraphStyle(paragraphOne, alignment);
        XWPFRun paragraphRunOne = paragraphOne.createRun();
        XWPFRunStyle(paragraphRunOne, Bold, size, texto);
    }  
    
    public void createParagraphCell(XWPFTableCell celda, 
                                    String texto, 
                                    int alignment, 
                                    boolean Bold, 
                                    String colorCelda, 
                                    String colorLetra, 
                                    int tamanoL){
        celda.setVerticalAlignment(XWPFTableCell.XWPFVertAlign.CENTER);
        if(colorCelda.length() == 6){
            celda.setColor(colorCelda);
        }
        XWPFParagraph para = celda.getParagraphs().get(0);
        para.setSpacingAfter(5);
        paragraphStyle(para, alignment);
        XWPFRun run = para.createRun();
        if(colorLetra.length() == 6){
            run.setColor(colorLetra);
        }
        XWPFRunStyle(run, Bold, tamanoL, texto);
    }
    
    public void createParagraphCell_aux(XWPFTableCell celda, 
                                    String texto, 
                                    String texto2,
                                    int alignment, 
                                    boolean Bold, 
                                    String colorCelda, 
                                    String colorLetra, 
                                    int tamanoL){
        celda.setVerticalAlignment(XWPFTableCell.XWPFVertAlign.CENTER);
        if(colorCelda.length() == 6){
            celda.setColor(colorCelda);
        }
        XWPFParagraph para = celda.getParagraphs().get(0);
        para.setSpacingAfter(5);
        paragraphStyle(para, alignment);        
        XWPFRun run = para.createRun();
        if(colorLetra.length() == 6){
            run.setColor(colorLetra);
        }
        XWPFRunStyle(run, Bold, tamanoL, texto);
        
        XWPFParagraph para2 = celda.addParagraph();
        para2.setSpacingAfter(5);
        paragraphStyle(para2, alignment);
        XWPFRun run2 = para2.createRun();
        if(colorLetra.length() == 6){
            run2.setColor(colorLetra);
        }
        XWPFRunStyle(run2, Bold, tamanoL, texto2);
    }
    
    public void XWPFRunStyle(XWPFRun paragraph, 
                             boolean Bold, 
                             int size, 
                             String texto){
        if(texto != null){
            paragraph.setText(texto);            
        }
        if(size != 0){
            paragraph.setFontSize(size);
        }
        if(Bold){
            paragraph.setBold(Bold);
        }                
    }
    
    public void paragraphStyle(XWPFParagraph paragraph, int alignment){
        if(alignment == 1){
            paragraph.setAlignment(ParagraphAlignment.CENTER);
        }
    }
    
    public String rutaDocument(){
        ServletContext servletCtx = (ServletContext)ctx.getExternalContext().getContext();
        String imageDirPath = servletCtx.getRealPath("/");
        return imageDirPath;
    }
    
    public void selectColorIndividual(ValueChangeEvent vce) {
        if(vce != null){
            Utils.addTarget(pfl6);
        }
    }
    
    public void selectColorIndividual2(ValueChangeEvent vce) {
        if(vce != null){
            Utils.addTarget(pfl7);
        }
    }
    
    public void opcionExportarCancelar(ActionEvent actionEvent) {
        popCo.cancel();
    }
    
    public BeanHorario getBeanHorarioByCodigo(List<BeanHorario> listaHorario, String cod){
        for(BeanHorario h : listaHorario){
            if(h.getCodigo().compareTo(cod) == 0){
                return h;
            }
        }
        return null;
    }
    
    public void exportarConfig(ActionEvent actionEvent) {
        Utils.showPopUpMIDDLE(popCo);
    }
    
    public String rangoHorasRestr(BeanConfiguracionHorario b){
        Format formatter = new SimpleDateFormat("hh:mm");
        return formatter.format(b.getHora_inicio()) + " - " + formatter.format(b.getHora_fin());
    }
    
    
    ///// ALGUN DIA SE IMPLEMENTARA ESTO XD XD XD XD XD XD///////////////////
    public String detalleLeccion1(BeanMain main){
        String retorna = main.getNombreCurso();
        if(sessionbGestionarHorarios.getNidNivel_aux() == 3){
            if(!tipoVistaAula()){
                retorna = retorna.concat(" / " + main.getNombreAula() );
            }            
        }
        return retorna;
    }
   
    /////////////////////////////////////// FIN EXPORTAR DATOS A WORD ////////////////////////////////////////

    /////////////////////////////////////// CAMBIAR COLOR  ////////////////////////////////////////
    public void cambiarColor(ActionEvent actionEvent) {
        sessionbGestionarHorarios.cargarDatosColor();
        Utils.showPopUpMIDDLE(popColor);
    }
    
    public void confirmarCambioColor(DialogEvent dialogEvent) {
        DialogEvent.Outcome outcome = dialogEvent.getOutcome();
        if(outcome == DialogEvent.Outcome.ok){            
            String rgb = Integer.toHexString(sessionbGestionarHorarios.getColor().getRGB()).substring(2);
            ln_T_SFProfesorRemoto.grabarColorProfesor(sessionbGestionarHorarios.getCodigo_selc(), rgb);
            sessionbGestionarHorarios.getListaHorario().get(sessionbGestionarHorarios.getPosicionProfSalon()).setColor(rgb);            
            BeanMain horario[][] = sessionbGestionarHorarios.horarioSelect().getHorario();
            for(int i = 0 ; i < sessionbGestionarHorarios.getNroBloque(); i++){
                for(int j = 0; j < 5 ; j++){ 
                    BeanMain main = horario[i][j];
                    if(main != null && main.getNidMain() > 0){
                        main.setColor_prof(rgb);
                        horario[i][j] = main;
                    }
                }
            }
            sessionbGestionarHorarios.setHorario(horario);   
            Utils.addTarget(t1);
        }
    }
    /////////////////////////////////////// FIN CAMBIAR COLOR ////////////////////////////////////////
    
       
    ///////////////////////////////////////  OTROS METODOS //////////////////////////////////////////////////

    /**
     * Metodo para agregar tiempo a una fecha
     * @param inicio
     * @param agregar
     * @return
     */
    public Date sumaHoras(Calendar inicio, Time agregar) {
        inicio.add(Calendar.HOUR, agregar.getHours());
        inicio.add(Calendar.MINUTE, agregar.getMinutes());
        return inicio.getTime();
    }

    /**
     * Metodo que llena las horas fin de vada bloque de clases
     * @param duracion
     * @param h_inicio
     */
    public void llenarHorasFin(Time duracion, Time[] h_inicio) {
        Time[] horas_fin = new Time[sessionbGestionarHorarios.getNroBloque()];
        Calendar c = new GregorianCalendar();
        for (int i = 0; i < horas_fin.length; i++) {
            c.setTime(h_inicio[i]);
            horas_fin[i] = new Time(sumaHoras(c, duracion).getTime());
        }
        sessionbGestionarHorarios.setHoras_fin(horas_fin);
    }

    public void llenarRangoHoras() {
        Format formatter = new SimpleDateFormat("hh:mm");
        List<String> rango_horas = new ArrayList();
        Time horas[] = sessionbGestionarHorarios.getHoras();
        Time horas_fin[] = sessionbGestionarHorarios.getHoras_fin();
        for (int i = 0; i < horas.length; i++) {
            rango_horas.add(formatter.format(horas[i]) + " - " + formatter.format(horas_fin[i]));
        }
        sessionbGestionarHorarios.setRango_horas(rango_horas);
    }
    
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
        sessionbGestionarHorarios.setListaTipoVista(vista);
    }
    
    public void llenarTipoDuracion(){
        String vec[] = {"Uno", "Dos", "Tres", "Cuatro", "Cinco"};
        List dur = new ArrayList();
        for(int i = 0; i < vec.length; i++){
            if(i < sessionbGestionarHorarios.getMaxBloque()){
                SelectItem si = new SelectItem();
                si.setValue(new Integer(i+1));
                si.setLabel(vec[i]);
                dur.add(si);
            }            
        }
        sessionbGestionarHorarios.setListaDuracion(dur);
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
        sessionbGestionarHorarios.setVectorDias(dias);
    }
    
    /**
     * Llena una lista con el nro de bloques por dia para luego ser validado rapidamente
     * @author david
     * @param dias
     */
    public void llenarLstDias(BeanHorario new_horario, int nro_bloque){
        List<BeanDia> lstDias = new ArrayList();
        for(int i = 0 ; i < 5; i++){
            BeanDia dia = new BeanDia();
            dia.setNDia(i);
            dia.setHoras(nro_bloque);
            lstDias.add(dia);
        }
        new_horario.setLstDias(lstDias);      
    }

    /**
     * Modifica la lista dias al ingresar una leccion
     * @param ndia
     * @param hora
     */
    public void modicarHorasBeanDias(int ndia, int hora, BeanHorario new_horario){           
        for(BeanDia dia : new_horario.getLstDias()){
            if(dia.getNDia() == ndia){
                dia.setHoras(dia.getHoras() - hora);  
            }
        }
    }
    
    /**
     * Elimina los dias que ya estan ocupados
     * @author david
     */
    public void eliminarDiasOcupados(BeanHorario new_horario){
        try{
            List<BeanDia> lstDias = new_horario.getLstDias();
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
    
    public List<BeanMain> cargarleccionProfesorAula(String dato, boolean concidencia){
        if(tipoVistaAula()){
            return ln_C_SFMainRemote.getLstMainByAttr_LN_Aula(dato);
        }else{
            return ln_C_SFMainRemote.getLstMainByAttr_LN_Profesor(dato, 
                                                                  sessionbGestionarHorarios.getNidSede_aux(), 
                                                                  sessionbGestionarHorarios.getNidNivel_aux(), 
                                                                  concidencia);
        }        
    }
    
    //////////////////////////////// GET AND SET ///////////////////////////////////////////

    public void setSessionbGestionarHorarios(bSessionbGestionarHorarios sessionbGestionarHorarios) {
        this.sessionbGestionarHorarios = sessionbGestionarHorarios;
    }

    public bSessionbGestionarHorarios getSessionbGestionarHorarios() {
        return sessionbGestionarHorarios;
    }

    public void setSi1(UISelectItems si1) {
        this.si1 = si1;
    }

    public UISelectItems getSi1() {
        return si1;
    }

    public void setChSede(RichSelectOneChoice chSede) {
        this.chSede = chSede;
    }

    public RichSelectOneChoice getChSede() {
        return chSede;
    }

    public void setChNivel(RichSelectOneChoice chNivel) {
        this.chNivel = chNivel;
    }

    public RichSelectOneChoice getChNivel() {
        return chNivel;
    }

    public void setSi2(UISelectItems si2) {
        this.si2 = si2;
    }

    public UISelectItems getSi2() {
        return si2;
    }

    public void setPbHor(RichPanelBox pbHor) {
        this.pbHor = pbHor;
    }

    public RichPanelBox getPbHor() {
        return pbHor;
    }

    public void setPgl1(RichPanelGroupLayout pgl1) {
        this.pgl1 = pgl1;
    }

    public RichPanelGroupLayout getPgl1() {
        return pgl1;
    }

    public void setSoc1(RichSelectOneChoice soc1) {
        this.soc1 = soc1;
    }

    public RichSelectOneChoice getSoc1() {
        return soc1;
    }

    public void setSmc1(RichSelectManyChoice smc1) {
        this.smc1 = smc1;
    }

    public RichSelectManyChoice getSmc1() {
        return smc1;
    }

    public void setPopDis(RichPopup popDis) {
        this.popDis = popDis;
    }

    public RichPopup getPopDis() {
        return popDis;
    }

    public void setTdis(RichTable tdis) {
        this.tdis = tdis;
    }

    public RichTable getTdis() {
        return tdis;
    }

    public void setT1(RichTable t1) {
        this.t1 = t1;
    }

    public RichTable getT1() {
        return t1;
    }

    public void setPopVer(RichPopup popVer) {
        this.popVer = popVer;
    }

    public RichPopup getPopVer() {
        return popVer;
    }

    public void setT2(RichTable t2) {
        this.t2 = t2;
    }

    public RichTable getT2() {
        return t2;
    }

    public void setBagrPr(RichButton bagrPr) {
        this.bagrPr = bagrPr;
    }

    public RichButton getBagrPr() {
        return bagrPr;
    }

    public void setPopAProf(RichPopup popAProf) {
        this.popAProf = popAProf;
    }

    public RichPopup getPopAProf() {
        return popAProf;
    }

    public void setD3(RichDialog d3) {
        this.d3 = d3;
    }

    public RichDialog getD3() {
        return d3;
    }

    public void setPfl1(RichPanelFormLayout pfl1) {
        this.pfl1 = pfl1;
    }

    public RichPanelFormLayout getPfl1() {
        return pfl1;
    }

    public void setT3(RichTable t3) {
        this.t3 = t3;
    }

    public RichTable getT3() {
        return t3;
    }

    public void setPopAlec(RichPopup popAlec) {
        this.popAlec = popAlec;
    }

    public RichPopup getPopAlec() {
        return popAlec;
    }

    public void setD4(RichDialog d4) {
        this.d4 = d4;
    }

    public RichDialog getD4() {
        return d4;
    }

    public void setChAulaProfesor(RichSelectOneChoice chAulaProfesor) {
        this.chAulaProfesor = chAulaProfesor;
    }

    public RichSelectOneChoice getChAulaProfesor() {
        return chAulaProfesor;
    }

    public void setChCurso(RichSelectOneChoice chCurso) {
        this.chCurso = chCurso;
    }

    public RichSelectOneChoice getChCurso() {
        return chCurso;
    }
    
    public void setChArea(RichSelectOneChoice chArea) {
        this.chArea = chArea;
    }

    public RichSelectOneChoice getChArea() {
        return chArea;
    }

    public void setCant(RichInputNumberSpinbox cant) {
        this.cant = cant;
    }

    public RichInputNumberSpinbox getCant() {
        return cant;
    }

    public void setChDuracion(RichSelectOneChoice chDuracion) {
        this.chDuracion = chDuracion;
    }

    public RichSelectOneChoice getChDuracion() {
        return chDuracion;
    }

    public void setT4(RichTable t4) {
        this.t4 = t4;
    }

    public RichTable getT4() {
        return t4;
    }

    public void setBexp1(RichButton bexp1) {
        this.bexp1 = bexp1;
    }

    public RichButton getBexp1() {
        return bexp1;
    }

    public void setSbc1(RichSelectBooleanCheckbox sbc1) {
        this.sbc1 = sbc1;
    }

    public RichSelectBooleanCheckbox getSbc1() {
        return sbc1;
    }

    public void setSbc2(RichSelectBooleanCheckbox sbc2) {
        this.sbc2 = sbc2;
    }

    public RichSelectBooleanCheckbox getSbc2() {
        return sbc2;
    }

    public void setPfl6(RichPanelFormLayout pfl6) {
        this.pfl6 = pfl6;
    }

    public RichPanelFormLayout getPfl6() {
        return pfl6;
    }

    public void setPopCo(RichPopup popCo) {
        this.popCo = popCo;
    }

    public RichPopup getPopCo() {
        return popCo;
    }

    public void setBexp2(RichButton bexp2) {
        this.bexp2 = bexp2;
    }

    public RichButton getBexp2() {
        return bexp2;
    }

    public void setPfl7(RichPanelFormLayout pfl7) {
        this.pfl7 = pfl7;
    }

    public RichPanelFormLayout getPfl7() {
        return pfl7;
    }

    public void setPopColor(RichPopup popColor) {
        this.popColor = popColor;
    }

    public RichPopup getPopColor() {
        return popColor;
    }

    public void setPb1(RichPanelBox pb1) {
        this.pb1 = pb1;
    }

    public RichPanelBox getPb1() {
        return pb1;
    }

    public void setTreeLec(RichTreeTable treeLec) {
        this.treeLec = treeLec;
    }

    public RichTreeTable getTreeLec() {
        return treeLec;
    }

    public void setLeccionesTree(ChildPropertyTreeModel leccionesTree) {
        this.leccionesTree = leccionesTree;
    }

    public ChildPropertyTreeModel getLeccionesTree() {
        return leccionesTree;
    }


    public void setPopDis_aux(RichPopup popDis_aux) {
        this.popDis_aux = popDis_aux;
    }

    public RichPopup getPopDis_aux() {
        return popDis_aux;
    }

    public void setSubAgr(RichSubform subAgr) {
        this.subAgr = subAgr;
    }

    public RichSubform getSubAgr() {
        return subAgr;
    }

    public void setPfl8(RichPanelFormLayout pfl8) {
        this.pfl8 = pfl8;
    }

    public RichPanelFormLayout getPfl8() {
        return pfl8;
    }

    public void setPb2(RichPanelBox pb2) {
        this.pb2 = pb2;
    }

    public RichPanelBox getPb2() {
        return pb2;
    }

    public void setChAulaProfesor2(RichSelectOneChoice chAulaProfesor2) {
        this.chAulaProfesor2 = chAulaProfesor2;
    }

    public RichSelectOneChoice getChAulaProfesor2() {
        return chAulaProfesor2;
    }

    public void setChArea2(RichSelectOneChoice chArea2) {
        this.chArea2 = chArea2;
    }

    public RichSelectOneChoice getChArea2() {
        return chArea2;
    }

    public void setChCurso2(RichSelectOneChoice chCurso2) {
        this.chCurso2 = chCurso2;
    }

    public RichSelectOneChoice getChCurso2() {
        return chCurso2;
    }

    public void setPopEM(RichPopup popEM) {
        this.popEM = popEM;
    }

    public RichPopup getPopEM() {
        return popEM;
    }

    public void setChProf(RichSelectOneChoice chProf) {
        this.chProf = chProf;
    }

    public RichSelectOneChoice getChProf() {
        return chProf;
    }

    public void setChoiceDia(RichSelectManyChoice choiceDia) {
        this.choiceDia = choiceDia;
    }

    public RichSelectManyChoice getChoiceDia() {
        return choiceDia;
    }
}
