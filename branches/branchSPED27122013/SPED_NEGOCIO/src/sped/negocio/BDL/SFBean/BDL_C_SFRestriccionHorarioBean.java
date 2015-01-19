package sped.negocio.BDL.SFBean;

import java.sql.Time;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import sped.negocio.BDL.IL.BDL_C_SFRestriccionHorarioLocal;
import sped.negocio.BDL.IR.BDL_C_SFRestriccionHorarioRemote;
import sped.negocio.entidades.sist.RestriccionHorario;

@Stateless(name = "BDL_C_SFRestriccionHorario", mappedName = "mapBDL_C_SFRestriccionHorario")
public class BDL_C_SFRestriccionHorarioBean implements BDL_C_SFRestriccionHorarioRemote,
                                                       BDL_C_SFRestriccionHorarioLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SPED_NEGOCIO")
    private EntityManager em;

    public BDL_C_SFRestriccionHorarioBean() {
    }
    
    public RestriccionHorario findRestriccionHorarioById(int nid){
        try{
            RestriccionHorario instance = em.find(RestriccionHorario.class, nid);
            return instance;
        }catch(RuntimeException re){
            throw re;
        }
    }
    
    public List<RestriccionHorario> listRestriccionHorarioByNidExcepcion(String tipo, String nid){
        try{
            String ejbQl = " SELECT res FROM RestriccionHorario res " +
                           " WHERE res.estado = 1 AND res.tipoRestr = :nidTipo " +
                           " AND res.nid = :nid " +
                           " ORDER BY res.hora_ini ASC ";
            List<RestriccionHorario> list = em.createQuery(ejbQl).setParameter("nidTipo", tipo)
                                                                 .setParameter("nid", nid)
                                                                 .getResultList();
            return list;
        }catch(Exception e){
            e.printStackTrace();
            return new ArrayList();
        }
    }
    
    public List<RestriccionHorario> countCruceRestriccionHorario(String codigo, 
                                                                 int dia,
                                                                 Time inicio, 
                                                                 Time fin){
        try{
            String ejbQl =  " SELECT res FROM RestriccionHorario res " +                            
                            " WHERE res.nDia = :dia AND res.nid = :codigo " +
                            " AND ( (res.hora_ini < :inicio AND res.hora_fin > :fin) " +
                            " OR    (res.hora_ini > :inicio AND res.hora_ini < :fin) " +
                            " OR    (res.hora_fin > :inicio AND res.hora_fin < :fin) " +
                            " OR    (res.hora_ini = :inicio AND res.hora_fin = :fin) ) ";
            List<RestriccionHorario> list = em.createQuery(ejbQl).setParameter("dia", dia)
                                                                 .setParameter("codigo", codigo)
                                                                 .setParameter("inicio", inicio)
                                                                 .setParameter("fin", fin)
                                                                 .getResultList();
            return list;
        }catch(Exception e){
            e.printStackTrace();
            return new ArrayList();
        }
    }
}
