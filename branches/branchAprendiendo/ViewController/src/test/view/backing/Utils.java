package test.view.backing;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.context.AdfFacesContext;

public class Utils {
    
    public static String showPopUpMIDDLE(RichPopup p) {
        try {
            RichPopup.PopupHints ph = new RichPopup.PopupHints();
            p.show(ph);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public static void depurar(Object o){
        System.out.println(o);
    }
    
    public static void addTarget(javax.faces.component.UIComponent componente){
        AdfFacesContext.getCurrentInstance().addPartialTarget(componente);
    }
    
    public static void addTargetMany(javax.faces.component.UIComponent ... componente){
        for(int i = 0; i < componente.length; i++){
            addTarget(componente[i]);
        }
    }
    
    public static void unselectFilas(RichTable tabla){
        if(tabla != null){
            if(tabla.getSelectedRowKeys() != null ){
                tabla.getSelectedRowKeys().removeAll();
                addTarget(tabla);
            }
        }
    }
    
    /**
     *
     * @author dfloresgonz
     * @param ctx facesContext: FacesContext ctx = FacesContext.getCurrentInstance();
     * @param error Texto a mostrar
     * @param severidad SEVERITY_ERROR = 1,SEVERITY_FATAL = 2, SEVERITY_INFO = 3, SEVERITY_WARN = 4
     */
    public static void throwError_Aux(FacesContext ctx, 
                                     String error,
                                     String titulo,
                                     int severidad) {
        FacesMessage msg = null;
        switch(severidad){
            case 1 : msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, error, titulo);break;
            case 2 : msg = new FacesMessage(FacesMessage.SEVERITY_FATAL, error, titulo);break;
            case 3 : msg = new FacesMessage(FacesMessage.SEVERITY_INFO, error, titulo);break;
            case 4 : msg = new FacesMessage(FacesMessage.SEVERITY_WARN, error, titulo);break;
        }
        msg.setSummary(error);
        ctx.addMessage(null, msg);
    }
}
