package mobile.beans;

import java.io.Serializable;

import java.sql.Date;
import java.sql.Timestamp;

public class BeanParteOcurrencia implements Serializable {
    private String comentario;
    private Date fechaRegistro;
    private String descProblema;
    private String nombresUsuario;
    private String profesor;
    private String curso;
    private String sede;
    private String areaAcademica;
    private String aula;
    //Nids
    private int nidUsuario;
    private int nidProblema;
    private long nidParte;
    private int nidMain;
    private String dniProfesor;
    private BeanPie lstPies[];//No se pone List<BeanPie> porque en movil no acepta
    private BeanBar lstBars[];

    public BeanParteOcurrencia(){
        
    }
    
    public BeanParteOcurrencia(String comentario,
                                Date fechaRegistro,
                                String descProblema,
                                String nombresUsuario,
                                String apellidos,
                                String nombres,
                                String curso,
                                String sede,
                                String areaAcademica,
                                String aula,
                                int nidUsuario,
                                int nidProblema,
                                long nidParte,
                                int nidMain,
                                String dniProfesor){
        this.comentario = comentario;
        this.fechaRegistro = fechaRegistro;
        this.descProblema = descProblema;
        this.nombresUsuario = nombresUsuario;
        this.profesor = apellidos +" "+nombres;
        this.curso = curso;
        this.sede = sede;
        this.areaAcademica = areaAcademica;
        this.aula = aula;
        this.nidUsuario = nidUsuario;
        this.nidProblema = nidProblema;
        this.nidParte = nidParte;
        this.nidMain = nidMain;
        this.dniProfesor = dniProfesor;
    }

    public void setDniProfesor(String dniProfesor) {
        this.dniProfesor = dniProfesor;
    }

    public String getDniProfesor() {
        return dniProfesor;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getComentario() {
        return comentario;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setDescProblema(String descProblema) {
        this.descProblema = descProblema;
    }

    public String getDescProblema() {
        return descProblema;
    }

    public void setNombresUsuario(String nombresUsuario) {
        this.nombresUsuario = nombresUsuario;
    }

    public String getNombresUsuario() {
        return nombresUsuario;
    }

    public void setProfesor(String profesor) {
        this.profesor = profesor;
    }

    public String getProfesor() {
        return profesor;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getCurso() {
        return curso;
    }

    public void setSede(String sede) {
        this.sede = sede;
    }

    public String getSede() {
        return sede;
    }

    public void setAreaAcademica(String areaAcademica) {
        this.areaAcademica = areaAcademica;
    }

    public String getAreaAcademica() {
        return areaAcademica;
    }

    public void setAula(String aula) {
        this.aula = aula;
    }

    public String getAula() {
        return aula;
    }

    public void setNidUsuario(int nidUsuario) {
        this.nidUsuario = nidUsuario;
    }

    public int getNidUsuario() {
        return nidUsuario;
    }

    public void setNidProblema(int nidProblema) {
        this.nidProblema = nidProblema;
    }

    public int getNidProblema() {
        return nidProblema;
    }

    public void setNidParte(long nidParte) {
        this.nidParte = nidParte;
    }

    public long getNidParte() {
        return nidParte;
    }

    public void setNidMain(int nidMain) {
        this.nidMain = nidMain;
    }

    public int getNidMain() {
        return nidMain;
    }

    public void setLstPies(BeanPie[] lstPies) {
        this.lstPies = lstPies;
    }

    public BeanPie[] getLstPies() {
        return lstPies;
    }

    public void setLstBars(BeanBar[] lstBars) {
        this.lstBars = lstBars;
    }

    public BeanBar[] getLstBars() {
        return lstBars;
    }
}