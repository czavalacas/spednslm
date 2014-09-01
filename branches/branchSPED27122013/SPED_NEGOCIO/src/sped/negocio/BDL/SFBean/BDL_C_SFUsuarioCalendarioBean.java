package sped.negocio.BDL.SFBean;

import java.util.Date;

import java.util.List;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import sped.negocio.BDL.IL.BDL_C_SFUsuarioCalendarioLocal;
import sped.negocio.BDL.IR.BDL_C_SFUsuarioCalendarioRemote;
import sped.negocio.entidades.admin.UsuarioCalendario;
import sped.negocio.entidades.admin.UsuarioCalendarioPK;

@Stateless(name = "BDL_C_SFUsuarioCalendario", mappedName = "mapBDL_C_SFUsuarioCalendario")
public class BDL_C_SFUsuarioCalendarioBean implements BDL_C_SFUsuarioCalendarioRemote, 
                                                      BDL_C_SFUsuarioCalendarioLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SPED_NEGOCIO")
    private EntityManager em;

    public BDL_C_SFUsuarioCalendarioBean() {
    }
    
    public UsuarioCalendario findUsuarioCalendarioById(Date nidFecha,int nidUsuario) {
        try {
            UsuarioCalendarioPK id = new UsuarioCalendarioPK(nidFecha,nidUsuario);
            UsuarioCalendario instance = em.find(UsuarioCalendario.class, id);
            return instance;
        } catch (RuntimeException re) {
            throw re;
        }
    }
    
    public int getCantidadDiasLaborablesByUsuario(int nidUsuario, Date fecMin,Date fecMax){
       try {
           String qlString = "SELECT COUNT(1) FROM (                         \n " + 
                             "	        SELECT 1                             \n" + 
                             "          FROM calendario c                    \n" + 
                             "	        WHERE c.estado = '1'                 \n" + 
                             "          AND c.pk_fecha between ? AND ?       \n" + 
                             "          AND c.esLaborable = 1                \n" + 
                             "          AND NOT EXISTS (SELECT c1.pk_fecha   \n" + 
                             "				FROM   calendario c1,\n" + 
                             "				       addusca uc,   \n" + 
                             "				       admusua u     \n" + 
                             "				WHERE c1.pk_fecha  = uc.pk_fecha    \n" + 
                             "				AND   u.nidUsuario = uc.nidUsuario  \n" + 
                             "                          AND   u.nidUsuario = ?              \n" + 
                             "                          AND   c1.pk_fecha = c.pk_fecha)     \n" + 
                             ") tab ";
           List lst = em.createNativeQuery(qlString).setParameter(1,fecMin)
                                                    .setParameter(2,fecMax)
                                                    .setParameter(3,nidUsuario).getResultList();
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
}