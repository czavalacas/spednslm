package sped.vista.beans.seguridad;

import java.io.Serializable;

import java.util.Locale;

import javax.ejb.EJB;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.output.RichActiveOutputText;
import oracle.adf.view.rich.component.rich.output.RichMessages;

import oracle.adf.view.rich.event.DialogEvent;

import sped.negocio.LNSF.IL.LN_C_SFUsuarioLocal;
import sped.negocio.LNSF.IR.LN_C_SFCorreoRemote;
import sped.negocio.entidades.beans.BeanUsuario;

import sped.vista.Utils.Utils;

/** Clase de respaldo Login del JSF Frm_login, ejecuta la logica de negocio necesaria para autenticar y autorizar a los
 * usuarios en la aplicacion
 * @author dfloresgonz
 * @since 26.12.2013
 */
public class bLogin implements Serializable {
    @SuppressWarnings("compatibility:3148044224508277483")
    private static final long serialVersionUID = 1L;
    private String redireccionar = "";
    private String usuario;
    private String clave;
    private String correo;
    FacesContext ctx = FacesContext.getCurrentInstance();
    @EJB
    private LN_C_SFUsuarioLocal ln_C_SFUsuarioLocal;
    @EJB
    private LN_C_SFCorreoRemote ln_C_SFCorreoRemote;
    FacesContext contx = FacesContext.getCurrentInstance();
    private String msjError;
    private RichActiveOutputText otError;
    private String tituloPopup;
    private String mensajeCorreo;
    private RichPopup popMsj;

    public bLogin(){
        super();
       // Utils.sysout("locale1: "+Locale.getDefault());
        //Utils.sysout("locale2: "+contx.getViewRoot().getLocale());
        Locale.setDefault(contx.getViewRoot().getLocale());
        //Utils.sysout("locale3: "+Locale.getDefault());
    }

    public void autenticarUsuario(ActionEvent actionEvent) {
        BeanUsuario beanUsuario = ln_C_SFUsuarioLocal.autenticarUsuarioLN(getUsuario(),getClave());
        if(beanUsuario.getError() != null){
            if(beanUsuario.getError().getCidError().equals("000")){
                Utils.putSession("USER",beanUsuario);
                setRedireccionar("000");
            }else{
                setMsjError(beanUsuario.getError().getDescripcionError());
                Utils.addTarget(otError);
            }
        }else{
            setMsjError(beanUsuario.getError().getDescripcionError());
            Utils.addTarget(otError);
        }
    }
    
    public void recuperarClave(DialogEvent dialogEvent) {
        DialogEvent.Outcome outcome = dialogEvent.getOutcome();
        if(outcome == DialogEvent.Outcome.ok){
            String enviar = ln_C_SFCorreoRemote.recuperarClave(correo);
            if(enviar.equals("000")){
                setTituloPopup("Revisa tu correo");
                setMensajeCorreo("Te hemos enviado un correo con tu clave. Recuerda cambiarla por seguridad");
            }else{
                setTituloPopup("Ocurrio un error al enviar tu clave");
                setMensajeCorreo("Trata nuevamente o comunicate con el administrador del sistema.");
            }
            Utils.showPopUpMIDDLE(popMsj);
        }
    }

    public void setTituloPopup(String tituloPopup) {
        this.tituloPopup = tituloPopup;
    }

    public String getTituloPopup() {
        return tituloPopup;
    }

    public void setMensajeCorreo(String mensajeCorreo) {
        this.mensajeCorreo = mensajeCorreo;
    }

    public String getMensajeCorreo() {
        return mensajeCorreo;
    }

    public void setMsjError(String msjError) {
        this.msjError = msjError;
    }

    public String getMsjError() {
        return msjError;
    }

    public void setRedireccionar(String redireccionar) {
        this.redireccionar = redireccionar;
    }

    public String getRedireccionar() {
        return redireccionar;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getClave() {
        return clave;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getCorreo() {
        return correo;
    }

    public void setOtError(RichActiveOutputText otError) {
        this.otError = otError;
    }

    public RichActiveOutputText getOtError() {
        return otError;
    }

    public void setPopMsj(RichPopup popMsj) {
        this.popMsj = popMsj;
    }

    public RichPopup getPopMsj() {
        return popMsj;
    }
}
