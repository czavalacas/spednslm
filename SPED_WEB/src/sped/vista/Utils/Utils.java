package sped.vista.Utils;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.el.MethodExpression;
import javax.el.ValueExpression;

import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UISelectItems;
import javax.faces.context.FacesContext;
import javax.faces.el.MethodBinding;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import oracle.adf.model.BindingContext;
import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.data.RichTreeTable;
import oracle.adf.view.rich.component.rich.input.RichSelectOneChoice;
import oracle.adf.view.rich.component.rich.nav.RichCommandLink;
import oracle.adf.view.rich.component.rich.nav.RichCommandMenuItem;
import oracle.adf.view.rich.context.AdfFacesContext;

import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

import org.apache.myfaces.trinidad.event.SelectionEvent;
import org.apache.myfaces.trinidad.render.ExtendedRenderKitService;
import org.apache.myfaces.trinidad.util.Service;

import sped.negocio.entidades.beans.BeanCombo;
import sped.negocio.entidades.beans.BeanComboString;
import sped.negocio.entidades.beans.BeanUsuario;

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
    public static void mostrarMensaje(FacesContext ctx, String detalle, String summary, int severidad) {
        FacesMessage msg = new FacesMessage();
        switch (severidad) {
            case 1: msg.setSeverity(FacesMessage.SEVERITY_ERROR);break;
            case 2: msg.setSeverity(FacesMessage.SEVERITY_FATAL);break;
            case 3: msg.setSeverity(FacesMessage.SEVERITY_INFO); break;
            case 4: msg.setSeverity(FacesMessage.SEVERITY_WARN); break;
        }
        msg.setSummary(summary);
        msg.setDetail(detalle);
        ctx.addMessage(summary, msg);
    }

    public static Object getRowTable(SelectionEvent se) {
        RichTable t = (RichTable) se.getSource();
        return t.getSelectedRowData();
    }

    public static void sysout(Object o) {
        System.out.println(o);
    }

    public static void unselectFilas(RichTable tabla) {
        if (tabla != null) {
            if (tabla.getSelectedRowKeys() != null) {
                tabla.getSelectedRowKeys().removeAll();
                addTarget(tabla);
            }
        }
    }

    public static void unselectFilas(RichTreeTable tabla) {
        if (tabla != null) {
            if (tabla.getSelectedRowKeys() != null) {
                tabla.getSelectedRowKeys().removeAll();
                addTarget(tabla);
            }
        }
    }

    public static String getChoiceLabel(ValueChangeEvent vce) {
        String label = "";
        try {
            RichSelectOneChoice csoc = (RichSelectOneChoice) vce.getComponent();
            UISelectItems itms = (UISelectItems) csoc.getChildren().get(0);
            List listaRoles = (List) itms.getValue();
            if (listaRoles != null) {
                if (listaRoles.size() > 0) {
                    for (int i = 0; i < listaRoles.size(); i++) {
                        SelectItem selItm = (SelectItem) listaRoles.get(i);
                        if (((String) selItm.getValue()).equals((String) vce.getNewValue())) {
                            label = selItm.getLabel();
                            return label;
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return label;
    }

    public static void addTarget(javax.faces.component.UIComponent componente) {
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

    public static void addTargetMany(javax.faces.component.UIComponent... componente) {
        for (int i = 0; i < componente.length; i++) {
            addTarget(componente[i]);
        }
    }

    public static void putSession(String k, Object v) {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(k, v);
    }

    public static void removeSession(String k) {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove(k);
    }

    public static Object getSession(String k) {
        return FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(k);
    }

    public static void redireccionar(String url) {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect(url);
            FacesContext.getCurrentInstance().responseComplete();
        } catch (Exception e) {
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

    public static Object invokeEL(String el) {
        return invokeEL(el, new Class[0], new Object[0]);
    }

    public static Object invokeEL(String el, Class[] paramTypes, Object[] params) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ELContext elContext = facesContext.getELContext();
        ExpressionFactory expressionFactory = facesContext.getApplication().getExpressionFactory();
        MethodExpression exp = expressionFactory.createMethodExpression(elContext, el, Object.class, paramTypes);
        return exp.invoke(elContext, params);
    }

    public static ValueExpression createValueExpression(String expression) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        Application app = facesContext.getApplication();
        ExpressionFactory elFactory = app.getExpressionFactory();
        ELContext elContext = facesContext.getELContext();
        ValueExpression valueExp = elFactory.createValueExpression(elContext, expression, Object.class);
        return valueExp;
    }
    
    public static Date removeTime(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }
    
    public static void _redireccionar(FacesContext ctx,String taskFLowId){
        UIComponent component = findComponent(ctx.getViewRoot(),"menu19");
        RichCommandMenuItem rcl = (RichCommandMenuItem) component;
      //  rcl.setShortDesc(taskFLowId);
        putSession("url",taskFLowId);
        putSession("override","1");
        ActionEvent actionEvent = new ActionEvent(rcl);
        actionEvent.queue();
    }
    
    /**
     * FacesContext fctx = FacesContext.getCurrentInstance();
     * UIViewRoot root = fctx.getViewRoot();
     * UIComponent component = findComponent(fctx.getViewRoot(),clientId);
     * @param base
     * @param id
     * @return
     */
    public static UIComponent findComponent(UIComponent base, String id) {
        if (id.equals(base.getId()))
            return base;

        UIComponent children = null;
        UIComponent result = null;
        Iterator childrens = base.getFacetsAndChildren();
        while (childrens.hasNext() && (result == null)) {
            children = (UIComponent)childrens.next();
            if (id.equals(children.getId())) {
                result = children;
                break;
            }
            result = findComponent(children, id);
            if (result != null) {
                break;
            }
        }
        return result;
    }
    
    public static ArrayList llenarCombo(List<BeanCombo> lista) {
        ArrayList unItems = new ArrayList();
        for (BeanCombo c : lista) {      
            unItems.add(new SelectItem(c.getId().toString(), c.getDescripcion().toString()));
        }
        return unItems;
    }
    
    public static ArrayList llenarComboString(List<BeanComboString> lista) {
        ArrayList unItems = new ArrayList();
        for (BeanComboString c : lista) {      
            unItems.add(new SelectItem(c.getId().toString(), c.getDescripcion().toString()));
        }
        return unItems;
    }
    
    public static boolean hasPermiso(List<Integer> lstPermisos,Integer permiso){
        try {
            if (lstPermisos.contains(permiso)) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public static void llamarJavascript(String metodo){
        FacesContext fctx = FacesContext.getCurrentInstance();
        ExtendedRenderKitService erks = Service.getRenderKitService(fctx, ExtendedRenderKitService.class);
        erks.addScript(fctx, ""+metodo+"();");  
    }
    
    public static boolean isNumeric(String cadena) {
        try {
            Integer.parseInt(cadena);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }
    
    public static boolean validarExtensionXls(String nombreArchivo){
        String extension = nombreArchivo.substring(nombreArchivo.lastIndexOf(".") + 1, nombreArchivo.length());
        if(extension.equalsIgnoreCase("xls")){
            return true;
        }else{
            return false;
        }
    }
    
    public static List<SelectItem> llenarListItem(List lst){
        List<SelectItem> lstItems  = new ArrayList<SelectItem>();
        for(Object dato : lst){
            lstItems.add(new SelectItem(dato.toString()));
        }
        return lstItems;
    }
    
    public static List<SelectItem> getSuggestions(List<SelectItem> lstSI, String dato){
        int maxSuggestedItems = 5; //este dato debe ser = al del autoSuggest; sirve para validar lo necesario
        List<SelectItem> items = new ArrayList<SelectItem>();
        for(SelectItem item : lstSI){
            if(item.getLabel().toUpperCase().contains(dato.toUpperCase())){
                items.add(item);
            }
            if(items.size() == maxSuggestedItems){
                break;
            } 
        }
        return items;
    }
    
}
