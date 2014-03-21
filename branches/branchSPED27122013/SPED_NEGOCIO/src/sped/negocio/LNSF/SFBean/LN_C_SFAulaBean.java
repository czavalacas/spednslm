package sped.negocio.LNSF.SFBean;

import javax.annotation.Resource;

import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import sped.negocio.BDL.IL.BDL_C_SFAulaLocal;
import sped.negocio.LNSF.IL.LN_C_SFAulaLocal;
import sped.negocio.LNSF.IR.LN_C_SFAulaRemote;
import sped.negocio.entidades.beans.BeanAula;

@Stateless(name = "LN_C_SFAula", mappedName = "mapLN_C_SFAula")
public class LN_C_SFAulaBean implements LN_C_SFAulaRemote, 
                                        LN_C_SFAulaLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SPED_NEGOCIO")
    private EntityManager em;
    @EJB
    private BDL_C_SFAulaLocal bdL_C_SFAulaLocal;

    public LN_C_SFAulaBean() {
    }
    
    public int getAulaByDescripcion_LN(int nidSede, 
                                       int nidNivel, 
                                       String descripcion){
        BeanAula beanAula = new BeanAula();
        beanAula.setNidNivel(nidNivel);
        beanAula.setNidSede(nidSede);
        beanAula.setDescripcionAula(descripcion);
        return bdL_C_SFAulaLocal.getAulaByDescripcion(beanAula);
    }
}
