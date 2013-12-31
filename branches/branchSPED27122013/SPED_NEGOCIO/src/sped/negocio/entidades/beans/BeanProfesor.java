package sped.negocio.entidades.beans;

import java.io.Serializable;

import java.util.List;

public class BeanProfesor implements Serializable {
    @SuppressWarnings("compatibility:-5658823572873989508")
    private static final long serialVersionUID = 1L;
    private String apellidos;
    private String dniProfesor;
    private String nombres;
    private List<BeanMain> mainLista;

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setDniProfesor(String dniProfesor) {
        this.dniProfesor = dniProfesor;
    }

    public String getDniProfesor() {
        return dniProfesor;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getNombres() {
        return nombres;
    }

    public void setMainLista(List<BeanMain> mainLista) {
        this.mainLista = mainLista;
    }

    public List<BeanMain> getMainLista() {
        return mainLista;
    }
}
