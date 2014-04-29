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

import sped.negocio.BDL.IL.BDL_C_SFConfiguracionHorarioLocal;
import sped.negocio.LNSF.IL.LN_C_SFConfiguracionHorarioLocal;
import sped.negocio.LNSF.IR.LN_C_SFConfiguracionHorarioRemote;
import sped.negocio.entidades.beans.BeanConfiguracionEventoHorario;
import sped.negocio.entidades.beans.BeanConfiguracionHorario;
import sped.negocio.entidades.sist.ConfiguracionHorario;

@Stateless(name = "LN_C_SFConfiguracionHorario", mappedName = "mapLN_C_SFConfiguracionHorario")
public class LN_C_SFConfiguracionHorarioBean implements LN_C_SFConfiguracionHorarioRemote,
                                                        LN_C_SFConfiguracionHorarioLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SPED_NEGOCIO")
    private EntityManager em;
    @EJB
    private BDL_C_SFConfiguracionHorarioLocal bdL_C_SFConfiguracionHorarioLocal;
    private MapperIF mapper = new DozerBeanMapper();
    

    public LN_C_SFConfiguracionHorarioBean() {
    }
    
    public List<BeanConfiguracionHorario> getConfiguracionBySedeNivel(int nidSede,
                                                                      int nidNivel){
        return transformLstConfiguracion(bdL_C_SFConfiguracionHorarioLocal.getConfiguracionBySedeNivel(nidSede, nidNivel));
    }
    
    public List<BeanConfiguracionHorario> transformLstConfiguracion(List<ConfiguracionHorario> lst){
        try{
            List<BeanConfiguracionHorario> lstBean = new ArrayList();
            for(ConfiguracionHorario ch : lst){
                BeanConfiguracionHorario bean = (BeanConfiguracionHorario) mapper.map(ch, BeanConfiguracionHorario.class);
                lstBean.add(bean);
            }
            return lstBean;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
