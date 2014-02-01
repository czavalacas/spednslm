package sped.negocio.BDL.SFBean;

import java.util.List;
import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import sped.negocio.BDL.IL.BDL_C_SFValorLocal;
import sped.negocio.BDL.IR.BDL_C_SFValorRemote;
import sped.negocio.entidades.eval.Valor;

@Stateless(name = "BDL_C_SFValor", mappedName = "mapBDL_C_SFValor")
public class BDL_C_SFValorBean implements BDL_C_SFValorRemote, 
                                             BDL_C_SFValorLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SPED_NEGOCIO")
    private EntityManager em;

    public BDL_C_SFValorBean() {
    }
    
    public List<Valor> getValoresAll_BDL(int valMin,int valMax){
        return em.createQuery("SELECT v " +
                               "FROM Valor v " +
                               "WHERE v.valor BETWEEN :valMin and :valor").setParameter("valMin",valMin).
                                                                           setParameter("valor",(valMax - 1)).getResultList();
    }
    
    public Valor findValorById(int id) {
        try {
            Valor instance = em.find(Valor.class, id);
            return instance;
        } catch (RuntimeException re) {
            throw re;
        }
    }
}