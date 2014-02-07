package mobile.respaldo.Evaluacion;

import javax.el.ValueExpression;

import oracle.adfmf.amx.event.ActionEvent;
import oracle.adfmf.bindings.OperationBinding;
import oracle.adfmf.bindings.dbf.AmxIteratorBinding;
import oracle.adfmf.framework.api.AdfmfJavaUtilities;
import oracle.adfmf.framework.model.AdfELContext;

public class bEvaluar {
    AdfELContext adfELContext = AdfmfJavaUtilities.getAdfELContext();
    private final static String METODO = "#{bindings.getLstIndicadoresByFichaCriterio_LN_WS}";
    private final static String METODO_ITERATOR = "#{bindings.getLstIndicadoresByFichaCriterio_LN_WSIterator}";

    public bEvaluar() {
    }

    public void getIndicadoresByCriterio(ActionEvent actionEvent) {
        ValueExpression ve = (ValueExpression)AdfmfJavaUtilities.getValueExpression(METODO, Object.class);
        OperationBinding method = (OperationBinding)ve.getValue(AdfmfJavaUtilities.getAdfELContext());
        ValueExpression veNidFicha    = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.nidFicha}",Integer.class);
        ValueExpression veNidCriterio = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.nidCriterio}", Integer.class);
        
        Integer nidFicha =    (Integer)veNidFicha.getValue(adfELContext);
        Integer nidCriterio = (Integer)veNidCriterio.getValue(adfELContext);
        
        method.getParamsMap().put("arg0",nidFicha);
        method.getParamsMap().put("arg1",nidCriterio);
        method.execute();
        ValueExpression veIter = (ValueExpression)AdfmfJavaUtilities.getValueExpression(METODO_ITERATOR,Object.class);
        AmxIteratorBinding iteratorBinding = (AmxIteratorBinding)veIter.getValue(AdfmfJavaUtilities.getAdfELContext());
        iteratorBinding.getIterator().refresh();
    }

    public String consultarIndicadores() {
        ValueExpression ve = (ValueExpression)AdfmfJavaUtilities.getValueExpression(METODO, Object.class);
        OperationBinding method = (OperationBinding)ve.getValue(AdfmfJavaUtilities.getAdfELContext());
        ValueExpression veNidFicha    = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.nidFicha}",Integer.class);
        ValueExpression veNidCriterio = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.nidCriterio}", Integer.class);
        
        Integer nidFicha =    (Integer)veNidFicha.getValue(adfELContext);
        Integer nidCriterio = (Integer)veNidCriterio.getValue(adfELContext);
        
        method.getParamsMap().put("arg0",nidFicha);
        method.getParamsMap().put("arg1",nidCriterio);
        method.execute();
        ValueExpression veIter = (ValueExpression)AdfmfJavaUtilities.getValueExpression(METODO_ITERATOR,Object.class);
        AmxIteratorBinding iteratorBinding = (AmxIteratorBinding)veIter.getValue(AdfmfJavaUtilities.getAdfELContext());
        iteratorBinding.getIterator().refresh();
        return null;
    }
}
