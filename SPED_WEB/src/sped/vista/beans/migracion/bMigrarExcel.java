package sped.vista.beans.migracion;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.input.RichInputFile;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.input.RichSelectOneChoice;
import oracle.adf.view.rich.component.rich.nav.RichButton;
import oracle.adf.view.rich.component.rich.output.RichMessages;
import org.apache.myfaces.trinidad.event.SelectionEvent;
import org.apache.myfaces.trinidad.model.UploadedFile;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import sped.negocio.BDL.IR.BDL_C_SFMainRemote;
import sped.negocio.BDL.IR.BDL_T_SFMainRemoto;
import sped.negocio.BDL.IR.BDL_T_SFUtilsRemote;
import sped.negocio.LNSF.IL.LN_T_SFMainLocal;
import sped.negocio.LNSF.IR.LN_C_SFAreaAcademicaRemote;
import sped.negocio.LNSF.IR.LN_C_SFAulaRemote;
import sped.negocio.LNSF.IR.LN_C_SFCursoRemoto;
import sped.negocio.LNSF.IR.LN_C_SFMainRemote;
import sped.negocio.LNSF.IR.LN_C_SFGradoRemote;
import sped.negocio.LNSF.IR.LN_C_SFNivelRemote;
import sped.negocio.LNSF.IR.LN_C_SFProfesorRemote;
import sped.negocio.LNSF.IR.LN_C_SFUtilsRemote;
import sped.negocio.LNSF.IR.LN_T_SFAreaAcademicaRemoto;
import sped.negocio.LNSF.IR.LN_T_SFAulaRemoto;
import sped.negocio.LNSF.IR.LN_T_SFCursoRemoto;
import sped.negocio.LNSF.IR.LN_T_SFProfesorRemoto;
import sped.negocio.LNSF.IR.LN_T_SFUsuarioRemote;
import sped.negocio.entidades.admin.Aula;
import sped.negocio.entidades.admin.Curso;
import sped.negocio.entidades.admin.Main;
import sped.negocio.entidades.admin.Profesor;
import sped.negocio.entidades.beans.BeanAreaAcademica;
import sped.negocio.entidades.beans.BeanAula;
import sped.negocio.entidades.beans.BeanCurso;
import sped.negocio.entidades.beans.BeanGrado;
import sped.negocio.entidades.beans.BeanGradoNivel;
import sped.negocio.entidades.beans.BeanMainWS;
import sped.negocio.entidades.beans.BeanNivel;
import sped.negocio.entidades.beans.BeanProfesor;
import sped.negocio.entidades.beans.BeanSede;
import sped.vista.Utils.Utils;

public class bMigrarExcel {
    @EJB
    private LN_C_SFCursoRemoto ln_C_SFCursoRemoto;
    @EJB
    private LN_T_SFCursoRemoto ln_T_SFCursoRemoto;
    @EJB
    private LN_C_SFAreaAcademicaRemote ln_C_SFAreaAcademicaRemote;
    @EJB
    private LN_T_SFAreaAcademicaRemoto ln_T_SFAreaAcademicaRemoto;
    @EJB
    private LN_C_SFAulaRemote ln_C_SFAulaRemoto;
    @EJB
    private LN_T_SFAulaRemoto ln_T_SFAulaRemoto;
    @EJB
    private LN_C_SFProfesorRemote ln_C_SFProfesorRemote;
    @EJB
    private LN_T_SFProfesorRemoto ln_T_SFProfesorRemoto;
    @EJB
    private LN_T_SFUsuarioRemote ln_T_SFUsuarioRemote;
    @EJB
    private LN_C_SFUtilsRemote ln_C_SFUtilsRemote;
    @EJB
    private LN_C_SFMainRemote ln_C_SFMainRemote;
    @EJB
    private LN_C_SFNivelRemote ln_C_SFNivelRemote;
    @EJB
    private LN_C_SFGradoRemote ln_C_SFGradoRemote;
    @EJB
    private LN_T_SFMainLocal ln_T_SFMainLocal;
    /** Temporal  */
    @EJB
    private BDL_T_SFMainRemoto bDL_T_SFMainRemoto;
    @EJB
    private BDL_C_SFMainRemote bdl_C_SFMainRemote;   
    @EJB
    private BDL_T_SFUtilsRemote bdl_T_SFUtilsRemote;
    private bSessionMigrarExcel sessionMigrarExcel;
    private RichSelectOneChoice choiceSede;
    private RichInputFile inputFileExcel;
    FacesContext ctx = FacesContext.getCurrentInstance();
    private RichPopup popupConfirmarMigracion;
    private RichButton btnSubirArchivo;
    /** Nueva carga */
    private RichSelectOneChoice cbSede;
    private RichSelectOneChoice cbProf;
    private RichSelectOneChoice cbCurso;
    private RichSelectOneChoice cbAula;
    private RichTable tbMain;
    private RichMessages msjGen;
    private RichButton btnAddMain;
    private RichButton btnModMain;
    private RichTable tbAulas;
    private RichSelectOneChoice choiceNivel;
    private RichSelectOneChoice choiceGrado;
    private RichSelectOneChoice choiceSedAula;
    private RichInputText inputDescAula;
    private RichButton btnEditarSave;
    private RichButton btnNuevaAula;
    private RichSelectOneChoice choiceEstadoAula;
    private RichButton btnRegistrarAula;
    private RichPopup popupCountMain;
    private RichButton btnGrabar;

    public bMigrarExcel() {
    }

    @PostConstruct
    public void methodInvokeOncedOnPageLoad() {
    //    sessionMigrarExcel.setNidSede("2");
        sessionMigrarExcel.setListaSedesChoice(Utils.llenarComboString(ln_C_SFUtilsRemote.getSedesString_LN()));
    }

    public void llenarCombos() {
        sessionMigrarExcel.setListaSedesChoice(Utils.llenarComboString(ln_C_SFUtilsRemote.getSedesString_LN()));
        sessionMigrarExcel.setListaProfesChoice(Utils.llenarComboString(ln_C_SFUtilsRemote.getProfesorActivosFull_LN()));
        sessionMigrarExcel.setListaCursosChoice(Utils.llenarComboString(ln_C_SFUtilsRemote.getCursosActivos_LN()));
        sessionMigrarExcel.setEstChoiceSede(false);
        sessionMigrarExcel.setEstChoiceProf(false);
        sessionMigrarExcel.setEstChoiceCurso(false);
        Utils.addTargetMany(cbSede,cbProf,cbCurso);
    }

    /** ****************************************************************************************************************/
    public void vclAccion(ValueChangeEvent vcl) {
        try{
            String val = (String) vcl.getNewValue();
            if(val != null){
                llenarCombos();
                sessionMigrarExcel.setAccionSess(val);
                sessionMigrarExcel.setNidMainModif(null);
                if("NEW".equals(val)){
                    sessionMigrarExcel.setDisabBtnNewMain(false);
                    sessionMigrarExcel.setDisabBtnModMain(true);
                    sessionMigrarExcel.setDisabBtnGrabMain(false);
                    sessionMigrarExcel.setVisibBtnGrabMain(true);
                    sessionMigrarExcel.setVisibBtnModMain(true);
                    sessionMigrarExcel.setVisibBtnNewMain(true);
                    
                    sessionMigrarExcel.setCidSedeHorarioSess(null);
                    sessionMigrarExcel.setDniProfSess(null);
                    sessionMigrarExcel.setCidAulaSess(null);
                    sessionMigrarExcel.setCidCursoSess(null);
                    sessionMigrarExcel.setNombresProf(null);
                    sessionMigrarExcel.setDescCurso(null);
                    sessionMigrarExcel.setDescSede(null);
                    sessionMigrarExcel.setDescAula(null);
                    sessionMigrarExcel.setEstChoiceAula(true);
                    sessionMigrarExcel.getLstMain().clear();
                    sessionMigrarExcel.getListaAulasChoice().clear();
                    tbMain.setValue(sessionMigrarExcel.getLstMain());
                }else if("MOD".equals(val)){
                    sessionMigrarExcel.setDisabBtnNewMain(true);
                    sessionMigrarExcel.setDisabBtnModMain(false);
                    sessionMigrarExcel.setDisabBtnGrabMain(true);
                    sessionMigrarExcel.setVisibBtnGrabMain(false);
                    sessionMigrarExcel.setVisibBtnModMain(true);
                    sessionMigrarExcel.setVisibBtnNewMain(false);
                    sessionMigrarExcel.getListaAulasChoice().clear();
                    this.refreshTablaMain();
                }
                Utils.addTargetMany(btnAddMain,btnModMain,btnGrabar,tbMain,cbAula);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void vclSede(ValueChangeEvent vcl) {
        String cidSede = (String) vcl.getNewValue();
        if(cidSede != null){
            sessionMigrarExcel.setListaAulasChoice(Utils.llenarComboString(ln_C_SFUtilsRemote.getAulasBySede_Activos_LN(Integer.parseInt(cidSede))));
            sessionMigrarExcel.setEstChoiceAula(false);
        }else{
            sessionMigrarExcel.getListaAulasChoice().clear();
            sessionMigrarExcel.setEstChoiceAula(true);
        }
        Utils.addTarget(cbAula);
        sessionMigrarExcel.setDescSede(Utils.getChoiceLabel(vcl));
        sessionMigrarExcel.setCidSedeHorarioSess(cidSede);
        sessionMigrarExcel.setCidAulaSess(null);
        if("MOD".equals(sessionMigrarExcel.getAccionSess()) && sessionMigrarExcel.getNidMainModif() == null){
            refreshTablaMain();
        }
    }
        
    public void vclSede2(ValueChangeEvent vcl) {
        String cidSede = (String) vcl.getNewValue();
        sessionMigrarExcel.setListaAulas(ln_C_SFAulaRemoto.getAulasBySedeGradoYNivelMigracion(cidSede, null, null));            
        sessionMigrarExcel.setListaNiveles(Utils.llenarCombo(ln_C_SFNivelRemote.getNivelLNPorSede_ByOrden(cidSede,null,null)));
        sessionMigrarExcel.setListGrados(null);
        sessionMigrarExcel.setNidGrado(null);
        sessionMigrarExcel.setNidNivel(null);           
        Utils.addTargetMany(tbAulas, choiceNivel,choiceGrado);
    }
 
    public void vclNivel(ValueChangeEvent vcl) {
        String cidNivel = (String) vcl.getNewValue();
        sessionMigrarExcel.setListaAulas(ln_C_SFAulaRemoto.getAulasBySedeGradoYNivelMigracion(sessionMigrarExcel.getCidSedeSess(), null, cidNivel));            
        sessionMigrarExcel.setListGrados(Utils.llenarCombo(ln_C_SFGradoRemote.getGradoLN_PorNivelByOrden(cidNivel)));            
        Utils.addTargetMany(tbAulas, choiceGrado);
    }
        
    public void vclGrado(ValueChangeEvent vcl) {
        String cidGrado = (String) vcl.getNewValue();
        sessionMigrarExcel.setDisableBtnEditar(false);
        sessionMigrarExcel.setDisableBtnNuevo(false);
        sessionMigrarExcel.setListaAulas(ln_C_SFAulaRemoto.getAulasBySedeGradoYNivelMigracion(sessionMigrarExcel.getCidSedeSess(), 
                                                                                              cidGrado, sessionMigrarExcel.getNidNivel()));            
        Utils.addTargetMany(tbAulas,btnEditarSave,btnNuevaAula);
    }  
    
    public void seleccionarAula(SelectionEvent se) {
        BeanAula aula = (BeanAula) Utils.getRowTable(se);
        if(bdl_C_SFMainRemote.countMainByAulaForEval(aula.getNidAula().toString())==0){//SI NO TIENE EVALUACIONES REALIZADAS
           //PERMITIRA CAMBIAR NOMBRE, GRADO Y NIVEL 
            sessionMigrarExcel.setBeanAula(aula);
            sessionMigrarExcel.setDescAula(aula.getDescripcionAula());
            sessionMigrarExcel.setNidNivel(aula.getGradoNivel().getNivel().getNidNivel().toString());
            sessionMigrarExcel.setNidGrado(aula.getGradoNivel().getGrado().getNidGrado().toString());
            sessionMigrarExcel.setFlgActivo(aula.getFlgActi());
            sessionMigrarExcel.setEstadoDescAula(true);//visible
            sessionMigrarExcel.setDisableChoiceSede(true);
            sessionMigrarExcel.setDisableChoiceNivel(false);
            sessionMigrarExcel.setDisableChoiceGrado(false);      
            sessionMigrarExcel.setVisibleRegistrar(true); 
            sessionMigrarExcel.setDisableDescripcionAula(false);
        }else{// SI EL AULA TIENE EVALUACIONES REALIZADAS
            // NO PERMITIRA CAMBIAR NOMBRE, GRADO NI NIVEL SOLO EL ESTADO
            sessionMigrarExcel.setBeanAula(aula);
            sessionMigrarExcel.setDescAula(aula.getDescripcionAula());
            sessionMigrarExcel.setNidNivel(aula.getGradoNivel().getNivel().getNidNivel().toString());
            sessionMigrarExcel.setNidGrado(aula.getGradoNivel().getGrado().getNidGrado().toString());
            sessionMigrarExcel.setFlgActivo(aula.getFlgActi());
            sessionMigrarExcel.setEstadoDescAula(true);//visible
            sessionMigrarExcel.setDisableChoiceSede(true);
            sessionMigrarExcel.setDisableChoiceNivel(true);
            sessionMigrarExcel.setDisableChoiceGrado(true);      
            sessionMigrarExcel.setVisibleRegistrar(true);
            sessionMigrarExcel.setDisableDescripcionAula(true);
        }
        sessionMigrarExcel.setVisibleChoiceEstado(true);                
        Utils.addTargetMany(choiceGrado,choiceNivel,choiceSedAula,inputDescAula,choiceEstadoAula,btnRegistrarAula);
    }
    
    public void editarAula(ActionEvent actionEvent) {
        sessionMigrarExcel.setExec(1);//Actualizar
        sessionMigrarExcel.setTablaSeleccionable("single");
        sessionMigrarExcel.setDisableBtnNuevo(true); 
        sessionMigrarExcel.setRequeridInput(true);
        sessionMigrarExcel.setDisableBtnEditar(true);
        Utils.addTargetMany(tbAulas,btnNuevaAula,btnEditarSave);
         
    }

    public void nuevaAula(ActionEvent actionEvent) {
       sessionMigrarExcel.setExec(0);//Nuevo
       sessionMigrarExcel.setDescAula("");
       sessionMigrarExcel.setEstadoDescAula(true);
       sessionMigrarExcel.setVisibleChoiceEstado(true);
       sessionMigrarExcel.setFlgActivo(1);
       sessionMigrarExcel.setVisibleRegistrar(true);
       sessionMigrarExcel.setDisableBtnNuevo(true);
       sessionMigrarExcel.setDisableBtnEditar(true);
       sessionMigrarExcel.setRequeridInput(true);
       Utils.addTargetMany(tbAulas,btnNuevaAula,inputDescAula,choiceEstadoAula,btnRegistrarAula,btnEditarSave);
    }

    public void vclAula(ValueChangeEvent vce) {
        sessionMigrarExcel.setCidAulaSess((String) vce.getNewValue());
        sessionMigrarExcel.setDescAula(Utils.getChoiceLabel(vce));
        if("MOD".equals(sessionMigrarExcel.getAccionSess()) && sessionMigrarExcel.getNidMainModif() == null){
            refreshTablaMain();
        }
    }

    public void vclProfesor(ValueChangeEvent vce) {
        sessionMigrarExcel.setDniProfSess((String) vce.getNewValue());
        sessionMigrarExcel.setNombresProf(Utils.getChoiceLabel(vce));
        if("MOD".equals(sessionMigrarExcel.getAccionSess()) && sessionMigrarExcel.getNidMainModif() == null){
            refreshTablaMain();
        }
    }

    public void vclCurso(ValueChangeEvent vce) {
        sessionMigrarExcel.setCidCursoSess((String) vce.getNewValue());
        sessionMigrarExcel.setDescCurso(Utils.getChoiceLabel(vce));
        if("MOD".equals(sessionMigrarExcel.getAccionSess()) && sessionMigrarExcel.getNidMainModif() == null){
            refreshTablaMain();
        }
    }
    
    public void btnNuevoMain(ActionEvent ae) {
        if(sessionMigrarExcel.getCidSedeHorarioSess() != null && sessionMigrarExcel.getCidAulaSess() != null &&
           sessionMigrarExcel.getDniProfSess() != null && sessionMigrarExcel.getCidCursoSess() != null){
            int contMain = ln_C_SFMainRemote.countMainByNidsEstado_LN(sessionMigrarExcel.getCidCursoSess(),
                                                                      sessionMigrarExcel.getCidAulaSess(),
                                                                      sessionMigrarExcel.getDniProfSess());
            int contTempMain = this.contMainTemp(sessionMigrarExcel.getDniProfSess(), sessionMigrarExcel.getCidCursoSess(),
                                                 sessionMigrarExcel.getCidAulaSess());
            int contFinal = 0;
            if("NEW".equals(sessionMigrarExcel.getAccionSess())){
                contFinal = contMain + contTempMain;
            }else if("MOD".equals(sessionMigrarExcel.getAccionSess())){
                contFinal = contMain;
            }
            if(contFinal == 0){//OK agregar
                BeanMainWS bMain = new BeanMainWS();
                bMain.setNidMain(sessionMigrarExcel.getLstMain().size() + 1);
                bMain.setDniProfesor(sessionMigrarExcel.getDniProfSess());
                bMain.setNidCurso(Integer.parseInt(sessionMigrarExcel.getCidCursoSess()));
                bMain.setNidAula(Integer.parseInt(sessionMigrarExcel.getCidAulaSess()));
                bMain.setNidSede(Integer.parseInt(sessionMigrarExcel.getCidSedeHorarioSess()));
                bMain.setSede(sessionMigrarExcel.getDescSede());
                bMain.setAula(sessionMigrarExcel.getDescAula());
                bMain.setCurso(sessionMigrarExcel.getDescCurso());
                bMain.setProfesor(sessionMigrarExcel.getNombresProf());
                sessionMigrarExcel.getLstMain().add(bMain.getNidMain()-1,bMain);
                tbMain.setValue(sessionMigrarExcel.getLstMain());
                Utils.addTarget(tbMain);
            }else{
                msjGen.setText("Registro repetido");
                Utils.addTarget(msjGen);
                Utils.mostrarMensaje(ctx, "Ya existe el registro", "Error", 1);
            }
        }else{
            msjGen.setText("Seleccionar valores");
            Utils.addTarget(msjGen);
            Utils.mostrarMensaje(ctx, "Seleccionar los campos", "Error", 1);
        }
    }
    
    public void btnModificarMain(ActionEvent ae) {
        int nidMain = Integer.parseInt(ae.getComponent().getAttributes().get("nidMain").toString());
        String cidSede = ae.getComponent().getAttributes().get("nidSede").toString();
        String dni = ae.getComponent().getAttributes().get("dni").toString();
        String cidAula = ae.getComponent().getAttributes().get("nidAula").toString();
        String cidCurso = ae.getComponent().getAttributes().get("nidCurso").toString();
        String profesor = ae.getComponent().getAttributes().get("profesor").toString();
        String curso = ae.getComponent().getAttributes().get("curso").toString();
        String sede = ae.getComponent().getAttributes().get("sede").toString();
        String aula = ae.getComponent().getAttributes().get("aula").toString();
        sessionMigrarExcel.setListaAulasChoice(Utils.llenarComboString(ln_C_SFUtilsRemote.getAulasBySede_Activos_LN(Integer.parseInt(cidSede))));
        sessionMigrarExcel.setNidMainModif(nidMain);
        sessionMigrarExcel.setCidSedeHorarioSess(cidSede);
        sessionMigrarExcel.setDniProfSess(dni);
        sessionMigrarExcel.setCidAulaSess(cidAula);
        sessionMigrarExcel.setCidCursoSess(cidCurso);
        sessionMigrarExcel.setNombresProf(profesor);
        sessionMigrarExcel.setDescCurso(curso);
        sessionMigrarExcel.setDescSede(sede);
        sessionMigrarExcel.setDescAula(aula);
        sessionMigrarExcel.setDisabBtnModMain(false);
        sessionMigrarExcel.setDisabBtnNewMain(true);
        sessionMigrarExcel.setEstChoiceAula(false);
        if("NEW".equals(sessionMigrarExcel.getAccionSess())){
            sessionMigrarExcel.setDisabBtnModMain(false);
            sessionMigrarExcel.setDisabBtnNewMain(true);
        }else if("MOD".equals(sessionMigrarExcel.getAccionSess())){
            sessionMigrarExcel.setDisabBtnModMain(false);
        }
        Utils.addTargetMany(cbSede,cbAula,cbProf,cbCurso,btnAddMain,btnModMain);
    }
    
    public void btnModMain_Action(ActionEvent ae) {
        if(sessionMigrarExcel.getCidSedeHorarioSess() != null && sessionMigrarExcel.getCidAulaSess() != null &&
           sessionMigrarExcel.getDniProfSess() != null && sessionMigrarExcel.getCidCursoSess() != null && 
           sessionMigrarExcel.getNidMainModif() != null){
            BeanMainWS bMain = this.getMainByNid(sessionMigrarExcel.getNidMainModif().intValue());
            int nidCurso = Integer.parseInt(sessionMigrarExcel.getCidCursoSess());
            int nidAula = Integer.parseInt(sessionMigrarExcel.getCidAulaSess());
            int nidSede = Integer.parseInt(sessionMigrarExcel.getCidSedeHorarioSess());
            if(bMain.getDniProfesor().equals(sessionMigrarExcel.getDniProfSess()) && 
               bMain.getNidCurso().intValue() == nidCurso &&  bMain.getNidAula() == nidAula &&
               bMain.getNidSede().intValue() == nidSede){
                msjGen.setText("No ha modificado los valores");
                Utils.addTarget(msjGen);
                Utils.mostrarMensaje(ctx, "Valores no modificados", "Info", 4);
                return;
            }
            bMain.setDniProfesor(sessionMigrarExcel.getDniProfSess());
            bMain.setNidCurso(nidCurso);
            bMain.setNidAula(nidAula);
            bMain.setNidSede(nidSede);
            bMain.setSede(sessionMigrarExcel.getDescSede());
            bMain.setAula(sessionMigrarExcel.getDescAula());
            bMain.setCurso(sessionMigrarExcel.getDescCurso());
            bMain.setProfesor(sessionMigrarExcel.getNombresProf());
            sessionMigrarExcel.setDisabBtnModMain(true);
            sessionMigrarExcel.setDisabBtnNewMain(false);
            if("NEW".equals(sessionMigrarExcel.getAccionSess())){
                sessionMigrarExcel.getLstMain().remove(bMain.getNidMain() - 1);
                sessionMigrarExcel.getLstMain().add(bMain.getNidMain()-1,bMain);
                sessionMigrarExcel.setCidSedeHorarioSess(null);
                sessionMigrarExcel.setDniProfSess(null);
                sessionMigrarExcel.setCidAulaSess(null);
                sessionMigrarExcel.setCidCursoSess(null);
                sessionMigrarExcel.setNombresProf(null);
                sessionMigrarExcel.setDescCurso(null);
                sessionMigrarExcel.setDescSede(null);
                sessionMigrarExcel.setDescAula(null);
            }else if("MOD".equals(sessionMigrarExcel.getAccionSess())){
                List<BeanMainWS> lstAdd = new ArrayList<BeanMainWS>();
                lstAdd.add(bMain);
                this.grabarAux(lstAdd,sessionMigrarExcel.getAccionSess());
                lstAdd.clear();
            }
            sessionMigrarExcel.setNidMainModif(null);
            Utils.addTargetMany(tbMain,btnModMain,btnAddMain,cbSede,cbAula,cbProf,cbCurso);
        }else{
            msjGen.setText("Seleccionar valores");
            Utils.addTarget(msjGen);
            Utils.mostrarMensaje(ctx, "Seleccionar los campos", "Error", 1);
        }
    }
    
    public void btnGrabarMain_Action(ActionEvent ae) {
        this.grabarAux(sessionMigrarExcel.getLstMain(),sessionMigrarExcel.getAccionSess());
    }
    
    public void grabarAux(List<BeanMainWS> listaAdd, String modoGrabar){
        String msjError = null;
        String titulo = null;
        int tip = 1;
        if(listaAdd.size() > 0){
            String error = ln_T_SFMainLocal.agregarMainMigracion(listaAdd,modoGrabar);
            if("000".equals(error)){
                msjError = "Se cargo la informacion.";
                titulo = "Que bien!";
                tip = 3;
            }else{
                msjError = "Hubo un error al cargar la informacion.";
                titulo = "Error";
            }
        }else{
            msjError = "No hay registros que guardar.";
            titulo = "Error";
        }
        msjGen.setText(msjError);
        Utils.addTarget(msjGen);
        Utils.mostrarMensaje(ctx, msjError,titulo,tip);
        if("NEW".equals(modoGrabar)){
            sessionMigrarExcel.setDisabBtnModMain(true);
            sessionMigrarExcel.setDisabBtnNewMain(false);
            sessionMigrarExcel.setDisabBtnGrabMain(true);
            sessionMigrarExcel.setNidMainModif(null);
            sessionMigrarExcel.setCidSedeHorarioSess(null);
            sessionMigrarExcel.setDniProfSess(null);
            sessionMigrarExcel.setCidAulaSess(null);
            sessionMigrarExcel.setCidCursoSess(null);
            sessionMigrarExcel.setNombresProf(null);
            sessionMigrarExcel.setDescCurso(null);
            sessionMigrarExcel.setDescSede(null);
            sessionMigrarExcel.setDescAula(null);
            sessionMigrarExcel.getLstMain().clear();
        }else if("MOD".equals(modoGrabar)){
            this.refreshTablaMain();   
        }
        Utils.addTargetMany(tbMain,btnModMain,btnAddMain,cbSede,cbAula,cbProf,cbCurso);
    }
    
    public void refreshTablaMain(){
        if(sessionMigrarExcel.getLstMain() != null){
            sessionMigrarExcel.getLstMain().clear();   
        }
        sessionMigrarExcel.setLstMain(ln_C_SFMainRemote.getListaMain_Activos(sessionMigrarExcel.getCidSedeHorarioSess(),
                                                                             sessionMigrarExcel.getCidAulaSess(),
                                                                             sessionMigrarExcel.getDniProfSess(),
                                                                             sessionMigrarExcel.getCidCursoSess()));
        tbMain.setValue(sessionMigrarExcel.getLstMain());
        Utils.addTarget(tbMain);
    }
    
    public void btnRefreshCombos(ActionEvent ae) {
        llenarCombos();
    }
    
    public BeanMainWS getMainByNid(int nidMain){
        Iterator it = sessionMigrarExcel.getLstMain().iterator();
        while(it.hasNext()){
            BeanMainWS bMain = (BeanMainWS) it.next();
            if(bMain.getNidMain() == nidMain){
                return bMain;
            }
        }
        return null;
    }
    
    public int contMainTemp(String dni, String cidCurso, String cidAula){
        Iterator it = sessionMigrarExcel.getLstMain().iterator();
        int nidCurso = Integer.parseInt(cidCurso);
        int nidAula = Integer.parseInt(cidAula);
        int res = 0;
        while(it.hasNext()){
            BeanMainWS bMain = (BeanMainWS) it.next();
            if(bMain.getDniProfesor().equals(dni) && bMain.getNidCurso().intValue() == nidCurso
               && bMain.getNidAula().intValue() == nidAula){
                   return 1;
               }
        }
        return res;
    }

    public void saveAula(){
        sessionMigrarExcel.getBeanAula().setDescripcionAula(sessionMigrarExcel.getDescAula());
        sessionMigrarExcel.getBeanAula().setFlgActi(sessionMigrarExcel.getFlgActivo());
        ln_T_SFAulaRemoto.actualizarAula(sessionMigrarExcel.getBeanAula()); 
        sessionMigrarExcel.setListaAulas(ln_C_SFAulaRemoto.getAulasBySedeGradoYNivelMigracion(sessionMigrarExcel.getCidSedeSess(), sessionMigrarExcel.getNidGrado(), sessionMigrarExcel.getNidNivel()));            
        sessionMigrarExcel.setBeanAula(null);
        sessionMigrarExcel.setDisableBtnNuevo(false);
        sessionMigrarExcel.setDisableChoiceSede(false);
        sessionMigrarExcel.setDisableChoiceNivel(false);
        sessionMigrarExcel.setDisableChoiceGrado(false);
        sessionMigrarExcel.setVisibleChoiceEstado(false);
        sessionMigrarExcel.setEstadoDescAula(false);
        sessionMigrarExcel.setVisibleRegistrar(false);
        sessionMigrarExcel.setTablaSeleccionable("none"); 
        sessionMigrarExcel.setRequeridInput(false);
        sessionMigrarExcel.setDisableBtnEditar(false);
        Utils.addTargetMany(tbAulas,btnNuevaAula,inputDescAula,btnRegistrarAula,choiceEstadoAula,choiceGrado,choiceNivel,choiceSedAula,btnEditarSave);
    }
    
    public void confirmarDesactivacionAula(ActionEvent actionEvent) {
        bdl_T_SFUtilsRemote.desactivarMainByAula(sessionMigrarExcel.getBeanAula().getNidAula().toString());  
        saveAula();        
    }

    public void cancelarDesactivacionAula(ActionEvent actionEvent) {
      popupCountMain.hide();
    }
    
    public void registrarAula(ActionEvent actionEvent) {
        if(sessionMigrarExcel.getExec() == 1){
            if(sessionMigrarExcel.getBeanAula() != null){
                int contMain=bdl_C_SFMainRemote.countMainInactivosByAula(sessionMigrarExcel.getBeanAula().getNidAula().toString());
                if(contMain>0 && sessionMigrarExcel.getFlgActivo() == 0){
                    sessionMigrarExcel.setNumMainActivos(contMain);
                    Utils.showPopUpMIDDLE(popupCountMain);
                }else{
                    saveAula();
                }
            }
        }else{
            BeanAula aula = new BeanAula();
            aula.setDescripcionAula(sessionMigrarExcel.getDescAula());
            BeanGradoNivel grani = new BeanGradoNivel();
            BeanGrado grado = new BeanGrado();       
            grado.setNidGrado(Integer.parseInt(sessionMigrarExcel.getNidGrado()));
            BeanNivel nivel = new BeanNivel();        
            nivel.setNidNivel(Integer.parseInt(sessionMigrarExcel.getNidNivel()));        
            grani.setGrado(grado);
            grani.setNivel(nivel);
            aula.setGradoNivel(grani);
            BeanSede sede = new BeanSede();
            sede.setNidSede(Integer.parseInt(sessionMigrarExcel.getCidSedeSess()));
            aula.setSede(sede);
            aula.setFlgActi(sessionMigrarExcel.getFlgActivo());
            ln_T_SFAulaRemoto.grabarAula(aula);
            sessionMigrarExcel.setVisibleRegistrar(false);
            sessionMigrarExcel.setEstadoDescAula(false);
            sessionMigrarExcel.setVisibleChoiceEstado(false);
            sessionMigrarExcel.setDisableBtnNuevo(false);
            sessionMigrarExcel.setDisableBtnEditar(false);
            sessionMigrarExcel.setRequeridInput(false);
            sessionMigrarExcel.setListaAulas(ln_C_SFAulaRemoto.getAulasBySedeGradoYNivelMigracion(sessionMigrarExcel.getCidSedeSess(), sessionMigrarExcel.getNidGrado(), sessionMigrarExcel.getNidNivel()));            
            Utils.addTargetMany(tbAulas,inputDescAula,btnRegistrarAula,choiceEstadoAula,btnNuevaAula,btnEditarSave);
        }
    }     

    public String migrarExcel() {
        Utils.showPopUpMIDDLE(popupConfirmarMigracion);
        return null;
    }

    public void confirmarMigracion(ActionEvent actionEvent) throws IOException {
        leerExcel(sessionMigrarExcel.getInputStreamFile());
        inputFileExcel.setValue(null);
        Utils.addTarget(inputFileExcel);
    }

    public void cancelarMigracion(ActionEvent actionEvent) {
        popupConfirmarMigracion.hide();
    }

    public void seleccionaExcel(ValueChangeEvent valueChangeEvent) {
        try {
            UploadedFile file = (UploadedFile) valueChangeEvent.getNewValue();
            if (Utils.validarExtensionXls(file.getFilename())) {
                sessionMigrarExcel.setFile(file);
                sessionMigrarExcel.setInputStreamFile(file.getInputStream());
                sessionMigrarExcel.setNombreArchivo(file.getFilename());
                sessionMigrarExcel.setEstadoBtnSubArchivo(false);
                Utils.addTarget(btnSubirArchivo);
            } else {
                sessionMigrarExcel.setEstadoBtnSubArchivo(true);
                Utils.addTarget(btnSubirArchivo);
                Utils.mostrarMensaje(ctx, "El archivo no es de tipo excel suba un xls/xlsx", null, 4);
            }
        } catch (Exception e) {
            Utils.mostrarMensaje(ctx, "Hubo un error a subir el Archivo ingrese nuevamente", null, 4);
        }
    }

    public List leerExcelXLSX(InputStream file) {
        List sheetData = new ArrayList();
        try {
            XSSFWorkbook wb = new XSSFWorkbook(file);
            XSSFSheet sheet = wb.getSheetAt(0);
            Iterator<Row> rows = sheet.rowIterator();
            Row row;
            Cell cell;
            while (rows.hasNext()) {
                row = rows.next();
                Iterator<Cell> cellIterator = row.cellIterator();
                List data = new ArrayList();
                while (cellIterator.hasNext()) {
                    cell = cellIterator.next();
                    data.add(cell);
                }
                sheetData.add(data);
            }
            return sheetData;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public List leerExcelXLS(InputStream file) {
        List sheetData = new ArrayList();
        try {
            HSSFWorkbook workbook = new HSSFWorkbook(file);
            HSSFSheet sheet = workbook.getSheetAt(0);
            Iterator rows = sheet.rowIterator();
            while (rows.hasNext()) {
                HSSFRow row = (HSSFRow) rows.next();
                Iterator cells = row.cellIterator();

                List data = new ArrayList();
                while (cells.hasNext()) {
                    HSSFCell cell = (HSSFCell) cells.next();
                    data.add(cell);
                }
                sheetData.add(data);
            }
            return sheetData;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public void leerExcel(InputStream file) throws IOException {
        List sheetData = new ArrayList();
        try {
            String extension =
                sessionMigrarExcel.getNombreArchivo().substring(sessionMigrarExcel.getNombreArchivo().lastIndexOf(".") +
                                                                1, sessionMigrarExcel.getNombreArchivo().length());
            if (extension.equalsIgnoreCase("xls")) {
                sheetData = leerExcelXLS(file);
            } else {
                sheetData = leerExcelXLSX(file);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (file != null) {
                file.close();
            }
        }
        if (sessionMigrarExcel.getTipoMigracion() == 1) {
            insertarCursos(sheetData);
        }
        if (sessionMigrarExcel.getTipoMigracion() == 2) {
            insertarAreaAcademica(sheetData);
        }
        if (sessionMigrarExcel.getTipoMigracion() == 3) {
            insertarAulas(sheetData);
        }
        if (sessionMigrarExcel.getTipoMigracion() == 4) {
            insertarProfesores(sheetData);
        }
        /**Temporal */
        if (sessionMigrarExcel.getTipoMigracion() == 5) {
            insertarMain(sheetData);
        }
    }

    private void insertarCursos(List sheetData) {
        List<BeanCurso> listcursosAInsertar = new ArrayList<BeanCurso>();
        for (int i = 0; i < sheetData.size(); i++) {
            BeanCurso curso = new BeanCurso();
            BeanAreaAcademica area = new BeanAreaAcademica();
            List list = (List) sheetData.get(i);
            if (!list.isEmpty()) {
                Cell cell = (Cell) list.get(0);
                if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC || cell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
                    curso.setNidCurso((int) cell.getNumericCellValue());
                    curso.setDescripcionCurso(list.get(1).toString());
                    Cell cell2 = (Cell) list.get(2);
                    area.setNidAreaAcademica((int) cell2.getNumericCellValue());
                    curso.setAreaAcademica(area);
                    curso.setTipoFichaCurso(list.get(3).toString());
                    Utils.sysout(curso.getNidCurso() + " - " + curso.getDescripcionCurso() + " - " +
                                 curso.getAreaAcademica().getNidAreaAcademica() + " - " + curso.getTipoFichaCurso());
                    listcursosAInsertar.add(curso);
                }
            }
        }
        List<BeanCurso> listaActual = ln_C_SFCursoRemoto.getlistaCursos();
        if (listaActual != null) {
            for (int j = 0; j < listaActual.size(); j++) {
                for (int i = 0; i < listcursosAInsertar.size(); i++) {
                    if (listaActual.get(j).getNidCurso().intValue() ==
                        listcursosAInsertar.get(i).getNidCurso().intValue()) {
                        listcursosAInsertar.remove(i);
                    }
                }
            }
            if (listcursosAInsertar != null) {
                ln_T_SFCursoRemoto.grabarCursosNuevos(listcursosAInsertar);
            }
        } else {
            ln_T_SFCursoRemoto.grabarCursosNuevos(listcursosAInsertar);
        }
    }

    private void insertarAreaAcademica(List sheetData) {
        List<BeanAreaAcademica> listAreasAInsertar = new ArrayList<BeanAreaAcademica>();
        for (int i = 0; i < sheetData.size(); i++) {
            BeanAreaAcademica area = new BeanAreaAcademica();
            List list = (List) sheetData.get(i);
            if (!list.isEmpty()) {
                Cell cell = (Cell) list.get(0);
                if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC || cell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
                    area.setNidAreaAcademica((int) cell.getNumericCellValue());
                    area.setDescripcionAreaAcademica(list.get(1).toString());

                    Utils.sysout(area.getNidAreaAcademica() + " - " + area.getDescripcionAreaAcademica());
                    listAreasAInsertar.add(area);
                }
            }
        }
        List<BeanAreaAcademica> listaActual = ln_C_SFAreaAcademicaRemote.getAreaAcademicaLN();
        if (listaActual != null) {
            for (int j = 0; j < listaActual.size(); j++) {
                for (int i = 0; i < listAreasAInsertar.size(); i++) {
                    if (listaActual.get(j).getNidAreaAcademica().intValue() ==
                        listAreasAInsertar.get(i).getNidAreaAcademica().intValue()) {
                        listAreasAInsertar.remove(i);
                        System.out.println("QUITO:");
                    }
                }
            }
            if (listAreasAInsertar != null) {
                ln_T_SFAreaAcademicaRemoto.grabarAreasNuevas(listAreasAInsertar);
            }

        } else {
            ln_T_SFAreaAcademicaRemoto.grabarAreasNuevas(listAreasAInsertar);
        }
    }

    private void insertarAulas(List sheetData) {
        List<BeanAula> listaulasAInsertar = new ArrayList<BeanAula>();
        for (int i = 0; i < sheetData.size(); i++) {
            BeanAula aula = new BeanAula();
            BeanGrado grado = new BeanGrado();
            BeanNivel nivel = new BeanNivel();
            BeanGradoNivel grani = new BeanGradoNivel();
            BeanSede sede = new BeanSede();
            List list = (List) sheetData.get(i);
            if (!list.isEmpty()) {
                Cell cell = (Cell) list.get(0);
                if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC || cell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
                    aula.setNidAula((int) cell.getNumericCellValue());
                    aula.setDescripcionAula(list.get(1).toString());
                    Cell cell2 = (Cell) list.get(2);
                    sede.setNidSede((int) cell2.getNumericCellValue());
                    aula.setSede(sede);
                    Cell cell3 = (Cell) list.get(3);
                    grado.setNidGrado((int) cell3.getNumericCellValue());
                    Cell cell4 = (Cell) list.get(4);
                    nivel.setNidNivel((int) cell4.getNumericCellValue());
                    grani.setGrado(grado);
                    grani.setNivel(nivel);
                    aula.setGradoNivel(grani);
                    /* Utils.sysout(curso.getNidCurso() + " - " + curso.getDescripcionCurso() + " - " +
                       curso.getAreaAcademica().getNidAreaAcademica() + " - " + curso.getTipoFichaCurso());*/
                    listaulasAInsertar.add(aula);
                }
            }
        }
        List<BeanAula> listaActual = ln_C_SFAulaRemoto.getAreaAulaLN();
        if (listaActual != null) {
            for (int j = 0; j < listaActual.size(); j++) {
                for (int i = 0; i < listaulasAInsertar.size(); i++) {
                    if (listaActual.get(j).getNidAula().intValue() ==
                        listaulasAInsertar.get(i).getNidAula().intValue()) {
                        listaulasAInsertar.remove(i);
                    }
                }
            }
            if (listaulasAInsertar != null) {
                ln_T_SFAulaRemoto.grabarAulasNuevas(listaulasAInsertar);
            }
        } else {
            ln_T_SFAulaRemoto.grabarAulasNuevas(listaulasAInsertar);
        }
    }

    /**
     * Metodo que verifica si la celda el DNI tiene 8 caracteres y es numerico
     * @author dfloresgonz
     * @since 12.04.2014
     * @param valorCelda - el valor de la celda
     * @return si es TRUE = es dni, FALSE = titulo
     */
    public boolean isDNI(String valorCelda) { //puede ser el titulo/dni
        if (Utils.isNumeric(valorCelda)) {
            int len = valorCelda.length();
            if (len == 8) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    private void insertarProfesores(List sheetData) {
        List<BeanProfesor> listProfesoresAInsertar = new ArrayList<BeanProfesor>();
        for (int i = 0; i < sheetData.size(); i++) {
            List list = (List) sheetData.get(i);
            if (!list.isEmpty()) {
                BeanProfesor profe = new BeanProfesor();
                Cell cell = (Cell) list.get(0);
                //dfloresgonz 12.04.2014
                //EDUSYS tiene que mandar la celda como string sino no vendran ls dni con 8 caracteres
                if (this.isDNI(cell.getStringCellValue())) {
                    profe.setDniProfesor(cell.getStringCellValue());
                    profe.setNombres(list.get(1).toString());
                    profe.setApellidos(list.get(2).toString());
                    profe.setCorreo(list.get(3).toString());
                    Utils.sysout("dni: " + profe.getDniProfesor() + " corr: " + profe.getCorreo() + " nom: " +
                                 profe.getNombres() + " ape: " + profe.getApellidos());
                    listProfesoresAInsertar.add(profe);
                }
                //FIN dfloresgonz 12.04.2014
            }
        }
        ln_T_SFUsuarioRemote.cambiarEstadoUsuarioProfesores(listProfesoresAInsertar);
        List<BeanProfesor> listaActual = ln_C_SFProfesorRemote.getProfesoresLN2();
        if (listaActual != null) {
            for (int j = 0; j < listaActual.size(); j++) {
                for (int i = 0; i < listProfesoresAInsertar.size(); i++) {
                    if (listaActual.get(j).getDniProfesor().equals(listProfesoresAInsertar.get(i).getDniProfesor())) {
                        listProfesoresAInsertar.remove(i);
                    }
                }
            }
            if (listProfesoresAInsertar != null && listProfesoresAInsertar.size() > 0) {
                ln_T_SFProfesorRemoto.grabarProfesoresNuevos(listProfesoresAInsertar);
            }

        } else {
            ln_T_SFProfesorRemoto.grabarProfesoresNuevos(listProfesoresAInsertar);
        }
    }

    public void seleccionarTipoMigracion(ValueChangeEvent valueChangeEvent) {
        if (choiceSede.getValue() == 1 || choiceSede.getValue() == 2 || choiceSede.getValue() == 3 ||
            choiceSede.getValue() == 4 || choiceSede.getValue() == 5) {
            sessionMigrarExcel.setEstadouploadFile(false);
            Utils.addTarget(inputFileExcel);
        } else {
            sessionMigrarExcel.setEstadouploadFile(true);
            Utils.addTarget(inputFileExcel);
        }
    }
    
    public void limpiarCamposYTabla(ActionEvent actionEvent) {
       sessionMigrarExcel.setBeanAula(null);
       sessionMigrarExcel.setTablaSeleccionable("none");
       sessionMigrarExcel.setDisableBtnEditar(true);
       sessionMigrarExcel.setDisableBtnNuevo(true);       
       sessionMigrarExcel.setDisableChoiceSede(false);
       sessionMigrarExcel.setDisableChoiceNivel(false);
       sessionMigrarExcel.setDisableChoiceGrado(false);
       sessionMigrarExcel.setDescAula(null);
       sessionMigrarExcel.setEstadoDescAula(false);
       sessionMigrarExcel.setFlgActivo(0);
       sessionMigrarExcel.setVisibleChoiceEstado(false);
       sessionMigrarExcel.setListaAulas(null);
       sessionMigrarExcel.setListaNiveles(null);
       sessionMigrarExcel.setListGrados(null);
       sessionMigrarExcel.setListaSedesChoice(null);
       sessionMigrarExcel.setCidSedeSess(null);
       sessionMigrarExcel.setNidNivel(null);
       sessionMigrarExcel.setNidGrado(null);  
       sessionMigrarExcel.setVisibleRegistrar(false);
       sessionMigrarExcel.setRequeridInput(false);
       sessionMigrarExcel.setListaSedesChoice(Utils.llenarComboString(ln_C_SFUtilsRemote.getSedesString_LN()));
       Utils.addTargetMany(choiceEstadoAula,tbAulas,choiceSedAula,choiceGrado,choiceNivel,btnEditarSave,btnNuevaAula,inputDescAula,btnRegistrarAula);
    }
    
    public void setPopupConfirmarMigracion(RichPopup popupConfirmarMigracion) {
        this.popupConfirmarMigracion = popupConfirmarMigracion;
    }

    public RichPopup getPopupConfirmarMigracion() {
        return popupConfirmarMigracion;
    }

    public void setBtnSubirArchivo(RichButton btnSubirArchivo) {
        this.btnSubirArchivo = btnSubirArchivo;
    }

    public RichButton getBtnSubirArchivo() {
        return btnSubirArchivo;
    }

    public void setChoiceSede(RichSelectOneChoice choiceSede) {
        this.choiceSede = choiceSede;
    }

    public RichSelectOneChoice getChoiceSede() {
        return choiceSede;
    }

    public void setSessionMigrarExcel(bSessionMigrarExcel sessionMigrarExcel) {
        this.sessionMigrarExcel = sessionMigrarExcel;
    }

    public bSessionMigrarExcel getSessionMigrarExcel() {
        return sessionMigrarExcel;
    }

    public void setInputFileExcel(RichInputFile inputFileExcel) {
        this.inputFileExcel = inputFileExcel;
    }

    public RichInputFile getInputFileExcel() {
        return inputFileExcel;
    }

    /** BORRRAR LO DE ABAJO LUEGO DE MIGRAR */

    private void insertarMain(List sheetData) {
        try {
            for (int i = 0; i < sheetData.size(); i++) {
                List list = (List) sheetData.get(i);
                if (!list.isEmpty()) {
                    Main ma = new Main();
                    Profesor profesor = new Profesor();
                    profesor.setDniProfesor(list.get(0).toString());
                    Aula aula = new Aula();
                    Cell cell = (Cell) list.get(1);
                    aula.setNidAula((int) cell.getNumericCellValue());
                    Curso curso = new Curso();
                    Cell cell2 = (Cell) list.get(2);
                    curso.setNidCurso((int) cell2.getNumericCellValue());

                    ma.setAula(aula);
                    ma.setCurso(curso);
                    ma.setProfesor(profesor);
                    ma.setEstado("1");
                    bDL_T_SFMainRemoto.persistMain(ma);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setCbSede(RichSelectOneChoice cbSede) {
        this.cbSede = cbSede;
    }

    public RichSelectOneChoice getCbSede() {
        return cbSede;
    }

    public void setCbProf(RichSelectOneChoice cbProf) {
        this.cbProf = cbProf;
    }

    public RichSelectOneChoice getCbProf() {
        return cbProf;
    }

    public void setCbCurso(RichSelectOneChoice cbCurso) {
        this.cbCurso = cbCurso;
    }

    public RichSelectOneChoice getCbCurso() {
        return cbCurso;
    }

    public void setCbAula(RichSelectOneChoice cbAula) {
        this.cbAula = cbAula;
    }

    public RichSelectOneChoice getCbAula() {
        return cbAula;
    }

    public void setTbMain(RichTable tbMain) {
        this.tbMain = tbMain;
    }

    public RichTable getTbMain() {
        return tbMain;
    }

    public void setMsjGen(RichMessages msjGen) {
        this.msjGen = msjGen;
    }

    public RichMessages getMsjGen() {
        return msjGen;
    }

    public void setBtnAddMain(RichButton btnAddMain) {
        this.btnAddMain = btnAddMain;
    }

    public RichButton getBtnAddMain() {
        return btnAddMain;
    }

    public void setBtnModMain(RichButton btnModMain) {
        this.btnModMain = btnModMain;
    }

    public RichButton getBtnModMain() {
        return btnModMain;
    }

    public void setTbAulas(RichTable tbAulas) {
        this.tbAulas = tbAulas;
    }

    public RichTable getTbAulas() {
        return tbAulas;
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


    public void setChoiceSedAula(RichSelectOneChoice choiceSedAula) {
        this.choiceSedAula = choiceSedAula;
    }

    public RichSelectOneChoice getChoiceSedAula() {
        return choiceSedAula;
    }


    public void setInputDescAula(RichInputText inputDescAula) {
        this.inputDescAula = inputDescAula;
    }

    public RichInputText getInputDescAula() {
        return inputDescAula;
    }

    public void setBtnEditarSave(RichButton btnEditarSave) {
        this.btnEditarSave = btnEditarSave;
    }

    public RichButton getBtnEditarSave() {
        return btnEditarSave;
    }

    public void setBtnNuevaAula(RichButton btnNuevaAula) {
        this.btnNuevaAula = btnNuevaAula;
    }

    public RichButton getBtnNuevaAula() {
        return btnNuevaAula;
    }

    public void setChoiceEstadoAula(RichSelectOneChoice choiceEstadoAula) {
        this.choiceEstadoAula = choiceEstadoAula;
    }

    public RichSelectOneChoice getChoiceEstadoAula() {
        return choiceEstadoAula;
    }

    public void setBtnRegistrarAula(RichButton btnRegistrarAula) {
        this.btnRegistrarAula = btnRegistrarAula;
    }

    public RichButton getBtnRegistrarAula() {
        return btnRegistrarAula;
    }   

    public void setPopupCountMain(RichPopup popupCountMain) {
        this.popupCountMain = popupCountMain;
    }

    public RichPopup getPopupCountMain() {
        return popupCountMain;
    }

    public void setBtnGrabar(RichButton btnGrabar) {
        this.btnGrabar = btnGrabar;
    }

    public RichButton getBtnGrabar() {
        return btnGrabar;
    }
}
