package sped.negocio.LNSF.SFBean;

import java.util.List;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import sped.negocio.BDL.IL.BDL_T_SFResultadoCriterioLocal;
import sped.negocio.LNSF.IL.LN_T_SFResultadoCriterioLocal;
import sped.negocio.LNSF.IR.LN_T_SFResultadoCriterioRemote;
import sped.negocio.Utils.Utiles;
import sped.negocio.entidades.beans.BeanCriterio;
import sped.negocio.entidades.beans.BeanCriterioIndicador;
import sped.negocio.entidades.eval.Evaluacion;
import sped.negocio.entidades.eval.FichaCriterio;
import sped.negocio.entidades.eval.ResultadoCriterio;

@Stateless(name = "LN_T_SFResultadoCriterio", mappedName = "mapLN_T_SFResultadoCriterio")
public class LN_T_SFResultadoCriterioBean implements LN_T_SFResultadoCriterioRemote, 
                                                        LN_T_SFResultadoCriterioLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SPED_NEGOCIO")
    private EntityManager em;
    @EJB
    private BDL_T_SFResultadoCriterioLocal bdL_T_SFResultadoCriterioLocal;

    public LN_T_SFResultadoCriterioBean() {
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void registrarResultadoCriterios_LN(List<BeanCriterio> lstBCrit,Evaluacion evaluacion){
        try {
            ResultadoCriterio rc = null;
            for(BeanCriterio bcrit : lstBCrit){
                rc = new ResultadoCriterio();
                rc.setEvaluacion(evaluacion);
                rc.setFichaCriterio(bcrit.getFichaCriterioAUX());
                int cantMax = bcrit.getCantidadValoresWS() * bcrit.getCantidadIndicadores();
                double notaCrit = (20 * bcrit.getSumaNota() ) / cantMax;
                rc.setValor(notaCrit);
                bdL_T_SFResultadoCriterioLocal.persistResultadoCriterio(rc);
            }
       } catch (Exception e) {
            e.printStackTrace();
        }
    }
}