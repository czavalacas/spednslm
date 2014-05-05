package sped.negocio.BDL.SFBean;

import java.util.List;

import javax.annotation.Resource;

import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import sped.negocio.BDL.IL.BDL_C_SFConfiguracionHorarioLocal;
import sped.negocio.BDL.IR.BDL_C_SFConfiguracionHorarioRemote;
import sped.negocio.entidades.sist.ConfiguracionHorario;

@Stateless(name = "BDL_C_SFConfiguracionHorario", mappedName = "mapBDL_C_SFConfiguracionHorario")
public class BDL_C_SFConfiguracionHorarioBean implements BDL_C_SFConfiguracionHorarioRemote,
                                                         BDL_C_SFConfiguracionHorarioLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SPED_NEGOCIO")
    private EntityManager em;

    public BDL_C_SFConfiguracionHorarioBean() {
    }
    
    public List<ConfiguracionHorario> getConfiguracionBySedeNivel(int nidSede,
                                                                  int nidNivel){
        try{
            String ejbQl = "SELECT ch from ConfiguracionHorario ch " +
                           "WHERE ch.nidSede =:nidSede " +
                           "AND ch.nidNivel =:nidNivel " +
                           "ORDER BY ch.hora_inicio ASC";
            return em.createQuery(ejbQl).setParameter("nidSede", nidSede)
                                        .setParameter("nidNivel", nidNivel)
                                        .getResultList();
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
    
}
