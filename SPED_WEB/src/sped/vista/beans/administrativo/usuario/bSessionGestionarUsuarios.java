package sped.vista.beans.administrativo.usuario;

import java.io.Serializable;

import java.util.List;

import sped.negocio.entidades.beans.BeanNivel;
import sped.negocio.entidades.beans.BeanSede;
import sped.negocio.entidades.beans.BeanSedeNivel;
import sped.negocio.entidades.beans.BeanUsuario;

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
    private String nombres;
    private String dni;
    private String usuario;
    private String clave;
    private boolean renderAreaAcdemica = false;
    private boolean renderActualizar = false;
    ///filtro///
    private String fNombres;
    private String fDni;
    private String fUsuario;
    private int fNidAreaAcademica;
    private int fNidRol;
    private int fNidEstado;
    private int fNidSede;
    private int fNidNivel;
    private boolean fbooleanSede = false;
    private boolean fbooleanNivel = false;

    private List lstRol;
    private List lstAreaAcademica;
    private List lstEstadoUsario;
    private List<BeanUsuario> lstUsuario;
    private List<BeanSede> lstSede;
    private List<BeanSedeNivel> lstNivel;

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

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getNombres() {
        return nombres;
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

    public void setFNombres(String fNombres) {
        this.fNombres = fNombres;
    }

    public String getFNombres() {
        return fNombres;
    }

    public void setFDni(String fDni) {
        this.fDni = fDni;
    }

    public String getFDni() {
        return fDni;
    }

    public void setFUsuario(String fUsuario) {
        this.fUsuario = fUsuario;
    }

    public String getFUsuario() {
        return fUsuario;
    }

    public void setFNidAreaAcademica(int fNidAreaAcademica) {
        this.fNidAreaAcademica = fNidAreaAcademica;
    }

    public int getFNidAreaAcademica() {
        return fNidAreaAcademica;
    }

    public void setFNidRol(int fNidRol) {
        this.fNidRol = fNidRol;
    }

    public int getFNidRol() {
        return fNidRol;
    }

    public void setFNidEstado(int fNidEstado) {
        this.fNidEstado = fNidEstado;
    }

    public int getFNidEstado() {
        return fNidEstado;
    }

    public void setFNidSede(int fNidSede) {
        this.fNidSede = fNidSede;
    }

    public int getFNidSede() {
        return fNidSede;
    }

    public void setFNidNivel(int fNidNivel) {
        this.fNidNivel = fNidNivel;
    }

    public int getFNidNivel() {
        return fNidNivel;
    }

    public void setLstRol(List lstRol) {
        this.lstRol = lstRol;
    }

    public List getLstRol() {
        return lstRol;
    }

    public void setLstAreaAcademica(List lstAreaAcademica) {
        this.lstAreaAcademica = lstAreaAcademica;
    }

    public List getLstAreaAcademica() {
        return lstAreaAcademica;
    }

    public void setLstEstadoUsario(List lstEstadoUsario) {
        this.lstEstadoUsario = lstEstadoUsario;
    }

    public List getLstEstadoUsario() {
        return lstEstadoUsario;
    }

    public void setLstUsuario(List<BeanUsuario> lstUsuario) {
        this.lstUsuario = lstUsuario;
    }

    public List<BeanUsuario> getLstUsuario() {
        return lstUsuario;
    }

    public void setLstSede(List<BeanSede> lstSede) {
        this.lstSede = lstSede;
    }

    public List<BeanSede> getLstSede() {
        return lstSede;
    }

    public void setLstNivel(List<BeanSedeNivel> lstNivel) {
        this.lstNivel = lstNivel;
    }

    public List<BeanSedeNivel> getLstNivel() {
        return lstNivel;
    }

    public void setFbooleanSede(boolean fbooleanSede) {
        this.fbooleanSede = fbooleanSede;
    }

    public boolean isFbooleanSede() {
        return fbooleanSede;
    }

    public void setFbooleanNivel(boolean fbooleanNivel) {
        this.fbooleanNivel = fbooleanNivel;
    }

    public boolean isFbooleanNivel() {
        return fbooleanNivel;
    }
}
