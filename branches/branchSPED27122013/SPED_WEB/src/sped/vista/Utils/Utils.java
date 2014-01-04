package sped.vista.Utils;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.el.MethodBinding;
import javax.faces.event.ActionEvent;

import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.context.AdfFacesContext;

/** Clase Utils contiene metodos reutilizables
 * @author dfloresgonz
 * @since 26.12.2013
 */
public class Utils {

    /**
     *
     * @author dfloresgonz
     * @param ctx facesContext: FacesContext ctx = FacesContext.getCurrentInstance();
     * @param detalle Texto a mostrar
     * @param summary Resumen (titulo)
     * @param severidad SEVERITY_ERROR = 1,SEVERITY_FATAL = 2, SEVERITY_INFO = 3, SEVERITY_WARN = 4
     */
    public static void mostrarMensaje(FacesContext ctx, 
                                     String detalle,
                                     String summary,
                                     int severidad) {
        FacesMessage msg = new FacesMessage();
        switch(severidad){
            case 1 : msg.setSeverity(FacesMessage.SEVERITY_ERROR);break;
            case 2 : msg.setSeverity(FacesMessage.SEVERITY_FATAL);break;
            case 3 : msg.setSeverity(FacesMessage.SEVERITY_INFO);break;
            case 4 : msg.setSeverity(FacesMessage.SEVERITY_WARN);break;
        }
        msg.setSummary(summary);
        msg.setDetail(detalle);
        ctx.addMessage(summary, msg);
    }
    
    public static void sysout(Object o){
        System.out.println(o);
    }
    
    public static void addTarget(javax.faces.component.UIComponent componente){
        AdfFacesContext.getCurrentInstance().addPartialTarget(componente);
    }
    
    public static String showPopUpMIDDLE(RichPopup p) {
        try {
            RichPopup.PopupHints ph = new RichPopup.PopupHints();
            p.show(ph);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public static void addTargetMany(javax.faces.component.UIComponent ... componente){
        for(int i = 0; i < componente.length; i++){
            addTarget(componente[i]);
        }
    }
    
    public static void putSession(String k, Object v) {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(k, v);
    }
    
    public static void removeSession(String k){
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove(k);
    }
    
    public static Object getSession(String k){
        return FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(k);
    }
    
    public static void redireccionar(String url){
        try{
            FacesContext.getCurrentInstance().getExternalContext().redirect(url);
            FacesContext.getCurrentInstance().responseComplete();
        }catch(Exception e){
            e.printStackTrace();
            e.getMessage();
        }
    }

    public static MethodBinding createActionListenerMethodBinding(String actionListenerString) {
        Class args[] = { ActionEvent.class };
        MethodBinding methodBinding = null;
        methodBinding =
            FacesContext.getCurrentInstance().getApplication().createMethodBinding(actionListenerString, args);
        return methodBinding;
    }

    public static MethodBinding createActionMethodBinding(String action) {
        Class args[] = { };
        MethodBinding methodBinding = null;
        methodBinding = FacesContext.getCurrentInstance().getApplication().createMethodBinding(action, args);
        return methodBinding;
    }
    public static void unselectFilas(RichTable tabla){
        if(tabla != null){
            if(tabla.getSelectedRowKeys() != null ){
                tabla.getSelectedRowKeys().removeAll();
                addTarget(tabla);
            }
        }
    }
}