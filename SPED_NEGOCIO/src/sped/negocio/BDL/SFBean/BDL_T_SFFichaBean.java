package sped.negocio.BDL.SFBean;

import javax.annotation.Resource;

import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.ejb.TransactionAttribute;

import javax.ejb.TransactionAttributeType;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import sped.negocio.BDL.IL.BDL_T_SFFichaLocal;
import sped.negocio.BDL.IR.BDL_T_SFFichaRemote;
import sped.negocio.entidades.eval.Ficha;

@Stateless(name = "BDL_T_SFFicha", mappedName = "mapBDL_T_SFFicha")
public class BDL_T_SFFichaBean implements BDL_T_SFFichaRemote,
                                             BDL_T_SFFichaLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SPED_NEGOCIO")
    private EntityManager em;

    public BDL_T_SFFichaBean() {
    }

    @TransactionAttribute(TransactionAttributeType.MANDATORY)
    public Ficha persistFicha(Ficha ficha) {
        em.persist(ficha);
        em.flush();
        em.refresh(ficha);
        return ficha;
    }

    @TransactionAttribute(TransactionAttributeType.MANDATORY)
    public Ficha mergeFicha(Ficha ficha) {
        return em.merge(ficha);
    }

    @TransactionAttribute(TransactionAttributeType.MANDATORY)
    public void removeFicha(Ficha ficha) {
        ficha = em.find(Ficha.class, ficha.getNidFicha());
        em.remove(ficha);
    }
    
    @TransactionAttribute(TransactionAttributeType.MANDATORY)
    public void reactivarFichaYDesactivarElResto(String tipFicha,
                                                  String tipCursoFicha,
                                                  int nidFicha){
        try{
            String sql = "UPDATE `mmsi_sped`.`evmfich`  " +
                         "SET   `estado_ficha` = '0' " +
                         "WHERE `tipo_ficha` = '"+tipFicha+"' " +
                         "AND   `tipo_ficha_curso` = '"+tipCursoFicha+"' " +
                         "AND   `nidFicha` <> "+nidFicha+" ";
            em.createNativeQuery(sql).executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
