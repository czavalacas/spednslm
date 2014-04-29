package sped.negocio.BDL.SFBean;

import java.util.List;

import javax.annotation.Resource;

import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import sped.negocio.BDL.IL.BDL_C_SFDuracionHorarioLocal;
import sped.negocio.BDL.IR.BDL_C_SFDuracionHorarioRemote;
import sped.negocio.entidades.sist.DuracionHorario;

@Stateless(name = "BDL_C_SFDuracionHorario", mappedName = "mapBDL_C_SFDuracionHorario")
public class BDL_C_SFDuracionHorarioBean implements BDL_C_SFDuracionHorarioRemote, 
                                                    BDL_C_SFDuracionHorarioLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SPED_NEGOCIO")
    private EntityManager em;

    public BDL_C_SFDuracionHorarioBean() {
    }
    
    public DuracionHorario getDuracionHorarioBySedeNivel(int nidSede,
                                                         int nidNivel){
        try{
            String qlString = "SELECT dh from DuracionHorario dh " +
                              "WHERE dh.nidSede =:nidSede " +
                              "AND dh.nidNivel =:nidNivel " ;
            return (DuracionHorario)em.createQuery(qlString).setParameter("nidSede", nidSede)
                                                            .setParameter("nidNivel", nidNivel)
                                                            .getSingleResult();
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
