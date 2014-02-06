package sped.negocio.LNSF.SFBean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import net.sf.dozer.util.mapping.DozerBeanMapper;
import net.sf.dozer.util.mapping.MapperIF;

import sped.negocio.LNSF.IL.LN_C_SFCriterioIndicadorLocal;
import sped.negocio.LNSF.IL.LN_C_SFResultadoLocal;
import sped.negocio.LNSF.IR.LN_C_SFCriterioIndicadorRemote;
import sped.negocio.LNSF.IR.LN_C_SFLeyendaRemote;
import sped.negocio.entidades.beans.BeanCriterioIndicador;
import sped.negocio.entidades.eval.CriterioIndicador;

@Stateless(name = "LN_C_SFCriterioIndicador", mappedName = "SPED_APP-SPED_NEGOCIO-LN_C_SFCriterioIndicador")
public class LN_C_SFCriterioIndicadorBean implements LN_C_SFCriterioIndicadorRemote, 
                                                     LN_C_SFCriterioIndicadorLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SPED_NEGOCIO")
    private EntityManager em;
    @EJB
    private LN_C_SFResultadoLocal ln_C_SFResultadoLocal;
    @EJB
    private LN_C_SFLeyendaRemote ln_C_SFLeyendaRemote;
    private MapperIF mapper = new DozerBeanMapper();

    public LN_C_SFCriterioIndicadorBean() {
    }
    
    public List<BeanCriterioIndicador> transformLstCriterioIndicador(List<CriterioIndicador> lstCrIn,
                                                                     int nidEvaluacion){
        try {
            List<BeanCriterioIndicador> beanCrIn = new ArrayList();
            int nidFicha = lstCrIn.get(0).getFichaCriterio().getFicha().getNidFicha();
            for(CriterioIndicador crin : lstCrIn){
                BeanCriterioIndicador bean = (BeanCriterioIndicador) mapper.map(crin, BeanCriterioIndicador.class);
                bean.setResultadoEvaluacion(ln_C_SFResultadoLocal.findResultadoByIdLN(bean.getNidCriterioIndicador(), 
                                                                                      nidEvaluacion));
                bean.setLeyenda(ln_C_SFLeyendaRemote.getLeyendabyEvaluacion(crin, nidFicha));
                beanCrIn.add(bean);
            }                    
            return beanCrIn;
       } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList();
        }
    }
}
