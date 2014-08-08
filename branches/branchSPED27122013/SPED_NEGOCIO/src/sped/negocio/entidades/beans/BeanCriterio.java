package sped.negocio.entidades.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import sped.negocio.Utils.Utiles;
import sped.negocio.entidades.eval.FichaCriterio;

public class BeanCriterio implements Serializable, Cloneable {
    @SuppressWarnings("compatibility:-4334476189667862718")
    private static final long serialVersionUID = 1L;

    private String descripcionCriterio;
    private Integer nidCriterio;
    private List<BeanCriterio> lstIndicadores = new ArrayList<BeanCriterio>();
    private boolean selected = false;
    private boolean mostrarBoton = false;
    private BeanError beanError = new BeanError();
    private List<BeanLeyenda> lstLeyenda = new ArrayList<BeanLeyenda>();
    private String display = "display:none;";
    private Integer orden;
    private boolean mostrarUpDown;
    private int esIndicador;
    private boolean noMostrarDown;
    private int cantidadValoresWS;
    //AUXILIAR
    private double sumaNota;
    private int cantidadIndicadores;
    private FichaCriterio fichaCriterioAUX;
    private String isRaiz;
    //
    private String displaySpinBox;
    private String displayInput;
    private String valorInput;
    private double valorSpinBox;
    private double valorSpinBox2;
    private String estilo;
    private Integer nidCriterioPadre;
    private double notaVige;
    //dfloresgonz 03.05.2014 se agrega porque salia error al evaluar con Subdirector rol = 4
    private int nidCriterioIndicador;
    //dfloresgonz 05.08.2014 se agrega para mantener el maximo Valor de un INDICADOR al traer en frm_evaluar
    private double maxValor;
    private List<BeanComboString> lstValoresPosibles = new ArrayList<BeanComboString>();
    private List lstValoresPosCombo;
    private String descLeyendaSeleccionada;//dfloresgonz 08.08.2014 para mostrar la desc. cuando se seleccione un valor al evaluar

    public Object clone() {
        Object clone = null;
        try {
            clone = super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return clone;
    }
    
    @Override
    public int hashCode(){
        if(this.getNidCriterio() != null){
            if(this.getNidCriterio() != 0){
                return this.getNidCriterio().hashCode();
            }else{
                return 0;    
            }
        }else{
            return 0;
        }
    }
    
    @Override
    public boolean equals(Object obj){
        if(obj == null){
            return false;
        }
        if(obj instanceof BeanCriterio){
            BeanCriterio crit = (BeanCriterio) obj;
            return this.getNidCriterio().equals(crit.getNidCriterio());
        }else{
            return false;
        }
    }

    public void setDescLeyendaSeleccionada(String descLeyendaSeleccionada) {
        this.descLeyendaSeleccionada = descLeyendaSeleccionada;
    }

    public String getDescLeyendaSeleccionada() {
        return descLeyendaSeleccionada;
    }

    public void setValorSpinBox2(double valorSpinBox2) {
        this.valorSpinBox2 = valorSpinBox2;
    }

    public double getValorSpinBox2() {
        return valorSpinBox2;
    }

    public void setLstValoresPosCombo(List lstValoresPosCombo) {
        this.lstValoresPosCombo = lstValoresPosCombo;
    }

    public List getLstValoresPosCombo() {
        return lstValoresPosCombo;
    }

    public void setLstValoresPosibles(List<BeanComboString> lstValoresPosibles) {
        this.lstValoresPosibles = lstValoresPosibles;
    }

    public List<BeanComboString> getLstValoresPosibles() {
        return lstValoresPosibles;
    }

    public void setMaxValor(double maxValor) {
        this.maxValor = maxValor;
    }

    public double getMaxValor() {
        return maxValor;
    }

    public void setNotaVige(double notaVige) {
        this.notaVige = notaVige;
    }

    public double getNotaVige() {
        return notaVige;
    }

    public void setNidCriterioPadre(Integer nidCriterioPadre) {
        this.nidCriterioPadre = nidCriterioPadre;
    }

    public Integer getNidCriterioPadre() {
        return nidCriterioPadre;
    }

    public void setValorInput(String valorInput) {
        this.valorInput = valorInput;
    }

    public String getValorInput() {
        return valorInput;
    }

    public void setValorSpinBox(double valorSpinBox) {
        this.valorSpinBox = valorSpinBox;
    }

    public double getValorSpinBox() {
        return valorSpinBox;
    }

    public void setEstilo(String estilo) {
        this.estilo = estilo;
    }

    public String getEstilo() {
        return estilo;
    }

    public void setDisplaySpinBox(String displaySpinBox) {
        this.displaySpinBox = displaySpinBox;
    }

    public String getDisplaySpinBox() {
        return displaySpinBox;
    }

    public void setDisplayInput(String displayInput) {
        this.displayInput = displayInput;
    }

    public String getDisplayInput() {
        return displayInput;
    }

    public void setIsRaiz(String isRaiz) {
        this.isRaiz = isRaiz;
    }

    public String getIsRaiz() {
        return isRaiz;
    }

    public void setSumaNota(double sumaNota) {
        this.sumaNota = sumaNota;
    }

    public double getSumaNota() {
        return sumaNota;
    }

    public void setCantidadIndicadores(int cantidadIndicadores) {
        this.cantidadIndicadores = cantidadIndicadores;
    }

    public int getCantidadIndicadores() {
        return cantidadIndicadores;
    }

    public void setFichaCriterioAUX(FichaCriterio fichaCriterioAUX) {
        this.fichaCriterioAUX = fichaCriterioAUX;
    }

    public FichaCriterio getFichaCriterioAUX() {
        return fichaCriterioAUX;
    }

    public void setCantidadValoresWS(int cantidadValoresWS) {
        this.cantidadValoresWS = cantidadValoresWS;
    }

    public int getCantidadValoresWS() {
        return cantidadValoresWS;
    }

    public void setNoMostrarDown(boolean noMostrarDown) {
        this.noMostrarDown = noMostrarDown;
    }

    public boolean isNoMostrarDown() {
        return noMostrarDown;
    }

    public void setEsIndicador(int esIndicador) {
        this.esIndicador = esIndicador;
    }

    public int getEsIndicador() {
        return esIndicador;
    }

    public int compareTo(Object obj){
        BeanCriterio item = (BeanCriterio) obj;
        Integer ordenObj = item.getOrden();
        Integer ordenThis = this.getOrden();
        return (ordenThis.compareTo(ordenObj));
    }

    public void setMostrarUpDown(boolean mostrarUpDown) {
        this.mostrarUpDown = mostrarUpDown;
    }

    public boolean isMostrarUpDown() {
        return mostrarUpDown;
    }

    public void setOrden(Integer orden) {
        this.orden = orden;
    }

    public Integer getOrden() {
        return orden;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

    public String getDisplay() {
        return display;
    }


    public void setLstLeyenda(List<BeanLeyenda> lstLeyenda) {
        this.lstLeyenda = lstLeyenda;
    }

    public List<BeanLeyenda> getLstLeyenda() {
        return lstLeyenda;
    }

    public void setBeanError(BeanError beanError) {
        this.beanError = beanError;
    }

    public BeanError getBeanError() {
        return beanError;
    }

    public void setMostrarBoton(boolean mostrarBoton) {
        this.mostrarBoton = mostrarBoton;
    }

    public boolean isMostrarBoton() {
        return mostrarBoton;
    }

    public void setDescripcionCriterio(String descripcionCriterio) {
        this.descripcionCriterio = descripcionCriterio;
    }

    public String getDescripcionCriterio() {
        return descripcionCriterio;
    }

    public void setNidCriterio(Integer nidCriterio) {
        this.nidCriterio = nidCriterio;
    }

    public Integer getNidCriterio() {
        return nidCriterio;
    }

    public void setLstIndicadores(List<BeanCriterio> lstIndicadores) {
        this.lstIndicadores = lstIndicadores;
    }

    public List<BeanCriterio> getLstIndicadores() {
        return lstIndicadores;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setNidCriterioIndicador(int nidCriterioIndicador) {
        this.nidCriterioIndicador = nidCriterioIndicador;
    }

    public int getNidCriterioIndicador() {
        return nidCriterioIndicador;
    }
}
