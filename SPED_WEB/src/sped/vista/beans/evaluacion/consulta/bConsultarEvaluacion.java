package sped.vista.beans.evaluacion.consulta;

import javax.annotation.PostConstruct;

import javax.ejb.EJB;

import javax.faces.event.ActionEvent;

import oracle.adf.view.rich.component.rich.data.RichTable;

import sped.negocio.LNSF.IR.LN_C_SFEvaluacionRemote;
import sped.negocio.entidades.beans.BeanUsuario;

import sped.vista.Utils.Utils;

public class bConsultarEvaluacion {
    
    private bSessionConsultarEvaluacion sessionConsultarEvaluacion;
    private BeanUsuario beanUsuario = (BeanUsuario) Utils.getSession("USER");
    @EJB
    private LN_C_SFEvaluacionRemote ln_C_SFEvaluacionRemote;
    private RichTable t1;

    public bConsultarEvaluacion() {
        
    }
    
    @PostConstruct
    public void methodInvokeOncedOnPageLoad() {
        if(sessionConsultarEvaluacion.getExec() == 0){
            System.out.println("ENTRO");
            sessionConsultarEvaluacion.setExec(1);
            sessionConsultarEvaluacion.setLstBeanEvaluacion(ln_C_SFEvaluacionRemote.getEvaluacionesByUsuarioLN(beanUsuario));
            System.out.println(sessionConsultarEvaluacion.getLstBeanEvaluacion().size());
        }
    }

    public void setSessionConsultarEvaluacion(bSessionConsultarEvaluacion sessionConsultarEvaluacion) {
        this.sessionConsultarEvaluacion = sessionConsultarEvaluacion;
    }

    public bSessionConsultarEvaluacion getSessionConsultarEvaluacion() {
        return sessionConsultarEvaluacion;
    }

    public void setT1(RichTable t1) {
        this.t1 = t1;
    }

    public RichTable getT1() {
        return t1;
    }
}
