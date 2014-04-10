package sped.negocio.entidades.beans;

import java.util.List;

import javax.persistence.Column;

public class BeanUsuario implements java.io.Serializable{
    @SuppressWarnings("compatibility:-7716331137436807403")
    private static final long serialVersionUID = 1L;

    private String clave;
    private String dni;
    private String estadoUsuario;
    private byte[] foto;
    private String img;//WS
    private Integer nidUsuario;
    private String nombres;
    private String usuario;
    private BeanRol rol;
    private int nidRol;
    private BeanSede sede;   
    private int nidSede;
    private int nidNivel;
    private BeanAreaAcademica areaAcademica;
    private int nidAreaAcademica;
    private BeanError error;
    private String areaAcYProf;
    private String descripcionEstadoUsuario;//Auxiliares tabla:admcons
    private List<Integer> lstPermisos;
    private Integer nidLog;
    private String correo;
    private String isNuevo;   
    private String isSupervisor;

    public BeanUsuario(){}

    public void setNidLog(Integer nidLog) {
        this.nidLog = nidLog;
    }

    public Integer getNidLog() {
        return nidLog;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getImg() {
        return img;
    }

    public void setLstPermisos(List<Integer> lstPermisos) {
        this.lstPermisos = lstPermisos;
    }

    public List<Integer> getLstPermisos() {
        return lstPermisos;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getClave() {
        return clave;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getDni() {
        return dni;
    }

    public void setEstadoUsuario(String estadoUsuario) {
        this.estadoUsuario = estadoUsuario;
    }

    public String getEstadoUsuario() {
        return estadoUsuario;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public byte[] getFoto() {
        return foto;
    }


    public void setNidUsuario(Integer nidUsuario) {
        this.nidUsuario = nidUsuario;
    }

    public Integer getNidUsuario() {
        return nidUsuario;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getNombres() {
        return nombres;
    } 
    
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setRol(BeanRol rol) {
        this.rol = rol;
    }

    public BeanRol getRol() {
        return rol;
    }

    public void setSede(BeanSede sede) {
        this.sede = sede;
    }

    public BeanSede getSede() {
        return sede;
    }

    public void setAreaAcademica(BeanAreaAcademica areaAcademica) {
        this.areaAcademica = areaAcademica;
    }

    public BeanAreaAcademica getAreaAcademica() {
        return areaAcademica;
    }

    public void setError(BeanError error) {
        this.error = error;
    }

    public BeanError getError() {
        return error;
    }

    public void setAreaAcYProf(String areaAcYProf) {
        this.areaAcYProf = areaAcYProf;
    }

    public String getAreaAcYProf() {
        return areaAcYProf;
    }

    public void setDescripcionEstadoUsuario(String descripcionEstadoUsuario) {
        this.descripcionEstadoUsuario = descripcionEstadoUsuario;
    }

    public String getDescripcionEstadoUsuario() {
        return descripcionEstadoUsuario;
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

    public void setNidSede(int nidSede) {
        this.nidSede = nidSede;
    }

    public int getNidSede() {
        return nidSede;
    }

    public void setNidNivel(int nidNivel) {
        this.nidNivel = nidNivel;
    }

    public int getNidNivel() {
        return nidNivel;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getCorreo() {
        return correo;
    }

    public void setIsNuevo(String isNuevo) {
        this.isNuevo = isNuevo;
    }

    public String getIsNuevo() {
        return isNuevo;
    }

    public void setIsSupervisor(String isSupervisor) {
        this.isSupervisor = isSupervisor;
    }

    public String getIsSupervisor() {
        return isSupervisor;
    }
}
