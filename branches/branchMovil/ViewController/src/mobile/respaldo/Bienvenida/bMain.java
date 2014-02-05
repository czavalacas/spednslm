package mobile.respaldo.Bienvenida;

import java.util.ArrayList;
import java.util.List;
import mobile.AdfmUtils;
import mobile.beans.BeanAreaAcademica;
import mobile.beans.BeanSede;
import oracle.adfmf.framework.api.AdfmfJavaUtilities;
import oracle.adfmf.framework.api.GenericTypeBeanSerializationHelper;
import oracle.adfmf.framework.exception.AdfInvocationException;
import oracle.adfmf.javax.faces.model.SelectItem;
import oracle.adfmf.util.GenericType;

public class bMain {
    
    private String urlIcon;
    private List listaSedes;
    private List listaAreas;
    private final static String WEBSERVICE_NAME = "WS_SPED";
    private final static String FEATURE = "MiApp";
    private final static String ALERTA = "mostrarMensaje";
    
    public bMain(){
        this.setListaSedes(this.llenarSedesCombo());
        this.setListaAreas(this.llenarAreasCombo());
    }
    
    public ArrayList llenarSedesCombo() {
        ArrayList sedesItems = new ArrayList();
        List sedes = getSedes_WEBSERVICE();
        if(sedes != null){
            for (int i = 0; i < sedes.size(); i++) {//en el 1.4 no se permite usar el foreach
                BeanSede r = (BeanSede) sedes.get(i);
                sedesItems.add(new SelectItem(r.getNidSede().toString(), 
                                              r.getDescripcionSede().toString()));
            }
        }
        return sedesItems;
    }
    
    public ArrayList llenarAreasCombo() {
        ArrayList areasItems = new ArrayList();
        List areas = getAreas_WEBSERVICE();
        if(areas != null){
            for (int i = 0; i < areas.size(); i++) {//en el 1.4 no se permite usar el foreach
                BeanAreaAcademica r = (BeanAreaAcademica) areas.get(i);
                areasItems.add(new SelectItem(r.getNidAreaAcademica().toString(), 
                                              r.getDescripcionAreaAcademica().toString()));
            }
        }
        return areasItems;
    }
    
    public List getSedes_WEBSERVICE(){
        GenericType result;
        List rolesWS = new ArrayList();//en el 1.4 no se permite usar List<Objecto>
        List nullParams = new ArrayList();
        try {
            result = (GenericType)AdfmfJavaUtilities.invokeDataControlMethod(WEBSERVICE_NAME, //Nombre del WS (definido cuando se creo el datacontrol
                                                                             null,
                                                                             "getSedes_WS", //nombre del metodo ver el datacontrol MethodName
                                                                             nullParams,
                                                                             nullParams,
                                                                             nullParams);
            for (int i = 0; i < result.getAttributeCount(); i++) {
                GenericType row = (GenericType)result.getAttribute(i);
                BeanSede beanSede = (BeanSede)GenericTypeBeanSerializationHelper.fromGenericType(BeanSede.class, 
                                                                                                 row);
                rolesWS.add(beanSede);
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
        return rolesWS;
    }
    
    public List getAreas_WEBSERVICE(){
        GenericType result;
        List rolesWS = new ArrayList();//en el 1.4 no se permite usar List<Objecto>
        List nullParams = new ArrayList();
        try {
            result = (GenericType)AdfmfJavaUtilities.invokeDataControlMethod(WEBSERVICE_NAME, //Nombre del WS (definido cuando se creo el datacontrol
                                                                             null,
                                                                             "getAreasAcademicas_WS", //nombre del metodo ver el datacontrol MethodName
                                                                             nullParams,
                                                                             nullParams,
                                                                             nullParams);
            for (int i = 0; i < result.getAttributeCount(); i++) {
                GenericType row = (GenericType)result.getAttribute(i);
                BeanAreaAcademica beanAreaAcademica = (BeanAreaAcademica)GenericTypeBeanSerializationHelper.fromGenericType(BeanAreaAcademica.class, 
                                                                                                                            row);
                rolesWS.add(beanAreaAcademica);
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
        return rolesWS;
    }
    
    public void setUrlIcon(String urlIcon) {
        this.urlIcon = urlIcon;
    }

    public String getUrlIcon() {
        return urlIcon;
    }

    public void setListaSedes(List listaSedes) {
        this.listaSedes = listaSedes;
    }

    public List getListaSedes() {
        return listaSedes;
    }

    public void setListaAreas(List listaAreas) {
        this.listaAreas = listaAreas;
    }

    public List getListaAreas() {
        return listaAreas;
    }
}
