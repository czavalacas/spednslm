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

import net.sf.dozer.util.mapping.MappingException;

import sped.negocio.BDL.IL.BDL_C_SFEvaluacionLocal;
import sped.negocio.LNSF.IL.LN_C_SFEvaluacionLocal;
import sped.negocio.LNSF.IR.LN_C_SFEvaluacionRemote;
import sped.negocio.entidades.beans.BeanEvaluacion;
import sped.negocio.entidades.beans.BeanUsuario;
import sped.negocio.entidades.eval.Evaluacion;

@Stateless(name = "LN_C_SFEvaluacion", mappedName = "SPED_APP-SPED_NEGOCIO-LN_C_SFEvaluacion")
public class LN_C_SFEvaluacionBean implements LN_C_SFEvaluacionRemote, 
                                              LN_C_SFEvaluacionLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SPED_NEGOCIO")
    private EntityManager em;
    @EJB
    private BDL_C_SFEvaluacionLocal bdL_C_SFEvaluacionLocal;
    private MapperIF mapper = new DozerBeanMapper();

    public LN_C_SFEvaluacionBean() {
    }
    
    public List<BeanEvaluacion> getEvaluacionesByUsuarioLN(BeanUsuario beanUsuario){
        try{
            return transformLstEvaluacion(bdL_C_SFEvaluacionLocal.getEvaluacionesByUsuarioBDL(beanUsuario));
        }catch(Exception e){
            System.out.println(e.getMessage());
            return new ArrayList<BeanEvaluacion>();
        }
    }
    
    public List<BeanEvaluacion> transformLstEvaluacion(List<Evaluacion> lstEvaluacion){
        try{
            List<BeanEvaluacion> lstBean= new ArrayList();
            for(Evaluacion eva : lstEvaluacion){
                BeanEvaluacion beanEva = (BeanEvaluacion) mapper.map(eva, BeanEvaluacion.class);
                lstBean.add(beanEva);
            }
            return lstBean;
        }catch(MappingException me){
            me.printStackTrace();
            return null;
        }
    }
}
