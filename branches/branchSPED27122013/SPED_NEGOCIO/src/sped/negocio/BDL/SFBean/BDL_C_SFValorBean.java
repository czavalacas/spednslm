package sped.negocio.BDL.SFBean;

import java.util.List;
import javax.annotation.Resource;

import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import sped.negocio.BDL.IL.BDL_C_SFUtilsLocal;
import sped.negocio.BDL.IL.BDL_C_SFValorLocal;
import sped.negocio.BDL.IR.BDL_C_SFValorRemote;
import sped.negocio.Utils.Utiles;
import sped.negocio.entidades.eval.Leyenda;
import sped.negocio.entidades.eval.Valor;

@Stateless(name = "BDL_C_SFValor", mappedName = "mapBDL_C_SFValor")
public class BDL_C_SFValorBean implements BDL_C_SFValorRemote, 
                                          BDL_C_SFValorLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SPED_NEGOCIO")
    private EntityManager em;

    public BDL_C_SFValorBean() {
    }
    
    public List<Valor> getValoresAll_BDL(int valMin,int valMax){
        return em.createQuery("SELECT v " +
                               "FROM Valor v " +
                               "WHERE v.valor BETWEEN :valMin and :valor").setParameter("valMin",valMin).
                                                                           setParameter("valor",(valMax - 1)).getResultList();
    }

    public int cantidadValoresByValor(double valor) {
        try {
            String strQuery = "SELECT COUNT(1) " +
                              "FROM Valor v " +
                              "WHERE v.valor = :valor ";
            List lst = em.createQuery(strQuery).setParameter("valor",valor).getResultList();
            if(lst.isEmpty()){
                return 0;
            }else{
                return Integer.parseInt(lst.get(0).toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    
    public Valor findValorById(int id) {
        try {
            Valor instance = em.find(Valor.class, id);
            return instance;
        } catch (RuntimeException re) {
            throw re;
        }
    }
   
    public String getRangoValorByFicha(int nidFicha){
        try{
            String strQuery = "SELECT " +
                              "MIN(v.valor),MAX(v.valor) " +
                              "FROM Valor v, FichaValor fv " +
                              "WHERE fv.valor.nidValoracion = v.nidValoracion " +//dfloresgonz 07.08.2014 se agrega nidValoracion
                              "AND fv.ficha.nidFicha = :nid_Ficha";
            Object[] o = (Object[]) em.createQuery(strQuery).setParameter("nid_Ficha", nidFicha)
                                               .getSingleResult();
            String rango = "";
            if(o != null){
                rango = o[0]+ " - "+o[1];
            }
            return rango;
        }catch(Exception e){
            e.printStackTrace();
            return "";
        }
    }
    
      public String getValoresByCriterio(int nidCriterio,
                                         int nidFicha){        
        try{
            String strQuery = "SELECT DISTINCT(v.valor) " +
                              "FROM Leyenda ley, Valor v " +
                              "WHERE ley.criterioIndicador.fichaCriterio.criterio.nidCriterio = :nidCriterio " +
                              "AND ley.fichaValor.ficha.nidFicha = :nid_Ficha " +
                              "AND ley.fichaValor.valor.nidValoracion = v.nidValoracion";
            List<Double> o = em.createQuery(strQuery).setParameter("nidCriterio", nidCriterio)
                                                     .setParameter("nid_Ficha", nidFicha)
                                                     .getResultList();
            if(o != null){
                if(o.size() > 0){
                    String vals = "";
                    for(int i = 0; i < o.size(); i++){
                        vals = vals + o.get(i)+"";
                        if((i + 1) < o.size()){
                            vals = vals + " , ";
                        }
                    }
                    return "Valores: "+vals;
                }
            }
            return null;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
}