package sped.negocio.entidades.beans;

import java.io.Serializable;

import java.util.List;

public class BeanProfesor implements Serializable {
    @SuppressWarnings("compatibility:-5658823572873989508")
    private static final long serialVersionUID = 1L;
    private String apellidos;
    private String dniProfesor;
    private String nombres;
    private String nombreCompleto;
    private List<BeanMain> mainLista;
    private String correo; 

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

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getCorreo() {
        return correo;
    }
}
