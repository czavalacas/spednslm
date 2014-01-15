package sped.negocio.BDL.SFBean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import net.sf.dozer.util.mapping.DozerBeanMapper;
import net.sf.dozer.util.mapping.MapperIF;

import sped.negocio.BDL.IL.BDL_C_SFUtilsLocal;
import sped.negocio.BDL.IR.BDL_C_SFUtilsRemote;
import sped.negocio.Utils.Utiles;
import sped.negocio.entidades.admin.Constraint;
import sped.negocio.entidades.admin.ConstraintPK;
import sped.negocio.entidades.beans.BeanConstraint;

@Stateless(name = "BDL_C_SFUtils", mappedName = "mapBDL_C_SFUtils")
public class BDL_C_SFUtilsBean implements BDL_C_SFUtilsRemote,
                                             BDL_C_SFUtilsLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SPED_NEGOCIO")
    private EntityManager em;
    MapperIF mapper = new DozerBeanMapper();

    public BDL_C_SFUtilsBean() {
    }
    
    public BeanConstraint getCatalogoConstraints(String nombreCampo, 
                                                  String nombreTabla, 
                                                  String valorCampo){
        try{
            ConstraintPK id = new ConstraintPK(nombreCampo,nombreTabla,valorCampo);
            Constraint constraint = this.findConstraintById(id);
            if(constraint != null){
                BeanConstraint beanConstraint = (BeanConstraint) mapper.map(constraint, BeanConstraint.class);
                return beanConstraint;
            }else{
                Utiles.sysout("Constraint no encontrado");
                return null;
            }
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
    
    public Constraint findConstraintById(ConstraintPK id) {
        try {
            Constraint instance = em.find(Constraint.class, id);
            return instance;
        } catch (RuntimeException re) {
            throw re;
        }
    }
    
    /**
     * Trae una lista de la tabla Constraint
     * @param nombreCampo
     * @param nombreTabla
     * @return
     */
    public List<Constraint> getListaConstraintsBDL(String nombreCampo, String nombreTabla){
        try{
            String ejbQl = "SELECT o FROM Constraint o" +
                           " WHERE o.nombreCampo = :nombreC"+
                           " and o.nombreTabla = :nombreT";
            List<Constraint> lstConstraint = em.createQuery(ejbQl).setParameter("nombreC", nombreCampo).
                                                                   setParameter("nombreT", nombreTabla).getResultList();
            return lstConstraint;
        }catch(Exception e){
            return null;
        }
    }
    
    public int findCountByProperty(String atributoDesc, 
                                    Object atributoValor, 
                                    String entidad, 
                                    boolean changeCase,
                                    boolean isUpdate) {
         try {
             String queryString = "select count(model) " +
                                  "from "+entidad+" model " +
                                  "where 1 = 1 ";
             if(changeCase){
                 queryString = queryString.concat(" and upper(model."+ atributoDesc +") = upper(:propertyValue) ");
             }else{
                 queryString = queryString.concat(" and model."+ atributoDesc +" = :propertyValue ");
             }
             if(isUpdate){
                 queryString = queryString.concat(" and upper(model."+ atributoDesc +") <> :propertyValue ");
             }
             List lst = em.createQuery(queryString).setParameter("propertyValue",atributoValor).getResultList();
             if(lst.isEmpty()){
                 return 0;
             }else{
                 return Integer.parseInt(lst.get(0).toString());
             }
         } catch (Exception re) {
             re.printStackTrace();
             return 0;
         }
     }
}