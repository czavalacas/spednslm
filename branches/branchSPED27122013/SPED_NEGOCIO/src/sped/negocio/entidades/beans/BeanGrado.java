package sped.negocio.entidades.beans;

import java.io.Serializable;

import java.util.List;

public class BeanGrado implements Serializable {
    @SuppressWarnings("compatibility:-8670388437005789117")
    private static final long serialVersionUID = 1L;

    private String descripcionGrado;
    private Integer nidGrado;
    private List<BeanGradoNivel> gradoNivelLista;
    private String abvr;

    public void setAbvr(String abvr) {
        this.abvr = abvr;
    }

    public String getAbvr() {
        return abvr;
    }

    public void setDescripcionGrado(String descripcionGrado) {
        this.descripcionGrado = descripcionGrado;
    }

    public String getDescripcionGrado() {
        return descripcionGrado;
    }


    public void setNidGrado(Integer nidGrado) {
        this.nidGrado = nidGrado;
    }

    public Integer getNidGrado() {
        return nidGrado;
    }

    public void setGradoNivelLista(List<BeanGradoNivel> gradoNivelLista) {
        this.gradoNivelLista = gradoNivelLista;
    }

    public List<BeanGradoNivel> getGradoNivelLista() {
        return gradoNivelLista;
    }
  
}
