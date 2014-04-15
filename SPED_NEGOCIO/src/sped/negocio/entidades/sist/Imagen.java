package sped.negocio.entidades.sist;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@NamedQueries({ @NamedQuery(name = "Imagen.findAll", query = "select o from Imagen o") })
@Table(name = "\"imagen\"")
public class Imagen implements Serializable {
    private static final long serialVersionUID = -5846554581308242528L;
    @Column(name = "descripcion", nullable = false)
    private String descripcion;
    @Column(name = "foto", nullable = false)
    private byte[] foto;
    @Id
    @Column(name = "idImagen", nullable = false)
    private int idImagen;

    public Imagen() {
    }

    public Imagen(String descripcion, int idImagen) {
        this.descripcion = descripcion;
        this.idImagen = idImagen;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public int getIdImagen() {
        return idImagen;
    }

    public void setIdImagen(int idImagen) {
        this.idImagen = idImagen;
    }
}
