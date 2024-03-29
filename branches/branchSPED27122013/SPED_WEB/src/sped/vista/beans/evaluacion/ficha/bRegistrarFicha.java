package sped.vista.beans.evaluacion.ficha;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import jxl.write.WriteException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.ComponentSystemEvent;
import javax.faces.event.ValueChangeEvent;
import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.data.RichColumn;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.data.RichTreeTable;
import oracle.adf.view.rich.component.rich.input.RichInputNumberSlider;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.input.RichSelectOneChoice;
import oracle.adf.view.rich.component.rich.layout.RichPanelBox;
import oracle.adf.view.rich.component.rich.layout.RichPanelGridLayout;
import oracle.adf.view.rich.component.rich.nav.RichButton;
import oracle.adf.view.rich.component.rich.output.RichActiveOutputText;
import oracle.adf.view.rich.component.rich.output.RichMessages;
import oracle.adf.view.rich.event.DialogEvent;
import oracle.adf.view.rich.event.PopupCanceledEvent;
import oracle.adf.view.rich.render.ClientEvent;
import org.apache.myfaces.trinidad.event.SelectionEvent;
import org.apache.myfaces.trinidad.model.ChildPropertyTreeModel;
import org.apache.myfaces.trinidad.model.RowKeySet;
import org.apache.myfaces.trinidad.model.TreeModel;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import sped.negocio.LNSF.IL.LN_C_SFFichaCriterioLocal;
import sped.negocio.LNSF.IL.LN_C_SFResultadoLocal;
import sped.negocio.LNSF.IL.LN_T_SFFichaLocal;
import sped.negocio.LNSF.IL.LN_T_SFLoggerLocal;
import sped.negocio.LNSF.IL.LN_T_SFValorLocal;
import sped.negocio.LNSF.IR.LN_C_SFCriterioRemote;
import sped.negocio.LNSF.IR.LN_C_SFFichaRemote;
import sped.negocio.LNSF.IR.LN_C_SFIndicadorRemote;
import sped.negocio.LNSF.IR.LN_C_SFUtilsRemote;
import sped.negocio.LNSF.IR.LN_T_SFCriterioRemote;
import sped.negocio.LNSF.IR.LN_T_SFIndicadorRemote;
import sped.negocio.entidades.beans.BeanCriterio;
import sped.negocio.entidades.beans.BeanCriterioValor;
import sped.negocio.entidades.beans.BeanError;
import sped.negocio.entidades.beans.BeanFicha;
import sped.negocio.entidades.beans.BeanIndicador;
import sped.negocio.entidades.beans.BeanLeyenda;
import sped.negocio.entidades.beans.BeanUsuario;

import sped.vista.Utils.Utils;

/** Clase de Respaldo de Frm_Registrar_Ficha.jsff
 * @author dfloresgonz
 * @since 27.12.2013
 */
public class bRegistrarFicha {
    
    private ChildPropertyTreeModel permisosTree;
    private RichTable tbFichas;
    private RichButton btnEditFicha;
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
    private RichInputText itLey;
    private RichPopup popLey;
    private RichSelectOneChoice socTipFicha;
    private RichSelectOneChoice socTipFichaCurs;
    private RichInputNumberSlider ins1;
    private RichButton btnNewFicha;
    private RichMessages msjGen;
    private RichButton btnBase;
    private RichButton btnActDesact;
    private RichPopup popActDes;
    private RichPanelGridLayout pgl2;
    private RichInputText itVal;
    private RichPopup popVal;
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
    @EJB
    private LN_T_SFFichaLocal ln_T_SFFichaLocal;
    @EJB
    private LN_C_SFResultadoLocal ln_C_SFResultadoLocal;
    @EJB
    private LN_C_SFFichaCriterioLocal ln_C_SFFichaCriterioLocal;
    @EJB
    private LN_T_SFLoggerLocal ln_T_SFLoggerLocal;
    @EJB
    private LN_C_SFUtilsRemote ln_C_SFUtilsRemote;
    @EJB
    private LN_T_SFValorLocal ln_T_SFValorLocal;
    FacesContext ctx = FacesContext.getCurrentInstance();
    private BeanUsuario usuario = (BeanUsuario) Utils.getSession("USER");
    private static final String CLASE = "sped.vista.beans.evaluacion.ficha.bRegistrarFicha";
    private RichSelectOneChoice socVals;
    private RichActiveOutputText otError;

    public bRegistrarFicha() {
        
    }

    @PostConstruct
    public void methodInvokeOncedOnPageLoad(){
        if(sessionRegistrarFicha.getExec() == 0){
            sessionRegistrarFicha.setExec(1);
            sessionRegistrarFicha.setLstFichas(ln_C_SFFichaRemote.getLstFichasByAttr_LN());
            mostrarCuadre();
            sessionRegistrarFicha.setBtnRegistrarFicha("Nueva Ficha");
        }else{
            mostrarCuadre();
            //Utils.depurar("POST CONSTRUCT otras veces");
        }
    }
    
    private String mostrarCuadre() {
        sessionRegistrarFicha.setPermisosTree(null);
        BeanCriterio b = new BeanCriterio();
        b.setDescripcionCriterio("::: Ficha de Evaluacion :::");
        b.setMostrarBoton(false);
        b.setIsRaiz("1");
        b.setDisplayValor("display:none;");
        List<BeanCriterio> lstBeanCriterio = new ArrayList<BeanCriterio>();
        Collections.sort(sessionRegistrarFicha.getLstCriteriosMultiples(), new Comparator<BeanCriterio>() {
            public int compare(BeanCriterio s1,BeanCriterio s2) {
                return s1.getOrden().compareTo(s2.getOrden());
            }
        });
        b.setLstIndicadores(sessionRegistrarFicha.getLstCriteriosMultiples());
        lstBeanCriterio.add(b);
        permisosTree = new ChildPropertyTreeModel(lstBeanCriterio,"lstIndicadores");
        sessionRegistrarFicha.setPermisosTree(permisosTree);
        if(treeCriIndi != null){
            treeCriIndi.setValue(permisosTree);
            Utils.addTarget(treeCriIndi);
        }
        return null;
    }
    
    public void selectFicha(SelectionEvent se) {
        try{
            BeanFicha beanFicha = (BeanFicha) Utils.getRowTable(se);
            if("0".equals(beanFicha.getEstadoFicha())){
                sessionRegistrarFicha.setActDesEstilo("FondoVerdeLetraRoja");
            }else{
                sessionRegistrarFicha.setActDesEstilo("FondoRojoLetraVerde");
            }
            boolean fichaEvaluada = ln_C_SFResultadoLocal.fichaUsadaEnEvaluacion_LN(beanFicha.getNidFicha());
            BeanFicha clon = (BeanFicha) beanFicha.clone();
            sessionRegistrarFicha.setFichaEditarClon(clon);
            if(!fichaEvaluada){
                btnEditFicha.setDisabled(false);
            }else{
                btnEditFicha.setDisabled(true);
            }
            btnActDesact.setText(sessionRegistrarFicha.getActDesact());
            btnActDesact.setStyleClass(sessionRegistrarFicha.getActDesEstilo());
            btnBase.setDisabled(false);
            btnActDesact.setDisabled(false);
            Utils.addTargetMany(btnBase,btnActDesact,btnEditFicha);
        }catch(Exception e){
            e.printStackTrace();
            ln_T_SFLoggerLocal.registrarLogErroresSistema(usuario.getNidLog(), 
                                                          "BAC",CLASE, "selectFicha(SelectionEvent se)",
                                                          "Error al seleccionar la Ficha",Utils.getStack(e));
        }
    }
    
    public void editarFicha(ActionEvent ae) {
        BeanFicha clon = sessionRegistrarFicha.getFichaEditarClon();
        sessionRegistrarFicha.setVisiblePanelBoxPanelBoxFicha(true);
        panelBoxNewFicha.setVisible(true);
        pgl2.setVisible(true);
        sessionRegistrarFicha.setTipoFicha(clon.getTipoFicha());
        sessionRegistrarFicha.setTipFichaCurs(clon.getTipoFichaCurso());
        sessionRegistrarFicha.setVersionGenerada(clon.getDescripcionVersion());
        sessionRegistrarFicha.setNumValores(clon.getCantidadValores());
        sessionRegistrarFicha.setLstCriteriosMultiples(ln_C_SFFichaCriterioLocal.getListaCriteriosByFicha(clon.getNidFicha()));
        if(sessionRegistrarFicha.getLstTiposFichaCurso() != null){
            sessionRegistrarFicha.getLstTiposFichaCurso().clear();
        }
        sessionRegistrarFicha.setLstTiposFichaCurso(Utils.llenarComboString(ln_C_SFFichaRemote.getListaTiposFichaByTipoRol_LN(clon.getTipoFicha())));
        Utils.addTarget(socTipFichaCurs);
        mostrarCuadre();
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
        crearColumnas(clon.getCantidadValores(), children);
        sessionRegistrarFicha.setBtnRegistrarFicha("Actualizar Ficha");
        sessionRegistrarFicha.setStyleClass("FondoRojoLetraBlanca");
        btnNewFicha.setText("Actualizar Ficha");
        btnNewFicha.setStyleClass("FondoRojoLetraBlanca");
        sessionRegistrarFicha.setTipEvento(2);
        btnEditFicha.setDisabled(true);
        btnBase.setDisabled(true);
        Utils.addTargetMany(panelBoxNewFicha,btnNewFicha,btnEditFicha,btnBase,pgl2);
    }
    
    public void selectCriterios(SelectionEvent se) {
        BeanCriterio beanCriterio = new BeanCriterio();
        beanCriterio = (BeanCriterio) Utils.getRowTable(se);
        if(beanCriterio != null){
            if(beanCriterio.isSelected()){
                int ordenBorrar = beanCriterio.getOrden();
                int size = sessionRegistrarFicha.getLstCriterios().size();
                if(ordenBorrar < size){
                    if(ordenBorrar > 1){
                        for(BeanCriterio itm : sessionRegistrarFicha.getLstCriterios()){
                            if(itm.getOrden() != null){
                                if(itm.getOrden() > 1 && ordenBorrar > 2){
                                      if(itm.getOrden() > 2){
                                          itm.setOrden(itm.getOrden() - 1);
                                      } 
                                }else if(itm.getOrden() > 1 && ordenBorrar == 2){
                                    itm.setOrden(itm.getOrden() - 1);
                                }
                            }
                        }
                    }else{
                        for(BeanCriterio itm : sessionRegistrarFicha.getLstCriterios()){
                            if(itm.getOrden() != null){
                                itm.setOrden(itm.getOrden() - 1);   
                            }
                        }
                    }
                }
                size = sessionRegistrarFicha.getLstCriteriosMultiples().size();
                if(ordenBorrar < size){
                    if(ordenBorrar > 1){
                        for(BeanCriterio itm : sessionRegistrarFicha.getLstCriteriosMultiples()){
                            if(itm.getOrden() > 1 && ordenBorrar > 2){
                                  if(itm.getOrden() > 2){
                                      itm.setOrden(itm.getOrden() - 1);
                                  } 
                            }else if(itm.getOrden() > 1 && ordenBorrar == 2){
                                itm.setOrden(itm.getOrden() - 1);
                            }
                        }
                    }else{
                        for(BeanCriterio itm : sessionRegistrarFicha.getLstCriteriosMultiples()){
                            itm.setOrden(itm.getOrden() - 1);
                        }
                    }
                }
               sessionRegistrarFicha.getLstCriteriosMultiples().remove(beanCriterio);
               beanCriterio.setSelected(false);
            }else{
                if(!this.contieneCriterio(sessionRegistrarFicha.getLstCriteriosMultiples(),beanCriterio.getNidCriterio())){
                    beanCriterio.setSelected(true);
                    beanCriterio.setMostrarBoton(true);
                    beanCriterio.setMostrarUpDown(true);
                    int orden = 1;
                    if (sessionRegistrarFicha.getLstCriteriosMultiples() != null) {
                        if (sessionRegistrarFicha.getLstCriteriosMultiples().size() >= 1) {
                            orden = sessionRegistrarFicha.getLstCriteriosMultiples().size() + 1;
                        }
                    }
                    beanCriterio.setOrden(orden);
                    beanCriterio.setNoMostrarDown(true);
                    beanCriterio.setDisplayValor("display:block;");
                    if(orden > 1){
                        BeanCriterio critAnterior = sessionRegistrarFicha.getLstCriteriosMultiples().get(orden - 2);
                        critAnterior.setNoMostrarDown(false);
                    }
                    sessionRegistrarFicha.getLstCriteriosMultiples().add(sessionRegistrarFicha.getLstCriteriosMultiples().size(),beanCriterio);
                }
            }
            buscarCriterios();
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
                        int ordenBorrar = bInd.getOrden();
                        int size = sessionRegistrarFicha.getLstIndisSelected().size();
                        if(ordenBorrar < size){
                            if(ordenBorrar > 1){
                                for(BeanIndicador itm : sessionRegistrarFicha.getLstIndisSelected()){
                                    if(itm.getOrden() > 1){
                                        itm.setOrden(itm.getOrden() - 1);    
                                    }
                                }
                            }else{
                                for(BeanIndicador itm : sessionRegistrarFicha.getLstIndisSelected()){
                                    itm.setOrden(itm.getOrden() - 1);
                                }
                            }
                        }
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
                if(!contiene(sessionRegistrarFicha.getLstIndisSelected(),beanIndicador.getNidIndicador(),true)){
                    sessionRegistrarFicha.getLstIndisSelected().add(beanIndicador);   
                }
                //sessionRegistrarFicha.getLstIndisSelected().remove(beanIndicador);
                beanIndicador.setSelected(false);
            }else{
                beanIndicador.setSelected(true);
                if(!contiene(sessionRegistrarFicha.getLstIndisSelected(),beanIndicador.getNidIndicador(),false)){
                    int orden = 1;
                    if (sessionRegistrarFicha.getLstIndisSelected() != null) {
                        if (sessionRegistrarFicha.getLstIndisSelected().size() >= 1) {
                            orden = sessionRegistrarFicha.getLstIndisSelected().size() + 1;
                        }
                    }
                    beanIndicador.setOrden(orden);
                    beanIndicador.setNoMostrarDown(true);
                    if(orden > 1){
                        BeanIndicador indAnterior = sessionRegistrarFicha.getLstIndisSelected().get(orden - 2);
                        indAnterior.setNoMostrarDown(false);
                    }
                    sessionRegistrarFicha.getLstIndisSelected().add(sessionRegistrarFicha.getLstIndisSelected().size(),beanIndicador);   
                }
            }
            BeanCriterio critSelected = this.getCriterio(sessionRegistrarFicha.getCritSelected().getNidCriterio());
            if(critSelected.getLstIndicadores() != null){
                if(!contieneCriterio(critSelected.getLstIndicadores(),beanIndicador.getNidIndicador())){
                    BeanCriterio crit = new BeanCriterio();
                    crit.setNidCriterio(beanIndicador.getNidIndicador());
                    crit.setOrden(beanIndicador.getOrden());
                    crit.setMostrarBoton(true);
                    crit.setMostrarUpDown(true);
                    crit.setNoMostrarDown(true);
                    crit.setEsIndicador(1);
                    crit.setIsRaiz("0");
                    crit.setDescripcionCriterio(beanIndicador.getDescripcionIndicador());
                    crit.setDisplay("display:block;");
                    crit.setDisplayValor("display:none;");
                    crit.setSelected(beanIndicador.isSelected());
                    if(critSelected.getLstIndicadores().size() > 0){
                        critSelected.getLstIndicadores().get(critSelected.getLstIndicadores().size()-1).setNoMostrarDown(false);   
                    }
                    critSelected.getLstIndicadores().add(crit);   
                } 
           //     critSelected.getLstIndicadores().removeAll(critSelected.getLstIndicadores());
              //  critSelected.setLstIndicadores(this.lstIndisToCrit(sessionRegistrarFicha.getLstIndisSelected()));
                if(!contiene(sessionRegistrarFicha.getLstIndicadoresByCriterio(),beanIndicador.getNidIndicador(),false)){
                    sessionRegistrarFicha.getLstIndicadoresByCriterio().add(beanIndicador);   
                }
               // sessionRegistrarFicha.setLstIndicadoresByCriterio(sessionRegistrarFicha.getLstIndisSelected());
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
                crit.setOrden(indi.getOrden());
                crit.setMostrarBoton(true);
                crit.setMostrarUpDown(true);
                crit.setNoMostrarDown(indi.isNoMostrarDown());
                crit.setEsIndicador(1);
                crit.setIsRaiz("0");
                crit.setDescripcionCriterio(indi.getDescripcionIndicador());
                crit.setDisplay("display:block;");
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
                ind.setOrden(cri.getOrden());
                ind.setNoMostrarDown(cri.isNoMostrarDown());
                ind.setDescripcionIndicador(cri.getDescripcionCriterio());
                ind.setSelected(cri.isSelected());
                lstIns.add(ind);
            }
        }
        return lstIns;
    }
    
    public void cancelarPopUpCriterios(PopupCanceledEvent popupCanceledEvent) {
        /*if(sessionRegistrarFicha.getLstCriteriosMultiples() != null){
            Utils.sysout("lista: "+sessionRegistrarFicha.getLstCriteriosMultiples().size());
        }*/
        if(treeCriIndi != null){ 
            treeCriIndi.setValue(sessionRegistrarFicha.getPermisosTree());
            Utils.addTarget(treeCriIndi);
        }
    }
    
    public void dialogOkCriterios(DialogEvent de) {
        /*if (de.getOutcome() == DialogEvent.Outcome.ok){
            for(BeanCriterio crit : sessionRegistrarFicha.getLstCriteriosMultiples()){
                Utils.sysout("crit:"+crit.getDescripcionCriterio());   
            }
        }*/
    }
    
    public void refrescarTablaFichas(ActionEvent actionEvent) {
        refrescarTablaFichasAux();
    }
    
    public void refrescarTablaFichasAux(){
        sessionRegistrarFicha.setLstFichas(ln_C_SFFichaRemote.getLstFichasByAttr_LN());
        tbFichas.setValue(sessionRegistrarFicha.getLstFichas());
        btnEditFicha.setDisabled(true);
        Utils.addTargetMany(tbFichas,btnEditFicha);
        Utils.unselectFilas(tbFichas);
    }

    public void nuevaFicha(ActionEvent actionEvent) {
        if(sessionRegistrarFicha.getTipEvento() == 0){
            //DEFAULT NI BIEN CARGA LA PAGINA Y SE APLASTA EL BOTON ESTO SE EJECUTA
            activar();
        }else if(sessionRegistrarFicha.getTipEvento() == 1 || sessionRegistrarFicha.getTipEvento() == 2){
            //CUANDO SE APLASTO ESTE BOTON X 1ERA VEZ SE SETEO LA VARIABLE A 1 (REGISTRAR/ACTUALIZAR FICHA)
            if(isOkRegistrarFicha()){
                try {
                    if(sessionRegistrarFicha.getTipEvento() == 2){
                        ln_T_SFFichaLocal.eliminarFichaCriterio(sessionRegistrarFicha.getFichaEditarClon().getNidFicha());
                    }
                    BeanFicha beanFicha = ln_T_SFFichaLocal.registrarFicha(sessionRegistrarFicha.getTipoFicha(),
                                                                           sessionRegistrarFicha.getTipFichaCurs(),
                                                                           sessionRegistrarFicha.getVersionGenerada(),
                                                                           sessionRegistrarFicha.getNumValores(),
                                                                           sessionRegistrarFicha.getLstCriteriosMultiples(),
                                                                           sessionRegistrarFicha.getTipEvento(),
                                                                           (sessionRegistrarFicha.getTipEvento() == 2 ? sessionRegistrarFicha.getFichaEditarClon().getNidFicha() : 0));
                    if (beanFicha.getBeanError() != null) {
                        BeanError error = beanFicha.getBeanError();
                        int severidad = 0;
                        if ("000".equals(error.getCidError())) {
                            severidad = 3;
                            Utils.sysout("Grabo la Ficha");
                        } else {
                            severidad = 1;
                        }
                        msjGen.setText(error.getTituloError());
                        Utils.addTarget(msjGen);
                        Utils.mostrarMensaje(ctx, error.getDescripcionError(), error.getTituloError(), severidad);
                    } else {
                        msjGen.setText("Error Inesperado");
                        Utils.addTarget(msjGen);
                        Utils.mostrarMensaje(ctx, "Error Inesperado", "Error", 1);
                    }
                } catch (Exception e) {
                    msjGen.setText("Error Inesperado");
                    Utils.addTarget(msjGen);
                    Utils.mostrarMensaje(ctx, "Error Inesperado", "Error", 1);
                    e.printStackTrace();
                } finally {
                    resetearDespuesGrabarEditar();
                }
            }
        }
    }
    
    public void activar(){
        sessionRegistrarFicha.setTipEvento(1);
        sessionRegistrarFicha.setVisiblePanelBoxPanelBoxFicha(true);
        panelBoxNewFicha.setVisible(true);
        treeCriIndi.setVisible(true);
        pgl2.setVisible(true);
        btnEditFicha.setDisabled(true);
        btnBase.setDisabled(true);
        btnActDesact.setDisabled(true);
        sessionRegistrarFicha.setActDesEstilo(null);
        btnActDesact.setStyleClass(null);
        if(socTipFicha != null){
            socTipFicha.setRequired(true);
            socTipFichaCurs.setRequired(true);
            ins1.setRequired(true);
        }
        sessionRegistrarFicha.setBtnRegistrarFicha("Registrar Ficha");
        sessionRegistrarFicha.setStyleClass("FondoRojoLetraBlanca");
        btnNewFicha.setText("Registrar Ficha");
        btnNewFicha.setStyleClass("FondoRojoLetraBlanca");
        Utils.addTargetMany(btnNewFicha,btnEditFicha,treeCriIndi,pgl2,panelBoxNewFicha,socTipFicha,socTipFichaCurs,ins1,btnBase,btnActDesact);
    }
    
    public void resetar(ActionEvent actionEvent) {
        resetearDespuesGrabarEditar();
    }
    
    public void usarComoBase(ActionEvent actionEvent) {
        BeanFicha clon = sessionRegistrarFicha.getFichaEditarClon();
        sessionRegistrarFicha.setVisiblePanelBoxPanelBoxFicha(true);
        panelBoxNewFicha.setVisible(true);
        sessionRegistrarFicha.setTipoFicha(clon.getTipoFicha());
        sessionRegistrarFicha.setTipFichaCurs(clon.getTipoFichaCurso());
        if(sessionRegistrarFicha.getLstTiposFichaCurso() != null){
            sessionRegistrarFicha.getLstTiposFichaCurso().clear();
        }
        sessionRegistrarFicha.setLstTiposFichaCurso(Utils.llenarComboString(ln_C_SFFichaRemote.getListaTiposFichaByTipoRol_LN(clon.getTipoFicha())));
        Utils.addTarget(socTipFichaCurs);
        
        getNuevaVersion(clon.getTipoFicha(),clon.getTipoFichaCurso());
        sessionRegistrarFicha.setNumValores(clon.getCantidadValores());
        sessionRegistrarFicha.setLstCriteriosMultiples(ln_C_SFFichaCriterioLocal.getListaCriteriosByFicha(clon.getNidFicha()));
        mostrarCuadre();
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
        crearColumnas(clon.getCantidadValores(), children);
        btnEditFicha.setDisabled(true);//panelBoxNewFicha
        Utils.addTargetMany(treeCriIndi,btnNewFicha,btnEditFicha,btnBase);
        activar();
    }
    
    public void resetearDespuesGrabarEditar(){
        sessionRegistrarFicha.setTipEvento(0);
        sessionRegistrarFicha.setVisiblePanelBoxPanelBoxFicha(false);
        panelBoxNewFicha.setVisible(false);
        pgl2.setVisible(false);
        treeCriIndi.setVisible(false);
        btnEditFicha.setDisabled(true);
        sessionRegistrarFicha.setBtnRegistrarFicha("Nueva Ficha");
        sessionRegistrarFicha.setStyleClass(null);
        sessionRegistrarFicha.setTipoFicha(null);
        sessionRegistrarFicha.setTipFichaCurs(null);
        sessionRegistrarFicha.setVersionGenerada(null);
        sessionRegistrarFicha.setNumValores(0);
        socTipFicha.resetValue();
        socTipFichaCurs.resetValue();
        itDescVersion.resetValue();
        ins1.resetValue();
        btnNewFicha.setText("Nueva Ficha");
        btnNewFicha.setStyleClass(null);
        btnBase.setDisabled(true);
        btnActDesact.setDisabled(true);
        btnActDesact.setStyleClass(null);
        sessionRegistrarFicha.setActDesEstilo(null);
        if(socTipFicha != null){
            socTipFicha.setRequired(false);
            socTipFichaCurs.setRequired(false);
            ins1.setRequired(false);
        }
        try {
            List<UIComponent> children = null;
            children = this.treeCriIndi.getChildren();
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
        refrescarTablaFichasAux();
        removerLista(sessionRegistrarFicha.getLstCriteriosMultiples());
        removerLista(sessionRegistrarFicha.getLstCriterios());
        removerLista(sessionRegistrarFicha.getLstIndicadores());
        removerLista(sessionRegistrarFicha.getLstIndicadoresByCriterio());
        removerLista(sessionRegistrarFicha.getLstIndisSelected());
        removerLista(sessionRegistrarFicha.getLstLeyendas());
        sessionRegistrarFicha.setCritSelected(null);
        sessionRegistrarFicha.setTt(null);
        sessionRegistrarFicha.setDescCriterioSeleccionado(null);
        sessionRegistrarFicha.setDescCriterio(null);
        sessionRegistrarFicha.setDescIndicador(null);
        sessionRegistrarFicha.setLeyenda(null);
        sessionRegistrarFicha.setValorDesc(null);
        Utils.addTargetMany(btnNewFicha,panelBoxNewFicha,pgl2,btnBase,btnActDesact,btnEditFicha,socTipFicha,socTipFichaCurs,itDescVersion,treeCriIndi);
    }
    
    public void removerLista(List<?> lista){
        if(lista != null){
            lista.removeAll(lista);
        }
    }
    
    private boolean isOkRegistrarFicha(){
        boolean isOk = true;
        int severidad = 4;
        String detalle = "Faltan Campos";
        if(sessionRegistrarFicha.getVersionGenerada() == null){
            isOk = false;
            Utils.mostrarMensaje(ctx,"Genere el numero de version de la ficha",detalle, severidad);
        }else{
            if(sessionRegistrarFicha.getVersionGenerada().equalsIgnoreCase("")){
                isOk = false;
                Utils.mostrarMensaje(ctx,"Genere el numero de version de la ficha",detalle, severidad);
            }
        }
        if(sessionRegistrarFicha.getNumValores() < 3){
            isOk = false;
            Utils.mostrarMensaje(ctx,"El numero de Valores tiene que ser mayor a 2",detalle,severidad);
        }
        if(this.arbolIsOk() != null){
            isOk = false;
            Utils.mostrarMensaje(ctx,this.arbolIsOk(),detalle,severidad);
        }
        if(isOk == false){
            msjGen.setText(detalle);
            Utils.addTarget(msjGen);
        }
        //faltan mas
        return isOk;
    }
    
    private String arbolIsOk(){
        if(sessionRegistrarFicha.getLstCriteriosMultiples() != null){
            if(sessionRegistrarFicha.getLstCriteriosMultiples().size() == 0){
                return "Agregue los Criterios";
            }/*else if(sessionRegistrarFicha.getLstCriteriosMultiples().size() == 1){
                return "Agregue al menos 2 Criterios";
            }*/ else{
                Iterator it = sessionRegistrarFicha.getLstCriteriosMultiples().iterator();
                while(it.hasNext()){
                    BeanCriterio crit = (BeanCriterio) it.next();
                    if(crit.getLstIndicadores() != null){
                        if(crit.getLstIndicadores().size() == 0){
                            return "Agregue los indicadores";
                        } else if(crit.getLstIndicadores().size() == 1){
                            return "Agregue al menos 2 indicadores";
                        }else{
                            //chequear leyendas
                            Iterator tit = crit.getLstIndicadores().iterator();
                            while(tit.hasNext()){
                                BeanCriterio indi = (BeanCriterio) tit.next();
                                if(indi.getLstLeyenda() != null){
                                    if(indi.getLstLeyenda().size() != sessionRegistrarFicha.getNumValores()){
                                        return "Agregue las leyendas";
                                    }else if(indi.getLstLeyenda().size() == sessionRegistrarFicha.getNumValores()){
                                        Iterator it3 = indi.getLstLeyenda().iterator();
                                        while(it3.hasNext()){
                                            BeanLeyenda ley = (BeanLeyenda) it3.next();
                                            if(ley.getDescripcionLeyenda() == null){
                                                return "Agregue las leyendas";
                                            }else{
                                                if("".equalsIgnoreCase(ley.getDescripcionLeyenda()) ){
                                                    return "Agregue las leyendas";
                                                }
                                            }
                                        }
                                    }
                                }else{
                                    return "Agregue las leyendas";
                                }
                            }
                        }
                    }else{
                        return "Agregue los indicadores";
                    }
                    if(crit.getLstCriteriosValor() == null){
                        return "Registre los valores del Criterio.";
                    }else{
                        if(crit.getLstCriteriosValor().size() == 0){
                            return "Registre los valores del Criterio.";
                        }
                        if(crit.getLstCriteriosValor().size() != sessionRegistrarFicha.getNumValores()){
                            return "Registre los valores del Criterio.";
                        }
                        if(crit.getLstCriteriosValor().size() == sessionRegistrarFicha.getNumValores()){
                            Iterator it3 = crit.getLstCriteriosValor().iterator();
                            while(it3.hasNext()){
                                BeanCriterioValor crval = (BeanCriterioValor) it3.next();
                                if(crval.getValor() == -1.0){
                                    return "Agregue los valores.";
                                }
                            }
                        }
                    }
                }
            }
        }else{
            return "Agregue los Criterios";
        }
        return null;
    }
    
    public void abrirPopCriterios(ActionEvent actionEvent) {
        buscarCriterios();
        Utils.showPopUpMIDDLE(popCrits);
    }
    
    public HashMap contiene(List<BeanCriterio> lstCritMult,Integer nidCrit){
        Iterator it = lstCritMult.iterator();
        HashMap mapa = new HashMap();
        while(it.hasNext()){
            BeanCriterio bCrit = (BeanCriterio) it.next();
            if(bCrit.getNidCriterio().compareTo(nidCrit) == 0){
                mapa.put("BOOL",true);
                mapa.put("ORDEN",bCrit.getOrden() == null ? 0 : bCrit.getOrden());
                return mapa;
            }
        }
        mapa.put("ORDEN",0);
        mapa.put("BOOL",false);
        return mapa;
    }
    
    private boolean contieneCriterio(List<BeanCriterio> lstCritMult,Integer nidCrit){
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
        if(sessionRegistrarFicha.getCritSelected() != null){
            sessionRegistrarFicha.setLstIndisSelected(this.lstCritToIndi(sessionRegistrarFicha.getCritSelected().getLstIndicadores()));   
        }
        Utils.showPopUpMIDDLE(popIndis);
    }
    
    public String buscarCriterios(){
        sessionRegistrarFicha.setLstCriterios(ln_C_SFCriterioRemote.getCriteriosByAttr_LN(sessionRegistrarFicha.getNidCriterio(),
                                                                                          sessionRegistrarFicha.getDescCriterio()));
        if(sessionRegistrarFicha.getLstCriteriosMultiples() != null && sessionRegistrarFicha.getLstCriterios() != null){
            Iterator it = sessionRegistrarFicha.getLstCriterios().iterator();
            while(it.hasNext()){
                BeanCriterio bCrit = (BeanCriterio) it.next();
                HashMap mapa = contiene(sessionRegistrarFicha.getLstCriteriosMultiples(),bCrit.getNidCriterio());
                boolean boo = (Boolean) mapa.get("BOOL");
                if(boo){
                    int ord = Integer.parseInt(mapa.get("ORDEN").toString());
                    if(ord != 0){
                        bCrit.setOrden(ord);
                    }
                    bCrit.setSelected(true);
                }
            }
        }
        Collections.sort(sessionRegistrarFicha.getLstCriterios(), new Comparator<BeanCriterio>() {
            public int compare(BeanCriterio s1,BeanCriterio s2) {
                if(s1.getOrden() != null && s2.getOrden() != null){
                    return s1.getOrden().compareTo(s2.getOrden());
                }
                return 0;
            }
        });
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
        String message = (String) ce.getParameters().get("fvalue");
        sessionRegistrarFicha.setDescCriterio(message);
        buscarCriterios();
    }
    
    public void busquedaConTeclaIndicadores(ClientEvent ce){
        String message = (String) ce.getParameters().get("fvalue");
        sessionRegistrarFicha.setDescIndicador(message);
        buscarIndicadores();
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
    
    public void alwp(ActionEvent actionEvent) {
      String param = (String)actionEvent.getComponent().getAttributes().get("consultar");
      if(sessionRegistrarFicha.getTt() != null && param.equalsIgnoreCase("consultar")){
          setear();
          Utils.showPopUpMIDDLE(popIndByCrit);
      }
    }
    
    public void setear(){
        RichTreeTable tt = sessionRegistrarFicha.getTt();
        TreeModel model = (TreeModel) tt.getValue();
        RowKeySet rks = tt.getSelectedRowKeys();
        Iterator keys = rks.iterator();
        List<BeanCriterio> lstBeanCriterio = (List<BeanCriterio>) model.getWrappedData();
        if (keys.hasNext()) {
            List key = (List)keys.next();
            int llave = Integer.parseInt(key.get(1).toString());
            BeanCriterio criterio = (BeanCriterio) lstBeanCriterio.get(0).getLstIndicadores().get(llave).clone();
            if(key.size() == 2){
                sessionRegistrarFicha.setCritSelected(criterio);
                sessionRegistrarFicha.setDescCriterioSeleccionado(criterio.getDescripcionCriterio());
                sessionRegistrarFicha.setLstIndicadoresByCriterio(this.lstCritToIndi(criterio.getLstIndicadores()));
            }
            if(key.size() == 3){
                sessionRegistrarFicha.setCritSelected(criterio);
                sessionRegistrarFicha.setLstIndicadoresByCriterio(this.lstCritToIndi(criterio.getLstIndicadores()));
            }
        } 
    }
    
    public void mover(ActionEvent actionEvent){
        String param = (String)actionEvent.getComponent().getAttributes().get("mover");
        int orden = Integer.parseInt(actionEvent.getComponent().getAttributes().get("orden").toString());
        int esIndicador = Integer.parseInt(actionEvent.getComponent().getAttributes().get("esIndicador").toString());
        setear();
        int mov = Integer.parseInt(param);//0 subir; 1 bajar
        int ord = orden - 1;
        if(esIndicador == 0){
            moverAux(ord, mov, orden,sessionRegistrarFicha.getLstCriteriosMultiples());   
        }else{
            BeanCriterio critSelected = this.getCriterio(sessionRegistrarFicha.getCritSelected().getNidCriterio());
            moverAux(ord, mov, orden,critSelected.getLstIndicadores());   
        }
        Utils.unselectFilas(treeCriIndi);
    }
    
    private void moverAux(int ord,int mov,int orden,List<BeanCriterio> lista){
        if((ord > 0 && mov == 0) || (orden < lista.size() && mov == 1) ){
            actualizarOrden(lista,orden,mov);   
            if(mov == 0){
                Collections.swap(lista,ord,ord - 1);
            }else{
                Collections.swap(lista,ord,ord + 1);
            }
        }else{
            Utils.sysout("no cumple");
        }
    }
    
    public void actualizarOrden(List<BeanCriterio> lista, int orden, int subirBajar/*0,1*/){
        for(BeanCriterio crit : lista){
            if(crit.getOrden() == orden){
                BeanCriterio otro = lista.get(subirBajar == 0 ? orden - 2 : orden);
                otro.setOrden(orden);
                if(subirBajar == 0){//Subir
                    if(lista.size() > 2){
                        otro.setNoMostrarDown(false);//false muestra la flecha para abajo. true NO       
                        if(orden == lista.size()){
                            otro.setNoMostrarDown(true);//false muestra la flecha para abajo. true NO 
                            crit.setNoMostrarDown(false);
                        }
                    }
                }else{
                    int next = orden + 1;
                    if(next == lista.size()){
                        otro.setNoMostrarDown(false);//false muestra la flecha para abajo. true NO 
                        crit.setNoMostrarDown(true);
                    }
                }
                crit.setOrden(subirBajar == 0 ? orden - 1 : orden + 1);
                return;
            }
        }
    }
    
    public void selectTree(SelectionEvent se) {
        RichTreeTable tt = (RichTreeTable) se.getSource();
        sessionRegistrarFicha.setTt(tt);
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
                    int ordenBorrar = beanCriterio.getOrden();
                    int size = critSelected.getLstIndicadores().size();
                    if(ordenBorrar > 1){
                        if(ordenBorrar == size){
                            BeanCriterio criAnterior = critSelected.getLstIndicadores().get(ordenBorrar - 2);
                            criAnterior.setNoMostrarDown(true);
                        }
                    }
                    if(ordenBorrar < size){
                        if(ordenBorrar > 1){
                            for(BeanCriterio itm : critSelected.getLstIndicadores()){
                                if(itm.getOrden() > 1 && ordenBorrar > 2){
                                      if(itm.getOrden() > 2){
                                          itm.setOrden(itm.getOrden() - 1);
                                      } 
                                }else if(itm.getOrden() > 1 && ordenBorrar == 2){
                                    itm.setOrden(itm.getOrden() - 1);
                                }
                            }
                        }else{
                            for(BeanCriterio itm : critSelected.getLstIndicadores()){
                                itm.setOrden(itm.getOrden() - 1);
                            }
                        }
                    }
                    iti.remove();
                    Utils.unselectFilas(treeCriIndi);
                    return;
                }
            }
        }
    }
    
    public void changeTipoFicha(ValueChangeEvent vce) {
        sessionRegistrarFicha.setTipFichaCurs(null);
        sessionRegistrarFicha.setVersionGenerada(null);
        itDescVersion.setValue(null);
        Utils.addTarget(itDescVersion);
        if(vce.getNewValue() != null){
            if(sessionRegistrarFicha.getTipFichaCurs() != null){
                getNuevaVersion(vce.getNewValue().toString(),sessionRegistrarFicha.getTipFichaCurs());
            }
            sessionRegistrarFicha.setLstTiposFichaCurso(Utils.llenarComboString(ln_C_SFFichaRemote.getListaTiposFichaByTipoRol_LN(vce.getNewValue().toString())));
            Utils.addTarget(socTipFichaCurs);
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
    }
    
    public void registrarCriterio(ActionEvent ae) {
        if(sessionRegistrarFicha.getDescCriterio() != null){
            if(!sessionRegistrarFicha.getDescCriterio().equalsIgnoreCase("")){
                BeanCriterio bCrit = ln_T_SFCriterioRemote.registrarCriterio(sessionRegistrarFicha.getDescCriterio());
                if(bCrit.getBeanError() != null){
                    BeanError error = bCrit.getBeanError();
                    int severidad = 0;
                    if("000".equals(error.getCidError())){
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
            }else{
                mensaje.setText("Ingrese el Criterio");
                Utils.addTarget(mensaje);
                Utils.mostrarMensaje(ctx,"Ingrese el Criterio","Error",1);
            }
        }else{
            mensaje.setText("Ingrese el Criterio");
            Utils.addTarget(mensaje);
            Utils.mostrarMensaje(ctx,"Ingrese el Criterio","Error",1);
        }
    }
       
    public void registrarIndicador(ActionEvent ae) {
        if(sessionRegistrarFicha.getDescIndicador() != null){
            if(!sessionRegistrarFicha.getDescIndicador().equalsIgnoreCase("")){
                BeanIndicador bIndi = ln_T_SFIndicadorRemote.registrarIndicador(sessionRegistrarFicha.getDescIndicador());
                if(bIndi.getBeanError() != null){
                    BeanError error = bIndi.getBeanError();
                    int severidad = 0;
                    if("000".equals(error.getCidError())){
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
            }else{
                mensajeIndicador.setText("Ingrese el Indicador");
                Utils.addTarget(mensajeIndicador);
                Utils.mostrarMensaje(ctx,"Ingrese el Indicador","Error",1);
            }
        }else{
            mensajeIndicador.setText("Ingrese el Indicador");
            Utils.addTarget(mensajeIndicador);
            Utils.mostrarMensaje(ctx,"Ingrese el Indicador","Error",1);
        }
    }
    
    public void changeSliderValor(ValueChangeEvent vce) {
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
        if(sessionRegistrarFicha.getLstLeyendas() != null){
            Iterator itLey = sessionRegistrarFicha.getLstLeyendas().iterator();
            int valAux = 1;
            while(itLey.hasNext()){
                int comp = valAux - val;
                BeanLeyenda leyenda = (BeanLeyenda) itLey.next();
                if(comp > 0 ){
                    itLey.remove();
                }
                valAux++;
            }
        }
        if(sessionRegistrarFicha.getLstCriteriosMultiples() != null){
            if(sessionRegistrarFicha.getLstCriteriosMultiples().size() > 0){
                Iterator it = sessionRegistrarFicha.getLstCriteriosMultiples().iterator();
                while(it.hasNext()){
                    BeanCriterio crit = (BeanCriterio) it.next();
                    if(crit.getLstIndicadores() != null){
                        if(crit.getLstIndicadores().size() > 0){
                            Iterator tit = crit.getLstIndicadores().iterator();
                            while(tit.hasNext()){
                                BeanCriterio indi = (BeanCriterio) tit.next();
                                if(indi.getLstLeyenda() != null){
                                    Iterator itLey = indi.getLstLeyenda().iterator();
                                    int valAux = 1;
                                    while(itLey.hasNext()){
                                        int comp = valAux - val;
                                        BeanLeyenda leyenda = (BeanLeyenda) itLey.next();
                                        if(comp > 0 ){
                                            itLey.remove();
                                        }
                                        valAux++;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        crearColumnas(val,children);
    }
    
    private void crearColumnas(int val,List<UIComponent> children){
        for(int c = 0; c < val; c++) {
            BeanLeyenda leyenda = new BeanLeyenda();
            String valor = "Valor "+(c + 1);
            leyenda.setHeader(valor);
            if(!this.contieneLeyenda(valor)){
                sessionRegistrarFicha.getLstLeyendas().add(leyenda);
            }
            RichColumn col = new RichColumn();
            col.setHeaderText(valor);
            col.setWidth("40");
            ValueExpression ve = Utils.createValueExpression("#{row.display}");//../recursos/img/usuarios/
            ValueExpression veIcono = Utils.createValueExpression("#{row.lstLeyenda["+c+"].descripcionLeyenda == null || row.lstLeyenda["+c+"].descripcionLeyenda == '' ? '../recursos/img/usuarios/closed_eye.png' : '../recursos/img/usuarios/ver.png' }");
            ValueExpression veShort = Utils.createValueExpression("#{row.isRaiz}");
            RichButton buton = new RichButton();//BOTON PARA VER LAS LEYENDAS DE LOS INDICADORES
            String name = RichButton.INLINE_STYLE_KEY.getName();
            String nameIcono = RichButton.ICON_KEY.getName();
            String nameShort = RichButton.SHORT_DESC_KEY.getName();
            buton.setValueExpression(name, ve);
            buton.setValueExpression(nameIcono,veIcono);
            buton.setValueExpression(nameShort,veShort);
            buton.setActionListener(Utils.createActionListenerMethodBinding("#{bRegistrarFicha.getVerLeyenda}"));
            buton.getAttributes().put("leyenda",valor);
            col.getChildren().add(buton);
            
            RichButton btnVerVals = new RichButton();//BOTON PARA VER EL VALOR QUE PUEDE TOMAR CADA CRITERIO
            ValueExpression veBtnVerVals = Utils.createValueExpression("#{row.displayValor}");
            String stiloBtnVerVals = RichButton.INLINE_STYLE_KEY.getName();
            btnVerVals.setValueExpression(stiloBtnVerVals,veBtnVerVals);
            btnVerVals.setText("Valor");
            btnVerVals.setActionListener(Utils.createActionListenerMethodBinding("#{bRegistrarFicha.getVerValor}"));
            btnVerVals.getAttributes().put("valor",(c + 1)+"");
            col.getChildren().add(btnVerVals);
            
            children.add(col);
        }
        Utils.addTarget(treeCriIndi);
    }

    public void actualizarLeyendasByValor(String valor,String descLeyenda){
        ChildPropertyTreeModel permisosTree = sessionRegistrarFicha.getPermisosTree();
        List<BeanCriterio> lstBeanCriterio = (List<BeanCriterio>) permisosTree.getWrappedData();
        lstBeanCriterio.get(0).setDisplay("display:block;");
        if(lstBeanCriterio.get(0) != null){
            lstBeanCriterio.get(0).getLstLeyenda().removeAll(lstBeanCriterio.get(0).getLstLeyenda());
        }
        lstBeanCriterio.get(0).setLstLeyenda(sessionRegistrarFicha.getLstLeyendasRaiz());
        BeanLeyenda ley1 = this.getLeyendaByValor(valor,lstBeanCriterio.get(0));
        if(ley1 == null){
            ley1 = new BeanLeyenda();
            ley1.setDescripcionLeyenda(descLeyenda);
            ley1.setHeader(valor);
            int indx = Integer.parseInt(valor.substring(valor.indexOf(" ")+1,valor.length()) ) - 1;
            if(lstBeanCriterio.get(0).getLstLeyenda().size() < indx){
                lstBeanCriterio.get(0).getLstLeyenda().addAll(Collections.<BeanLeyenda>nCopies((indx - lstBeanCriterio.get(0).getLstLeyenda().size()), null));
                sessionRegistrarFicha.getLstLeyendasRaiz().addAll(Collections.<BeanLeyenda>nCopies((indx - lstBeanCriterio.get(0).getLstLeyenda().size()), null));
            }else if(lstBeanCriterio.get(0).getLstLeyenda().size() > indx){
                lstBeanCriterio.get(0).getLstLeyenda().remove(indx);
                sessionRegistrarFicha.getLstLeyendasRaiz().remove(indx);
            }
            lstBeanCriterio.get(0).getLstLeyenda().add(indx,ley1);
         //   sessionRegistrarFicha.getLstLeyendasRaiz().add(indx,ley1);
        }else{
            ley1.setDescripcionLeyenda(descLeyenda);
            ley1.setHeader(valor);
        }
        if(lstBeanCriterio.get(0).getLstIndicadores() != null){
            if(lstBeanCriterio.get(0).getLstIndicadores().size() > 0){
                for(int i = 0; i < lstBeanCriterio.get(0).getLstIndicadores().size(); i++){
                    BeanCriterio crit = lstBeanCriterio.get(0).getLstIndicadores().get(i);
                    if(crit.getLstIndicadores() != null){
                        if(crit.getLstIndicadores().size() > 0){
                            for(int j = 0; j < crit.getLstIndicadores().size(); j++){
                                BeanCriterio indi = crit.getLstIndicadores().get(j);
                                if(indi.getLstLeyenda() != null){
                                    BeanLeyenda ley = this.getLeyendaByValor(valor,indi);
                                    if(ley == null){
                                        BeanLeyenda le = new BeanLeyenda();
                                        le.setDescripcionLeyenda(descLeyenda);
                                        le.setHeader(valor);
                                        int indx = Integer.parseInt(valor.substring(valor.indexOf(" ")+1,valor.length()) ) - 1;
                                        if(indi.getLstLeyenda().size() < indx){
                                            indi.getLstLeyenda().addAll(Collections.<BeanLeyenda>nCopies((indx - indi.getLstLeyenda().size()), null));
                                        }else if(indi.getLstLeyenda().size() > indx){
                                            indi.getLstLeyenda().remove(indx);
                                        }
                                        indi.getLstLeyenda().add(indx,le);
                                    }else{
                                        ley.setDescripcionLeyenda(descLeyenda);
                                        ley.setHeader(valor);
                                    }
                                }
                            }
                        }  
                    }
                }
            } 
        }
        permisosTree = new ChildPropertyTreeModel(lstBeanCriterio,"lstIndicadores");
        if(treeCriIndi != null){
            treeCriIndi.setValue(permisosTree);
            sessionRegistrarFicha.setPermisosTree(permisosTree);
            Utils.addTarget(treeCriIndi);
        }
    }
    
    public void getVerValor(ActionEvent ae){
        String param = (String)ae.getComponent().getAttributes().get("valor");       
        sessionRegistrarFicha.setParamValor(param);
        if(itVal != null){
            itVal.resetValue();
            Utils.addTarget(itVal);
        }
        RichTreeTable tt = sessionRegistrarFicha.getTt();
        TreeModel model = (TreeModel) tt.getValue();
        RowKeySet rks = tt.getSelectedRowKeys();
        Iterator keys = rks.iterator();
        List<BeanCriterio> lstBeanCriterio = (List<BeanCriterio>) model.getWrappedData();
        if (keys.hasNext()){
            List key = (List)keys.next();
            if(key.size() == 2){
                int llaveCrit = Integer.parseInt(key.get(1).toString());
                BeanCriterio criterio = lstBeanCriterio.get(0).getLstIndicadores().get(llaveCrit);
                sessionRegistrarFicha.setCritSelectedValor(criterio);
                int indxValor = Integer.parseInt(sessionRegistrarFicha.getParamValor());
                BeanCriterioValor val = this.getCriterioFromLista(indxValor,criterio);
                if(val != null){
                    sessionRegistrarFicha.setValorSelected(val.getValor());
                    sessionRegistrarFicha.setValorDoubleString(val.getIdValoracion()+"");
                    if(itVal != null){
                        //socVals.setValue(sessionRegistrarFicha.getIdValorSelected()+"");
                        socVals.setValue(val.getIdValoracion()+"");
                        itVal.resetValue();
                        itVal.setValue(val.getValor());
                        Utils.addTargetMany(itVal,socVals);
                    }
                }else{
                    sessionRegistrarFicha.setValorSelected(-1.0);
                    sessionRegistrarFicha.setValorDoubleString("-1.0");
                    if(itVal != null){
                        socVals.setValue("-1.0");
                        itVal.resetValue();
                        itVal.setValue(null);
                        Utils.addTargetMany(itVal,socVals);
                    }
                }
                sessionRegistrarFicha.setLstValores(Utils.llenarComboDouble(ln_C_SFUtilsRemote.getListaValores_LN()));
                if(otError != null){
                    otError.setValue("");
                    Utils.addTarget(otError);
                }
                Utils.showPopUpMIDDLE(popVal);
            }else{
                sessionRegistrarFicha.setValorSelected(-1.0);
                sessionRegistrarFicha.setValorDoubleString("-1.0");
                if(itVal != null){
                    socVals.setValue("-1.0");
                    itVal.resetValue();
                    itVal.setValue(null);
                    Utils.addTargetMany(itVal,socVals);
                }
            }
        }
    }
    
    public void getVerLeyenda(ActionEvent ae){
        String param = (String)ae.getComponent().getAttributes().get("leyenda");
        if(itLey != null){
            itLey.resetValue();
            Utils.addTarget(itLey);
        }
        RichTreeTable tt = sessionRegistrarFicha.getTt();
        TreeModel model = (TreeModel) tt.getValue();
        RowKeySet rks = tt.getSelectedRowKeys();
        Iterator keys = rks.iterator();
        List<BeanCriterio> lstBeanCriterio = (List<BeanCriterio>) model.getWrappedData();
        if (keys.hasNext()) {
            List key = (List)keys.next();
            boolean isGeneral = sessionRegistrarFicha.isLeyendasGenerales() == true && key.size() == 1;
            if((isGeneral)){
                RichButton btn = (RichButton) ae.getComponent();
                sessionRegistrarFicha.setShortDesc(btn.getShortDesc());
                BeanCriterio criterio = lstBeanCriterio.get(0);
                sessionRegistrarFicha.setIndiHeaderSelectLeyenda(criterio);
                sessionRegistrarFicha.setDescCriterioSeleccionado("-- Leyendas en general --");
                BeanLeyenda ley = this.getLeyendaByValor(param,criterio);
                if(ley != null){
                    sessionRegistrarFicha.setLeyenda(ley.getDescripcionLeyenda());
                    if(itLey != null){
                        itLey.resetValue();
                        itLey.setValue(ley.getDescripcionLeyenda());
                        Utils.addTarget(itLey);
                    }
                }else{
                    sessionRegistrarFicha.setLeyenda(null);
                    if(itLey != null){
                        itLey.resetValue();
                        itLey.setValue(null);
                        Utils.addTarget(itLey);
                    }
                }
                sessionRegistrarFicha.setDescIndicadorSelected(null);
                sessionRegistrarFicha.setValorDesc(param);
                Utils.showPopUpMIDDLE(popLey);
            }else{
                if(key.size() == 3){
                    sessionRegistrarFicha.setShortDesc("0");
                    int llave = Integer.parseInt(key.get(2).toString());
                    int llaveCrit = Integer.parseInt(key.get(1).toString());
                    BeanCriterio criterio = lstBeanCriterio.get(0).getLstIndicadores().get(llaveCrit);
                    BeanCriterio indiSelected = criterio.getLstIndicadores().get(llave);
                    sessionRegistrarFicha.setDescCriterioSeleccionado(criterio.getDescripcionCriterio());
                    BeanLeyenda ley = this.getLeyendaByValor(param, indiSelected);
                    if(ley != null){
                        sessionRegistrarFicha.setLeyenda(ley.getDescripcionLeyenda());
                        if(itLey != null){
                            itLey.resetValue();
                            itLey.setValue(ley.getDescripcionLeyenda());
                            Utils.addTarget(itLey);
                        }
                    }else{
                        sessionRegistrarFicha.setLeyenda(null);
                        if(itLey != null){
                            itLey.resetValue();
                            itLey.setValue(null);
                            Utils.addTarget(itLey);
                        }
                    }
                    sessionRegistrarFicha.setIndiSelectLeyenda(indiSelected);
                    sessionRegistrarFicha.setDescIndicadorSelected(indiSelected.getDescripcionCriterio());
                    sessionRegistrarFicha.setValorDesc(param);
                    Utils.showPopUpMIDDLE(popLey);
                }else{
                    sessionRegistrarFicha.setValorDesc(null);
                    sessionRegistrarFicha.setLeyenda(null);
                    if(itLey != null){
                        itLey.resetValue();
                        itLey.setValue(null);
                        Utils.addTarget(itLey);
                    }
                }
            }
        }
    }
    
    public void asignarValor(ActionEvent ae){
        if(sessionRegistrarFicha.getIdValorSelected() > 0 && sessionRegistrarFicha.getValorSelected() > -1.0){
            BeanCriterio criterio = sessionRegistrarFicha.getCritSelectedValor();
            int indxValor = Integer.parseInt(sessionRegistrarFicha.getParamValor());
            if(criterio.getLstCriteriosValor() != null){
                if(this.checkValorRepetido(criterio.getLstCriteriosValor(),sessionRegistrarFicha.getValorSelected())){
                    return;
                }
                if(this.checkValorOrderAsc(criterio.getLstCriteriosValor(),sessionRegistrarFicha.getValorSelected(),(indxValor - 1))){
                    return;
                }
                BeanCriterioValor bcval = this.getCriterioFromLista(indxValor,criterio);
                if(bcval != null){
                    bcval.setValor(sessionRegistrarFicha.getValorSelected());
                    bcval.setDescripcionValor("Valor "+sessionRegistrarFicha.getValorSelected());
                    bcval.setIdValoracion(sessionRegistrarFicha.getIdValorSelected());
                    bcval.setValor(sessionRegistrarFicha.getValorSelected());
                    bcval.setOrden(indxValor);
                }else{
                    bcval = new BeanCriterioValor();
                    bcval.setDescripcionValor("Valor "+sessionRegistrarFicha.getValorSelected());
                    bcval.setIdValoracion(sessionRegistrarFicha.getIdValorSelected());
                    bcval.setValor(sessionRegistrarFicha.getValorSelected());
                    bcval.setOrden(indxValor);
                    //bcval.setNidCriterioValor(nidCriterioValor);Este se sacara de consulta de la BD, luego de insertarlo
                    indxValor = indxValor - 1;
                    if(criterio.getLstCriteriosValor().size() < indxValor){
                        criterio.getLstCriteriosValor().addAll(Collections.<BeanCriterioValor>nCopies((indxValor - criterio.getLstCriteriosValor().size()), null));
                    }else if(criterio.getLstCriteriosValor().size() > indxValor){
                        criterio.getLstCriteriosValor().remove(indxValor);
                    }
                    criterio.getLstCriteriosValor().add(indxValor,bcval);
                }
                if(itVal != null){
                    sessionRegistrarFicha.setValorDoubleString("-1.0");
                    sessionRegistrarFicha.setValorPopupTreeNew(-1.0);
                    itVal.resetValue();
                    itVal.setValue(null);
                    Utils.addTarget(itVal);
                }
            }else{
                BeanCriterioValor bcval = new BeanCriterioValor();
                bcval.setDescripcionValor("Valor "+sessionRegistrarFicha.getValorSelected());
                bcval.setIdValoracion(sessionRegistrarFicha.getIdValorSelected());
                bcval.setValor(sessionRegistrarFicha.getValorSelected());
                criterio.getLstCriteriosValor().add(indxValor,bcval);
                bcval.setOrden(indxValor);
                if(itVal != null){
                    itVal.resetValue();
                    Utils.addTarget(itVal);
                }
            }
            popVal.hide();
            Utils.addTarget(treeCriIndi);
        }else{
            otError.setValue("Hubo un error al asignar el Valor");
            Utils.addTarget(otError);
        }
    }
    
    public void asignarLeyenda(ActionEvent ae) {
        if(sessionRegistrarFicha.getLeyenda() != null){
            String shortDesc = sessionRegistrarFicha.getShortDesc();
            String leyendaDesc = sessionRegistrarFicha.getLeyenda();
            boolean isHeader = true;
            if(shortDesc != null){
                if("1".equalsIgnoreCase(shortDesc)){
                    BeanCriterio critRaiz = sessionRegistrarFicha.getIndiHeaderSelectLeyenda();
                    if(critRaiz.getLstLeyenda() != null){
                        BeanLeyenda ley = this.getLeyendaByValor(sessionRegistrarFicha.getValorDesc(),critRaiz);
                        if(ley != null){
                            ley.setDescripcionLeyenda(sessionRegistrarFicha.getLeyenda());
                        }else{
                            ley = new BeanLeyenda();
                            ley.setDescripcionLeyenda(sessionRegistrarFicha.getLeyenda());
                            ley.setHeader(sessionRegistrarFicha.getValorDesc());
                            int indx = Integer.parseInt(sessionRegistrarFicha.getValorDesc().substring(sessionRegistrarFicha.getValorDesc().indexOf(" ")+1,
                                                                                                       sessionRegistrarFicha.getValorDesc().length()) ) - 1;
                            if(critRaiz.getLstLeyenda().size() < indx){
                                critRaiz.getLstLeyenda().addAll(Collections.<BeanLeyenda>nCopies((indx - critRaiz.getLstLeyenda().size()), null));
                            }else if(critRaiz.getLstLeyenda().size() > indx){
                                critRaiz.getLstLeyenda().remove(indx);
                            }
                            critRaiz.getLstLeyenda().add(indx,ley);
                        }
                        if(itLey != null){
                            sessionRegistrarFicha.setLeyenda(null);
                            itLey.resetValue();
                            itLey.setValue(null);
                            Utils.addTarget(itLey);
                        }
                    }else{
                        BeanLeyenda leye = new BeanLeyenda();
                        leye.setDescripcionLeyenda(sessionRegistrarFicha.getLeyenda());
                        leye.setHeader(sessionRegistrarFicha.getValorDesc());
                        int indx = Integer.parseInt(sessionRegistrarFicha.getValorDesc().substring(sessionRegistrarFicha.getValorDesc().indexOf(" ")+1,
                                                                                                   sessionRegistrarFicha.getValorDesc().length())       );
                        critRaiz.getLstLeyenda().add(indx,leye);
                        if(itLey != null){
                            itLey.resetValue();
                            Utils.addTarget(itLey);
                        }
                    }
                    popLey.hide();
                    Utils.addTarget(treeCriIndi);
                    actualizarLeyendasByValor(sessionRegistrarFicha.getValorDesc(),leyendaDesc);
                }else{
                    isHeader = false;
                }
            }else{
                isHeader = false;
            }
            if(isHeader == false){
                BeanCriterio indiSelected = sessionRegistrarFicha.getIndiSelectLeyenda();
                if(indiSelected.getLstLeyenda() != null){
                    BeanLeyenda ley = this.getLeyendaByValor(sessionRegistrarFicha.getValorDesc(), indiSelected);
                    if(ley != null){
                        ley.setDescripcionLeyenda(sessionRegistrarFicha.getLeyenda());
                    }else{
                        ley = new BeanLeyenda();
                        ley.setDescripcionLeyenda(sessionRegistrarFicha.getLeyenda());
                        ley.setHeader(sessionRegistrarFicha.getValorDesc());
                        int indx = Integer.parseInt(sessionRegistrarFicha.getValorDesc().substring(sessionRegistrarFicha.getValorDesc().indexOf(" ")+1,
                                                                                                   sessionRegistrarFicha.getValorDesc().length()) ) - 1;
                        if(indiSelected.getLstLeyenda().size() < indx){
                            indiSelected.getLstLeyenda().addAll(Collections.<BeanLeyenda>nCopies((indx - indiSelected.getLstLeyenda().size()), null));
                        }else if(indiSelected.getLstLeyenda().size() > indx){
                            indiSelected.getLstLeyenda().remove(indx);
                        }
                        indiSelected.getLstLeyenda().add(indx,ley);
                    }
                    if(itLey != null){
                        sessionRegistrarFicha.setLeyenda(null);
                        itLey.resetValue();
                        itLey.setValue(null);
                        Utils.addTarget(itLey);
                    }
                }else{
                    BeanLeyenda leye = new BeanLeyenda();
                    leye.setDescripcionLeyenda(sessionRegistrarFicha.getLeyenda());
                    leye.setHeader(sessionRegistrarFicha.getValorDesc());
                    int indx = Integer.parseInt(sessionRegistrarFicha.getValorDesc().substring(sessionRegistrarFicha.getValorDesc().indexOf(" ")+1,
                                                                                               sessionRegistrarFicha.getValorDesc().length())       );
                    indiSelected.getLstLeyenda().add(indx,leye);
                    if(itLey != null){
                        itLey.resetValue();
                        Utils.addTarget(itLey);
                    }
                }
            }
            popLey.hide();
            Utils.addTarget(treeCriIndi);
        }
    }
    
    public BeanLeyenda getLeyendaByValor(String val,BeanCriterio indiSelected){
        try {
            if(indiSelected.getLstLeyenda() != null){
                Iterator it = indiSelected.getLstLeyenda().iterator();
                int ix = 0;
                while (it.hasNext()) {
                    ix++;
                    BeanLeyenda ley = (BeanLeyenda) it.next();
                    if (ley != null) {
                        if (val.equalsIgnoreCase("Valor "+ix)) {//ley.getHeader()
                            return ley;
                        }
                    } else {
                        return null;
                    }
                }
            }else{
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }
    
    public BeanCriterioValor getCriterioFromLista(int iParam,BeanCriterio beanCriterio){
        try {
            if(beanCriterio.getLstCriteriosValor() != null){
                Iterator it = beanCriterio.getLstCriteriosValor().iterator();
                int ix = 0;
                //int iParam = 0;
                while (it.hasNext()) {
                    ix++;
                    BeanCriterioValor critVal = (BeanCriterioValor) it.next();
                    //iParam = Integer.parseInt(param);
                    if (critVal != null) {
                        if (iParam == ix) {//ley.getHeader()
                            return critVal;
                        }
                    } else {
                        return null;
                    }
                }
            }else{
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }
    
    public boolean contieneLeyenda(String valor){
        if(sessionRegistrarFicha.getLstLeyendas() != null){
            Iterator it = sessionRegistrarFicha.getLstLeyendas().iterator();
            int ix = 0;
            while(it.hasNext()){
                ix++;
                it.next();
                if(valor.equalsIgnoreCase("Valor "+ix)){//ley.getHeader() 
                    return true;
                }
            }
        }
        return false;
    }
    
    public void activarDesactivar(ActionEvent ae) {
        try {
            if (sessionRegistrarFicha.getFichaEditarClon() != null) {
                if ("0".equals(sessionRegistrarFicha.getFichaEditarClon().getEstadoFicha())) { //VA A ACTIVAR
                    BeanFicha beanFicha = ln_C_SFFichaRemote.checkSiSePuedeActivar(sessionRegistrarFicha.getFichaEditarClon().getTipoFicha(),
                                                                                   sessionRegistrarFicha.getFichaEditarClon().getTipoFichaCurso());
                    if (beanFicha.getBeanError() != null) {
                        BeanError error = beanFicha.getBeanError();
                        int severidad = 0;
                        if ("000".equals(error.getCidError())) {
                            BeanFicha beanFicha2 = ln_T_SFFichaLocal.desactivarActivarFicha(sessionRegistrarFicha.getFichaEditarClon().getNidFicha(),"1");
                            if (beanFicha.getBeanError() != null) {
                                BeanError error2 = beanFicha2.getBeanError();
                                int severidad2 = 0;
                                if ("000".equals(error2.getCidError())) {
                                    severidad2 = 3;
                                    Utils.sysout("Activo la Ficha");
                                } else {
                                    severidad2 = 1;
                                }
                                msjGen.setText(error2.getTituloError());
                                Utils.addTarget(msjGen);
                                Utils.mostrarMensaje(ctx, error2.getDescripcionError(), error2.getTituloError(),severidad2);
                            } else {
                                msjGen.setText("Error Inesperado");
                                Utils.addTarget(msjGen);
                                Utils.mostrarMensaje(ctx, "Error Inesperado", "Error", 1);
                            }
                        } else if ("SPED-00004".equals(error.getCidError())) {
                            sessionRegistrarFicha.setAdvertenciaSPED0004(error.getDescripcionError());
                            Utils.showPopUpMIDDLE(popActDes);
                        } else {
                            severidad = 1;
                        }
                        if (!"SPED-00004".equals(error.getCidError())) {
                            msjGen.setText(error.getTituloError());
                            Utils.addTarget(msjGen);
                            Utils.mostrarMensaje(ctx, error.getDescripcionError(), error.getTituloError(), severidad);
                        }
                        refrescarTablaFichasAux();
                    } else {
                        msjGen.setText("Error Inesperado");
                        Utils.addTarget(msjGen);
                        Utils.mostrarMensaje(ctx, "Error Inesperado", "Error", 1);
                    }
                } else { //VA A DESACTIVAR
                    BeanFicha beanFicha = ln_T_SFFichaLocal.desactivarActivarFicha(sessionRegistrarFicha.getFichaEditarClon().getNidFicha(),"0");
                    if (beanFicha.getBeanError() != null) {
                        BeanError error = beanFicha.getBeanError();
                        int severidad = 0;
                        if ("000".equals(error.getCidError())) {
                            severidad = 3;
                            Utils.sysout("Desactivo la Ficha");
                        } else {
                            severidad = 1;
                        }
                        msjGen.setText(error.getTituloError());
                        Utils.addTarget(msjGen);
                        Utils.mostrarMensaje(ctx, error.getDescripcionError(), error.getTituloError(), severidad);
                        refrescarTablaFichasAux();
                    } else {
                        msjGen.setText("Error Inesperado");
                        Utils.addTarget(msjGen);
                        Utils.mostrarMensaje(ctx, "Error Inesperado", "Error", 1);
                    }
                }
                btnBase.setDisabled(true);
                btnActDesact.setDisabled(true);
                btnActDesact.setStyleClass(null);
                sessionRegistrarFicha.setActDesEstilo(null);
                Utils.addTargetMany(btnBase, btnActDesact);
            }
        } catch (Exception e) {
            e.printStackTrace();
            ln_T_SFLoggerLocal.registrarLogErroresSistema(usuario.getNidLog(),
                                                          "BAC",CLASE,"void activarDesactivar(ActionEvent ae)",
                                                          "Error al activar/desactivar la Ficha",Utils.getStack(e));
        }
    }
    
    public void dialogOKReactivarFicha(DialogEvent dialogEvent) {
        try {
            BeanFicha beanFicha =  ln_T_SFFichaLocal.desactivarActivarFicha(sessionRegistrarFicha.getFichaEditarClon().getNidFicha(), "1");
            ln_T_SFFichaLocal.reactivarFichaYDesactivarElResto(sessionRegistrarFicha.getFichaEditarClon().getTipoFicha(),
                                                               sessionRegistrarFicha.getFichaEditarClon().getTipoFichaCurso(),
                                                               sessionRegistrarFicha.getFichaEditarClon().getNidFicha());
            if (beanFicha.getBeanError() != null) {
                BeanError error = beanFicha.getBeanError();
                int severidad = 0;
                if ("000".equals(error.getCidError())) {
                    severidad = 3;
                    Utils.sysout("Reactivo la Ficha y desactivo el resto");
                } else {
                    severidad = 1;
                }
                msjGen.setText(error.getTituloError());
                Utils.addTarget(msjGen);
                Utils.mostrarMensaje(ctx, error.getDescripcionError(), error.getTituloError(), severidad);
                refrescarTablaFichasAux();
            } else {
                msjGen.setText("Error Inesperado");
                Utils.addTarget(msjGen);
                Utils.mostrarMensaje(ctx, "Error Inesperado", "Error", 1);
            }
            btnBase.setDisabled(true);
            btnActDesact.setDisabled(true);
            btnActDesact.setStyleClass(null);
            sessionRegistrarFicha.setActDesEstilo(null);
            Utils.addTargetMany(btnBase, btnActDesact);
        } catch (Exception e) {
            e.printStackTrace();
            ln_T_SFLoggerLocal.registrarLogErroresSistema(usuario.getNidLog(),
                                                          "BAC",CLASE,"dialogOKReactivarFicha(DialogEvent dialogEvent)",
                                                          "Error al reactivar la Ficha",Utils.getStack(e));
        }
    }
    
    public void changeLeyendasGenerales(ValueChangeEvent vce) {
        boolean isGeneral = (Boolean)vce.getNewValue();
        sessionRegistrarFicha.setLeyendasGenerales(isGeneral);
        if(isGeneral){//Checked
            ChildPropertyTreeModel permisosTree = sessionRegistrarFicha.getPermisosTree();
            try {
                List<BeanCriterio> lstBeanCriterio = (List<BeanCriterio>) permisosTree.getWrappedData();
                lstBeanCriterio.get(0).setDisplay("display:block;");
                if(treeCriIndi != null){
                    treeCriIndi.setValue(sessionRegistrarFicha.getPermisosTree());
                    Utils.addTarget(treeCriIndi);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{//Unchecked
            List<BeanCriterio> lstBeanCriterio = (List<BeanCriterio>) permisosTree.getWrappedData();
            lstBeanCriterio.get(0).setDisplay("display:none;");
                if(treeCriIndi != null){
                    treeCriIndi.setValue(sessionRegistrarFicha.getPermisosTree());
                    Utils.addTarget(treeCriIndi);
                }
            }
    }

    public void changeChoiceValor(ValueChangeEvent vce) {
        if(vce.getNewValue() != null){
            Integer val = Integer.parseInt((String) vce.getNewValue());
            sessionRegistrarFicha.setIdValorSelected(val);
            sessionRegistrarFicha.setValorDoubleString(val+"");
            sessionRegistrarFicha.setValorSelected(Utils.getChoiceLabelDouble(vce));
        }
    }

    public void registrarNuevoValor(ActionEvent ae) {
        try {
            String error = ln_T_SFValorLocal.registrarNuevoValor_LN(sessionRegistrarFicha.getValorPopupTreeNew());
            if("000".equals(error)){
                sessionRegistrarFicha.setLstValores(Utils.llenarComboDouble(ln_C_SFUtilsRemote.getListaValores_LN()));
                otError.setValue("Se registro el Valor");
                Utils.addTargetMany(socVals,otError);
            }else{
                otError.setValue("Hubo un error al registrar el Valor");
                Utils.addTarget(otError);
            }
        } catch (Exception e) {
            e.printStackTrace();
            otError.setValue("Hubo un error al registrar el Valor");
            Utils.addTarget(otError);
        }
    }
    
    public boolean checkValorRepetido(List<BeanCriterioValor> critsVals,double valor){
        for(BeanCriterioValor cv : critsVals){
            if(cv.getValor() == valor){
                otError.setValue("El Valor es repetido. Use otro.");
                Utils.addTarget(otError);
                return true;
            }
        }
        return false;
    }
    
    public boolean checkValorOrderAsc(List<BeanCriterioValor> critsVals,double valor, int idx){
        Collections.sort(critsVals, new Comparator<BeanCriterioValor>() {
            public int compare(BeanCriterioValor s1,BeanCriterioValor s2) {
                return (s1.getOrden().compareTo(s2.getOrden()));
            }
        });
        if(idx == 0){//El primer valor
            if( (idx + 1) < critsVals.size()){
                if(valor > critsVals.get((idx + 1)).getValor() ){
                    otError.setValue("El Valor es mayor al siguiente :"+critsVals.get((idx + 1)).getValor());
                    Utils.addTarget(otError);
                    return true;
                }
            }
        }
        if(idx > 0){//El 2do para adelante valor
            if(idx < critsVals.size() ){//El valor a cambiar ya esta en la lista
                if(valor < critsVals.get((idx - 1)).getValor() ){
                    otError.setValue("El Valor es menor al anterior :"+critsVals.get((idx - 1)).getValor());
                    Utils.addTarget(otError);
                    return true;
                }
                if( (idx + 1) < critsVals.size()){
                    if(valor > critsVals.get((idx + 1)).getValor() ){
                        otError.setValue("El Valor es mayor al siguiente :"+critsVals.get((idx + 1)).getValor());
                        Utils.addTarget(otError);
                        return true;
                    }
                }
            }
            if(critsVals.size() == idx){//El valor es nuevo, se agregara al final de la lista
                if(valor < critsVals.get((idx - 1)).getValor() ){
                    otError.setValue("El Valor es menor al anterior :"+critsVals.get((idx - 1)).getValor());
                    Utils.addTarget(otError);
                    return true;
                }
            }
        }
        return false;
    }
    
    public void setUsuario(BeanUsuario usuario) {
        this.usuario = usuario;
    }

    public BeanUsuario getUsuario() {
        return usuario;
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

    public void setItLey(RichInputText itLey) {
        this.itLey = itLey;
    }

    public RichInputText getItLey() {
        return itLey;
    }

    public void setPopLey(RichPopup popLey) {
        this.popLey = popLey;
    }

    public RichPopup getPopLey() {
        return popLey;
    }

    public void setSocTipFicha(RichSelectOneChoice socTipFicha) {
        this.socTipFicha = socTipFicha;
    }

    public RichSelectOneChoice getSocTipFicha() {
        return socTipFicha;
    }

    public void setSocTipFichaCurs(RichSelectOneChoice socTipFichaCurs) {
        this.socTipFichaCurs = socTipFichaCurs;
    }

    public RichSelectOneChoice getSocTipFichaCurs() {
        return socTipFichaCurs;
    }

    public void setIns1(RichInputNumberSlider ins1) {
        this.ins1 = ins1;
    }

    public RichInputNumberSlider getIns1() {
        return ins1;
    }

    public void setBtnNewFicha(RichButton btnNewFicha) {
        this.btnNewFicha = btnNewFicha;
    }

    public RichButton getBtnNewFicha() {
        return btnNewFicha;
    }

    public void setMsjGen(RichMessages msjGen) {
        this.msjGen = msjGen;
    }

    public RichMessages getMsjGen() {
        return msjGen;
    }

    public void setBtnBase(RichButton btnBase) {
        this.btnBase = btnBase;
    }

    public RichButton getBtnBase() {
        return btnBase;
    }

    public void setBtnActDesact(RichButton btnActDesact) {
        this.btnActDesact = btnActDesact;
    }

    public RichButton getBtnActDesact() {
        return btnActDesact;
    }

    public void setPopActDes(RichPopup popActDes) {
        this.popActDes = popActDes;
    }

    public RichPopup getPopActDes() {
        return popActDes;
    }

    public void setPgl2(RichPanelGridLayout pgl2) {
        this.pgl2 = pgl2;
    }

    public RichPanelGridLayout getPgl2() {
        return pgl2;
    }

    public void setItVal(RichInputText itVal) {
        this.itVal = itVal;
    }

    public RichInputText getItVal() {
        return itVal;
    }

    public void setPopVal(RichPopup popVal) {
        this.popVal = popVal;
    }

    public RichPopup getPopVal() {
        return popVal;
    }

    public void setSocVals(RichSelectOneChoice socVals) {
        this.socVals = socVals;
    }

    public RichSelectOneChoice getSocVals() {
        return socVals;
    }

    public void setOtError(RichActiveOutputText otError) {
        this.otError = otError;
    }

    public RichActiveOutputText getOtError() {
        return otError;
    }
}
