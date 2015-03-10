package sped.negocio.entidades.beans;

import java.io.Serializable;

import java.sql.Time;

import java.util.List;

/** Clase BeanLeccion.java
 * @author dangeles
 * @since 15.08.2014
 */
public class BeanLeccion implements Serializable{
    @SuppressWarnings("compatibility:5676618496955297284")
    private static final long serialVersionUID = 1L;

    private int nidLecc;
    private int nidLeccRef;
    private String codigoDniAula;
    private String titulo;
    private BeanCurso curso;
    private BeanAula aula;
    private BeanProfesor profesor;
    private int nroHoras;
    private int nroHoras_aux;
    private int nroDuracion;
    private boolean update;
    private List<BeanLeccion> lstBeanLeccion;
    private List<String> lstErrores; 
    private int exec;
    
    public BeanLeccion(){} 
    
    public void setNroHoras(int nroHoras) {
        this.nroHoras = nroHoras;
    }

    public int getNroHoras() {
        return nroHoras;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setNroDuracion(int nroDuracion) {
        this.nroDuracion = nroDuracion;
    }

    public int getNroDuracion() {
        return nroDuracion;
    }

    public void setLstBeanLeccion(List<BeanLeccion> lstBeanLeccion) {
        this.lstBeanLeccion = lstBeanLeccion;
    }

    public List<BeanLeccion> getLstBeanLeccion() {
        return lstBeanLeccion;
    }

    public void setLstErrores(List<String> lstErrores) {
        this.lstErrores = lstErrores;
    }

    public List<String> getLstErrores() {
        return lstErrores;
    }

    public void setExec(int exec) {
        this.exec = exec;
    }

    public int getExec() {
        return exec;
    }

    public void setNroHoras_aux(int nroHoras_aux) {
        this.nroHoras_aux = nroHoras_aux;
    }

    public int getNroHoras_aux() {
        return nroHoras_aux;
    }   

    public void setUpdate(boolean update) {
        this.update = update;
    }

    public boolean isUpdate() {
        return update;
    }

    public void setNidLecc(int nidLecc) {
        this.nidLecc = nidLecc;
    }

    public int getNidLecc() {
        return nidLecc;
    }

    public void setCurso(BeanCurso curso) {
        this.curso = curso;
    }

    public BeanCurso getCurso() {
        return curso;
    }

    public void setAula(BeanAula aula) {
        this.aula = aula;
    }

    public BeanAula getAula() {
        return aula;
    }

    public void setProfesor(BeanProfesor profesor) {
        this.profesor = profesor;
    }

    public BeanProfesor getProfesor() {
        return profesor;
    }

    public void setCodigoDniAula(String codigoDniAula) {
        this.codigoDniAula = codigoDniAula;
    }

    public String getCodigoDniAula() {
        return codigoDniAula;
    }

    public void setNidLeccRef(int nidLeccRef) {
        this.nidLeccRef = nidLeccRef;
    }

    public int getNidLeccRef() {
        return nidLeccRef;
    }

}
