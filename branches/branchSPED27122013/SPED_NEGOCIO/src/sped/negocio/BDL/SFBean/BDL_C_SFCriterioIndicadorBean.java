package sped.negocio.BDL.SFBean;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import sped.negocio.BDL.IL.BDL_C_SFCriterioIndicadorLocal;
import sped.negocio.BDL.IR.BDL_C_SFCriterioIndicadorRemote;
import sped.negocio.entidades.eval.CriterioIndicador;

@Stateless(name = "BDL_C_SFCriterioIndicador", mappedName = "mapBDL_C_SFCriterioIndicador")
public class BDL_C_SFCriterioIndicadorBean implements BDL_C_SFCriterioIndicadorRemote, 
                                                         BDL_C_SFCriterioIndicadorLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SPED_NEGOCIO")
    private EntityManager em;

    public BDL_C_SFCriterioIndicadorBean() {
    }

    /** <code>select o from CriterioIndicador o</code> */
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<CriterioIndicador> getCriterioIndicadorFindAll() {
        return em.createNamedQuery("CriterioIndicador.findAll", CriterioIndicador.class).getResultList();
    }
    
    public List<CriterioIndicador> getLstIndicadoresByFichaCriterio_BDL_WS(int nidFicha, int nidCriterio){
        try{
            String qlString = "SELECT ci " +
                              "FROM CriterioIndicador ci " +
                              "WHERE ci.fichaCriterio.criterio.nidCriterio = :nidCriterio " +
                              "AND ci.fichaCriterio.ficha.nidFicha = :nidFicha " +
                              "ORDER BY ci.orden ASC ";
            List<CriterioIndicador> lstIndicadores = em.createQuery(qlString).setParameter("nidFicha",nidFicha).setParameter("nidCriterio",nidCriterio).getResultList();
            int size = lstIndicadores == null ? 0 : lstIndicadores.size();
            if (size > 0) {
                return lstIndicadores;
            } else {
                return new ArrayList<CriterioIndicador>();
            }
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
