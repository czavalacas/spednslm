package sped.negocio.LNSF.SFBean;

import java.util.ArrayList;
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
import sped.negocio.BDL.IL.BDL_C_SFUsuarioLocal;
import sped.negocio.LNSF.IL.LN_C_SFEvaluacionLocal;
import sped.negocio.LNSF.IR.LN_C_SFEvaluacionRemote;
import sped.negocio.entidades.admin.AreaAcademica;
import sped.negocio.entidades.beans.BeanAreaAcademica;
import sped.negocio.entidades.beans.BeanEvaluacion;
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
            System.out.println("ESTADO EVALUACION " +bean.getEstadoEvaluacion() );
            if(bean.getEstadoEvaluacion().equals("EJECUTADO")){
                bean.setNidEstadoEvaluacion("1");
                System.out.println(" 1 ");
            }
            if(bean.getEstadoEvaluacion().equals("PENDIENTE")){
                bean.setNidEstadoEvaluacion("2");
                System.out.println(" 2 ");
            }
            if(bean.getEstadoEvaluacion().equals("NO REALIZADO")){
                bean.setNidEstadoEvaluacion("3");
                System.out.println(" 3 ");
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
                                                           String estado,
                                                           String nomProfesor,
                                                           String nomEvaluador,
                                                           Date fechaPlanifiacion,
                                                           Date fechaPlanifiacionF,
                                                           Date fechaEvaluacion,
                                                           Date fachaEvaluacionF){
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
            return transformLstEvaluacion(bdL_C_SFEvaluacionLocal.getEvaluacionesByUsuarioBDL(beanUsuario,
                                                                                              beanEva));
        }catch(Exception e){
            System.out.println(e.getMessage());
            return new ArrayList<BeanEvaluacion>();
        }
    }
    
    public List<BeanEvaluacion> transformLstEvaluacion(List<Evaluacion> lstEvaluacion){
        try{
            List<BeanEvaluacion> lstBean= new ArrayList();
            for(Evaluacion eva : lstEvaluacion){
                BeanEvaluacion beanEva = (BeanEvaluacion) mapper.map(eva, BeanEvaluacion.class);
                beanEva.setNombreEvaluador(bdL_C_SFUsuarioLocal.
                                           getNombresUsuarioByNidUsuario(beanEva.getNidEvaluador()));
                beanEva.setResultado(resultadoBeanEvaluacion(beanEva));
                lstBean.add(beanEva);
            }
            return lstBean;
        }catch(MappingException me){
            me.printStackTrace();
            return null;
        }
    }
    
    
    public String resultadoBeanEvaluacion(BeanEvaluacion beanEva){
        String resu = "-";
        int indicador = 0, criterio = 0,
            contCriterios, contIndicador, sumaIndicador;
        double promedio, sumaPromedio;
        if(beanEva.getResultadoLista().size() != 0){
            double total = 0;
            for(BeanResultado res : beanEva.getResultadoLista()){
            }
            resu = total+"";
        }
        return resu;
    }
}
