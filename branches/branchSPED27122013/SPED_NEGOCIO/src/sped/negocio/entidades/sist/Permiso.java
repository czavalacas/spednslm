package sped.negocio.entidades.sist;

import java.io.Serializable;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@NamedQueries({ @NamedQuery(name = "Permiso.findAll", query = "select o from Permiso o") })
@Table(name = "\"stmperm\"")
public class Permiso implements Serializable {
    private static final long serialVersionUID = -5555987909802412680L;
    @Column(name = "desc_permiso", nullable = false)
    private String descripcionPermiso;
    @Column(name = "estado_registro", nullable = false)
    private String estadoRegistro;
    @Column(name = "habilidad", nullable = false)
    private String habilidad;
    @Column(name = "isNodo", nullable = false)
    private String isNodo;
    @Column(name = "nidPadre")
    private int nidPadre;
    @Id
    @Column(name = "nidPermiso", nullable = false)
    private int nidPermiso;
    @Column(name = "nivel", nullable = false)
    private int nivel;
    @Column(name = "url")
    private String url;
    @Column(name = "url_icono")
    private String urlIcono;
    @OneToMany(mappedBy = "permiso", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private List<RolPermiso> rolPermisoLista;
    @Column(name = "indMostrar")
    private String indMostrar;
    @Column(name = "accelerator")
    private String accelerator;
    @Column(name = "accessKey")
    private char accessKey;

    public Permiso() {
    }

    public Permiso(String descripcionPermiso, String estadoRegistro, String habilidad, String isNodo, int nidPadre,
                   int nidPermiso, byte nivel, String url, String urlIcono) {
        this.descripcionPermiso = descripcionPermiso;
        this.estadoRegistro = estadoRegistro;
        this.habilidad = habilidad;
        this.isNodo = isNodo;
        this.nidPadre = nidPadre;
        this.nidPermiso = nidPermiso;
        this.nivel = nivel;
        this.url = url;
        this.urlIcono = urlIcono;
    }

    public void setIndMostrar(String indMostrar) {
        this.indMostrar = indMostrar;
    }

    public String getIndMostrar() {
        return indMostrar;
    }

    public String getDescripcionPermiso() {
        return descripcionPermiso;
    }

    public void setDescripcionPermiso(String descripcionPermiso) {
        this.descripcionPermiso = descripcionPermiso;
    }

    public String getEstadoRegistro() {
        return estadoRegistro;
    }

    public void setEstadoRegistro(String estadoRegistro) {
        this.estadoRegistro = estadoRegistro;
    }

    public String getHabilidad() {
        return habilidad;
    }

    public void setHabilidad(String habilidad) {
        this.habilidad = habilidad;
    }

    public String getIsNodo() {
        return isNodo;
    }

    public void setIsNodo(String isNodo) {
        this.isNodo = isNodo;
    }

    public int getNidPadre() {
        return nidPadre;
    }

    public void setNidPadre(int nidPadre) {
        this.nidPadre = nidPadre;
    }

    public int getNidPermiso() {
        return nidPermiso;
    }

    public void setNidPermiso(int nidPermiso) {
        this.nidPermiso = nidPermiso;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrlIcono() {
        return urlIcono;
    }

    public void setUrlIcono(String urlIcono) {
        this.urlIcono = urlIcono;
    }

    public List<RolPermiso> getRolPermisoLista() {
        return rolPermisoLista;
    }

    public void setRolPermisoLista(List<RolPermiso> rolPermisoLista) {
        this.rolPermisoLista = rolPermisoLista;
    }

    public RolPermiso addRolPermiso(RolPermiso rolPermiso) {
        getRolPermisoLista().add(rolPermiso);
        rolPermiso.setPermiso(this);
        return rolPermiso;
    }

    public RolPermiso removeRolPermiso(RolPermiso rolPermiso) {
        getRolPermisoLista().remove(rolPermiso);
        rolPermiso.setPermiso(null);
        return rolPermiso;
    }

    public void setAccelerator(String accelerator) {
        this.accelerator = accelerator;
    }

    public String getAccelerator() {
        return accelerator;
    }

    public void setAccessKey(char accessKey) {
        this.accessKey = accessKey;
    }

    public char getAccessKey() {
        return accessKey;
    }

    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append(getClass().getName() + "@" + Integer.toHexString(hashCode()));
        buffer.append('[');
        buffer.append("descripcionPermiso=");
        buffer.append(getDescripcionPermiso());
        buffer.append(',');
        buffer.append("estadoRegistro=");
        buffer.append(getEstadoRegistro());
        buffer.append(',');
        buffer.append("habilidad=");
        buffer.append(getHabilidad());
        buffer.append(',');
        buffer.append("isNodo=");
        buffer.append(getIsNodo());
        buffer.append(',');
        buffer.append("nidPadre=");
        buffer.append(getNidPadre());
        buffer.append(',');
        buffer.append("nidPermiso=");
        buffer.append(getNidPermiso());
        buffer.append(',');
        buffer.append("nivel=");
        buffer.append(getNivel());
        buffer.append(',');
        buffer.append("url=");
        buffer.append(getUrl());
        buffer.append(',');
        buffer.append("urlIcono=");
        buffer.append(getUrlIcono());
        buffer.append(']');
        return buffer.toString();
    }
}
