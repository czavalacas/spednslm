package sped.negocio.entidades.beans;

import java.io.Serializable;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Id;

public class BeanPermiso implements Serializable {
    @SuppressWarnings("compatibility:8549470001952528068")
    private static final long serialVersionUID = 1L;

    private String descripcionPermiso;
    private String estadoRegistro;
    private String habilidad;
    private String isNodo;
    private int nidPadre;
    private int nidPermiso;
    private int nivel;
    private String url;
    private String urlIcono;
    private List<Integer> lstPermisos;
    private String indMostrar;
    private List<BeanPermiso> listaHijos;

    public void setIndMostrar(String indMostrar) {
        this.indMostrar = indMostrar;
    }

    public String getIndMostrar() {
        return indMostrar;
    }

    public void setListaHijos(List<BeanPermiso> listaHijos) {
        this.listaHijos = listaHijos;
    }

    public List<BeanPermiso> getListaHijos() {
        return listaHijos;
    }

    public void setDescripcionPermiso(String descripcionPermiso) {
        this.descripcionPermiso = descripcionPermiso;
    }

    public String getDescripcionPermiso() {
        return descripcionPermiso;
    }

    public void setLstPermisos(List<Integer> lstPermisos) {
        this.lstPermisos = lstPermisos;
    }

    public List<Integer> getLstPermisos() {
        return lstPermisos;
    }

    public void setEstadoRegistro(String estadoRegistro) {
        this.estadoRegistro = estadoRegistro;
    }

    public String getEstadoRegistro() {
        return estadoRegistro;
    }

    public void setHabilidad(String habilidad) {
        this.habilidad = habilidad;
    }

    public String getHabilidad() {
        return habilidad;
    }

    public void setIsNodo(String isNodo) {
        this.isNodo = isNodo;
    }

    public String getIsNodo() {
        return isNodo;
    }

    public void setNidPadre(int nidPadre) {
        this.nidPadre = nidPadre;
    }

    public int getNidPadre() {
        return nidPadre;
    }

    public void setNidPermiso(int nidPermiso) {
        this.nidPermiso = nidPermiso;
    }

    public int getNidPermiso() {
        return nidPermiso;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public int getNivel() {
        return nivel;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrlIcono(String urlIcono) {
        this.urlIcono = urlIcono;
    }

    public String getUrlIcono() {
        return urlIcono;
    }
}
