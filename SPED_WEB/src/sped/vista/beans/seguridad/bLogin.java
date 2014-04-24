package sped.vista.beans.seguridad;

import java.io.IOException;
import java.io.Serializable;

import java.util.Locale;

import javax.ejb.EJB;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
    private RichPopup popMsj;
    private RichActiveOutputText otError;
    FacesContext ctx = FacesContext.getCurrentInstance();
    @EJB
    private LN_C_SFUsuarioLocal ln_C_SFUsuarioLocal;
    @EJB
    private LN_C_SFCorreoRemote ln_C_SFCorreoRemote;
    FacesContext contx = FacesContext.getCurrentInstance();
    private String msjError;
    private String redireccionar = "";
    private String usuario;
    private String clave;
    private String correo;
    private String tituloPopup;
    private String mensajeCorreo;
    private BeanUsuario beanUsuario = (BeanUsuario) Utils.getSession("USER");
    private final static String LOGIN = "/faces/Frm_login";

    public bLogin(){
        super();
        if(beanUsuario != null){
            logoutTarget(LOGIN);
        }
        Locale.setDefault(contx.getViewRoot().getLocale());
    }

    public void autenticarUsuario(ActionEvent actionEvent) {
        if(getUsuario() == null || getClave() == null){
            setMsjError("Ingrese su usuario y clave");
            Utils.addTarget(otError);
            return;
        }
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
            String enviar = ln_C_SFCorreoRemote.recuperarClave(correo, 0, Utils.rutaImagenes());
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

    public String logoutTarget(String aTarget) {
        ExternalContext ectx = FacesContext.getCurrentInstance().getExternalContext();
        HttpServletResponse response = (HttpServletResponse)ectx.getResponse();
        String url = ectx.getRequestContextPath() + aTarget;
        HttpSession session = (HttpSession)ectx.getSession(false);
        //close session
        session.invalidate();
        try {//@TODO log
            Utils.sysout("usuario cerro sesion");
            response.sendRedirect(url);
            FacesContext.getCurrentInstance().responseComplete();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
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
