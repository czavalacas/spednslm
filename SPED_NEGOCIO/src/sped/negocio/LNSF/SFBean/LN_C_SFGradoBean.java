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

import sped.negocio.BDL.IL.BDL_C_SFGradoLocal;
import sped.negocio.LNSF.IL.LN_C_SFGradoLocal;
import sped.negocio.LNSF.IR.LN_C_SFGradoRemote;
import sped.negocio.entidades.admin.Grado;
import sped.negocio.entidades.beans.BeanGrado;
import sped.negocio.entidades.beans.BeanUsuario;

@Stateless(name = "LN_C_SFGrado", mappedName = "SPED_APP-SPED_NEGOCIO-LN_C_SFGrado")
public class LN_C_SFGradoBean implements LN_C_SFGradoRemote, 
                                         LN_C_SFGradoLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SPED_NEGOCIO")
    private EntityManager em;
    @EJB
    private BDL_C_SFGradoLocal bdL_C_SFGradoLocal;
    private MapperIF mapper = new DozerBeanMapper();

    public LN_C_SFGradoBean() {
    }
    
    public List<BeanGrado> getGradoLN(){
        try{
            return transformLstGrado(bdL_C_SFGradoLocal.getGradoFindAll());
        }catch(Exception e){
            System.out.println(e.getMessage());
            return new ArrayList<BeanGrado>();
        }
    }
    
    public List<BeanGrado> transformLstGrado(List<Grado> lstGrado){
        try{
            List<BeanGrado> lstBean = new ArrayList();
            for(Grado g : lstGrado){
                BeanGrado beanGrado = (BeanGrado) mapper.map(g, BeanGrado.class);
                lstBean.add(beanGrado);
            }
            return lstBean;
        }catch(MappingException me){
            me.printStackTrace();
            return null;
        }
    }
}
