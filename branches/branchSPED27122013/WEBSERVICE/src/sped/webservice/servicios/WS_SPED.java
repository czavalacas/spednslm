package sped.webservice.servicios;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import java.util.Map;

import javax.ejb.EJB;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import sped.negocio.LNSF.IL.LN_C_SFAreaAcademicaLocal;
import sped.negocio.LNSF.IL.LN_C_SFCriterioIndicadorLocal;
import sped.negocio.LNSF.IL.LN_C_SFEvaluacionLocal;
import sped.negocio.LNSF.IL.LN_C_SFFichaCriterioLocal;
import sped.negocio.LNSF.IL.LN_C_SFFichaLocal;
import sped.negocio.LNSF.IL.LN_C_SFLeyendaLocal;
import sped.negocio.LNSF.IL.LN_C_SFPermisosLocal;
import sped.negocio.LNSF.IL.LN_C_SFSedeLocal;
import sped.negocio.LNSF.IL.LN_C_SFUsuarioLocal;
import sped.negocio.LNSF.IL.LN_C_SFUtilsLocal;
import sped.negocio.LNSF.IL.LN_T_SFEvaluacionLocal;
import sped.negocio.Utils.Utiles;
import sped.negocio.entidades.beans.BeanAreaAcademica;
import sped.negocio.entidades.beans.BeanCombo;
import sped.negocio.entidades.beans.BeanComboString;
import sped.negocio.entidades.beans.BeanCriterio;
import sped.negocio.entidades.beans.BeanCriterioIndicadorWS;
import sped.negocio.entidades.beans.BeanCriterioWS;
import sped.negocio.entidades.beans.BeanEvaluacion;
import sped.negocio.entidades.beans.BeanEvaluacionWS;
import sped.negocio.entidades.beans.BeanFicha;
import sped.negocio.entidades.beans.BeanIndicadorValorWS;
import sped.negocio.entidades.beans.BeanLeyendaWS;
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
    @EJB
    private LN_C_SFFichaLocal ln_C_SFFichaLocal;
    @EJB
    private LN_C_SFFichaCriterioLocal ln_C_SFFichaCriterioLocal;
    @EJB
    private LN_C_SFCriterioIndicadorLocal ln_C_SFCriterioIndicadorLocal;
    @EJB
    private LN_C_SFLeyendaLocal ln_C_SFLeyendaLocal;
    @EJB
    private LN_T_SFEvaluacionLocal ln_T_SFEvaluacionLocal;
    @EJB
    private LN_C_SFUtilsLocal ln_C_SFUtilsLocal;


    @WebMethod
    public BeanUsuario autenticarUsuarioLN(@WebParam(name = "arg0") String usuario,
                                           @WebParam(name = "arg1") String clave,
                                           @WebParam(name = "arg2") String cadenaPhoneData){
        BeanUsuario bu = ln_C_SFUsuarioLocal.autenticarUsuarioLN_WS(usuario, clave,cadenaPhoneData);
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
        if(nidRol == 1 || nidRol == 2 || nidRol == 4 || nidRol == 5){//Solo Director,EvaXArea,EvaXSede y EvaGeneral pueden consultar
            return ln_C_SFEvaluacionLocal.getPlanificaciones_LN_WS(nidRol, 
                                                                       nidSede, 
                                                                       nidAreaAcademica,
                                                                       nidUsuario, 
                                                                       nombresProfesor, 
                                                                       curso, 
                                                                       nidSedeFiltro, 
                                                                       nidAAFiltro);
        }else{
            return null;
        }
    }

    @WebMethod
    public List<BeanEvaluacionWS> getEvaluaciones_WS(@WebParam(name = "arg0") int nidRol,
                                                     @WebParam(name = "arg1") int nidSede,
                                                     @WebParam(name = "arg2") int nidAreaAcademica,
                                                     @WebParam(name = "arg3") int nidUsuario,
                                                     @WebParam(name = "arg4") String nombresProfesor,
                                                     @WebParam(name = "arg5") String curso,
                                                     @WebParam(name = "arg6") int nidSedeFiltro,
                                                     @WebParam(name = "arg7") int nidAAFiltro,
                                                     @WebParam(name = "arg8") String estado,
                                                     @WebParam(name = "arg9") Date fechaMin,
                                                     @WebParam(name = "arg10") Date fechaMax,
                                                     @WebParam(name = "arg11") String tipoVisita,
                                                     @WebParam(name = "arg12") Integer nidPlanificador,
                                                     @WebParam(name = "arg13") Integer nidEvaluador){
        System.out.println("invocando al WS getEvaluaciones_WS nidRol: "+nidRol);
        System.out.println("nidSede: "+nidSede);
        System.out.println("nidAreaAcademica: "+nidAreaAcademica);
        System.out.println("nidUsuario: "+nidUsuario);
        System.out.println("nombresProfesor: "+nombresProfesor);
        System.out.println("curso: "+curso);
        System.out.println("nidSedeFiltro: "+nidSedeFiltro);
        System.out.println("nidAAFiltro: "+nidAAFiltro);
        System.out.println("estado: "+estado);
        System.out.println("fechaMin: "+fechaMin);
        System.out.println("fechaMax: "+fechaMax);
        System.out.println("tipoVisita: "+tipoVisita);
        System.out.println("nidPlanificador: "+nidPlanificador);
        System.out.println("nidEvaluador: "+nidEvaluador);
        if(nidRol == 1 || nidRol == 2 || nidRol == 4 || nidRol == 5 || nidRol == 3){//Solo Director,EvaXArea,EvaXSede,Profesor y EvaGeneral pueden consultar
            if(estado == null){
                estado = "EJECUTADO";
            }else{
                if(estado.equals("0")){
                    estado = "EJECUTADO";
                }
            }
            if(fechaMin == null){
                fechaMin = new Date();
            }
            if(fechaMax == null){
                fechaMax = new Date();
            }
            if(tipoVisita != null){
                if(tipoVisita.equals("0")){
                    tipoVisita = null;
                }
            }
            return ln_C_SFEvaluacionLocal.getEvaluaciones_LN_WS(nidRol, 
                                                                    nidSede, 
                                                                    nidAreaAcademica,
                                                                    nidUsuario, 
                                                                    nombresProfesor, 
                                                                    curso, 
                                                                    nidSedeFiltro, 
                                                                    nidAAFiltro,
                                                                    estado,
                                                                    fechaMin,
                                                                    fechaMax,
                                                                    tipoVisita,
                                                                    nidPlanificador,
                                                                    nidEvaluador);
        }else{
            return new ArrayList<BeanEvaluacionWS>();
        }
    }

    @WebMethod
    public List<BeanCriterioWS> getCriteriosEvaluacion_WS(@WebParam(name = "arg0") int nidRol,
                                                          @WebParam(name = "arg1") String tipoFichaCurso){
        int ficha = ln_C_SFFichaLocal.getFichaActivaEvaluacion(nidRol == 4 ? "E" : "S", tipoFichaCurso);
        return ln_C_SFFichaCriterioLocal.getListaCriteriosByFicha_WS(ficha);
    }

    @WebMethod
    public List<BeanCriterioIndicadorWS> getLstIndicadoresByFichaCriterio_LN_WS(@WebParam(name = "arg0") int nidFicha,
                                                                                @WebParam(name = "arg1")
                                                                                int nidCriterio){
        return ln_C_SFCriterioIndicadorLocal.getLstIndicadoresByFichaCriterio_LN_WS(nidFicha, nidCriterio);
    }

    @WebMethod
    public List<BeanLeyendaWS> getLeyendasByCritIndicador_WS(@WebParam(name = "arg0") int nidCriterioIndicador){
        return ln_C_SFLeyendaLocal.getLeyendasByCriterioIndicador_LN_WS(nidCriterioIndicador);
    }

    @WebMethod
    public List<BeanAreaAcademica> getAreasAcademicas_WS(){
        return ln_C_SFAreaAcademicaLocal.getAreaAcademicaLN();
    }

    @WebMethod
    public List<BeanSede> getSedes_WS(){
        return ln_C_SFSedeLocal.getSedeLN();
    }


    @WebMethod
    public String evaluarDocente_WS(@WebParam(name = "arg0") Integer nidEvaluacion,
                                    @WebParam(name = "arg1") String cadenaIndisXValor,
                                    @WebParam(name = "arg2") Integer nidUsuario,
                                    @WebParam(name = "arg3") Integer nidLog){
        return ln_T_SFEvaluacionLocal.registrarEvaluacion_LN_WS(this.prepararListStringParametro(cadenaIndisXValor),nidEvaluacion,nidUsuario,nidLog);
    }

    @WebMethod(exclude = true)
    public List<BeanIndicadorValorWS> prepararListStringParametro(String parametro){
        String array1[] = parametro.split(";");
        List<BeanIndicadorValorWS> lstBeanIndiVal = new ArrayList<BeanIndicadorValorWS>();
        BeanIndicadorValorWS bean = null;
        for(int i = 0; i < array1.length; i++){
            String array2[] = array1[i].split(",");
            bean = new BeanIndicadorValorWS(new Integer(array2[0]),new Integer(array2[1]));
            lstBeanIndiVal.add(bean);
        }
        return lstBeanIndiVal;
    }

    @WebMethod
    public List<BeanCombo> getPlanificadores_LN_WS(){
        return ln_C_SFUtilsLocal.getPlanificadores_LN_WS();
    }

    @WebMethod
    public List<BeanCombo> getEvaluadores_LN_WS(){
        return ln_C_SFUtilsLocal.getEvaluadores_LN_WS();
    }

    @WebMethod
    public List<BeanComboString> getTipoVisita_LN_WS(){
        return ln_C_SFUtilsLocal.getTipoVisitaFromConstraint();
    }
}