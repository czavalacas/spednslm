package sped.vista.beans.seguridad;

import java.io.IOException;
import java.io.Serializable;
import java.util.Locale;
import javax.ejb.EJB;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.output.RichActiveOutputText;
import oracle.adf.view.rich.event.DialogEvent;
import oracle.adf.view.rich.render.ClientEvent;
import sped.negocio.LNSF.IL.LN_C_SFUsuarioLocal;
import sped.negocio.LNSF.IL.LN_T_SFLogLocal;
import sped.negocio.LNSF.IL.LN_T_SFLoggerLocal;
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
    private RichInputText itUsuario;
    private RichInputText itClave;
    FacesContext ctx = FacesContext.getCurrentInstance();
    @EJB
    private LN_C_SFUsuarioLocal ln_C_SFUsuarioLocal;
    @EJB
    private LN_C_SFCorreoRemote ln_C_SFCorreoRemote;
    @EJB
    private LN_T_SFLogLocal ln_T_SFLogLocal;
    @EJB
    private LN_T_SFLoggerLocal ln_T_SFLoggerLocal;
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
    ExternalContext ectx = FacesContext.getCurrentInstance().getExternalContext();
    private String[] vecData = new String[8];
    private static final String CLASE = "sped.vista.beans.seguridad.bLogin";

    public bLogin(){
        super();
        if(beanUsuario != null){
            logoutTarget(LOGIN);
        }
        Locale.setDefault(contx.getViewRoot().getLocale());
    }

    public void autenticarUsuario(ActionEvent actionEvent) {
        try {
            if (getUsuario() == null || getClave() == null) {
                setMsjError("Ingrese su usuario y clave");
                Utils.addTarget(otError);
                return;
            }
            BeanUsuario beanUsuario = ln_C_SFUsuarioLocal.autenticarUsuarioLN(getUsuario(), getClave());
            if (beanUsuario.getError() != null) {
                if ("000".equals(beanUsuario.getError().getCidError())) {
                    beanUsuario.setNidLog(ln_T_SFLogLocal.grabarLogLogInWeb_LN(vecData, beanUsuario.getNidUsuario()));
                    Utils.putSession("USER", beanUsuario);
                    setRedireccionar("000");
                } else {
                    setMsjError(beanUsuario.getError().getDescripcionError());
                    Utils.addTarget(otError);
                    itClave.resetValue();
                    setClave(null);
                    Utils.addTarget(itClave);
                }
            } else {
                setMsjError(beanUsuario.getError().getDescripcionError());
                Utils.addTarget(otError);
                itClave.resetValue();
                setClave(null);
                Utils.addTarget(itClave);
            }
        } catch (Exception e) {
            ln_T_SFLoggerLocal.registrarLogErroresSistema(beanUsuario.getNidLog(), 
                                                          "BAC",
                                                          CLASE, 
                                                          "autenticarUsuario(ActionEvent actionEvent)",
                                                          "Error Inesperado Logear al usuario",Utils.getStack(e));
        }
    }
    
    public void recuperarClave(DialogEvent dialogEvent) {
        DialogEvent.Outcome outcome = dialogEvent.getOutcome();
        if(outcome == DialogEvent.Outcome.ok){
            String enviar = ln_C_SFCorreoRemote.recuperarClave(correo, 0, Utils.rutaImagenes());
            if("000".equals(enviar)){
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
            ln_T_SFLoggerLocal.registrarLogErroresSistema(beanUsuario.getNidLog(), 
                                                          "BAC",
                                                          CLASE, 
                                                          "logoutTarget(String aTarget)",
                                                          "Error Inesperado Cerrar Sesion",Utils.getStack(e));
        }
        return null;
    }
    
    public void traerData(ClientEvent ce){
        vecData[0] = (String) ce.getParameters().get("browser");
        vecData[1] = (String) ce.getParameters().get("os");
        vecData[2] = String.valueOf(ce.getParameters().get("altura"));
        vecData[3] = String.valueOf(ce.getParameters().get("anchura"));
        vecData[4] = String.valueOf(java.awt.Toolkit.getDefaultToolkit().getScreenSize().width);
        vecData[5] = String.valueOf(java.awt.Toolkit.getDefaultToolkit().getScreenSize().height);
        vecData[6] =  ((HttpServletRequest) ectx.getRequest()).getRemoteAddr();
        vecData[7] = (String) ce.getParameters().get("iplocal");
    }

    public void setVecData(String[] vecData) {
        this.vecData = vecData;
    }

    public String[] getVecData() {
        return vecData;
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

    public void setItUsuario(RichInputText itUsuario) {
        this.itUsuario = itUsuario;
    }

    public RichInputText getItUsuario() {
        return itUsuario;
    }

    public void setItClave(RichInputText itClave) {
        this.itClave = itClave;
    }

    public RichInputText getItClave() {
        return itClave;
    }
}
