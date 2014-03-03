package mobile.respaldo.parteocu;

import java.sql.Date;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.el.ValueExpression;

import mobile.AdfmUtils;

import mobile.beans.BeanCombo;

import oracle.adfmf.amx.event.ActionEvent;
import oracle.adfmf.amx.event.ValueChangeEvent;
import oracle.adfmf.bindings.OperationBinding;
import oracle.adfmf.bindings.dbf.AmxIteratorBinding;
import oracle.adfmf.framework.api.AdfmfJavaUtilities;
import oracle.adfmf.framework.api.GenericTypeBeanSerializationHelper;
import oracle.adfmf.framework.exception.AdfInvocationException;
import oracle.adfmf.framework.model.AdfELContext;
import oracle.adfmf.javax.faces.model.SelectItem;
import oracle.adfmf.util.GenericType;

public class bConsultar_PO {
    
    private List listUsuarios;
    private AdfELContext adfELContext = AdfmfJavaUtilities.getAdfELContext();
    private final static String WEBSERVICE_NAME = "WS_SPED";
    private final static String FEATURE = "MiApp";
    private final static String ALERTA = "mostrarMensaje";
    private final static String METODO = "#{bindings.getListaPartesOcurrencia_WS}";
    private final static String METODO_ITERATOR = "#{bindings.getListaPartesOcurrencia_WSIterator}";
    private Date hoy;
    
    public bConsultar_PO(){
        this.setListUsuarios(this.llenarUsuarios());
    }
    
    public void cambioEnSede(ValueChangeEvent vce) {
        actualizarSegunSOC("arg3", vce);
    }
    
    public void cambioEnUsuario(ValueChangeEvent vce) {
        actualizarSegunSOC("arg5", vce);
    }
    
    public void actualizarSegunSOC(String argumento, ValueChangeEvent vce) {
        ValueExpression ve = (ValueExpression)AdfmfJavaUtilities.getValueExpression(METODO, Object.class);
        OperationBinding method = (OperationBinding)ve.getValue(AdfmfJavaUtilities.getAdfELContext());
        method.getParamsMap().put(argumento, vce.getNewValue());
        method.execute();
        ValueExpression veIter = (ValueExpression)AdfmfJavaUtilities.getValueExpression(METODO_ITERATOR,Object.class);
        AmxIteratorBinding iteratorBinding = (AmxIteratorBinding)veIter.getValue(AdfmfJavaUtilities.getAdfELContext());
        iteratorBinding.getIterator().refresh();
    }
    
    public void refrescarBusqueda(ActionEvent actionEvent) {
        ValueExpression ve = (ValueExpression)AdfmfJavaUtilities.getValueExpression(METODO, Object.class);
        OperationBinding method = (OperationBinding)ve.getValue(AdfmfJavaUtilities.getAdfELContext());
        ValueExpression ve1 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.usuario.rol.nidRol}", Integer.class);
        Integer rol = (Integer)ve1.getValue(adfELContext);

        ValueExpression ve2 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.usuario.sedeNivel.sede.nidSede}", Integer.class);
        Integer sede = (Integer)ve2.getValue(adfELContext);
        
        ValueExpression ve3 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.usuario.nidUsuario}", Integer.class);
        Integer usuario = (Integer)ve3.getValue(adfELContext);
        
        AdfmfJavaUtilities.setELValue("#{bindings.arg0.inputValue}", null);
        method.getParamsMap().put("arg0",null);
        AdfmfJavaUtilities.setELValue("#{bindings.arg1.inputValue}", null);
        method.getParamsMap().put("arg1",null);
        AdfmfJavaUtilities.setELValue("#{bindings.arg2.inputValue}", null);
        method.getParamsMap().put("arg2",null);
        AdfmfJavaUtilities.setELValue("#{bindings.arg3.inputValue}", null);
        method.getParamsMap().put("arg3",null);
        if(rol.intValue() == 1 || rol.intValue() == 5){
            AdfmfJavaUtilities.setELValue("#{bindings.arg4.inputValue}", null);
            method.getParamsMap().put("arg4", null);
        }else{
            AdfmfJavaUtilities.setELValue("#{bindings.arg4.inputValue}", null);
            method.getParamsMap().put("arg4",sede);
        }
        if(rol.intValue() == 1 || rol.intValue() == 5){
            AdfmfJavaUtilities.setELValue("#{bindings.arg5.inputValue}", null);
            method.getParamsMap().put("arg4", null);
        }else{
            AdfmfJavaUtilities.setELValue("#{bindings.arg5.inputValue}", null);
            method.getParamsMap().put("arg4",usuario);
        }

        method.execute();
        ValueExpression veIter = (ValueExpression)AdfmfJavaUtilities.getValueExpression(METODO_ITERATOR,Object.class);
        AmxIteratorBinding iteratorBinding = (AmxIteratorBinding)veIter.getValue(AdfmfJavaUtilities.getAdfELContext());
        iteratorBinding.getIterator().refresh();
    }
    
    public ArrayList llenarUsuarios() {
        ArrayList ususItems = new ArrayList();
        List lstUsus = getUsus_WEBSERVICE("getUsuarios_LN_WS");
        if(lstUsus != null){
            for (int i = 0; i < lstUsus.size(); i++) {//en el 1.4 no se permite usar el foreach
                BeanCombo r = (BeanCombo) lstUsus.get(i);
                ususItems.add(new SelectItem(r.getId().toString(), 
                                             r.getDescripcion().toString()));
            }
        }
        return ususItems;
    }
    
    public List getUsus_WEBSERVICE(String metodo){
        GenericType result;
        List probsWS = new ArrayList();//en el 1.4 no se permite usar List<Objecto>
        List nullParams = new ArrayList();
        try {
            result = (GenericType)AdfmfJavaUtilities.invokeDataControlMethod(WEBSERVICE_NAME, //Nombre del WS (definido cuando se creo el datacontrol
                                                                             null,
                                                                             metodo, //nombre del metodo ver el datacontrol MethodName
                                                                             nullParams,
                                                                             nullParams,
                                                                             nullParams);
            for (int i = 0; i < result.getAttributeCount(); i++) {
                GenericType row = (GenericType)result.getAttribute(i);
                BeanCombo beanCombo = (BeanCombo)GenericTypeBeanSerializationHelper.fromGenericType(BeanCombo.class,row);
                probsWS.add(beanCombo);
            }
        } catch (AdfInvocationException ex) {
            if (AdfInvocationException.CATEGORY_WEBSERVICE.compareTo(ex.getErrorCategory()) == 0) {
                //throw new AdfException("Hubo un error al iniciar la Aplicaci�n", AdfException.WARNING);
                AdfmUtils.alert(FEATURE,
                                ALERTA,
                                new Object[] {"m_0001"});
            }
            return null;
        } catch(Exception e){
            e.printStackTrace();
            AdfmUtils.alert(FEATURE,
                            ALERTA,
                            new Object[] {"m_0001"});
        }
        return probsWS;
    }
    
    public void setListUsuarios(List listUsuarios) {
        this.listUsuarios = listUsuarios;
    }

    public List getListUsuarios() {
        return listUsuarios;
    }

    public void setAdfELContext(AdfELContext adfELContext) {
        this.adfELContext = adfELContext;
    }

    public AdfELContext getAdfELContext() {
        return adfELContext;
    }

    public void setHoy(Date hoy) {
        this.hoy = hoy;
    }

    public Date getHoy() {
        java.util.Calendar cal = java.util.Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        java.util.Date utilDate = cal.getTime();
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        hoy = sqlDate;
        return hoy;
    }
}
