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
import sped.negocio.BDL.IL.BDL_C_SFProblemaLocal;
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
import sped.negocio.entidades.beans.BeanEvaluacion;
import sped.negocio.entidades.beans.BeanEvaluacionWS;
import sped.negocio.entidades.beans.BeanResultado;
import sped.negocio.entidades.beans.BeanUsuario;
import sped.negocio.entidades.eval.Evaluacion;

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
    private BDL_C_SFValorLocal bdL_C_SFValorLocal;
    @EJB
    private BDL_C_SFProblemaLocal bdL_C_SFProblemaLocal;
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
    
    public List<BeanEvaluacion> getEvaluacionesByUsuarioLN(BeanUsuario beanUsuario, int nidSede, int nidNivel,
                                                           int nidArea, int nidCurso, int nidGrado, String estado,
                                                           String nomProfesor, String nomEvaluador,
                                                           Date fechaPlanifiacion, Date fechaPlanifiacionF,
                                                           Date fechaEvaluacion, Date fachaEvaluacionF) {
        try{
            BeanEvaluacion beanEva = new BeanEvaluacion();
            beanEva.setNidSede(nidSede);
            beanEva.setNidNivel(nidNivel);
            beanEva.setNidArea(nidArea);
            beanEva.setNidCurso(nidCurso);
            beanEva.setNidGrado(nidGrado);
            beanEva.setEstadoEvaluacion(estado);
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
    
    public double resultadoBeanEvaluacionAux_WS(Evaluacion eva){
        double resu = 0;
        int tamano = eva.getResultadoCriterioList().size();
        if(tamano != 0){
          /*  beanEva.setResultadoCriterioList(ln_C_SFResultadoCriterioLocal.transformLstResultadoCriterio
                                             (eva.getResultadoCriterioList()));*/
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
            BeanConstraint constr =
                bdL_C_SFUtilsLocal.getCatalogoConstraints("tipo_visita", "evmeval", eva.getTipoVisita());
            beanEva.setTipoVisita(constr.getDescripcionAMostrar());
            beanEva.setAula(eva.getMain().getAula().getDescripcionAula());
           // Utiles.sysout("beanEva:"+beanEva.getNidEvaluacion()+" ape:"+beanEva.getMain().getProfesor().getApellidos()+", "+eva.getMain().getProfesor().getNombres()+" startdate:"+beanEva.getStartDate());
            lstBeanEvas.add(beanEva);
        }
        return lstBeanEvas;
    }
    
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
            BeanConstraint constr =
                bdL_C_SFUtilsLocal.getCatalogoConstraints("tipo_visita", "evmeval", eva.getTipoVisita());
            beanEva.setTipoVisita(constr.getDescripcionAMostrar());
            beanEva.setAula(eva.getMain().getAula().getDescripcionAula());
            double nota = resultadoBeanEvaluacionAux_WS(eva);Utiles.sysout("nota:"+nota);
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
           // Utiles.sysout("beanEva:"+beanEva.getNidEvaluacion()+" ape:"+beanEva.getMain().getProfesor().getApellidos()+", "+eva.getMain().getProfesor().getNombres()+" startdate:"+beanEva.getStartDate());
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
    
    private BeanEvaluacionWS getLast(List<BeanEvaluacionWS> lstBeanEvas){
        return lstBeanEvas.get(lstBeanEvas.size() - 1);
    }
    
    public List<BeanEvaluacion> getDesempenoEvaluacionbyFiltroLN(int tipoBusqueda, String parametro, String parametro2,
                                                                 List lstnidRol, List lstnidEva, List lstnidSede,
                                                                 List lstnidArea, Date fechaPlanifiacion,
                                                                 Date fechaPlanifiacionF, Date fechaEvaluacion,
                                                                 Date fachaEvaluacionF){
        try{
            List<BeanEvaluacion> lstBeanEva = new ArrayList();
            BeanEvaluacion beanEva = new BeanEvaluacion();
            beanEva.setNombreEvaluador(parametro);
            beanEva.setEstadoEvaluacion(parametro2);
            beanEva.setFechaMinPlanificacion(fechaPlanifiacion);
            beanEva.setFechaMaxPlanificacion(fechaPlanifiacionF);
            beanEva.setFechaMinEvaluacion(fechaEvaluacion);
            beanEva.setFechaMaxEvaluacion(fachaEvaluacionF);
            List listaBD =
                bdL_C_SFEvaluacionLocal.getDesempenoEvaluacionbyFiltroBDL(tipoBusqueda, lstnidRol, lstnidEva,
                                                                          lstnidSede, lstnidArea, beanEva);
            if(tipoBusqueda != 2){
                for(Object dato : listaBD){
                    BeanEvaluacion bean = new BeanEvaluacion();
                    Object[] datos = (Object[]) dato;                     
                    if(tipoBusqueda == 3){
                        bean = (BeanEvaluacion) mapper.map((Evaluacion) datos[6], BeanEvaluacion.class);
                    }
                    if(tipoBusqueda != 4){
                        Usuario usu = (Usuario)datos[1];
                        bean.setNidEvaluador(usu.getNidUsuario());
                        bean.setNombreEvaluador(usu.getNombres());
                        bean.setCantEjecutado(Integer.parseInt(""+datos[2]));
                        bean.setCantPendiente(Integer.parseInt(""+datos[3]));
                        bean.setCantNoEjecutado(Integer.parseInt(""+datos[4]));
                        bean.setCantNoJEjecutado(Integer.parseInt(""+datos[5])); 
                        if(tipoBusqueda == 5){
                            bean.setDescripcion(usu.getRol().getDescripcionRol());
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
            }
            if(tipoBusqueda == 2){
                lstBeanEva = transformLstEvaluacion(listaBD);
            }
            return lstBeanEva;
        } catch(Exception e){
            e.printStackTrace();
            return new ArrayList();
        }        
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
}
