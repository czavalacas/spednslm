package sped.vista.beans.horarios;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.input.RichInputNumberSpinbox;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.input.RichSelectOneChoice;
import oracle.adf.view.rich.component.rich.nav.RichButton;
import oracle.adf.view.rich.component.rich.output.RichOutputText;
import org.apache.myfaces.trinidad.event.SelectionEvent;
import sped.negocio.LNSF.IL.LN_C_SFCalendarioLocal;
import sped.negocio.LNSF.IL.LN_C_SFUtilsLocal;
import sped.negocio.LNSF.IL.LN_T_SFCalendarioLocal;
import sped.negocio.LNSF.IL.LN_T_SFUtilsLocal;
import sped.negocio.LNSF.IR.LN_C_SFConfiguracionEventoHorarioRemoto;
import sped.negocio.LNSF.IR.LN_C_SFConfiguracionHorarioRemote;
import sped.negocio.LNSF.IR.LN_C_SFDuracionHorarioRemote;
import sped.negocio.LNSF.IR.LN_C_SFMainRemote;
import sped.negocio.LNSF.IR.LN_C_SFNivelRemote;
import sped.negocio.LNSF.IR.LN_C_SFSedeRemote;
import sped.negocio.LNSF.IR.LN_T_SFConfiguracionHorarioRemoto;
import sped.negocio.LNSF.IR.LN_T_SFDuracionHorarioRemoto;
import sped.negocio.entidades.beans.BeanCalendario;
import sped.negocio.entidades.beans.BeanConfiguracionEventoHorario;
import sped.negocio.entidades.beans.BeanConfiguracionHorario;
import sped.negocio.entidades.beans.BeanConstraint;
import sped.negocio.entidades.beans.BeanDuracionHorario;
import sped.vista.Utils.Utils;

public class bConfiguracionHorario {
    
    private RichSelectOneChoice choiceSede;
    private RichSelectOneChoice choiceNivel;
    private RichButton btnAgregarEventoHorario;
    private RichTable tbEventoHorario;
    private RichSelectOneChoice choiceEventoHorario;
    private RichButton btnGuardar;
    private RichInputText inputHoraInicioRestriccion;
    private RichInputText inputHoraFinRestriccion;
    private RichButton btnRemoverEventoHorario;
    private RichInputText inputHoraInicioClases;
    private RichInputNumberSpinbox inputNumeroBloques;
    private RichInputText inputDuracionXBloque;
    private RichInputNumberSpinbox inputMaxBloquesXCurs;
    private RichPopup popupConfigurarNuevaRestriccion;
    private RichButton btnEditarRestriccion;
    private RichInputText inputHoraFinClases;
    private RichPopup popupExisteHorario;
    private RichOutputText otError;
    private String errorDesc;
    private RichPopup popCalen;
    private RichTable tbCalen;
    private bSessionConfiguracionHorario sessionConfiguracionHorario;
    FacesContext ctx = FacesContext.getCurrentInstance();
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
    @EJB
    private LN_C_SFDuracionHorarioRemote ln_C_SFDuracionHorarioRemote;
    @EJB
    private LN_C_SFConfiguracionHorarioRemote ln_C_SFConfiguracionHorarioRemote;
    @EJB
    private LN_C_SFMainRemote ln_C_SFMainRemote;
    @EJB
    private LN_C_SFCalendarioLocal ln_C_SFCalendarioLocal;
    @EJB
    private LN_T_SFCalendarioLocal ln_T_SFCalendarioLocal;
    @EJB
    private LN_C_SFUtilsLocal ln_C_SFUtilsLocal;
    @EJB
    private LN_T_SFUtilsLocal ln_T_SFUtilsLocal;
    private String errorConfig;

    public bConfiguracionHorario() {

    }
    
    @PostConstruct
    public void methodInvokeOncedOnPageLoad() {
        if (sessionConfiguracionHorario.getExec() == 0) {
            sessionConfiguracionHorario.setExec(1);
            llenarCombos();
            sessionConfiguracionHorario.setLstBeanCons(ln_C_SFUtilsLocal.getMinMaxEvasPorDiaConfigConstraint_LN());
        }
    }

    public void getNivelesBySede(ValueChangeEvent vce) {
        if (choiceSede != null) {
            sessionConfiguracionHorario.setListaNivelesChoice(Utils.llenarCombo(ln_C_SFNivelRemote.getAllNivelesBySedes(choiceSede.getValue().toString())));
            sessionConfiguracionHorario.setEstadoDisableChoiceNive(false);
        }
        Utils.addTarget(choiceNivel);
    }

    public String guardarConfiguracionDeHorario() {
        if(this.validarCombos() == false){
            return null;
        }
        BeanDuracionHorario duracion = new BeanDuracionHorario();
        duracion.setNidSede(Integer.parseInt(sessionConfiguracionHorario.getNidSedeChoice()));
        duracion.setNidNivel(Integer.parseInt(sessionConfiguracionHorario.getNidNivelChoice()));
        duracion.setHora_inicio(Time.valueOf(sessionConfiguracionHorario.getInputHoraInicioClases() + ":00"));
        duracion.setMax_bloque(sessionConfiguracionHorario.getMaxBloquesPorCurso());
        duracion.setNro_bloque(sessionConfiguracionHorario.getNumeroDeBloques());
        duracion.setDuracion(Time.valueOf(sessionConfiguracionHorario.getDuracionPorBloque() + ":00"));

        List<BeanConfiguracionHorario> lstConfHorario = new ArrayList<BeanConfiguracionHorario>();
        for (int i = 0; i < sessionConfiguracionHorario.getListaEventosHorarioTabla().size(); i++) {
            BeanConfiguracionHorario bean = new BeanConfiguracionHorario();
            bean.setNidSede(Integer.parseInt(sessionConfiguracionHorario.getNidSedeChoice()));
            bean.setNidNivel(Integer.parseInt(sessionConfiguracionHorario.getNidNivelChoice()));
            bean.setHora_inicio(Time.valueOf(sessionConfiguracionHorario.getListaEventosHorarioTabla().get(i).getHoraInicio() +
                                             ":00"));
            bean.setHora_fin(Time.valueOf(sessionConfiguracionHorario.getListaEventosHorarioTabla().get(i).getHoraFin() +
                                          ":00"));
            lstConfHorario.add(bean);
        }

        if (validaConfiguracionHorario(duracion, lstConfHorario) == true) {

            if (sessionConfiguracionHorario.getAccionPersist() == 1) { ///ACCION 1 = NUEVO REGISTRO
                for (int i = 0; i < sessionConfiguracionHorario.getListaEventosHorarioTabla().size(); i++) {
                    ln_T_SFConfiguracionHorarioRemoto.registrarConfiguracionHorario_LN(Integer.parseInt(sessionConfiguracionHorario.getNidSedeChoice()),
                                                                                       Integer.parseInt(sessionConfiguracionHorario.getNidNivelChoice()),
                                                                                       Time.valueOf(sessionConfiguracionHorario.getListaEventosHorarioTabla().get(i).getHoraInicio() +
                                                                                                    ":00"),
                                                                                       Time.valueOf(sessionConfiguracionHorario.getListaEventosHorarioTabla().get(i).getHoraFin() +
                                                                                                    ":00"),
                                                                                       sessionConfiguracionHorario.getListaEventosHorarioTabla().get(i).getStmconfev().getNidConfev());
                }

                ln_T_SFDuracionHorarioRemoto.registrarDuracionHorario_LN(Integer.parseInt(sessionConfiguracionHorario.getNidSedeChoice()),
                                                                         Integer.parseInt(sessionConfiguracionHorario.getNidNivelChoice()),
                                                                         Time.valueOf(sessionConfiguracionHorario.getInputHoraInicioClases() +
                                                                                      ":00"),
                                                                         Time.valueOf(sessionConfiguracionHorario.getDuracionPorBloque() +
                                                                                      ":00"),
                                                                         sessionConfiguracionHorario.getMaxBloquesPorCurso(),
                                                                         sessionConfiguracionHorario.getNumeroDeBloques());
            }

            if (sessionConfiguracionHorario.getAccionPersist() == 2) { //ACCION 2 = EDICION DE REGISTRO EXISTENTE
                List<BeanConfiguracionHorario> listaBD =
                    ln_C_SFConfiguracionHorarioRemote.getConfiguracionBySedeNivel(Integer.parseInt(sessionConfiguracionHorario.getNidSedeChoice()),
                                                                                  Integer.parseInt(sessionConfiguracionHorario.getNidNivelChoice()));
                for (int i = 0; i < sessionConfiguracionHorario.getListaEventosHorarioTabla().size(); i++) {
                    for (int j = 0; j < listaBD.size(); j++) {
                        if (sessionConfiguracionHorario.getListaEventosHorarioTabla().get(i).getNidConfig() ==
                            listaBD.get(j).getNidConfig()) {
                            listaBD.remove(j); //Removemos los elementos que existan en la tabla para eliminar los que quedan es decir los que ya no estan.
                        }
                    }
                }

                if (listaBD.size() != 0) { //la lista a eliminar
                    for (int j = 0; j < listaBD.size(); j++) {
                        ln_T_SFConfiguracionHorarioRemoto.actualizarConfiguracionHorario_LN(listaBD.get(j), 2);
                    }
                }

                List<BeanConfiguracionHorario> listaBD2 =
                    ln_C_SFConfiguracionHorarioRemote.getConfiguracionBySedeNivel(Integer.parseInt(sessionConfiguracionHorario.getNidSedeChoice()),
                                                                                  /* Volvemos a llenar la lista ya que la anterior se modifico */Integer.parseInt(sessionConfiguracionHorario.getNidNivelChoice()));
                for (int i = 0; i < listaBD2.size(); i++) {
                    for (int j = 0; j < sessionConfiguracionHorario.getListaEventosHorarioTabla().size(); j++) {
                        if (sessionConfiguracionHorario.getListaEventosHorarioTabla().get(j).getNidConfig() ==
                            listaBD2.get(i).getNidConfig()) {
                            sessionConfiguracionHorario.getListaEventosHorarioTabla().remove(j); //removemos los elementos que que existen en la bd para insertar solo los que quedan es decir los nuevos
                        }
                    }
                }

                if (sessionConfiguracionHorario.getListaEventosHorarioTabla().size() != 0) { //lista con los items nuevos a insertar
                    for (int i = 0; i < sessionConfiguracionHorario.getListaEventosHorarioTabla().size(); i++) {
                        ln_T_SFConfiguracionHorarioRemoto.registrarConfiguracionHorario_LN(Integer.parseInt(sessionConfiguracionHorario.getNidSedeChoice()),
                                                                                           Integer.parseInt(sessionConfiguracionHorario.getNidNivelChoice()),
                                                                                           Time.valueOf(sessionConfiguracionHorario.getListaEventosHorarioTabla().get(i).getHoraInicio() +
                                                                                                        ":00"),
                                                                                           Time.valueOf(sessionConfiguracionHorario.getListaEventosHorarioTabla().get(i).getHoraFin() +
                                                                                                        ":00"),
                                                                                           sessionConfiguracionHorario.getListaEventosHorarioTabla().get(i).getStmconfev().getNidConfev()); //inserta
                    }
                }
            }

            /** Limpiar campos y reanudar todo****/
            sessionConfiguracionHorario.setNidNivelChoice(null);
            sessionConfiguracionHorario.setNidSedeChoice(null);
            Utils.addTargetMany(choiceNivel, choiceSede);
            reanudar();
            Utils.mostrarMensaje(ctx, "Operacion Realizada Con Exito", "Confirmacion", 0);
        } else {
            Utils.mostrarMensaje(ctx,
                                 "Los datos ingresados no son validos para la generacion de Horarios, porfavor ingrese datos que tengan relacion",
                                 "Error", 1);
        }

        return null;
    }

    public String agregarEventoHorario() throws ParseException {
        if (choiceEventoHorario.getValue() != null) {
            if (Time.valueOf(sessionConfiguracionHorario.getInputHoraFinEventoHorario() +":00").before(Time.valueOf(sessionConfiguracionHorario.getInputHoraInicioEventoHorario() +":00"))) {
                Utils.mostrarMensaje(ctx,"Hora Fin de evento no puedo ser antes de la Hora inicio, porfavor ingrese datos correctos","Error", 1);
            } else {
                BeanConfiguracionEventoHorario beanConfEv = ln_C_SFConfiguracionEventoHorarioRemoto.getEventoHorarioByID(Integer.parseInt(choiceEventoHorario.getValue().toString()));
                BeanConfiguracionHorario beanConHora = new BeanConfiguracionHorario();
                beanConHora.setStmconfev(beanConfEv);
                beanConHora.setHoraInicio(sessionConfiguracionHorario.getInputHoraInicioEventoHorario());
                beanConHora.setHoraFin(sessionConfiguracionHorario.getInputHoraFinEventoHorario());
                sessionConfiguracionHorario.getListaEventosHorarioTabla().add(beanConHora);
                sessionConfiguracionHorario.setNidEventoHorario(null);
                sessionConfiguracionHorario.setInputHoraInicioEventoHorario(null);
                sessionConfiguracionHorario.setInputHoraFinEventoHorario(null);
                sessionConfiguracionHorario.setEstadoinPutHoraInicioRestriccion(true);
                sessionConfiguracionHorario.setEstadoinPutHoraFinRestriccion(true);
                estadosPanel2(false);
            }
            Utils.addTargetMany(choiceEventoHorario, inputHoraInicioRestriccion, inputHoraFinRestriccion,
                                tbEventoHorario);
        }
        return null;
    }

    public String aceptaExisteHorarios() {
        popupExisteHorario.hide();
        return null;
    }

    public void seleccionaRestriccion(ValueChangeEvent valueChangeEvent) {
        if (valueChangeEvent.getNewValue() != null) {
            estadosPanel1(false);
            estadosPanel2(true);
        }
        if (valueChangeEvent.getNewValue() == null) {
            estadosPanel1(true);
            estadosPanel2(false);
        }
    }

    public void eventoChoiceNivel(ValueChangeEvent valueChangeEvent) {
        reanudar();
    }

    public String reanudar() {
        limpiarTodosLosCampos();
        estadosPanel2(true);
        estadosPanel1(true);
        sessionConfiguracionHorario.setEstadoDisableChoiceRestriccion(true);
        sessionConfiguracionHorario.setEstadoBtnEditarRestriccion(true);
        sessionConfiguracionHorario.setEstadobBtnRemoverRestriccion(true);
        sessionConfiguracionHorario.setSeleccionTable(0);
        Utils.addTargetMany(choiceEventoHorario, btnEditarRestriccion, btnRemoverEventoHorario);
        return null;
    }
    
    public boolean validarCombos(){
        if(sessionConfiguracionHorario.getNidSedeChoice() == null){
            this.setErrorDesc("Seleccione una Sede");
            return false;
        }
        if(sessionConfiguracionHorario.getNidNivelChoice() == null){
            this.setErrorDesc("Seleccione un Nivel");
            return false;
        }
        this.setErrorDesc(null);
        return true;
    }

    public String editarConfiguracion() {
        if(this.validarCombos() == false){
            return null;
        }
        if (ln_C_SFMainRemote.buscarHorariosBySedeYNivel(Integer.parseInt(sessionConfiguracionHorario.getNidSedeChoice()),
                                                         Integer.parseInt(sessionConfiguracionHorario.getNidNivelChoice())) == 0) { //Si no existen horarios permite editar
            sessionConfiguracionHorario.setEstadoDisableChoiceRestriccion(false);
            estadosPanel2(false);
            Utils.addTarget(choiceEventoHorario);
            sessionConfiguracionHorario.setSeleccionTable(1); //editable al seleccionar
        } else {
            Utils.showPopUpMIDDLE(popupExisteHorario);
        }
        return null;
    }
    
    public void llenarCombos(){
        sessionConfiguracionHorario.setListaSedesChoice(Utils.llenarCombo(ln_C_SFSedeRemote.getAllSedes()));        
        sessionConfiguracionHorario.setListaEventosHorariosChoice(Utils.llenarCombo(ln_C_SFConfiguracionEventoHorarioRemoto.getAllEventosDeHorario()));
        sessionConfiguracionHorario.setLstYears(Utils.llenarComboStringCalend(ln_C_SFCalendarioLocal.getYearsCalendario()));
    }

    public String realizarNuevaRestriccion() {
        sessionConfiguracionHorario.setEstadoDisableChoiceRestriccion(false);     
        Utils.addTargetMany(choiceEventoHorario);
        sessionConfiguracionHorario.setAccionPersist(1);//persist
        sessionConfiguracionHorario.setSeleccionTable(1);//table editable
        return null;
    }
    
    public String estadosPanel1(boolean valor){
        sessionConfiguracionHorario.setEstadoinPutHoraInicioRestriccion(valor);
        sessionConfiguracionHorario.setEstadoinPutHoraFinRestriccion(valor);  
        sessionConfiguracionHorario.setEstadoBtnAgregarRestriccion(valor);
        Utils.addTargetMany(inputHoraInicioRestriccion,inputHoraFinRestriccion,btnAgregarEventoHorario);
        return null;
    }
    
    public String estadosPanel2(boolean valor){
        sessionConfiguracionHorario.setEstadoHoraInicioClases(valor);
        sessionConfiguracionHorario.setEstadoNumBloques(valor);
        sessionConfiguracionHorario.setEstadoDuracionPorBloque(valor);
        sessionConfiguracionHorario.setEstadoMaxBloquesXCurso(valor);
        sessionConfiguracionHorario.setEstadoDisableBtnGuardar(valor);
        Utils.addTargetMany(inputHoraInicioClases,inputNumeroBloques,inputDuracionXBloque,inputMaxBloquesXCurs,btnGuardar);
        return null;
    }
    
    public String limpiarTodosLosCampos(){
        sessionConfiguracionHorario.setNidEventoHorario(null);
        sessionConfiguracionHorario.setInputHoraInicioEventoHorario(null);
        sessionConfiguracionHorario.setInputHoraFinEventoHorario(null);
        sessionConfiguracionHorario.getListaEventosHorarioTabla().clear();
        sessionConfiguracionHorario.setInputHoraInicioClases(null);
        sessionConfiguracionHorario.setNumeroDeBloques(0);
        sessionConfiguracionHorario.setDuracionPorBloque(null);
        sessionConfiguracionHorario.setHoraFinClases(null);
        sessionConfiguracionHorario.setMaxBloquesPorCurso(0);
        Utils.addTargetMany(inputHoraFinClases,choiceEventoHorario,inputDuracionXBloque,inputHoraFinRestriccion,inputHoraInicioClases,inputHoraInicioRestriccion,inputNumeroBloques,tbEventoHorario,inputMaxBloquesXCurs);
        return null;
    }
    public String cancelarNuevaRestriccion() {
        popupConfigurarNuevaRestriccion.hide();
        return null;
    }

    public String realizarBusquedaDeRestricciones() {
        if(this.validarCombos() == false){
            return null;
        }
        BeanDuracionHorario beanDura =
            ln_C_SFDuracionHorarioRemote.getDuracionHorarioBySedeNivel(Integer.parseInt(sessionConfiguracionHorario.getNidSedeChoice()),
                                                                       Integer.parseInt(sessionConfiguracionHorario.getNidNivelChoice()));
        sessionConfiguracionHorario.setBeanDuracionHorario(beanDura);
        if (beanDura != null) {
            List<BeanConfiguracionHorario> listBeanConf =
                ln_C_SFConfiguracionHorarioRemote.getConfiguracionBySedeNivel(Integer.parseInt(sessionConfiguracionHorario.getNidSedeChoice()),
                                                                              Integer.parseInt(sessionConfiguracionHorario.getNidNivelChoice()));
            for (int i = 0; i < listBeanConf.size(); i++) {
                listBeanConf.get(i).setHoraInicio(formatearTime(listBeanConf.get(i).getHora_inicio()));
                listBeanConf.get(i).setHoraFin(formatearTime(listBeanConf.get(i).getHora_fin()));
            }
            sessionConfiguracionHorario.setListaEventosHorarioTabla(listBeanConf);
            sessionConfiguracionHorario.setInputHoraInicioClases(formatearTime(beanDura.getHora_inicio()));
            sessionConfiguracionHorario.setMaxBloquesPorCurso(beanDura.getMax_bloque());
            sessionConfiguracionHorario.setNumeroDeBloques(beanDura.getNro_bloque());
            sessionConfiguracionHorario.setDuracionPorBloque(formatearTime(beanDura.getDuracion()));
            sessionConfiguracionHorario.setEstadoBtnEditarRestriccion(false);
            sessionConfiguracionHorario.setAccionPersist(2); //merge
            calculaHoraFinDeClases();
            Utils.addTargetMany(tbEventoHorario, inputDuracionXBloque, inputHoraInicioClases, inputMaxBloquesXCurs,
                                inputNumeroBloques, btnEditarRestriccion);
        } else {
            Utils.showPopUpMIDDLE(popupConfigurarNuevaRestriccion);
        }
        return null;
    }
    
    public void seleccionarRestriccionTAbla(SelectionEvent selectionEvent) {
        RichTable t = (RichTable) selectionEvent.getSource();
        Object _selectedRowData = t.getSelectedRowData();
        BeanConfiguracionHorario config = (BeanConfiguracionHorario) _selectedRowData;
        sessionConfiguracionHorario.setBeanconfiguracionHorario(config); 
        if(sessionConfiguracionHorario.getSeleccionTable()==1){
            sessionConfiguracionHorario.setEstadobBtnRemoverRestriccion(false);
            sessionConfiguracionHorario.setEstadoinPutHoraInicioRestriccion(true);
            sessionConfiguracionHorario.setEstadoinPutHoraFinRestriccion(true);
            sessionConfiguracionHorario.setEstadoBtnAgregarRestriccion(true);
            sessionConfiguracionHorario.setNidEventoHorario(null);
            Utils.addTargetMany(btnRemoverEventoHorario,btnAgregarEventoHorario,inputHoraInicioRestriccion,inputHoraFinRestriccion,choiceEventoHorario);   
            estadosPanel2(true);
        }      
    }
    
    public String formatearTime(Time hora){
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        Date fechaActual = hora;
        String fechaConFormato = sdf.format(fechaActual);     
        return fechaConFormato;
    }

    public String removerEventoHorarioTabla() {
        for (int i = 0; i < sessionConfiguracionHorario.getListaEventosHorarioTabla().size(); i++) {
            if (sessionConfiguracionHorario.getListaEventosHorarioTabla().get(i).getHoraFin().equals(sessionConfiguracionHorario.getBeanconfiguracionHorario().getHoraFin()) &&
                sessionConfiguracionHorario.getListaEventosHorarioTabla().get(i).getHoraInicio().equals(sessionConfiguracionHorario.getBeanconfiguracionHorario().getHoraInicio()) &&
                sessionConfiguracionHorario.getListaEventosHorarioTabla().get(i).getStmconfev().equals(sessionConfiguracionHorario.getBeanconfiguracionHorario().getStmconfev())) {
                sessionConfiguracionHorario.getListaEventosHorarioTabla().remove(i);
            }
        }
        sessionConfiguracionHorario.setEstadobBtnRemoverRestriccion(true);
        estadosPanel2(false);
        Utils.addTargetMany(tbEventoHorario, btnRemoverEventoHorario);
        return null;
    }
    
    public boolean validaConfiguracionHorario(BeanDuracionHorario duracion, List<BeanConfiguracionHorario> lstConfHorario){
        try{
            Utils.putSession("maxHoras", (duracion.getMax_bloque()*5));//grabamos el maximo permitido por curso
            sessionConfiguracionHorario.setNroBloque(duracion.getNro_bloque());//guardo el numero de bloques al dia permitido
            sessionConfiguracionHorario.setMaxBloque(duracion.getMax_bloque());
            Time horas[] = new Time[duracion.getNro_bloque()];
            Calendar inicio = new GregorianCalendar();
            inicio.setTime(duracion.getHora_inicio());
            Time time_aux = new Time(inicio.getTimeInMillis());
            int cont = 1;
            boolean restr = false;
            horas[0] = new Time(time_aux.getTime());
            while(cont < duracion.getNro_bloque()){
                if(lstConfHorario.size() > 0){
                    for(BeanConfiguracionHorario configuracionH : lstConfHorario){                       
                        if(configuracionH.getHora_inicio().equals(time_aux)){
                            cont--;     
                            inicio.setTime(configuracionH.getHora_fin());
                            horas[cont] = new Time(time_aux.getTime());      
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
                    horas[cont] = new Time(time_aux.getTime());               
                    cont++;
                }                     
            }    // fin de la validacion
            for(int i = 0 ; i < horas.length; i++){
                System.out.println(horas[i]);
            }
            if(lstConfHorario.size() != 0){//valida si se validaron todas las restriciones
                return false;
            }
            sessionConfiguracionHorario.setHoras(horas);
            return true;  
        }catch(Exception e){            
            e.printStackTrace();
            return false;
        }
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
    
    public String calculaHoraFinDeClases(){      
        Time horaInicio=Time.valueOf(sessionConfiguracionHorario.getInputHoraInicioClases()+":00");    
        Time duracionXBloque=Time.valueOf(sessionConfiguracionHorario.getDuracionPorBloque()+":00");        
        int numBloque=sessionConfiguracionHorario.getNumeroDeBloques();        
        /* Pasamos a segundos la hora inicio*/
          int numHora = horaInicio.getHours();
          int numMinutos = horaInicio.getMinutes();
          int segundosEnMinutos = numMinutos * 60;
          int toalSegundos = numHora * 3600 + segundosEnMinutos;      
          /* Pasamos a segundos la hora DuracionXBloque*/
          int numHora2 = duracionXBloque.getHours();
          int numMinutos2 = duracionXBloque.getMinutes();
          int segundosEnMinutos2 = numMinutos2 * 60;
          int toalSegundos2 = numHora2 * 3600 + segundosEnMinutos2;     
          /* Multiplicamos la Duracion por Bloque en segundos por el numero de bloques*/
          int totalSegundos3 =toalSegundos2*numBloque;          
          /* Sumamos la hora multiplicada mas la hora inicio para obtener la hora final en segundos*/
          int segundosXBloqueYHoraInicio=totalSegundos3+toalSegundos;/** segundo final 1*/
        
          int segundosDeTabla=0;
          for(int i=0; i<sessionConfiguracionHorario.getListaEventosHorarioTabla().size(); i++){
             int hora= Time.valueOf(sessionConfiguracionHorario.getListaEventosHorarioTabla().get(i).getHoraFin()+":00").getHours() - 
                    Time.valueOf(sessionConfiguracionHorario.getListaEventosHorarioTabla().get(i).getHoraInicio()+":00").getHours() ;
             int minutos=  Time.valueOf(sessionConfiguracionHorario.getListaEventosHorarioTabla().get(i).getHoraFin()+":00").getMinutes() - 
                        Time.valueOf(sessionConfiguracionHorario.getListaEventosHorarioTabla().get(i).getHoraInicio()+":00").getMinutes() ;
           
            int segunds = minutos * 60;
            int totalseg = hora * 3600 + segunds;            
            segundosDeTabla=segundosDeTabla+totalseg;              
        }        
          int finalSegundos=segundosXBloqueYHoraInicio+segundosDeTabla;       
          int hor = finalSegundos / 3600;       
          int min = (finalSegundos - (3600 * hor)) / 60;                 
          Time hora=new Time(00,00,00);
          hora.setHours(hor);
          hora.setMinutes(min);        
          sessionConfiguracionHorario.setHoraFinClases(formatearTime(hora));
          Utils.addTarget(inputHoraFinClases);
          
        return null;
    }
    
    public void calcularHoraFinClases(ValueChangeEvent vce) {
        calculaHoraFinDeClases();
    }
    
    /***************************** CALENDARIO *******************************************************/
    
    public void refrescarTabla(ActionEvent ae) {
        if(sessionConfiguracionHorario.getCidMes() == null){
            sessionConfiguracionHorario.setCidMes("1");
        }
        sessionConfiguracionHorario.setLstCalendario(ln_C_SFCalendarioLocal.getCalendarioActivo_LN(Integer.parseInt(sessionConfiguracionHorario.getCidMes()),
                                                                                                   Integer.parseInt(sessionConfiguracionHorario.getYear()) ));
    }
    
    public void selectTablaCalendario(SelectionEvent se) {
        BeanCalendario calen = (BeanCalendario) Utils.getRowTable(se);
        if(calen.getEsLaborable() == 1 || (calen.getEsLaborable() == 0 && calen.getEsFeriado() == 0 && calen.getEsDiaSemana() == 1) ){
            sessionConfiguracionHorario.setCalenSelected(calen);
            sessionConfiguracionHorario.setDescDia(calen.getDescripcionDia());
            Utils.showPopUpMIDDLE(popCalen);
        }
    }
    
    public void actualizarDiaCalendario(ActionEvent ae) {
        ln_T_SFCalendarioLocal.registrarDiaNoLaborable(sessionConfiguracionHorario.getCalenSelected().getNidFecha(),
                                                       sessionConfiguracionHorario.getDescDia());
        sessionConfiguracionHorario.setLstCalendario(ln_C_SFCalendarioLocal.getCalendarioActivo_LN(sessionConfiguracionHorario.getCalenSelected().getMesNumero(),
                                                                                                   sessionConfiguracionHorario.getCalenSelected().getYear() ));
        sessionConfiguracionHorario.setCalenSelected(null);
        Utils.unselectFilas(tbCalen);
        popCalen.hide();
    }
    
    public void anularDiaCalendario(ActionEvent ae) {
        ln_T_SFCalendarioLocal.registrarDiaLaborable(sessionConfiguracionHorario.getCalenSelected().getNidFecha());
        sessionConfiguracionHorario.setLstCalendario(ln_C_SFCalendarioLocal.getCalendarioActivo_LN(sessionConfiguracionHorario.getCalenSelected().getMesNumero(),
                                                                                                   sessionConfiguracionHorario.getCalenSelected().getYear() ));
        sessionConfiguracionHorario.setCalenSelected(null);
        Utils.unselectFilas(tbCalen);
        popCalen.hide();
    }
    
    public void selectConfigDias(SelectionEvent se) {
        BeanConstraint bc = (BeanConstraint) Utils.getRowTable(se);
        sessionConfiguracionHorario.setEditCons(bc);
    }
    
    public void actualizarConfig_Action(ActionEvent ae) {
        String err = ln_T_SFUtilsLocal.actualizarConstraintConfigEvasXDia(sessionConfiguracionHorario.getLstBeanCons());
        if(err == null){
            this.setErrorConfig("Hubo un error inesperado.");
            sessionConfiguracionHorario.setLstBeanCons(ln_C_SFUtilsLocal.getMinMaxEvasPorDiaConfigConstraint_LN());
            return;
        }
        if("111".equalsIgnoreCase(err)){
            this.setErrorConfig("Hay un error en los valores, verifique que el minimo no sea menor o igual a cero o mayor que el valor Maximo. Usar valores enteros positivos");
        }else if("000".equalsIgnoreCase(err)){
            this.setErrorConfig("Valores actualizados.");
        }else if("222".equalsIgnoreCase(err)){
            this.setErrorConfig("No hay cambios.");
        }
        sessionConfiguracionHorario.setLstBeanCons(ln_C_SFUtilsLocal.getMinMaxEvasPorDiaConfigConstraint_LN());
    }
    
    public void cambiarValor(ValueChangeEvent vce) {
        sessionConfiguracionHorario.getEditCons().setValorCampo(vce.getNewValue()+"");
    }
    
    public void setPopupConfigurarNuevaRestriccion(RichPopup popupConfigurarNuevaRestriccion) {
        this.popupConfigurarNuevaRestriccion = popupConfigurarNuevaRestriccion;
    }

    public RichPopup getPopupConfigurarNuevaRestriccion() {
        return popupConfigurarNuevaRestriccion;
    }

    public void setBtnEditarRestriccion(RichButton btnEditarRestriccion) {
        this.btnEditarRestriccion = btnEditarRestriccion;
    }

    public RichButton getBtnEditarRestriccion() {
        return btnEditarRestriccion;
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

    public void setInputHoraInicioRestriccion(RichInputText inputHoraInicioRestriccion) {
        this.inputHoraInicioRestriccion = inputHoraInicioRestriccion;
    }

    public RichInputText getInputHoraInicioRestriccion() {
        return inputHoraInicioRestriccion;
    }

    public void setInputHoraFinRestriccion(RichInputText inputHoraFinRestriccion) {
        this.inputHoraFinRestriccion = inputHoraFinRestriccion;
    }

    public RichInputText getInputHoraFinRestriccion() {
        return inputHoraFinRestriccion;
    }

    public void setBtnRemoverEventoHorario(RichButton btnRemoverEventoHorario) {
        this.btnRemoverEventoHorario = btnRemoverEventoHorario;
    }

    public RichButton getBtnRemoverEventoHorario() {
        return btnRemoverEventoHorario;
    }

    public void setInputHoraInicioClases(RichInputText inputHoraInicioClases) {
        this.inputHoraInicioClases = inputHoraInicioClases;
    }

    public RichInputText getInputHoraInicioClases() {
        return inputHoraInicioClases;
    }

    public void setInputNumeroBloques(RichInputNumberSpinbox inputNumeroBloques) {
        this.inputNumeroBloques = inputNumeroBloques;
    }

    public RichInputNumberSpinbox getInputNumeroBloques() {
        return inputNumeroBloques;
    }

    public void setInputDuracionXBloque(RichInputText inputDuracionXBloque) {
        this.inputDuracionXBloque = inputDuracionXBloque;
    }

    public RichInputText getInputDuracionXBloque() {
        return inputDuracionXBloque;
    }

    public void setInputMaxBloquesXCurs(RichInputNumberSpinbox inputMaxBloquesXCurs) {
        this.inputMaxBloquesXCurs = inputMaxBloquesXCurs;
    }

    public RichInputNumberSpinbox getInputMaxBloquesXCurs() {
        return inputMaxBloquesXCurs;
    }


    public void setInputHoraFinClases(RichInputText inputHoraFinClases) {
        this.inputHoraFinClases = inputHoraFinClases;
}

    public RichInputText getInputHoraFinClases() {
        return inputHoraFinClases;
    }

    public void setPopupExisteHorario(RichPopup popupExisteHorario) {
        this.popupExisteHorario = popupExisteHorario;
    }

    public RichPopup getPopupExisteHorario() {
        return popupExisteHorario;
    }


    public void setOtError(RichOutputText otError) {
        this.otError = otError;
    }

    public RichOutputText getOtError() {
        return otError;
    }

    public void setErrorDesc(String errorDesc) {
        this.errorDesc = errorDesc;
    }

    public String getErrorDesc() {
        return errorDesc;
    }

    public void setPopCalen(RichPopup popCalen) {
        this.popCalen = popCalen;
    }

    public RichPopup getPopCalen() {
        return popCalen;
    }

    public void setTbCalen(RichTable tbCalen) {
        this.tbCalen = tbCalen;
    }

    public RichTable getTbCalen() {
        return tbCalen;
    }

    public void setErrorConfig(String errorConfig) {
        this.errorConfig = errorConfig;
    }

    public String getErrorConfig() {
        return errorConfig;
    }
}
