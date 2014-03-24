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
import sped.negocio.LNSF.IL.LN_C_SFCorreoLocal;
import sped.negocio.LNSF.IL.LN_C_SFCriterioIndicadorLocal;
import sped.negocio.LNSF.IL.LN_C_SFEvaluacionLocal;
import sped.negocio.LNSF.IL.LN_C_SFFichaCriterioLocal;
import sped.negocio.LNSF.IL.LN_C_SFFichaLocal;
import sped.negocio.LNSF.IL.LN_C_SFLeyendaLocal;
import sped.negocio.LNSF.IL.LN_C_SFMainLocal;
import sped.negocio.LNSF.IL.LN_C_SFParteOcurrenciaLocal;
import sped.negocio.LNSF.IL.LN_C_SFPermisosLocal;
import sped.negocio.LNSF.IL.LN_C_SFSedeLocal;
import sped.negocio.LNSF.IL.LN_C_SFUsuarioLocal;
import sped.negocio.LNSF.IL.LN_C_SFUtilsLocal;
import sped.negocio.LNSF.IL.LN_T_SFEvaluacionLocal;
import sped.negocio.LNSF.IL.LN_T_SFParteOcurrenciaLocal;
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
import sped.negocio.entidades.beans.BeanMainWS;
import sped.negocio.entidades.beans.BeanParteOcurrencia;
import sped.negocio.entidades.beans.BeanPermiso;
import sped.negocio.entidades.beans.BeanPie;
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
    @EJB
    private LN_C_SFMainLocal ln_C_SFMainLocal;
    @EJB
    private LN_T_SFParteOcurrenciaLocal ln_T_SFParteOcurrenciaLocal;
    @EJB
    private LN_C_SFParteOcurrenciaLocal ln_C_SFParteOcurrenciaLocal;
    @EJB
    private LN_C_SFCorreoLocal ln_C_SFCorreoLocal;

    @WebMethod
    public BeanUsuario autenticarUsuarioLN(@WebParam(name = "arg0") String usuario,
                                           @WebParam(name = "arg1") String clave,
                                           @WebParam(name = "arg2") String cadenaPhoneData){
        return ln_C_SFUsuarioLocal.autenticarUsuarioLN_WS(usuario, clave,cadenaPhoneData);
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
        if(nidRol == 1 || nidRol == 2 || nidRol == 4 || nidRol == 5 || nidRol == 3){//Solo Director,EvaXArea,EvaXSede,Profesor y EvaGeneral pueden consultar
            if(estado == null){
                estado = "EJECUTADO";
            }else{
                if(estado.equals("0")){
                    estado = "EJECUTADO";
                }
            }
            if(fechaMin == null){
                fechaMin = Utiles.removeTime(new Date());
            }
            if(fechaMax == null){
                fechaMax = Utiles.removeTime(new Date());
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
        int ficha = ln_C_SFFichaLocal.getFichaActivaEvaluacion(nidRol == 2 ? "E" : "S", tipoFichaCurso);
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
    public List<BeanCombo> getAreasAcademicas_WS(){
        return ln_C_SFUtilsLocal.getAreas_LN_WS();
    }

    @WebMethod
    public List<BeanSede> getSedes_WS(){
        return ln_C_SFSedeLocal.getSedeLN();
    }


    @WebMethod
    public String evaluarDocente_WS(@WebParam(name = "arg0") Integer nidEvaluacion,
                                    @WebParam(name = "arg1") String cadenaIndisXValor,
                                    @WebParam(name = "arg2") Integer nidUsuario,
                                    @WebParam(name = "arg3") Integer nidLog,
                                    @WebParam(name = "arg4") String comentarioEvaluador){
        return ln_T_SFEvaluacionLocal.registrarEvaluacion_LN_WS(this.prepararListStringParametro(cadenaIndisXValor),
                                                                    nidEvaluacion,
                                                                    nidUsuario,
                                                                    nidLog,
                                                                    comentarioEvaluador);
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

    @WebMethod
    public BeanEvaluacionWS getDetalleEvaluacionById_WS(@WebParam(name = "arg0") Integer nidEvaluacion){
        return ln_C_SFEvaluacionLocal.getEvaluacionById_LN_WS(nidEvaluacion);
    }

    @WebMethod
    public List<BeanCombo> getUsuarios_LN_WS(){
        return ln_C_SFUtilsLocal.getUsuarios_LN_WS();
    }

    @WebMethod
    public List<BeanMainWS> getMainByAttr_WS(@WebParam(name = "arg0") Integer nidSede,
                                             @WebParam(name = "arg1") String profesor,
                                             @WebParam(name = "arg2") String curso,
                                             @WebParam(name = "arg3") String aula){
        return ln_C_SFMainLocal.getMainByAttr_LN_WS(nidSede, 
                                                       profesor,
                                                       curso, 
                                                       aula);
    }

    @WebMethod
    public List<BeanCombo> getProblemas_LN_WS(){
        return ln_C_SFUtilsLocal.getProblemas_LN_WS();
    }

    @WebMethod
    public String registrarParteOcurrencia_WS(@WebParam(name = "arg0") Integer nidMain,
                                              @WebParam(name = "arg1") String comentario,
                                              @WebParam(name = "arg2") Integer nidProblema,
                                              @WebParam(name = "arg3") Integer nidUsuario,
                                              @WebParam(name = "arg4") Integer nidSede){
        return ln_T_SFParteOcurrenciaLocal.registrarParteOcurrencia_LN(nidMain, 
                                                                           comentario,
                                                                           nidProblema,
                                                                           nidUsuario,
                                                                           nidSede);
    }

    @WebMethod
    public List<BeanParteOcurrencia> getListaPartesOcurrencia_WS(@WebParam(name = "arg0") Date fechaMin,
                                                                 @WebParam(name = "arg1") Date fechaMax,
                                                                 @WebParam(name = "arg2") Integer nidProblema,
                                                                 @WebParam(name = "arg3") String nombreProfesor,
                                                                 @WebParam(name = "arg4") Integer nidSede,
                                                                 @WebParam(name = "arg5") Integer nidUsuario) {
        if(fechaMin == null){
            fechaMin = Utiles.removeTime(new Date());
        }
        if(fechaMax == null){
            fechaMax = Utiles.removeTime(new Date());
        }
        return ln_C_SFParteOcurrenciaLocal.getListaPartesOcurrencia_LN(fechaMin,
                                                                            fechaMax, 
                                                                            nidProblema,
                                                                            nombreProfesor, 
                                                                            nidSede, 
                                                                            nidUsuario);
    }

    @WebMethod
    public List<BeanPie> getPieProfesor_WS(@WebParam(name = "arg0") Date fechaMin,
                                           @WebParam(name = "arg1") Date fechaMax,
                                           @WebParam(name = "arg2") String dniProfesor,
                                           @WebParam(name = "arg3") Integer nidSede,
                                           @WebParam(name = "arg4") Integer nidUsuario){
        if(fechaMin == null){
            fechaMin = Utiles.removeTime(new Date());
        }
        if(fechaMax == null){
            fechaMax = Utiles.removeTime(new Date());
        }
        return ln_C_SFParteOcurrenciaLocal.getPiePO_ByProfesor_LN_WS(fechaMin, 
                                                                          fechaMax,
                                                                          dniProfesor,
                                                                          nidSede, 
                                                                          nidUsuario);
    }

    @WebMethod
    public String recuperarClave(@WebParam(name = "arg0") String correo){
        String msj = ln_C_SFCorreoLocal.recuperarClave(correo);
        return msj.equals("000") ? "Se envio informacion para recuperar su clave a su correo." : "Hubo un error, trate nuevamente o comuniquese con el administrador";
    }
}