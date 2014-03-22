package mobile.respaldo.parteocu;

import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import javax.el.ValueExpression;
import oracle.adfmf.amx.event.ActionEvent;
import oracle.adfmf.framework.api.AdfmfJavaUtilities;
import mobile.AdfmUtils;

import mobile.beans.BeanBar;
import mobile.beans.BeanEvaluacionWS;
import mobile.beans.BeanPie;

import oracle.adfmf.bindings.OperationBinding;
import oracle.adfmf.bindings.dbf.AmxIteratorBinding;
import oracle.adfmf.framework.api.GenericTypeBeanSerializationHelper;
import oracle.adfmf.framework.exception.AdfInvocationException;
import oracle.adfmf.framework.model.AdfELContext;
import oracle.adfmf.util.GenericType;

public class bDetalle_PO {
    
    private String titulo = "Detalle del Parte de Ocurrencia";
    private final static String FEATURE = "MiApp";
    private final static String ALERTA = "mostrarMensaje";
    private AdfELContext adfELContext = AdfmfJavaUtilities.getAdfELContext();
    private List lstPie = new ArrayList();
    private List lstBarra = new ArrayList();
    
    public bDetalle_PO() {
        
    }
    
    public void _resetearAlSalir(){
        AdfmfJavaUtilities.setELValue("#{pageFlowScope._comentario}", null);
        AdfmfJavaUtilities.setELValue("#{pageFlowScope._descProblema}", null);
        AdfmfJavaUtilities.setELValue("#{pageFlowScope._profesor}", null);
        AdfmfJavaUtilities.setELValue("#{pageFlowScope._curso}", null);
        AdfmfJavaUtilities.setELValue("#{pageFlowScope._fechaRegistro}", null);
        AdfmfJavaUtilities.setELValue("#{pageFlowScope._areaAcademica}", null);
        AdfmfJavaUtilities.setELValue("#{pageFlowScope._aula}", null);
        AdfmfJavaUtilities.setELValue("#{pageFlowScope._sede}", null);
        AdfmfJavaUtilities.setELValue("#{pageFlowScope._nombresUsuario}", null);
    }
    
    public void initPieProfe(){
        try {
            lstPie.removeAll(lstPie);
            lstBarra.removeAll(lstBarra);
            ValueExpression veIter = (ValueExpression)AdfmfJavaUtilities.getValueExpression("#{bindings.ReturnIterator}",Object.class);
            AmxIteratorBinding iteratorBinding = (AmxIteratorBinding)veIter.getValue(AdfmfJavaUtilities.getAdfELContext());
            iteratorBinding.getIterator().refresh();
            
            GenericType row = null;
            iteratorBinding.getIterator().first();
            BeanPie pie = null;
            for (int i = 0; i < iteratorBinding.getIterator().getTotalRowCount(); i++){
                row = (GenericType)iteratorBinding.getCurrentRow();
                pie = (BeanPie)GenericTypeBeanSerializationHelper.fromGenericType(BeanPie.class,row);
                lstPie.add(pie);
                try {
                    if ((i + 1) == iteratorBinding.getIterator().getTotalRowCount()) {
                        BeanBar[] lstBarras = pie.getLstBar();
                        for(int j = 0; j < lstBarras.length; j++){
                            lstBarra.add(lstBarras[j]);
                        } 
                    }
                } catch (Exception e) {
                   // AdfmUtils.logStackTrace(e);
                }
                iteratorBinding.getIterator().next();
            }
        } catch(Exception e){
            AdfmUtils.alert(FEATURE,
                            ALERTA,
                            new Object[] {"m_0001"});
        }
        return;
    }
    
    public BeanPie[] getPieProfesor(){
        //initPieProfe();
        BeanPie c[] = null;
        if(lstPie != null){
            c = (BeanPie[])lstPie.toArray(new BeanPie[lstPie.size()]);
        }
        return c; 
    }
    
    public BeanBar[] getBarProfesor(){
        initPieProfe();
        BeanBar c[] = null;
        if(lstBarra != null){
            c = (BeanBar[])lstBarra.toArray(new BeanBar[lstBarra.size()]);
        }
        return c; 
    }
    
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setAdfELContext(AdfELContext adfELContext) {
        this.adfELContext = adfELContext;
    }

    public AdfELContext getAdfELContext() {
        return adfELContext;
    }

    public void setLstPie(List lstPie) {
        this.lstPie = lstPie;
    }

    public List getLstPie() {
        return lstPie;
    }

    public void setLstBarra(List lstBarra) {
        this.lstBarra = lstBarra;
    }

    public List getLstBarra() {
        return lstBarra;
    }
}
