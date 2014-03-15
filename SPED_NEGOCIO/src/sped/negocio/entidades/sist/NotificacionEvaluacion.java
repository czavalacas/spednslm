package sped.negocio.entidades.sist;

import java.io.Serializable;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@NamedQueries({
              @NamedQuery(name = "NotificacionEvaluacion.findAll", query = "select o from NotificacionEvaluacion o") })
@Table(name = "\"stmnoti\"")
public class NotificacionEvaluacion implements Serializable {
    private static final long serialVersionUID = -1041479516917624244L;
    @Id
    @Column(name = "cidNotificacion", nullable = false)
    private String cidNotificacion;
    @Column(name = "fechaLeido")
    private Timestamp fechaLeido;
    @Column(name = "fechaRegistro", nullable = false)
    private Timestamp fechaRegistro;
    @Column(name = "leido", nullable = false)
    private String leido;
    @Column(name = "nidCriterioIndicador", nullable = false)
    private int nidCriterioIndicador;
    @Column(name = "nidEvaluacion", nullable = false)
    private int nidEvaluacion;
    @Column(name = "nidUsuario", nullable = false)
    private int nidUsuario;
    @Column(name = "valor")
    private short valor;
    @Column(name = "notaVigecimal")
    private double notaVigecimal;
    
    public NotificacionEvaluacion() {
    }

    public NotificacionEvaluacion(String cidNotificacion, Timestamp fechaLeido, Timestamp fechaRegistro, String leido,
                                  int nidCriterioIndicador, int nidEvaluacion, int nidUsuario) {
        this.cidNotificacion = cidNotificacion;
        this.fechaLeido = fechaLeido;
        this.fechaRegistro = fechaRegistro;
        this.leido = leido;
        this.nidCriterioIndicador = nidCriterioIndicador;
        this.nidEvaluacion = nidEvaluacion;
        this.nidUsuario = nidUsuario;
    }

    public void setValor(short valor) {
        this.valor = valor;
    }

    public short getValor() {
        return valor;
    }

    public void setNotaVigecimal(double notaVigecimal) {
        this.notaVigecimal = notaVigecimal;
    }

    public double getNotaVigecimal() {
        return notaVigecimal;
    }

    public String getCidNotificacion() {
        return cidNotificacion;
    }

    public void setCidNotificacion(String cidNotificacion) {
        this.cidNotificacion = cidNotificacion;
    }

    public Timestamp getFechaLeido() {
        return fechaLeido;
    }

    public void setFechaLeido(Timestamp fechaLeido) {
        this.fechaLeido = fechaLeido;
    }

    public Timestamp getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Timestamp fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getLeido() {
        return leido;
    }

    public void setLeido(String leido) {
        this.leido = leido;
    }

    public int getNidCriterioIndicador() {
        return nidCriterioIndicador;
    }

    public void setNidCriterioIndicador(int nidCriterioIndicador) {
        this.nidCriterioIndicador = nidCriterioIndicador;
    }

    public int getNidEvaluacion() {
        return nidEvaluacion;
    }

    public void setNidEvaluacion(int nidEvaluacion) {
        this.nidEvaluacion = nidEvaluacion;
    }

    public int getNidUsuario() {
        return nidUsuario;
    }

    public void setNidUsuario(int nidUsuario) {
        this.nidUsuario = nidUsuario;
    }
}
