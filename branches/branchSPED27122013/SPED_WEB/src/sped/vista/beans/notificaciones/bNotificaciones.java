package sped.vista.beans.notificaciones;

import java.util.List;

import javax.annotation.PostConstruct;

import javax.ejb.EJB;

import javax.faces.context.FacesContext;

import javax.faces.event.ActionEvent;

import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.data.RichTable;

import oracle.adf.view.rich.component.rich.input.RichInputDate;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.input.RichSelectOneChoice;

import oracle.adf.view.rich.component.rich.nav.RichButton;

import oracle.adf.view.rich.event.DialogEvent;

import org.apache.myfaces.trinidad.event.SelectionEvent;

import sped.negocio.LNSF.IL.LN_C_SFNotificacionLocal;

import sped.negocio.LNSF.IL.LN_C_SFUtilsLocal;
import sped.negocio.LNSF.IL.LN_T_SFUtilsLocal;
import sped.negocio.entidades.beans.BeanNotificacionEvaluacion;

import sped.negocio.entidades.beans.BeanParteOcurrencia;
import sped.negocio.entidades.beans.BeanUsuario;

import sped.vista.Utils.ADFUtil;
import sped.vista.Utils.Utils;

/** Clase de Respaldo de Frm_notificaciones.jsff
 * @author dfloresgonz
 * @since 15.03.2014
 */
public class bNotificaciones {
    private RichTable tbEvas;
    private RichSelectOneChoice socSedes;
    private RichInputText itDoc;
    private RichInputText itIndi;
    private RichSelectOneChoice socEst;
    private RichInputDate idMin;
    private RichInputDate idMax;
    private RichSelectOneChoice socSedesPO;
    private RichSelectOneChoice socEstPO;
    private RichInputDate idMinPO;
    private RichInputDate idMaxPO;
    private RichInputText itDocPO;
    private RichSelectOneChoice socProbPO;
    private RichTable tbPOs;
    private sessionNotificaciones sessionNoti;
    FacesContext ctx = FacesContext.getCurrentInstance();
    @EJB
    private LN_C_SFNotificacionLocal ln_C_SFNotificacionLocal;
    @EJB
    private LN_C_SFUtilsLocal ln_C_SFUtilsLocal;
    @EJB
    private LN_T_SFUtilsLocal ln_T_SFUtilsLocal;
    private BeanUsuario usuario = (BeanUsuario) Utils.getSession("USER");
    private RichPopup popDet;
    private RichTable tbEvDet;
    private RichButton btnDet;

    public bNotificaciones() {
    }

    @PostConstruct
    public void methodInvokeOncedOnPageLoad(){
        try {
            String evas = ADFUtil.getParameter("evas");
            String pos = ADFUtil.getParameter("pos");
            if(evas == null){
                evas = "S";
            }
            if(pos == null){
                pos = "S";
            }
            if (sessionNoti.getExec() == 0) {
                sessionNoti.setExec(1);
                sessionNoti.setEvas(evas);
                sessionNoti.setPos(pos);
                sessionNoti.setLstSedes(Utils.llenarCombo(ln_C_SFUtilsLocal.getSedes_LN()));
                if(usuario.getRol().getNidRol() == 4){
                    sessionNoti.setEnableSedes(true);
                    sessionNoti.setCidSede(String.valueOf(usuario.getSede().getNidSede()));
                    sessionNoti.setCidSedePO(String.valueOf(usuario.getSede().getNidSede()));
                }else{
                    sessionNoti.setEnableSedes(false);
                }
                if(evas != null){
                    sessionNoti.setMostrarNotifEvas(evas.equals("S") ? true : false);
                    if(evas.equals("S")){
                        buscarNotificacionesEvaluaciones();
                    }
                }
                if(pos != null){
                    sessionNoti.setLstProblemas(Utils.llenarCombo(ln_C_SFUtilsLocal.getProblemas_LN_WS()));
                    sessionNoti.setMostrarNotifPOs(pos.equals("S") ? true : false);
                    if(pos.equals("S")){
                        buscarNotificacionesPartesOcurrencia();
                    }
                }
            } else {
                if(evas != null){
                    sessionNoti.setMostrarNotifEvas(evas.equals("S") ? true : false);
                }else{
                    sessionNoti.setMostrarNotifEvas(sessionNoti.getEvas().equals("S") ? true : false);
                }
                if(pos != null){
                    sessionNoti.setMostrarNotifPOs(pos.equals("S") ? true : false);
                }else{
                    sessionNoti.setMostrarNotifPOs(sessionNoti.getPos().equals("S") ? true : false);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public String buscarNotificacionesEvaluaciones(){
        try {
           if(usuario.getRol().getNidRol() == 4){
               sessionNoti.setCidSede(String.valueOf(usuario.getSede().getNidSede()));
           }else{
               if(sessionNoti.getCidSede() == null){
                   sessionNoti.setCidSede("0");
               }
           }
           sessionNoti.setLstNotifEvaluaciones(ln_C_SFNotificacionLocal.getListaNotificacionesByAttr_BDL(sessionNoti.getDocente(), 
                                                                                                         sessionNoti.getIndicador(), 
                                                                                                         new Integer(sessionNoti.getCidSede()),
                                                                                                         sessionNoti.getEstadoLeido(),
                                                                                                         sessionNoti.getFecMin(),
                                                                                                         sessionNoti.getFecMax(),
                                                                                                         usuario.getNidUsuario()));
            //actualizar a LEIDOS
           ln_T_SFUtilsLocal.cambiarALeidoNotificacion_LN("stmnoti",usuario.getNidUsuario());
           if(tbEvas != null){
               tbEvas.setValue(sessionNoti.getLstNotifEvaluaciones());
               Utils.addTarget(tbEvas);
           }
       } catch (Exception e) {
            e.printStackTrace();
       }
        return null;
    }
    
    public String buscarNotificacionesPartesOcurrencia(){
        try {
           if(usuario.getRol().getNidRol() == 4){
               sessionNoti.setCidSedePO(String.valueOf(usuario.getSede().getNidSede()));
           }else{
               if(sessionNoti.getCidSedePO() == null){
                   sessionNoti.setCidSedePO("0");
               }
           }
           if(sessionNoti.getCidProblema() == null){
               sessionNoti.setCidProblema("0");
           }
           sessionNoti.setLstNotifPOs(ln_C_SFNotificacionLocal.getListaNotificacionesPartesOcurrenciaByAttr_BDL(sessionNoti.getDocentePO(), 
                                                                                                                     new Integer(sessionNoti.getCidProblema()),
                                                                                                                     new Integer(sessionNoti.getCidSedePO()),
                                                                                                                     sessionNoti.getEstadoLeidoPO(),
                                                                                                                     sessionNoti.getFecMinPO(),
                                                                                                                     sessionNoti.getFecMaxPO(),
                                                                                                                     usuario.getNidUsuario()));
            //actualizar a LEIDOS
           ln_T_SFUtilsLocal.cambiarALeidoNotificacion_LN("stmnopo",usuario.getNidUsuario());
           if(tbPOs != null){
               tbPOs.setValue(sessionNoti.getLstNotifPOs());
               Utils.addTarget(tbPOs);
           }
       } catch (Exception e) {
            e.printStackTrace();
       }
        return null;
    }
    
    public void selectNotif_Evaluacion(SelectionEvent se) {
        try {
            BeanNotificacionEvaluacion beanEvaNoti = (BeanNotificacionEvaluacion) Utils.getRowTable(se);
            sessionNoti.setNidEvaluacionSelected(beanEvaNoti.getNidEvaluacion());
            btnDet.setDisabled(false);
            Utils.unselectFilas(tbEvDet);
            Utils.addTarget(btnDet);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void btnVerDetalle(ActionEvent actionEvent) {
        try {
            if (sessionNoti.getNidEvaluacionSelected() != 0) {
                sessionNoti.setLstNotifEvaluacionesDetalle(ln_C_SFNotificacionLocal.getListaNotificaciones_Detalle_ByEval_ByAttr_LN(sessionNoti.getNidEvaluacionSelected()));
                if (tbEvDet != null) {
                    tbEvDet.setValue(sessionNoti.getLstNotifEvaluacionesDetalle());
                    Utils.addTarget(tbEvDet);
                }
                Utils.showPopUpMIDDLE(popDet);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    
    public void diagOK_VerDetalle(DialogEvent dialogEvent) {
        if (dialogEvent.getOutcome() == DialogEvent.Outcome.ok){
            btnDet.setDisabled(true);
            Utils.addTarget(btnDet);
            sessionNoti.setNidEvaluacionSelected(0);
        }
    }
    
    public void limpiar(ActionEvent actionEvent) {
        _limpiar();
    }
    
    public void limpiarPO(ActionEvent actionEvent) {
        _limpiarPO();
    }
    
    public List<BeanNotificacionEvaluacion> _limpiar(){
        sessionNoti.getLstNotifEvaluaciones().clear();
        sessionNoti.setDocente(null);
        sessionNoti.setIndicador(null);
        sessionNoti.setCidSede(null);
        sessionNoti.setIndicador(null);
        sessionNoti.setFecMax(null);
        sessionNoti.setFecMin(null);
        buscarNotificacionesEvaluaciones();
        socSedes.resetValue();
        socEst.resetValue();
        itDoc.resetValue();
        itIndi.resetValue();
        idMax.resetValue();
        idMin.resetValue();
        Utils.addTargetMany(socSedes,socEst,itDoc,itIndi,idMax,idMin);
        return sessionNoti.getLstNotifEvaluaciones();
    }
    
    public List<BeanParteOcurrencia> _limpiarPO(){
        sessionNoti.getLstNotifPOs().clear();
        sessionNoti.setDocentePO(null);
        sessionNoti.setCidProblema(null);
        sessionNoti.setCidSedePO(null);
        sessionNoti.setFecMaxPO(null);
        sessionNoti.setFecMinPO(null);
        buscarNotificacionesPartesOcurrencia();
        socSedesPO.resetValue();
        socEstPO.resetValue();
        itDocPO.resetValue();
        socProbPO.resetValue();
        idMaxPO.resetValue();
        idMinPO.resetValue();
        Utils.addTargetMany(socSedesPO,socEstPO,itDocPO,socProbPO,idMaxPO,idMinPO);
        return sessionNoti.getLstNotifPOs();
    }

    public void setSessionNoti(sessionNotificaciones sessionNoti) {
        this.sessionNoti = sessionNoti;
    }

    public sessionNotificaciones getSessionNoti() {
        return sessionNoti;
    }
    
    public void setUsuario(BeanUsuario usuario) {
        this.usuario = usuario;
    }

    public BeanUsuario getUsuario() {
        return usuario;
    }

    public void setTbEvas(RichTable tbEvas) {
        this.tbEvas = tbEvas;
    }

    public RichTable getTbEvas() {
        return tbEvas;
    }

    public void setSocSedes(RichSelectOneChoice socSedes) {
        this.socSedes = socSedes;
    }

    public RichSelectOneChoice getSocSedes() {
        return socSedes;
    }

    public void setItDoc(RichInputText itDoc) {
        this.itDoc = itDoc;
    }

    public RichInputText getItDoc() {
        return itDoc;
    }

    public void setItIndi(RichInputText itIndi) {
        this.itIndi = itIndi;
    }

    public RichInputText getItIndi() {
        return itIndi;
    }

    public void setSocEst(RichSelectOneChoice socEst) {
        this.socEst = socEst;
    }

    public RichSelectOneChoice getSocEst() {
        return socEst;
    }

    public void setIdMin(RichInputDate idMin) {
        this.idMin = idMin;
    }

    public RichInputDate getIdMin() {
        return idMin;
    }

    public void setIdMax(RichInputDate idMax) {
        this.idMax = idMax;
    }

    public RichInputDate getIdMax() {
        return idMax;
    }

    public void setSocSedesPO(RichSelectOneChoice socSedesPO) {
        this.socSedesPO = socSedesPO;
    }

    public RichSelectOneChoice getSocSedesPO() {
        return socSedesPO;
    }

    public void setSocEstPO(RichSelectOneChoice socEstPO) {
        this.socEstPO = socEstPO;
    }

    public RichSelectOneChoice getSocEstPO() {
        return socEstPO;
    }

    public void setIdMinPO(RichInputDate idMinPO) {
        this.idMinPO = idMinPO;
    }

    public RichInputDate getIdMinPO() {
        return idMinPO;
    }

    public void setIdMaxPO(RichInputDate idMaxPO) {
        this.idMaxPO = idMaxPO;
    }

    public RichInputDate getIdMaxPO() {
        return idMaxPO;
    }

    public void setItDocPO(RichInputText itDocPO) {
        this.itDocPO = itDocPO;
    }

    public RichInputText getItDocPO() {
        return itDocPO;
    }

    public void setSocProbPO(RichSelectOneChoice socProbPO) {
        this.socProbPO = socProbPO;
    }

    public RichSelectOneChoice getSocProbPO() {
        return socProbPO;
    }

    public void setTbPOs(RichTable tbPOs) {
        this.tbPOs = tbPOs;
    }

    public RichTable getTbPOs() {
        return tbPOs;
    }

    public void setPopDet(RichPopup popDet) {
        this.popDet = popDet;
    }

    public RichPopup getPopDet() {
        return popDet;
    }

    public void setTbEvDet(RichTable tbEvDet) {
        this.tbEvDet = tbEvDet;
    }

    public RichTable getTbEvDet() {
        return tbEvDet;
    }

    public void setBtnDet(RichButton btnDet) {
        this.btnDet = btnDet;
    }

    public RichButton getBtnDet() {
        return btnDet;
    }
}