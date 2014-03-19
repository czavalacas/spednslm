package sped.vista.beans.administrativo.parte;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;

import oracle.adf.view.rich.component.rich.input.RichInputDate;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.input.RichSelectOneChoice;

import sped.negocio.LNSF.IL.LN_C_SFParteOcurrenciaLocal;
import sped.negocio.LNSF.IL.LN_C_SFUtilsLocal;

import sped.negocio.entidades.beans.BeanUsuario;

import sped.vista.Utils.Utils;

/** Clase de Frm_ParteOcurrencia.jsff
 * @author dfloresgonz
 * @since 18.03.2013
 */
public class bParteOcurrencia {
    
    private RichSelectOneChoice socSedesPO;
    private RichInputText itDocPO;
    private RichSelectOneChoice socProbPO;
    private RichInputDate idMinPO;
    private RichInputDate idMaxPO;
    private bSessionParteOcurrencia sessionParteOcurrencia;
    FacesContext ctx = FacesContext.getCurrentInstance();
    private BeanUsuario usuario = (BeanUsuario) Utils.getSession("USER");
    @EJB
    private LN_C_SFUtilsLocal ln_C_SFUtilsLocal;
    @EJB
    private LN_C_SFParteOcurrenciaLocal ln_C_SFParteOcurrenciaLocal;

    public bParteOcurrencia(){
        
    }
    
    @PostConstruct
    public void methodInvokeOncedOnPageLoad(){
        if (sessionParteOcurrencia.getExec() == 0){
            sessionParteOcurrencia.setExec(1);
            sessionParteOcurrencia.setLstSedes(Utils.llenarCombo(ln_C_SFUtilsLocal.getSedes_LN()));
            sessionParteOcurrencia.setLstProblemas(Utils.llenarCombo(ln_C_SFUtilsLocal.getProblemas_LN_WS()));
            if(usuario.getNidRol() == 4){
                sessionParteOcurrencia.setEnableSedes(false);
                sessionParteOcurrencia.setCidSedePO(String.valueOf(usuario.getNidRol()));
            }else{
                sessionParteOcurrencia.setEnableSedes(true);
            }
        }else{
            
        }
    }

    public void setUsuario(BeanUsuario usuario) {
        this.usuario = usuario;
    }

    public BeanUsuario getUsuario() {
        return usuario;
    }

    public void setSessionParteOcurrencia(bSessionParteOcurrencia sessionParteOcurrencia) {
        this.sessionParteOcurrencia = sessionParteOcurrencia;
    }

    public bSessionParteOcurrencia getSessionParteOcurrencia() {
        return sessionParteOcurrencia;
    }

    public void setSocSedesPO(RichSelectOneChoice socSedesPO) {
        this.socSedesPO = socSedesPO;
    }

    public RichSelectOneChoice getSocSedesPO() {
        return socSedesPO;
    }

    public void setItDocPO(RichInputText itDocPO) {
        this.itDocPO = itDocPO;
    }

    public RichInputText getItDocPO() {
        return itDocPO;
    }

    public void setSocProbPO(RichSelectOneChoice socProbPO) {
        this.socProbPO = socProbPO;
    }

    public RichSelectOneChoice getSocProbPO() {
        return socProbPO;
    }

    public void setIdMinPO(RichInputDate idMinPO) {
        this.idMinPO = idMinPO;
    }

    public RichInputDate getIdMinPO() {
        return idMinPO;
    }

    public void setIdMaxPO(RichInputDate idMaxPO) {
        this.idMaxPO = idMaxPO;
    }

    public RichInputDate getIdMaxPO() {
        return idMaxPO;
    }
}
