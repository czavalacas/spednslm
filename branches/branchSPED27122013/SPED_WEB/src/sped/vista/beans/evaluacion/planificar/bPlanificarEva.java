package sped.vista.beans.evaluacion.planificar;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.component.UISelectItems;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.el.MethodExpression;
import javax.el.ValueExpression;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.RowSet;
import oracle.adf.model.BindingContext;
import oracle.adf.model.binding.DCBindingContainer;
import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.share.logging.ADFLogger;
import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.data.RichCalendar;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.input.RichInputDate;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.input.RichSelectOneChoice;
import oracle.adf.view.rich.component.rich.layout.RichPanelBox;
import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.component.rich.nav.RichButton;
import oracle.adf.view.rich.component.rich.nav.RichCommandButton;
import oracle.adf.view.rich.component.rich.output.RichActiveOutputText;
import oracle.adf.view.rich.event.CalendarActivityEvent;
import oracle.adf.view.rich.event.CalendarEvent;
import oracle.adf.view.rich.model.CalendarActivity;
import oracle.adf.view.rich.render.ClientEvent;
import oracle.adf.view.rich.util.CalendarActivityRamp;
import oracle.adf.view.rich.util.InstanceStyles;
import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;
import oracle.jbo.ViewObject;
import oracle.jbo.server.ViewObjectImpl;
import oracle.jbo.uicli.binding.JUCtrlActionBinding;
import org.apache.myfaces.trinidad.event.AttributeChangeEvent;
import org.apache.myfaces.trinidad.event.SelectionEvent;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import sped.negocio.BDL.IR.BDL_C_SFAreaAcademicaRemote;
import sped.negocio.BDL.IR.BDL_C_SFEvaluacionRemoto;
import sped.negocio.BDL.IR.BDL_C_SFMainRemote;
import sped.negocio.BDL.IR.BDL_C_SFUsuarioRemote;
import sped.negocio.BDL.IR.BDL_T_SFEvaluacionRemoto;
import sped.negocio.BDL.IR.BDL_T_SFMainRemoto;
import sped.negocio.BDL.IR.BDL_T_SFUsuarioRemote;
import sped.negocio.LNSF.IR.LN_C_SFAreaAcademicaRemote;
import sped.negocio.LNSF.IR.LN_C_SFAulaRemote;
import sped.negocio.LNSF.IR.LN_C_SFCursoRemoto;
import sped.negocio.LNSF.IR.LN_C_SFEvaluacionRemote;
import sped.negocio.LNSF.IR.LN_C_SFFichaRemote;
import sped.negocio.LNSF.IR.LN_C_SFGradoRemote;
import sped.negocio.LNSF.IR.LN_C_SFMainRemote;
import sped.negocio.LNSF.IR.LN_C_SFNivelRemote;
import sped.negocio.LNSF.IR.LN_C_SFProfesorRemote;
import sped.negocio.LNSF.IR.LN_C_SFSedeRemote;
import sped.negocio.LNSF.IR.LN_C_SFUsuarioRemote;
import sped.negocio.LNSF.IR.LN_C_SFUtilsRemote;
import sped.negocio.LNSF.IR.LN_T_SFEvaluacionRemote;
import sped.negocio.entidades.admin.AreaAcademica;
import sped.negocio.entidades.admin.Aula;
import sped.negocio.entidades.admin.Constraint;
import sped.negocio.entidades.admin.Curso;
import sped.negocio.entidades.admin.Main;
import sped.negocio.entidades.admin.Profesor;
import sped.negocio.entidades.admin.Usuario;
import sped.negocio.entidades.beans.BeanAreaAcademica;
import sped.negocio.entidades.beans.BeanAula;
import sped.negocio.entidades.beans.BeanCombo;
import sped.negocio.entidades.beans.BeanComboString;
import sped.negocio.entidades.beans.BeanConstraint;
import sped.negocio.entidades.beans.BeanCurso;
import sped.negocio.entidades.beans.BeanEvaluacion;
import sped.negocio.entidades.beans.BeanEvaluacionPlani;
import sped.negocio.entidades.beans.BeanGrado;
import sped.negocio.entidades.beans.BeanGradoNivel;
import sped.negocio.entidades.beans.BeanMain;
import sped.negocio.entidades.beans.BeanNivel;
import sped.negocio.entidades.beans.BeanProfesor;
import sped.negocio.entidades.beans.BeanSede;
import sped.negocio.entidades.beans.BeanUsuario;
//import sped.negocio.entidades.eval.Evaluacion;
import sped.vista.Utils.Utils;

/** Clase de Respaldo bPlanificar.java
 * @author czavalacas
 * @since 29.12.2013
 */
public class bPlanificarEva {

    private sessionPlanificar sessionPlanificarEva;
    private RichCalendar calendar;
    private RichPopup popupEvento;
    private RichTable tbHorario;
    private List evaluadores;
    private RichSelectOneChoice choiceEvaluadores;
    private RichSelectOneChoice choiceProfesores;
    private UISelectItems itemsEvaluadores;
    private RichCommandButton btnEvaluar;
    private RichPopup popupDetalleEva;
    private RichSelectOneChoice choiceCursos;
    private RichPopup popupEvalua;
    private HtmlOutputText outDatosEva;
    private RichPopup popupEliminarEvalu;
    private RichButton btnAsignarEva;
    private RichTable tbEvaluadores;
    private String nidAreaAcademicaFiltro;
    private RichSelectOneChoice choiceFiltArea;
    private RichSelectOneChoice choiceSede;
    private RichSelectOneChoice choiceNivel;
    private RichSelectOneChoice choiceGrado;
    private RichPopup popupSeleccionBloque;
    private RichButton btnBloque1;
    private RichButton btnBloque2;
    private RichSelectOneChoice choiceTipoVisita;
    private RichSelectOneChoice choiceAreaAcademicas;
    private RichPanelBox panelBoxComentYSug;
    private RichPanelBox panelBoxJusticacion;
    private RichSelectOneChoice choiceProblema;
    private RichInputText inputDescripcionOtros;
    private RichInputText inputComentarioEvaluador;
    private RichInputText inputComentarioProfesor;
    private RichButton btnSaveComentEvalu;
    private RichButton btnSaveJustificacion;    
    private RichPopup popupEvento2;
    private RichSelectOneChoice choiceAula;
    private RichInputDate horaInicioTemporal;
    private RichInputDate horaFinTemporal;
    private HtmlOutputText outDatosEva2;
    private RichPanelGroupLayout pl1;
    private RichPopup popupExisteEvaluacion;
    private RichActiveOutputText errJustif;
    private RichInputText inputHoraFin;
    private RichInputText inputHoraInicio;
    @EJB
    private LN_C_SFMainRemote ln_C_SFMainRemote;
    @EJB
    private LN_C_SFUsuarioRemote ln_C_SFUsuarioRemote;
    @EJB
    private LN_C_SFEvaluacionRemote ln_C_SFEvaluacionRemoto;
    private final static String LOOKUP_NAME_SFEVALUADORES_REMOTO = "mapLN_C_SFUsuario";   
    @EJB
    private LN_C_SFAreaAcademicaRemote ln_C_SFAreaAcademicaRemote;
    @EJB
    private LN_C_SFSedeRemote ln_C_SFSedeRemote;
    @EJB
    private LN_T_SFEvaluacionRemote ln_T_SFEvaluacionRemote;
    @EJB
    private LN_C_SFCursoRemoto ln_C_SFCursoRemoto;
    @EJB
    private LN_C_SFGradoRemote ln_C_SFGradoRemote;
    @EJB
    private LN_C_SFNivelRemote ln_C_SFNivelRemote; 
    @EJB
    private LN_C_SFUtilsRemote ln_C_SFUtilsRemote; 
    @EJB
    private LN_C_SFAulaRemote ln_C_SFAulaRemote;
    @EJB
    private LN_C_SFProfesorRemote ln_C_SFProfesorRemote;
    @EJB
    private LN_C_SFFichaRemote ln_C_SFFichaRemote;
    FacesContext ctx = FacesContext.getCurrentInstance();
    private HashMap activityStyles = new HashMap<Set<String>, InstanceStyles>();
    private BeanUsuario beanUsuario = (BeanUsuario) Utils.getSession("USER");
    private String msjErrorJustif;//dfloresgonz 29.05.2014

    public bPlanificarEva() {
        try {
            final Context ctx;
            ctx = new InitialContext();
            ln_C_SFUsuarioRemote = (LN_C_SFUsuarioRemote) ctx.lookup(LOOKUP_NAME_SFEVALUADORES_REMOTO);           
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @PostConstruct
    public void methodInvokeOncedOnPageLoad() {
        loadactivityStyles();
        sessionPlanificarEva.setNidPlanificador(beanUsuario.getNidUsuario());
        sessionPlanificarEva.setNidRol(beanUsuario.getRol().getNidRol());
        //rol=1 Director // rol=6 Administrador // Podran Asignar Evaluaciones
        if (beanUsuario.getRol().getNidRol() == 1 || beanUsuario.getRol().getNidRol() == 6 ||
            beanUsuario.getRol().getNidRol() == 2 && "1".equals(beanUsuario.getIsSupervisor())) {
            sessionPlanificarEva.setEstadoChoiceEvaluadores(true);
        } else {
            sessionPlanificarEva.setEstadoChoiceEvaluadores(false);
            sessionPlanificarEva.setNidUsuario(beanUsuario.getNidUsuario().toString());
        }
        if (sessionPlanificarEva.getExec() == 0) {
            sessionPlanificarEva.setExec(1);
            sessionPlanificarEva.setItemNombreProferos(Utils.llenarListItem(ln_C_SFProfesorRemote.getNombreProfesor_LN()));
        }
    }

    public String popupEvaluadores() {
        sessionPlanificarEva.setListBeanUsua(ln_C_SFUsuarioRemote.getEvaluadores(sessionPlanificarEva.getNidAreaFiltro()));
        sessionPlanificarEva.setListAreaAcademica(Utils.llenarCombo(ln_C_SFUtilsRemote.getAreas_LN_WS()));
        Utils.showPopUpMIDDLE(popupEvalua);
        return null;
    }    

    public String grabarEva1() {//TODO dale un nombre entendible
        grabarEvaluacion(1);//TODO dale un nombre entendible
        popupSeleccionBloque.hide();
        return null;
    }

    public String grabarEva2() {//TODO dale un nombre entendible
        grabarEvaluacion(2);//TODO dale un nombre entendible
        popupSeleccionBloque.hide();
        return null;
    }
         
    public String grabarEvaluacion(int opc) {     
        long s=0;//TODO variables sin sentido que es s o c ???
        long c=0;
        if (opc == 1) {
             s = sessionPlanificarEva.getFechaInicioEvaluacion().getTime();
             c = sessionPlanificarEva.getHoraPartidaInicio().getTime();
        }
        if (opc == 2) {
             s = sessionPlanificarEva.getHoraPartidaInicio().getTime();
             c = sessionPlanificarEva.getFechaFinEvaluacion().getTime();
        }
        String nidDat = Utils.generarAlfanumerico();   
        ln_T_SFEvaluacionRemote.registrarEvaluacion_LN(s, 
                                                       c, 
                                                       sessionPlanificarEva.getBeanHorario().getNidMain(), 
                                                       Integer.parseInt(getSessionPlanificarEva().getNidUsuario()), 
                                                       nidDat, 
                                                       sessionPlanificarEva.getNidPlanificador(), 
                                                       sessionPlanificarEva.getValorTipoVisita());
        llenarBean();
        if (tbHorario != null) {
            Utils.unselectFilas(tbHorario);
            tbHorario.setValue(sessionPlanificarEva.getListaHorarios());
            Utils.addTarget(tbHorario);
        }
        Utils.invokeEL("#{bindings.ExecuteWithParams.execute}");
        sessionPlanificarEva.setStyleClass(null);
        btnAsignarEva.setStyleClass(null);
        sessionPlanificarEva.setEstadoAsignarEvaluacion(true);
        btnAsignarEva.setDisabled(true);
        Utils.addTargetMany(calendar, btnAsignarEva);
        return null;
    }

    public void abrirPopupEvaluar(CalendarActivityEvent calendarActivityEvent) {
        CalendarActivity activity = calendarActivityEvent.getCalendarActivity();
        sessionPlanificarEva.setCalendaryActivityID(activity.getId());
        BeanEvaluacionPlani entida = ln_C_SFEvaluacionRemoto.getEvaluacionById_LN(activity.getId());
        sessionPlanificarEva.setFechaEvaluacionPopup(entida.getStartDate());
        sessionPlanificarEva.setHoraEvaluacionPopup(entida.getEndDate());
        sessionPlanificarEva.setSedeEvaluacion(entida.getDescSede());
        sessionPlanificarEva.setAulaEvaluacion(entida.getDescAula());
        sessionPlanificarEva.setCursoEvaluacion(entida.getDescCurso());
        sessionPlanificarEva.setGradoEvaluacion(entida.getDescGrado());
        sessionPlanificarEva.setNivelEvaluacion(entida.getDescNivel());
        sessionPlanificarEva.setDocenteEvaluacion(entida.getNombreCompletoProfesor());
        sessionPlanificarEva.setDniDocenteEvaluacion(entida.getDniDocente());
        sessionPlanificarEva.setNidEvaluacionDelet(entida.getNidEvaluacion());
        sessionPlanificarEva.setNombrePlanificador(ln_C_SFUsuarioRemote.getNombresUsuarioByNidUsuario_LN(entida.getNidPlanificador()));
        BeanUsuario usua=ln_C_SFUsuarioRemote.findConstrainByIdLN(entida.getNidPlanificador());
        sessionPlanificarEva.setRolPlanificador(usua.getRol().getDescripcionRol());
        BeanConstraint con = ln_C_SFEvaluacionRemoto.getTipoVisita_ByValorLN(entida.getTipoVisita());
        sessionPlanificarEva.setTipoEvaluacion(con.getDescripcionAMostrar());
        sessionPlanificarEva.setComentarioEvaluador(entida.getComentarioEvaluador());
        sessionPlanificarEva.setComentarioProfesor(entida.getComentarioProfesor());
        sessionPlanificarEva.setJustificacionProfesor(entida.getDescProblema());
        sessionPlanificarEva.setEstadoDeEvaluacion(entida.getEstadoEvaluacion());
        
        if ("0".equals(entida.getFlgAnular())) {
            sessionPlanificarEva.setEstadoBotonEliminarEvaluacion(false);
        } else {
            if (entida.getNidPlanificador().intValue() != sessionPlanificarEva.getNidPlanificador().intValue() &&
                "0".equals(beanUsuario.getIsSupervisor())) {
                sessionPlanificarEva.setEstadoBotonEliminarEvaluacion(false);
            } else {
                sessionPlanificarEva.setEstadoBotonEliminarEvaluacion(true);
            }
        }
        if("EJECUTADO".equals(sessionPlanificarEva.getEstadoDeEvaluacion())){
            sessionPlanificarEva.setNidProblema(null);
            sessionPlanificarEva.setEstadoBoxComentarios(true);
            sessionPlanificarEva.setEstadoBoxJustificacion(false);
        }
        if ("1".equals(entida.getFlgJustificar())) { //cambio
            sessionPlanificarEva.setListaProblemas(Utils.llenarCombo(ln_C_SFUtilsRemote.getProblemas_LN_WS()));
            sessionPlanificarEva.setEstadoBoxJustificacion(true);
            sessionPlanificarEva.setEstadoBoxComentarios(false);
            if (   entida.getNidProblema() != 0) {
                sessionPlanificarEva.setNidProblema("" + entida.getNidProblema());
                sessionPlanificarEva.setEstadoDisableChoiceProblema(true);
                sessionPlanificarEva.setEstadoDinputJustificacion(true);
                sessionPlanificarEva.setEstadoDinputJustificacionVisible(true);
            } else {
                sessionPlanificarEva.setNidProblema(null);
                sessionPlanificarEva.setEstadoDisableChoiceProblema(false);
                sessionPlanificarEva.setEstadoDinputJustificacion(false);
                }                  
        }else{
            sessionPlanificarEva.setEstadoBoxJustificacion(false);
            sessionPlanificarEva.setEstadoBoxComentarios(true);
            
        }
       /* if(sessionPlanificarEva.getEstadoDeEvaluacion().equals("NO EJECUTADO")){
            sessionPlanificarEva.setListaProblemas(Utils.llenarCombo(ln_C_SFUtilsRemote.getProblemas_LN_WS()));  
            sessionPlanificarEva.setEstadoBoxJustificacion(true);
            sessionPlanificarEva.setEstadoBoxComentarios(false);
            if(entida.getNidProblema()!=0){               
                sessionPlanificarEva.setNidProblema(""+entida.getNidProblema());
                sessionPlanificarEva.setEstadoDisableChoiceProblema(true);
                sessionPlanificarEva.setEstadoDinputJustificacion(true);
                sessionPlanificarEva.setEstadoDinputJustificacionVisible(true);
            }else{
            sessionPlanificarEva.setNidProblema(null);
            sessionPlanificarEva.setEstadoDisableChoiceProblema(false);
            sessionPlanificarEva.setEstadoDinputJustificacion(false);
            }
        }*/
        if(sessionPlanificarEva.getEstadoDeEvaluacion().equals("PENDIENTE")){
            sessionPlanificarEva.setEstadoBoxJustificacion(false);
            sessionPlanificarEva.setEstadoBoxComentarios(false);
        }
        //dfloresgonz 29.05.2014
        this.setMsjErrorJustif(null);
        if(errJustif != null){
            this.errJustif.setValue(null);
            Utils.addTarget(errJustif); 
        }//dfloresgonz 29.05.2014 FIN
        Utils.showPopUpMIDDLE(popupDetalleEva);
    }
    
    public void seleccionarProblema(ValueChangeEvent valueChangeEvent) {
        //dfloresgonz 04.05.2014 - Quite la validacion para mostrar el inputtext de descripcion porque se debe permitir para todos.
        sessionPlanificarEva.setEstadoBtnSaveJustificaEvalu(true);
        Utils.addTargetMany(inputDescripcionOtros,btnSaveJustificacion);
    }
    
    public String guardarJustificacion() {//dfloresgonz 29.05.2014
        if(sessionPlanificarEva.isEstadoDisableChoiceProblema() == false && 
           sessionPlanificarEva.isEstadoDinputJustificacion() == false && 
           sessionPlanificarEva.isEstadoBtnSaveJustificaEvalu() ){
            if(sessionPlanificarEva.getNidProblema() == null || sessionPlanificarEva.getJustificacionProfesor() == null){
                this.setMsjErrorJustif("Seleccione el problema y su descripcion");
                this.errJustif.setValue(this.getMsjErrorJustif());
                Utils.addTarget(errJustif);
                return null;
            }else{
                if("0".equals(sessionPlanificarEva.getNidProblema()) || sessionPlanificarEva.getJustificacionProfesor().isEmpty() ){
                    this.setMsjErrorJustif("Seleccione el problema y su descripcion");
                    this.errJustif.setValue(this.getMsjErrorJustif());
                    Utils.addTarget(errJustif);
                    return null;
                }
            }
        }//dfloresgonz 29.05.2014 FIN
        ln_T_SFEvaluacionRemote.grabarComentariosYJustificacionesDeEvaluacion(sessionPlanificarEva.getCalendaryActivityID(), 
                                                                              sessionPlanificarEva.getComentarioEvaluador(),
                                                                              sessionPlanificarEva.getJustificacionProfesor(),//Problema que se presento
                                                                              sessionPlanificarEva.getNidProblema());
        sessionPlanificarEva.setEstadoBtnSaveJustificaEvalu(false);
        sessionPlanificarEva.setEstadoDinputJustificacion(true);
        sessionPlanificarEva.setEstadoDisableChoiceProblema(true);
        Utils.invokeEL("#{bindings.ExecuteWithParams.execute}");
        Utils.addTargetMany(btnSaveJustificacion,choiceProblema,inputDescripcionOtros,calendar);
        return null;
    }
    
    public String guardarComentarioEvaluador() {
        ln_T_SFEvaluacionRemote.grabarComentariosYJustificacionesDeEvaluacion(sessionPlanificarEva.getCalendaryActivityID(), 
                                                                              sessionPlanificarEva.getComentarioEvaluador(),
                                                                              sessionPlanificarEva.getJustificacionProfesor(), //Problema que se presento
                                                                              sessionPlanificarEva.getNidProblema());
       sessionPlanificarEva.setEstadoDinputcomentarioEvaluador(false);
       sessionPlanificarEva.setEstadoBtnSaveComentEvalu(false);
       Utils.addTargetMany(inputComentarioEvaluador,btnSaveComentEvalu);
       return null;
    }

    public void activarEstadoComentEvalu(ValueChangeEvent vce) {
        sessionPlanificarEva.setEstadoBtnSaveComentEvalu(true);
        Utils.addTarget(btnSaveComentEvalu);
    }

    public String llenarHorarios(BeanMain beanMain) {
        List<BeanMain> lis = ln_C_SFMainRemote.llenarHorario(beanMain);
        if (lis != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");//TODO usar del metodo Utils.getHoyFormato
            Date fechaActual = sessionPlanificarEva.getFechaInicioSeleccionada();
            String fechaConFormato = sdf.format(fechaActual);
            List<BeanEvaluacion> listaEvaluaciones =
                ln_C_SFEvaluacionRemoto.getEvaluaciones_LN(fechaConFormato, sessionPlanificarEva.getNidAreaAcademica(),
                                                           Integer.parseInt(sessionPlanificarEva.getNidUsuario()),
                                                           sessionPlanificarEva.getDniProfesor(),
                                                           sessionPlanificarEva.getNidCurso(),
                                                           sessionPlanificarEva.getNidSedeEvaluador());
            if (listaEvaluaciones.size() == lis.size()) {
                lis.clear();
            } else {
                if (lis.size() != 0) {
                    if (listaEvaluaciones.size() != 0) {
                        for (int j = 0; j < listaEvaluaciones.size(); j++) {
                            for (int i = 0; i < lis.size(); i++) {
                                if (listaEvaluaciones.get(j).getMain().getNidMain() == lis.get(i).getNidMain()) {
                                    lis.remove(i);
                                }
                            }
                        }
                    }
                }
            }
            sessionPlanificarEva.setListaHorarios(lis);
        }
        return null;
    }

    public void llenarBean() {
        BeanMain beanMain = new BeanMain();
        BeanAreaAcademica beanAca = new BeanAreaAcademica();
        BeanCurso beanCurso = new BeanCurso();       
        if (sessionPlanificarEva.getNidAreaAcademica() != 0) {
            beanAca.setNidAreaAcademica(sessionPlanificarEva.getNidAreaAcademica());
            beanCurso.setAreaAcademica(beanAca);            
        }
            if (sessionPlanificarEva.getNidAreaAcademicaChoice() != null) {
                beanAca.setNidAreaAcademica(Integer.parseInt(sessionPlanificarEva.getNidAreaAcademicaChoice()));
                beanCurso.setAreaAcademica(beanAca);                
            }        
      
        if (sessionPlanificarEva.getNidCurso() != null) {
            beanCurso.setNidCurso(Integer.parseInt(sessionPlanificarEva.getNidCurso()));          
        }
        BeanProfesor beanProf = new BeanProfesor();
        if (sessionPlanificarEva.getDniProfesor() != null) {
            beanProf.setDniProfesor(sessionPlanificarEva.getDniProfesor().toString());
            beanMain.setProfesor(beanProf);          
        }
        BeanSede sede = new BeanSede();
        BeanAula aula = new BeanAula();
        BeanGradoNivel grani = new BeanGradoNivel();
        if (sessionPlanificarEva.getNidSedeEvaluador() != 0) {
            sede.setNidSede(sessionPlanificarEva.getNidSedeEvaluador());
            aula.setSede(sede);           
        }
        if (sessionPlanificarEva.getNidSede() != null) {
            sede.setNidSede(Integer.parseInt(sessionPlanificarEva.getNidSede()));
            aula.setSede(sede);
        }
        if (sessionPlanificarEva.getNidGrado() != null) {
            BeanGrado grado = new BeanGrado();
            grado.setNidGrado(Integer.parseInt(sessionPlanificarEva.getNidGrado()));
            grani.setGrado(grado);
        }
        if (sessionPlanificarEva.getNidNivel() != null) {
            BeanNivel nivel = new BeanNivel();
            nivel.setNidNivel(Integer.parseInt(sessionPlanificarEva.getNidNivel()));
            grani.setNivel(nivel);
        }
        aula.setGradoNivel(grani);
        beanMain.setAula(aula);
        beanMain.setCurso(beanCurso);
        beanMain.setDia(sessionPlanificarEva.getDiaDeLaSemana());
     //   llenarHorarios(beanMain); COMENTADO TEMPORALMENTE ARREGLAR EL MAPPER DENTRO
    }

    public void abrirNuevoEvento(CalendarEvent calendarEvent) {
        sessionPlanificarEva.setIsSupervisor(0);
        sessionPlanificarEva.setEstadoChoiceTemporalAula(true);
        sessionPlanificarEva.setEstadoChoiceTemporalCurso(true);
        sessionPlanificarEva.setEstadoChoiceTemporalDocente(true);
        sessionPlanificarEva.setEstadoChoiceTemporalNivel(true);
        sessionPlanificarEva.setNidAreaAcademica(0);
        sessionPlanificarEva.setNidSedeEvaluador(0);
        sessionPlanificarEva.setEstadoDisableChoiceSede(false);
        sessionPlanificarEva.setEstadoDisableChoiceArea(false);
        Date fechaHoy = new Date();
        Date fecha = (Date) fechaHoy.clone();
        fecha.setHours(0);
        fecha.setMinutes(0);
        fecha.setSeconds(0);
        Date fechaCalen = calendarEvent.getTriggerDate();
        Date fecha2 = (Date) fechaCalen.clone();
        fecha2.setSeconds(01);
        if (fecha2.after(fecha)) {
            if (sessionPlanificarEva.getNidUsuario() != null) {
                sessionPlanificarEva.setListaProfesores(null);
                sessionPlanificarEva.setListaHorarios(null);
                sessionPlanificarEva.setListaCursos(null);
                sessionPlanificarEva.setDniProfesor(null);
                sessionPlanificarEva.setDiaDeLaSemana(null);
                sessionPlanificarEva.setNidCurso(null);
                sessionPlanificarEva.setListaSedes(null);
                sessionPlanificarEva.setListaNiveles(null);
                sessionPlanificarEva.setListaGrados(null);
                sessionPlanificarEva.setListAreasAcade(null);
                sessionPlanificarEva.setNidSede(null);
                sessionPlanificarEva.setNidGrado(null);
                sessionPlanificarEva.setNidNivel(null);
                sessionPlanificarEva.setNidAreaAcademicaChoice(null);
                sessionPlanificarEva.setTipoFichaCurs(null);
                sessionPlanificarEva.setFNombres(null);
                if (tbHorario != null) {
                    tbHorario.setValue(null);
                }
                if (sessionPlanificarEva.getNidUsuario() != null) {
                    BeanUsuario evaluador =  ln_C_SFUsuarioRemote.findConstrainByIdLN(Integer.parseInt(sessionPlanificarEva.getNidUsuario()));
                    if("1".equals(evaluador.getIsSupervisor())){
                        sessionPlanificarEva.setIsSupervisor(1);
                    }
                    sessionPlanificarEva.setNidRolUsuarioEnSession(evaluador.getRol().getNidRol());
                    if(evaluador.getRol().getNidRol() == 4){
                        sessionPlanificarEva.setNidSede(""+evaluador.getSede().getNidSede());
                        sessionPlanificarEva.setEstadoDisableChoiceSede(true);
                        sessionPlanificarEva.setListaNiveles(Utils.llenarCombo(ln_C_SFNivelRemote.getAllNivelesBySedes(evaluador.getSede().getNidSede().toString())));
                        sessionPlanificarEva.setEstadoChoiceTemporalNivel(false);
                        sessionPlanificarEva.setEstadoVisibleComboAreaacademica(false);
                    }
                        if(evaluador.getRol().getNidRol() == 2){
                            sessionPlanificarEva.setNidAreaAcademicaChoice(""+evaluador.getAreaAcademica().getNidAreaAcademica());
                            sessionPlanificarEva.setEstadoDisableChoiceArea(true);
                        }
                    if(evaluador.getAreaAcademica()!=null){
                        if(evaluador.getAreaAcademica().getNidAreaAcademica() != 0){
                            sessionPlanificarEva.setNidAreaAcademica(evaluador.getAreaAcademica().getNidAreaAcademica());
                        }                        
                    }
                    if (evaluador.getSede() != null) {
                        if (evaluador.getSede().getNidSede() != 0) {
                            sessionPlanificarEva.setNidSedeEvaluador(evaluador.getSede().getNidSede());
                        }
                    }
                }
                sessionPlanificarEva.setFechaInicioSeleccionada(calendarEvent.getTriggerDate());
                String dia = Utils.getDiaDeCalendario(calendarEvent.getTriggerDate().getDay());
                sessionPlanificarEva.setDiaDeLaSemana(dia);
                llenarBean();
                /**Temporalemnte se nulea el dia de la semana de setListaCursos hasta tener el modulo de horarios completo*/
                //  sessionPlanificarEva.setListaProfesores(Utils.llenarComboString(ln_C_SFMainRemote.findProfesoresPorAreaAcademica_LN(sessionPlanificarEva.getNidAreaAcademica(),
                 //                                               sessionPlanificarEva.getDiaDeLaSemana())));
              /*  sessionPlanificarEva.setListaCursos(Utils.llenarCombo(ln_C_SFCursoRemoto.findCursosPorAreaAcademica(sessionPlanificarEva.getNidAreaAcademica(),
                                                         null)));*/
                sessionPlanificarEva.setListaCursos(Utils.llenarCombo(ln_C_SFCursoRemoto.findCursosByArea(""+sessionPlanificarEva.getNidAreaAcademica())));
                /**Comentado Temporalmente*/
               /* sessionPlanificarEva.setListaSedes(Utils.llenarCombo(ln_C_SFSedeRemote.findSedePorAreaAcademica(sessionPlanificarEva.getNidAreaAcademica(),
                                                       sessionPlanificarEva.getDiaDeLaSemana())));                
                  sessionPlanificarEva.setListaNiveles(Utils.llenarCombo(ln_C_SFNivelRemote.findNivelPorAreaAcademica(sessionPlanificarEva.getNidAreaAcademica(),
                                                        sessionPlanificarEva.getDiaDeLaSemana())));
                */
                /*****/
                sessionPlanificarEva.setListaSedes(Utils.llenarCombo(ln_C_SFSedeRemote.getAllSedes()));/** ESTO NO VA BORRAR LUEGO DE completar modulo de horarios..**/
                sessionPlanificarEva.setListaNiveles(Utils.llenarCombo(ln_C_SFNivelRemote.getAllNivelesBySedes(null))); /** ESTO NO VA BORRAR LUEGO DE completar modulo de horarios..**/
                sessionPlanificarEva.setListaGrados(Utils.llenarCombo(ln_C_SFGradoRemote.findGradoPorAreaAcademica(sessionPlanificarEva.getNidAreaAcademica(),
                                                                                                                   sessionPlanificarEva.getDiaDeLaSemana())));
                
                sessionPlanificarEva.setListAreasAcade(Utils.llenarCombo(ln_C_SFUtilsRemote.getAreas_LN_WS()));
                sessionPlanificarEva.setEstadoAsignarEvaluacion(true);
                //Utils.showPopUpMIDDLE(popupEvento);  ESCONDIDO HASTA QUE HAIGA EL MODULO DE HORARIOS                
              /**DE AQUI HACIA ABAJO TODO ES TEMPORAL... */
                sessionPlanificarEva.setLstTiposFichaCurso(Utils.llenarComboString(ln_C_SFFichaRemote.getListaTiposFichaByTipoRol_LN("E")));
                sessionPlanificarEva.setListaAulasTemporal(Utils.llenarCombo(ln_C_SFAulaRemote.getAulaPorSedeNivelYGrado(sessionPlanificarEva.getNidSede(), sessionPlanificarEva.getNidGrado(), sessionPlanificarEva.getNidNivel())));
                Date fechaCalen2 = calendarEvent.getTriggerDate();
                Date fechaMinTemporal = (Date) fechaCalen2.clone();
           //     Date fechaMxxTemporal = (Date) fechaCalen2.clone();
                fechaMinTemporal.setHours(0);
                fechaMinTemporal.setMinutes(0);
                fechaMinTemporal.setSeconds(0);
          /*      fechaMxxTemporal.setHours(23);
                fechaMxxTemporal.setMinutes(59);
                fechaMxxTemporal.setSeconds(59);*/
                sessionPlanificarEva.setFechaMinTemporal(fechaMinTemporal);
              //  sessionPlanificarEva.setFehcaMaxTemporal(fechaMxxTemporal);
                sessionPlanificarEva.setListatipoVisita(Utils.llenarComboString(ln_C_SFUtilsRemote.getTipoVisitaFromConstraint()));
                sessionPlanificarEva.setValorTipoVisita("OP");
                sessionPlanificarEva.setFechaYhoraInicialTemporal(fechaMinTemporal);
                sessionPlanificarEva.setFechaYhoraFinTemporal(fechaMinTemporal);
                sessionPlanificarEva.setHoraInicio("00:00");
                sessionPlanificarEva.setHoraFin("00:00");
                sessionPlanificarEva.setNidAulaTemporal(null);
                Utils.showPopUpMIDDLE(popupEvento2);
            }
        }
    }

    public void seleccionarHorario(SelectionEvent se) {
        BeanMain main = (BeanMain) Utils.getRowTable(se);//TODO usar este metodo del utils mira en otros lados que lo puedas usar
        if (main != null) {
            sessionPlanificarEva.setBeanHorario(main);
            Date fechaMaster = sessionPlanificarEva.getFechaInicioSeleccionada();
            Date horaInicio = (Date) fechaMaster.clone();
            horaInicio.setHours(sessionPlanificarEva.getBeanHorario().getHoraInicio().getHours());
            horaInicio.setMinutes(sessionPlanificarEva.getBeanHorario().getHoraInicio().getMinutes());
            Date horaFin = (Date) fechaMaster.clone();
            horaFin.setHours(sessionPlanificarEva.getBeanHorario().getHoraFin().getHours());
            horaFin.setMinutes(sessionPlanificarEva.getBeanHorario().getHoraFin().getMinutes());
            sessionPlanificarEva.setFechaInicioEvaluacion(horaInicio);
            sessionPlanificarEva.setFechaFinEvaluacion(horaFin);
            sessionPlanificarEva.setFechaEvaluacionPopup(sessionPlanificarEva.getFechaInicioEvaluacion());
            sessionPlanificarEva.setHoraEvaluacionPopup(sessionPlanificarEva.getFechaFinEvaluacion());
            sessionPlanificarEva.setSedeEvaluacion(main.getAula().getSede().getDescripcionSede());
            sessionPlanificarEva.setAulaEvaluacion(main.getAula().getDescripcionAula());
            sessionPlanificarEva.setCursoEvaluacion(main.getCurso().getDescripcionCurso());
            sessionPlanificarEva.setGradoEvaluacion(main.getAula().getGradoNivel().getGrado().getDescripcionGrado());
            sessionPlanificarEva.setNivelEvaluacion(main.getAula().getGradoNivel().getNivel().getDescripcionNivel());
            sessionPlanificarEva.setDocenteEvaluacion(main.getProfesor().getApellidos() + " " +main.getProfesor().getNombres());
            int numHora = horaFin.getHours() - horaInicio.getHours();
            int numMinutos = horaFin.getMinutes() - horaInicio.getMinutes();
            int segundosEnMinutos = numMinutos * 60;
            int toalSegundos = numHora * 3600 + segundosEnMinutos;
            int segundosAumen = toalSegundos / 2;
            int num = segundosAumen;
            int hor = num / 3600;
            int min = (num - (3600 * hor)) / 60;
            int seg = num - ((hor * 3600) + (min * 60));
            Date fechaPartida = (Date) horaInicio.clone();
            fechaPartida.setHours(fechaPartida.getHours() + hor);
            fechaPartida.setMinutes(fechaPartida.getMinutes() + min);
            sessionPlanificarEva.setHoraPartidaInicio(fechaPartida);
        }
        sessionPlanificarEva.setStyleClass("FondoRojoLetraBlanca");
        btnAsignarEva.setStyleClass("FondoRojoLetraBlanca");
        sessionPlanificarEva.setEstadoAsignarEvaluacion(false);
        btnAsignarEva.setDisabled(false);
        Utils.addTarget(btnAsignarEva);
    }

    public String buscarHorariosFiltro() {
        llenarBean();
        if (tbHorario != null) {
            Utils.unselectFilas(tbHorario);
            tbHorario.setValue(sessionPlanificarEva.getListaHorarios());
            Utils.addTarget(tbHorario);
        }
        return null;
    }

    public String btnLimpiar() {
        /**  temporal */
        if (choiceAula != null) {
            sessionPlanificarEva.setNidAulaTemporal(null);
            choiceAula.resetValue();
            Utils.addTarget(choiceAula);
        }
        if (horaInicioTemporal != null) {
            sessionPlanificarEva.setFechaYhoraInicialTemporal(null);
            horaInicioTemporal.resetValue();
            Utils.addTarget(horaInicioTemporal);
        }
        if (horaFinTemporal != null) {
            sessionPlanificarEva.setFechaYhoraFinTemporal(null);
            horaFinTemporal.resetValue();
            Utils.addTarget(horaFinTemporal);
        }
        /** hasta aqui */
        if (choiceCursos != null) {
            sessionPlanificarEva.setNidCurso(null);
            choiceCursos.resetValue();
            Utils.addTarget(choiceCursos);
        }
        if (choiceProfesores != null) {
            sessionPlanificarEva.setDniProfesor(null);
            choiceProfesores.resetValue();
            Utils.addTarget(choiceProfesores);
        }
        if (choiceGrado != null) {
            sessionPlanificarEva.setNidGrado(null);
            choiceGrado.resetValue();
            Utils.addTarget(choiceGrado);
        }
        if (choiceNivel != null) {
            sessionPlanificarEva.setNidNivel(null);
            choiceNivel.resetValue();
            Utils.addTarget(choiceNivel);
        }
        if (choiceSede != null) {
            if (sessionPlanificarEva.getNidRolUsuarioEnSession() == 4) {
                choiceSede.setValue(sessionPlanificarEva.getNidSede());
                Utils.addTarget(choiceSede);
            } else {
                sessionPlanificarEva.setNidSede(null);
                choiceSede.resetValue();
                Utils.addTarget(choiceSede);
            }
        }
        if (choiceAreaAcademicas != null) {
            if (sessionPlanificarEva.getNidRolUsuarioEnSession() == 2) {
                choiceAreaAcademicas.setValue(sessionPlanificarEva.getNidAreaAcademicaChoice());
                Utils.addTarget(choiceAreaAcademicas);
            } else {
                sessionPlanificarEva.setNidAreaAcademicaChoice(null);
                choiceAreaAcademicas.resetValue();
                Utils.addTarget(choiceAreaAcademicas);
            }
        }
        llenarBean();
        if (tbHorario != null) {
            Utils.unselectFilas(tbHorario);
            tbHorario.setValue(sessionPlanificarEva.getListaHorarios());
            Utils.addTarget(tbHorario);
        }
        return null;
    }

    public void seleccionarEvaluador(SelectionEvent se) {
        sessionPlanificarEva.setNombreEvaluador(null);
        sessionPlanificarEva.setAreaEvaluador(null);
        BeanUsuario usu = (BeanUsuario) Utils.getRowTable(se);
        if (usu != null) {
            sessionPlanificarEva.setNidUsuario(usu.getNidUsuario().toString());
            sessionPlanificarEva.setNombreEvaluador(usu.getNombres());
            if (usu.getAreaAcademica() != null) {
                sessionPlanificarEva.setAreaEvaluador(usu.getAreaAcademica().getDescripcionAreaAcademica());
                sessionPlanificarEva.setEstadoOutDatosEva1(true);
                sessionPlanificarEva.setEstadoOutDatosEva2(true);
            }
            Utils.addTargetMany(outDatosEva, outDatosEva2, pl1);
            Utils.invokeEL("#{bindings.ExecuteWithParams.execute}");
            Utils.addTarget(calendar);
        }
    }

    public void eliminarEvaluacion(ActionEvent actionEvent) {       
        ln_T_SFEvaluacionRemote.removerEvaluacion_LN(sessionPlanificarEva.getNidEvaluacionDelet());
        Utils.invokeEL("#{bindings.ExecuteWithParams.execute}");
        Utils.addTarget(calendar);
        popupDetalleEva.hide();
    }

    public void cancelarAnulacion(ActionEvent actionEvent) {
        popupEliminarEvalu.hide();
    }

    public void confirmarAnulacion(ActionEvent actionEvent) {
        Utils.showPopUpMIDDLE(popupEliminarEvalu);
    }

    public void filtrarListaEvaluadores(ValueChangeEvent vce) {
        if ("0".equals(choiceFiltArea.getValue().toString())) {//TODO referencia siempre a las variables no al componente
            refTbEvalu(null);
        } else {
            refTbEvalu(choiceFiltArea.getValue().toString());//TODO referencia siempre a las variables no al componente
        }
    }

    public void refTbEvalu(String valor) {
        sessionPlanificarEva.setListBeanUsua(ln_C_SFUsuarioRemote.getEvaluadores(valor));
        if (tbEvaluadores != null) {
            Utils.unselectFilas(tbEvaluadores);
            tbEvaluadores.setValue(sessionPlanificarEva.getListBeanUsua());
            Utils.addTarget(tbEvaluadores);
        }
    }

    public String seleccionarBloque() {
        sessionPlanificarEva.setEstadoBtnBloq1(false);
        sessionPlanificarEva.setEstadoBtnBloq2(false);
        sessionPlanificarEva.setEstadoOut1(false);
        sessionPlanificarEva.setEstadoOut2(false);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date fechaActual = sessionPlanificarEva.getFechaInicioSeleccionada();
        String fechaConFormato = sdf.format(fechaActual); //TODO usa el Utils.getHoyFormato
        List<BeanEvaluacion> listaEvaluaciones =
            ln_C_SFEvaluacionRemoto.getEvaluaciones_LN(fechaConFormato, sessionPlanificarEva.getNidAreaAcademica(),
                                                       Integer.parseInt(sessionPlanificarEva.getNidUsuario()),
                                                       sessionPlanificarEva.getDniProfesor(),
                                                       sessionPlanificarEva.getNidCurso(),
                                                       sessionPlanificarEva.getNidSedeEvaluador());
        if (listaEvaluaciones.size() != 0) {//TODO estos IF con varias condiciones ponlos en metodos para que sea mas facil de leer
            for (int i = 0; i < listaEvaluaciones.size(); i++) {
                if (sessionPlanificarEva.getFechaInicioEvaluacion().getHours() ==
                    listaEvaluaciones.get(i).getStartDate().getHours() &&
                    sessionPlanificarEva.getFechaInicioEvaluacion().getMinutes() ==
                    listaEvaluaciones.get(i).getStartDate().getMinutes() &&
                    sessionPlanificarEva.getHoraPartidaInicio().getHours() ==
                    listaEvaluaciones.get(i).getEndDate().getHours() &&
                    sessionPlanificarEva.getHoraPartidaInicio().getMinutes() ==
                    listaEvaluaciones.get(i).getEndDate().getMinutes()) {
                    sessionPlanificarEva.setEstadoBtnBloq1(true);
                    sessionPlanificarEva.setEstadoOut1(true);
                }
                if (sessionPlanificarEva.getFechaInicioEvaluacion().getHours() ==
                    listaEvaluaciones.get(i).getStartDate().getHours() &&
                    sessionPlanificarEva.getFechaInicioEvaluacion().getMinutes() ==
                    listaEvaluaciones.get(i).getStartDate().getMinutes() &&
                    (sessionPlanificarEva.getHoraPartidaInicio().getHours() ==
                     listaEvaluaciones.get(i).getEndDate().getHours() &&
                     sessionPlanificarEva.getHoraPartidaInicio().getMinutes() >
                     listaEvaluaciones.get(i).getEndDate().getMinutes() ||
                     sessionPlanificarEva.getHoraPartidaInicio().getHours() >
                     listaEvaluaciones.get(i).getEndDate().getHours())) {
                    sessionPlanificarEva.setEstadoBtnBloq1(true);
                    sessionPlanificarEva.setEstadoOut1(true);
                }
                if (sessionPlanificarEva.getFechaInicioEvaluacion().getHours() ==
                    listaEvaluaciones.get(i).getStartDate().getHours() &&
                    sessionPlanificarEva.getFechaInicioEvaluacion().getMinutes() ==
                    listaEvaluaciones.get(i).getStartDate().getMinutes() &&
                    (sessionPlanificarEva.getHoraPartidaInicio().getHours() ==
                     listaEvaluaciones.get(i).getEndDate().getHours() &&
                     sessionPlanificarEva.getHoraPartidaInicio().getMinutes() <
                     listaEvaluaciones.get(i).getEndDate().getMinutes() ||
                     sessionPlanificarEva.getHoraPartidaInicio().getHours() <
                     listaEvaluaciones.get(i).getEndDate().getHours())) {
                    sessionPlanificarEva.setEstadoBtnBloq1(true);
                    sessionPlanificarEva.setEstadoOut1(true);
                }
                if ((sessionPlanificarEva.getFechaInicioEvaluacion().getHours() ==
                     listaEvaluaciones.get(i).getStartDate().getHours() &&
                     sessionPlanificarEva.getFechaInicioEvaluacion().getMinutes() >
                     listaEvaluaciones.get(i).getStartDate().getMinutes() ||
                     sessionPlanificarEva.getFechaInicioEvaluacion().getHours() >
                     listaEvaluaciones.get(i).getStartDate().getHours()) &&
                    sessionPlanificarEva.getHoraPartidaInicio().getHours() ==
                    listaEvaluaciones.get(i).getEndDate().getHours() &&
                    sessionPlanificarEva.getHoraPartidaInicio().getMinutes() ==
                    listaEvaluaciones.get(i).getEndDate().getMinutes()) {
                    sessionPlanificarEva.setEstadoBtnBloq1(true);
                    sessionPlanificarEva.setEstadoOut1(true);
                }
                if ((sessionPlanificarEva.getFechaInicioEvaluacion().getHours() ==
                     listaEvaluaciones.get(i).getStartDate().getHours() &&
                     sessionPlanificarEva.getFechaInicioEvaluacion().getMinutes() >
                     listaEvaluaciones.get(i).getStartDate().getMinutes() ||
                     sessionPlanificarEva.getFechaInicioEvaluacion().getHours() >
                     listaEvaluaciones.get(i).getStartDate().getHours()) &&
                    (sessionPlanificarEva.getHoraPartidaInicio().getHours() ==
                     listaEvaluaciones.get(i).getEndDate().getHours() &&
                     sessionPlanificarEva.getHoraPartidaInicio().getMinutes() >
                     listaEvaluaciones.get(i).getEndDate().getMinutes() ||
                     sessionPlanificarEva.getHoraPartidaInicio().getHours() >
                     listaEvaluaciones.get(i).getEndDate().getHours()) &&
                    (sessionPlanificarEva.getFechaInicioEvaluacion().getHours() ==
                     listaEvaluaciones.get(i).getEndDate().getHours() &&
                     sessionPlanificarEva.getFechaInicioEvaluacion().getMinutes() <
                     listaEvaluaciones.get(i).getEndDate().getMinutes() ||
                     sessionPlanificarEva.getFechaInicioEvaluacion().getHours() <
                     listaEvaluaciones.get(i).getEndDate().getHours()))
                {
                    sessionPlanificarEva.setEstadoBtnBloq1(true);
                    sessionPlanificarEva.setEstadoOut1(true);
                }
                if ((sessionPlanificarEva.getFechaInicioEvaluacion().getHours() ==
                     listaEvaluaciones.get(i).getStartDate().getHours() &&
                     sessionPlanificarEva.getFechaInicioEvaluacion().getMinutes() >
                     listaEvaluaciones.get(i).getStartDate().getMinutes() ||
                     sessionPlanificarEva.getFechaInicioEvaluacion().getHours() >
                     listaEvaluaciones.get(i).getStartDate().getHours()) &&

                    (sessionPlanificarEva.getHoraPartidaInicio().getHours() ==
                     listaEvaluaciones.get(i).getEndDate().getHours() &&
                     sessionPlanificarEva.getHoraPartidaInicio().getMinutes() <
                     listaEvaluaciones.get(i).getEndDate().getMinutes() ||
                     sessionPlanificarEva.getHoraPartidaInicio().getHours() <
                     listaEvaluaciones.get(i).getEndDate().getHours())) {
                    sessionPlanificarEva.setEstadoBtnBloq1(true);
                    sessionPlanificarEva.setEstadoOut1(true);
                }
                if ((sessionPlanificarEva.getFechaInicioEvaluacion().getHours() ==
                     listaEvaluaciones.get(i).getStartDate().getHours() &&
                     sessionPlanificarEva.getFechaInicioEvaluacion().getMinutes() <
                     listaEvaluaciones.get(i).getStartDate().getMinutes() ||
                     sessionPlanificarEva.getFechaInicioEvaluacion().getHours() <
                     listaEvaluaciones.get(i).getStartDate().getHours()) &&
                    (sessionPlanificarEva.getHoraPartidaInicio().getHours() ==
                     listaEvaluaciones.get(i).getEndDate().getHours() &&
                     sessionPlanificarEva.getHoraPartidaInicio().getMinutes() <
                     listaEvaluaciones.get(i).getEndDate().getMinutes() ||
                     sessionPlanificarEva.getHoraPartidaInicio().getHours() <
                     listaEvaluaciones.get(i).getEndDate().getHours()) &&
                    (sessionPlanificarEva.getHoraPartidaInicio().getHours() ==
                     listaEvaluaciones.get(i).getStartDate().getHours() &&
                     sessionPlanificarEva.getHoraPartidaInicio().getMinutes() >
                     listaEvaluaciones.get(i).getStartDate().getMinutes() ||
                     sessionPlanificarEva.getHoraPartidaInicio().getHours() >
                     listaEvaluaciones.get(i).getStartDate().getHours()))
                {
                    sessionPlanificarEva.setEstadoBtnBloq1(true);
                    sessionPlanificarEva.setEstadoOut1(true);
                }
                if ((sessionPlanificarEva.getFechaInicioEvaluacion().getHours() ==
                     listaEvaluaciones.get(i).getStartDate().getHours() &&
                     sessionPlanificarEva.getFechaInicioEvaluacion().getMinutes() <
                     listaEvaluaciones.get(i).getStartDate().getMinutes() ||
                     sessionPlanificarEva.getFechaInicioEvaluacion().getHours() <
                     listaEvaluaciones.get(i).getStartDate().getHours()) &&
                    sessionPlanificarEva.getHoraPartidaInicio().getHours() ==
                    listaEvaluaciones.get(i).getEndDate().getHours() &&
                    sessionPlanificarEva.getHoraPartidaInicio().getMinutes() ==
                    listaEvaluaciones.get(i).getEndDate().getMinutes())
                {
                    sessionPlanificarEva.setEstadoBtnBloq1(true);
                    sessionPlanificarEva.setEstadoOut1(true);
                }
                if ((sessionPlanificarEva.getFechaInicioEvaluacion().getHours() ==
                     listaEvaluaciones.get(i).getStartDate().getHours() &&
                     sessionPlanificarEva.getFechaInicioEvaluacion().getMinutes() <
                     listaEvaluaciones.get(i).getStartDate().getMinutes() ||
                     sessionPlanificarEva.getFechaInicioEvaluacion().getHours() <
                     listaEvaluaciones.get(i).getStartDate().getHours()) &&
                    (sessionPlanificarEva.getHoraPartidaInicio().getHours() ==
                     listaEvaluaciones.get(i).getEndDate().getHours() &&
                     sessionPlanificarEva.getHoraPartidaInicio().getMinutes() >
                     listaEvaluaciones.get(i).getEndDate().getMinutes() ||
                     sessionPlanificarEva.getHoraPartidaInicio().getHours() >
                     listaEvaluaciones.get(i).getEndDate().getHours()))
                {
                    sessionPlanificarEva.setEstadoBtnBloq1(true);
                    sessionPlanificarEva.setEstadoOut1(true);
                }
                // BTN2
                if (sessionPlanificarEva.getHoraPartidaInicio().getHours() ==
                    listaEvaluaciones.get(i).getStartDate().getHours() &&
                    sessionPlanificarEva.getHoraPartidaInicio().getMinutes() ==
                    listaEvaluaciones.get(i).getStartDate().getMinutes() &&
                    sessionPlanificarEva.getFechaFinEvaluacion().getHours() ==
                    listaEvaluaciones.get(i).getEndDate().getHours() &&
                    sessionPlanificarEva.getFechaFinEvaluacion().getMinutes() ==
                    listaEvaluaciones.get(i).getEndDate().getMinutes()) {
                    sessionPlanificarEva.setEstadoBtnBloq2(true);
                    sessionPlanificarEva.setEstadoOut2(true);
                }
                if (sessionPlanificarEva.getHoraPartidaInicio().getHours() ==
                    listaEvaluaciones.get(i).getStartDate().getHours() &&
                    sessionPlanificarEva.getHoraPartidaInicio().getMinutes() ==
                    listaEvaluaciones.get(i).getStartDate().getMinutes() &&

                    (sessionPlanificarEva.getFechaFinEvaluacion().getHours() ==
                     listaEvaluaciones.get(i).getEndDate().getHours() &&
                     sessionPlanificarEva.getFechaFinEvaluacion().getMinutes() >
                     listaEvaluaciones.get(i).getEndDate().getMinutes() ||
                     sessionPlanificarEva.getFechaFinEvaluacion().getHours() >
                     listaEvaluaciones.get(i).getEndDate().getHours())) {
                    sessionPlanificarEva.setEstadoBtnBloq2(true);
                    sessionPlanificarEva.setEstadoOut2(true);
                }
                if (sessionPlanificarEva.getHoraPartidaInicio().getHours() ==
                    listaEvaluaciones.get(i).getStartDate().getHours() &&
                    sessionPlanificarEva.getHoraPartidaInicio().getMinutes() ==
                    listaEvaluaciones.get(i).getStartDate().getMinutes() &&

                    (sessionPlanificarEva.getFechaFinEvaluacion().getHours() ==
                     listaEvaluaciones.get(i).getEndDate().getHours() &&
                     sessionPlanificarEva.getFechaFinEvaluacion().getMinutes() <
                     listaEvaluaciones.get(i).getEndDate().getMinutes() ||
                     sessionPlanificarEva.getFechaFinEvaluacion().getHours() <
                     listaEvaluaciones.get(i).getEndDate().getHours())) {
                    sessionPlanificarEva.setEstadoBtnBloq2(true);
                    sessionPlanificarEva.setEstadoOut2(true);
                }
                if ((sessionPlanificarEva.getHoraPartidaInicio().getHours() ==
                     listaEvaluaciones.get(i).getStartDate().getHours() &&
                     sessionPlanificarEva.getHoraPartidaInicio().getMinutes() >
                     listaEvaluaciones.get(i).getStartDate().getMinutes() ||
                     sessionPlanificarEva.getHoraPartidaInicio().getHours() >
                     listaEvaluaciones.get(i).getStartDate().getHours()) &&

                    sessionPlanificarEva.getFechaFinEvaluacion().getHours() ==
                    listaEvaluaciones.get(i).getEndDate().getHours() &&
                    sessionPlanificarEva.getFechaFinEvaluacion().getMinutes() ==
                    listaEvaluaciones.get(i).getEndDate().getMinutes()) {
                    sessionPlanificarEva.setEstadoBtnBloq2(true);
                    sessionPlanificarEva.setEstadoOut2(true);
                }
                if ((sessionPlanificarEva.getHoraPartidaInicio().getHours() ==
                     listaEvaluaciones.get(i).getStartDate().getHours() &&
                     sessionPlanificarEva.getHoraPartidaInicio().getMinutes() >
                     listaEvaluaciones.get(i).getStartDate().getMinutes() ||
                     sessionPlanificarEva.getHoraPartidaInicio().getHours() >
                     listaEvaluaciones.get(i).getStartDate().getHours()) &&
                    (sessionPlanificarEva.getFechaFinEvaluacion().getHours() ==
                     listaEvaluaciones.get(i).getEndDate().getHours() &&
                     sessionPlanificarEva.getFechaFinEvaluacion().getMinutes() >
                     listaEvaluaciones.get(i).getEndDate().getMinutes() ||
                     sessionPlanificarEva.getFechaFinEvaluacion().getHours() >
                     listaEvaluaciones.get(i).getEndDate().getHours()) &&
                    (sessionPlanificarEva.getHoraPartidaInicio().getHours() ==
                     listaEvaluaciones.get(i).getEndDate().getHours() &&
                     sessionPlanificarEva.getHoraPartidaInicio().getMinutes() <
                     listaEvaluaciones.get(i).getEndDate().getMinutes() ||
                     sessionPlanificarEva.getHoraPartidaInicio().getHours() <
                     listaEvaluaciones.get(i).getEndDate().getHours()))
                {
                    sessionPlanificarEva.setEstadoBtnBloq2(true);
                    sessionPlanificarEva.setEstadoOut2(true);
                }
                if ((sessionPlanificarEva.getHoraPartidaInicio().getHours() ==
                     listaEvaluaciones.get(i).getStartDate().getHours() &&
                     sessionPlanificarEva.getHoraPartidaInicio().getMinutes() >
                     listaEvaluaciones.get(i).getStartDate().getMinutes() ||
                     sessionPlanificarEva.getHoraPartidaInicio().getHours() >
                     listaEvaluaciones.get(i).getStartDate().getHours()) &&

                    (sessionPlanificarEva.getFechaFinEvaluacion().getHours() ==
                     listaEvaluaciones.get(i).getEndDate().getHours() &&
                     sessionPlanificarEva.getFechaFinEvaluacion().getMinutes() <
                     listaEvaluaciones.get(i).getEndDate().getMinutes() ||
                     sessionPlanificarEva.getFechaFinEvaluacion().getHours() <
                     listaEvaluaciones.get(i).getEndDate().getHours())) {
                    sessionPlanificarEva.setEstadoBtnBloq2(true);
                    sessionPlanificarEva.setEstadoOut2(true);
                }
                if ((sessionPlanificarEva.getHoraPartidaInicio().getHours() ==
                     listaEvaluaciones.get(i).getStartDate().getHours() &&
                     sessionPlanificarEva.getHoraPartidaInicio().getMinutes() <
                     listaEvaluaciones.get(i).getStartDate().getMinutes() ||
                     sessionPlanificarEva.getHoraPartidaInicio().getHours() <
                     listaEvaluaciones.get(i).getStartDate().getHours()) &&
                    (sessionPlanificarEva.getFechaFinEvaluacion().getHours() ==
                     listaEvaluaciones.get(i).getEndDate().getHours() &&
                     sessionPlanificarEva.getFechaFinEvaluacion().getMinutes() <
                     listaEvaluaciones.get(i).getEndDate().getMinutes() ||
                     sessionPlanificarEva.getFechaFinEvaluacion().getHours() <
                     listaEvaluaciones.get(i).getEndDate().getHours()) &&
                    (sessionPlanificarEva.getFechaFinEvaluacion().getHours() ==
                     listaEvaluaciones.get(i).getStartDate().getHours() &&
                     sessionPlanificarEva.getFechaFinEvaluacion().getMinutes() >
                     listaEvaluaciones.get(i).getStartDate().getMinutes() ||
                     sessionPlanificarEva.getFechaFinEvaluacion().getHours() >
                     listaEvaluaciones.get(i).getStartDate().getHours()))

                {
                    sessionPlanificarEva.setEstadoBtnBloq2(true);
                    sessionPlanificarEva.setEstadoOut2(true);
                }
                if ((sessionPlanificarEva.getHoraPartidaInicio().getHours() ==
                     listaEvaluaciones.get(i).getStartDate().getHours() &&
                     sessionPlanificarEva.getHoraPartidaInicio().getMinutes() <
                     listaEvaluaciones.get(i).getStartDate().getMinutes() ||
                     sessionPlanificarEva.getHoraPartidaInicio().getHours() <
                     listaEvaluaciones.get(i).getStartDate().getHours()) &&
                    sessionPlanificarEva.getFechaFinEvaluacion().getHours() ==
                    listaEvaluaciones.get(i).getEndDate().getHours() &&
                    sessionPlanificarEva.getFechaFinEvaluacion().getMinutes() ==
                    listaEvaluaciones.get(i).getEndDate().getMinutes())
                {
                    sessionPlanificarEva.setEstadoBtnBloq2(true);
                    sessionPlanificarEva.setEstadoOut2(true);
                }
                if ((sessionPlanificarEva.getHoraPartidaInicio().getHours() ==
                     listaEvaluaciones.get(i).getStartDate().getHours() &&
                     sessionPlanificarEva.getHoraPartidaInicio().getMinutes() <
                     listaEvaluaciones.get(i).getStartDate().getMinutes() ||
                     sessionPlanificarEva.getHoraPartidaInicio().getHours() <
                     listaEvaluaciones.get(i).getStartDate().getHours()) &&
                    (sessionPlanificarEva.getFechaFinEvaluacion().getHours() ==
                     listaEvaluaciones.get(i).getEndDate().getHours() &&
                     sessionPlanificarEva.getFechaFinEvaluacion().getMinutes() >
                     listaEvaluaciones.get(i).getEndDate().getMinutes() ||
                     sessionPlanificarEva.getFechaFinEvaluacion().getHours() >
                     listaEvaluaciones.get(i).getEndDate().getHours()))
                {
                    sessionPlanificarEva.setEstadoBtnBloq2(true);
                    sessionPlanificarEva.setEstadoOut2(true);
                }
            }
        }
        sessionPlanificarEva.setListatipoVisita(Utils.llenarComboString(ln_C_SFUtilsRemote.getTipoVisitaFromConstraint()));
        sessionPlanificarEva.setValorTipoVisita("OP");
        Utils.showPopUpMIDDLE(popupSeleccionBloque);
        return null;
    }

    public void loadactivityStyles() {
        try {
            HashSet setconf = new HashSet<String>();
            HashSet setnoconf = new HashSet<String>();
            HashSet setadm = new HashSet<String>();
            HashSet setnojust = new HashSet<String>();
            HashSet porJusti = new HashSet<String>();
            HashSet Justi = new HashSet<String>();
            setconf.add("EJECUTADO");
            setnoconf.add("PENDIENTE");
            setadm.add("NO EJECUTADO");
            setnojust.add("INJUSTIFICADO");
            porJusti.add("POR JUSTIFICAR");
            Justi.add("JUSTIFICADO");

            activityStyles.put(setconf, CalendarActivityRamp.getActivityRamp(CalendarActivityRamp.RampKey.GREEN));
            activityStyles.put(setnoconf, CalendarActivityRamp.getActivityRamp(CalendarActivityRamp.RampKey.BLUE));
            activityStyles.put(setadm, CalendarActivityRamp.getActivityRamp(CalendarActivityRamp.RampKey.ORANGE));
            activityStyles.put(setnojust, CalendarActivityRamp.getActivityRamp(CalendarActivityRamp.RampKey.RED));
            activityStyles.put(Justi, CalendarActivityRamp.getActivityRamp(CalendarActivityRamp.RampKey.MIDNIGHTBLUE));
            activityStyles.put(porJusti, CalendarActivityRamp.getActivityRamp(CalendarActivityRamp.RampKey.LAVENDAR));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    //TODO que hacen estos metodos vacios??????
    public void activarBotonEvaluar(AttributeChangeEvent attributeChangeEvent) {
    }

    public void activarbtnEvaluar(ValueChangeEvent valueChangeEvent) {
    }

    public void getAulasBysede(ValueChangeEvent valueChangeEvent) {//TODO de nuevo estas llamando al componente, debes llamar a la variable o usar el valueChangeEvent
        sessionPlanificarEva.setListaNiveles(Utils.llenarCombo(ln_C_SFNivelRemote.getAllNivelesBySedes(choiceSede.getValue().toString())));
        sessionPlanificarEva.setEstadoChoiceTemporalNivel(false);
        Utils.addTarget(choiceNivel);
    }

    public void getNivelesBySede(ValueChangeEvent valueChangeEvent) {
        if (sessionPlanificarEva.getIsSupervisor() == 1) {
            sessionPlanificarEva.setEstadoDisableChoiceArea(false);
            Utils.addTarget(choiceAreaAcademicas);
        }
        if (sessionPlanificarEva.getNidAreaAcademicaChoice() != null) {//TODO igual estas llamando a los componentes!!!
            sessionPlanificarEva.setListaProfesores(Utils.llenarComboString(ln_C_SFProfesorRemote.getPRofesorPorSedeYNivel(choiceSede.getValue().toString(),
                                                                                                                           choiceNivel.getValue().toString(),
                                                                                                                           Integer.parseInt(sessionPlanificarEva.getNidAreaAcademicaChoice()))));
        } else {
            sessionPlanificarEva.setListaProfesores(Utils.llenarComboString(ln_C_SFProfesorRemote.getPRofesorPorSedeYNivel(choiceSede.getValue().toString(),
                                                                                                                           choiceNivel.getValue().toString(),
                                                                                                                           0)));
        }
        sessionPlanificarEva.setEstadoChoiceTemporalDocente(false);
        Utils.addTarget(choiceProfesores);
    }

    public void getCursosByProfesor(ValueChangeEvent valueChangeEvent) {
        if (sessionPlanificarEva.getNidAreaAcademicaChoice() != null) {//TODO igual estas llamando a los componentes!!!          
            sessionPlanificarEva.setListaCursos(Utils.llenarCombo(ln_C_SFCursoRemoto.getCursoPorSedeNivelyPofesor(choiceSede.getValue().toString(),
                                                                                                                  choiceNivel.getValue().toString(),
                                                                                                                  choiceProfesores.getValue().toString(),
                                                                                                                  Integer.parseInt(sessionPlanificarEva.getNidAreaAcademicaChoice()))));
        } else {
            sessionPlanificarEva.setListaCursos(Utils.llenarCombo(ln_C_SFCursoRemoto.getCursoPorSedeNivelyPofesor(choiceSede.getValue().toString(),
                                                                                                                  choiceNivel.getValue().toString(),
                                                                                                                  choiceProfesores.getValue().toString(),
                                                                                                                  0)));

        }
        sessionPlanificarEva.setEstadoChoiceTemporalCurso(false);
        Utils.addTarget(choiceCursos);
    }

    public void getAulasByCurso(ValueChangeEvent valueChangeEvent) {//TODO igual estas llamando a los componentes!!!
        if (sessionPlanificarEva.getNidAreaAcademicaChoice() != null) {
            sessionPlanificarEva.setListaAulasTemporal(Utils.llenarCombo(ln_C_SFAulaRemote.getAulaPorSedeNivelProfesorYCurso(choiceSede.getValue().toString(),
                                                                                                                             choiceNivel.getValue().toString(),
                                                                                                                             choiceProfesores.getValue().toString(),
                                                                                                                             Integer.parseInt(sessionPlanificarEva.getNidAreaAcademicaChoice()),
                                                                                                                             choiceCursos.getValue().toString())));
        } else {
            sessionPlanificarEva.setListaAulasTemporal(Utils.llenarCombo(ln_C_SFAulaRemote.getAulaPorSedeNivelProfesorYCurso(choiceSede.getValue().toString(),
                                                                                                                             choiceNivel.getValue().toString(),
                                                                                                                             choiceProfesores.getValue().toString(),
                                                                                                                             0,
                                                                                                                             choiceCursos.getValue().toString())));

        }
        sessionPlanificarEva.setEstadoChoiceTemporalAula(false);
        Utils.addTarget(choiceAula);
    }
    
    /**Temporal*/
    public String agregarEvaluacionYMainProvicional() {
        Main main = ln_C_SFMainRemote.getMainPorSedeNivelYCurso(sessionPlanificarEva.getNidAulaTemporal(),
                                                                sessionPlanificarEva.getNidCurso(),
                                                                sessionPlanificarEva.getDniProfesor());
        sessionPlanificarEva.getListaPlanificacionesExistentes().clear();
        sessionPlanificarEva.setNidMainPlanificacion(main.getNidMain());
        List<BeanEvaluacion> lstEvas = ln_C_SFEvaluacionRemoto.getEvaluacionesEnRango(Utils.removeTime(sessionPlanificarEva.getFechaYhoraInicialTemporal()),
                                                                                      main.getNidMain());
        int num = 0;
        if (lstEvas.size() > 0) {
            for (int i = 0; i < lstEvas.size(); i++) {//TODO un IF tan grande pasalo a un metodo booleano
                if ((lstEvas.get(i).getStartDate().after(sessionPlanificarEva.getFechaYhoraInicialTemporal()) &&
                     lstEvas.get(i).getStartDate().before(sessionPlanificarEva.getFechaYhoraFinTemporal())) ||
                    (lstEvas.get(i).getEndDate().after(sessionPlanificarEva.getFechaYhoraInicialTemporal()) &&
                     lstEvas.get(i).getEndDate().before(sessionPlanificarEva.getFechaYhoraFinTemporal())) ||
                    (lstEvas.get(i).getStartDate().before(sessionPlanificarEva.getFechaYhoraInicialTemporal()) &&
                     lstEvas.get(i).getEndDate().after(sessionPlanificarEva.getFechaYhoraFinTemporal())) ||
                    (lstEvas.get(i).getStartDate() == sessionPlanificarEva.getFechaYhoraInicialTemporal()) &&
                    lstEvas.get(i).getEndDate() == sessionPlanificarEva.getFechaYhoraFinTemporal()) {
                    lstEvas.get(i).setNombreEvaluador(ln_C_SFUsuarioRemote.getNombresUsuarioByNidUsuario_LN(lstEvas.get(i).getNidEvaluador()));
                    sessionPlanificarEva.getListaPlanificacionesExistentes().add(lstEvas.get(i));
                    num = 1;
                }
            }
        }
        if (num == 0) {
            guardarPlanificacion();
        } else {
            Utils.showPopUpMIDDLE(popupExisteEvaluacion);
        }
        return null;
    }
  
    /**Temporal*/ 
  public String guardarPlanificacion(){//TODO dale un nombre entendible a las variable s, c  // NUEVO NOMBRE STARDATE Y ENDDATE
  if(Time.valueOf(sessionPlanificarEva.getHoraFin()+":00").before(Time.valueOf(sessionPlanificarEva.getHoraInicio()+":00")) ){
      Utils.mostrarMensaje(ctx,"Hora Fin de Evaluacion no puedo ser antes de la Hora inicio, porfavor ingrese datos correctos","Error",1);
  }else{  
      sessionPlanificarEva.getFechaYhoraInicialTemporal().clone();  
      sessionPlanificarEva.getFechaYhoraInicialTemporal().setHours(Integer.parseInt(sessionPlanificarEva.getHoraInicio().charAt(0)+""+sessionPlanificarEva.getHoraInicio().charAt(1)));
      sessionPlanificarEva.getFechaYhoraInicialTemporal().setMinutes(Integer.parseInt(sessionPlanificarEva.getHoraInicio().charAt(3)+""+sessionPlanificarEva.getHoraInicio().charAt(4)));
      long startDate = sessionPlanificarEva.getFechaYhoraInicialTemporal().getTime();
      sessionPlanificarEva.getFechaYhoraFinTemporal().clone();
      sessionPlanificarEva.getFechaYhoraFinTemporal().setHours(Integer.parseInt(sessionPlanificarEva.getHoraFin().charAt(0)+""+sessionPlanificarEva.getHoraFin().charAt(1)));
      sessionPlanificarEva.getFechaYhoraFinTemporal().setMinutes(Integer.parseInt(sessionPlanificarEva.getHoraFin().charAt(3)+""+sessionPlanificarEva.getHoraFin().charAt(4)));
      long endDate = sessionPlanificarEva.getFechaYhoraFinTemporal().getTime();  
      String nidDat = Utils.generarAlfanumerico();      
      ln_T_SFEvaluacionRemote.registrarEvaluacion_LN(startDate, 
                                                     endDate,
                                                     sessionPlanificarEva.getNidMainPlanificacion(), 
                                                     Integer.parseInt(getSessionPlanificarEva().getNidUsuario()),
                                                     nidDat, 
                                                     sessionPlanificarEva.getNidPlanificador(), 
                                                     sessionPlanificarEva.getValorTipoVisita());      
      Utils.invokeEL("#{bindings.ExecuteWithParams.execute}");
      Utils.addTarget(calendar);
      popupEvento2.hide();    
      }  
      return null;
  }

    public void eventoChoiceArea(ValueChangeEvent valueChangeEvent) {//TODO ESTAS llamando al componente!
        if (valueChangeEvent.getNewValue().toString() != null) {
            sessionPlanificarEva.setListaProfesores(Utils.llenarComboString(ln_C_SFProfesorRemote.getPRofesorPorSedeYNivel(choiceSede.getValue().toString(),
                                                                                                                           choiceNivel.getValue().toString(),
                                                                                                                           Integer.parseInt(valueChangeEvent.getNewValue().toString()))));
        } else {
            sessionPlanificarEva.setListaProfesores(Utils.llenarComboString(ln_C_SFProfesorRemote.getPRofesorPorSedeYNivel(choiceSede.getValue().toString(),
                                                                                                                           choiceNivel.getValue().toString(),
                                                                                                                           0)));
        }
        sessionPlanificarEva.setEstadoChoiceTemporalDocente(false);
        Utils.addTarget(choiceProfesores);
    }

    public void setMsjErrorJustif(String msjErrorJustif) {
        this.msjErrorJustif = msjErrorJustif;
    }

    public String getMsjErrorJustif() {
        return msjErrorJustif;
    }

    public void cancelarPlanificacion(ActionEvent actionEvent) {
        popupExisteEvaluacion.hide();
    }

    public void confirmarPlanificacion(ActionEvent actionEvent) {
        guardarPlanificacion();
    }
  
    public List<SelectItem> suggestNombreProfesor(String string) {
        return Utils.getSuggestions(sessionPlanificarEva.getItemNombreProferos(), string);
    }

    public void setHoraInicioTemporal(RichInputDate horaInicioTemporal) {
        this.horaInicioTemporal = horaInicioTemporal;
    }

    public RichInputDate getHoraInicioTemporal() {
        return horaInicioTemporal;
    }

    public void setHoraFinTemporal(RichInputDate horaFinTemporal) {
        this.horaFinTemporal = horaFinTemporal;
    }

    public RichInputDate getHoraFinTemporal() {
        return horaFinTemporal;
    }


    public void setOutDatosEva2(HtmlOutputText outDatosEva2) {
        this.outDatosEva2 = outDatosEva2;
    }

    public HtmlOutputText getOutDatosEva2() {
        return outDatosEva2;
    }

    public void setPl1(RichPanelGroupLayout pl1) {
        this.pl1 = pl1;
    }

    public RichPanelGroupLayout getPl1() {
        return pl1;
    }
    
    public void setTbEvaluadores(RichTable tbEvaluadores) {
        this.tbEvaluadores = tbEvaluadores;
    }

    public RichTable getTbEvaluadores() {
        return tbEvaluadores;
    }

    public void setNidAreaAcademicaFiltro(String nidAreaAcademicaFiltro) {
        this.nidAreaAcademicaFiltro = nidAreaAcademicaFiltro;
    }

    public String getNidAreaAcademicaFiltro() {
        return nidAreaAcademicaFiltro;
    }

    public void setChoiceFiltArea(RichSelectOneChoice choiceFiltArea) {
        this.choiceFiltArea = choiceFiltArea;
    }

    public RichSelectOneChoice getChoiceFiltArea() {
        return choiceFiltArea;
    }

    public void setChoiceSede(RichSelectOneChoice choiceSede) {
        this.choiceSede = choiceSede;
    }

    public RichSelectOneChoice getChoiceSede() {
        return choiceSede;
    }

    public void setChoiceNivel(RichSelectOneChoice choiceNivel) {
        this.choiceNivel = choiceNivel;
    }

    public RichSelectOneChoice getChoiceNivel() {
        return choiceNivel;
    }

    public void setChoiceGrado(RichSelectOneChoice choiceGrado) {
        this.choiceGrado = choiceGrado;
    }

    public RichSelectOneChoice getChoiceGrado() {
        return choiceGrado;
    }

    public void setPopupSeleccionBloque(RichPopup popupSeleccionBloque) {
        this.popupSeleccionBloque = popupSeleccionBloque;
    }

    public RichPopup getPopupSeleccionBloque() {
        return popupSeleccionBloque;
    }
    
    public void setCalendar(RichCalendar calendar) {
        this.calendar = calendar;
    }

    public RichCalendar getCalendar() {
        return calendar;
    }


    public void setPopupEvento(RichPopup popupEvento) {
        this.popupEvento = popupEvento;
    }

    public RichPopup getPopupEvento() {
        return popupEvento;
    }

    public void setTbHorario(RichTable tbHorario) {
        this.tbHorario = tbHorario;
    }

    public RichTable getTbHorario() {
        return tbHorario;
    }

    public void setEvaluadores(List evaluadores) {
        this.evaluadores = evaluadores;
    }

    public List getEvaluadores() {
        return evaluadores;
    }

    public void setChoiceEvaluadores(RichSelectOneChoice choiceEvaluadores) {
        this.choiceEvaluadores = choiceEvaluadores;
    }

    public RichSelectOneChoice getChoiceEvaluadores() {
        return choiceEvaluadores;
    }

    public void setChoiceProfesores(RichSelectOneChoice choiceProfesores) {
        this.choiceProfesores = choiceProfesores;
    }

    public RichSelectOneChoice getChoiceProfesores() {
        return choiceProfesores;
    }

    public void setItemsEvaluadores(UISelectItems itemsEvaluadores) {
        this.itemsEvaluadores = itemsEvaluadores;
    }

    public UISelectItems getItemsEvaluadores() {
        return itemsEvaluadores;
    }


    public void setBtnEvaluar(RichCommandButton btnEvaluar) {
        this.btnEvaluar = btnEvaluar;
    }

    public RichCommandButton getBtnEvaluar() {
        return btnEvaluar;
    }

    public void setSessionPlanificarEva(sessionPlanificar sessionPlanificarEva) {
        this.sessionPlanificarEva = sessionPlanificarEva;
    }

    public sessionPlanificar getSessionPlanificarEva() {
        return sessionPlanificarEva;
    }

    public void setPopupDetalleEva(RichPopup popupDetalleEva) {
        this.popupDetalleEva = popupDetalleEva;
    }

    public RichPopup getPopupDetalleEva() {
        return popupDetalleEva;
    }

    public void setChoiceCursos(RichSelectOneChoice choiceCursos) {
        this.choiceCursos = choiceCursos;
    }

    public RichSelectOneChoice getChoiceCursos() {
        return choiceCursos;
    }

    public void setPopupEvalua(RichPopup popupEvalua) {
        this.popupEvalua = popupEvalua;
    }

    public RichPopup getPopupEvalua() {
        return popupEvalua;
    }

    public void setOutDatosEva(HtmlOutputText outDatosEva) {
        this.outDatosEva = outDatosEva;
    }

    public HtmlOutputText getOutDatosEva() {
        return outDatosEva;
    }

    public void setPopupEliminarEvalu(RichPopup popupEliminarEvalu) {
        this.popupEliminarEvalu = popupEliminarEvalu;
    }

    public RichPopup getPopupEliminarEvalu() {
        return popupEliminarEvalu;
    }

    public void setBtnAsignarEva(RichButton btnAsignarEva) {
        this.btnAsignarEva = btnAsignarEva;
    }

    public RichButton getBtnAsignarEva() {
        return btnAsignarEva;
    }
    
    public void setBtnBloque1(RichButton btnBloque1) {
        this.btnBloque1 = btnBloque1;
    }

    public RichButton getBtnBloque1() {
        return btnBloque1;
    }

    public void setBtnBloque2(RichButton btnBloque2) {
        this.btnBloque2 = btnBloque2;
    }

    public RichButton getBtnBloque2() {
        return btnBloque2;
    }

    public void setChoiceTipoVisita(RichSelectOneChoice choiceTipoVisita) {
        this.choiceTipoVisita = choiceTipoVisita;
    }

    public RichSelectOneChoice getChoiceTipoVisita() {
        return choiceTipoVisita;
    }

    public void setChoiceAreaAcademicas(RichSelectOneChoice choiceAreaAcademicas) {
        this.choiceAreaAcademicas = choiceAreaAcademicas;
    }

    public RichSelectOneChoice getChoiceAreaAcademicas() {
        return choiceAreaAcademicas;
    }

    public void setPanelBoxComentYSug(RichPanelBox panelBoxComentYSug) {
        this.panelBoxComentYSug = panelBoxComentYSug;
    }

    public RichPanelBox getPanelBoxComentYSug() {
        return panelBoxComentYSug;
    }

    public void setPanelBoxJusticacion(RichPanelBox panelBoxJusticacion) {
        this.panelBoxJusticacion = panelBoxJusticacion;
    }

    public RichPanelBox getPanelBoxJusticacion() {
        return panelBoxJusticacion;
    }

    public void setChoiceProblema(RichSelectOneChoice choiceProblema) {
        this.choiceProblema = choiceProblema;
    }

    public RichSelectOneChoice getChoiceProblema() {
        return choiceProblema;
    }

    public void setInputDescripcionOtros(RichInputText inputDescripcionOtros) {
        this.inputDescripcionOtros = inputDescripcionOtros;
    }

    public RichInputText getInputDescripcionOtros() {
        return inputDescripcionOtros;
    }

    public void setInputComentarioEvaluador(RichInputText inputComentarioEvaluador) {
        this.inputComentarioEvaluador = inputComentarioEvaluador;
    }

    public RichInputText getInputComentarioEvaluador() {
        return inputComentarioEvaluador;
    }

    public void setInputComentarioProfesor(RichInputText inputComentarioProfesor) {
        this.inputComentarioProfesor = inputComentarioProfesor;
    }

    public RichInputText getInputComentarioProfesor() {
        return inputComentarioProfesor;
    }

    public void setBtnSaveComentEvalu(RichButton btnSaveComentEvalu) {
        this.btnSaveComentEvalu = btnSaveComentEvalu;
    }

    public RichButton getBtnSaveComentEvalu() {
        return btnSaveComentEvalu;
    }

    public void setBtnSaveJustificacion(RichButton btnSaveJustificacion) {
        this.btnSaveJustificacion = btnSaveJustificacion;
    }

    public RichButton getBtnSaveJustificacion() {
        return btnSaveJustificacion;
    }
           
    public void setActivityStyles(HashMap activityStyles) {
        this.activityStyles = activityStyles;
    }

    public HashMap getActivityStyles() {
        return activityStyles;
    }

    public void setPopupEvento2(RichPopup popupEvento2) {
        this.popupEvento2 = popupEvento2;
    }

    public RichPopup getPopupEvento2() {
        return popupEvento2;
    }

    public void setChoiceAula(RichSelectOneChoice choiceAula) {
        this.choiceAula = choiceAula;
    }

    public RichSelectOneChoice getChoiceAula() {
        return choiceAula;
    }

    public void setPopupExisteEvaluacion(RichPopup popupExisteEvaluacion) {
        this.popupExisteEvaluacion = popupExisteEvaluacion;
    }

    public RichPopup getPopupExisteEvaluacion() {
        return popupExisteEvaluacion;
    }

    public void setErrJustif(RichActiveOutputText errJustif) {
        this.errJustif = errJustif;
    }

    public RichActiveOutputText getErrJustif() {
        return errJustif;
    }

    public void setInputHoraFin(RichInputText inputHoraFin) {
        this.inputHoraFin = inputHoraFin;
    }

    public RichInputText getInputHoraFin() {
        return inputHoraFin;
    }

    public void setInputHoraInicio(RichInputText inputHoraInicio) {
        this.inputHoraInicio = inputHoraInicio;
    }

    public RichInputText getInputHoraInicio() {
        return inputHoraInicio;
    }
}