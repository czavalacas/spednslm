package sped.negocio.entidades.beans;

import java.io.Serializable;

import java.util.List;

public class BeanGrado implements Serializable {
    @SuppressWarnings("compatibility:-8670388437005789117")
    private static final long serialVersionUID = 1L;

    private String descripcionGrado;
    private int nidGrado;
    private List<BeanGradoNivel> gradoNivelLista;

    public void setDescripcionGrado(String descripcionGrado) {
        this.descripcionGrado = descripcionGrado;
    }

    public String getDescripcionGrado() {
        return descripcionGrado;
    }

    public void setNidGrado(int nidGrado) {
        this.nidGrado = nidGrado;
    }

    public int getNidGrado() {
        return nidGrado;
    }

    public void setGradoNivelLista(List<BeanGradoNivel> gradoNivelLista) {
        this.gradoNivelLista = gradoNivelLista;
    }

    public List<BeanGradoNivel> getGradoNivelLista() {
        return gradoNivelLista;
    }
  
}
