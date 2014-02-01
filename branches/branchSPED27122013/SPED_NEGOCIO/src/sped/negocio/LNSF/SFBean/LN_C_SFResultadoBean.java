package sped.negocio.LNSF.SFBean;

import javax.annotation.Resource;

import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import sped.negocio.BDL.IL.BDL_C_SFResultadoLocal;
import sped.negocio.LNSF.IL.LN_C_SFResultadoLocal;
import sped.negocio.LNSF.IR.LN_C_SFResultadoRemote;

@Stateless(name = "LN_C_SFResultado", mappedName = "mapLN_C_SFResultado")
public class LN_C_SFResultadoBean implements LN_C_SFResultadoRemote, 
                                                LN_C_SFResultadoLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SPED_NEGOCIO")
    private EntityManager em;
    @EJB
    private BDL_C_SFResultadoLocal bdL_C_SFResultadoLocal;

    public LN_C_SFResultadoBean() {
    }
    
    public boolean fichaUsadaEnEvaluacion_LN(int nidFicha){
        return bdL_C_SFResultadoLocal.fichaUsadaEnEvaluacion(nidFicha);
    }
}