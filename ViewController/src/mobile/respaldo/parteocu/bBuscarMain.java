package mobile.respaldo.parteocu;

import javax.el.ValueExpression;

import oracle.adfmf.amx.event.ActionEvent;
import oracle.adfmf.amx.event.ValueChangeEvent;
import oracle.adfmf.bindings.OperationBinding;
import oracle.adfmf.bindings.dbf.AmxIteratorBinding;
import oracle.adfmf.framework.api.AdfmfJavaUtilities;
import oracle.adfmf.framework.model.AdfELContext;

public class bBuscarMain {
    
    private AdfELContext adfELContext = AdfmfJavaUtilities.getAdfELContext();
    private final static String METODO = "#{bindings.getMainByAttr_WS}";
    private final static String METODO_ITERATOR = "#{bindings.getMainByAttr_WSIterator}";
    
    public bBuscarMain() {
    }

    public void cambioEnSede(ValueChangeEvent vce) {
        actualizarSegunSOC("arg0", vce);
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
        
        if(rol.intValue() == 1 || rol.intValue() == 2 || rol.intValue() == 5){
            AdfmfJavaUtilities.setELValue("#{bindings.arg0.inputValue}", null);
            method.getParamsMap().put("arg0", null);
        }else{
            AdfmfJavaUtilities.setELValue("#{bindings.arg0.inputValue}", null);
            method.getParamsMap().put("arg0",sede);
        }
        AdfmfJavaUtilities.setELValue("#{bindings.arg1.inputValue}", null);
        method.getParamsMap().put("arg1",null);
        AdfmfJavaUtilities.setELValue("#{bindings.arg2.inputValue}", null);
        method.getParamsMap().put("arg2",null);
        AdfmfJavaUtilities.setELValue("#{bindings.arg3.inputValue}", null);
        method.getParamsMap().put("arg3",null);

        method.execute();
        ValueExpression veIter = (ValueExpression)AdfmfJavaUtilities.getValueExpression(METODO_ITERATOR,Object.class);
        AmxIteratorBinding iteratorBinding = (AmxIteratorBinding)veIter.getValue(AdfmfJavaUtilities.getAdfELContext());
        iteratorBinding.getIterator().refresh();
    }
}
