package sped.negocio.BDL.SFBean;

import javax.annotation.Resource;

import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import sped.negocio.BDL.IL.BDL_T_SFValorLocal;
import sped.negocio.BDL.IR.BDL_T_SFValorRemote;
import sped.negocio.entidades.eval.Valor;

@Stateless(name = "BDL_T_SFValor", mappedName = "mapBDL_T_SFValor")
public class BDL_T_SFValorBean implements BDL_T_SFValorRemote, 
                                          BDL_T_SFValorLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SPED_NEGOCIO")
    private EntityManager em;

    public BDL_T_SFValorBean() {
    }

    public Valor persistValor(Valor valor) {
        em.persist(valor);
        return valor;
    }

    public Valor mergeValor(Valor valor) {
        return em.merge(valor);
    }

    public void removeValor(Valor valor) {
        valor = em.find(Valor.class, valor.getNidValoracion());
        em.remove(valor);
    }
}