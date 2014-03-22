package mobile.respaldo.Consultas;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.el.ValueExpression;

import mobile.AdfmUtils;

import mobile.beans.BeanCombo;
import mobile.beans.BeanComboString;
import mobile.beans.BeanRangoNota;
import mobile.beans.BeanSede;

import oracle.adfmf.amx.event.ActionEvent;
import oracle.adfmf.amx.event.ValueChangeEvent;
import oracle.adfmf.bindings.OperationBinding;
import oracle.adfmf.bindings.dbf.AmxIteratorBinding;
import oracle.adfmf.framework.api.AdfmfJavaUtilities;
import oracle.adfmf.framework.api.GenericTypeBeanSerializationHelper;
import oracle.adfmf.framework.exception.AdfInvocationException;
import oracle.adfmf.java.beans.PropertyChangeListener;
import oracle.adfmf.java.beans.PropertyChangeSupport;
import oracle.adfmf.java.beans.ProviderChangeListener;
import oracle.adfmf.java.beans.ProviderChangeSupport;
import oracle.adfmf.javax.faces.model.SelectItem;
import oracle.adfmf.util.GenericType;

public class bConsultarEvaluacion {
    
    private final static String METODO = "#{bindings.getEvaluaciones_WS}";
    private final static String METODO_ITERATOR = "#{bindings.getEvaluaciones_WSIterator}";
    private List listTipoVisita;
    private List listPlanificadores;
    private List listEvaluadores;
    private static List listBeanRangoNota = null;
    private double minNota = 0.0;
    private double promedioNota = 0.0;
    private double maxNota = 0.0;
    private String titulo = "Evaluaciones";
    private final static String WEBSERVICE_NAME = "WS_SPED";
    private final static String FEATURE = "MiApp";
    private final static String ALERTA = "mostrarMensaje";
    protected transient PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
    protected transient ProviderChangeSupport providerChangeSupport = new ProviderChangeSupport(this);
    
    public bConsultarEvaluacion(){
        this.setListTipoVisita(this.llenarListTipoVisita());
        this.setListPlanificadores(this.llenarPlanificadores());
        this.setListEvaluadores(this.llenarEvaluadores());
    }
    
    public void cambioEnSede(ValueChangeEvent vce) {
        actualizarSegunSOC("arg6", vce);
    }

    public void cambioEnArea(ValueChangeEvent vce) {
        actualizarSegunSOC("arg7", vce);
    }
    
    public void cambioEnTipoVisita(ValueChangeEvent vce) {
        actualizarSegunSOC("arg11", vce);
    }
    
    public void cambioEnPlanificador(ValueChangeEvent vce) {
        actualizarSegunSOC("arg12", vce);
    }
    
    public void cambioEnEvaluador(ValueChangeEvent vce) {
        actualizarSegunSOC("arg13", vce);
    }
    
    public void cambioEnEstado(ValueChangeEvent vce) {
        actualizarSegunSOC("arg8", vce);
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

    public void verGraficosInit(ActionEvent ae) {
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.panel}","pi2");
        ValueExpression veIter = (ValueExpression)AdfmfJavaUtilities.getValueExpression("#{bindings.ReturnIterator}",Object.class);
        AmxIteratorBinding iteratorBinding = (AmxIteratorBinding)veIter.getValue(AdfmfJavaUtilities.getAdfELContext());
        iteratorBinding.getIterator().refresh();
        GenericType row = null;
        iteratorBinding.getIterator().first();

        for (int i = 0; i < iteratorBinding.getIterator().getTotalRowCount(); i++) {
            row = (GenericType)iteratorBinding.getCurrentRow();
            
            if((i + 1) == iteratorBinding.getIterator().getTotalRowCount()){
                Double notaMin = (Double) row.getAttribute("notaMin");
                Double notaProm = (Double) row.getAttribute("notaProm");
                Double notaMax = (Double) row.getAttribute("notaMax");
                this.setMinNota(notaMin.doubleValue());
                this.setPromedioNota(notaProm.doubleValue());
                this.setMaxNota(notaMax.doubleValue());
                
                Integer cant1 = (Integer) row.getAttribute("ceroDiezCant");
                Integer cant2 = (Integer) row.getAttribute("onceQuinceCant");
                Integer cant3 = (Integer) row.getAttribute("restoCant");
                if(listBeanRangoNota == null){
                    listBeanRangoNota = new ArrayList();
                    listBeanRangoNota.add(new BeanRangoNota("0-10",cant1.intValue(),"rgb(255,0,0)"));
                    listBeanRangoNota.add(new BeanRangoNota("11-15",cant2.intValue(),"rgb(255,255,0)"));
                    listBeanRangoNota.add(new BeanRangoNota("16-20",cant3.intValue(),"rgb(0,102,0)"));
                }else{
                    listBeanRangoNota.removeAll(listBeanRangoNota);
                    listBeanRangoNota.add(new BeanRangoNota("0-10",cant1.intValue(),"rgb(255,0,0)"));
                    listBeanRangoNota.add(new BeanRangoNota("11-15",cant2.intValue(),"rgb(255,255,0)"));
                    listBeanRangoNota.add(new BeanRangoNota("16-20",cant3.intValue(),"rgb(0,102,0)"));
                }
            }
            iteratorBinding.getIterator().next();
        }
    }
    
    public BeanRangoNota[] getBeanRangoNota() { 
        BeanRangoNota c[] = null;
        if(listBeanRangoNota != null){
            c = (BeanRangoNota[])listBeanRangoNota.toArray(new BeanRangoNota[listBeanRangoNota.size()]); 
        }
        return c;
    }
    
    
    public ArrayList llenarListTipoVisita() {
        ArrayList tipoVisitaItems = new ArrayList();
        List tipoVisita = getTipoVisita_WEBSERVICE();
        if(tipoVisita != null){
            for (int i = 0; i < tipoVisita.size(); i++) {//en el 1.4 no se permite usar el foreach
                BeanComboString r = (BeanComboString) tipoVisita.get(i);
                tipoVisitaItems.add(new SelectItem(r.getId().toString(), 
                                                   r.getDescripcion().toString()));
            }
        }
        return tipoVisitaItems;
    }
    
    public ArrayList llenarPlanificadores() {
        ArrayList planifItems = new ArrayList();
        List tipoVisita = getPlanificadoresEvaluadores_WEBSERVICE("getPlanificadores_LN_WS");
        if(tipoVisita != null){
            for (int i = 0; i < tipoVisita.size(); i++) {//en el 1.4 no se permite usar el foreach
                BeanCombo r = (BeanCombo) tipoVisita.get(i);
                planifItems.add(new SelectItem(r.getId().toString(), 
                                               r.getDescripcion().toString()));
            }
        }
        return planifItems;
    }
    
    public ArrayList llenarEvaluadores() {
        ArrayList evaludoresItems = new ArrayList();
        List tipoVisita = getPlanificadoresEvaluadores_WEBSERVICE("getEvaluadores_LN_WS");
        if(tipoVisita != null){
            for (int i = 0; i < tipoVisita.size(); i++) {//en el 1.4 no se permite usar el foreach
                BeanCombo r = (BeanCombo) tipoVisita.get(i);
                evaludoresItems.add(new SelectItem(r.getId().toString(), 
                                                   r.getDescripcion().toString()));
            }
        }
        return evaludoresItems;
    }
    
    public List getTipoVisita_WEBSERVICE(){
        GenericType result;
        List tipoVisitaWS = new ArrayList();//en el 1.4 no se permite usar List<Objecto>
        List nullParams = new ArrayList();
        try {
            result = (GenericType)AdfmfJavaUtilities.invokeDataControlMethod(WEBSERVICE_NAME, //Nombre del WS (definido cuando se creo el datacontrol
                                                                             null,
                                                                             "getTipoVisita_LN_WS", //nombre del metodo ver el datacontrol MethodName
                                                                             nullParams,
                                                                             nullParams,
                                                                             nullParams);
            for (int i = 0; i < result.getAttributeCount(); i++) {
                GenericType row = (GenericType)result.getAttribute(i);
                BeanComboString beanComboString = (BeanComboString)GenericTypeBeanSerializationHelper.fromGenericType(BeanComboString.class,row);
                tipoVisitaWS.add(beanComboString);
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
        return tipoVisitaWS;
    }
    
    public List getPlanificadoresEvaluadores_WEBSERVICE(String metodo){
        GenericType result;
        List planifWS = new ArrayList();//en el 1.4 no se permite usar List<Objecto>
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
                planifWS.add(beanCombo);
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
        return planifWS;
    }
    
    public void accionBack(){
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.panel}","pi1");
        if(listBeanRangoNota != null){
            listBeanRangoNota.removeAll(listBeanRangoNota);
        }
    }
    
    public void setListTipoVisita(List listTipoVisita) {
        this.listTipoVisita = listTipoVisita;
    }

    public List getListTipoVisita() {
        return listTipoVisita;
    }

    public void setListPlanificadores(List listPlanificadores) {
        this.listPlanificadores = listPlanificadores;
    }

    public List getListPlanificadores() {
        return listPlanificadores;
    }

    public void setListEvaluadores(List listEvaluadores) {
        this.listEvaluadores = listEvaluadores;
    }

    public List getListEvaluadores() {
        return listEvaluadores;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setMinNota(double minNota) {
        double oldminNota = this.minNota;
        this.minNota = minNota;
        propertyChangeSupport.firePropertyChange("minNota",oldminNota,minNota);
    }

    public double getMinNota() {
        return minNota;
    }

    public void setPromedioNota(double promedioNota) {
        double oldpromedioNota = this.promedioNota;
        this.promedioNota = promedioNota;
        propertyChangeSupport.firePropertyChange("promedioNota",oldpromedioNota,promedioNota);
    }

    public double getPromedioNota() {
        return promedioNota;
    }

    public void setMaxNota(double maxNota) {
        double oldmaxNota = this.maxNota;
        this.maxNota = maxNota;
        propertyChangeSupport.firePropertyChange("maxNota",oldmaxNota,maxNota);
    }

    public double getMaxNota() {
        return maxNota;
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

    public void setProviderChangeSupport(ProviderChangeSupport providerChangeSupport) {
        this.providerChangeSupport = providerChangeSupport;
    }

    public ProviderChangeSupport getProviderChangeSupport() {
        return providerChangeSupport;
    }

    public void setListBeanRangoNota(List listBeanRangoNota) {
        this.listBeanRangoNota = listBeanRangoNota;
    }

    public List getListBeanRangoNota() {
        return listBeanRangoNota;
    }
}
