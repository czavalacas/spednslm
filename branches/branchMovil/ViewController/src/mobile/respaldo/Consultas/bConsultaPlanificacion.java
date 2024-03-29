package mobile.respaldo.Consultas;

import com.sun.util.logging.Level;
import com.sun.util.logging.Logger;

import java.sql.Timestamp;

import java.util.Date;

import javax.el.ValueExpression;

import mobile.AdfmUtils;

import mobile.beans.BeanPlanificacion;

import oracle.adfmf.amx.event.ActionEvent;
import oracle.adfmf.amx.event.ValueChangeEvent;
import oracle.adfmf.bindings.OperationBinding;
import oracle.adfmf.bindings.dbf.AmxIteratorBinding;
import oracle.adfmf.dc.bean.ConcreteJavaBeanObject;
import oracle.adfmf.framework.api.AdfmfJavaUtilities;
import oracle.adfmf.framework.model.AdfELContext;
import oracle.adfmf.util.Utility;

public class bConsultaPlanificacion {

    AdfELContext adfELContext = AdfmfJavaUtilities.getAdfELContext();
    private final static String METODO = "#{bindings.getPlanificaciones_WS}";
    private final static String METODO_ITERATOR = "#{bindings.getPlanificaciones_WSIterator}";
    private final static String FEATURE = "MiApp";
    private final static String ALERTA = "mostrarMensaje";

    public bConsultaPlanificacion() {

    }

    public void cambioEnSede(ValueChangeEvent vce) {
        actualizarSegunSOC("arg6", vce);
    }

    public void cambioEnArea(ValueChangeEvent vce) {
        actualizarSegunSOC("arg7", vce);
    }
    
    public String goToEvaluar(){
        ValueExpression ve = AdfmfJavaUtilities.getValueExpression("#{bindings.ReturnIterator.currentRow.dataProvider}", Object.class);
        Object obj = ve.getValue(AdfmfJavaUtilities.getAdfELContext());
        BeanPlanificacion beanPlanificacion = new BeanPlanificacion();
        if (obj instanceof ConcreteJavaBeanObject) {
            ConcreteJavaBeanObject cjbo = (ConcreteJavaBeanObject)obj; 
            beanPlanificacion = (BeanPlanificacion)cjbo.getInstance(); 
            AdfmfJavaUtilities.setELValue("#{pageFlowScope.beanPlanificacion}", null);
            AdfmfJavaUtilities.setELValue("#{pageFlowScope.beanPlanificacion}",beanPlanificacion);
        }
        return "evaluar"; 
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

    public void refrescarResultado(ActionEvent actionEvent) {
        ValueExpression ve = (ValueExpression)AdfmfJavaUtilities.getValueExpression(METODO, Object.class);
        OperationBinding method = (OperationBinding)ve.getValue(AdfmfJavaUtilities.getAdfELContext());
        ValueExpression ve1 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.usuario.rol.nidRol}", Integer.class);
        Integer rol = (Integer)ve1.getValue(adfELContext);

        ValueExpression ve2 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.usuario.sede.nidSede}", Integer.class);
        Integer sede = (Integer)ve2.getValue(adfELContext);

        ValueExpression ve3 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.usuario.areaAcademica.nidAreaAcademica}",Integer.class);
        Integer area = (Integer)ve3.getValue(adfELContext);

        ValueExpression ve4 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.usuario.nidUsuario}", Integer.class);
        Integer usuario = (Integer)ve4.getValue(adfELContext);
        method.getParamsMap().put("arg0", rol);
        method.getParamsMap().put("arg1", sede);
        method.getParamsMap().put("arg2", area);
        method.getParamsMap().put("arg3", usuario);

        AdfmfJavaUtilities.setELValue("#{bindings.arg4.inputValue}", null);
        method.getParamsMap().put("arg4", null);
        AdfmfJavaUtilities.setELValue("#{bindings.arg5.inputValue}", null);
        method.getParamsMap().put("arg5", null);
        if(rol.intValue() == 1 || rol.intValue() == 2){//Director || Area
            AdfmfJavaUtilities.setELValue("#{bindings.arg6.inputValue}", new Integer(0));
            method.getParamsMap().put("arg6", new Integer(0));
        }
        if(rol.intValue() == 1 || rol.intValue() == 4){//Director || Area
            AdfmfJavaUtilities.setELValue("#{bindings.arg7.inputValue}", new Integer(0));
            method.getParamsMap().put("arg7", new Integer(0));
        }
        method.execute();
        ValueExpression veIter = (ValueExpression)AdfmfJavaUtilities.getValueExpression(METODO_ITERATOR,Object.class);
        AmxIteratorBinding iteratorBinding = (AmxIteratorBinding)veIter.getValue(AdfmfJavaUtilities.getAdfELContext());
        iteratorBinding.getIterator().refresh();
    }

    public void cambioProfesor(ValueChangeEvent vce) {
        actualizarSegunSOC("arg4", vce);
    }

    public void cambioCurso(ValueChangeEvent vce) {
        actualizarSegunSOC("arg5", vce);
    }

    public String selectPlanif() {
        ValueExpression ve1 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.startDate}", Date.class);
        ValueExpression ve2 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.endDate}", Date.class);
        
        Date fecInicio = (Date)ve1.getValue(adfELContext);
        Date fecFin =    (Date)ve2.getValue(adfELContext);
        
       // AdfmUtils.log("fecInicio: "+fecInicio.getTime()+" fecFin: "+fecFin.getTime());
        Timestamp ts_fecI = new Timestamp(fecInicio.getTime());
        Timestamp ts_fecF = new Timestamp(fecFin.getTime());AdfmUtils.log("ts_fecI: "+ts_fecI+" ts_fecF: "+ts_fecF);
        Timestamp hoy = new Timestamp(new Date().getTime());AdfmUtils.log("hoy:"+hoy);
        if(hoy.after(ts_fecI)){
            return "evaluar";
        }else{
            AdfmUtils.alert(FEATURE, ALERTA, new Object[] {"Aun no es hora de evaluar, espero que llegue la hora de Inicio"});
        }
       /* boolean isBetween = hoy.after(ts_fecI) && hoy.before(ts_fecF);AdfmUtils.log("isbet: "+isBetween);
        if(isBetween){
            return "evaluar";
        }else{
            AdfmUtils.alert(FEATURE, ALERTA, new Object[] {"La hora actual no es la indicada para realizar esta evaluacion, o se paso la hora o aun no llega."});
        }*/
        return null;
    }
}