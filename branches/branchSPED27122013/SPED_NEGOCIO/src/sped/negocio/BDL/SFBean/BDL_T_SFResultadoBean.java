package sped.negocio.BDL.SFBean;

import java.sql.CallableStatement;
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

import sped.negocio.BDL.IL.BDL_T_SFResultadoLocal;
import sped.negocio.BDL.IR.BDL_T_SFResultadoRemote;
import sped.negocio.entidades.eval.Resultado;
import sped.negocio.entidades.eval.ResultadoPK;

@Stateless(name = "BDL_T_SFResultado", mappedName = "mapBDL_T_SFResultado")
public class BDL_T_SFResultadoBean implements BDL_T_SFResultadoRemote,
                                              BDL_T_SFResultadoLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SPED_NEGOCIO")
    private EntityManager em;
    @Resource(mappedName = "java:/app/jdbc/jdbc/SPED_RemotoDS")
    private DataSource lubalDS;
    Connection conn = null;

    public BDL_T_SFResultadoBean() {
    }

    @TransactionAttribute(TransactionAttributeType.MANDATORY)
    public Resultado persistResultado(Resultado resultado) {
        em.persist(resultado);
        em.flush();
        return resultado;
    }

    @TransactionAttribute(TransactionAttributeType.MANDATORY)
    public Resultado mergeResultado(Resultado resultado) {
        return em.merge(resultado);
    }

    @TransactionAttribute(TransactionAttributeType.MANDATORY)
    public void removeResultado(Resultado resultado) {
        resultado = em.find(Resultado.class,
                    new ResultadoPK(resultado.getCriterioIndicador().getNidCriterioIndicador(),
                                    resultado.getEvaluacion().getNidEvaluacion()));
        em.remove(resultado);
    }

    public int removerResultadosByEvaluacion(int nidEvaluacion) {
        try {
            conn = lubalDS.getConnection();
            String sqlPuro = "DELETE FROM evdresu WHERE nidEvaluacion = ? ";
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
