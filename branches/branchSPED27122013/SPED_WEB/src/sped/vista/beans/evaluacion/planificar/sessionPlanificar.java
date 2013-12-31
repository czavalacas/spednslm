package sped.vista.beans.evaluacion.planificar;

import java.util.List;
import sped.negocio.entidades.beans.BeanMain;
import sped.negocio.entidades.beans.BeanUsuario;

/** Clase de Sesion del Bean BeanMain.java
 * @author czavalacas
 * @since 29.12.2013
 */
public class sessionPlanificar {
   
    private List<BeanMain> listaHorarios;
    private BeanMain beanHorario;
    private int nidAula;
    private List<BeanUsuario> listaEvaluadores;
    private BeanUsuario beanUsuario;
    private int nidAreaAcademica;
    private int nidEvaluador;

    public void setListaHorarios(List<BeanMain> listaHorarios) {
        this.listaHorarios = listaHorarios;
    }

    public List<BeanMain> getListaHorarios() {
        return listaHorarios;
    }

    public void setBeanHorario(BeanMain beanHorario) {
        this.beanHorario = beanHorario;
    }

    public BeanMain getBeanHorario() {
        return beanHorario;
    }

    public void setNidAula(int nidAula) {
        this.nidAula = nidAula;
    }

    public int getNidAula() {
        return nidAula;
    }

    public void setListaEvaluadores(List<BeanUsuario> listaEvaluadores) {
        this.listaEvaluadores = listaEvaluadores;
    }

    public List<BeanUsuario> getListaEvaluadores() {
        return listaEvaluadores;
    }

    public void setNidAreaAcademica(int nidAreaAcademica) {
        this.nidAreaAcademica = nidAreaAcademica;
    }

    public int getNidAreaAcademica() {
        return nidAreaAcademica;
    }

    public void setNidEvaluador(int nidEvaluador) {
        this.nidEvaluador = nidEvaluador;
    }

    public int getNidEvaluador() {
        return nidEvaluador;
    }

    public void setBeanUsuario(BeanUsuario beanUsuario) {
        this.beanUsuario = beanUsuario;
    }

    public BeanUsuario getBeanUsuario() {
        return beanUsuario;
    }
}
