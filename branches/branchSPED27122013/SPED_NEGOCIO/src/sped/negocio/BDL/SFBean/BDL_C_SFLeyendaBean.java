package sped.negocio.BDL.SFBean;

import javax.annotation.Resource;

import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import sped.negocio.BDL.IL.BDL_C_SFLeyendaLocal;
import sped.negocio.BDL.IR.BDL_C_SFLeyendaRemote;
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
        try{
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
}
