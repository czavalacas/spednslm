package sped.negocio.LNSF.SFBean;

import java.util.ArrayList;

import javax.annotation.Resource;

import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import net.sf.dozer.util.mapping.DozerBeanMapper;
import net.sf.dozer.util.mapping.MapperIF;

import sped.negocio.BDL.IL.BDL_C_SFHorarioLocal;
import sped.negocio.LNSF.IL.LN_C_SFHorarioLocal;
import sped.negocio.LNSF.IR.LN_C_SFHorarioRemote;
import sped.negocio.entidades.beans.BeanHorario;

@Stateless(name = "LN_C_SFHorario", mappedName = "mapLN_C_SFHorario")
public class LN_C_SFHorarioBean implements LN_C_SFHorarioRemote, 
                                           LN_C_SFHorarioLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SPED_NEGOCIO")
    private EntityManager em;
    @EJB
    private BDL_C_SFHorarioLocal bdL_C_SFHorarioLocal;
    private MapperIF mapper = new DozerBeanMapper();

    public LN_C_SFHorarioBean() {
    }
    
    public BeanHorario getHorario(){        
        if(bdL_C_SFHorarioLocal.getHorarioFindAll() != null){
            return (BeanHorario) mapper.map(bdL_C_SFHorarioLocal.getHorarioFindAll().get(0), BeanHorario.class);
        }else{
            return new BeanHorario();
        }        
    }
}
