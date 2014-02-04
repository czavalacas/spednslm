package sped.webservice.servicios;

import java.util.List;

import javax.ejb.EJB;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import sped.negocio.LNSF.IL.LN_C_SFPermisosLocal;
import sped.negocio.LNSF.IL.LN_C_SFUsuarioLocal;
import sped.negocio.entidades.beans.BeanPermiso;
import sped.negocio.entidades.beans.BeanUsuario;

@WebService
public class WS_SPED {
    
    @EJB
    private LN_C_SFUsuarioLocal ln_C_SFUsuarioLocal;
    @EJB
    private LN_C_SFPermisosLocal ln_C_SFPermisosLocal;

    @WebMethod
    public BeanUsuario autenticarUsuarioLN(@WebParam(name = "arg0") String usuario,
                                            @WebParam(name = "arg1") String clave){
        BeanUsuario bu = ln_C_SFUsuarioLocal.autenticarUsuarioLN(usuario, clave);
        return bu;
    }

    @WebMethod
    public List<BeanPermiso> getPermisosByUsuarioRol_WS(@WebParam(name = "arg0") int nidRol,
                                                         @WebParam(name = "arg1") int nidUsuario) {
        return ln_C_SFPermisosLocal.getPermisos_WS(nidRol, nidUsuario);
    }
}