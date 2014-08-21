package sped.negocio.entidades.eval;

import java.io.Serializable;

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
import javax.persistence.Table;
import javax.persistence.TableGenerator;

@Entity
@NamedQueries({ @NamedQuery(name = "CriterioValor.findAll", query = "select o from CriterioValor o") })
@Table(name = "\"ecrival\"")
public class CriterioValor implements Serializable {
    @Column(name = "desc_valor")
    private String descripcionValor;
    @Column(name = "idValoracion", nullable = false)
    private int idValoracion;
    /*@Column(name = "nidCriterio", nullable = false)
    private int nidCriterio;*/
    @Id
    @TableGenerator( name = "stmcodi.ecrival", table = "stmcodi", pkColumnName = "APP_SEQ_NAME", pkColumnValue = "ecrival.nidCriterioValor", valueColumnName = "APP_SEQ_VALUE", initialValue = 1, allocationSize = 1 )
    @GeneratedValue( strategy = GenerationType.TABLE, generator = "stmcodi.ecrival" )
    @Column(name = "nidCriterioValor", nullable = false)
    private int nidCriterioValor;
    /*@Column(name = "nidFicha", nullable = false)
    private int nidFicha;*/
    @Column(name = "valor")
    private double valor;
    @ManyToOne
    @JoinColumns({
                 @JoinColumn(name = "nidFicha", referencedColumnName = "nidFicha"),
                 @JoinColumn(name = "nidCriterio", referencedColumnName = "nidCriterio")
        })
    private FichaCriterio fichaCriterio;
    @Column(name = "orden", nullable = false)
    private Integer orden;

    public CriterioValor() {
    }

    public CriterioValor(String descripcionValor, int idValoracion, int nidCriterioValor,
                         double valor) {
        this.descripcionValor = descripcionValor;
        this.idValoracion = idValoracion;
        //this.nidCriterio = nidCriterio;
        this.nidCriterioValor = nidCriterioValor;
        //this.nidFicha = nidFicha;
        this.valor = valor;
    }

    public void setOrden(Integer orden) {
        this.orden = orden;
    }

    public Integer getOrden() {
        return orden;
    }

    public void setFichaCriterio(FichaCriterio fichaCriterio) {
        this.fichaCriterio = fichaCriterio;
    }

    public FichaCriterio getFichaCriterio() {
        return fichaCriterio;
    }

    public String getDescripcionValor() {
        return descripcionValor;
    }

    public void setDescripcionValor(String descripcionValor) {
        this.descripcionValor = descripcionValor;
    }

    public int getIdValoracion() {
        return idValoracion;
    }

    public void setIdValoracion(int idValoracion) {
        this.idValoracion = idValoracion;
    }

    /*public int getNidCriterio() {
        return nidCriterio;
    }

    public void setNidCriterio(int nidCriterio) {
        this.nidCriterio = nidCriterio;
    }*/

    public int getNidCriterioValor() {
        return nidCriterioValor;
    }

    public void setNidCriterioValor(int nidCriterioValor) {
        this.nidCriterioValor = nidCriterioValor;
    }
/*
    public int getNidFicha() {
        return nidFicha;
    }

    public void setNidFicha(int nidFicha) {
        this.nidFicha = nidFicha;
    }*/

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
}