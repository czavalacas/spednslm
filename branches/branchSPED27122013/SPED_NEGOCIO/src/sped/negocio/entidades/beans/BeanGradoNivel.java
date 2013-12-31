package sped.negocio.entidades.beans;

import java.io.Serializable;

import java.util.List;

public class BeanGradoNivel implements Serializable {
    @SuppressWarnings("compatibility:8119410623663526334")
    private static final long serialVersionUID = 1L;
    
    private List<BeanAula> aulaLista;    
    private BeanNivel nivel;    
    private BeanGrado grado;

    public void setAulaLista(List<BeanAula> aulaLista) {
        this.aulaLista = aulaLista;
    }

    public List<BeanAula> getAulaLista() {
        return aulaLista;
    }

    public void setNivel(BeanNivel nivel) {
        this.nivel = nivel;
    }

    public BeanNivel getNivel() {
        return nivel;
    }

    public void setGrado(BeanGrado grado) {
        this.grado = grado;
    }

    public BeanGrado getGrado() {
        return grado;
    }
}
