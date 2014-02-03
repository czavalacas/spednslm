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

import sped.negocio.BDL.IL.BDL_C_SFResultadoLocal;
import sped.negocio.LNSF.IL.LN_C_SFResultadoLocal;
import sped.negocio.LNSF.IR.LN_C_SFResultadoRemote;
import sped.negocio.entidades.beans.BeanResultado;
import sped.negocio.entidades.eval.Resultado;

@Stateless(name = "LN_C_SFResultado", mappedName = "mapLN_C_SFResultado")
public class LN_C_SFResultadoBean implements LN_C_SFResultadoRemote, 
                                                LN_C_SFResultadoLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SPED_NEGOCIO")
    private EntityManager em;
    @EJB
    private BDL_C_SFResultadoLocal bdL_C_SFResultadoLocal;
    private MapperIF mapper = new DozerBeanMapper();

    public LN_C_SFResultadoBean() {
    }
    
    public boolean fichaUsadaEnEvaluacion_LN(int nidFicha){
        return bdL_C_SFResultadoLocal.fichaUsadaEnEvaluacion(nidFicha);
    }
    
    public List<BeanResultado> transformLstResultado(List<Resultado> lstResultado){
        try {
            List<BeanResultado> lstBeanRes = new ArrayList();
            for(Resultado r : lstResultado){
                BeanResultado bean = (BeanResultado) mapper.map(r, BeanResultado.class);
                lstBeanRes.add(bean);
            }
            return lstBeanRes;
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList();
        }
    }
}