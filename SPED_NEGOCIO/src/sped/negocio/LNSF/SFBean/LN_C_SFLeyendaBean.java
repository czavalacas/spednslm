package sped.negocio.LNSF.SFBean;

import javax.annotation.Resource;

import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import net.sf.dozer.util.mapping.DozerBeanMapper;
import net.sf.dozer.util.mapping.MapperIF;

import sped.negocio.BDL.IL.BDL_C_SFLeyendaLocal;
import sped.negocio.LNSF.IL.LN_C_SFLeyendaLocal;
import sped.negocio.LNSF.IR.LN_C_SFLeyendaRemote;
import sped.negocio.entidades.beans.BeanLeyenda;
import sped.negocio.entidades.eval.CriterioIndicador;

@Stateless(name = "LN_C_SFLeyenda", mappedName = "mapLN_C_SFLeyenda")
public class LN_C_SFLeyendaBean implements LN_C_SFLeyendaRemote, 
                                           LN_C_SFLeyendaLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SPED_NEGOCIO")
    private EntityManager em;
    @EJB
    private BDL_C_SFLeyendaLocal bdL_C_SFLeyendaLocal;
    private MapperIF mapper = new DozerBeanMapper();

    public LN_C_SFLeyendaBean() {
    }
    
    public BeanLeyenda getLeyendabyEvaluacion(CriterioIndicador cri,
                                              int nidFicha, 
                                              int valor){
        return (BeanLeyenda) mapper.map(bdL_C_SFLeyendaLocal.getLeyendabyEvaluacion(cri, nidFicha, valor), BeanLeyenda.class);
    }
}
