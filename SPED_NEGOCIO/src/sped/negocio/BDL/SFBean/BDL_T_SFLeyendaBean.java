package sped.negocio.BDL.SFBean;

import javax.annotation.Resource;

import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import sped.negocio.BDL.IL.BDL_T_SFLeyendaLocal;
import sped.negocio.BDL.IR.BDL_T_SFLeyendaRemote;
import sped.negocio.entidades.eval.Leyenda;
import sped.negocio.entidades.eval.LeyendaPK;

@Stateless(name = "BDL_T_SFLeyenda", mappedName = "mapBDL_T_SFLeyenda")
public class BDL_T_SFLeyendaBean implements BDL_T_SFLeyendaRemote,
                                               BDL_T_SFLeyendaLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SPED_NEGOCIO")
    private EntityManager em;

    public BDL_T_SFLeyendaBean() {
    }

    public Leyenda persistLeyenda(Leyenda leyenda) {
        em.persist(leyenda);
        return leyenda;
    }

    public Leyenda mergeLeyenda(Leyenda leyenda) {
        return em.merge(leyenda);
    }

    public void removeLeyenda(Leyenda leyenda) {
        leyenda = em.find(Leyenda.class,new LeyendaPK(leyenda.getCriterioIndicador().getNidCriterioIndicador(),leyenda.getFichaValor().getNidFichaValor()));
        em.remove(leyenda);
    }
}
