package sped.negocio.entidades.beans;

import java.io.Serializable;

import java.util.List;

import sped.negocio.entidades.admin.GradoNivel;
import sped.negocio.entidades.admin.Main;
import sped.negocio.entidades.admin.Sede;
/** Clase BeanAula.java
 * @author czavalacas 
 * @since 29.12.2013
 */
public class BeanAula implements Serializable {
    @SuppressWarnings("compatibility:-3143063337760356247")
    private static final long serialVersionUID = 1L;
    private String descripcionAula;
    private BeanGradoNivel gradoNivel;
    private List<BeanMain> mainLista;
    private Integer nidAula;    
    private BeanSede sede;
    private int nidSede;
    private int nidNivel;
    private int flgActi;
    private String styleColor;

    public void setDescripcionAula(String descripcionAula) {
        this.descripcionAula = descripcionAula;
    }

    public String getDescripcionAula() {
        return descripcionAula;
    }

    public void setNidAula(Integer nidAula) {
        this.nidAula = nidAula;
    }

    public Integer getNidAula() {
        return nidAula;
    }

    public void setGradoNivel(BeanGradoNivel gradoNivel) {
        this.gradoNivel = gradoNivel;
    }

    public BeanGradoNivel getGradoNivel() {
        return gradoNivel;
    }

    public void setSede(BeanSede sede) {
        this.sede = sede;
    }

    public BeanSede getSede() {
        return sede;
    }

    public void setMainLista(List<BeanMain> mainLista) {
        this.mainLista = mainLista;
    }

    public List<BeanMain> getMainLista() {
        return mainLista;
    }

    public void setNidSede(int nidSede) {
        this.nidSede = nidSede;
    }

    public int getNidSede() {
        return nidSede;
    }

    public void setNidNivel(int nidNivel) {
        this.nidNivel = nidNivel;
    }

    public int getNidNivel() {
        return nidNivel;
    }

    public void setFlgActi(int flgActi) {
        this.flgActi = flgActi;
    }

    public int getFlgActi() {
        return flgActi;
    }

    public void setStyleColor(String styleColor) {
        this.styleColor = styleColor;
    }

    public String getStyleColor() {
        return styleColor;
    }
}
