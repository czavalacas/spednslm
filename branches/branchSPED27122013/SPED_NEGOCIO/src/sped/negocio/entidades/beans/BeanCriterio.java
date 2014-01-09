package sped.negocio.entidades.beans;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

public class BeanCriterio implements Serializable {
    @SuppressWarnings("compatibility:-4334476189667862718")
    private static final long serialVersionUID = 1L;

    private String descripcionCriterio;
    private int nidCriterio;
    private List<BeanCriterio> lstIndicadores = new ArrayList<BeanCriterio>();
    private boolean selected = false;

    public void setDescripcionCriterio(String descripcionCriterio) {
        this.descripcionCriterio = descripcionCriterio;
    }

    public String getDescripcionCriterio() {
        return descripcionCriterio;
    }

    public void setNidCriterio(int nidCriterio) {
        this.nidCriterio = nidCriterio;
    }

    public int getNidCriterio() {
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
}
