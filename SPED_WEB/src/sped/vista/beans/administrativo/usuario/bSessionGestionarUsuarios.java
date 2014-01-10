package sped.vista.beans.administrativo.usuario;

import java.io.Serializable;

public class bSessionGestionarUsuarios implements Serializable {
    @SuppressWarnings("compatibility:-2520286367188850798")
    private static final long serialVersionUID = 1L;

    private int exec;
    private int cod;
    private int nidRol;
    private int nidAreaAcademica;
    private int nidUsuario;
    private int tipoEvento;
    private String titleDialogGestion;
    private String nomBtnGestion;    
    private String nombre;
    private String apellido;
    private String dni;
    private String usuario;
    private String clave;
    private boolean renderAreaAcdemica = false;
    private boolean renderActualizar = false;

    public bSessionGestionarUsuarios() {
    }

    public void setNidRol(int nidRol) {
        this.nidRol = nidRol;
    }

    public int getNidRol() {
        return nidRol;
    }

    public void setNidAreaAcademica(int nidAreaAcademica) {
        this.nidAreaAcademica = nidAreaAcademica;
    }

    public int getNidAreaAcademica() {
        return nidAreaAcademica;
    }

    public void setExec(int exec) {
        this.exec = exec;
    }

    public int getExec() {
        return exec;
    }

    public void setTitleDialogGestion(String titleDialogGestion) {
        this.titleDialogGestion = titleDialogGestion;
    }

    public String getTitleDialogGestion() {
        return titleDialogGestion;
    }

    public void setNomBtnGestion(String nomBtnGestion) {
        this.nomBtnGestion = nomBtnGestion;
    }

    public String getNomBtnGestion() {
        return nomBtnGestion;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public int getCod() {
        return cod;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getApellido() {
        return apellido;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getDni() {
        return dni;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getClave() {
        return clave;
    }

    public void setTipoEvento(int tipoEvento) {
        this.tipoEvento = tipoEvento;
    }

    public int getTipoEvento() {
        return tipoEvento;
    }

    public void setNidUsuario(int nidUsuario) {
        this.nidUsuario = nidUsuario;
    }

    public int getNidUsuario() {
        return nidUsuario;
    }

    public void setRenderAreaAcdemica(boolean renderAreaAcdemica) {
        this.renderAreaAcdemica = renderAreaAcdemica;
    }

    public boolean isRenderAreaAcdemica() {
        return renderAreaAcdemica;
    }

    public void setRenderActualizar(boolean renderActualizar) {
        this.renderActualizar = renderActualizar;
    }

    public boolean isRenderActualizar() {
        return renderActualizar;
    }
}
