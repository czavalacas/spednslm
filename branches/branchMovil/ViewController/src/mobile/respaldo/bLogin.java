package mobile.respaldo;

import com.sun.util.logging.Level;
import com.sun.util.logging.Logger;

import java.util.ArrayList;
import java.util.List;

import java.util.Map;

import javax.el.ValueExpression;

import mobile.AdfmUtils;
import mobile.beans.BeanUsuario;

import oracle.adf.model.datacontrols.device.DeviceManagerFactory;

import oracle.adfmf.amx.event.ActionEvent;
import oracle.adfmf.framework.api.AdfmfContainerUtilities;
import oracle.adfmf.framework.api.AdfmfJavaUtilities;
import oracle.adfmf.framework.api.GenericTypeBeanSerializationHelper;
import oracle.adfmf.framework.exception.AdfInvocationException;
import oracle.adfmf.framework.model.AdfELContext;
import oracle.adfmf.java.beans.PropertyChangeListener;
import oracle.adfmf.java.beans.PropertyChangeSupport;
import oracle.adfmf.java.beans.ProviderChangeListener;
import oracle.adfmf.java.beans.ProviderChangeSupport;
import oracle.adfmf.util.GenericType;
import oracle.adfmf.util.Utility;

public class bLogin {
    
    private List listaRoles;
    private String redirect;
    private final static String WEBSERVICE_NAME = "WS_SPED";
    private final static String FEATURE = "MiApp";
    private final static String ALERTA = "mostrarMensaje";
    private String usuario;
    private String clave;
    private String msj;
    private String claveRecuperar;
    protected transient PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
    protected transient ProviderChangeSupport providerChangeSupport = new ProviderChangeSupport(this);
    
    public bLogin(){
    }
    
    public String autenticarUsuario() {
        String error = "";
        if(usuario != null && clave != null){
            if(!usuario.equals("") && !clave.equals("")){
                List pnames = new ArrayList();//aqui van los nombres en el metodo WS are01,arg02, etc si son 5 hasta arg5
                List params = new ArrayList();//en esta seteas todos los valores si son 5 la lista tendra list.size = 5
                List ptypes = new ArrayList();//aqui van los tipos de datos,estas 3 listas siempre vas a mandar
                pnames.add("arg0");
                ptypes.add(String.class);
                params.add(usuario);
                
                pnames.add("arg1");
                ptypes.add(String.class);
                params.add(clave);
                
                pnames.add("arg2");
                ptypes.add(String.class);
                params.add(this.getAllPhoneData());
                try {
                    GenericType result = (GenericType) AdfmfJavaUtilities.invokeDataControlMethod(WEBSERVICE_NAME, //Nombre del WS (definido cuando se creo el datacontrol
                                                                                                  null, "autenticarUsuarioLN", //nombre del metodo ver el datacontrol MethodName
                                                                                                  pnames,
                                                                                                  params,
                                                                                                  ptypes);
                    GenericType row = (GenericType)result.getAttribute(0);
                    BeanUsuario beanUsuario = (BeanUsuario)GenericTypeBeanSerializationHelper.fromGenericType(BeanUsuario.class,row);
                    error = beanUsuario.getError().getCidError();
                    if(error.equals("000")){
                        clearScopeVariables();
                        AdfmfJavaUtilities.setELValue("#{pageFlowScope.usuario}", null);
                        AdfmfJavaUtilities.setELValue("#{pageFlowScope.usuario}",beanUsuario);
                        setRedirect(error);
                        return error;
                    }else{
                        error = beanUsuario.getError().getDescripcionError();
                    }
                }catch (AdfInvocationException ex) {
                    error = "m_0002";
                } catch (Exception e) {
                    e.printStackTrace();
                    //AdfmUtils.log("error!: "+e.getMessage());
                    error = "m_0003";
                }
                if(!error.equals("000")){
                    AdfmUtils.alert(FEATURE, 
                                    ALERTA, 
                                    new Object[] {error});
                }
            }else{
                AdfmUtils.alert(FEATURE, 
                                ALERTA, 
                                new Object[] {"Ingrese usuario y clave"});
            }
        }else{
            AdfmUtils.alert(FEATURE, 
                            ALERTA, 
                            new Object[] {"Ingrese usuario y clave"});
        }
        return error;
    }
    
    public void recuperarClave(ActionEvent actionEvent) {
        try{
            if(claveRecuperar != null){
                if(!claveRecuperar.equals("")){
                    List pnames = new ArrayList();
                    List params = new ArrayList();
                    List ptypes = new ArrayList();
                    
                    pnames.add("arg0");
                    params.add(this.getClaveRecuperar());
                    ptypes.add(String.class);
                    AdfmUtils.log("clave: "+claveRecuperar);
                    String resultado = (String)AdfmfJavaUtilities.invokeDataControlMethod(WEBSERVICE_NAME,
                                                                                         null, 
                                                                                         "recuperarClave",
                                                                                         pnames, 
                                                                                         params, 
                                                                                         ptypes);AdfmUtils.log("paso:"+resultado);
                    AdfmUtils.alert(FEATURE, ALERTA, new Object[] {resultado});
                }else{
                    AdfmUtils.alert(FEATURE, 
                                    ALERTA, 
                                    new Object[] {"Ingrese su correo"});
                }
            }else{
                AdfmUtils.alert(FEATURE, 
                                ALERTA, 
                                new Object[] {"Ingrese su correo"});
            }
        }catch(Exception e){
            AdfmUtils.logStackTrace(e);
            AdfmUtils.alert(FEATURE, ALERTA, new Object[] { "Hubo un error al insertar. Intentelo nuevamente."});
        }
        setClaveRecuperar(null);
    }
    
    public void salirAplicacion(ActionEvent e){
        clearScopeVariables();
        AdfmUtils.alert(FEATURE,
                        "salirApp",
                        new Object[] {});
    }
    
    public void clearScopeVariables() {
        ValueExpression ve1 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.usuario}", BeanUsuario.class);
        ve1.setValue(AdfmfJavaUtilities.getAdfELContext(),null);
    }
    
    private String getAllPhoneData(){
        String returno = "";
        returno = returno + DeviceManagerFactory.getDeviceManager().getName()+"|";
        returno = returno + DeviceManagerFactory.getDeviceManager().getPlatform()+"|";
        returno = returno + DeviceManagerFactory.getDeviceManager().getVersion()+"|";
        returno = returno + DeviceManagerFactory.getDeviceManager().getOs()+"|";
        returno = returno + DeviceManagerFactory.getDeviceManager().getModel()+"|";
        returno = returno + DeviceManagerFactory.getDeviceManager().hasGeolocation()+"|";
        returno = returno + DeviceManagerFactory.getDeviceManager().getNetworkStatus()+"|";
        returno = returno + DeviceManagerFactory.getDeviceManager().getScreenWidth()+"|";
        returno = returno + DeviceManagerFactory.getDeviceManager().getScreenHeight()+"|";
        returno = returno + DeviceManagerFactory.getDeviceManager().getAvailableScreenWidth()+"|";
        returno = returno + DeviceManagerFactory.getDeviceManager().getAvailableScreenHeight()+"|";
        returno = returno + DeviceManagerFactory.getDeviceManager().getScreenDpi()+"|";
        returno = returno + DeviceManagerFactory.getDeviceManager().getScreenDiagonalSize();
     //   AdfmUtils.log("Log: "+returno);
        return returno;
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
    
    public void setListaRoles(List listaRoles) {
        this.listaRoles = listaRoles;
    }

    public List getListaRoles() {
        return listaRoles;
    }

    public void setRedirect(String redirect) {
        this.redirect = redirect;
    }

    public String getRedirect() {
        return redirect;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getClave() {
        return clave;
    }

    public void setMsj(String msj) {
        this.msj = msj;
    }

    public String getMsj() {
        return msj;
    }

    public void setClaveRecuperar(String claveRecuperar) {
        String oldclaveRecuperar = this.claveRecuperar;
        this.claveRecuperar = claveRecuperar;
        propertyChangeSupport.firePropertyChange("claveRecuperar",oldclaveRecuperar,claveRecuperar);
    }

    public String getClaveRecuperar() {
        return claveRecuperar;
    }
}
