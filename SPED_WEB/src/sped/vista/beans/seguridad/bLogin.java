package sped.vista.beans.seguridad;

import java.io.Serializable;

import javax.ejb.EJB;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import oracle.adf.view.rich.component.rich.output.RichMessages;

import sped.negocio.LNSF.IL.LN_C_SFUsuarioLocal;
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
    FacesContext ctx = FacesContext.getCurrentInstance();
    @EJB
    private LN_C_SFUsuarioLocal ln_C_SFUsuarioLocal;
    private RichMessages mensaje;

    public bLogin(){
        super();
    }

    public void autenticarUsuario(ActionEvent actionEvent) {
        BeanUsuario beanUsuario = ln_C_SFUsuarioLocal.autenticarUsuarioLN(getUsuario(),getClave());
        if(beanUsuario.getError() != null){
            if(beanUsuario.getError().getCidError().equals("000")){
                Utils.putSession("USER",beanUsuario);
                setRedireccionar("000");
            }else{
                mensaje.setText(beanUsuario.getError().getTituloError());
                Utils.addTarget(mensaje);
                Utils.mostrarMensaje(ctx,beanUsuario.getError().getDescripcionError(),beanUsuario.getError().getTituloError(),2);
            }
        }else{
            Utils.mostrarMensaje(ctx,"Error Inesperado","Comuniquese con el administrador",2);
        }
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

    public void setMensaje(RichMessages mensaje) {
        this.mensaje = mensaje;
    }

    public RichMessages getMensaje() {
        return mensaje;
    }
}
