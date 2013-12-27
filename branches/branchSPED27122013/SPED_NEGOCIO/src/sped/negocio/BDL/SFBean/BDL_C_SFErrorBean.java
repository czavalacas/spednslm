package sped.negocio.BDL.SFBean;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import sped.negocio.BDL.IL.BDL_C_SFErrorLocal;
import sped.negocio.BDL.IR.BDL_C_SFErrorRemote;

@Stateless(name = "BDL_C_SFError", mappedName = "mapBDL_C_SFError")
public class BDL_C_SFErrorBean implements BDL_C_SFErrorRemote, 
                                             BDL_C_SFErrorLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SPED_NEGOCIO")
    private EntityManager em;

    public BDL_C_SFErrorBean() {
    }
    
    public sped.negocio.entidades.sist.Error getErrorByCodigo(String cidError){
        sped.negocio.entidades.sist.Error stError = new sped.negocio.entidades.sist.Error();
        try{
            String ejbQl = "SELECT e "+
                           "FROM Error e "+
                           "WHERE e.cidError = :ciderror ";
            stError = (sped.negocio.entidades.sist.Error) em.createQuery(ejbQl).setParameter("ciderror",cidError)
                                .getSingleResult();
        }catch(NoResultException nre){
            System.out.println("\n\n\nEl Codigo de Error no existe!!!!!!!!!!!!!!!!!!!! nre. \n\n\n");
            return null;
        }catch(Exception e){
            e.printStackTrace();
            return null;   
        }
        return stError;
    }
}