package sped.vista.beans.evaluacion.ficha;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.apache.myfaces.trinidad.model.ChildPropertyTreeModel;
import sped.negocio.entidades.beans.BeanCriterio;
import sped.negocio.entidades.beans.BeanFicha;
import sped.negocio.entidades.beans.BeanIndicador;

/** Clase de Sesion del Bean bRegistrarFicha
 * @author dfloresgonz
 * @since 27.12.2013
 */
public class bSessionRegistrarFicha implements Serializable {
    
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
    private List<BeanCriterio> lstCriteriosFin;
    private HashSet<BeanCriterio> lstCriteriosMultiples = new HashSet<BeanCriterio>();
    //private HashSet<BeanTRItem> hashItemsFinal = new HashSet<BeanTRItem>();
    
    public bSessionRegistrarFicha() {

    }

    public void setLstCriteriosMultiples(HashSet<BeanCriterio> lstCriteriosMultiples) {
        this.lstCriteriosMultiples = lstCriteriosMultiples;
    }

    public HashSet<BeanCriterio> getLstCriteriosMultiples() {
        return lstCriteriosMultiples;
    }

    public void setPermisosTree(ChildPropertyTreeModel permisosTree) {
        this.permisosTree = permisosTree;
    }

    public ChildPropertyTreeModel getPermisosTree() {
        return permisosTree;
    }

    public void setLstCriteriosFin(List<BeanCriterio> lstCriteriosFin) {
        this.lstCriteriosFin = lstCriteriosFin;
    }

    public List<BeanCriterio> getLstCriteriosFin() {
        return lstCriteriosFin;
    }

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
