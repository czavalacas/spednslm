package sped.vista.beans.evaluacion.grafico_reporte;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.model.SelectItem;

import sped.negocio.entidades.beans.BeanConsDesem;

public class bSessionDesempenoIndicador implements Serializable{
    @SuppressWarnings("compatibility:7736607558516257445")
    private static final long serialVersionUID = 1L;
    private int exec = 0;
    private String indicador;
    private List<SelectItem> itemNombreIndicadores;
    private String nidSede;
    private String nidArea;
    private String nidNivele;
    private boolean estadoChoiceSede = false;
    private List listaSedesFiltro;
    private List listaAreasFiltro;
    private List listaNivelesFiltro;
    private Date fechaInicio;
    private Date fechaFin;
    private String cidIndicador;
    List<BeanConsDesem> lstGrafPies = new ArrayList<BeanConsDesem>();

    public void setCidIndicador(String cidIndicador) {
        this.cidIndicador = cidIndicador;
    }

    public String getCidIndicador() {
        return cidIndicador;
    }

    public void setLstGrafPies(List<BeanConsDesem> lstGrafPies) {
        this.lstGrafPies = lstGrafPies;
    }

    public List<BeanConsDesem> getLstGrafPies() {
        return lstGrafPies;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setNidArea(String nidArea) {
        this.nidArea = nidArea;
    }

    public String getNidArea() {
        return nidArea;
    }

    public void setNidNivele(String nidNivele) {
        this.nidNivele = nidNivele;
    }

    public String getNidNivele() {
        return nidNivele;
    }

    public void setExec(int exec) {
        this.exec = exec;
    }

    public int getExec() {
        return exec;
    }

    public void setIndicador(String indicador) {
        this.indicador = indicador;
    }

    public String getIndicador() {
        return indicador;
    }

    public void setItemNombreIndicadores(List<SelectItem> itemNombreIndicadores) {
        this.itemNombreIndicadores = itemNombreIndicadores;
    }

    public List<SelectItem> getItemNombreIndicadores() {
        return itemNombreIndicadores;
    }

    public void setNidSede(String nidSede) {
        this.nidSede = nidSede;
    }

    public String getNidSede() {
        return nidSede;
    }

    public void setEstadoChoiceSede(boolean estadoChoiceSede) {
        this.estadoChoiceSede = estadoChoiceSede;
    }

    public boolean isEstadoChoiceSede() {
        return estadoChoiceSede;
    }

    public void setListaSedesFiltro(List listaSedesFiltro) {
        this.listaSedesFiltro = listaSedesFiltro;
    }

    public List getListaSedesFiltro() {
        return listaSedesFiltro;
    }

    public void setListaAreasFiltro(List listaAreasFiltro) {
        this.listaAreasFiltro = listaAreasFiltro;
    }

    public List getListaAreasFiltro() {
        return listaAreasFiltro;
    }

    public void setListaNivelesFiltro(List listaNivelesFiltro) {
        this.listaNivelesFiltro = listaNivelesFiltro;
    }

    public List getListaNivelesFiltro() {
        return listaNivelesFiltro;
    }
}
