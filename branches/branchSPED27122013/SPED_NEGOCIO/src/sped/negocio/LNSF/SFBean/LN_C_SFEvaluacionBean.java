package sped.negocio.LNSF.SFBean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import net.sf.dozer.util.mapping.DozerBeanMapper;
import net.sf.dozer.util.mapping.MapperIF;

import net.sf.dozer.util.mapping.MappingException;

import sped.negocio.BDL.IL.BDL_C_SFEvaluacionLocal;
import sped.negocio.BDL.IL.BDL_C_SFLeyendaLocal;
import sped.negocio.BDL.IL.BDL_C_SFProblemaLocal;
import sped.negocio.BDL.IL.BDL_C_SFResultadoLocal;
import sped.negocio.BDL.IL.BDL_C_SFUsuarioLocal;
import sped.negocio.BDL.IL.BDL_C_SFUtilsLocal;
import sped.negocio.BDL.IL.BDL_C_SFValorLocal;
import sped.negocio.LNSF.IL.LN_C_SFEvaluacionLocal;
import sped.negocio.LNSF.IL.LN_C_SFResultadoCriterioLocal;
import sped.negocio.LNSF.IR.LN_C_SFEvaluacionRemote;
import sped.negocio.Utils.Utiles;
import sped.negocio.entidades.admin.AreaAcademica;
import sped.negocio.entidades.admin.Constraint;
import sped.negocio.entidades.admin.Usuario;
import sped.negocio.entidades.beans.BeanAreaAcademica;
import sped.negocio.entidades.beans.BeanConstraint;
import sped.negocio.entidades.beans.BeanCriterioWS;
import sped.negocio.entidades.beans.BeanEvaluacion;
import sped.negocio.entidades.beans.BeanEvaluacionWS;

import sped.negocio.entidades.beans.BeanEvaluacion_DP;
import sped.negocio.entidades.beans.BeanFiltrosGraficos;

import sped.negocio.entidades.beans.BeanIndicadorValorWS;

import sped.negocio.entidades.beans.BeanResultado;
import sped.negocio.entidades.beans.BeanResultadoCriterio;
import sped.negocio.entidades.beans.BeanUsuario;
import sped.negocio.entidades.eval.Evaluacion;
import sped.negocio.entidades.eval.Resultado;
import sped.negocio.entidades.eval.ResultadoCriterio;

@Stateless(name = "LN_C_SFEvaluacion", mappedName = "SPED_APP-SPED_NEGOCIO-LN_C_SFEvaluacion")
public class LN_C_SFEvaluacionBean implements LN_C_SFEvaluacionRemote, 
                                              LN_C_SFEvaluacionLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SPED_NEGOCIO")
    private EntityManager em;
    @EJB
    private BDL_C_SFEvaluacionLocal bdL_C_SFEvaluacionLocal;
    @EJB
    private BDL_C_SFUsuarioLocal bdL_C_SFUsuarioLocal;
    @EJB
    private LN_C_SFResultadoCriterioLocal ln_C_SFResultadoCriterioLocal;
    //dfloresgonz 04.02.2013
    @EJB
    private BDL_C_SFUtilsLocal bdL_C_SFUtilsLocal;
    @EJB
    private BDL_C_SFResultadoLocal bdL_C_SFResultadoLocal;
    @EJB
    private BDL_C_SFProblemaLocal bdL_C_SFProblemaLocal;
    @EJB
    private BDL_C_SFLeyendaLocal bdL_C_SFLeyendaLocal;
    private MapperIF mapper = new DozerBeanMapper();

    public LN_C_SFEvaluacionBean() {
    }
    
    public List<BeanEvaluacion> getPlanificacion(BeanEvaluacion beanEvaluacion){
        List<BeanEvaluacion> lstBean = new ArrayList();
        List<Evaluacion> lstAreaAcd = bdL_C_SFEvaluacionLocal.getPlanificacion(beanEvaluacion);
        for(Evaluacion a : lstAreaAcd){
            BeanEvaluacion bean = (BeanEvaluacion) mapper.map(a, BeanEvaluacion.class);
            bean.setNombreEvaluador(bdL_C_SFUsuarioLocal.getNombresUsuarioByNidUsuario(bean.getNidEvaluador()));
            bean.setNombrePLanificador(bdL_C_SFUsuarioLocal.getNombresUsuarioByNidUsuario(bean.getNidPlanificador()));
        //    System.out.println("ESTADO EVALUACION " +bean.getEstadoEvaluacion() );
            if(bean.getEstadoEvaluacion().equals("EJECUTADO")){
                bean.setNidEstadoEvaluacion("1");
             //   System.out.println(" 1 ");
            }
            if(bean.getEstadoEvaluacion().equals("PENDIENTE")){
                bean.setNidEstadoEvaluacion("2");
           //     System.out.println(" 2 ");
            }
            if(bean.getEstadoEvaluacion().equals("NO REALIZADO")){
                bean.setNidEstadoEvaluacion("3");
               // System.out.println(" 3 ");
            }
            lstBean.add(bean);
        }
        return lstBean;
    }
    
    public List<BeanEvaluacion> getEvaluacionesByUsuarioLN(BeanUsuario beanUsuario, 
                                                           int nidSede, 
                                                           int nidNivel,
                                                           int nidArea, 
                                                           int nidCurso, 
                                                           int nidGrado, 
                                                           String nomProfesor, 
                                                           String nomEvaluador,
                                                           Date fechaPlanifiacion, 
                                                           Date fechaPlanifiacionF,
                                                           Date fechaEvaluacion, 
                                                           Date fachaEvaluacionF) {
        try{
            BeanEvaluacion beanEva = new BeanEvaluacion();
            beanEva.setNidSede(nidSede);
            beanEva.setNidNivel(nidNivel);
            beanEva.setNidArea(nidArea);
            beanEva.setNidCurso(nidCurso);
            beanEva.setNidGrado(nidGrado);
            beanEva.setApellidosDocentes(nomProfesor);//nom y ap del docente
            beanEva.setNombreEvaluador(nomEvaluador);
            beanEva.setFechaMinPlanificacion(fechaPlanifiacion);
            beanEva.setFechaMaxPlanificacion(fechaPlanifiacionF);
            beanEva.setFechaMinEvaluacion(fechaEvaluacion);
            beanEva.setFechaMaxEvaluacion(fachaEvaluacionF);
            return transformLstEvaluacion(bdL_C_SFEvaluacionLocal.getEvaluacionesByUsuarioBDL(beanUsuario, beanEva));
        }catch(Exception e){
            System.out.println(e.getMessage());
            return new ArrayList<BeanEvaluacion>();
        }
    }
    
    public List<BeanEvaluacion> transformLstEvaluacion(List<Evaluacion> lstEvaluacion){
        try{
            List<BeanEvaluacion> lstBean = new ArrayList();
            for(Evaluacion eva : lstEvaluacion){
                BeanEvaluacion beanEva = (BeanEvaluacion) mapper.map(eva, BeanEvaluacion.class);
                beanEva.setNombreEvaluador(bdL_C_SFUsuarioLocal.getNombresUsuarioByNidUsuario(beanEva.getNidEvaluador()));
                double nota = resultadoBeanEvaluacion(beanEva, eva);
                beanEva.setResultado(nota);
                beanEva.setColorResultado(colorNota(nota));
                if(beanEva.getNidProblema() != 0){
                    beanEva.setDescProblema(bdL_C_SFProblemaLocal.getDescripcionProblemaById(beanEva.getNidProblema()));
                }
                lstBean.add(beanEva);
            }
            return lstBean;
        }catch(MappingException me){
            me.printStackTrace();
            return null;
        }
    }
    
    
    public double resultadoBeanEvaluacion(BeanEvaluacion beanEva, Evaluacion eva){
        double resu = 0;
        int tamano = beanEva.getResultadoCriterioList().size();
        if(tamano != 0){
            beanEva.setResultadoCriterioList(ln_C_SFResultadoCriterioLocal.transformLstResultadoCriterio(eva.getResultadoCriterioList()));
            double total = 0;
            for(int i = 0; i < tamano; i++){
                total = total + beanEva.getResultadoCriterioList().get(i).getValor();
            }
            resu = (total/tamano);
        }
        return resu;
    }
    
    /**
     * Metodo para calcular y obtener el resultado de la evaluacion para el MOVIL
     * @author dfloresgonz
     * @param eva entidad evaluacion 
     * @return
     */
    
    public double resultadoBeanEvaluacionAux_WS(Evaluacion eva){
        double resu = 0;
        int tamano = eva.getResultadoCriterioList().size();
        if(tamano != 0){
            double total = 0;
            for(int i = 0; i < tamano; i++){
                total = total + eva.getResultadoCriterioList().get(i).getValor();
            }
            int r = (int) Math.round( (total/tamano) * 100);
            resu = r / 100.0;
        }
        return resu;
    }
    
    public String colorNota(double nota){
        String color="";
        if(nota == 0){
            color = "White";
        }else if(nota <= 5){
            color = "Red";
        }else if(nota <= 10){
            color = "Orange";
        }else if(nota <= 15){
            color = "Yellow";
        }else{
            color = "Green";
        }
        return color;
    }
    
    /**
     * Metodo de Logica que retorna las planificaciones para el usuario Movil (WS)
     * @param nidRol
     * @param nidSede
     * @param nidAreaAcademica
     * @param nidUsuario
     * @param nombresProfesor
     * @param curso
     * @param nidSedeFiltro
     * @param nidAAFiltro
     * @author dfloresgonz
     * @since 04.02.2014
     * @return List<BeanEvaluacion>
     */
    public List<BeanEvaluacionWS> getPlanificaciones_LN_WS(int nidRol, int nidSede, int nidAreaAcademica,
                                                           int nidUsuario, String nombresProfesor, String curso,
                                                           int nidSedeFiltro, int nidAAFiltro) {
        List<BeanEvaluacionWS> lstBeanEvas = new ArrayList<BeanEvaluacionWS>();
        List<Evaluacion> lstEvas =
            bdL_C_SFEvaluacionLocal.getPlanificaciones_BDL_WS(nidRol, nidSede, nidAreaAcademica, nidUsuario,
                                                              nombresProfesor, curso, nidSedeFiltro, nidAAFiltro);
        for(Evaluacion eva : lstEvas){
            BeanEvaluacionWS beanEva = new BeanEvaluacionWS(); //(BeanEvaluacion) mapper.map(eva, BeanEvaluacion.class);
            beanEva.setNidEvaluacion(eva.getNidEvaluacion());
            beanEva.setNidEvaluador(eva.getNidEvaluador());
            beanEva.setEvaluador(bdL_C_SFUsuarioLocal.getNombresUsuarioByNidUsuario(eva.getNidEvaluador()));
            beanEva.setPlanificador(bdL_C_SFUsuarioLocal.getNombresUsuarioByNidUsuario(eva.getNidPlanificador()));
            beanEva.setProfesor(eva.getMain().getProfesor().getApellidos() + " " +
                                eva.getMain().getProfesor().getNombres());
            beanEva.setCurso(eva.getMain().getCurso().getDescripcionCurso());
            beanEva.setStartDate(eva.getStartDate());
            beanEva.setEndDate(eva.getEndDate());
            beanEva.setSede(eva.getMain().getAula().getSede().getDescripcionSede());
            beanEva.setAreaAcademica(eva.getMain().getCurso().getAreaAcademica().getDescripcionAreaAcademica());
            BeanConstraint constr = bdL_C_SFUtilsLocal.getCatalogoConstraints("tipo_visita", "evmeval", eva.getTipoVisita());
            beanEva.setTipoVisita(constr.getDescripcionAMostrar());
            beanEva.setAula(eva.getMain().getAula().getDescripcionAula());
           // Utiles.sysout("beanEva:"+beanEva.getNidEvaluacion()+" ape:"+beanEva.getMain().getProfesor().getApellidos()+", "+eva.getMain().getProfesor().getNombres()+" startdate:"+beanEva.getStartDate());
            lstBeanEvas.add(beanEva);
        }
        return lstBeanEvas;
    }
    
    /**
     * Metodo para consultar por filtros las evaluaciones - (WS) movil
     * @author dfloresgonz
     * @param nidRol
     * @param nidSede
     * @param nidAreaAcademica
     * @param nidUsuario
     * @param nombresProfesor
     * @param curso
     * @param nidSedeFiltro
     * @param nidAAFiltro
     * @param estado
     * @param fechaMin
     * @param fechaMax
     * @param tipoVisita
     * @param nidPlanificador
     * @param nidEvaluador
     * @return List<BeanEvaluacionWS>
     */
    public List<BeanEvaluacionWS> getEvaluaciones_LN_WS(int nidRol, int nidSede, int nidAreaAcademica, int nidUsuario,
                                                        String nombresProfesor, String curso, int nidSedeFiltro,
                                                        int nidAAFiltro, String estado, Date fechaMin, Date fechaMax,
                                                        String tipoVisita, Integer nidPlanificador,
                                                          Integer nidEvaluador){
        List<BeanEvaluacionWS> lstBeanEvas = new ArrayList<BeanEvaluacionWS>();
        List<Evaluacion> lstEvas =
            bdL_C_SFEvaluacionLocal.getEvaluaciones_BDL_WS(nidRol, nidSede, nidAreaAcademica, nidUsuario,
                                                           nombresProfesor, curso, nidSedeFiltro, nidAAFiltro, estado,
                                                           fechaMin, fechaMax, tipoVisita, nidPlanificador,
                                                                                     nidEvaluador);
        List<Double> lst = new ArrayList<Double>();
        double suma = 0.0;
        int cant1 = 0;
        int cant2 = 0;
        int cant3 = 0;
        for(Evaluacion eva : lstEvas){
            BeanEvaluacionWS beanEva = new BeanEvaluacionWS(); //(BeanEvaluacion) mapper.map(eva, BeanEvaluacion.class);
            beanEva.setNidEvaluacion(eva.getNidEvaluacion());
            beanEva.setNidEvaluador(eva.getNidEvaluador());
            beanEva.setEvaluador(bdL_C_SFUsuarioLocal.getNombresUsuarioByNidUsuario(eva.getNidEvaluador()));
            beanEva.setPlanificador(bdL_C_SFUsuarioLocal.getNombresUsuarioByNidUsuario(eva.getNidPlanificador()));
            beanEva.setProfesor(eva.getMain().getProfesor().getApellidos() + " " +
                                eva.getMain().getProfesor().getNombres());
            beanEva.setCurso(eva.getMain().getCurso().getDescripcionCurso());
            beanEva.setStartDate(eva.getStartDate());
            beanEva.setEndDate(eva.getEndDate());
            beanEva.setSede(eva.getMain().getAula().getSede().getDescripcionSede());
            beanEva.setAreaAcademica(eva.getMain().getCurso().getAreaAcademica().getDescripcionAreaAcademica());
            BeanConstraint constr = bdL_C_SFUtilsLocal.getCatalogoConstraints("tipo_visita", "evmeval", eva.getTipoVisita());
            beanEva.setTipoVisita(constr.getDescripcionAMostrar());
            beanEva.setAula(eva.getMain().getAula().getDescripcionAula());
            double nota = resultadoBeanEvaluacionAux_WS(eva);
            lst.add(nota);
            if(nota < 11.00){
                cant1++;
            }
            if(nota >= 11.00 && nota < 16.00){
                cant2++;
            }
            if(nota >= 16.00){
                cant3++;
            }
            suma = suma + nota;
            beanEva.setNotaFinal(nota);
            lstBeanEvas.add(beanEva);
        }
        if(lst.size() > 0){
            BeanEvaluacionWS beanEvaGraficos = this.getLast(lstBeanEvas);
            double max = Collections.max(lst);
            double min = Collections.min(lst);
            double promedio = suma / lst.size();
            beanEvaGraficos.setNotaMin(min);
            beanEvaGraficos.setNotaMax(max);
            beanEvaGraficos.setNotaProm(promedio);
            beanEvaGraficos.setCeroDiez("0-10");
            beanEvaGraficos.setOnceQuince("11-15");
            beanEvaGraficos.setResto("16-20");
            beanEvaGraficos.setCeroDiezCant(cant1);
            beanEvaGraficos.setOnceQuinceCant(cant2);
            beanEvaGraficos.setRestoCant(cant3);            
        }
        return lstBeanEvas;
    }
    
    /**
     * Metodo para traer la ultima evaluacion de la lista y alli agregarle data general - WS
     * @author dfloresgonz
     * @param lstBeanEvas
     * @return
     */
    private BeanEvaluacionWS getLast(List<BeanEvaluacionWS> lstBeanEvas){
        return lstBeanEvas.get(lstBeanEvas.size() - 1);
    }
    
    public List<BeanEvaluacion> getDesempenoEvaluacionbyFiltroLN(int tipoBusqueda,
                                                                 String nombre,
                                                                 String estado,                                                                 
                                                                 String desProblema,
                                                                 String desRol,
                                                                 List lstnidRol,
                                                                 List lstnidEva,
                                                                 List lstnidSede,
                                                                 List lstnidArea,                                                                 
                                                                 Date fechaPlanifiacion,
                                                                 Date fechaPlanifiacionF,
                                                                 Date fechaEvaluacion,
                                                                 Date fachaEvaluacionF){
        try{
            List<BeanEvaluacion> lstBeanEva = new ArrayList();
            BeanEvaluacion beanEva = new BeanEvaluacion();            
            beanEva.setFechaMinPlanificacion(fechaPlanifiacion);
            beanEva.setFechaMaxPlanificacion(fechaPlanifiacionF);
            beanEva.setFechaMinEvaluacion(fechaEvaluacion);
            beanEva.setFechaMaxEvaluacion(fachaEvaluacionF);
            beanEva.setNombreEvaluador(nombre);
            beanEva.setEstadoEvaluacion(estado);
            beanEva.setDescRol(desRol);
            if(desProblema !=null){
                int idProb = bdL_C_SFProblemaLocal.getNidProblemaByDescripcion(desProblema);
                if(idProb != 0){
                    beanEva.setNidProblema(idProb);
                }
            }
            List listaBD = bdL_C_SFEvaluacionLocal.getDesempenoEvaluacionbyFiltroBDL(tipoBusqueda,
                                                                                     lstnidRol,
                                                                                     lstnidEva,
                                                                                     lstnidSede,
                                                                                     lstnidArea,
                                                                                     beanEva);
            for(Object dato : listaBD){
                BeanEvaluacion bean = new BeanEvaluacion();
                Object[] datos = (Object[]) dato;
                if(tipoBusqueda == 2){
                    bean = (BeanEvaluacion) mapper.map((Evaluacion) datos[0], BeanEvaluacion.class);
                    BeanUsuario usu = (BeanUsuario)mapper.map((Usuario) datos[1], BeanUsuario.class);
                    if(bean.getNidProblema() != 0){
                        bean.setDescProblema(bdL_C_SFProblemaLocal.getDescripcionProblemaById(bean.getNidProblema()));
                    }
                    bean.setUsuario(usu);
                }                                   
                if(tipoBusqueda == 1 || tipoBusqueda == 3 || tipoBusqueda == 5){
                    bean.setCantEjecutado(Integer.parseInt(""+datos[0]));
                    bean.setCantPendiente(Integer.parseInt(""+datos[1]));
                    bean.setCantNoEjecutado(Integer.parseInt(""+datos[2]));
                    bean.setCantNoJEjecutado(Integer.parseInt(""+datos[3]));
                    desempenoEvaluador(bean);
                    if(tipoBusqueda == 1){
                        bean.setNombreEvaluador(""+datos[4]);
                    }
                    if(tipoBusqueda == 3){
                        bean.setEndDate((Date)datos[4]);
                    }
                    if(tipoBusqueda == 5){
                        bean.setDescripcion(""+datos[4]);
                    }                    
                }                 
                if(tipoBusqueda == 4){                       
                    int id = Integer.parseInt(""+datos[1]);
                    bean.setCantProblema(Integer.parseInt(""+datos[0]));
                    bean.setNidProblema(id);
                    bean.setDescProblema(bdL_C_SFProblemaLocal.getDescripcionProblemaById(id));
                }
                lstBeanEva.add(bean);
            }                         
            return lstBeanEva;
        } catch(Exception e){
            e.printStackTrace();
            return new ArrayList();
        }        
    }
    
    public void desempenoEvaluador(BeanEvaluacion eva){
        double porcentaje = 0;
        double cant = eva.getCantEjecutado() + 
                      eva.getCantNoEjecutado() + eva.getCantNoJEjecutado();
        if(cant != 0){
            porcentaje = eva.getCantEjecutado()/cant;
        }
        eva.setPorcentajeDesempeno(porcentaje*100);
        eva.setColorResultado(colorNota(porcentaje*20));
    }
   
    public List<BeanConstraint> getTipoVisitaLN() {
        List<BeanConstraint> lstBean = new ArrayList();
        List<Constraint> lstConstr = bdL_C_SFEvaluacionLocal.getTipoVisita();
        for (Constraint a : lstConstr) {
            BeanConstraint bean = (BeanConstraint) mapper.map(a, BeanConstraint.class);
            lstBean.add(bean);
}
        return lstBean;
    }

    public BeanConstraint getTipoVisita_ByValorLN(String valor) {
        Constraint entida = bdL_C_SFEvaluacionLocal.getTipoVisitaByValor(valor);
        BeanConstraint bean = (BeanConstraint) mapper.map(entida, BeanConstraint.class);
        return bean;
    }

    public BeanEvaluacion getEvaluacionById_LN(String nidDate) {
        Evaluacion entida = bdL_C_SFEvaluacionLocal.getEvaluacionById(nidDate);
        BeanEvaluacion bean = (BeanEvaluacion) mapper.map(entida, BeanEvaluacion.class);
        return bean;
    }    
   

    public List<BeanEvaluacion> getEvaluaciones_LN(String fechaHoy, Integer nidAreaAcademica, Integer nidEvaluador,
                                                   String dniProfesor, String nidCurso, Integer nidSede) {
        List<BeanEvaluacion> lstBean = new ArrayList();
        List<Evaluacion> lstEva = bdL_C_SFEvaluacionLocal.getEvaluaciones(fechaHoy,nidAreaAcademica,nidEvaluador,dniProfesor,nidCurso,nidSede);
        for (Evaluacion a : lstEva) {
            BeanEvaluacion bean = (BeanEvaluacion) mapper.map(a, BeanEvaluacion.class);
            lstBean.add(bean);
        }
        return lstBean;
    }
    /**
     * Metodo que trae la evaluacion consultada en el Movil - WS
     * @author dfloresgonz
     * @since 23.02.2014
     * @param nidEvaluacion
     * @return BeanEvaluacionWS
     */
    public BeanEvaluacionWS getEvaluacionById_LN_WS(Integer nidEvaluacion){
        Evaluacion evaluacion = bdL_C_SFEvaluacionLocal.findEvaluacionById(nidEvaluacion);
        BeanEvaluacionWS beanEvaluacion = new BeanEvaluacionWS();
       // beanEvaluacion.setNotaFinal(this.resultadoBeanEvaluacionAux_WS(evaluacion));
        beanEvaluacion.setComentarioEvaluador(evaluacion.getComentario_evaluador());
        beanEvaluacion.setComentarioProfesor(evaluacion.getComentario_profesor());
        int cantCrit = evaluacion.getResultadoCriterioList().size();
        int size = 0;
        BeanCriterioWS[] beanCritVec = new BeanCriterioWS[cantCrit];
        BeanCriterioWS bcws = null;
        for(int i = 0; i < evaluacion.getResultadoCriterioList().size(); i++){
            ResultadoCriterio rc = evaluacion.getResultadoCriterioList().get(i);
            bcws = new BeanCriterioWS();
            bcws.setDescripcionCriterio(rc.getFichaCriterio().getCriterio().getDescripcionCriterio());
            bcws.setNota(rc.getValor());
            if(i == 0){
                size = rc.getFichaCriterio().getFicha().getFichaValorLista().size();
            }
            List<Resultado> resultados = bdL_C_SFResultadoLocal.getResultadoByEvaluacionCriterio_BDL(nidEvaluacion,rc.getFichaCriterio().getCriterio().getNidCriterio());
            BeanIndicadorValorWS bindi = null;
            BeanIndicadorValorWS[] indicadoresVec = new BeanIndicadorValorWS[resultados.size()];
            for(int j = 0; j < resultados.size(); j++){
                bindi = new BeanIndicadorValorWS();
                bindi.setIndice((j+1));
                bindi.setDescripcionIndicador(resultados.get(j).getCriterioIndicador().getIndicador().getDescripcionIndicador());
                bindi.setValor(new Integer(resultados.get(j).getValor()));
                bindi.setLeyenda(bdL_C_SFLeyendaLocal.getLeyendabyEvaluacion_BDL(rc.getFichaCriterio().getCriterio().getNidCriterio(), 
                                                                                    resultados.get(j).getCriterioIndicador().getIndicador().getNidIndicador(), 
                                                                                    rc.getFichaCriterio().getFicha().getNidFicha(), 
                                                                                    resultados.get(j).getValor()));
                indicadoresVec[j] = bindi;
            }
            bcws.setIndicadoresVec(indicadoresVec);
            beanCritVec[i] = bcws;
        }
        beanEvaluacion.setCriterios(beanCritVec);
        beanEvaluacion.setValores("1-"+size);
        return beanEvaluacion;
    }
    //terminarrrrrr
    public List<BeanEvaluacion_DP> desempenoDocentePorEvaluacion(BeanFiltrosGraficos beanFiltros,String fechaHoy){
    List<BeanEvaluacion_DP> lstBeanEvas = new ArrayList<BeanEvaluacion_DP>();
    List<Evaluacion> lstEvas = new ArrayList<Evaluacion>();
        lstEvas=  bdL_C_SFEvaluacionLocal.getEvaluaciones_DeDocente(beanFiltros,fechaHoy);
        for(Evaluacion eva : lstEvas){
            BeanEvaluacion_DP beanEva = new BeanEvaluacion_DP();
            beanEva.setNidEvaluacion(eva.getNidEvaluacion());
            beanEva.setNidEvaluador(eva.getNidEvaluador());
            beanEva.setEvaluador(bdL_C_SFUsuarioLocal.getNombresUsuarioByNidUsuario(eva.getNidEvaluador()));
            beanEva.setPlanificador(bdL_C_SFUsuarioLocal.getNombresUsuarioByNidUsuario(eva.getNidPlanificador()));
            beanEva.setProfesor(eva.getMain().getProfesor().getApellidos() + " " +
                                eva.getMain().getProfesor().getNombres());
            beanEva.setCurso(eva.getMain().getCurso().getDescripcionCurso());
            beanEva.setStartDate(eva.getStartDate());
            beanEva.setEndDate(eva.getEndDate());
            beanEva.setSede(eva.getMain().getAula().getSede().getDescripcionSede());
            beanEva.setAreaAcademica(eva.getMain().getCurso().getAreaAcademica().getDescripcionAreaAcademica());
            BeanConstraint constr = bdL_C_SFUtilsLocal.getCatalogoConstraints("tipo_visita", "evmeval", eva.getTipoVisita());
            beanEva.setTipoVisita(constr.getDescripcionAMostrar());
            beanEva.setAula(eva.getMain().getAula().getDescripcionAula());     
            double nota = resultadoBeanEvaluacionAux_WS(eva);  
            beanEva.setNotaFinal(nota);
            lstBeanEvas.add(beanEva);
        }
    
          //traer el peso de cada valor de indicador por evaluacion.
      
       // System.out.println("RESULTADO DE LOS INDICADORES "+resu);
        return lstBeanEvas;
}
        /**
         * Metodo que trae el promedio de las notas finales de una lista de evaluaciones
         * @author czavalacas
         * @since 27.02.2014
         * @param list<BeanEvaluacionWS>
         * @return resultado
         */
        
    public double promedioGeneralPorFiltroDesempeñoDocente(List<BeanEvaluacion_DP> listaEva_WS){
        double suma=0;
        double resu=0;
        List<BeanEvaluacion_DP> listEvaWS=new ArrayList<BeanEvaluacion_DP>();
        listEvaWS=listaEva_WS;
        for(BeanEvaluacion_DP a: listEvaWS){
            System.out.println("nota final "+a.getNotaFinal()+" - "+a.getAreaAcademica()+" "+a.getCurso());
            suma=suma+a.getNotaFinal();
        }
        if(listEvaWS!=null){
        if(listEvaWS.size()!=0){
            resu=suma/listEvaWS.size(); 
        }
        }       
        return resu;        
    }
        
        /**Si beanFiltros.nidIndicador!= null  : Trae el promedio del Indicador elejido */
        public double resultadoPromediodeIndicador(BeanFiltrosGraficos beanFiltros, Integer nidIndicador, String fechaHoy){
            List<Evaluacion> listaEvaluaciones =
                bdL_C_SFEvaluacionLocal.getEvaluaciones_DeDocente(beanFiltros, fechaHoy); 
            double resu = 0;
            int tamano = listaEvaluaciones.size();
            int size=0;
            if(tamano != 0){
                double total = 0;
                for(int i = 0; i < tamano; i++){
                    for(int j=0; j<listaEvaluaciones.get(i).getResultadoLista().size(); j++){
                        if(listaEvaluaciones.get(i).getResultadoLista().get(j).getCriterioIndicador().getIndicador().getNidIndicador()==nidIndicador){
                        total = total + listaEvaluaciones.get(i).getResultadoLista().get(j).getValor();System.out.println("Valor del indicador "+listaEvaluaciones.get(i).getResultadoLista().get(j).getValor());
                   size++;
                    }
                   
                    }
                }
                int r = (int) Math.round( (total/size) * 100);
                resu = r / 100.0;
                System.out.println("VALOR RESU "+resu);
            }
            return resu;
        }    
    }
