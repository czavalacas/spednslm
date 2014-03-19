package sped.vista.beans.administrativo.parte;

import java.util.ArrayList;
import java.util.Date;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;

import javax.faces.event.ActionEvent;

import javax.faces.event.ValueChangeEvent;

import oracle.adf.view.faces.bi.component.graph.UIGraph;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.input.RichInputDate;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.input.RichSelectOneChoice;

import sped.negocio.LNSF.IL.LN_C_SFParteOcurrenciaLocal;
import sped.negocio.LNSF.IL.LN_C_SFUtilsLocal;

import sped.negocio.entidades.beans.BeanEvaluacion;
import sped.negocio.entidades.beans.BeanParteOcurrencia;
import sped.negocio.entidades.beans.BeanPie;
import sped.negocio.entidades.beans.BeanUsuario;

import sped.vista.Utils.Utils;

/** Clase de Frm_ParteOcurrencia.jsff
 * @author dfloresgonz
 * @since 18.03.2013
 */
public class bParteOcurrencia {
    
    private RichSelectOneChoice socSedesPO;
    private RichInputText itDocPO;
    private RichSelectOneChoice socProbPO;
    private RichInputDate idMinPO;
    private RichInputDate idMaxPO;
    private RichTable tbPOs;
    private bSessionParteOcurrencia sessionParteOcurrencia;
    FacesContext ctx = FacesContext.getCurrentInstance();
    private BeanUsuario usuario = (BeanUsuario) Utils.getSession("USER");
    @EJB
    private LN_C_SFUtilsLocal ln_C_SFUtilsLocal;
    @EJB
    private LN_C_SFParteOcurrenciaLocal ln_C_SFParteOcurrenciaLocal;
    private UIGraph piePO;

    public bParteOcurrencia(){
        
    }
    
    @PostConstruct
    public void methodInvokeOncedOnPageLoad(){
        if (sessionParteOcurrencia.getExec() == 0){
            sessionParteOcurrencia.setExec(1);
            sessionParteOcurrencia.setLstSedes(Utils.llenarCombo(ln_C_SFUtilsLocal.getSedes_LN()));
            sessionParteOcurrencia.setLstProblemas(Utils.llenarCombo(ln_C_SFUtilsLocal.getProblemas_LN_WS()));
            if(usuario.getRol().getNidRol() == 4){
                sessionParteOcurrencia.setEnableSedes(true);
                sessionParteOcurrencia.setCidSedePO(String.valueOf(usuario.getSedeNivel().getSede().getNidSede()));
            }else{
                sessionParteOcurrencia.setEnableSedes(false);
            }
            buscarPartesOcurrencia();
            setListPieGraph(sessionParteOcurrencia.getLstNotifPOs().get(sessionParteOcurrencia.getLstNotifPOs().size()-1).getLstPies());
        }else{
            
        }
    }

    public String buscarPartesOcurrencia(){
        try {
           int idUsuario = 0; 
           if(usuario.getRol().getNidRol() == 4){
               sessionParteOcurrencia.setCidSedePO(String.valueOf(usuario.getSedeNivel().getSede().getNidSede()));
               idUsuario = usuario.getNidUsuario();
           }else{
               if(sessionParteOcurrencia.getCidSedePO() == null){
                   sessionParteOcurrencia.setCidSedePO("0");
               }
           }
           if(sessionParteOcurrencia.getCidProblema() == null){
               sessionParteOcurrencia.setCidProblema("0");
           }
           sessionParteOcurrencia.setLstNotifPOs(ln_C_SFParteOcurrenciaLocal.getListaPartesOcurrencia_LN(sessionParteOcurrencia.getFecMinPO(),
                                                                                                                 sessionParteOcurrencia.getFecMaxPO(),
                                                                                                                 new Integer(sessionParteOcurrencia.getCidProblema()),
                                                                                                                 sessionParteOcurrencia.getDocentePO(),
                                                                                                                 new Integer(sessionParteOcurrencia.getCidSedePO()),
                                                                                                                 idUsuario));
           if(tbPOs != null){
               tbPOs.setValue(sessionParteOcurrencia.getLstNotifPOs());
               Utils.addTarget(tbPOs);
           }
       } catch (Exception e) {
            e.printStackTrace();
       }
        return null;
    }
    
    public void setListPieGraph(BeanPie[] pieArray){
        List<Object[]> lstPOs = new ArrayList();
        for(int i = 0; i < pieArray.length; i++){
            BeanPie eva = pieArray[i];
            Object[] obj1 = { "Partes de Ocurrencia", eva.getSerie(), eva.getCantSlice()};
            lstPOs.add(obj1);
        }
        sessionParteOcurrencia.setLstEvaPieG(lstPOs);
    }
    
    public void change3DPie(ValueChangeEvent vce) {
        boolean val = (Boolean) vce.getNewValue();
        sessionParteOcurrencia.setPie3D(val);
        piePO.setThreeDEffect(val);
        Utils.addTarget(piePO);
    }
    
    public void limpiarPO(ActionEvent actionEvent) {
        _limpiarPO();
    }
    
    public List<BeanParteOcurrencia> _limpiarPO(){
        sessionParteOcurrencia.getLstNotifPOs().clear();
        sessionParteOcurrencia.setDocentePO(null);
        sessionParteOcurrencia.setCidProblema(null);
        sessionParteOcurrencia.setCidSedePO(null);
        sessionParteOcurrencia.setFecMaxPO(null);
        sessionParteOcurrencia.setFecMinPO(null);
        buscarPartesOcurrencia();
        socSedesPO.resetValue();
        itDocPO.resetValue();
        socProbPO.resetValue();
        idMaxPO.resetValue();
        idMinPO.resetValue();
        Utils.addTargetMany(socSedesPO,itDocPO,socProbPO,idMaxPO,idMinPO);
        return sessionParteOcurrencia.getLstNotifPOs();
    }
    
    public void setUsuario(BeanUsuario usuario) {
        this.usuario = usuario;
    }

    public BeanUsuario getUsuario() {
        return usuario;
    }

    public void setSessionParteOcurrencia(bSessionParteOcurrencia sessionParteOcurrencia) {
        this.sessionParteOcurrencia = sessionParteOcurrencia;
    }

    public bSessionParteOcurrencia getSessionParteOcurrencia() {
        return sessionParteOcurrencia;
    }

    public void setSocSedesPO(RichSelectOneChoice socSedesPO) {
        this.socSedesPO = socSedesPO;
    }

    public RichSelectOneChoice getSocSedesPO() {
        return socSedesPO;
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

    public void setTbPOs(RichTable tbPOs) {
        this.tbPOs = tbPOs;
    }

    public RichTable getTbPOs() {
        return tbPOs;
    }

    public void setPiePO(UIGraph piePO) {
        this.piePO = piePO;
    }

    public UIGraph getPiePO() {
        return piePO;
    }
}