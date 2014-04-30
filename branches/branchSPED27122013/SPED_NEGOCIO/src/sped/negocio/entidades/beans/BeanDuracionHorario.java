package sped.negocio.entidades.beans;

import java.io.Serializable;

import java.sql.Time;

/** Clase BeanDuracionHorario.java
 *  @author david
 *  @since 27.04.14
 */
public class BeanDuracionHorario implements Serializable {
    @SuppressWarnings("compatibility:-1503629546192991631")
    private static final long serialVersionUID = 1L;

    private Time duracion;
    private Time hora_inicio;
    private int nro_bloque;
    private int max_bloque;
    private int nidDura;
    private int nidNivel;
    private int nidSede;

    public BeanDuracionHorario() {
    }

    public void setDuracion(Time duracion) {
        this.duracion = duracion;
    }

    public Time getDuracion() {
        return duracion;
    }

    public void setHora_inicio(Time hora_inicio) {
        this.hora_inicio = hora_inicio;
    }

    public Time getHora_inicio() {
        return hora_inicio;
    }

    public void setMax_bloque(int max_bloque) {
        this.max_bloque = max_bloque;
    }

    public int getMax_bloque() {
        return max_bloque;
    }

    public void setNidDura(int nidDura) {
        this.nidDura = nidDura;
    }

    public int getNidDura() {
        return nidDura;
    }

    public void setNidNivel(int nidNivel) {
        this.nidNivel = nidNivel;
    }

    public int getNidNivel() {
        return nidNivel;
    }

    public void setNidSede(int nidSede) {
        this.nidSede = nidSede;
    }

    public int getNidSede() {
        return nidSede;
    }

    public void setNro_bloque(int nro_bloque) {
        this.nro_bloque = nro_bloque;
    }

    public int getNro_bloque() {
        return nro_bloque;
    }
}
