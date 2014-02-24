package mobile.respaldo.Consultas;

import java.util.ArrayList;
import java.util.List;

import javax.el.ValueExpression;

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
    
    private static List listCrits = null;
    AdfELContext adfELContext = AdfmfJavaUtilities.getAdfELContext();
    private final static String WS_SERVICE = "WS_SPED";
    private final static String FEATURE = "MiApp";
    private final static String ALERTA = "mostrarMensaje";
    
    private String comentarioProfe;
    private String comentarioEva;
    private String vals;
    
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
            this.setComentarioEva(beanEvaluacionWS.getComentarioEvaluador());
            this.setComentarioProfe(beanEvaluacionWS.getComentarioProfesor());
            this.setVals(beanEvaluacionWS.getValores());
        } catch (AdfInvocationException aie) {
            // TODO: Add catch code
            aie.printStackTrace();
        }
        
        if(listCrits == null){
            listCrits = new ArrayList();
          /*  BeanIndicador[] vec1 = new BeanIndicador[3];
            BeanIndicador b = new BeanIndicador("Indicador 1",new Integer(1));
            vec1[0] = b;
            b = new BeanIndicador("Indicador 2",new Integer(2));
            vec1[1] = b;
            b = new BeanIndicador("Indicador 3",new Integer(3));
            vec1[2] = b;
            listCrits.add(new BeanCriterio("Crit 1",new Integer(1),vec1,11.3));
            
            BeanIndicador[] vec2 = new BeanIndicador[3];
            BeanIndicador b2 = new BeanIndicador("Indicador 4",new Integer(4));
            vec2[0] = b2;
            b2 = new BeanIndicador("Indicador 5",new Integer(5));
            vec2[1] = b2;
            b2 = new BeanIndicador("Indicador 6",new Integer(6));
            vec2[2] = b2;
            listCrits.add(new BeanCriterio("Crit 2",new Integer(2),vec2,16.7));*/
            
            for(int i = 0; i < beanEvaluacionWS.getCriterios().length; i++){
                BeanCriterio bc = beanEvaluacionWS.getCriterios()[i];
                listCrits.add(bc);
            }
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
    
    public static void setListCrits(List listCrits) {
        bVerEvaluacion.listCrits = listCrits;
    }

    public static List getListCrits() {
        return listCrits;
    }

    public void setAdfELContext(AdfELContext adfELContext) {
        this.adfELContext = adfELContext;
    }

    public AdfELContext getAdfELContext() {
        return adfELContext;
    }

    public void setComentarioProfe(String comentarioProfe) {
        String oldComentarioProfe = comentarioProfe;
        this.comentarioProfe = comentarioProfe;
        propertyChangeSupport.firePropertyChange("comentarioProfe",oldComentarioProfe,comentarioProfe);
    }

    public String getComentarioProfe() {
        return comentarioProfe;
    }

    public void setComentarioEva(String comentarioEva) {
        String oldComentarioEva = this.comentarioEva;
        this.comentarioEva = comentarioEva;
        propertyChangeSupport.firePropertyChange("comentarioEva",oldComentarioEva,comentarioEva);
    }

    public String getComentarioEva() {
        return comentarioEva;
    }

    public void setVals(String vals) {
        String oldVals = vals;
        this.vals = vals;
        propertyChangeSupport.firePropertyChange("vals",oldVals,vals);
    }

    public String getVals() {
        return vals;
    }
}
