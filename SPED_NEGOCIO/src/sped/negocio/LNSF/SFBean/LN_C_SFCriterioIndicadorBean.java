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

import sped.negocio.BDL.IL.BDL_C_SFCriterioIndicadorLocal;
import sped.negocio.LNSF.IL.LN_C_SFCriterioIndicadorLocal;
import sped.negocio.LNSF.IL.LN_C_SFResultadoLocal;
import sped.negocio.LNSF.IR.LN_C_SFCriterioIndicadorRemote;
import sped.negocio.LNSF.IR.LN_C_SFLeyendaRemote;
import sped.negocio.Utils.Utiles;
import sped.negocio.entidades.beans.BeanCriterioIndicador;
import sped.negocio.entidades.beans.BeanCriterioIndicadorWS;
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
    @EJB
    private BDL_C_SFCriterioIndicadorLocal bdL_C_SFCriterioIndicadorLocal;
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
    
    /**
     * Metodo que retorna la lista de indicadores cuando se seleccione un criterio para el MOVIL
     * @param nidFicha
     * @param nidCriterio
     * @author dfloresgonz
     * @since 06.02.2014
     * @return
     */
    public List<BeanCriterioIndicadorWS> getLstIndicadoresByFichaCriterio_LN_WS(int nidFicha, int nidCriterio){
        List<BeanCriterioIndicadorWS> lst = new ArrayList<BeanCriterioIndicadorWS>();
        for(CriterioIndicador ci : bdL_C_SFCriterioIndicadorLocal.getLstIndicadoresByFichaCriterio_BDL_WS(nidFicha, nidCriterio)){
            BeanCriterioIndicadorWS bean = new BeanCriterioIndicadorWS();Utiles.sysout("ci.getIndicador().getDescripcionIndicador():"+ci.getIndicador().getDescripcionIndicador());
            bean.setDescripcionIndicador(ci.getIndicador().getDescripcionIndicador());
            bean.setNidCriterioIndicador(ci.getNidCriterioIndicador());
            bean.setNidIndicador(ci.getIndicador().getNidIndicador());
            bean.setOrden(ci.getOrden());
            lst.add(bean);
        }
        return lst;
    }
}
