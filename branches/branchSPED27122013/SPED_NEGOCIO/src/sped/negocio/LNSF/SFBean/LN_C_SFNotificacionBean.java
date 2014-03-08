package sped.negocio.LNSF.SFBean;

import javax.annotation.Resource;

import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import sped.negocio.BDL.IL.BDL_C_SFNotificacionLocal;
import sped.negocio.LNSF.IL.LN_C_SFNotificacionLocal;
import sped.negocio.LNSF.IR.LN_C_SFNotificacionRemote;
import sped.negocio.Utils.Utiles;

/**
 * Clase Session Facade que maneja la Logica de Negocio para las notificaciones de usuario
 * @author dfloresgonz
 * @since 07.03.2014
 */
@Stateless(name = "LN_C_SFNotificacion", mappedName = "mapLN_C_SFNotificacion")
public class LN_C_SFNotificacionBean implements LN_C_SFNotificacionRemote, 
                                                   LN_C_SFNotificacionLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SPED_NEGOCIO")
    private EntityManager em;
    @EJB
    private BDL_C_SFNotificacionLocal bdL_C_SFNotificacionLocal;

    public LN_C_SFNotificacionBean() {
    }
    
    public int[] getCantidadAMostrarNotificaciones(int nidUsuario){
        int vec[] = new int[3];
        vec[0] = 0;
        vec[1] = 0;
        vec[2] = 0;
        try {
            vec[0] = bdL_C_SFNotificacionLocal.getCantidadNotificacionesEvaluaciones_BDL(nidUsuario);
            vec[1] = bdL_C_SFNotificacionLocal.getCantidadNotificacionesParteOcurrencia_BDL(nidUsuario);
            vec[2] = vec[0] + vec[1];
            return vec;
       } catch (Exception e) {
            e.printStackTrace();
            return vec;
        }
    }
}