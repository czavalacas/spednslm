package sped.webservice.servicios;

import java.util.List;

import javax.ejb.EJB;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import sped.negocio.LNSF.IL.LN_C_SFAreaAcademicaLocal;
import sped.negocio.LNSF.IL.LN_C_SFEvaluacionLocal;
import sped.negocio.LNSF.IL.LN_C_SFPermisosLocal;
import sped.negocio.LNSF.IL.LN_C_SFSedeLocal;
import sped.negocio.LNSF.IL.LN_C_SFUsuarioLocal;
import sped.negocio.entidades.beans.BeanAreaAcademica;
import sped.negocio.entidades.beans.BeanEvaluacion;
import sped.negocio.entidades.beans.BeanEvaluacionWS;
import sped.negocio.entidades.beans.BeanPermiso;
import sped.negocio.entidades.beans.BeanSede;
import sped.negocio.entidades.beans.BeanUsuario;

@WebService
public class WS_SPED {
    
    @EJB
    private LN_C_SFUsuarioLocal ln_C_SFUsuarioLocal;
    @EJB
    private LN_C_SFPermisosLocal ln_C_SFPermisosLocal;
    @EJB
    private LN_C_SFEvaluacionLocal ln_C_SFEvaluacionLocal;
    @EJB
    private LN_C_SFAreaAcademicaLocal ln_C_SFAreaAcademicaLocal;
    @EJB
    private LN_C_SFSedeLocal ln_C_SFSedeLocal;

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

    @WebMethod
    public List<BeanEvaluacionWS> getPlanificaciones_WS(@WebParam(name = "arg0") int nidRol,
                                                        @WebParam(name = "arg1") int nidSede,
                                                        @WebParam(name = "arg2") int nidAreaAcademica,
                                                        @WebParam(name = "arg3") int nidUsuario,
                                                        @WebParam(name = "arg4") String nombresProfesor,
                                                        @WebParam(name = "arg5") String curso,
                                                        @WebParam(name = "arg6") int nidSedeFiltro,
                                                        @WebParam(name = "arg7") int nidAAFiltro){
        System.out.println("rol:"+nidRol);
        System.out.println("nidSede:"+nidSede);
        System.out.println("nidAreaAcademica:"+nidAreaAcademica);
        System.out.println("nidUsuario:"+nidUsuario);
        System.out.println("nombresProfesor:"+nombresProfesor);
        System.out.println("curso:"+curso);
        System.out.println("nidSedeFiltro:"+nidSedeFiltro);
        System.out.println("nidAAFiltro:"+nidAAFiltro);
        if(nidRol == 1 || nidRol == 2 || nidRol == 4 || nidRol == 5){//Solo Director,EvaXArea,EvaXSede y EvaGeneral pueden consultar
            return ln_C_SFEvaluacionLocal.getPlanificaciones_LN_WS(nidRol, nidSede, nidAreaAcademica, nidUsuario, nombresProfesor, curso, nidSedeFiltro, nidAAFiltro);
        }else{
            return null;
        }
    }

    @WebMethod
    public List<BeanAreaAcademica> getAreasAcademicas_WS(){
        return ln_C_SFAreaAcademicaLocal.getAreaAcademicaLN();
    }

    @WebMethod
    public List<BeanSede> getSedes_WS(){
        return ln_C_SFSedeLocal.getSedeLN();
    }
}