package sped.negocio.BDL.SFBean;

import java.util.List;

import javax.annotation.Resource;

import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import sped.negocio.BDL.IL.BDL_C_SFResultadoLocal;
import sped.negocio.BDL.IR.BDL_C_SFResultadoRemote;
import sped.negocio.entidades.beans.BeanResultado;
import sped.negocio.entidades.eval.Resultado;
import sped.negocio.entidades.eval.ResultadoPK;

@Stateless(name = "BDL_C_SFResultado", mappedName = "mapBDL_C_SFResultado")
public class BDL_C_SFResultadoBean implements BDL_C_SFResultadoRemote, 
                                              BDL_C_SFResultadoLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SPED_NEGOCIO")
    private EntityManager em;

    public BDL_C_SFResultadoBean() {
    }

    /** <code>select o from Resultado o</code> */
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<Resultado> getResultadoFindAll() {
        return em.createNamedQuery("Resultado.findAll", Resultado.class).getResultList();
    }
    
    public boolean fichaUsadaEnEvaluacion(int nidFicha){
        try{
            String strQuery =  "SELECT COUNT(1) " +
                               "FROM Resultado r " +
                               "WHERE r.criterioIndicador.fichaCriterio.ficha.nidFicha = :nidFicha";
            Object oResultados = em.createQuery(strQuery).setParameter("nidFicha",nidFicha)
                                .getSingleResult();
            int iResultados = 0;
            if(oResultados != null){
                iResultados = Integer.parseInt(oResultados.toString());
            }
            if(iResultados == 0){
                return false;
            }else{
                return true;
            }
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }
    
    public Resultado findResultadoById(int criterioIndicador,
                                       int evaluacion){
        try {
            ResultadoPK id = new ResultadoPK();
            id.setCriterioIndicador(criterioIndicador);
            id.setEvaluacion(evaluacion);
            Resultado instance = em.find(Resultado.class, id);
            return instance;
        } catch (RuntimeException re) {
            throw re;
        }
    }
    
    public List<Resultado> getResultadoByEvaluacionCriterio_BDL(int nidEvaluacion,int nidCriterio){
        try {
            String qlString = "SELECT r " +
                              "FROM Resultado r " +
                              "WHERE r.criterioIndicador.fichaCriterio.criterio.nidCriterio = :nidCriterio " +
                              "AND r.evaluacion.nidEvaluacion = :nidEvaluacion ";
           return em.createQuery(qlString).setParameter("nidCriterio",nidCriterio).setParameter("nidEvaluacion",nidEvaluacion).getResultList();
       } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public double getValorResultadoByNidCriterioIndicador_Evaluacion(int nidCriterioIndicador,
                                                                     int nidEvaluacion){
        try{
            String strQuery = "SELECT r.valor " +
                              "FROM Resultado r " +
                              "WHERE r.criterioIndicador.nidCriterioIndicador = :nidCriterioIndicador " +
                              "AND   r.evaluacion.nidEvaluacion = :nidEvaluacion ";
            List lst = em.createQuery(strQuery).setParameter("nidCriterioIndicador",nidCriterioIndicador)
                                               .setParameter("nidEvaluacion",nidEvaluacion).getResultList();
            if(lst.isEmpty()){
                return 0;
            }else{
                return Double.parseDouble(lst.get(0).toString());
            }
        }catch(Exception e){
            e.printStackTrace();
            return 0;
        }
    }
}
