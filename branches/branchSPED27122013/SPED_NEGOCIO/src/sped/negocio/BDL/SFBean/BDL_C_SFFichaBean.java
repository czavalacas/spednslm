package sped.negocio.BDL.SFBean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import sped.negocio.BDL.IL.BDL_C_SFFichaLocal;
import sped.negocio.BDL.IR.BDL_C_SFFichaRemote;
import sped.negocio.entidades.eval.Ficha;

@Stateless(name = "BDL_C_SFFicha", mappedName = "mapBDL_C_SFFicha")
public class BDL_C_SFFichaBean implements BDL_C_SFFichaRemote, 
                                             BDL_C_SFFichaLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SPED_NEGOCIO")
    private EntityManager em;

    public BDL_C_SFFichaBean() {
    }

    /** <code>select o from Ficha o</code> */
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<Ficha> getFichaFindAll() {
        return em.createNamedQuery("Ficha.findAll", Ficha.class).getResultList();
    }
    
    public List<Ficha> getFichaByAttr_BDL() {
        String strQuery = "SELECT f " +
                          "FROM Ficha f ";
        try{
            List<Ficha> lstFichas = em.createQuery(strQuery).getResultList();
            int size = lstFichas == null ? 0 : lstFichas.size();
            if (size > 0) {
                return lstFichas;
            } else {
                return new ArrayList<Ficha>();
            }
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
    
    public Object[] getLastestFichaVersionByAttr_BDL(int year,
                                                    int mes,
                                                    String tipFicha,
                                                    String tipFichaCurso){
        try{
            String qlString = "SELECT max(SUBSTRING(f.descripcionVersion,15,Length(f.descripcionVersion)))," +
                              "       f.descripcionVersion," +
                              "       f.nidFicha," +
                              "       f.estadoFicha," +
                              "       f.fechaFicha  " +
                              "FROM Ficha f " +
                              " WHERE EXTRACT(YEAR FROM f.fechaFicha) = :year " +
                              " AND EXTRACT(MONTH FROM f.fechaFicha) = :mes " +
                              " AND f.tipoFicha = :tipFicha " +
                              " AND f.tipoFichaCurso = :tipFichaCurso ";
            return (Object[]) em.createQuery(qlString).setParameter("year",year).
                                                        setParameter("mes",mes).
                                                        setParameter("tipFicha",tipFicha).
                                                        setParameter("tipFichaCurso",tipFichaCurso).
                                                        getSingleResult();
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
}