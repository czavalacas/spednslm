package sped.vista.beans.evaluacion.ficha;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import javax.faces.event.ActionEvent;

import oracle.adf.view.rich.component.rich.data.RichTreeTable;

import org.apache.myfaces.trinidad.model.ChildPropertyTreeModel;
import sped.negocio.entidades.beans.BeanCriterio;
import sped.negocio.entidades.beans.BeanFicha;
import sped.negocio.entidades.beans.BeanIndicador;
import sped.negocio.entidades.beans.BeanLeyenda;

import sped.vista.Utils.Utils;

/** Clase de Sesion del Bean bRegistrarFicha
 * @author dfloresgonz
 * @since 27.12.2013
 */
public class bSessionRegistrarFicha implements Serializable {
    @SuppressWarnings("compatibility:-5173839665784232314")
    private static final long serialVersionUID = 1L;

    private List<BeanFicha> lstFichas;
    private int exec = 0;
    private String tipoFicha;
    private String tipFichaCurs;
    private boolean visiblePanelBoxPanelBoxFicha = false;
    private List<BeanCriterio> lstCriterios;
    private int nidCriterio;//busqueda popCrits
    private String descCriterio;//busqueda popCrits
    private List<BeanIndicador> lstIndicadores;
    private int nidIndicador;//busqueda popIndis
    private String descIndicador;//busqueda popIndis
    private ChildPropertyTreeModel permisosTree;
    //private List<BeanCriterio> lstCriteriosFin;
    //private HashSet<BeanCriterio> lstCriteriosMultiples = new HashSet<BeanCriterio>();
    private List<BeanCriterio> lstCriteriosMultiples = new ArrayList<BeanCriterio>();
    //private HashSet<BeanTRItem> hashItemsFinal = new HashSet<BeanTRItem>();
    private List<BeanIndicador> lstIndicadoresByCriterio = new ArrayList<BeanIndicador>();
    private String descCriterioSeleccionado;
    private List<BeanIndicador> lstIndisSelected = new ArrayList<BeanIndicador>();
    private BeanCriterio critSelected;   
    private List<BeanLeyenda> lstLeyendas = new ArrayList<BeanLeyenda>();
    private BeanCriterio critSelectedAux;
    private Date fechaHoy = new Date();
    private String versionGenerada;
    private transient RichTreeTable tt;
    private String leyenda;
    private String descIndicadorSelected;
    private String valorDesc;
    private BeanCriterio indiSelectLeyenda = new BeanCriterio();
    private String btnRegistrarFicha;
    private String styleClass;
    private int numValores;
    private int tipEvento;//0=default, 1=registrar(nuevo), 2=editar
    private BeanFicha fichaEditarClon;
    private String actDesact;
    private String actDesEstilo;
    private String advertenciaSPED0004;
    private boolean leyendasGenerales;
    private BeanCriterio indiHeaderSelectLeyenda = new BeanCriterio();
    private String shortDesc;
    private List<BeanLeyenda> lstLeyendasRaiz = new ArrayList<BeanLeyenda>();
    private List lstTiposFichaCurso;
    
    public bSessionRegistrarFicha() {

    }

    public void setLstTiposFichaCurso(List lstTiposFichaCurso) {
        this.lstTiposFichaCurso = lstTiposFichaCurso;
    }

    public List getLstTiposFichaCurso() {
        return lstTiposFichaCurso;
    }

    public void setLstLeyendasRaiz(List<BeanLeyenda> lstLeyendasRaiz) {
        this.lstLeyendasRaiz = lstLeyendasRaiz;
    }

    public List<BeanLeyenda> getLstLeyendasRaiz() {
        return lstLeyendasRaiz;
    }

    public void setShortDesc(String shortDesc) {
        this.shortDesc = shortDesc;
    }

    public String getShortDesc() {
        return shortDesc;
    }

    public void setIndiHeaderSelectLeyenda(BeanCriterio indiHeaderSelectLeyenda) {
        this.indiHeaderSelectLeyenda = indiHeaderSelectLeyenda;
    }

    public BeanCriterio getIndiHeaderSelectLeyenda() {
        return indiHeaderSelectLeyenda;
    }

    public void setLeyendasGenerales(boolean leyendasGenerales) {
        this.leyendasGenerales = leyendasGenerales;
    }

    public boolean isLeyendasGenerales() {
        return leyendasGenerales;
    }

    public void setActDesact(String actDesact) {
        this.actDesact = actDesact;
    }

    public void setAdvertenciaSPED0004(String advertenciaSPED0004) {
        this.advertenciaSPED0004 = advertenciaSPED0004;
    }

    public String getAdvertenciaSPED0004() {
        return advertenciaSPED0004;
    }

    public String getActDesact() {
        if(fichaEditarClon != null){
            if(fichaEditarClon.getEstadoFicha().equals("0")){
                actDesact = "Activar";
            }else{
                actDesact = "Desactivar";
            }
        }else{
            actDesact = "Activar/Desactivar";
        }
        return actDesact;
    }

    public void setActDesEstilo(String actDesEstilo) {
        this.actDesEstilo = actDesEstilo;
    }

    public String getActDesEstilo() {
        return actDesEstilo;
    }

    public void setFichaEditarClon(BeanFicha fichaEditarClon) {
        this.fichaEditarClon = fichaEditarClon;
    }

    public BeanFicha getFichaEditarClon() {
        return fichaEditarClon;
    }

    public void setNumValores(int numValores) {
        this.numValores = numValores;
    }

    public int getNumValores() {
        return numValores;
    }

    public void setTipEvento(int tipEvento) {
        this.tipEvento = tipEvento;
    }

    public int getTipEvento() {
        return tipEvento;
    }

    public void setStyleClass(String styleClass) {
        this.styleClass = styleClass;
    }

    public String getStyleClass() {
        return styleClass;
    }

    public void setBtnRegistrarFicha(String btnRegistrarFicha) {
        this.btnRegistrarFicha = btnRegistrarFicha;
    }

    public String getBtnRegistrarFicha() {
        return btnRegistrarFicha;
    }

    public void setIndiSelectLeyenda(BeanCriterio indiSelectLeyenda) {
        this.indiSelectLeyenda = indiSelectLeyenda;
    }

    public BeanCriterio getIndiSelectLeyenda() {
        return indiSelectLeyenda;
    }

    public void setValorDesc(String valorDesc) {
        this.valorDesc = valorDesc;
    }

    public String getValorDesc() {
        return valorDesc;
    }

    public void setDescIndicadorSelected(String descIndicadorSelected) {
        this.descIndicadorSelected = descIndicadorSelected;
    }

    public String getDescIndicadorSelected() {
        return descIndicadorSelected;
    }

    public void setLeyenda(String leyenda) {
        this.leyenda = leyenda;
    }

    public String getLeyenda() {
        return leyenda;
    }

    public void setTt(RichTreeTable tt) {
        this.tt = tt;
    }

    public RichTreeTable getTt() {
        return tt;
    }

    public void setLstLeyendas(List<BeanLeyenda> lstLeyendas) {
        this.lstLeyendas = lstLeyendas;
    }

    public List<BeanLeyenda> getLstLeyendas() {
        return lstLeyendas;
    }

    public void setVersionGenerada(String versionGenerada) {
        this.versionGenerada = versionGenerada;
    }

    public String getVersionGenerada() {
        return versionGenerada;
    }

    public void setFechaHoy(Date fechaHoy) {
        this.fechaHoy = fechaHoy;
    }

    public Date getFechaHoy() {
        return fechaHoy;
    }

    public void setCritSelectedAux(BeanCriterio critSelectedAux) {
        this.critSelectedAux = critSelectedAux;
    }

    public BeanCriterio getCritSelectedAux() {
        return critSelectedAux;
    }

    public void setCritSelected(BeanCriterio critSelected) {
        this.critSelected = critSelected;
    }

    public BeanCriterio getCritSelected() {
        return critSelected;
    }

    public void setLstIndisSelected(List<BeanIndicador> lstIndisSelected) {
        this.lstIndisSelected = lstIndisSelected;
    }

    public List<BeanIndicador> getLstIndisSelected() {
        return lstIndisSelected;
    }

    public void setDescCriterioSeleccionado(String descCriterioSeleccionado) {
        this.descCriterioSeleccionado = descCriterioSeleccionado;
    }

    public String getDescCriterioSeleccionado() {
        return descCriterioSeleccionado;
    }

    public void setLstIndicadoresByCriterio(List<BeanIndicador> lstIndicadoresByCriterio) {
        this.lstIndicadoresByCriterio = lstIndicadoresByCriterio;
    }

    public List<BeanIndicador> getLstIndicadoresByCriterio() {
        return lstIndicadoresByCriterio;
    }
/*
    public void setLstCriteriosMultiples(HashSet<BeanCriterio> lstCriteriosMultiples) {
        this.lstCriteriosMultiples = lstCriteriosMultiples;
    }

    public HashSet<BeanCriterio> getLstCriteriosMultiples() {
        return lstCriteriosMultiples;
    }
*/
    public void setLstCriteriosMultiples(List<BeanCriterio> lstCriteriosMultiples) {
        this.lstCriteriosMultiples = lstCriteriosMultiples;
    }

    public List<BeanCriterio> getLstCriteriosMultiples() {
        return lstCriteriosMultiples;
    }

    public void setPermisosTree(ChildPropertyTreeModel permisosTree) {
        this.permisosTree = permisosTree;
    }

    public ChildPropertyTreeModel getPermisosTree() {
        return permisosTree;
    }

   /* public void setLstCriteriosFin(List<BeanCriterio> lstCriteriosFin) {
        this.lstCriteriosFin = lstCriteriosFin;
    }

    public List<BeanCriterio> getLstCriteriosFin() {
        return lstCriteriosFin;
    }
*/
    public void setLstCriterios(List<BeanCriterio> lstCriterios) {
        this.lstCriterios = lstCriterios;
    }

    public List<BeanCriterio> getLstCriterios() {
        return lstCriterios;
    }

    public void setLstFichas(List<BeanFicha> lstFichas) {
        this.lstFichas = lstFichas;
    }

    public List<BeanFicha> getLstFichas() {
        return lstFichas;
    }

    public void setExec(int exec) {
        this.exec = exec;
    }

    public int getExec() {
        return exec;
    }

    public void setNidCriterio(int nidCriterio) {
        this.nidCriterio = nidCriterio;
    }

    public int getNidCriterio() {
        return nidCriterio;
    }

    public void setDescCriterio(String descCriterio) {
        this.descCriterio = descCriterio;
    }

    public String getDescCriterio() {
        return descCriterio;
    }

    public void setVisiblePanelBoxPanelBoxFicha(boolean visiblePanelBoxPanelBoxFicha) {
        this.visiblePanelBoxPanelBoxFicha = visiblePanelBoxPanelBoxFicha;
    }

    public boolean isVisiblePanelBoxPanelBoxFicha() {
        return visiblePanelBoxPanelBoxFicha;
    }

    public void setTipoFicha(String tipoFicha) {
        this.tipoFicha = tipoFicha;
    }

    public String getTipoFicha() {
        return tipoFicha;
    }

    public void setTipFichaCurs(String tipFichaCurs) {
        this.tipFichaCurs = tipFichaCurs;
    }

    public String getTipFichaCurs() {
        return tipFichaCurs;
    }

    public void setLstIndicadores(List<BeanIndicador> lstIndicadores) {
        this.lstIndicadores = lstIndicadores;
    }

    public List<BeanIndicador> getLstIndicadores() {
        return lstIndicadores;
    }

    public void setNidIndicador(int nidIndicador) {
        this.nidIndicador = nidIndicador;
    }

    public int getNidIndicador() {
        return nidIndicador;
    }

    public void setDescIndicador(String descIndicador) {
        this.descIndicador = descIndicador;
    }

    public String getDescIndicador() {
        return descIndicador;
    }
}
