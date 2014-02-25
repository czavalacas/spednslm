package mobile.respaldo.parteocu;

import java.util.ArrayList;
import java.util.List;

import mobile.AdfmUtils;

import mobile.beans.BeanCombo;

import oracle.adfmf.framework.api.AdfmfJavaUtilities;
import oracle.adfmf.framework.api.GenericTypeBeanSerializationHelper;
import oracle.adfmf.framework.exception.AdfInvocationException;
import oracle.adfmf.javax.faces.model.SelectItem;
import oracle.adfmf.util.GenericType;

public class bRegistrarPO {
    
    private List listProblemas;
    private final static String WEBSERVICE_NAME = "WS_SPED";
    private final static String FEATURE = "MiApp";
    private final static String ALERTA = "mostrarMensaje";
    private String comentario;
    
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
}
