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

import sped.negocio.BDL.IL.BDL_C_SFRolLocal;
import sped.negocio.LNSF.IL.LN_C_SFRolLocal;
import sped.negocio.LNSF.IR.LN_C_SFRolRemote;
import sped.negocio.entidades.beans.BeanRol;
import sped.negocio.entidades.sist.Rol;

@Stateless(name = "LN_C_SFRol", mappedName = "SPED_APP-SPED_NEGOCIO-LN_C_SFRol")
public class LN_C_SFRolBean implements LN_C_SFRolRemote, 
                                       LN_C_SFRolLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SPED_NEGOCIO")
    private EntityManager em;

    @EJB
    private BDL_C_SFRolLocal bdL_C_SFRolLocal;
    private MapperIF mapper = new DozerBeanMapper();
    
    public LN_C_SFRolBean() {
    }
    
    public List<BeanRol> getRolLN(){
        try{
            return transformLstRol(bdL_C_SFRolLocal.getRolFindAll());
        }catch(Exception e){
            e.printStackTrace();
            return new ArrayList();
        }
    }
    
    public boolean validaRolbyDescripcion(int nidRol, String descripcion){
        boolean valida = false;        
        if(nidRol == bdL_C_SFRolLocal.getIdbyDescripcion(descripcion)){
            valida = true;
        }
        return valida;        
    }
    
    public List<BeanRol> getListRolbyNombreLN(String descripcion){
        try{
            return transformLstRol(bdL_C_SFRolLocal.getListRolbyNombreBDL(descripcion));
        }catch(Exception e){
            e.printStackTrace();
            return new ArrayList();
        }
    }
    
    public List<BeanRol> transformLstRol(List<Rol> lstRol){
        try{
            List<BeanRol> lstBean = new ArrayList();
            for(Rol r : lstRol){
                BeanRol bean = (BeanRol) mapper.map(r, BeanRol.class);
                lstBean.add(bean);
            }
            return lstBean;
        }catch(MappingException me){
            me.printStackTrace();
            return null;
        }  
    }
}
