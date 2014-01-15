package sped.vista.beans.evaluacion.planificar;


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

import java.util.Iterator;

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
import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.data.RichCalendar;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.input.RichSelectOneChoice;
import oracle.adf.view.rich.component.rich.nav.RichCommandButton;
import oracle.adf.view.rich.event.CalendarActivityEvent;
import oracle.adf.view.rich.event.CalendarEvent;
import oracle.adf.view.rich.model.CalendarActivity;


import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

import oracle.jbo.ViewObject;
import oracle.jbo.server.ViewObjectImpl;
import oracle.jbo.uicli.binding.JUCtrlActionBinding;

import org.apache.myfaces.trinidad.event.SelectionEvent;

import sped.negocio.BDL.IR.BDL_C_SFAreaAcademicaRemote;
import sped.negocio.BDL.IR.BDL_C_SFEvaluacionRemoto;
import sped.negocio.BDL.IR.BDL_C_SFMainRemote;
import sped.negocio.BDL.IR.BDL_C_SFUsuarioRemote;
import sped.negocio.BDL.IR.BDL_T_SFEvaluacionRemoto;
import sped.negocio.BDL.IR.BDL_T_SFUsuarioRemote;
import sped.negocio.LNSF.IR.LN_C_SFCursoRemoto;
import sped.negocio.LNSF.IR.LN_C_SFMainRemote;
import sped.negocio.LNSF.IR.LN_C_SFUsuarioRemote;
import sped.negocio.entidades.admin.Curso;
import sped.negocio.entidades.admin.Main;
import sped.negocio.entidades.admin.Profesor;
import sped.negocio.entidades.admin.Usuario;
import sped.negocio.entidades.beans.BeanAreaAcademica;
import sped.negocio.entidades.beans.BeanCurso;
import sped.negocio.entidades.beans.BeanMain;
import sped.negocio.entidades.beans.BeanProfesor;
import sped.negocio.entidades.beans.BeanUsuario;
import sped.negocio.entidades.eval.Evaluacion;


import sped.vista.Utils.Utils;
import utils.system;


/** Clase de Respaldo bPlanificar.java
 * @author czavalacas
 * @since 29.12.2013
 */
public class bPlanificarEva {

    @EJB
    private LN_C_SFMainRemote ln_C_SFMainRemote;
    @EJB
    private LN_C_SFUsuarioRemote ln_C_SFUsuarioRemote;
    @EJB
    private BDL_C_SFEvaluacionRemoto bdl_C_SFEvaluacionRemoto;
    private final static String LOOKUP_NAME_SFEVALUADORES_REMOTO =
        "mapLN_C_SFUsuario#sped.negocio.LNSF.IR.LN_C_SFUsuarioRemote";
    @EJB
    private BDL_C_SFMainRemote bdl_C_SFMainRemote;
    @EJB
    private BDL_C_SFAreaAcademicaRemote bdl_C_SFAreaAcademicaRemote;
    @EJB
    private BDL_T_SFEvaluacionRemoto bdl_T_SFEvaluacionRemoto;
    @EJB
    private LN_C_SFCursoRemoto ln_C_SFCursoRemoto;
    @EJB
    private BDL_C_SFUsuarioRemote bdl_C_SFUsuarioRemote;
    FacesContext ctx = FacesContext.getCurrentInstance();
    private sessionPlanificar sessionPlanificarEva;
    private RichCalendar calendar;
    private RichPopup popupEvento;
    private RichTable tbHorario;
    private List evaluadores;
    private RichSelectOneChoice choiceEvaluadores;
    private RichSelectOneChoice choiceProfesores;
    private RichCommandButton btnBuscar;
    private UISelectItems itemsEvaluadores;
    private RichCommandButton btnEvaluar;
    private RichCommandButton btnAsignarEva;
    private RichPopup popupDetalleEva;
    private RichSelectOneChoice choiceCursos;
    private BeanUsuario beanUsuario=new BeanUsuario();
    private RichPopup popupEvalua;
    private HtmlOutputText outDatosEva;
    private RichPopup popupEliminarEvalu;


    public bPlanificarEva() {
        try {
            final Context ctx;
            ctx = new InitialContext();
            ln_C_SFUsuarioRemote = (LN_C_SFUsuarioRemote) ctx.lookup(LOOKUP_NAME_SFEVALUADORES_REMOTO);
            this.setEvaluadores(this.llenarEvaluadores());   
        } catch (Exception e) {
            e.printStackTrace();
        }
    } 
    @PostConstruct
    public void methodInvokeOncedOnPageLoad(){
        beanUsuario = (BeanUsuario) Utils.getSession("USER");
         sessionPlanificarEva.setNidPlanificador(beanUsuario.getNidUsuario());
         sessionPlanificarEva.setNidRol(beanUsuario.getRol().getNidRol());
        if(beanUsuario.getRol().getNidRol()==1){
            sessionPlanificarEva.setEstadoChoiceEvaluadores(false);  
            sessionPlanificarEva.setNidUsuario(beanUsuario.getNidUsuario().toString());
        }else{
            sessionPlanificarEva.setEstadoChoiceEvaluadores(true);  
        }
    }
    
    public String popupEvaluadores(){
        sessionPlanificarEva.setListBeanUsua(ln_C_SFUsuarioRemote.getEvaluadores());  
        Utils.showPopUpMIDDLE(popupEvalua);
        return null;
    }
 
    public ArrayList llenarEvaluadores() {
        ArrayList unItems = new ArrayList();
        List<BeanUsuario> roles = ln_C_SFUsuarioRemote.getEvaluadores();
        for (BeanUsuario r : roles) {
            r.setAreaAcYProf(r.getAreaAcademica().getDescripcionAreaAcademica() + " - " + r.getNombres());
            unItems.add(new SelectItem(r.getNidUsuario().toString(),
                    r.getNombres().toString()));
        }
        return unItems;
    }
    
    public ArrayList llenarCursos() {
        ArrayList unItems = new ArrayList();
        List<BeanCurso> roles =  ln_C_SFCursoRemoto.getlistaCursos();
        for (BeanCurso r : roles) {
            //r.setAreaAcYProf(r.getAreaAcademica().getDescripcionAreaAcademica() + " - " + r.getNombres());
            unItems.add(new SelectItem(r.getNidCurso().toString(),               
                    r.getDescripcionCurso().toString()));
        }
        return unItems;
    }
   
   
    public ArrayList llenarProfesores(Integer nidAreaAcademica) {
        ArrayList unItems = new ArrayList();
        List<Profesor> roles =
            bdl_C_SFMainRemote.findProfesoresPorAreaAcademica(sessionPlanificarEva.getNidAreaAcademica(),
                                                              sessionPlanificarEva.getDiaDeLaSemana());
        for (Profesor r : roles) {
            BeanProfesor bean = new BeanProfesor();
            bean.setNombreCompleto(r.getApellidos() + " " + r.getNombres());
            unItems.add(new SelectItem(r.getDniProfesor().toString(), bean.getNombreCompleto().toString()));
        }
        return unItems;
    }  

    public String grabarEvaluacion() {
        Evaluacion eva = new Evaluacion();
        long s = sessionPlanificarEva.getFechaInicioEvaluacion().getTime();
        eva.setStartDate(new Timestamp(s));
        long c = sessionPlanificarEva.getFechaFinEvaluacion().getTime();
        eva.setEndDate(new Timestamp(c));
        Main main = new Main();
        main.setNidMain(sessionPlanificarEva.getBeanHorario().getNidMain());
        eva.setMain(main);
        eva.setNidEvaluador(Integer.parseInt(getSessionPlanificarEva().getNidUsuario()));
        eva.setDescripcion("HOLA QUE HACE");
        eva.setEstadoEvaluacion("PENDIENTE");
        String nidDat = generarAlfanumerico();
        eva.setNidDate(nidDat);
        eva.setNidPlanificador(sessionPlanificarEva.getNidPlanificador());
        bdl_T_SFEvaluacionRemoto.persistEvaluacion(eva);     
        llenarBean();
        if (tbHorario != null) {
            Utils.unselectFilas(tbHorario);
            tbHorario.setValue(sessionPlanificarEva.getListaHorarios());
            Utils.addTarget(tbHorario);
        } 
        Utils.invokeEL("#{bindings.ExecuteWithParams.execute}"); 
        Utils.addTarget(calendar);
        return null;
    }
    
    public static void setEL(String el, Object val) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ELContext elContext = facesContext.getELContext();
        ExpressionFactory expressionFactory = facesContext.getApplication().getExpressionFactory();
        ValueExpression exp = expressionFactory.createValueExpression(elContext, el, Object.class);
        exp.setValue(elContext, val);
    }

    public void abrirPopupEvaluar(CalendarActivityEvent calendarActivityEvent) {
        CalendarActivity activity = calendarActivityEvent.getCalendarActivity();
        Evaluacion entida = bdl_C_SFEvaluacionRemoto.getEvaluacionById(activity.getId());
        sessionPlanificarEva.setFechaEvaluacionPopup(entida.getStartDate());
        sessionPlanificarEva.setHoraEvaluacionPopup(entida.getStartDate());
        sessionPlanificarEva.setSedeEvaluacion(entida.getMain().getAula().getSede().getDescripcionSede());
        sessionPlanificarEva.setAulaEvaluacion(entida.getMain().getAula().getDescripcionAula());
        sessionPlanificarEva.setCursoEvaluacion(entida.getMain().getCurso().getDescripcionCurso());
        sessionPlanificarEva.setGradoEvaluacion(entida.getMain().getAula().getGradoNivel().getGrado().getDescripcionGrado());
        sessionPlanificarEva.setNivelEvaluacion(entida.getMain().getAula().getGradoNivel().getNivel().getDescripcionNivel());
        sessionPlanificarEva.setDocenteEvaluacion(entida.getMain().getProfesor().getApellidos() + " " +
                                                  entida.getMain().getProfesor().getNombres());
        sessionPlanificarEva.setDniDocenteEvaluacion(entida.getMain().getProfesor().getDniProfesor());
        sessionPlanificarEva.setNidEvaluacionDelet(entida.getNidEvaluacion());
        
        Date fechaHoy=new Date();
        if(sessionPlanificarEva.getFechaEvaluacionPopup().before(fechaHoy)){
            sessionPlanificarEva.setEstadoBotonEliminarEvaluacion(false);
        }else{
            if(entida.getNidPlanificador()!=sessionPlanificarEva.getNidPlanificador() && sessionPlanificarEva.getNidRol()==1){
                 sessionPlanificarEva.setEstadoBotonEliminarEvaluacion(false);
            }else{
                sessionPlanificarEva.setEstadoBotonEliminarEvaluacion(true);
    }
        }       
        Utils.showPopUpMIDDLE(popupDetalleEva);              
    }

    public String getDiaDeCalendario(int dia) {
        String day = "";
        if (dia == 1) {
            day = "Lunes";
        }
        if (dia == 2) {
            day = "Martes";
        }
        if (dia == 3) {
            day = "Miercoles";
        }
        if (dia == 4) {
            day = "Jueves";
        }
        if (dia == 5) {
            day = "Viernes";
        }
        if (dia == 6) {
            day = "Sabado";
        }
        if (dia == 0) {
            day = "Domingo";
        }
        return day;
    }
   
    public String llenarHorarios(BeanMain beanMain) {
        List<BeanMain> lis = ln_C_SFMainRemote.llenarHorario(beanMain);        
        if (lis != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date fechaActual = sessionPlanificarEva.getFechaInicioSeleccionada();
            String fechaConFormato = sdf.format(fechaActual);
            List<Evaluacion> listaEvaluaciones =bdl_C_SFEvaluacionRemoto.getEvaluaciones(fechaConFormato, sessionPlanificarEva.getNidAreaAcademica(), Integer.parseInt(sessionPlanificarEva.getNidUsuario()));
            if(listaEvaluaciones.size()==lis.size()){
                lis.clear();
            }else{
                Date fechaMaster = sessionPlanificarEva.getFechaInicioSeleccionada();
                Date horaInicio = (Date) fechaMaster.clone();                           
                if (lis.size() != 0) {
                     for (int i = 0; i < lis.size(); i++) {
                     /*   horaInicio.setHours(lis.get(i).getHoraInicio().getHours()); 
                        horaInicio.setMinutes(lis.get(i).getHoraInicio().getMinutes()); 
                        Date FechaHoy=new Date();
                         System.out.println(" ;;; " +horaInicio+" :: "+FechaHoy);
                         if(horaInicio.before(FechaHoy)){
                             lis.remove(i);
                         }*/
                         
                        if (listaEvaluaciones.size() != 0) {
                            for (int j = 0; j < listaEvaluaciones.size(); j++) {                           
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
    
    public void llenarBean(){
        BeanMain beanMain = new BeanMain();
        BeanAreaAcademica beanAca = new BeanAreaAcademica();
        beanAca.setNidAreaAcademica(sessionPlanificarEva.getNidAreaAcademica());        
        BeanCurso beanCurso = new BeanCurso();
        beanCurso.setAreaAcademica(beanAca);
        if(sessionPlanificarEva.getNidCurso()!=null){
        beanCurso.setNidCurso(Integer.parseInt(sessionPlanificarEva.getNidCurso()));     }  
        BeanProfesor beanProf = new BeanProfesor();
        if (sessionPlanificarEva.getDniProfesor() != null) {
            beanProf.setDniProfesor(sessionPlanificarEva.getDniProfesor().toString());
            beanMain.setProfesor(beanProf);
        }   
        beanMain.setCurso(beanCurso);
        beanMain.setDia(sessionPlanificarEva.getDiaDeLaSemana());
        llenarHorarios(beanMain);
    }
    
    public void abrirNuevoEvento(CalendarEvent calendarEvent) {
        Date fechaHoy=new Date();
        Date fecha=(Date) fechaHoy.clone();           
        fecha.setHours(0);
        fecha.setMinutes(0);
        fecha.setSeconds(0);
        Date fechaCalen=calendarEvent.getTriggerDate();
        Date fecha2=(Date) fechaCalen.clone();
        fecha2.setSeconds(01);         
        if(fecha2.after(fecha)){
        if(sessionPlanificarEva.getNidUsuario()!=null){            
            sessionPlanificarEva.setListaProfesores(null);
            sessionPlanificarEva.setListaHorarios(null);
            sessionPlanificarEva.setListaCursos(null);
            sessionPlanificarEva.setDniProfesor(null);
            sessionPlanificarEva.setDiaDeLaSemana(null);
            sessionPlanificarEva.setNidCurso(null);
            if (tbHorario != null) {
                tbHorario.setValue(null);
            }
            if (sessionPlanificarEva.getNidUsuario() != null) {
                Usuario evaluador = bdl_C_SFUsuarioRemote.findConstrainById(Integer.parseInt(sessionPlanificarEva.getNidUsuario()));
                sessionPlanificarEva.setNidAreaAcademica(evaluador.getAreaAcademica().getNidAreaAcademica());
            }
            sessionPlanificarEva.setFechaInicioSeleccionada(calendarEvent.getTriggerDate());      
            String dia = getDiaDeCalendario(calendarEvent.getTriggerDate().getDay());
            sessionPlanificarEva.setDiaDeLaSemana(dia);
            llenarBean();
            sessionPlanificarEva.setListaProfesores(this.llenarProfesores(sessionPlanificarEva.getNidAreaAcademica()));
            sessionPlanificarEva.setListaCursos(this.llenarCursos());
            Utils.showPopUpMIDDLE(popupEvento);
        }
            }    
     
    }

    public String showPopUp(RichPopup p) {
        try {
            RichPopup.PopupHints ph = new RichPopup.PopupHints();
            ph.add(RichPopup.PopupHints.HintTypes.HINT_ALIGN, RichPopup.PopupHints.AlignTypes.ALIGN_AFTER_END);
            //ph.add(RichPopup.PopupHints.HintTypes.HINT_ALIGN_ID,cb5);
            p.show(ph);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void seleccionarHorario(SelectionEvent selectionEvent) {
        RichTable t = (RichTable) selectionEvent.getSource();
        Object _selectedRowData = t.getSelectedRowData();
        BeanMain main = (BeanMain) _selectedRowData;
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
       sessionPlanificarEva.setNidCurso(null);
       sessionPlanificarEva.setDniProfesor(null);
       llenarBean();
        if (tbHorario != null) {
            Utils.unselectFilas(tbHorario);
            tbHorario.setValue(sessionPlanificarEva.getListaHorarios());
            Utils.addTarget(tbHorario);
        }
       return null;
    }

    /**Metodo para generar Codigo Alfanumerico de tipo NNNN-XXXX-XXXX-XXXX-NNNN*/
    public String generarAlfanumerico() {
        String[] abecedario = {
            "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U",
            "V", "W", "X", "Y", "Z"
        };
        String[] numeros = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" };
        String[] cadena = { "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "" };
        for (int i = 0; i <= 7; i++) {
            cadena[i] = numeros[(int) (Math.random() * 9 + 1)];
        }
        for (int i = 8; i <= 19; i++) {
            cadena[i] = abecedario[(int) (Math.random() * 25 + 1)];
        }
        String codigo =
            cadena[0] + cadena[1] + cadena[2] + cadena[3] + "-" + cadena[8] + cadena[9] + cadena[10] + cadena[11] +
            "-" + cadena[12] + cadena[13] + cadena[14] + cadena[15] + "-" + cadena[16] + cadena[17] + cadena[18] +
            cadena[19] + "-" + cadena[4] + cadena[5] + cadena[6] + cadena[7];
        return codigo;
    }    

    public void seleccionarEvaluador(SelectionEvent selectionEvent) {
        RichTable t = (RichTable) selectionEvent.getSource();
        Object _selectedRowData = t.getSelectedRowData();
        BeanUsuario usu = (BeanUsuario) _selectedRowData;
        sessionPlanificarEva.setNidUsuario(usu.getNidUsuario().toString());
        sessionPlanificarEva.setNombreEvaluador(usu.getNombres());
        sessionPlanificarEva.setAreaEvaluador(usu.getAreaAcademica().getDescripcionAreaAcademica());
        Utils.addTarget(outDatosEva);        
        Utils.invokeEL("#{bindings.ExecuteWithParams.execute}"); 
        Utils.addTarget(calendar);        
      
    }
    
    public void eliminarEvaluacion(ActionEvent actionEvent) {        
        Evaluacion evaluacion=new Evaluacion();
        evaluacion.setNidEvaluacion(sessionPlanificarEva.getNidEvaluacionDelet());
        bdl_T_SFEvaluacionRemoto.removeEvaluacion(evaluacion);
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

    public void setBtnBuscar(RichCommandButton btnBuscar) {
        this.btnBuscar = btnBuscar;
    }

    public RichCommandButton getBtnBuscar() {
        return btnBuscar;
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

    public void setBtnAsignarEva(RichCommandButton btnAsignarEva) {
        this.btnAsignarEva = btnAsignarEva;
    }

    public RichCommandButton getBtnAsignarEva() {
        return btnAsignarEva;
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

    
}
