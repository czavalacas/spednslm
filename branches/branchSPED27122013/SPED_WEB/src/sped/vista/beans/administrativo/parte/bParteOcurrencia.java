package sped.vista.beans.administrativo.parte;

import java.util.ArrayList;
import java.util.Date;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;

import javax.faces.event.ActionEvent;

import javax.faces.event.ValueChangeEvent;

import javax.faces.model.SelectItem;

import oracle.adf.view.faces.bi.component.graph.UIGraph;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.input.RichInputDate;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.input.RichSelectBooleanCheckbox;
import oracle.adf.view.rich.component.rich.input.RichSelectOneChoice;

import oracle.adf.view.rich.component.rich.layout.RichPanelSplitter;

import oracle.adf.view.rich.component.rich.nav.RichButton;
import oracle.adf.view.rich.datatransfer.DataFlavor;
import oracle.adf.view.rich.dnd.DnDAction;
import oracle.adf.view.rich.event.DropEvent;

import sped.negocio.LNSF.IL.LN_C_SFParteOcurrenciaLocal;
import sped.negocio.LNSF.IL.LN_C_SFUtilsLocal;

import sped.negocio.entidades.beans.BeanBar;
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
    private UIGraph piePO;
    private UIGraph gbarPOs;
    private RichPanelSplitter ps1;
    private RichTable tbProRr;
    private RichTable tbProbs;
    private UIGraph gradar;
    private RichSelectBooleanCheckbox sbc1;
    private RichButton btnRadar;
    private RichSelectBooleanCheckbox chkbResc;
    private bSessionParteOcurrencia sessionParteOcurrencia;
    FacesContext ctx = FacesContext.getCurrentInstance();
    private BeanUsuario usuario = (BeanUsuario) Utils.getSession("USER");
    @EJB
    private LN_C_SFUtilsLocal ln_C_SFUtilsLocal;
    @EJB
    private LN_C_SFParteOcurrenciaLocal ln_C_SFParteOcurrenciaLocal;

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
                sessionParteOcurrencia.setCidSedePO(String.valueOf(usuario.getSede().getNidSede()));
            }else{
                sessionParteOcurrencia.setEnableSedes(false);
            }
            buscarPartesOcurrencia();
        }else{
            
        }
    }

    public String buscarPartesOcurrencia(){
        try {
           int idUsuario = 0; 
           if(usuario.getRol().getNidRol() == 4){
               sessionParteOcurrencia.setCidSedePO(String.valueOf(usuario.getSede().getNidSede()));
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
        sessionParteOcurrencia.setLstPOsPieG(lstPOs);
    }
    
    public void setDataBarrasChart(BeanBar[] lst){
        List<Object[]> lstPOs = new ArrayList();
        String nombreProfesor;
        String nombreProblema;
        int contProblemas;
        for(int i = 0; i < lst.length; i++){
            nombreProfesor = lst[i].getGroup();
            contProblemas = lst[i].getCantidad();
            nombreProblema = lst[i].getSerie();
            Object[] obj1 = { nombreProfesor,nombreProblema,contProblemas};
            lstPOs.add(obj1);
        }
        sessionParteOcurrencia.setLstPOsBarChart(lstPOs);
    }
    
    public void setDataRadar(BeanBar[] vecBar){
        List<Object[]> lstPOs = new ArrayList();
        for(int i = 0; i < vecBar.length; i++){
            BeanBar bar = vecBar[i];
            if(sessionParteOcurrencia.getLstProfesoresRadar().contains(bar.getGroup()) && 
               sessionParteOcurrencia.getLstProblemasRadar().contains(bar.getSerie()) ){
                Object[] obj1 = { bar.getSerie(),bar.getGroup(),bar.getCantidad()};
                lstPOs.add(obj1);     
            }
        }
        sessionParteOcurrencia.setLstPOsRadarChart(lstPOs);
    }
    
    public void change3DPie(ValueChangeEvent vce) {
        boolean val = (Boolean) vce.getNewValue();
        sessionParteOcurrencia.setPie3D(val);
        if(sessionParteOcurrencia.isRenderedPie()){
            piePO.setThreeDEffect(val);
            Utils.addTarget(piePO);
        }else if(sessionParteOcurrencia.isRenderedBar()){
            gbarPOs.setThreeDEffect(val);
            Utils.addTarget(gbarPOs);
        }
    }
    

    public void changeRescalar(ValueChangeEvent vce) {
        boolean val = (Boolean) vce.getNewValue();
        String rescalar = val == true ? "withRescale" : "withoutRescale";
        sessionParteOcurrencia.setRescalar(val);
        if(sessionParteOcurrencia.isRenderedPie()){
            piePO.setHideAndShowBehavior(rescalar);
            Utils.addTarget(piePO);
        }else if(sessionParteOcurrencia.isRenderedBar()){
            gbarPOs.setHideAndShowBehavior(rescalar);
            Utils.addTarget(gbarPOs);
        }
    }
    
    public void cambioTipoGrafico(ValueChangeEvent vce) {
        try{
            String tipGraf = (String) vce.getNewValue();
            sessionParteOcurrencia.getLstProblemasRadar().clear();
            sessionParteOcurrencia.getLstProfesoresRadar().clear();
            if(sessionParteOcurrencia.getLstPOsRadarChart() != null){
                sessionParteOcurrencia.getLstPOsRadarChart().clear();   
            }
            if(tipGraf.equals("P")){
                setListPieGraph(sessionParteOcurrencia.getLstNotifPOs().get(sessionParteOcurrencia.getLstNotifPOs().size()-1).getLstPies());
                sessionParteOcurrencia.setRenderedPie(true);
                piePO.setTabularData(sessionParteOcurrencia.getLstPOsPieG());
                piePO.setRendered(true);
                
                sessionParteOcurrencia.setRenderedBar(false);
                gbarPOs.setRendered(false);
                
                sbc1.setRendered(true);
                chkbResc.setRendered(true);
                
                sessionParteOcurrencia.setRenderedRadar(false);
                tbProRr.setRendered(false);
                gradar.setRendered(false);
                tbProbs.setRendered(false);
                btnRadar.setRendered(false);
                Utils.addTargetMany(sbc1,chkbResc,tbProRr,tbProbs,btnRadar,piePO,gbarPOs,gradar);
            }else if(tipGraf.equals("B")){
                setDataBarrasChart(sessionParteOcurrencia.getLstNotifPOs().get(sessionParteOcurrencia.getLstNotifPOs().size()-1).getLstBars());
                sessionParteOcurrencia.setRenderedBar(true);
                gbarPOs.setTabularData(sessionParteOcurrencia.getLstPOsBarChart());
                gbarPOs.setRendered(true);
                
                sessionParteOcurrencia.setRenderedPie(false);
                piePO.setRendered(false);
                sbc1.setRendered(true);
                chkbResc.setRendered(true);
                
                sessionParteOcurrencia.setRenderedRadar(false);
                gradar.setRendered(false);
                tbProRr.setRendered(false);
                tbProbs.setRendered(false);
                btnRadar.setRendered(false);
                
                Utils.addTargetMany(sbc1,chkbResc,tbProRr,tbProbs,btnRadar,piePO,gbarPOs,gradar);
            }else{//R = radar
                sessionParteOcurrencia.setRenderedBar(false);
                gbarPOs.setRendered(false);
                Utils.addTarget(gbarPOs);
                
                sessionParteOcurrencia.setRenderedPie(false);
                piePO.setRendered(false);
                Utils.addTarget(piePO);
                
                sessionParteOcurrencia.setRenderedRadar(true);
                gradar.setRendered(true);
                tbProRr.setRendered(true);
                tbProbs.setRendered(true);
                btnRadar.setRendered(true);
                sbc1.setRendered(false);
                chkbResc.setRendered(false);
                
                Utils.addTargetMany(sbc1,tbProRr,tbProbs,btnRadar,gradar,chkbResc);
            }
            Utils.addTarget(ps1);
        }catch(Exception e){
            e.printStackTrace();
            return;
        }
    }
    
    public void verGraficoRadar(ActionEvent ae) {
        setDataRadar(sessionParteOcurrencia.getLstNotifPOs().get(sessionParteOcurrencia.getLstNotifPOs().size()-1).getLstBars());
        gradar.setTabularData(sessionParteOcurrencia.getLstPOsRadarChart());
        Utils.addTargetMany(ps1,gradar);
    }
    
    public void limpiarPO(ActionEvent actionEvent) {
        if(sessionParteOcurrencia.getLstNotifPOs() != null){
            sessionParteOcurrencia.getLstNotifPOs().clear();
        }
        _limpiarPO();
        if(sessionParteOcurrencia.isRenderedPie()){
            setListPieGraph(sessionParteOcurrencia.getLstNotifPOs().get(sessionParteOcurrencia.getLstNotifPOs().size()-1).getLstPies());
            piePO.setTabularData(sessionParteOcurrencia.getLstPOsPieG());
            Utils.addTargetMany(piePO,ps1);
        }else if(sessionParteOcurrencia.isRenderedBar()){
            setDataBarrasChart(sessionParteOcurrencia.getLstNotifPOs().get(sessionParteOcurrencia.getLstNotifPOs().size()-1).getLstBars());
            gbarPOs.setTabularData(sessionParteOcurrencia.getLstPOsBarChart());
            Utils.addTargetMany(gbarPOs,ps1);
        }
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
    
    public DnDAction handleItemDrop(DropEvent dropEvent) {
        try {
            DataFlavor<String> df = DataFlavor.getDataFlavor(String.class);
            String droppedValue = dropEvent.getTransferable().getData(df);
            if (droppedValue == null) {
                return DnDAction.NONE;
            } else {//ot6 es el id de la columna profesor de la tabla tbPOs en el jsff
                if(sessionParteOcurrencia.getLstProfesoresRadar().size() <= 2 && dropEvent.getDragClientId().contains("ot6")){
                    if(!sessionParteOcurrencia.getLstProfesoresRadar().contains(droppedValue)){
                        sessionParteOcurrencia.getLstProfesoresRadar().add(droppedValue);
                        tbProRr.setValue(sessionParteOcurrencia.getLstProfesoresRadar());
                        Utils.addTarget(tbProRr);
                    }
                }
            }
            return DnDAction.COPY;
        } catch (Exception ex) {
            System.out.println("item drop failed with : " + ex.getMessage());
            ex.printStackTrace();
            return DnDAction.NONE;
        }
    }
    
    public DnDAction handleItemDropProblema(DropEvent dropEvent) {
        try {
            DataFlavor<String> df = DataFlavor.getDataFlavor(String.class);
            String droppedValue = dropEvent.getTransferable().getData(df);
            if (droppedValue == null) {
                return DnDAction.NONE;
            } else {//ot9 es el id de la columna problema de la tabla tbPOs en el jsff
                if(sessionParteOcurrencia.getLstProblemasRadar().size() <= 6 && dropEvent.getDragClientId().contains("ot9")){
                    if(!sessionParteOcurrencia.getLstProblemasRadar().contains(droppedValue)){
                        sessionParteOcurrencia.getLstProblemasRadar().add(droppedValue);
                        tbProbs.setValue(sessionParteOcurrencia.getLstProblemasRadar());
                        Utils.addTarget(tbProbs);
                    }
                }
            }
            return DnDAction.COPY;
        } catch (Exception ex) {
            System.out.println("item drop failed with : " + ex.getMessage());
            ex.printStackTrace();
            return DnDAction.NONE;
        }
    }
    
    public DnDAction handleItemDropProblemaProfesor(DropEvent dropEvent) {
        try {
            DataFlavor<String> df = DataFlavor.getDataFlavor(String.class);
            String droppedValue = dropEvent.getTransferable().getData(df);
            if (droppedValue == null) {
                return DnDAction.NONE;
            } else {
                if(sessionParteOcurrencia.getLstProblemasRadar().contains(droppedValue)){
                    sessionParteOcurrencia.getLstProblemasRadar().remove(droppedValue);
                    tbProbs.setValue(sessionParteOcurrencia.getLstProblemasRadar());
                    Utils.addTarget(tbProbs);
                }
                if(sessionParteOcurrencia.getLstProfesoresRadar().contains(droppedValue)){
                    sessionParteOcurrencia.getLstProfesoresRadar().remove(droppedValue);
                    tbProRr.setValue(sessionParteOcurrencia.getLstProfesoresRadar());
                    Utils.addTarget(tbProRr);
                }
            }
            return DnDAction.COPY;
        } catch (Exception ex) {
            System.out.println("item drop failed with : " + ex.getMessage());
            ex.printStackTrace();
            return DnDAction.NONE;
        }
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

    public void setGbarPOs(UIGraph gbarPOs) {
        this.gbarPOs = gbarPOs;
    }

    public UIGraph getGbarPOs() {
        return gbarPOs;
    }

    public void setPs1(RichPanelSplitter ps1) {
        this.ps1 = ps1;
    }

    public RichPanelSplitter getPs1() {
        return ps1;
    }

    public void setTbProRr(RichTable tbProRr) {
        this.tbProRr = tbProRr;
    }

    public RichTable getTbProRr() {
        return tbProRr;
    }

    public void setTbProbs(RichTable tbProbs) {
        this.tbProbs = tbProbs;
    }

    public RichTable getTbProbs() {
        return tbProbs;
    }

    public void setGradar(UIGraph gradar) {
        this.gradar = gradar;
    }

    public UIGraph getGradar() {
        return gradar;
    }

    public void setSbc1(RichSelectBooleanCheckbox sbc1) {
        this.sbc1 = sbc1;
    }

    public RichSelectBooleanCheckbox getSbc1() {
        return sbc1;
    }

    public void setBtnRadar(RichButton btnRadar) {
        this.btnRadar = btnRadar;
    }

    public RichButton getBtnRadar() {
        return btnRadar;
    }

    public void setChkbResc(RichSelectBooleanCheckbox chkbResc) {
        this.chkbResc = chkbResc;
    }

    public RichSelectBooleanCheckbox getChkbResc() {
        return chkbResc;
    }
}