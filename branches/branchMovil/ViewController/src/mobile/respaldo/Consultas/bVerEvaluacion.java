package mobile.respaldo.Consultas;

import java.util.ArrayList;
import java.util.List;

import javax.el.ValueExpression;

import mobile.AdfmUtils;

import mobile.beans.BeanCriterio;
import mobile.beans.BeanEvaluacionWS;
import mobile.beans.BeanIndicador;
import mobile.beans.BeanRangoNota;

import mobile.beans.BeanUsuario;

import oracle.adfmf.framework.api.AdfmfJavaUtilities;
import oracle.adfmf.framework.api.GenericTypeBeanSerializationHelper;
import oracle.adfmf.framework.exception.AdfInvocationException;
import oracle.adfmf.framework.model.AdfELContext;
import oracle.adfmf.java.beans.PropertyChangeListener;
import oracle.adfmf.java.beans.PropertyChangeSupport;
import oracle.adfmf.java.beans.ProviderChangeListener;
import oracle.adfmf.java.beans.ProviderChangeSupport;
import oracle.adfmf.util.GenericType;

public class bVerEvaluacion {
    
    private List listCrits = new ArrayList();
    AdfELContext adfELContext = AdfmfJavaUtilities.getAdfELContext();
    private final static String WS_SERVICE = "WS_SPED";
    
    private String titulo = "Evaluacion";
    
    protected transient PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
    protected transient ProviderChangeSupport providerChangeSupport = new ProviderChangeSupport(this);

    public bVerEvaluacion(){
            
    }
    
    public BeanCriterio[] getBeanCriterio(){
        ValueExpression veNidEva = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope._nidEvaluacion}", Integer.class);
        Integer nidEva = (Integer)veNidEva.getValue(adfELContext);
        
        List pnames = new ArrayList();
        List params = new ArrayList();
        List ptypes = new ArrayList();
        
        pnames.add("arg0");
        params.add(nidEva);
        ptypes.add(Integer.class);
        BeanEvaluacionWS beanEvaluacionWS = null;
        try {
            GenericType genericType = (GenericType)AdfmfJavaUtilities.invokeDataControlMethod(WS_SERVICE, 
                                                                                             null, 
                                                                                             "getDetalleEvaluacionById_WS",
                                                                                             pnames, 
                                                                                             params, 
                                                                                             ptypes);
            GenericType row = (GenericType)genericType.getAttribute(0);
            beanEvaluacionWS = (BeanEvaluacionWS)GenericTypeBeanSerializationHelper.fromGenericType(BeanEvaluacionWS.class,row);
            AdfmfJavaUtilities.setELValue("#{pageFlowScope.cometEva}",beanEvaluacionWS.getComentarioEvaluador());
            AdfmfJavaUtilities.setELValue("#{pageFlowScope.cometProf}",beanEvaluacionWS.getComentarioProfesor());
            AdfmfJavaUtilities.setELValue("#{pageFlowScope.vals}",beanEvaluacionWS.getValores());
        } catch (AdfInvocationException aie) {
            // TODO: Add catch code
            aie.printStackTrace();
        }
        listCrits.removeAll(listCrits);
        BeanCriterio bc = null;
        for(int i = 0; i < beanEvaluacionWS.getCriterios().length; i++){
            bc = new BeanCriterio();
            bc = beanEvaluacionWS.getCriterios()[i];
            bc.setIndicadoresVec(bc.getIndicadoresVec());
            listCrits.add(bc);
        }
        
        BeanCriterio c[] = null;
        if(listCrits != null){
            c = (BeanCriterio[])listCrits.toArray(new BeanCriterio[listCrits.size()]);
        }
        return c; 
    }
    
    public void addProviderChangeListener(ProviderChangeListener l) {
        providerChangeSupport.addProviderChangeListener(l);
    }

    public void removeProviderChangeListener(ProviderChangeListener l) {
        providerChangeSupport.removeProviderChangeListener(l);
    }

    public void addPropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.addPropertyChangeListener(l);
    }

    public void removePropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.removePropertyChangeListener(l);
    }
    
    public void setPropertyChangeSupport(PropertyChangeSupport propertyChangeSupport) {
        this.propertyChangeSupport = propertyChangeSupport;
    }

    public PropertyChangeSupport getPropertyChangeSupport() {
        return propertyChangeSupport;
    }
    
    public void setListCrits(List listCrits) {
        List oldlistCrits = this.listCrits;
        this.listCrits = listCrits;
        propertyChangeSupport.firePropertyChange("listCrits", oldlistCrits,listCrits);
    }

    public List getListCrits() {
        return listCrits;
    }

    public void setAdfELContext(AdfELContext adfELContext) {
        this.adfELContext = adfELContext;
    }

    public AdfELContext getAdfELContext() {
        return adfELContext;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setProviderChangeSupport(ProviderChangeSupport providerChangeSupport) {
        this.providerChangeSupport = providerChangeSupport;
    }

    public ProviderChangeSupport getProviderChangeSupport() {
        return providerChangeSupport;
    }
}
