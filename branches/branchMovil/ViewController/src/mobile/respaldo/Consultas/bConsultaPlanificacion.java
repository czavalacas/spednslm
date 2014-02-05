package mobile.respaldo.Consultas;

import javax.el.ValueExpression;
import oracle.adfmf.amx.event.ValueChangeEvent;
import oracle.adfmf.bindings.OperationBinding;
import oracle.adfmf.bindings.dbf.AmxIteratorBinding;
import oracle.adfmf.framework.api.AdfmfJavaUtilities;

public class bConsultaPlanificacion {
    public bConsultaPlanificacion() {
    }

    public void cambioEnSede(ValueChangeEvent vce) {
        actualizarSegunSOC("arg6",vce);
    }
    
    public void cambioEnArea(ValueChangeEvent vce) {
        actualizarSegunSOC("arg7",vce);
    }
    
    public void actualizarSegunSOC(String argumento,ValueChangeEvent vce){
        ValueExpression ve = (ValueExpression)AdfmfJavaUtilities.getValueExpression("#{bindings.getPlanificaciones_WS}",
                                                                                    Object.class);
        OperationBinding method = (OperationBinding)ve.getValue(AdfmfJavaUtilities.getAdfELContext());
        //pass new departmentId argument and execute method
        method.getParamsMap().put(argumento, vce.getNewValue());
        method.execute();
        //refresh iterator
        ValueExpression veIter = (ValueExpression)AdfmfJavaUtilities.getValueExpression("#{bindings.getPlanificaciones_WSIterator}",
                                                                                        Object.class);
        AmxIteratorBinding iteratorBinding = (AmxIteratorBinding)veIter.getValue(AdfmfJavaUtilities.getAdfELContext());
        iteratorBinding.getIterator().refresh();
    }

    public void cambioProfesor(ValueChangeEvent vce) {
        actualizarSegunSOC("arg4",vce);
    }

    public void cambioCurso(ValueChangeEvent vce) {
        actualizarSegunSOC("arg5",vce);
    }
}
