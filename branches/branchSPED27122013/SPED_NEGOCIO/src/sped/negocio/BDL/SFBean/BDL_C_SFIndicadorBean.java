package sped.negocio.BDL.SFBean;

import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import javax.persistence.Query;

import sped.negocio.BDL.IL.BDL_C_SFIndicadorLocal;
import sped.negocio.BDL.IR.BDL_C_SFIndicadorRemote;
import sped.negocio.entidades.admin.Profesor;
import sped.negocio.entidades.beans.BeanCriterio;
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
                if(beanIndicador.getLstCritsArbol() != null){
                    if(beanIndicador.getLstCritsArbol().size() > 0){
                        Iterator it = beanIndicador.getLstCritsArbol().iterator();
                        while(it.hasNext()){
                            Integer crit = (Integer) it.next();
                            qlString = qlString.concat(" AND i.nidIndicador <> "+crit);
                        }
                    }
                }else{
                    if(beanIndicador.getNidIndicador() != 0){
                        qlString = qlString.concat(" AND i.nidIndicador = :nidIndicador ");
                    }
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
    
    public List<Indicador> getIndicadoresByDescripcion(String Indicador){
        try{
            String qlString = "SELECT i " +
                              "FROM Indicador i " +
                              "WHERE upper(i.descripcionIndicador) like :descripcionIndicador ";            
            Query query = em.createQuery(qlString);
            if(Indicador != null){
                query.setParameter("descripcionIndicador","%"+Indicador.toUpperCase()+"%");   
            }
            return query.getResultList();
           
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
    
    public List getNombreIndicadores(){
        try{
            String ejbQl = " SELECT indi.descripcionIndicador" +
                           " FROM Indicador indi " +
                           " ORDER BY indi.descripcionIndicador ASC";   
            List lst = em.createQuery(ejbQl).getResultList();
            return lst;
        }catch(Exception e){
            return null;
        }
    }
    
    public List<Object[]> getNombreIndicadores_BDL(){
        try{
            String ejbQl = " SELECT indi.nidIndicador,indi.descripcionIndicador" +
                           " FROM Indicador indi " +
                           " ORDER BY indi.descripcionIndicador ASC";   
            return em.createQuery(ejbQl).getResultList();
        }catch(Exception e){
            return null;
        }
    }
    
    public Indicador getIndicadorByDescripcion(String descripcion) {
        try{
            String ejbQl = " SELECT ind " +
                           " FROM Indicador ind" +
                           " WHERE ind.descripcionIndicador='"+descripcion+"'";   
                Indicador indicad = (Indicador)em.createQuery(ejbQl).getSingleResult();           
                return indicad;         
        }catch(Exception e){
            e.printStackTrace();  
            return null;
        }   
    }
}