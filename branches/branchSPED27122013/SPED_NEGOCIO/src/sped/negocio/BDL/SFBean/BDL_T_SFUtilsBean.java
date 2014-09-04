package sped.negocio.BDL.SFBean;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import sped.negocio.BDL.IL.BDL_T_SFUtilsLocal;
import sped.negocio.BDL.IR.BDL_T_SFUtilsRemote;
import sped.negocio.entidades.admin.Constraint;

@Stateless(name = "BDL_T_SFUtils", mappedName = "mapBDL_T_SFUtils")
public class BDL_T_SFUtilsBean implements BDL_T_SFUtilsRemote, 
                                          BDL_T_SFUtilsLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SPED_NEGOCIO")
    private EntityManager em;

    public BDL_T_SFUtilsBean() {
    }
    
    public void cambiarALeidoNotificacion(String tabla,Integer nidUsuario){
        try {
            String sql = "UPDATE "+tabla+ " " +
                         "t SET t.leido = '1' " +
                         "WHERE t.nidUsuario = "+nidUsuario;
            em.createNativeQuery(sql).executeUpdate();
       } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void actualizarConstraint(String newValor,String tabla, String campo, String oldValor){
        try {
            String sql = "UPDATE admcons SET valor = ? " +
                         "WHERE campo = ? " +
                         "AND tabla = ? " +
                         "AND valor = ? ";
            em.createNativeQuery(sql).setParameter(1,newValor).setParameter(2,campo)
                                     .setParameter(3, tabla).setParameter(4, oldValor).executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @TransactionAttribute(TransactionAttributeType.MANDATORY)
    public Constraint mergeConstraint(Constraint constraint) {
        return em.merge(constraint);
    }
}