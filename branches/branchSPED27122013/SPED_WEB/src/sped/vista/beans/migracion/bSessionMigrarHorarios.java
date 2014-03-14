package sped.vista.beans.migracion;

import java.io.Serializable;

import java.util.List;

import sped.negocio.entidades.beans.BeanCombo;

public class bSessionMigrarHorarios implements Serializable {
    @SuppressWarnings("compatibility:4148271209688817738")
    private static final long serialVersionUID = 1L;
    private List lstsedes;
    private int exec;
    
    public bSessionMigrarHorarios() {
    }

    public void setLstsedes(List lstsedes) {
        this.lstsedes = lstsedes;
    }

    public List getLstsedes() {
        return lstsedes;
    }

    public void setExec(int exec) {
        this.exec = exec;
    }

    public int getExec() {
        return exec;
    }
}
