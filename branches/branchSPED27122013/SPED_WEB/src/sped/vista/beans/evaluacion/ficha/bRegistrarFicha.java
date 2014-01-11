package sped.vista.beans.evaluacion.ficha;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;

import javax.ejb.EJB;

import javax.faces.event.ActionEvent;

import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.ComponentSystemEvent;

import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.data.RichTable;

import oracle.adf.view.rich.component.rich.data.RichTreeTable;
import oracle.adf.view.rich.component.rich.layout.RichPanelBox;
import oracle.adf.view.rich.component.rich.nav.RichButton;

import oracle.adf.view.rich.event.DialogEvent;

import oracle.adf.view.rich.event.PopupCanceledEvent;
import oracle.adf.view.rich.render.ClientEvent;
import oracle.adf.view.rich.component.rich.data.RichTreeTable;


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
import sped.negocio.entidades.beans.BeanCriterio;
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
        b.setDescripcionCriterio("Ficha");
        List<BeanCriterio> lstBeanCriterio = new ArrayList<BeanCriterio>();
        /*BeanCriterio b1 = new BeanCriterio();
        b1.setDescripcionCriterio("criterio 1");
        BeanCriterio i1 = new BeanCriterio();
        i1.setDescripcionCriterio("indicador 1");
        BeanCriterio i2 = new BeanCriterio();
        i2.setDescripcionCriterio("indicador 2");
        BeanCriterio i3 = new BeanCriterio();
        i3.setDescripcionCriterio("indicador 3");
        List<BeanCriterio> lstBI = new ArrayList<BeanCriterio>();
        lstBI.add(i1);lstBI.add(i2);lstBI.add(i3);
        b1.setLstIndicadores(lstBI);
        BeanCriterio b6 = new BeanCriterio();
        b6.setDescripcionCriterio("criterio 6");
        //
        BeanCriterio b2 = new BeanCriterio();
        b2.setDescripcionCriterio("criterio 2");
        BeanCriterio i4 = new BeanCriterio();
        i4.setDescripcionCriterio("indicador 4");
        BeanCriterio i5 = new BeanCriterio();
        i5.setDescripcionCriterio("indicador 5");
        BeanCriterio i6 = new BeanCriterio();
        i6.setDescripcionCriterio("indicador 6");
        List<BeanCriterio> lstBI2 = new ArrayList<BeanCriterio>();
        lstBI2.add(i4);lstBI2.add(i5);lstBI2.add(i6);
        b2.setLstIndicadores(lstBI2);
        */
       /* List<BeanCriterio> _lstBeanCriterio = new ArrayList<BeanCriterio>();
        _lstBeanCriterio.add(b1);
        _lstBeanCriterio.add(b2);
        _lstBeanCriterio.add(b6);*/
        List<BeanCriterio> _lstBeanCriterio = new ArrayList<BeanCriterio>(sessionRegistrarFicha.getLstCriteriosMultiples());
        b.setLstIndicadores(_lstBeanCriterio);
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
            BeanFicha beanFicha = (BeanFicha) Utils.getRowTable(se);
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
        }
    }
    
    public void selectIndis(SelectionEvent se) {
        BeanIndicador beanIndicador = (BeanIndicador) Utils.getRowTable(se);
        if(beanIndicador != null){
            if(beanIndicador.isSelected()){
                Iterator iti = sessionRegistrarFicha.getLstIndisSelected().iterator();
                while(iti.hasNext()){
                    BeanIndicador bInd = (BeanIndicador) iti.next();
                }
                sessionRegistrarFicha.getLstIndisSelected().remove(beanIndicador);
                beanIndicador.setSelected(false);
            }else{
                beanIndicador.setSelected(true);
                sessionRegistrarFicha.getLstIndisSelected().add(beanIndicador);
            }
            BeanCriterio critSelected = sessionRegistrarFicha.getCritSelected();
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
                                                                                                     sessionRegistrarFicha.getNidIndicador()));
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
            List key = (List)keys.next();Utils.sysout("key; "+key+" size: "+key.size());
            if(key.size() == 2){
                int llave = Integer.parseInt(key.get(1).toString());
                List<BeanCriterio> lstBeanCriterio = (List<BeanCriterio>) model.getWrappedData();
                BeanCriterio criterio = lstBeanCriterio.get(0).getLstIndicadores().get(llave);
                sessionRegistrarFicha.setCritSelected(criterio);
                Utils.showPopUpMIDDLE(popIndByCrit);
                sessionRegistrarFicha.setDescCriterioSeleccionado(criterio.getDescripcionCriterio());
                sessionRegistrarFicha.setLstIndicadoresByCriterio(this.lstCritToIndi(criterio.getLstIndicadores()));
                Utils.sysout("crit"+criterio.getDescripcionCriterio());
            }
        }
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
}
