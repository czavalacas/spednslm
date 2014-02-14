package sped.negocio.BDL.SFBean;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import sped.negocio.BDL.IL.BDL_C_SFLeyendaLocal;
import sped.negocio.BDL.IR.BDL_C_SFLeyendaRemote;
import sped.negocio.Utils.Utiles;
import sped.negocio.entidades.eval.CriterioIndicador;
import sped.negocio.entidades.eval.Leyenda;

@Stateless(name = "BDL_C_SFLeyenda", mappedName = "mapBDL_C_SFLeyenda")
public class BDL_C_SFLeyendaBean implements BDL_C_SFLeyendaRemote, 
                                            BDL_C_SFLeyendaLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SPED_NEGOCIO")
    private EntityManager em;

    public BDL_C_SFLeyendaBean() {
    }
    
    public Leyenda getLeyendabyEvaluacion(CriterioIndicador cri,
                                          int nidFicha,
                                          int valorValoracion){        
        try{Utiles.sysout("cri:"+cri+" nidFicha:"+nidFicha+" valorValoracion:"+valorValoracion);
            String strQuery = "SELECT o " +
                              "FROM Leyenda o " +
                              "WHERE o.criterioIndicador = :crInd " +
                              "AND o.fichaValor.ficha.nidFicha = :nid_Ficha " +
                              "AND o.fichaValor.valor.valor = :valor";
            return (Leyenda) em.createQuery(strQuery).setParameter("crInd", cri)
                                                     .setParameter("nid_Ficha", nidFicha)
                                                     .setParameter("valor", valorValoracion)
                                                     .getSingleResult();
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
    
    /**
     * Metodo que retorna la lista de leyendas segun el indicador, usado para mostrar en el aplicativo Movil
     * @param nidCriterioIndicador id del criterioIndicador
     * @author dfloresgonz
     * @since 07.02.2014
     * @return List<Leyenda>
     */
    public List<Leyenda> getLeyendasByCriterioIndicador_BDL_WS(int nidCriterioIndicador){
        try{
            String qlString = "SELECT l " +
                              "FROM Leyenda l " +
                              "WHERE l.criterioIndicador.nidCriterioIndicador = :nidCriterioIndicador ";
            List<Leyenda> lstLeys = em.createQuery(qlString).setParameter("nidCriterioIndicador",nidCriterioIndicador).getResultList();
            int size = lstLeys == null ? 0 : lstLeys.size();
            if (size > 0) {
                return lstLeys;
            } else {
                return new ArrayList<Leyenda>();
            }
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
