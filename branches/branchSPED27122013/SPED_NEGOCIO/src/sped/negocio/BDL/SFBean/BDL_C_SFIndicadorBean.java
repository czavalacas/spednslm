package sped.negocio.BDL.SFBean;

import java.util.List;

import javax.annotation.Resource;

import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import javax.persistence.Query;

import sped.negocio.BDL.IL.BDL_C_SFIndicadorLocal;
import sped.negocio.BDL.IR.BDL_C_SFIndicadorRemote;
import sped.negocio.entidades.beans.BeanIndicador;
import sped.negocio.entidades.eval.Indicador;

@Stateless(name = "BDL_C_SFIndicador", mappedName = "mapBDL_C_SFIndicador")
public class BDL_C_SFIndicadorBean implements BDL_C_SFIndicadorRemote, 
                                                 BDL_C_SFIndicadorLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SPED_NEGOCIO")
    private EntityManager em;

    public BDL_C_SFIndicadorBean() {
    }
    
    public List<Indicador> getIndicadoresByAttr_BD(BeanIndicador beanIndicador){
        try{
            String qlString = "SELECT i " +
                              "FROM Indicador i " +
                              "WHERE 1 = 1 ";
            if(beanIndicador != null){
                if(beanIndicador.getDescripcionIndicador() != null){
                    qlString = qlString.concat(" AND upper(i.descripcionIndicador) like :descripcionIndicador ");
                }
                if(beanIndicador.getNidIndicador() != 0){
                    qlString = qlString.concat(" AND i.nidIndicador = :nidIndicador ");
                }
            }
            Query query = em.createQuery(qlString);
            if(beanIndicador.getDescripcionIndicador() != null){
                query.setParameter("descripcionIndicador","%"+beanIndicador.getDescripcionIndicador().toUpperCase()+"%");
            }
            if(beanIndicador.getNidIndicador() != 0){
                query.setParameter("nidIndicador",beanIndicador.getNidIndicador());
            }
            return query.getResultList();
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
}