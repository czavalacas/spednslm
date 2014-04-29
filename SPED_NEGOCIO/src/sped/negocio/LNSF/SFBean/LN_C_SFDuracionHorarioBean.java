package sped.negocio.LNSF.SFBean;

import javax.annotation.Resource;

import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import net.sf.dozer.util.mapping.DozerBeanMapper;
import net.sf.dozer.util.mapping.MapperIF;

import sped.negocio.BDL.IL.BDL_C_SFDuracionHorarioLocal;
import sped.negocio.LNSF.IL.LN_C_SFDuracionHorarioLocal;
import sped.negocio.LNSF.IR.LN_C_SFDuracionHorarioRemote;
import sped.negocio.entidades.beans.BeanDuracionHorario;
import sped.negocio.entidades.sist.DuracionHorario;

@Stateless(name = "LN_C_SFDuracionHorario", mappedName = "mapLN_C_SFDuracionHorario")
public class LN_C_SFDuracionHorarioBean implements LN_C_SFDuracionHorarioRemote, 
                                                   LN_C_SFDuracionHorarioLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SPED_NEGOCIO")
    private EntityManager em;
    @EJB
    private BDL_C_SFDuracionHorarioLocal bdL_C_SFDuracionHorarioLocal;
    private MapperIF mapper = new DozerBeanMapper();

    public LN_C_SFDuracionHorarioBean() {
    }
    
    public BeanDuracionHorario getDuracionHorarioBySedeNivel(int nidSede,
                                                             int nidNivel){
        DuracionHorario dh = bdL_C_SFDuracionHorarioLocal.getDuracionHorarioBySedeNivel(nidSede, nidNivel);
        if(dh != null){
            return (BeanDuracionHorario)mapper.map(dh, BeanDuracionHorario.class);
        }else{
            return null;
        }
    }
}
