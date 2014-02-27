package mobile.respaldo.parteocu;

import java.util.ArrayList;
import java.util.List;

import javax.el.ValueExpression;

import mobile.AdfmUtils;

import mobile.beans.BeanCombo;

import oracle.adfmf.amx.event.ActionEvent;
import oracle.adfmf.framework.api.AdfmfContainerUtilities;
import oracle.adfmf.framework.api.AdfmfJavaUtilities;
import oracle.adfmf.framework.api.GenericTypeBeanSerializationHelper;
import oracle.adfmf.framework.exception.AdfInvocationException;
import oracle.adfmf.framework.model.AdfELContext;
import oracle.adfmf.javax.faces.model.SelectItem;
import oracle.adfmf.util.GenericType;

public class bRegistrarPO {
    
    private List listProblemas;
    AdfELContext adfELContext = AdfmfJavaUtilities.getAdfELContext();
    private final static String WEBSERVICE_NAME = "WS_SPED";
    private final static String FEATURE = "MiApp";
    private final static String ALERTA = "mostrarMensaje";
    private String comentario;
    private Integer idProblema;
    
    public bRegistrarPO() {
        this.setListProblemas(this.llenarProblemas());
    }

    public ArrayList llenarProblemas() {
        ArrayList probItems = new ArrayList();
        List lstProbs = getProblemas_WEBSERVICE("getProblemas_LN_WS");
        if(lstProbs != null){
            for (int i = 0; i < lstProbs.size(); i++) {//en el 1.4 no se permite usar el foreach
                BeanCombo r = (BeanCombo) lstProbs.get(i);
                probItems.add(new SelectItem(r.getId().toString(), 
                                             r.getDescripcion().toString()));
            }
        }
        return probItems;
    }
    
    public List getProblemas_WEBSERVICE(String metodo){
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
    
    public void registrarParteOcurrencia(ActionEvent actionEvent) {
        try {
            String error = isValid();
            if (error.equalsIgnoreCase("000")) {
                ValueExpression veNidUsu = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.usuario.nidUsuario}", Integer.class);
                ValueExpression veNidMain = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope._nidMain}", Integer.class);

                Integer nidUsu = (Integer)veNidUsu.getValue(adfELContext);
                Integer nidMain = (Integer)veNidMain.getValue(adfELContext);

                List pnames = new ArrayList();
                List params = new ArrayList();
                List ptypes = new ArrayList();

                pnames.add("arg0");
                pnames.add("arg1");
                pnames.add("arg2");
                pnames.add("arg3");
                
                params.add(nidMain);
                params.add(this.getComentario());
                params.add(this.getIdProblema());
                params.add(nidUsu);
                
                ptypes.add(Integer.class);
                ptypes.add(String.class);
                ptypes.add(Integer.class);
                ptypes.add(Integer.class);
                
                String resultado = (String)AdfmfJavaUtilities.invokeDataControlMethod(WEBSERVICE_NAME,
                                                                                     null, 
                                                                                     "registrarParteOcurrencia_WS",
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
            }else{
                AdfmUtils.alert(FEATURE, ALERTA, new Object[] { error });
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
            AdfmUtils.alert(FEATURE, ALERTA, new Object[] { "Hubo un error al insertar. Intentelo nuevamente."});
        }
    }
    
    public String redirectEvaluadoPopUp() {
        resetearValores();
        return "000";
    }
    
    public String isValid(){
        if(this.getIdProblema() == null){
            return "m_0006";
        }
        if(this.getIdProblema().intValue() == 0){
            return "m_0006";
        }
        if(this.getComentario() == null){
            return "m_0007";
        }
        if(this.getComentario().equalsIgnoreCase("")){
            return "m_0007";
        }
        return "000";
    }
    
    public void resetearValores(){
        AdfmfJavaUtilities.setELValue("#{pageFlowScope._nidMain}", null);
    }
    
    public void resetOnBack(ActionEvent actionEvent) {
        resetearValores();
    }
    
    public void setListProblemas(List listProblemas) {
        this.listProblemas = listProblemas;
    }

    public List getListProblemas() {
        return listProblemas;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getComentario() {
        return comentario;
    }

    public void setIdProblema(Integer idProblema) {
        this.idProblema = idProblema;
    }

    public Integer getIdProblema() {
        return idProblema;
    }
}
