package mobile.beans;

import java.io.Serializable;

public class BeanEvaluacionWS implements Serializable {
    
    private String valores;
    private String comentarioEvaluador;
    private String comentarioProfesor;
    private BeanCriterio[] criterios;

    public BeanEvaluacionWS(){
        
    }

    public BeanEvaluacionWS(String valores,String comentarioEvaluador,String comentarioProfesor,BeanCriterio[] criterios){
        this.valores = valores;
        this.comentarioEvaluador = comentarioEvaluador;
        this.comentarioProfesor = comentarioProfesor;
        this.criterios = criterios;
    }
    
    public void setValores(String valores) {
        this.valores = valores;
    }

    public String getValores() {
        return valores;
    }

    public void setComentarioEvaluador(String comentarioEvaluador) {
        this.comentarioEvaluador = comentarioEvaluador;
    }

    public String getComentarioEvaluador() {
        return comentarioEvaluador;
    }

    public void setComentarioProfesor(String comentarioProfesor) {
        this.comentarioProfesor = comentarioProfesor;
    }

    public String getComentarioProfesor() {
        return comentarioProfesor;
    }

    public void setCriterios(BeanCriterio[] criterios) {
        this.criterios = criterios;
    }

    public BeanCriterio[] getCriterios() {
        return criterios;
    }
}
