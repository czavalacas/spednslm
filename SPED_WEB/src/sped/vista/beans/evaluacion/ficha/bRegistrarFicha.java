package sped.vista.beans.evaluacion.ficha;

import com.sun.faces.facelets.tag.jsf.core.FacetHandler;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;

import javax.ejb.EJB;

import javax.el.ValueExpression;

import javax.faces.component.FacesComponent;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.ComponentSystemEvent;

import javax.faces.event.ValueChangeEvent;

import javax.faces.webapp.FacetTag;

import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.data.RichColumn;
import oracle.adf.view.rich.component.rich.data.RichTable;

import oracle.adf.view.rich.component.rich.data.RichTreeTable;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.layout.RichPanelBox;
import oracle.adf.view.rich.component.rich.nav.RichButton;

import oracle.adf.view.rich.component.rich.output.RichMessages;
import oracle.adf.view.rich.event.DialogEvent;

import oracle.adf.view.rich.event.PopupCanceledEvent;
import oracle.adf.view.rich.render.ClientEvent;
import oracle.adf.view.rich.component.rich.data.RichTreeTable;


import oracle.adf.view.rich.component.rich.output.RichContextInfo;
import oracle.adf.view.rich.component.rich.output.RichOutputText;

import oracle.adf.view.rich.event.ClientListenerSet;

import oracle.adfinternal.view.faces.taglib.behaviors.ShowPopupBehaviorTag;

import oracle.jbo.Row;
import oracle.jbo.uicli.binding.JUCtrlHierBinding;

import oracle.jbo.uicli.binding.JUCtrlHierNodeBinding;

import org.apache.myfaces.trinidad.event.SelectionEvent;

import org.apache.myfaces.trinidad.model.ChildPropertyTreeModel;
import org.apache.myfaces.trinidad.model.CollectionModel;

import org.apache.myfaces.trinidad.model.RowKeySet;
import org.apache.myfaces.trinidad.model.TreeModel;

import sped.negocio.LNSF.IR.LN_C_SFCriterioRemote;
import sped.negocio.LNSF.IR.LN_C_SFFichaRemote;

import sped.negocio.LNSF.IR.LN_C_SFIndicadorRemote;
import sped.negocio.LNSF.IR.LN_T_SFCriterioRemote;
import sped.negocio.LNSF.IR.LN_T_SFIndicadorRemote;
import sped.negocio.entidades.beans.BeanCriterio;
import sped.negocio.entidades.beans.BeanError;
import sped.negocio.entidades.beans.BeanFicha;

import sped.negocio.entidades.beans.BeanIndicador;

import sped.vista.Utils.Utils;

/** Clase de Respaldo de Frm_Registrar_Ficha.jsff
 * @author dfloresgonz
 * @since 27.12.2013
 */
public class bRegistrarFicha {
    
    private bSessionRegistrarFicha sessionRegistrarFicha;
    @EJB
    private LN_C_SFFichaRemote ln_C_SFFichaRemote;
    @EJB
    private LN_C_SFCriterioRemote ln_C_SFCriterioRemote;
    @EJB
    private LN_C_SFIndicadorRemote ln_C_SFIndicadorRemote;
    @EJB
    private LN_T_SFCriterioRemote ln_T_SFCriterioRemote;
    @EJB
    private LN_T_SFIndicadorRemote ln_T_SFIndicadorRemote;
    FacesContext ctx = FacesContext.getCurrentInstance();
    private ChildPropertyTreeModel permisosTree;
    private RichTable tbFichas;
    private RichButton btnEditFicha;
    private RichButton btnNuevaFlota;
    private RichPanelBox panelBoxNewFicha;
    private RichPopup popCrits;
    private RichPopup popIndis;
    private RichTable tbCrits;
    private RichTable tbIndis;
    private RichTreeTable treeCriIndi;
    private RichTable tbIndCr;
    private RichPopup popIndByCrit;
    private RichInputText itDescVersion;
    private RichMessages mensaje;
    private RichInputText itDescCrit;
    private RichMessages mensajeIndicador;
    private RichInputText itDescIndi;

    public bRegistrarFicha() {
        
    }

    @PostConstruct
    public void methodInvokeOncedOnPageLoad(){
        if(sessionRegistrarFicha.getExec() == 0){
            sessionRegistrarFicha.setExec(1);
            sessionRegistrarFicha.setLstFichas(ln_C_SFFichaRemote.getLstFichasByAttr_LN());
            mostrarCuadre();
        }else{
            mostrarCuadre();
            //Utils.depurar("POST CONSTRUCT otras veces");
        }
    }
    
    public String mostrarCuadre() {
        BeanCriterio b = new BeanCriterio();
        b.setDescripcionCriterio("Ficha de Evaluacion");
        List<BeanCriterio> lstBeanCriterio = new ArrayList<BeanCriterio>();
        b.setLstIndicadores(new ArrayList<BeanCriterio>(sessionRegistrarFicha.getLstCriteriosMultiples()));
        lstBeanCriterio.add(b);
        permisosTree = new ChildPropertyTreeModel(lstBeanCriterio,"lstIndicadores");
        sessionRegistrarFicha.setPermisosTree(permisosTree);
        if(treeCriIndi != null){ 
            treeCriIndi.setValue(sessionRegistrarFicha.getPermisosTree());
            Utils.addTarget(treeCriIndi);
        }
        return null;
    }
    
    public void selectFicha(SelectionEvent se) {
        try{
          //  BeanFicha beanFicha = (BeanFicha) Utils.getRowTable(se);
            btnEditFicha.setDisabled(false);
            Utils.addTarget(btnEditFicha);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void selectCriterios(SelectionEvent se) {
        BeanCriterio beanCriterio = new BeanCriterio();
        beanCriterio = (BeanCriterio) Utils.getRowTable(se);
        if(beanCriterio != null){
            if(beanCriterio.isSelected()){
                Iterator iti = sessionRegistrarFicha.getLstCriteriosMultiples().iterator();
                while(iti.hasNext()){
                    BeanCriterio bCrit = (BeanCriterio) iti.next();
                }
                sessionRegistrarFicha.getLstCriteriosMultiples().remove(beanCriterio);
                beanCriterio.setSelected(false);
            }else{
                beanCriterio.setSelected(true);
                beanCriterio.setMostrarBoton(true);
                sessionRegistrarFicha.getLstCriteriosMultiples().add(beanCriterio);
            }
            Utils.unselectFilas(tbCrits);
            mostrarCuadre();
        }
    }
    
    public boolean contiene(List<BeanIndicador> lstIndisSelec, Integer nidInd,boolean borrar){
        if(lstIndisSelec != null){
            Iterator it = lstIndisSelec.iterator();
            while(it.hasNext()){
                BeanIndicador bInd = (BeanIndicador) it.next();
                if(bInd.getNidIndicador().compareTo(nidInd) == 0){
                    if(borrar){
                        it.remove();
                    }
                    return true;
                }
            }
        }
        return false;
    }
    
    public void selectIndis(SelectionEvent se) {
        BeanIndicador beanIndicador = (BeanIndicador) Utils.getRowTable(se);
        if(beanIndicador != null){
            if(beanIndicador.isSelected()){
                /*Iterator iti = sessionRegistrarFicha.getLstIndisSelected().iterator();
                while(iti.hasNext()){
                    BeanIndicador bInd = (BeanIndicador) iti.next();
                }*/
                if(!contiene(sessionRegistrarFicha.getLstIndisSelected(),beanIndicador.getNidIndicador(),true)){
                    sessionRegistrarFicha.getLstIndisSelected().add(beanIndicador);   
                }
                //sessionRegistrarFicha.getLstIndisSelected().remove(beanIndicador);
                beanIndicador.setSelected(false);
            }else{
                beanIndicador.setSelected(true);
                if(!contiene(sessionRegistrarFicha.getLstIndisSelected(),beanIndicador.getNidIndicador(),false)){
                    sessionRegistrarFicha.getLstIndisSelected().add(beanIndicador);   
                }
            }
            BeanCriterio critSelected = this.getCriterio(sessionRegistrarFicha.getCritSelected().getNidCriterio());
            if(critSelected.getLstIndicadores() != null){
                critSelected.getLstIndicadores().removeAll(critSelected.getLstIndicadores());
                critSelected.setLstIndicadores(this.lstIndisToCrit(sessionRegistrarFicha.getLstIndisSelected()));
                sessionRegistrarFicha.setLstIndicadoresByCriterio(sessionRegistrarFicha.getLstIndisSelected());
            }
            Utils.unselectFilas(tbIndis);
            Utils.addTarget(treeCriIndi);
            if(tbIndCr != null){
                Utils.unselectFilas(tbIndCr);
            }
        }
    }
    
    public BeanCriterio getCriterio(int nidCriterio){
        if(sessionRegistrarFicha.getLstCriteriosMultiples() != null){
            for(BeanCriterio crit : sessionRegistrarFicha.getLstCriteriosMultiples()){
                if(crit.getNidCriterio().compareTo(nidCriterio) == 0){
                    return crit;
                }
            }
        }
        return null;
    }
    
    public List<BeanCriterio> lstIndisToCrit(List<BeanIndicador> lstIndis){
        List<BeanCriterio> lstCrits = new ArrayList<BeanCriterio>();
        if(lstIndis != null){
            Iterator it = lstIndis.iterator();
            BeanIndicador indi = new BeanIndicador();
            while(it.hasNext()){
                BeanCriterio crit = new BeanCriterio();
                indi = (BeanIndicador) it.next();
                crit.setNidCriterio(indi.getNidIndicador());
                crit.setDescripcionCriterio(indi.getDescripcionIndicador());
                crit.setSelected(indi.isSelected());
                lstCrits.add(crit);    
            }
        }
        return lstCrits;
    }
    
    public List<BeanIndicador> lstCritToIndi(List<BeanCriterio> lstCrits){
        List<BeanIndicador> lstIns = new ArrayList<BeanIndicador>();
        if(lstCrits != null){
            Iterator it = lstCrits.iterator();
            BeanCriterio cri = new BeanCriterio();
            while(it.hasNext()){
                BeanIndicador ind = new BeanIndicador();
                cri = (BeanCriterio) it.next();
                ind.setNidIndicador(cri.getNidCriterio());
                ind.setDescripcionIndicador(cri.getDescripcionCriterio());
                ind.setSelected(cri.isSelected());
                lstIns.add(ind);
            }
        }
        return lstIns;
    }
    
    public void cancelarPopUpCriterios(PopupCanceledEvent popupCanceledEvent) {
        if(sessionRegistrarFicha.getLstCriteriosMultiples() != null){
            Utils.sysout("lista: "+sessionRegistrarFicha.getLstCriteriosMultiples().size());
        }
        if(treeCriIndi != null){ 
            treeCriIndi.setValue(sessionRegistrarFicha.getPermisosTree());
            Utils.addTarget(treeCriIndi);
        }
    }
    
    public void dialogOkCriterios(DialogEvent de) {
        if (de.getOutcome() == DialogEvent.Outcome.ok){
            for(BeanCriterio crit : sessionRegistrarFicha.getLstCriteriosMultiples()){
                Utils.sysout("crit:"+crit.getDescripcionCriterio());   
            }
        }
    }
    
    public void refrescarTablaFichas(ActionEvent actionEvent) {
        sessionRegistrarFicha.setLstFichas(ln_C_SFFichaRemote.getLstFichasByAttr_LN());
        tbFichas.setValue(sessionRegistrarFicha.getLstFichas());
        btnEditFicha.setDisabled(true);
        Utils.addTargetMany(tbFichas,btnEditFicha);
        Utils.unselectFilas(tbFichas);
    }

    public void nuevaFicha(ActionEvent actionEvent) {
        sessionRegistrarFicha.setVisiblePanelBoxPanelBoxFicha(true);
        panelBoxNewFicha.setVisible(true);
        btnEditFicha.setDisabled(true);
        Utils.addTargetMany(btnNuevaFlota,btnEditFicha,panelBoxNewFicha);
    }
    
    public void abrirPopCriterios(ActionEvent actionEvent) {
        buscarCriterios();
        if(sessionRegistrarFicha.getLstCriteriosMultiples() != null && sessionRegistrarFicha.getLstCriterios() != null){
            Iterator it = sessionRegistrarFicha.getLstCriterios().iterator();
            while(it.hasNext()){
                BeanCriterio bCrit = (BeanCriterio) it.next();
                if(contiene(sessionRegistrarFicha.getLstCriteriosMultiples(),bCrit.getNidCriterio())){
                    Utils.sysout("contiene");
                    bCrit.setSelected(true);
                }
            }
          //  sessionRegistrarFicha.getLstCriteriosMultiples().removeAll(sessionRegistrarFicha.getLstCriteriosMultiples());
        }
        Utils.showPopUpMIDDLE(popCrits);
    }
    
    public boolean contiene(HashSet<BeanCriterio> lstCritMult,Integer nidCrit){
        Iterator it = lstCritMult.iterator();
        while(it.hasNext()){
            BeanCriterio bCrit = (BeanCriterio) it.next();
            if(bCrit.getNidCriterio().compareTo(nidCrit) == 0){
                return true;
            }
        }
        return false;
    }
    
    public void abrirPopIndicadores(ActionEvent actionEvent) {
        buscarIndicadores();
        sessionRegistrarFicha.setLstIndisSelected(new ArrayList<BeanIndicador>());
        sessionRegistrarFicha.setLstIndisSelected(this.lstCritToIndi(sessionRegistrarFicha.getCritSelected().getLstIndicadores()));
        Utils.showPopUpMIDDLE(popIndis);
    }
    
    public String buscarCriterios(){
        sessionRegistrarFicha.setLstCriterios(ln_C_SFCriterioRemote.getCriteriosByAttr_LN(sessionRegistrarFicha.getNidCriterio(),
                                                                                                sessionRegistrarFicha.getDescCriterio()));
        Utils.unselectFilas(tbCrits);
        return null;
    }
    
    public String buscarIndicadores(){
        sessionRegistrarFicha.setLstIndicadores(ln_C_SFIndicadorRemote.getIndicadoresByAttr_LN(sessionRegistrarFicha.getDescIndicador(),
                                                                                                     sessionRegistrarFicha.getNidIndicador(),
                                                                                                     sessionRegistrarFicha.getLstCriteriosMultiples()));
        Utils.unselectFilas(tbIndis);
        return null;
    }
    
    public void busquedaConTecla(ClientEvent ce){
        //String message = (String) ce.getParameters().get("fvalue");
        buscarCriterios();
    }
    
    public String resetCrits(){
        sessionRegistrarFicha.setNidCriterio(0);
        sessionRegistrarFicha.setDescCriterio(null);
        buscarCriterios();
        return null;
    }
    
    public String resetIndis(){
        sessionRegistrarFicha.setNidIndicador(0);
        sessionRegistrarFicha.setDescIndicador(null);
        buscarIndicadores();
        return null;
    }
    
    public void selectTree(SelectionEvent se) {
        RichTreeTable tt = (RichTreeTable) se.getSource();
        TreeModel model = (TreeModel) tt.getValue();
        RowKeySet rks = tt.getSelectedRowKeys();
        Iterator keys = rks.iterator();
        if (keys.hasNext()) {
            List key = (List)keys.next();
            if(key.size() == 2){
                int llave = Integer.parseInt(key.get(1).toString());
                List<BeanCriterio> lstBeanCriterio = (List<BeanCriterio>) model.getWrappedData();
                BeanCriterio criterio = (BeanCriterio) lstBeanCriterio.get(0).getLstIndicadores().get(llave).clone();
                sessionRegistrarFicha.setCritSelected(criterio);
                Utils.showPopUpMIDDLE(popIndByCrit);
                sessionRegistrarFicha.setDescCriterioSeleccionado(criterio.getDescripcionCriterio());
                sessionRegistrarFicha.setLstIndicadoresByCriterio(this.lstCritToIndi(criterio.getLstIndicadores()));
            }
        }
    }

    public void selectIndicadorByCriterio(SelectionEvent se) {
        BeanIndicador beanIndicador = (BeanIndicador) Utils.getRowTable(se);
        Iterator it = sessionRegistrarFicha.getLstIndicadoresByCriterio().iterator();
        while(it.hasNext()){
            BeanIndicador beanIndicador2 = (BeanIndicador) it.next();
            if(beanIndicador.getNidIndicador().compareTo(beanIndicador2.getNidIndicador()) == 0){
                it.remove();
                beanIndicador2.setSelected(false);
                Utils.unselectFilas(tbIndCr);
            }
        }
        BeanCriterio critSelected = this.getCriterio(sessionRegistrarFicha.getCritSelected().getNidCriterio());
        if(critSelected.getLstIndicadores() != null){
            Iterator iti = critSelected.getLstIndicadores().iterator();
            while(iti.hasNext()){
                BeanCriterio beanCriterio = (BeanCriterio) iti.next();
                if(beanIndicador.getNidIndicador().compareTo(beanCriterio.getNidCriterio()) == 0){
                    iti.remove();
                    Utils.unselectFilas(treeCriIndi);
                    return;
                }
            }
        }
    }
    
    public void changeTipoFicha(ValueChangeEvent vce) {
        if(sessionRegistrarFicha.getTipFichaCurs() != null){
            if(vce.getNewValue() != null){
                getNuevaVersion(vce.getNewValue().toString(),sessionRegistrarFicha.getTipFichaCurs());
            }
        }
    }
    
    public void changeTipoFichaCurso(ValueChangeEvent vce) {
        if(sessionRegistrarFicha.getTipoFicha() != null){
            if(vce.getNewValue() != null){
                getNuevaVersion(sessionRegistrarFicha.getTipoFicha(),vce.getNewValue().toString());
            }
        }
    }
    
    public void getNuevaVersion(String tipFicha,String tipFichaCurso){
        int year = (sessionRegistrarFicha.getFechaHoy().getYear() + 1900);
        int mes = (sessionRegistrarFicha.getFechaHoy().getMonth() + 1);
        String version = ln_C_SFFichaRemote.getNextVersionFichaByAttr_LN(year,mes,tipFicha,tipFichaCurso);
        sessionRegistrarFicha.setVersionGenerada(version);
        itDescVersion.setValue(version);
        Utils.addTarget(itDescVersion);
        Utils.sysout("version:"+version);
    }
    
    public void cancelarPopIndiByCrit(PopupCanceledEvent popupCanceledEvent) {
        Utils.unselectFilas(treeCriIndi);
    }
    
    public void registrarCriterio(ActionEvent ae) {
        BeanCriterio bCrit = ln_T_SFCriterioRemote.registrarCriterio(sessionRegistrarFicha.getDescCriterio());
        if(bCrit.getBeanError() != null){
            BeanError error = bCrit.getBeanError();
            int severidad = 0;
            if(error.getCidError().equals("000")){
                severidad = 3;
                Utils.sysout("Grabo el criterio");
            }else{
                severidad = 1;
            }
            mensaje.setText(error.getTituloError());
            Utils.addTarget(mensaje);
            Utils.mostrarMensaje(ctx,error.getDescripcionError(),error.getTituloError(),severidad);
        }else{
            Utils.mostrarMensaje(ctx,"Error Inesperado","Error",1);
        }
        sessionRegistrarFicha.setDescCriterio(null);
        itDescCrit.resetValue();
        Utils.addTarget(itDescCrit);
        buscarCriterios();
    }
    
    
    public void registrarIndicador(ActionEvent ae) {
        BeanIndicador bIndi = ln_T_SFIndicadorRemote.registrarIndicador(sessionRegistrarFicha.getDescIndicador());
        if(bIndi.getBeanError() != null){
            BeanError error = bIndi.getBeanError();
            int severidad = 0;
            if(error.getCidError().equals("000")){
                severidad = 3;
                Utils.sysout("Grabo el indicador");
            }else{
                severidad = 1;
            }
            mensajeIndicador.setText(error.getTituloError());
            Utils.addTarget(mensajeIndicador);
            Utils.mostrarMensaje(ctx,error.getDescripcionError(),error.getTituloError(),severidad);
        }else{
            Utils.mostrarMensaje(ctx,"Error Inesperado","Error",1);
        }
        sessionRegistrarFicha.setDescIndicador(null);
        itDescIndi.resetValue();
        Utils.addTarget(itDescIndi);
        buscarIndicadores();
    }
    
    public void changeSliderValor(ValueChangeEvent vce) {
        Utils.sysout("vce:"+vce.getNewValue());
        int val = Integer.parseInt(vce.getNewValue().toString());
        List<UIComponent> children = null;
        children = this.treeCriIndi.getChildren();
        try {
            if (children != null) {
                Iterator it = children.iterator();
                while(it.hasNext()){
                    UIComponent child = (UIComponent) it.next();
                    it.remove();   
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        for(int c = 0; c < val; c++) {
            RichColumn col = new RichColumn();
            col.setHeaderText("Valor "+c);
            col.setWidth("40");
            RichOutputText coldata = new RichOutputText();
            coldata.setValue("Leyenda");
            ClientListenerSet set = coldata.getClientListeners();
            if (set == null) {
                set = new ClientListenerSet();
                set.addBehavior("new AdfShowPopupBehavior('::p1',AdfRichPopup.ALIGN_AFTER_END,null,'click')");
                coldata.setClientListeners(set);
            }
            col.getChildren().add(coldata);
            children.add(col);
        }
        Utils.addTarget(treeCriIndi);
    }
    
    public void setTbFichas(RichTable tbFichas) {
        this.tbFichas = tbFichas;
    }

    public RichTable getTbFichas() {
        return tbFichas;
    }

    public void setSessionRegistrarFicha(bSessionRegistrarFicha sessionRegistrarFicha) {
        this.sessionRegistrarFicha = sessionRegistrarFicha;
    }

    public bSessionRegistrarFicha getSessionRegistrarFicha() {
        return sessionRegistrarFicha;
    }

    public void setBtnEditFicha(RichButton btnEditFicha) {
        this.btnEditFicha = btnEditFicha;
    }

    public RichButton getBtnEditFicha() {
        return btnEditFicha;
    }

    public void setBtnNuevaFlota(RichButton btnNuevaFlota) {
        this.btnNuevaFlota = btnNuevaFlota;
    }

    public RichButton getBtnNuevaFlota() {
        return btnNuevaFlota;
    }

    public void setPanelBoxNewFicha(RichPanelBox panelBoxNewFicha) {
        this.panelBoxNewFicha = panelBoxNewFicha;
    }

    public RichPanelBox getPanelBoxNewFicha() {
        return panelBoxNewFicha;
    }

    public void setPopCrits(RichPopup popCrits) {
        this.popCrits = popCrits;
    }

    public RichPopup getPopCrits() {
        return popCrits;
    }

    public void setPopIndis(RichPopup popIndis) {
        this.popIndis = popIndis;
    }

    public RichPopup getPopIndis() {
        return popIndis;
    }

    public void setTbCrits(RichTable tbCrits) {
        this.tbCrits = tbCrits;
    }

    public RichTable getTbCrits() {
        return tbCrits;
    }

    public void setTbIndis(RichTable tbIndis) {
        this.tbIndis = tbIndis;
    }

    public RichTable getTbIndis() {
        return tbIndis;
    }

    public void setPermisosTree(ChildPropertyTreeModel permisosTree) {
        this.permisosTree = permisosTree;
    }

    public ChildPropertyTreeModel getPermisosTree() {
        return permisosTree;
    }

    public void setTreeCriIndi(RichTreeTable treeCriIndi) {
        this.treeCriIndi = treeCriIndi;
    }

    public RichTreeTable getTreeCriIndi() {
        return treeCriIndi;
    }

    public void setTbIndCr(RichTable tbIndCr) {
        this.tbIndCr = tbIndCr;
    }

    public RichTable getTbIndCr() {
        return tbIndCr;
    }

    public void setPopIndByCrit(RichPopup popIndByCrit) {
        this.popIndByCrit = popIndByCrit;
    }

    public RichPopup getPopIndByCrit() {
        return popIndByCrit;
    }


    public void setItDescVersion(RichInputText itDescVersion) {
        this.itDescVersion = itDescVersion;
    }

    public RichInputText getItDescVersion() {
        return itDescVersion;
    }

    public void setMensaje(RichMessages mensaje) {
        this.mensaje = mensaje;
    }

    public RichMessages getMensaje() {
        return mensaje;
    }

    public void setItDescCrit(RichInputText itDescCrit) {
        this.itDescCrit = itDescCrit;
    }

    public RichInputText getItDescCrit() {
        return itDescCrit;
    }

    public void setMensajeIndicador(RichMessages mensajeIndicador) {
        this.mensajeIndicador = mensajeIndicador;
    }

    public RichMessages getMensajeIndicador() {
        return mensajeIndicador;
    }

    public void setItDescIndi(RichInputText itDescIndi) {
        this.itDescIndi = itDescIndi;
    }

    public RichInputText getItDescIndi() {
        return itDescIndi;
    }
}
