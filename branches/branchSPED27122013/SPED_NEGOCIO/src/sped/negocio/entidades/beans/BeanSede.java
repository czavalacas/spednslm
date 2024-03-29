package sped.negocio.entidades.beans;

import java.io.Serializable;

public class BeanSede implements Serializable {
    @SuppressWarnings("compatibility:-678576333470706426")
    private static final long serialVersionUID = 1L;
    
    private String descripcionSede;
    private Integer nidSede;
    private String abvr;

    public void setAbvr(String abvr) {
        this.abvr = abvr;
    }

    public String getAbvr() {
        return abvr;
    }

    public void setDescripcionSede(String descripcionSede) {
        this.descripcionSede = descripcionSede;
    }

    public String getDescripcionSede() {
        return descripcionSede;
    }


    public void setNidSede(Integer nidSede) {
        this.nidSede = nidSede;
    }

    public Integer getNidSede() {
        return nidSede;
    }
}
