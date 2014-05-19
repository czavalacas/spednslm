package sped.negocio.entidades.beans;

import java.io.Serializable;

import java.sql.Time;

import java.util.List;

public class BeanHorario implements Serializable {
    @SuppressWarnings("compatibility:-7442992646229351967")
    private static final long serialVersionUID = 1L;

    private Time inicio;
    private Time fin;
    private BeanMain lunes;
    private List<BeanMain> lstMain;

    public BeanHorario(){}

    public void setInicio(Time inicio) {
        this.inicio = inicio;
    }

    public Time getInicio() {
        return inicio;
    }

    public void setFin(Time fin) {
        this.fin = fin;
    }

    public Time getFin() {
        return fin;
    }

    public void setLunes(BeanMain lunes) {
        this.lunes = lunes;
    }

    public BeanMain getLunes() {
        return lunes;
    }

    public void setLstMain(List<BeanMain> lstMain) {
        this.lstMain = lstMain;
    }

    public List<BeanMain> getLstMain() {
        return lstMain;
    }

}
