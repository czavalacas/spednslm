package sped.negocio.entidades.admin;

import java.io.Serializable;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import javax.persistence.TableGenerator;

import sped.negocio.entidades.sist.Rol;
import sped.negocio.entidades.sist.UsuarioPermiso;

@Entity
@NamedQueries({ @NamedQuery(name = "Usuario.findAll", query = "select o from Usuario o") })
@Table(name = "\"admusua\"")
public class Usuario implements Serializable {
    private static final long serialVersionUID = 662411220815945263L;
    @Column(name = "clave")
    private String clave;
    @Column(name = "dni")
    private String dni;
    @Column(name = "estado_usuario")
    private String estadoUsuario;
    @Column(name = "foto")
    private byte[] foto;
    @Id
    @Column(name = "nidUsuario", nullable = false)
    @TableGenerator( name = "stmcodi_admusua", table = "stmcodi", pkColumnName = "APP_SEQ_NAME", pkColumnValue = "admusua.nidUsuario", valueColumnName = "APP_SEQ_VALUE", initialValue = 3, allocationSize = 1 )
    @GeneratedValue( strategy = GenerationType.TABLE, generator = "stmcodi_admusua" )
    private int nidUsuario;
    @Column(name = "nombres")
    private String nombres;
    @Column(name = "usuario")
    private String usuario;
    @ManyToOne
    @JoinColumn(name = "nidRol")
    private Rol rol;
    @ManyToOne
    @JoinColumn(name = "nidSede")
    private Sede sede;
    @ManyToOne
    @JoinColumn(name = "nidAreaAcademica")
    private AreaAcademica areaAcademica;
    @OneToMany(mappedBy = "usuario", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private List<UsuarioPermiso> usuarioPermisosLista;
    @Column(name = "correo")
    private String correo;
    @Column(name = "isNuevo")
    private String isNuevo;
    @Column(name = "isSupervisor")
    private String isSupervisor;

    public Usuario() {
    }

    public Usuario(String clave, String dni, String estadoUsuario, AreaAcademica areaAcademica, Rol rol,
                   Sede sede, int nidUsuario, String nombres, String usuario, String isNuevo, String isSupervisor) {
        this.clave = clave;
        this.dni = dni;
        this.estadoUsuario = estadoUsuario;
        this.areaAcademica = areaAcademica;
        this.rol = rol;
        this.sede = sede;
        this.nidUsuario = nidUsuario;
        this.nombres = nombres;
        this.usuario = usuario;
        this.isNuevo = isNuevo;
        this.isSupervisor = isSupervisor;
    }

    public void setUsuarioPermisosLista(List<UsuarioPermiso> usuarioPermisosLista) {
        this.usuarioPermisosLista = usuarioPermisosLista;
    }

    public List<UsuarioPermiso> getUsuarioPermisosLista() {
        return usuarioPermisosLista;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getEstadoUsuario() {
        return estadoUsuario;
    }

    public void setEstadoUsuario(String estadoUsuario) {
        this.estadoUsuario = estadoUsuario;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }


    public int getNidUsuario() {
        return nidUsuario;
    }

    public void setNidUsuario(int nidUsuario) {
        this.nidUsuario = nidUsuario;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public void setSede(Sede sede) {
        this.sede = sede;
    }

    public Sede getSede() {
        return sede;
    }

    public AreaAcademica getAreaAcademica() {
        return areaAcademica;
    }

    public void setAreaAcademica(AreaAcademica areaAcademica) {
        this.areaAcademica = areaAcademica;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getCorreo() {
        return correo;
    }

    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append(getClass().getName() + "@" + Integer.toHexString(hashCode()));
        buffer.append('[');
        buffer.append("clave=");
        buffer.append(getClave());
        buffer.append(',');
        buffer.append("dni=");
        buffer.append(getDni());
        buffer.append(',');
        buffer.append("estadoUsuario=");
        buffer.append(getEstadoUsuario());
        buffer.append(',');
        buffer.append("foto=");
        buffer.append(getFoto());
        buffer.append(',');
        buffer.append("nidUsuario=");
        buffer.append(getNidUsuario());
        buffer.append(',');
        buffer.append("nombres=");
        buffer.append(getNombres());
        buffer.append(',');
        buffer.append("usuario=");
        buffer.append(getUsuario());
        buffer.append(']');
        return buffer.toString();
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
