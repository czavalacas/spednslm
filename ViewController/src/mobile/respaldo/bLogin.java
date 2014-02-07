package mobile.respaldo;

import com.sun.util.logging.Level;
import com.sun.util.logging.Logger;

import java.util.ArrayList;
import java.util.List;

import javax.el.ValueExpression;

import mobile.AdfmUtils;
import mobile.beans.BeanUsuario;
import oracle.adfmf.amx.event.ActionEvent;
import oracle.adfmf.framework.api.AdfmfJavaUtilities;
import oracle.adfmf.framework.api.GenericTypeBeanSerializationHelper;
import oracle.adfmf.framework.exception.AdfInvocationException;
import oracle.adfmf.framework.model.AdfELContext;
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
    private AdfELContext adfELContext = AdfmfJavaUtilities.getAdfELContext();
    
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
                params.add(clave);//este es el valor amandar
                try {
                    GenericType result = (GenericType) AdfmfJavaUtilities.invokeDataControlMethod(WEBSERVICE_NAME, //Nombre del WS (definido cuando se creo el datacontrol
                                                                                                  null, "autenticarUsuarioLN", //nombre del metodo ver el datacontrol MethodName
                                                                                                  pnames,
                                                                                                  params,
                                                                                                  ptypes);
                    GenericType row = (GenericType)result.getAttribute(0);
                    BeanUsuario beanUsuario = (BeanUsuario)GenericTypeBeanSerializationHelper.fromGenericType(BeanUsuario.class, 
                                                                                                              row);
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
    
    /*
    public void autorizarUsuario(ActionEvent actionEvent) { //ActionListener - se ejecuta primero, ejecuta logica y validaciones
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
                params.add(clave);//este es el valor amandar
                try {
                    GenericType result = (GenericType) AdfmfJavaUtilities.invokeDataControlMethod(WEBSERVICE_NAME, //Nombre del WS (definido cuando se creo el datacontrol
                                                                                                  null, "autenticarUsuarioLN", //nombre del metodo ver el datacontrol MethodName
                                                                                                  pnames,
                                                                                                  params,
                                                                                                  ptypes);
                    GenericType row = (GenericType)result.getAttribute(0);
                    BeanUsuario beanUsuario = (BeanUsuario)GenericTypeBeanSerializationHelper.fromGenericType(BeanUsuario.class, 
                                                                                                              row);
                    error = beanUsuario.getError().getCidError();           
                    if(error.equals("000")){
                        try {
                            clearScopeVariables();
                        } catch (Exception e) {
                            // TODO: Add catch code
                            e.printStackTrace();
                        }
                        AdfmfJavaUtilities.setELValue("#{pageFlowScope.usuario}",beanUsuario);
                        setRedirect(error);
                        return;
                    }else{
                        error = beanUsuario.getError().getDescripcionError();
                    }
                }catch (AdfInvocationException ex) {
                    error = "m_0002";
                } catch (Exception e) {
                    e.printStackTrace();
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
    }
    */
    
    public void salirAplicacion(ActionEvent e){
     //   AdfmfJavaUtilities.setELValue("#{pageFlowScope.usuario.rol}",null);
     //   AdfmfJavaUtilities.setELValue("#{pageFlowScope.usuario.sedeNivel}",null);
     //   AdfmfJavaUtilities.setELValue("#{pageFlowScope.usuario.areaAcademica}",null);
        //AdfmfJavaUtilities.setELValue("#{pageFlowScope.usuario}",null);
        clearScopeVariables();
        AdfmUtils.alert(FEATURE,
                        "salirApp",
                        new Object[] {});
    }
    
    public void clearScopeVariables() {
        ValueExpression ve1 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.usuario}", BeanUsuario.class);
        ve1.setValue(AdfmfJavaUtilities.getAdfELContext(),null);
       // ValueExpression ve2 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.fileNames}", String.class);
       // ve2.setValue(AdfmfJavaUtilities.getAdfELContext(), "");
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
}