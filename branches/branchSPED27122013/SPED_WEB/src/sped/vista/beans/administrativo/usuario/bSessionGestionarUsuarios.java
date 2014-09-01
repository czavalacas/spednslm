package sped.vista.beans.administrativo.usuario;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import sped.negocio.entidades.beans.BeanCalendario;
import sped.negocio.entidades.beans.BeanUsuario;

public class bSessionGestionarUsuarios implements Serializable {
    @SuppressWarnings("compatibility:-2520286367188850798")
    private static final long serialVersionUID = 1L;

    private int exec;
    private int cod;
    private int nidRol;
    private String nidAreaAcademica;
    private String nidSede;
    private int nidNivel;
    private int nidUsuario;
    private int tipoEvento;
    private boolean supervisorboolean;
    private String titleDialogGestion;
    private String nomBtnGestion;
    private String nombres;
    private String correo;
    private String dni;
    private String usuario;
    private boolean renderAreaAcdemica = false;
    private boolean disabledActualizar = false;
    private boolean disableRol;
    private boolean renderSede = false;
    private boolean renderImg = false;
    ///filtro///
    private String fNombres;
    private String fDni;
    private String fUsuario;
    private String fNidAreaAcademica;
    private int fNidRol;
    private int fNidEstado;
    private String fNidSede;
    private int fNidNivel;
    private boolean fbooleanSede = false;
    private String rutaImg;
    private List lstRolf;
    private List lstRol;
    private List lstAreaAcademica;
    private List lstEstadoUsario;
    private List<BeanUsuario> lstUsuario;
    private List lstSede;
    private List lstNivel;
    private List<SelectItem> itemDni;
    private List<SelectItem> itemUsuario;
    private List<SelectItem> itemNombre;
    private boolean disableFArea;
    private boolean disableFRol;
    //Calendario dfloresgonz 27.08.2014
    private List<BeanCalendario> lstCalendario = new ArrayList<BeanCalendario>();
    private String cidMes;
    private BeanCalendario calenSelected = new BeanCalendario();
    private String descDia;
    private List lstItemsTiposFalta;
    private String tipoFalta;
    private String msjTabla;
    private int cantEvasLaborables;

    public bSessionGestionarUsuarios() {
    }

    public void setCantEvasLaborables(int cantEvasLaborables) {
        this.cantEvasLaborables = cantEvasLaborables;
    }

    public int getCantEvasLaborables() {
        return cantEvasLaborables;
    }

    public void setMsjTabla(String msjTabla) {
        this.msjTabla = msjTabla;
    }

    public String getMsjTabla() {
        return msjTabla;
    }

    public void setTipoFalta(String tipoFalta) {
        this.tipoFalta = tipoFalta;
    }

    public String getTipoFalta() {
        return tipoFalta;
    }

    public void setLstItemsTiposFalta(List lstItemsTiposFalta) {
        this.lstItemsTiposFalta = lstItemsTiposFalta;
    }

    public List getLstItemsTiposFalta() {
        return lstItemsTiposFalta;
    }

    public void setCalenSelected(BeanCalendario calenSelected) {
        this.calenSelected = calenSelected;
    }

    public BeanCalendario getCalenSelected() {
        return calenSelected;
    }

    public void setDescDia(String descDia) {
        this.descDia = descDia;
    }

    public String getDescDia() {
        return descDia;
    }

    public void setCidMes(String cidMes) {
        this.cidMes = cidMes;
    }

    public String getCidMes() {
        return cidMes;
    }

    public void setLstCalendario(List<BeanCalendario> lstCalendario) {
        this.lstCalendario = lstCalendario;
    }

    public List<BeanCalendario> getLstCalendario() {
        return lstCalendario;
    }

    public void setNidRol(int nidRol) {
        this.nidRol = nidRol;
    }

    public int getNidRol() {
        return nidRol;
    }

    public void setNidAreaAcademica(String nidAreaAcademica) {
        this.nidAreaAcademica = nidAreaAcademica;
    }

    public String getNidAreaAcademica() {
        return nidAreaAcademica;
    }

    public void setNidSede(String nidSede) {
        this.nidSede = nidSede;
    }

    public String getNidSede() {
        return nidSede;
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

    public void setDisabledActualizar(boolean disabledActualizar) {
        this.disabledActualizar = disabledActualizar;
    }

    public boolean isDisabledActualizar() {
        return disabledActualizar;
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

    public void setFbooleanSede(boolean fbooleanSede) {
        this.fbooleanSede = fbooleanSede;
    }

    public boolean isFbooleanSede() {
        return fbooleanSede;
    }

    public void setRutaImg(String rutaImg) {
        this.rutaImg = rutaImg;
    }

    public String getRutaImg() {
        return rutaImg;
    }

    public void setRenderImg(boolean renderImg) {
        this.renderImg = renderImg;
    }

    public boolean isRenderImg() {
        return renderImg;
    }

    public void setRenderSede(boolean renderSede) {
        this.renderSede = renderSede;
    }

    public boolean isRenderSede() {
        return renderSede;
    }

    public void setNidNivel(int nidNivel) {
        this.nidNivel = nidNivel;
    }

    public int getNidNivel() {
        return nidNivel;
    }

    public void setLstSede(List lstSede) {
        this.lstSede = lstSede;
    }

    public List getLstSede() {
        return lstSede;
    }

    public void setLstNivel(List lstNivel) {
        this.lstNivel = lstNivel;
    }

    public List getLstNivel() {
        return lstNivel;
    }

    public void setLstRolf(List lstRolf) {
        this.lstRolf = lstRolf;
    }

    public List getLstRolf() {
        return lstRolf;
    }

    public void setDisableRol(boolean disableRol) {
        this.disableRol = disableRol;
    }

    public boolean isDisableRol() {
        return disableRol;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getCorreo() {
        return correo;
    }

    public void setItemDni(List<SelectItem> itemDni) {
        this.itemDni = itemDni;
    }

    public List<SelectItem> getItemDni() {
        return itemDni;
    }

    public void setItemUsuario(List<SelectItem> itemUsuario) {
        this.itemUsuario = itemUsuario;
    }

    public List<SelectItem> getItemUsuario() {
        return itemUsuario;
    }

    public void setItemNombre(List<SelectItem> itemNombre) {
        this.itemNombre = itemNombre;
    }

    public List<SelectItem> getItemNombre() {
        return itemNombre;
    }

    public void setFNidAreaAcademica(String fNidAreaAcademica) {
        this.fNidAreaAcademica = fNidAreaAcademica;
    }

    public String getFNidAreaAcademica() {
        return fNidAreaAcademica;
    }

    public void setFNidSede(String fNidSede) {
        this.fNidSede = fNidSede;
    }

    public String getFNidSede() {
        return fNidSede;
    }

    public void setSupervisorboolean(boolean supervisorboolean) {
        this.supervisorboolean = supervisorboolean;
    }

    public boolean isSupervisorboolean() {
        return supervisorboolean;
    }

    public void setDisableFArea(boolean disableFArea) {
        this.disableFArea = disableFArea;
    }

    public boolean isDisableFArea() {
        return disableFArea;
    }

    public void setDisableFRol(boolean disableFRol) {
        this.disableFRol = disableFRol;
    }

    public boolean isDisableFRol() {
        return disableFRol;
    }
}
