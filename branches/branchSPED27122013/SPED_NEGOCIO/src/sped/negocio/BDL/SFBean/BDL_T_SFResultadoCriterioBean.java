package sped.negocio.BDL.SFBean;

import java.sql.Connection;

import java.sql.PreparedStatement;

import java.sql.SQLException;

import javax.annotation.Resource;

import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import javax.persistence.Query;

import javax.sql.DataSource;

import sped.negocio.BDL.IL.BDL_T_SFResultadoCriterioLocal;
import sped.negocio.BDL.IR.BDL_T_SFResultadoCriterioRemote;
import sped.negocio.entidades.eval.ResultadoCriterio;

@Stateless(name = "BDL_T_SFResultadoCriterio", mappedName = "mapBDL_T_SFResultadoCriterio")
public class BDL_T_SFResultadoCriterioBean implements BDL_T_SFResultadoCriterioRemote,
                                                      BDL_T_SFResultadoCriterioLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SPED_NEGOCIO")
    private EntityManager em;
    @Resource(mappedName = "java:/app/jdbc/jdbc/SPED_RemotoDS")
    private DataSource lubalDS;
    Connection conn = null;

    public BDL_T_SFResultadoCriterioBean() {
    }
    
    @TransactionAttribute(TransactionAttributeType.MANDATORY)
    public ResultadoCriterio persistResultadoCriterio(ResultadoCriterio resultadoCriterio) {
        em.persist(resultadoCriterio);
        return resultadoCriterio;
    }

    @TransactionAttribute(TransactionAttributeType.MANDATORY)
    public ResultadoCriterio mergeResultadoCriterio(ResultadoCriterio resultadoCriterio) {
        return em.merge(resultadoCriterio);
    }

    @TransactionAttribute(TransactionAttributeType.MANDATORY)
    public void removeResultadoCriterio(ResultadoCriterio resultadoCriterio) {
        resultadoCriterio = em.find(ResultadoCriterio.class, resultadoCriterio.getNidResultadoCriterio());
        em.remove(resultadoCriterio);
    }
    
    public int removerResultadoCriterioByEvaluacion(int nidEvaluacion) {
        try {
            conn = lubalDS.getConnection();
            String sqlPuro = "DELETE FROM evdrefc WHERE nidEvaluacion = ? ";
            PreparedStatement pst = conn.prepareStatement(sqlPuro);
            pst.setInt(1,nidEvaluacion);
            int resu = pst.executeUpdate();
            conn.close();
            return resu;
        }catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                conn.close();
            } catch (SQLException f) {
                f.printStackTrace();
            }
            return 0;
        }
        return 0;
    }
}