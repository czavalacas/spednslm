package sped.negocio.entidades.beans;

import java.io.Serializable;

import java.util.Date;
import java.util.List;

public class BeanEvaluacion implements Serializable {
    @SuppressWarnings("compatibility:-9034418511481595866")
    private static final long serialVersionUID = 1L;
    private String descripcion;    
        private Date endDate;    
        private String estadoEvaluacion; 
        private String nidDate;
        private int nidEvaluacion;    
        private int nidEvaluador;    
        private Date startDate;      
        private BeanMain main;
        private List<BeanResultado> resultadoLista;


    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEstadoEvaluacion(String estadoEvaluacion) {
        this.estadoEvaluacion = estadoEvaluacion;
    }

    public String getEstadoEvaluacion() {
        return estadoEvaluacion;
    }

    public void setNidDate(String nidDate) {
        this.nidDate = nidDate;
    }

    public String getNidDate() {
        return nidDate;
    }

    public void setNidEvaluacion(int nidEvaluacion) {
        this.nidEvaluacion = nidEvaluacion;
    }

    public int getNidEvaluacion() {
        return nidEvaluacion;
    }

    public void setNidEvaluador(int nidEvaluador) {
        this.nidEvaluador = nidEvaluador;
    }

    public int getNidEvaluador() {
        return nidEvaluador;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setMain(BeanMain main) {
        this.main = main;
    }

    public BeanMain getMain() {
        return main;
    }

    public void setResultadoLista(List<BeanResultado> resultadoLista) {
        this.resultadoLista = resultadoLista;
    }

    public List<BeanResultado> getResultadoLista() {
        return resultadoLista;
    }
}
