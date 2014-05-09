package sped.negocio.entidades.beans;

import java.io.Serializable;

public class BeanDia implements Serializable {
    @SuppressWarnings("compatibility:2314389932354345395")
    private static final long serialVersionUID = 1L;

    private Integer nDia;
    private Integer horas;

    public void setNDia(Integer nDia) {
        this.nDia = nDia;
    }

    public Integer getNDia() {
        return nDia;
    }

    public void setHoras(Integer horas) {
        this.horas = horas;
    }

    public Integer getHoras() {
        return horas;
    }

}
