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
import oracle.adf.share.logging.ADFLogger;
import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.data.RichCalendar;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.input.RichSelectOneChoice;
import oracle.adf.view.rich.component.rich.nav.RichButton;
import oracle.adf.view.rich.component.rich.nav.RichCommandButton;
import oracle.adf.view.rich.event.CalendarActivityEvent;
import oracle.adf.view.rich.event.CalendarEvent;
import oracle.adf.view.rich.model.CalendarActivity;


import oracle.adf.view.rich.render.ClientEvent;

import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

import oracle.jbo.ViewObject;
import oracle.jbo.server.ViewObjectImpl;
import oracle.jbo.uicli.binding.JUCtrlActionBinding;

import org.apache.myfaces.trinidad.event.AttributeChangeEvent;
import org.apache.myfaces.trinidad.event.SelectionEvent;

import sped.negocio.BDL.IR.BDL_C_SFAreaAcademicaRemote;
import sped.negocio.BDL.IR.BDL_C_SFEvaluacionRemoto;
import sped.negocio.BDL.IR.BDL_C_SFMainRemote;
import sped.negocio.BDL.IR.BDL_C_SFUsuarioRemote;
import sped.negocio.BDL.IR.BDL_T_SFEvaluacionRemoto;
import sped.negocio.BDL.IR.BDL_T_SFUsuarioRemote;
import sped.negocio.LNSF.IR.LN_C_SFAreaAcademicaRemote;
import sped.negocio.LNSF.IR.LN_C_SFCursoRemoto;
import sped.negocio.LNSF.IR.LN_C_SFGradoRemote;
import sped.negocio.LNSF.IR.LN_C_SFMainRemote;
import sped.negocio.LNSF.IR.LN_C_SFNivelRemote;
import sped.negocio.LNSF.IR.LN_C_SFSedeRemote;
import sped.negocio.LNSF.IR.LN_C_SFUsuarioRemote;
import sped.negocio.entidades.admin.AreaAcademica;
import sped.negocio.entidades.admin.Constraint;
import sped.negocio.entidades.admin.Curso;
import sped.negocio.entidades.admin.Main;
import sped.negocio.entidades.admin.Profesor;
import sped.negocio.entidades.admin.Usuario;
import sped.negocio.entidades.beans.BeanAreaAcademica;
import sped.negocio.entidades.beans.BeanAula;
import sped.negocio.entidades.beans.BeanCurso;
import sped.negocio.entidades.beans.BeanGrado;
import sped.negocio.entidades.beans.BeanGradoNivel;
import sped.negocio.entidades.beans.BeanMain;
import sped.negocio.entidades.beans.BeanNivel;
import sped.negocio.entidades.beans.BeanProfesor;
import sped.negocio.entidades.beans.BeanSede;
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
    private LN_C_SFAreaAcademicaRemote ln_C_SFAreaAcademicaRemote;
    @EJB
    private LN_C_SFSedeRemote ln_C_SFSedeRemote;
    @EJB
    private BDL_T_SFEvaluacionRemoto bdl_T_SFEvaluacionRemoto;
    @EJB
    private LN_C_SFCursoRemoto ln_C_SFCursoRemoto;
    @EJB
    private LN_C_SFGradoRemote ln_C_SFGradoRemote;
    @EJB
    private LN_C_SFNivelRemote ln_C_SFNivelRemote;
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
    private UISelectItems itemsEvaluadores;
    private RichCommandButton btnEvaluar;
    private RichPopup popupDetalleEva;
    private RichSelectOneChoice choiceCursos;
    private BeanUsuario beanUsuario = new BeanUsuario();
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


    public bPlanificarEva() {
        try {
            final Context ctx;
            ctx = new InitialContext();
            ln_C_SFUsuarioRemote = (LN_C_SFUsuarioRemote) ctx.lookup(LOOKUP_NAME_SFEVALUADORES_REMOTO);
            //   this.setEvaluadores(this.llenarEvaluadores());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @PostConstruct
    public void methodInvokeOncedOnPageLoad() {
        beanUsuario = (BeanUsuario) Utils.getSession("USER");
        sessionPlanificarEva.setNidPlanificador(beanUsuario.getNidUsuario());
        sessionPlanificarEva.setNidRol(beanUsuario.getRol().getNidRol());
        //rol=1 Director // rol=6 Administrador // Podran Asignar Evaluaciones
        if (beanUsuario.getRol().getNidRol() == 1 || beanUsuario.getRol().getNidRol() == 6) {
            sessionPlanificarEva.setEstadoChoiceEvaluadores(true);           
        } 
       else {
            sessionPlanificarEva.setEstadoChoiceEvaluadores(false);
            sessionPlanificarEva.setNidUsuario(beanUsuario.getNidUsuario().toString());
        }
    }

    public String popupEvaluadores() {
        sessionPlanificarEva.setListBeanUsua(ln_C_SFUsuarioRemote.getEvaluadores(sessionPlanificarEva.getNidAreaFiltro()));
        sessionPlanificarEva.setListAreaAcademica(llenarAreasAcademicas());
        Utils.showPopUpMIDDLE(popupEvalua);
        return null;
    }

    public ArrayList llenarTipoVisita() {
        ArrayList unItems = new ArrayList();
        List<Constraint> roles = bdl_C_SFEvaluacionRemoto.getTipoVisita();
        for (Constraint r : roles) {
            //r.setAreaAcYProf(r.getAreaAcademica().getDescripcionAreaAcademica() + " - " + r.getNombres());
            unItems.add(new SelectItem(r.getValorCampo().toString(), r.getDescripcionAMostrar().toString()));
        }
        return unItems;
    }


    public ArrayList llenarCursos() {
        ArrayList unItems = new ArrayList();
        List<BeanCurso> roles =
            ln_C_SFCursoRemoto.findCursosPorAreaAcademica(sessionPlanificarEva.getNidAreaAcademica(),
                                                          sessionPlanificarEva.getDiaDeLaSemana());
        for (BeanCurso r : roles) {
            //r.setAreaAcYProf(r.getAreaAcademica().getDescripcionAreaAcademica() + " - " + r.getNombres());
            unItems.add(new SelectItem(r.getNidCurso().toString(), r.getDescripcionCurso().toString()));
        }
        return unItems;
    }

    public ArrayList llenarSedes() {
        ArrayList unItems = new ArrayList();
        List<BeanSede> roles =
            ln_C_SFSedeRemote.findSedePorAreaAcademica(sessionPlanificarEva.getNidAreaAcademica(),
                                                       sessionPlanificarEva.getDiaDeLaSemana());
        for (BeanSede r : roles) {
            //r.setAreaAcYProf(r.getAreaAcademica().getDescripcionAreaAcademica() + " - " + r.getNombres());
            unItems.add(new SelectItem(r.getNidSede().toString(), r.getDescripcionSede().toString()));
        }
        return unItems;
    }

    public ArrayList llenarGrados() {
        ArrayList unItems = new ArrayList();
        List<BeanGrado> roles =
            ln_C_SFGradoRemote.findGradoPorAreaAcademica(sessionPlanificarEva.getNidAreaAcademica(),
                                                         sessionPlanificarEva.getDiaDeLaSemana());
        for (BeanGrado r : roles) {
            //r.setAreaAcYProf(r.getAreaAcademica().getDescripcionAreaAcademica() + " - " + r.getNombres());
            unItems.add(new SelectItem(r.getNidGrado().toString(), r.getDescripcionGrado().toString()));
        }
        return unItems;
    }

    public ArrayList llenarNiveles() {
        ArrayList unItems = new ArrayList();
        List<BeanNivel> roles =
            ln_C_SFNivelRemote.findNivelPorAreaAcademica(sessionPlanificarEva.getNidAreaAcademica(),
                                                         sessionPlanificarEva.getDiaDeLaSemana());
        for (BeanNivel r : roles) {
            //r.setAreaAcYProf(r.getAreaAcademica().getDescripcionAreaAcademica() + " - " + r.getNombres());
            unItems.add(new SelectItem(r.getNidNivel().toString(), r.getDescripcionNivel().toString()));
        }
        return unItems;
    }

    public ArrayList llenarAreasAcademicas() {
        ArrayList unItems = new ArrayList();
        List<BeanAreaAcademica> roles = ln_C_SFAreaAcademicaRemote.getAreaAcademicaLN();
        System.out.println("TAMAÑO AREAS " + roles.size());
        for (BeanAreaAcademica r : roles) {
            //r.setAreaAcYProf(r.getAreaAcademica().getDescripcionAreaAcademica() + " - " + r.getNombres());
            unItems.add(new SelectItem(r.getNidAreaAcademica().toString(), r.getDescripcionAreaAcademica().toString()));
        }
        return unItems;
    }


    public ArrayList llenarProfesores() {
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

    public String grabarEva1() {
        grabarEvaluacion(1);
        popupSeleccionBloque.hide();
        return null;
    }

    public String grabarEva2() {
        grabarEvaluacion(2);
        popupSeleccionBloque.hide();
        return null;
    }

    public String grabarEvaluacion(int opc) {
        Evaluacion eva = new Evaluacion();
        if (opc == 1) {
            long s = sessionPlanificarEva.getFechaInicioEvaluacion().getTime();
            eva.setStartDate(new Timestamp(s));
            long c = sessionPlanificarEva.getHoraPartidaInicio().getTime();
            eva.setEndDate(new Timestamp(c));
        }
        if (opc == 2) {
            long s = sessionPlanificarEva.getHoraPartidaInicio().getTime();
            eva.setStartDate(new Timestamp(s));
            long c = sessionPlanificarEva.getFechaFinEvaluacion().getTime();
            eva.setEndDate(new Timestamp(c));
        }
        Main main = new Main();
        main.setNidMain(sessionPlanificarEva.getBeanHorario().getNidMain());
        eva.setMain(main);
        eva.setNidEvaluador(Integer.parseInt(getSessionPlanificarEva().getNidUsuario()));
        eva.setDescripcion("HOLA QUE HACE");
        eva.setEstadoEvaluacion("PENDIENTE");
        String nidDat = generarAlfanumerico();
        eva.setNidDate(nidDat);
        eva.setNidPlanificador(sessionPlanificarEva.getNidPlanificador());
        Date fechaHoy = new Date();
        long d = fechaHoy.getTime();
        eva.setFechaPlanificacion(new Timestamp(d));
        eva.setTipoVisita(sessionPlanificarEva.getValorTipoVisita());
        bdl_T_SFEvaluacionRemoto.persistEvaluacion(eva);
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
        sessionPlanificarEva.setHoraEvaluacionPopup(entida.getEndDate());
        sessionPlanificarEva.setSedeEvaluacion(entida.getMain().getAula().getSede().getDescripcionSede());
        sessionPlanificarEva.setAulaEvaluacion(entida.getMain().getAula().getDescripcionAula());
        sessionPlanificarEva.setCursoEvaluacion(entida.getMain().getCurso().getDescripcionCurso());
        sessionPlanificarEva.setGradoEvaluacion(entida.getMain().getAula().getGradoNivel().getGrado().getDescripcionGrado());
        sessionPlanificarEva.setNivelEvaluacion(entida.getMain().getAula().getGradoNivel().getNivel().getDescripcionNivel());
        sessionPlanificarEva.setDocenteEvaluacion(entida.getMain().getProfesor().getApellidos() + " " +
                                                  entida.getMain().getProfesor().getNombres());
        sessionPlanificarEva.setDniDocenteEvaluacion(entida.getMain().getProfesor().getDniProfesor());
        sessionPlanificarEva.setNidEvaluacionDelet(entida.getNidEvaluacion());
        sessionPlanificarEva.setNombrePlanificador(bdl_C_SFUsuarioRemote.getNombresUsuarioByNidUsuario(entida.getNidPlanificador()));
        Usuario usua=bdl_C_SFUsuarioRemote.findConstrainById(entida.getNidPlanificador());
        sessionPlanificarEva.setRolPlanificador(usua.getRol().getDescripcionRol());
        Constraint con = bdl_C_SFEvaluacionRemoto.getTipoVisitaByValor(entida.getTipoVisita());
        sessionPlanificarEva.setTipoEvaluacion(con.getDescripcionAMostrar());

        Date fechaHoy = new Date();
        if (sessionPlanificarEva.getFechaEvaluacionPopup().before(fechaHoy)) {
            sessionPlanificarEva.setEstadoBotonEliminarEvaluacion(false);
        } else {
            if (entida.getNidPlanificador() != sessionPlanificarEva.getNidPlanificador() &&
                sessionPlanificarEva.getNidRol() == 1) {
                sessionPlanificarEva.setEstadoBotonEliminarEvaluacion(false);
            } else {
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
        System.out.println("Tamaño de beanMain " + lis.size());
        if (lis != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date fechaActual = sessionPlanificarEva.getFechaInicioSeleccionada();
            String fechaConFormato = sdf.format(fechaActual);
            List<Evaluacion> listaEvaluaciones =
                bdl_C_SFEvaluacionRemoto.getEvaluaciones(fechaConFormato, sessionPlanificarEva.getNidAreaAcademica(),
                                                         Integer.parseInt(sessionPlanificarEva.getNidUsuario()),
                                                         sessionPlanificarEva.getDniProfesor(),
                                                         sessionPlanificarEva.getNidCurso(),sessionPlanificarEva.getNidSedeEvaluador());
           System.out.println("Tamaño de Cursos del dia " + lis.size());
           System.out.println("Tamaño de evaluaciones " + listaEvaluaciones.size());
            if (listaEvaluaciones.size() == lis.size()) {
                lis.clear();
            } else {             
                if (lis.size() != 0) {
                    for (int i = 0; i < lis.size(); i++) {
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

    public void llenarBean() {
        BeanMain beanMain = new BeanMain();
        BeanAreaAcademica beanAca = new BeanAreaAcademica();
        System.out.println("Valor de Area Academica " + sessionPlanificarEva.getNidAreaAcademica());
        beanAca.setNidAreaAcademica(sessionPlanificarEva.getNidAreaAcademica());
        BeanCurso beanCurso = new BeanCurso();
        beanCurso.setAreaAcademica(beanAca);
        if (sessionPlanificarEva.getNidCurso() != null) {
            beanCurso.setNidCurso(Integer.parseInt(sessionPlanificarEva.getNidCurso()));
            System.out.println("Valor de Curso " + sessionPlanificarEva.getNidCurso());
        }
        BeanProfesor beanProf = new BeanProfesor();
        if (sessionPlanificarEva.getDniProfesor() != null) {
            beanProf.setDniProfesor(sessionPlanificarEva.getDniProfesor().toString());
            beanMain.setProfesor(beanProf);
            System.out.println("Valor deProfesor " + sessionPlanificarEva.getDniProfesor());
        }
        BeanSede sede = new BeanSede();
        BeanAula aula = new BeanAula();
        BeanGradoNivel grani = new BeanGradoNivel();
        if (sessionPlanificarEva.getNidSedeEvaluador() != 0) {
            sede.setNidSede(sessionPlanificarEva.getNidSedeEvaluador());
            aula.setSede(sede);
            System.out.println("Valor sede " + sessionPlanificarEva.getNidSedeEvaluador());
        }
        if (sessionPlanificarEva.getNidGrado() != null) {
            BeanGrado grado = new BeanGrado();
            grado.setNidGrado(Integer.parseInt(sessionPlanificarEva.getNidGrado()));
            grani.setGrado(grado);
            System.out.println("Valor Grado " + sessionPlanificarEva.getNidGrado());
        }
        if (sessionPlanificarEva.getNidNivel() != null) {
            BeanNivel nivel = new BeanNivel();
            nivel.setNidNivel(Integer.parseInt(sessionPlanificarEva.getNidNivel()));
            grani.setNivel(nivel);
            System.out.println("Valor Nivel " + sessionPlanificarEva.getNidNivel());
        }
        aula.setGradoNivel(grani);
        beanMain.setAula(aula);
        beanMain.setCurso(beanCurso);
        beanMain.setDia(sessionPlanificarEva.getDiaDeLaSemana());
        llenarHorarios(beanMain);
    }

    public void abrirNuevoEvento(CalendarEvent calendarEvent) {
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
                sessionPlanificarEva.setNidSede(null);
                sessionPlanificarEva.setNidGrado(null);
                sessionPlanificarEva.setNidNivel(null);
                if (tbHorario != null) {
                    tbHorario.setValue(null);
                }
                if (sessionPlanificarEva.getNidUsuario() != null) {
                    Usuario evaluador =  bdl_C_SFUsuarioRemote.findConstrainById(Integer.parseInt(sessionPlanificarEva.getNidUsuario()));
                    if(evaluador.getAreaAcademica()!=null){
                        if(evaluador.getAreaAcademica().getNidAreaAcademica()!=0){
                            System.out.println("Valor AREA <<< " + evaluador.getAreaAcademica().getNidAreaAcademica());
                            sessionPlanificarEva.setNidAreaAcademica(evaluador.getAreaAcademica().getNidAreaAcademica());
                        }                        
                        } 
                    if(evaluador.getSedeNivel()!=null){
                            System.out.println("NO ES NULO "); 
                            System.out.println("Valor 1 <<< " + evaluador.getSedeNivel());
                            System.out.println("Valor 2 <<< " + evaluador.getSedeNivel().getSede());
                            System.out.println("Valor 3 <<< " + evaluador.getSedeNivel().getSede().getNidSede());
                        if(evaluador.getSedeNivel().getSede().getNidSede()!=0){
                                System.out.println("Valor SEDE <<< " + evaluador.getSedeNivel().getSede().getNidSede());
                            sessionPlanificarEva.setNidSedeEvaluador(evaluador.getSedeNivel().getSede().getNidSede());                         }
                        }                    }
                        
                sessionPlanificarEva.setFechaInicioSeleccionada(calendarEvent.getTriggerDate());
                String dia = getDiaDeCalendario(calendarEvent.getTriggerDate().getDay());
                sessionPlanificarEva.setDiaDeLaSemana(dia);
                llenarBean();
                sessionPlanificarEva.setListaProfesores(this.llenarProfesores());
                sessionPlanificarEva.setListaCursos(this.llenarCursos());
                sessionPlanificarEva.setListaSedes(this.llenarSedes());
                sessionPlanificarEva.setListaGrados(this.llenarGrados());
                sessionPlanificarEva.setListaNiveles(this.llenarNiveles());
                sessionPlanificarEva.setEstadoAsignarEvaluacion(true);
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
            sessionPlanificarEva.setDocenteEvaluacion(main.getProfesor().getApellidos() + " " +
                                                      main.getProfesor().getNombres());

            int numHora = horaFin.getHours() - horaInicio.getHours();
            int numMinutos = horaFin.getMinutes() - horaInicio.getMinutes();
            int segundosEnMinutos = numMinutos * 60;
            int toalSegundos = numHora * 3600 + segundosEnMinutos;
            int segundosAumen = toalSegundos / 2;
            int num = segundosAumen;
            int hor = num / 3600;
            int min = (num - (3600 * hor)) / 60;
            int seg = num - ((hor * 3600) + (min * 60));
            System.out.println(hor + "h " + min + "m " + seg + "s");
            Date fechaPartida = (Date) horaInicio.clone();
            fechaPartida.setHours(fechaPartida.getHours() + hor);
            fechaPartida.setMinutes(fechaPartida.getMinutes() + min);

            sessionPlanificarEva.setHoraPartidaInicio(fechaPartida);
        }
        sessionPlanificarEva.setStyleClass("FondoRojoLetraBlanca");
        btnAsignarEva.setStyleClass("FondoRojoLetraBlanca");
        sessionPlanificarEva.setEstadoAsignarEvaluacion(false);
        btnAsignarEva.setDisabled(false);
        ;
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
            sessionPlanificarEva.setNidSede(null);
            choiceSede.resetValue();
            Utils.addTarget(choiceSede);
        }
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
        sessionPlanificarEva.setNombreEvaluador(null);
        sessionPlanificarEva.setAreaEvaluador(null);
        RichTable t = (RichTable) selectionEvent.getSource();
        Object _selectedRowData = t.getSelectedRowData();
        BeanUsuario usu = (BeanUsuario) _selectedRowData;
        if (usu != null) {
            sessionPlanificarEva.setNidUsuario(usu.getNidUsuario().toString());
            sessionPlanificarEva.setNombreEvaluador(usu.getNombres());
            if(usu.getAreaAcademica()!=null ){
            sessionPlanificarEva.setAreaEvaluador(usu.getAreaAcademica().getDescripcionAreaAcademica());}                    
            Utils.addTarget(outDatosEva);
            Utils.invokeEL("#{bindings.ExecuteWithParams.execute}");
            Utils.addTarget(calendar);
        }

    }

    public void eliminarEvaluacion(ActionEvent actionEvent) {
        Evaluacion evaluacion = new Evaluacion();
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

    public void filtrarListaEvaluadores(ValueChangeEvent valueChangeEvent) {
        if (choiceFiltArea.getValue().toString().equals("0")) {
            refTbEvalu(null);
        } else {
            refTbEvalu(choiceFiltArea.getValue().toString());
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

    public String seleccionarBloque() {
        sessionPlanificarEva.setEstadoBtnBloq1(false);
        sessionPlanificarEva.setEstadoBtnBloq2(false);
        sessionPlanificarEva.setEstadoOut1(false);
        sessionPlanificarEva.setEstadoOut2(false);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date fechaActual = sessionPlanificarEva.getFechaInicioSeleccionada();
        String fechaConFormato = sdf.format(fechaActual);
        List<Evaluacion> listaEvaluaciones =
            bdl_C_SFEvaluacionRemoto.getEvaluaciones(fechaConFormato, sessionPlanificarEva.getNidAreaAcademica(),
                                                     Integer.parseInt(sessionPlanificarEva.getNidUsuario()),
                                                     sessionPlanificarEva.getDniProfesor(),
                                                     sessionPlanificarEva.getNidCurso(),sessionPlanificarEva.getNidSedeEvaluador());
        if (listaEvaluaciones.size() != 0) {
            System.out.println("Tamaño de evaluaciones del dia "+ listaEvaluaciones.size());
            for (int i = 0; i < listaEvaluaciones.size(); i++) {
                System.out.println("HORA PLANIFI INICIO "+ sessionPlanificarEva.getFechaInicioEvaluacion().getHours());
                System.out.println("MINUTO PLANIFI INICIO "+ sessionPlanificarEva.getFechaInicioEvaluacion().getMinutes());
                System.out.println("HORA EVA INICIO "+ listaEvaluaciones.get(i).getStartDate().getHours());
                System.out.println("MINUTO EVA INICIO "+ listaEvaluaciones.get(i).getStartDate().getMinutes());
                System.out.println("HORA PLANIFI FIN "+ sessionPlanificarEva.getFechaFinEvaluacion().getHours() );
                System.out.println("MINUTO PLANIFI FIN "+ sessionPlanificarEva.getFechaFinEvaluacion().getMinutes() );
                System.out.println("HORA EVA FIN "+ listaEvaluaciones.get(i).getEndDate().getHours());
                System.out.println("MINUTO EVA FIN "+ listaEvaluaciones.get(i).getEndDate().getMinutes());
                System.out.println("HORA PARTI INICIO "+ sessionPlanificarEva.getHoraPartidaInicio().getHours());
                System.out.println("MINUTO PARTI INICIO "+ sessionPlanificarEva.getHoraPartidaInicio().getMinutes()); 
                System.out.println("/////////////////////////////////////////////////////////////////"); 
                
                if (sessionPlanificarEva.getFechaInicioEvaluacion().getHours() == listaEvaluaciones.get(i).getStartDate().getHours() &&
                    sessionPlanificarEva.getFechaInicioEvaluacion().getMinutes()== listaEvaluaciones.get(i).getStartDate().getMinutes() &&                    
                    sessionPlanificarEva.getHoraPartidaInicio().getHours() == listaEvaluaciones.get(i).getEndDate().getHours() && 
                    sessionPlanificarEva.getHoraPartidaInicio().getMinutes() == listaEvaluaciones.get(i).getEndDate().getMinutes() 
                 )   
                     {
                        System.out.println("ENTRO IF 1 IGUALDAD"); 
                        sessionPlanificarEva.setEstadoBtnBloq1(true);
                        sessionPlanificarEva.setEstadoOut1(true); 
                    }
                  
                  if(  sessionPlanificarEva.getFechaInicioEvaluacion().getHours()== listaEvaluaciones.get(i).getStartDate().getHours() &&
                       sessionPlanificarEva.getFechaInicioEvaluacion().getMinutes()== listaEvaluaciones.get(i).getStartDate().getMinutes() &&
                                    
                       (sessionPlanificarEva.getHoraPartidaInicio().getHours() == listaEvaluaciones.get(i).getEndDate().getHours() && 
                        sessionPlanificarEva.getHoraPartidaInicio().getMinutes() > listaEvaluaciones.get(i).getEndDate().getMinutes()
                        ||
                        sessionPlanificarEva.getHoraPartidaInicio().getHours() > listaEvaluaciones.get(i).getEndDate().getHours() 
                        )          
                     ){
                           System.out.println("ENTRO IF 2    = HORA INICIO , HFP > HFE"); 
                           sessionPlanificarEva.setEstadoBtnBloq1(true);
                           sessionPlanificarEva.setEstadoOut1(true); 
                       }
                  
                  if( sessionPlanificarEva.getFechaInicioEvaluacion().getHours() == listaEvaluaciones.get(i).getStartDate().getHours() &&
                    sessionPlanificarEva.getFechaInicioEvaluacion().getMinutes() == listaEvaluaciones.get(i).getStartDate().getMinutes() &&
                    
                      (sessionPlanificarEva.getHoraPartidaInicio().getHours() == listaEvaluaciones.get(i).getEndDate().getHours() && 
                       sessionPlanificarEva.getHoraPartidaInicio().getMinutes() < listaEvaluaciones.get(i).getEndDate().getMinutes()
                       ||
                       sessionPlanificarEva.getHoraPartidaInicio().getHours() < listaEvaluaciones.get(i).getEndDate().getHours() 
                       )  ){
                      System.out.println("ENTRO IF 3    = HORA INICIO , HFP < HFE"); 
                      sessionPlanificarEva.setEstadoBtnBloq1(true);
                      sessionPlanificarEva.setEstadoOut1(true); 
                  }
                 
                        
                  if( (sessionPlanificarEva.getFechaInicioEvaluacion().getHours() == listaEvaluaciones.get(i).getStartDate().getHours() &&
                        sessionPlanificarEva.getFechaInicioEvaluacion().getMinutes() > listaEvaluaciones.get(i).getStartDate().getMinutes() ||
                               sessionPlanificarEva.getFechaInicioEvaluacion().getHours() > listaEvaluaciones.get(i).getStartDate().getHours()) &&
                              
                               sessionPlanificarEva.getHoraPartidaInicio().getHours() == listaEvaluaciones.get(i).getEndDate().getHours() && 
                               sessionPlanificarEva.getHoraPartidaInicio().getMinutes() == listaEvaluaciones.get(i).getEndDate().getMinutes())    {
                        System.out.println("ENTRO IF 4"); 
                        sessionPlanificarEva.setEstadoBtnBloq1(true);
                        sessionPlanificarEva.setEstadoOut1(true); 
                    }
                   if(
                       ( sessionPlanificarEva.getFechaInicioEvaluacion().getHours()== listaEvaluaciones.get(i).getStartDate().getHours() &&
                         sessionPlanificarEva.getFechaInicioEvaluacion().getMinutes()> listaEvaluaciones.get(i).getStartDate().getMinutes() ||
                         sessionPlanificarEva.getFechaInicioEvaluacion().getHours()> listaEvaluaciones.get(i).getStartDate().getHours() 
                       ) 
                       
                       &&
                                          
                       ( sessionPlanificarEva.getHoraPartidaInicio().getHours() == listaEvaluaciones.get(i).getEndDate().getHours() &&
                         sessionPlanificarEva.getHoraPartidaInicio().getMinutes() > listaEvaluaciones.get(i).getEndDate().getMinutes() ||
                         sessionPlanificarEva.getHoraPartidaInicio().getHours() > listaEvaluaciones.get(i).getEndDate().getHours() 
                       )
                       
                       &&
                                         
                       ( sessionPlanificarEva.getFechaInicioEvaluacion().getHours() == listaEvaluaciones.get(i).getEndDate().getHours() &&
                        sessionPlanificarEva.getFechaInicioEvaluacion().getMinutes() < listaEvaluaciones.get(i).getEndDate().getMinutes() ||
                        sessionPlanificarEva.getFechaInicioEvaluacion().getHours() < listaEvaluaciones.get(i).getEndDate().getHours() 
                       )
                   
                   ){
                        System.out.println("ENTRO IF 5"); 
                        sessionPlanificarEva.setEstadoBtnBloq1(true);
                        sessionPlanificarEva.setEstadoOut1(true); 
                    }
                    if((
                    sessionPlanificarEva.getFechaInicioEvaluacion().getHours()== listaEvaluaciones.get(i).getStartDate().getHours() &&
                    sessionPlanificarEva.getFechaInicioEvaluacion().getMinutes()> listaEvaluaciones.get(i).getStartDate().getMinutes() ||
                    sessionPlanificarEva.getFechaInicioEvaluacion().getHours()> listaEvaluaciones.get(i).getStartDate().getHours() ) &&
                    
                    (sessionPlanificarEva.getHoraPartidaInicio().getHours() == listaEvaluaciones.get(i).getEndDate().getHours() &&
                    sessionPlanificarEva.getHoraPartidaInicio().getMinutes() < listaEvaluaciones.get(i).getEndDate().getMinutes() ||
                        sessionPlanificarEva.getHoraPartidaInicio().getHours() < listaEvaluaciones.get(i).getEndDate().getHours() 
                     )  )
                    {
                        System.out.println("ENTRO IF 6"); 
                        sessionPlanificarEva.setEstadoBtnBloq1(true);
                        sessionPlanificarEva.setEstadoOut1(true); 
                    }
                if(   
                     ( sessionPlanificarEva.getFechaInicioEvaluacion().getHours()== listaEvaluaciones.get(i).getStartDate().getHours() &&
                       sessionPlanificarEva.getFechaInicioEvaluacion().getMinutes()< listaEvaluaciones.get(i).getStartDate().getMinutes() ||
                       sessionPlanificarEva.getFechaInicioEvaluacion().getHours()< listaEvaluaciones.get(i).getStartDate().getHours() 
                     ) 
                     
                     &&
                                        
                     ( sessionPlanificarEva.getHoraPartidaInicio().getHours() == listaEvaluaciones.get(i).getEndDate().getHours() &&
                       sessionPlanificarEva.getHoraPartidaInicio().getMinutes() < listaEvaluaciones.get(i).getEndDate().getMinutes() ||
                       sessionPlanificarEva.getHoraPartidaInicio().getHours() < listaEvaluaciones.get(i).getEndDate().getHours() 
                     )
                
                    &&
                                       
                    ( sessionPlanificarEva.getHoraPartidaInicio().getHours() == listaEvaluaciones.get(i).getStartDate().getHours() &&
                      sessionPlanificarEva.getHoraPartidaInicio().getMinutes() > listaEvaluaciones.get(i).getStartDate().getMinutes() ||
                      sessionPlanificarEva.getHoraPartidaInicio().getHours() > listaEvaluaciones.get(i).getStartDate().getHours() 
                    )
                 )
                
                  {
                        System.out.println("ENTRO IF 7"); 
                        sessionPlanificarEva.setEstadoBtnBloq1(true);
                        sessionPlanificarEva.setEstadoOut1(true); 
                    }
                   if(
                       ( sessionPlanificarEva.getFechaInicioEvaluacion().getHours()== listaEvaluaciones.get(i).getStartDate().getHours() &&
                         sessionPlanificarEva.getFechaInicioEvaluacion().getMinutes()< listaEvaluaciones.get(i).getStartDate().getMinutes() ||
                         sessionPlanificarEva.getFechaInicioEvaluacion().getHours()< listaEvaluaciones.get(i).getStartDate().getHours() 
                       ) 
                       
                       &&
                                          
                        sessionPlanificarEva.getHoraPartidaInicio().getHours() == listaEvaluaciones.get(i).getEndDate().getHours() &&
                        sessionPlanificarEva.getHoraPartidaInicio().getMinutes() == listaEvaluaciones.get(i).getEndDate().getMinutes() 
                     
                       ){
                        System.out.println("ENTRO IF 8"); 
                        sessionPlanificarEva.setEstadoBtnBloq1(true);
                        sessionPlanificarEva.setEstadoOut1(true); 
                    }                
                
                if (
                    ( sessionPlanificarEva.getFechaInicioEvaluacion().getHours()== listaEvaluaciones.get(i).getStartDate().getHours() &&
                      sessionPlanificarEva.getFechaInicioEvaluacion().getMinutes()< listaEvaluaciones.get(i).getStartDate().getMinutes() ||
                      sessionPlanificarEva.getFechaInicioEvaluacion().getHours()< listaEvaluaciones.get(i).getStartDate().getHours() 
                    ) 
                    
                    &&
                                       
                    ( sessionPlanificarEva.getHoraPartidaInicio().getHours() == listaEvaluaciones.get(i).getEndDate().getHours() &&
                      sessionPlanificarEva.getHoraPartidaInicio().getMinutes() > listaEvaluaciones.get(i).getEndDate().getMinutes() ||
                      sessionPlanificarEva.getHoraPartidaInicio().getHours() > listaEvaluaciones.get(i).getEndDate().getHours() 
                    )
             
                )
                {
                    System.out.println("ENTRO IF 9"); 
                    sessionPlanificarEva.setEstadoBtnBloq1(true);
                    sessionPlanificarEva.setEstadoOut1(true);
                }
                
                // BTN2
                
                if (sessionPlanificarEva.getHoraPartidaInicio().getHours() == listaEvaluaciones.get(i).getStartDate().getHours() &&
                    sessionPlanificarEva.getHoraPartidaInicio().getMinutes()== listaEvaluaciones.get(i).getStartDate().getMinutes() &&                    
                    sessionPlanificarEva.getFechaFinEvaluacion().getHours() == listaEvaluaciones.get(i).getEndDate().getHours() && 
                    sessionPlanificarEva.getFechaFinEvaluacion().getMinutes() == listaEvaluaciones.get(i).getEndDate().getMinutes() 
                 )   
                     {
                        System.out.println("ENTRO IF 1 IGUALDAD"); 
                        sessionPlanificarEva.setEstadoBtnBloq2(true);
                        sessionPlanificarEva.setEstadoOut2(true); 
                    }
                  
                  if(  sessionPlanificarEva.getHoraPartidaInicio().getHours()== listaEvaluaciones.get(i).getStartDate().getHours() &&
                       sessionPlanificarEva.getHoraPartidaInicio().getMinutes()== listaEvaluaciones.get(i).getStartDate().getMinutes() &&
                                    
                       (sessionPlanificarEva.getFechaFinEvaluacion().getHours() == listaEvaluaciones.get(i).getEndDate().getHours() && 
                        sessionPlanificarEva.getFechaFinEvaluacion().getMinutes() > listaEvaluaciones.get(i).getEndDate().getMinutes()
                        ||
                        sessionPlanificarEva.getFechaFinEvaluacion().getHours() > listaEvaluaciones.get(i).getEndDate().getHours() 
                        )          
                     ){
                           System.out.println("ENTRO IF 2    = HORA INICIO , HFP > HFE"); 
                           sessionPlanificarEva.setEstadoBtnBloq2(true);
                           sessionPlanificarEva.setEstadoOut2(true); 
                       }
                  
                  if( sessionPlanificarEva.getHoraPartidaInicio().getHours() == listaEvaluaciones.get(i).getStartDate().getHours() &&
                    sessionPlanificarEva.getHoraPartidaInicio().getMinutes() == listaEvaluaciones.get(i).getStartDate().getMinutes() &&
                    
                      (sessionPlanificarEva.getFechaFinEvaluacion().getHours() == listaEvaluaciones.get(i).getEndDate().getHours() && 
                       sessionPlanificarEva.getFechaFinEvaluacion().getMinutes() < listaEvaluaciones.get(i).getEndDate().getMinutes()
                       ||
                       sessionPlanificarEva.getFechaFinEvaluacion().getHours() < listaEvaluaciones.get(i).getEndDate().getHours() 
                       )  ){
                      System.out.println("ENTRO IF 3    = HORA INICIO , HFP < HFE"); 
                      sessionPlanificarEva.setEstadoBtnBloq2(true);
                      sessionPlanificarEva.setEstadoOut2(true); 
                  }
                 
                        
                  if( (sessionPlanificarEva.getHoraPartidaInicio().getHours() == listaEvaluaciones.get(i).getStartDate().getHours() &&
                        sessionPlanificarEva.getHoraPartidaInicio().getMinutes() > listaEvaluaciones.get(i).getStartDate().getMinutes() ||
                               sessionPlanificarEva.getHoraPartidaInicio().getHours() > listaEvaluaciones.get(i).getStartDate().getHours()) &&
                              
                               sessionPlanificarEva.getFechaFinEvaluacion().getHours() == listaEvaluaciones.get(i).getEndDate().getHours() && 
                               sessionPlanificarEva.getFechaFinEvaluacion().getMinutes() == listaEvaluaciones.get(i).getEndDate().getMinutes())    {
                        System.out.println("ENTRO IF 4"); 
                        sessionPlanificarEva.setEstadoBtnBloq2(true);
                        sessionPlanificarEva.setEstadoOut2(true); 
                    }
                   if(
                       ( sessionPlanificarEva.getHoraPartidaInicio().getHours()== listaEvaluaciones.get(i).getStartDate().getHours() &&
                         sessionPlanificarEva.getHoraPartidaInicio().getMinutes()> listaEvaluaciones.get(i).getStartDate().getMinutes() ||
                         sessionPlanificarEva.getHoraPartidaInicio().getHours()> listaEvaluaciones.get(i).getStartDate().getHours() 
                       ) 
                       
                       &&
                                          
                       ( sessionPlanificarEva.getFechaFinEvaluacion().getHours() == listaEvaluaciones.get(i).getEndDate().getHours() &&
                         sessionPlanificarEva.getFechaFinEvaluacion().getMinutes() > listaEvaluaciones.get(i).getEndDate().getMinutes() ||
                         sessionPlanificarEva.getFechaFinEvaluacion().getHours() > listaEvaluaciones.get(i).getEndDate().getHours() 
                       )
                       
                       &&
                                         
                       ( sessionPlanificarEva.getHoraPartidaInicio().getHours() == listaEvaluaciones.get(i).getEndDate().getHours() &&
                        sessionPlanificarEva.getHoraPartidaInicio().getMinutes() < listaEvaluaciones.get(i).getEndDate().getMinutes() ||
                        sessionPlanificarEva.getHoraPartidaInicio().getHours() < listaEvaluaciones.get(i).getEndDate().getHours() 
                       )
                   
                   ){
                        System.out.println("ENTRO IF 5"); 
                        sessionPlanificarEva.setEstadoBtnBloq2(true);
                        sessionPlanificarEva.setEstadoOut2(true); 
                    }
                    if((
                    sessionPlanificarEva.getHoraPartidaInicio().getHours()== listaEvaluaciones.get(i).getStartDate().getHours() &&
                    sessionPlanificarEva.getHoraPartidaInicio().getMinutes()> listaEvaluaciones.get(i).getStartDate().getMinutes() ||
                    sessionPlanificarEva.getHoraPartidaInicio().getHours()> listaEvaluaciones.get(i).getStartDate().getHours() ) &&
                    
                    (sessionPlanificarEva.getFechaFinEvaluacion().getHours() == listaEvaluaciones.get(i).getEndDate().getHours() &&
                    sessionPlanificarEva.getFechaFinEvaluacion().getMinutes() < listaEvaluaciones.get(i).getEndDate().getMinutes() ||
                        sessionPlanificarEva.getFechaFinEvaluacion().getHours() < listaEvaluaciones.get(i).getEndDate().getHours() 
                     )  )
                    {
                        System.out.println("ENTRO IF 6"); 
                        sessionPlanificarEva.setEstadoBtnBloq2(true);
                        sessionPlanificarEva.setEstadoOut2(true); 
                    }
                if(   
                     ( sessionPlanificarEva.getHoraPartidaInicio().getHours()== listaEvaluaciones.get(i).getStartDate().getHours() &&
                       sessionPlanificarEva.getHoraPartidaInicio().getMinutes()< listaEvaluaciones.get(i).getStartDate().getMinutes() ||
                       sessionPlanificarEva.getHoraPartidaInicio().getHours()< listaEvaluaciones.get(i).getStartDate().getHours() 
                     ) 
                     
                     &&
                                        
                     ( sessionPlanificarEva.getFechaFinEvaluacion().getHours() == listaEvaluaciones.get(i).getEndDate().getHours() &&
                       sessionPlanificarEva.getFechaFinEvaluacion().getMinutes() < listaEvaluaciones.get(i).getEndDate().getMinutes() ||
                       sessionPlanificarEva.getFechaFinEvaluacion().getHours() < listaEvaluaciones.get(i).getEndDate().getHours() 
                     )
                
                    &&
                                       
                    ( sessionPlanificarEva.getFechaFinEvaluacion().getHours() == listaEvaluaciones.get(i).getStartDate().getHours() &&
                      sessionPlanificarEva.getFechaFinEvaluacion().getMinutes() > listaEvaluaciones.get(i).getStartDate().getMinutes() ||
                      sessionPlanificarEva.getFechaFinEvaluacion().getHours() > listaEvaluaciones.get(i).getStartDate().getHours() 
                    )
                 )
                
                  {
                        System.out.println("ENTRO IF 7"); 
                        sessionPlanificarEva.setEstadoBtnBloq2(true);
                        sessionPlanificarEva.setEstadoOut2(true); 
                    }
                   if(
                       ( sessionPlanificarEva.getHoraPartidaInicio().getHours()== listaEvaluaciones.get(i).getStartDate().getHours() &&
                         sessionPlanificarEva.getHoraPartidaInicio().getMinutes()< listaEvaluaciones.get(i).getStartDate().getMinutes() ||
                         sessionPlanificarEva.getHoraPartidaInicio().getHours()< listaEvaluaciones.get(i).getStartDate().getHours() 
                       ) 
                       
                       &&
                                          
                        sessionPlanificarEva.getFechaFinEvaluacion().getHours() == listaEvaluaciones.get(i).getEndDate().getHours() &&
                        sessionPlanificarEva.getFechaFinEvaluacion().getMinutes() == listaEvaluaciones.get(i).getEndDate().getMinutes() 
                     
                       ){
                        System.out.println("ENTRO IF 8"); 
                        sessionPlanificarEva.setEstadoBtnBloq2(true);
                        sessionPlanificarEva.setEstadoOut2(true); 
                    }                
                
                if (
                    ( sessionPlanificarEva.getHoraPartidaInicio().getHours()== listaEvaluaciones.get(i).getStartDate().getHours() &&
                      sessionPlanificarEva.getHoraPartidaInicio().getMinutes()< listaEvaluaciones.get(i).getStartDate().getMinutes() ||
                      sessionPlanificarEva.getHoraPartidaInicio().getHours()< listaEvaluaciones.get(i).getStartDate().getHours() 
                    ) 
                    
                    &&
                                       
                    ( sessionPlanificarEva.getFechaFinEvaluacion().getHours() == listaEvaluaciones.get(i).getEndDate().getHours() &&
                      sessionPlanificarEva.getFechaFinEvaluacion().getMinutes() > listaEvaluaciones.get(i).getEndDate().getMinutes() ||
                      sessionPlanificarEva.getFechaFinEvaluacion().getHours() > listaEvaluaciones.get(i).getEndDate().getHours() 
                    )
                
                )
                {
                    System.out.println("ENTRO IF 9"); 
                    sessionPlanificarEva.setEstadoBtnBloq2(true);
                    sessionPlanificarEva.setEstadoOut2(true);
                }
                
                }

            }
        
        sessionPlanificarEva.setListatipoVisita(llenarTipoVisita());
        sessionPlanificarEva.setValorTipoVisita("OP");
        Utils.showPopUpMIDDLE(popupSeleccionBloque);
        return null;
    }

    public void activarBotonEvaluar(AttributeChangeEvent attributeChangeEvent) {
        System.out.println("Atributte " + choiceTipoVisita.getValue());
    }

    public void activarbtnEvaluar(ValueChangeEvent valueChangeEvent) {
        System.out.println("Value " + choiceTipoVisita.getValue());
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
}
