package mobile.respaldo.Evaluacion;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import java.util.Map;

import javax.el.ValueExpression;

import mobile.AdfmUtils;

import mobile.beans.BeanTipoXRol;

import oracle.adf.model.datacontrols.device.DeviceManager;

import oracle.adf.model.datacontrols.device.DeviceManagerFactory;

import oracle.adfmf.amx.event.ValueChangeEvent;
import oracle.adfmf.framework.exception.AdfInvocationException;
import oracle.adfmf.java.beans.PropertyChangeListener;
import oracle.adfmf.java.beans.PropertyChangeSupport;
import oracle.adfmf.java.beans.ProviderChangeListener;
import oracle.adfmf.java.beans.ProviderChangeSupport;
import oracle.adfmf.amx.event.ActionEvent;
import oracle.adfmf.bindings.OperationBinding;
import oracle.adfmf.bindings.dbf.AmxIteratorBinding;
import oracle.adfmf.framework.api.AdfmfContainerUtilities;
import oracle.adfmf.framework.api.AdfmfJavaUtilities;
import oracle.adfmf.framework.api.GenericTypeBeanSerializationHelper;
import oracle.adfmf.framework.model.AdfELContext;
import oracle.adfmf.util.GenericType;

public class bEvaluar {
    AdfELContext adfELContext = AdfmfJavaUtilities.getAdfELContext();
    private final static String METODO = "#{bindings.getLstIndicadoresByFichaCriterio_LN_WS}";
    private final static String METODO_ITERATOR = "#{bindings.getLstIndicadoresByFichaCriterio_LN_WSIterator}";
    
    private final static String METODO_LEYENDAS = "#{bindings.getLeyendasByCritIndicador_WS}";
    private final static String METODO_ITERATOR_LEYENDAS = "#{bindings.getLeyendasByCritIndicador_WSIterator}";
    
    private final static String METODO_CRITERIOS = "#{bindings.getCriteriosEvaluacion_WS}";
    private final static String METODO_ITERATOR_CRITERIOS = "#{bindings.getCriteriosEvaluacion_WSIterator}";
    
    private final static String WS_SERVICE = "WS_SPED";
    private final static String FEATURE = "MiApp";
    private final static String ALERTA = "mostrarMensaje";
    private String titulo = "Evaluar";
    
    private int maxValByCriterio;
    private int  sumaByCriterio;
    private double  notaEscala20;
    private double notaFinalEscala20;
    
    protected transient PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
    protected transient ProviderChangeSupport providerChangeSupport = new ProviderChangeSupport(this);
    
    List lstCriterios = new ArrayList();
    
    private String redireccionar;
    private boolean isOkRedireccionar = false;

    public bEvaluar() {
        this.setNotaEscala20(0.0);
        this.setNotaFinalEscala20(0.0);
        ValueExpression veNidRol    = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.usuario.rol.nidRol}",Integer.class);
        Integer nidRol =    (Integer)veNidRol.getValue(adfELContext);
       
        List pnames = new ArrayList();//aqui van los nombres en el metodo WS are01,arg02, etc si son 5 hasta arg5
        List params = new ArrayList();//en esta seteas todos los valores si son 5 la lista tendra list.size = 5
        List ptypes = new ArrayList();
        
        pnames.add("arg0");        
        params.add(nidRol);
        ptypes.add(Integer.class);
        
        pnames.add("arg1");        
        params.add("O");
        ptypes.add(String.class);
        
        try {
            Map mapa = null;
            ValueExpression veIter = (ValueExpression)AdfmfJavaUtilities.getValueExpression("#{bindings.ReturnIterator}",Object.class);
            AmxIteratorBinding iteratorBinding = (AmxIteratorBinding)veIter.getValue(AdfmfJavaUtilities.getAdfELContext());
            iteratorBinding.getIterator().refresh();
            GenericType rowCrit = null;
            iteratorBinding.getIterator().first();
            for (int i = 0; i < iteratorBinding.getIterator().getTotalRowCount(); i++){
                rowCrit = (GenericType)iteratorBinding.getCurrentRow();
                Integer critIndiCurrent = (Integer) rowCrit.getAttribute("nidCriterio");
                mapa = new HashMap();
                mapa.put("NID_CRIT",critIndiCurrent);
                mapa.put("INDIS",null);
                mapa.put("INDEX",new Integer(i));
                lstCriterios.add(i,mapa);
                iteratorBinding.getIterator().next();
            }
         /*   
            GenericType result = (GenericType)AdfmfJavaUtilities.invokeDataControlMethod(WS_SERVICE, //Nombre del WS (definido cuando se creo el datacontrol
                                                                                        null, 
                                                                                        "getCriteriosEvaluacion_WS", //nombre del metodo ver el datacontrol MethodName
                                                                                        pnames, params, ptypes);
            */
           /* for (int i = 0; i < result.getAttributeCount(); i++) {
                GenericType row = (GenericType)result.getAttribute(i);
                Integer critIndiCurrent = (Integer) row.getAttribute("nidCriterio");
                //propertyChangeSupport.firePropertyChange("nota",row.getAttribute("nota"),new Double(0.0));
                //row.setAttribute("nota",new Double(0.0));
                mapa = new HashMap();
                mapa.put("NID_CRIT",critIndiCurrent);
                mapa.put("INDIS",null);
                mapa.put("INDEX",new Integer(i));
                lstCriterios.add(i,mapa);
            }*/
        }/* catch (AdfInvocationException aie) {
            aie.printStackTrace();
            AdfmUtils.log(" AdfInvocationException");
        } */catch(Exception e){
            AdfmUtils.log(" Exception");
        }
    }

    private void actualizarLstCrit(Integer nidCrit,List listaIndis, Integer index,Integer index2, Integer valor, Integer nidCI,boolean isCI){
        Map mapa = new HashMap();
        mapa.put("NID_CRIT",nidCrit);
        if(isCI){
            Map map = (Map) lstCriterios.get(index.intValue());
            List indis = (List) map.get("INDIS");
            Map mapaIndis = new HashMap();
            if(indis != null){
                if(indis.size() > 0 ){
                    mapaIndis = this.getListaIndis(index2,indis);
                    mapaIndis.remove("NID_CI");
                    mapaIndis.remove("VALOR");
                    mapaIndis.remove("INDEX");
                }
            }
            mapaIndis.put("NID_CI", nidCI);
            mapaIndis.put("VALOR", valor);
            mapaIndis.put("INDEX",index2);
            listaIndis.remove(index2.intValue());
            listaIndis.add(index2.intValue(),mapaIndis);
            mapa.put("INDIS",listaIndis);
        }else{
            mapa.put("INDIS",listaIndis);
        }
        mapa.put("INDEX",index);
        lstCriterios.remove(index.intValue());
        lstCriterios.add(index.intValue(),mapa);
    }
    
    private Map getListaIndis(Integer index2,List lst){
        for(int i = 0; i < lst.size(); i++){
            if(index2.intValue() == i){
                return (Map) lst.get(i);
            }
        }
        return new HashMap();
    }

    public void getIndicadoresByCriterio(ActionEvent actionEvent) {
        ValueExpression ve = (ValueExpression)AdfmfJavaUtilities.getValueExpression(METODO, Object.class);
        OperationBinding method = (OperationBinding)ve.getValue(AdfmfJavaUtilities.getAdfELContext());
        
        ValueExpression veNidFicha    = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.nidFicha}",Integer.class);
        ValueExpression veNidCriterio = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.nidCriterio}", Integer.class);
        ValueExpression veMaxVals = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.maxVals}", Integer.class);
        
        Integer nidFicha =    (Integer)veNidFicha.getValue(adfELContext);
        Integer nidCriterio = (Integer)veNidCriterio.getValue(adfELContext);
        Integer maxVals = (Integer)veMaxVals.getValue(adfELContext);
        
        method.getParamsMap().put("arg0",nidFicha);
        method.getParamsMap().put("arg1",nidCriterio);
        method.execute();
        
        ValueExpression veIter = (ValueExpression)AdfmfJavaUtilities.getValueExpression("#{bindings.ReturnIterator1}",Object.class);
        AmxIteratorBinding iteratorBinding = (AmxIteratorBinding)veIter.getValue(AdfmfJavaUtilities.getAdfELContext());
        iteratorBinding.getIterator().refresh();
        Map mapa = this.getMapaByNidCriterio(nidCriterio);
        Integer index = (Integer) mapa.get("INDEX");
        Map mapaIndis = null;
        GenericType row = null;
        List lstIndis = new ArrayList();
        iteratorBinding.getIterator().first();
        this.setMaxValByCriterio(maxVals.intValue() * iteratorBinding.getIterator().getTotalRowCount());
        int sumaTotalXCriterio = 0;
        for (int i = 0; i < iteratorBinding.getIterator().getTotalRowCount(); i++) {
            row = (GenericType)iteratorBinding.getCurrentRow();
            Integer nidCriterioIndicador = new Integer(row.getAttribute("nidCriterioIndicador").toString());
            if (mapa.get("INDIS") != null) { //se selecciono por 2da a + veces
                lstIndis = (List)mapa.get("INDIS");
                iteratorBinding.getIterator().previous();
                iteratorBinding.getIterator().next();
                if(i == 0){
                    iteratorBinding.getIterator().previous();
                }
                if((i+1) == iteratorBinding.getIterator().getTotalRowCount()){
                    iteratorBinding.getIterator().previous();
                }
                Integer val = this.getValorFromMapIndisByNidCriterioIndicador(nidCriterioIndicador, lstIndis);
                sumaTotalXCriterio = sumaTotalXCriterio + val.intValue();
                row.setAttribute("valor",val);
                actualizarLstCrit(nidCriterio,lstIndis,index,null,null,null,false);
            } else { //Primera vez que se ejecuta este metodo(se selecciono un criterio)
                mapaIndis = new HashMap();
                mapaIndis.put("NID_CI", nidCriterioIndicador);
                mapaIndis.put("VALOR", null);
                mapaIndis.put("INDEX",new Integer(i));
                lstIndis.add(i, mapaIndis);
            }
            iteratorBinding.getIterator().next();
        }
        this.setSumaByCriterio(sumaTotalXCriterio);
        double notaEscala20 = (sumaTotalXCriterio * 20) / this.getMaxValByCriterio();
        this.setNotaEscala20(notaEscala20);
        if (mapa.get("INDIS") == null){
            actualizarLstCrit(nidCriterio,lstIndis,index,null,null,null,false);
        }
    }
    
    private Map getMapaByNidCriterio(Integer nidCriterio){
        Map mapa = null;
        for(int i = 0; i < lstCriterios.size();i++){
            mapa = (Map)lstCriterios.get(i);
            Integer nidCrit = (Integer) mapa.get("NID_CRIT");
            if(nidCrit.intValue() == nidCriterio.intValue()){
                return mapa;
            }
        }
        return null;
    }

    private Integer getValorFromMapIndisByNidCriterioIndicador(Integer nidCritIndicador,List lstIndis){
        Map mapaIndis = null;
        for (int i = 0; i < lstIndis.size(); i++){
            mapaIndis = (Map) lstIndis.get(i);
            Integer nid = (Integer) mapaIndis.get("NID_CI");
            if(nid.intValue() == nidCritIndicador.intValue()){
                return ((Integer) mapaIndis.get("VALOR") == null) ? new Integer(0) : (Integer) mapaIndis.get("VALOR");
            }
        }
        return new Integer(0);
    }
    
    public String consultarIndicadores() {
        ValueExpression ve = (ValueExpression)AdfmfJavaUtilities.getValueExpression(METODO, Object.class);
        OperationBinding method = (OperationBinding)ve.getValue(AdfmfJavaUtilities.getAdfELContext());
        
        ValueExpression veNidFicha = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.nidFicha}",Integer.class);
        ValueExpression veNidCriterio = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.nidCriterio}", Integer.class);
        
        Integer nidFicha = (Integer)veNidFicha.getValue(adfELContext);
        Integer nidCriterio = (Integer)veNidCriterio.getValue(adfELContext);
        
        method.getParamsMap().put("arg0",nidFicha);
        method.getParamsMap().put("arg1",nidCriterio);
        method.execute();
        
        ValueExpression veIter = (ValueExpression)AdfmfJavaUtilities.getValueExpression(METODO_ITERATOR,Object.class);
        AmxIteratorBinding iteratorBinding = (AmxIteratorBinding)veIter.getValue(AdfmfJavaUtilities.getAdfELContext());
        iteratorBinding.getIterator().refresh();
        return null;
    }

    public void consultarLeyendas(ActionEvent actionEvent) {
        
        ValueExpression ve = (ValueExpression)AdfmfJavaUtilities.getValueExpression(METODO_LEYENDAS, Object.class);
        OperationBinding method = (OperationBinding)ve.getValue(AdfmfJavaUtilities.getAdfELContext());
        ValueExpression veNiCritIndi = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.nidCritIndicador}",Integer.class);
        
        Integer niCritIndi = (Integer)veNiCritIndi.getValue(adfELContext);
        
        method.getParamsMap().put("arg0",niCritIndi);
        method.execute();
        ValueExpression veIter = (ValueExpression)AdfmfJavaUtilities.getValueExpression("#{bindings.ReturnIterator2}",Object.class);
        AmxIteratorBinding iteratorBinding = (AmxIteratorBinding)veIter.getValue(AdfmfJavaUtilities.getAdfELContext());
        iteratorBinding.getIterator().refresh();
        
        ValueExpression veIterIndis = (ValueExpression)AdfmfJavaUtilities.getValueExpression("#{bindings.ReturnIterator1}",Object.class);
        AmxIteratorBinding iteIndis = (AmxIteratorBinding)veIterIndis.getValue(AdfmfJavaUtilities.getAdfELContext());
        iteIndis.getIterator().refresh();
        iteIndis.getIterator().first();
        for (int i = 0; i < iteIndis.getIterator().getTotalRowCount(); i++){
            GenericType row = (GenericType) iteIndis.getCurrentRow();
            Integer critIndiCurrent = (Integer) row.getAttribute("nidCriterioIndicador");
            if (critIndiCurrent.intValue() == niCritIndi.intValue()){
                if(row.getAttribute("valor") != null){
                    Integer valor = (Integer) row.getAttribute("valor");
                    AdfmfJavaUtilities.setELValue("#{pageFlowScope.pfcvalor}",valor.intValue() == 0 ? new Integer(1) : valor);
                }else{
                    AdfmfJavaUtilities.setELValue("#{pageFlowScope.pfcvalor}",new Integer(1));
                }
            }
            iteIndis.getIterator().next();
        }
    }

    public void aplicarCambioValor(ActionEvent actionEvent) {
        try {
            ValueExpression veValor = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.pfcvalor}", Integer.class);
            ValueExpression venidCritIndicador = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.nidCritIndicador}", Integer.class);
            ValueExpression veNidCriterio = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.nidCriterio}", Integer.class);
            
            Integer nidCriterio = (Integer)veNidCriterio.getValue(adfELContext);

            Integer newVal = (Integer)veValor.getValue(adfELContext);
            Integer nidCritIndi = (Integer)venidCritIndicador.getValue(adfELContext);

            ValueExpression ve1 = AdfmfJavaUtilities.getValueExpression("#{bindings.ReturnIterator1}", AmxIteratorBinding.class);
            AmxIteratorBinding iter = (AmxIteratorBinding)ve1.getValue(AdfmfJavaUtilities.getAdfELContext());
            iter.getIterator().refresh();
            iter.getIterator().first();
            Map mapa = this.getMapaByNidCriterio(nidCriterio);
            List listaIndis = (List) mapa.get("INDIS");
            Integer index = (Integer) mapa.get("INDEX");
            int sumaTotalXCriterio = 0;
            GenericType row = null;
            for (int i = 0; i < iter.getIterator().getTotalRowCount(); i++) {
                row = (GenericType)iter.getCurrentRow();
                Integer critIndiCurrent = (Integer) row.getAttribute("nidCriterioIndicador");
                Integer val = (Integer) row.getAttribute("valor");
                iter.getIterator().previous();
                iter.getIterator().next();
                if(i == 0){
                    iter.getIterator().previous();
                }
                if (critIndiCurrent.intValue() == nidCritIndi.intValue()) {
                    if((i+1) == iter.getIterator().getTotalRowCount()){
                        iter.getIterator().previous();
                    }
                    propertyChangeSupport.firePropertyChange("valor",row.getAttribute("valor"),newVal);
                    row.setAttribute("valor", newVal);
                    sumaTotalXCriterio = sumaTotalXCriterio + newVal.intValue();
                    actualizarLstCrit(nidCriterio, listaIndis, index,new Integer(i),newVal,nidCritIndi,true);
                }else{
                    sumaTotalXCriterio = sumaTotalXCriterio + val.intValue();
                }
                iter.getIterator().next();
            }
            this.setSumaByCriterio(sumaTotalXCriterio);
            double notaEscala20 = (double) (sumaTotalXCriterio * 20) / this.getMaxValByCriterio();
            this.setNotaEscala20(notaEscala20);
            
            ValueExpression veIter = (ValueExpression)AdfmfJavaUtilities.getValueExpression("#{bindings.ReturnIterator}",Object.class);
            AmxIteratorBinding iteratorBinding = (AmxIteratorBinding)veIter.getValue(AdfmfJavaUtilities.getAdfELContext());
            iteratorBinding.getIterator().refresh();
            iteratorBinding.getIterator().first();
            double notaFinal = 0.0;
            GenericType rowCrit = null;
            for (int i = 0; i < iteratorBinding.getIterator().getTotalRowCount(); i++){
                rowCrit = (GenericType)iteratorBinding.getCurrentRow();
                Integer nidCrit = (Integer) rowCrit.getAttribute("nidCriterio");
                Double notaFinalCrit = (Double) rowCrit.getAttribute("nota");
                iteratorBinding.getIterator().previous();
                iteratorBinding.getIterator().next();
                if(i == 0){
                    iteratorBinding.getIterator().previous();
                }
                if(nidCriterio.intValue() == nidCrit.intValue()){
                    if((i+1) == iteratorBinding.getIterator().getTotalRowCount()){
                        iteratorBinding.getIterator().previous();
                    }
                    propertyChangeSupport.firePropertyChange("nota",rowCrit.getAttribute("nota"),new Double(notaEscala20));
                    rowCrit.setAttribute("nota",new Double(notaEscala20));
                    notaFinal = notaFinal + notaEscala20;
                }else{
                    notaFinal = notaFinal + notaFinalCrit.doubleValue();
                }
                iteratorBinding.getIterator().next();
            }
            this.setNotaFinalEscala20((notaFinal / iteratorBinding.getIterator().getTotalRowCount())); 
        } catch (NumberFormatException nfe) {
            nfe.printStackTrace();
        }
    }

    public void resetearValoresCriterios(ActionEvent actionEvent) {
        this.setNotaEscala20(0.0);
        this.setNotaFinalEscala20(0.0);
        Map mapa = null;
        ValueExpression veIter = (ValueExpression)AdfmfJavaUtilities.getValueExpression("#{bindings.ReturnIterator}",Object.class);
        AmxIteratorBinding iteratorBinding = (AmxIteratorBinding)veIter.getValue(AdfmfJavaUtilities.getAdfELContext());
        iteratorBinding.getIterator().refresh();
        iteratorBinding.getIterator().first();
        GenericType rowCrit = null;
        for (int i = 0; i < iteratorBinding.getIterator().getTotalRowCount(); i++){
            rowCrit = (GenericType)iteratorBinding.getCurrentRow();
            Integer critIndiCurrent = (Integer) rowCrit.getAttribute("nidCriterio");
            iteratorBinding.getIterator().previous();
            iteratorBinding.getIterator().next();
            if(i == 0){
                iteratorBinding.getIterator().previous();
            }
            mapa = new HashMap();
            mapa.put("NID_CRIT",critIndiCurrent);
            mapa.put("INDIS",null);
            mapa.put("INDEX",new Integer(i));
            lstCriterios.add(i,mapa);
            propertyChangeSupport.firePropertyChange("nota",rowCrit.getAttribute("nota"),new Double(0.0));
            rowCrit.setAttribute("nota",new Double(0.0));
            iteratorBinding.getIterator().next();
        }
    }
    
    public Map isValid(){
        Map resultado = new HashMap();
        resultado.put("BOOL","TRUE");
        String strParam = "";
        for (int i = 0; i < lstCriterios.size(); i++) {
            Map mapaCrit = (HashMap)lstCriterios.get(i);
            List lstIndis = (List) mapaCrit.get("INDIS");
            if(lstIndis != null){
                for(int j = 0; j < lstIndis.size(); j++){
                    Map mapaIndis = (Map) lstIndis.get(j);
                    if(mapaIndis == null){
                        resultado.put("BOOL","FALSE");
                        return resultado;
                    }else{
                        Integer val = (Integer) mapaIndis.get("VALOR");
                        if(val == null){
                            resultado.put("BOOL","FALSE");
                            return resultado;
                        }else{
                            strParam = strParam.concat((Integer)mapaIndis.get("NID_CI")+","+(Integer)mapaIndis.get("VALOR")+";");
                        }
                    }
                }
            }else{
                resultado.put("BOOL","FALSE");
                return resultado;
            }
        }
        resultado.put("PARAM",strParam);
        return resultado;
    }
    
    public void evaluarDocente(ActionEvent actionEvent) {
        try{
            Map res = isValid();
            String valid = (String) res.get("BOOL");
            if (valid.equals("FALSE")) {
                AdfmUtils.alert(FEATURE, ALERTA, new Object[] { "m_0005" });
                return;
            }
            ValueExpression veNidUsu = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.usuario.nidUsuario}",Integer.class);
            ValueExpression veNidEva = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.nidEvaluacion}",Integer.class);
            ValueExpression veNidLog = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.usuario.nidLog}",Integer.class);
            
            Integer nidUsu = (Integer)veNidUsu.getValue(adfELContext);
            Integer nidEva = (Integer)veNidEva.getValue(adfELContext);
            Integer nidLog = (Integer)veNidLog.getValue(adfELContext);
            
            String cadenaIndisXValor = (String) res.get("PARAM");
           
            List pnames = new ArrayList();
            List params = new ArrayList();
            List ptypes = new ArrayList();
            
            pnames.add("arg0");       pnames.add("arg1");                   pnames.add("arg2");       pnames.add("arg3");        
            params.add(nidEva);       params.add(cadenaIndisXValor);        params.add(nidUsu);       params.add(nidLog);
            ptypes.add(Integer.class);ptypes.add(String.class);             ptypes.add(Integer.class);ptypes.add(Integer.class);
            
            String resultado = (String)AdfmfJavaUtilities.invokeDataControlMethod(WS_SERVICE,
                                                                                 null, 
                                                                                 "evaluarDocente_WS",
                                                                                 pnames, 
                                                                                 params, 
                                                                                 ptypes);  
            if(!resultado.equals("000")){                                                                  
                AdfmUtils.alert(FEATURE, ALERTA, new Object[] {resultado});
            } else {
                AdfmfContainerUtilities.invokeContainerJavaScriptFunction(FEATURE,
                                                                         "showPopup",
                                                                         new Object[] {} );
            }
        }catch(Exception e){
            e.printStackTrace();
            AdfmUtils.alert(FEATURE, ALERTA, new Object[] { "Hubo un error al insertar. Intentelo nuevamente."});
        }
    }
    
    public String redirectEvaluadoPopUp() {
        return "000";
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
       
    public void setAdfELContext(AdfELContext adfELContext) {
        this.adfELContext = adfELContext;
    }

    public AdfELContext getAdfELContext() {
        return adfELContext;
    }

    public void setLstCriterios(List lstCriterios) {
        this.lstCriterios = lstCriterios;
    }

    public List getLstCriterios() {
        return lstCriterios;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setMaxValByCriterio(int maxValByCriterio) {
        int oldMaxValByCriterio = this.maxValByCriterio;
        this.maxValByCriterio = maxValByCriterio;
        propertyChangeSupport.firePropertyChange("maxValByCriterio",oldMaxValByCriterio,maxValByCriterio);
    }

    public int getMaxValByCriterio() {
        return maxValByCriterio;
    }
    public void setNotaEscala20(double notaEscala20) {
        double oldNotaEscala20 = this.notaEscala20;
        this.notaEscala20 = notaEscala20;
        propertyChangeSupport.firePropertyChange("notaEscala20",oldNotaEscala20,notaEscala20);
    }

    public double getNotaEscala20() {
        return notaEscala20;
    }

    public void setSumaByCriterio(int sumaByCriterio) {
        int oldSumaByCriterio = this.sumaByCriterio;
        this.sumaByCriterio = sumaByCriterio;
        propertyChangeSupport.firePropertyChange("sumaByCriterio",oldSumaByCriterio,sumaByCriterio);
    }

    public int getSumaByCriterio() {
        return sumaByCriterio;
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

    public void setNotaFinalEscala20(double notaFinalEscala20) {
        double oldNotaFinalEscala20 = this.notaFinalEscala20;
        this.notaFinalEscala20 = notaFinalEscala20;
        propertyChangeSupport.firePropertyChange("notaFinalEscala20",oldNotaFinalEscala20,notaFinalEscala20);
    }

    public double getNotaFinalEscala20() {
        return notaFinalEscala20;
    }
    
    public void setRedireccionar(String redireccionar) {
        this.redireccionar = redireccionar;
    }

    public String getRedireccionar() {
        return redireccionar;
    }

    public void setIsOkRedireccionar(boolean isOkRedireccionar) {
        this.isOkRedireccionar = isOkRedireccionar;
    }

    public boolean isIsOkRedireccionar() {
        return isOkRedireccionar;
    }
}
