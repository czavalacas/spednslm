package sped.negocio.entidades.beans;

import java.io.Serializable;

public class BeanProblemaProfesor implements Serializable {
    @SuppressWarnings("compatibility:-4203262855490582413")
    private static final long serialVersionUID = 1L;
    
    private String problema;
    private String profesor;
    
    public BeanProblemaProfesor(){
        
    }
    
    public BeanProblemaProfesor(String problema,String apellidos, String nombres){
        this.problema = problema;
        this.profesor = apellidos+" "+nombres;
    }

    public void setProblema(String problema) {
        this.problema = problema;
    }

    public String getProblema() {
        return problema;
    }

    public void setProfesor(String profesor) {
        this.profesor = profesor;
    }

    public String getProfesor() {
        return profesor;
    }
}
