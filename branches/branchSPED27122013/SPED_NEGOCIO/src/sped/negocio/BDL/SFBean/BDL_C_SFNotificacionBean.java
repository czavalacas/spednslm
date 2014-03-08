package sped.negocio.BDL.SFBean;

import java.util.List;

import javax.annotation.Resource;

import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import sped.negocio.BDL.IL.BDL_C_SFNotificacionLocal;
import sped.negocio.BDL.IR.BDL_C_SFNotificacionRemote;

/**
 * Clase Session Facade que maneja los queries de consulta para las notificaciones de usuario
 * @author dfloresgonz
 * @since 07.03.2014
 */
@Stateless(name = "BDL_C_SFNotificacion", mappedName = "mapBDL_C_SFNotificacion")
public class BDL_C_SFNotificacionBean implements BDL_C_SFNotificacionRemote,
                                                    BDL_C_SFNotificacionLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SPED_NEGOCIO")
    private EntityManager em;

    public BDL_C_SFNotificacionBean() {
    }
    
    public int getCantidadNotificacionesEvaluaciones_BDL(int nidUsuario){
        try {
            String qlString = "SELECT count(n.cidNotificacion) " +
                              "FROM NotificacionEvaluacion n " +
                              "WHERE n.leido = '0' " +
                              "AND n.nidUsuario = :nidUsuario ";
           List lst = em.createQuery(qlString).setParameter("nidUsuario",nidUsuario).getResultList();
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
    
    public int getCantidadNotificacionesParteOcurrencia_BDL(int nidUsuario){
        try {
            String qlString = "SELECT count(n.cidNotificacion) " +
                              "FROM NotificacionParteOcurrencia n " +
                              "WHERE n.leido = '0' " +
                              "AND n.nidUsuario = :nidUsuario ";
           List lst = em.createQuery(qlString).setParameter("nidUsuario",nidUsuario).getResultList();
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