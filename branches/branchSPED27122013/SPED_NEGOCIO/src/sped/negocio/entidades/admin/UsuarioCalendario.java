package sped.negocio.entidades.admin;

import java.io.Serializable;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "\"addusca\"")
@IdClass(UsuarioCalendarioPK.class)
public class UsuarioCalendario implements Serializable {
    private static final long serialVersionUID = -2827037309783035547L;
    @Column(name = "desc_diaLibre")
    private String descripcionDiaLibre;
    @Id
    @Temporal(TemporalType.DATE)
    @Column(name = "pk_fecha", nullable = false)
    private Date nidFecha;
    @Id
    @Column(name = "nidUsuario", nullable = false)
    private int nidUsuario;
    @Column(name = "tipoFalta", nullable = false)
    private String tipoFalta;
    @Column(name = "estilo")
    private String estilo;

    public UsuarioCalendario() {
    }

    public UsuarioCalendario(String descripcionDiaLibre, Date nidFecha, int nidUsuario, String tipoFalta,
                             String estilo) {
        this.descripcionDiaLibre = descripcionDiaLibre;
        this.nidFecha = nidFecha;
        this.nidUsuario = nidUsuario;
        this.tipoFalta = tipoFalta;
        this.estilo = estilo;
    }

    public void setEstilo(String estilo) {
        this.estilo = estilo;
    }

    public String getEstilo() {
        return estilo;
    }

    public String getDescripcionDiaLibre() {
        return descripcionDiaLibre;
    }

    public void setDescripcionDiaLibre(String descripcionDiaLibre) {
        this.descripcionDiaLibre = descripcionDiaLibre;
    }

    public Date getNidFecha() {
        return nidFecha;
    }

    public void setNidFecha(Date nidFecha) {
        this.nidFecha = nidFecha;
    }

    public int getNidUsuario() {
        return nidUsuario;
    }

    public void setNidUsuario(int nidUsuario) {
        this.nidUsuario = nidUsuario;
    }

    public String getTipoFalta() {
        return tipoFalta;
    }

    public void setTipoFalta(String tipoFalta) {
        this.tipoFalta = tipoFalta;
    }
}
