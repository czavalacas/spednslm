package sped.negocio.LNSF.SFBean;

import java.util.ArrayList;
import java.util.Iterator;
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

import sped.negocio.BDL.IL.BDL_C_SFSedeLocal;
import sped.negocio.LNSF.IL.LN_C_SFSedeLocal;
import sped.negocio.LNSF.IR.LN_C_SFSedeRemote;
import sped.negocio.Utils.Utiles;
import sped.negocio.entidades.admin.Curso;
import sped.negocio.entidades.admin.Sede;
import sped.negocio.entidades.beans.BeanAreaAcademica;
import sped.negocio.entidades.beans.BeanCombo;
import sped.negocio.entidades.beans.BeanSede;
import sped.negocio.entidades.beans.BeanUsuario;

@Stateless(name = "LN_C_SFSede", mappedName = "SPED_APP-SPED_NEGOCIO-LN_C_SFSede")
public class LN_C_SFSedeBean implements LN_C_SFSedeRemote, 
                                        LN_C_SFSedeLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SPED_NEGOCIO")
    private EntityManager em;
    
    @EJB
    private BDL_C_SFSedeLocal bdL_C_SFSedeLocal;
    private MapperIF mapper = new DozerBeanMapper();

    public LN_C_SFSedeBean() {
    }
    
    public List<BeanSede> getSedeLN(){
        try{
            return transformLstSede(bdL_C_SFSedeLocal.getSedeFindAll());
        }catch(Exception e){
            return new ArrayList<BeanSede>();
        }            
    }
    public List<BeanCombo> findSedePorAreaAcademica(Integer nidAreaAcademica, String dia) {
        List<Sede> listaSedes=bdL_C_SFSedeLocal.findSedePorAreaAcademica(nidAreaAcademica,dia);
        List<BeanCombo> list=new ArrayList<BeanCombo>();
        Iterator it=listaSedes.iterator();
        while(it.hasNext()){
            Sede entida= (Sede)it.next();
            BeanCombo bean=new BeanCombo();
            bean.setId(entida.getNidSede());
            bean.setDescripcion(entida.getDescripcionSede());
            list.add(bean);
        }
        return list;  
    }
    
    public List<BeanSede> transformLstSede(List<Sede> lstSede){
        try{
            List<BeanSede> lstBeanSede = new ArrayList();
            for(Sede s : lstSede){
                BeanSede bean =  (BeanSede) mapper.map(s, BeanSede.class);
                lstBeanSede.add(bean);
            }
            return lstBeanSede;
        }catch(MappingException me){
            me.printStackTrace();
            return null;
        }
    }
    
    public BeanSede findConstrainByIdLN(int id){
        try{
            BeanSede bean = (BeanSede)mapper.map(bdL_C_SFSedeLocal.findSedeById(id),BeanSede.class);
            return bean;
        } catch(Exception e){
            e.printStackTrace();
            return null;
        }        
    }
    
    public List<BeanCombo> getAllSedes() {
        List<Sede> listaSedes=bdL_C_SFSedeLocal.getSedeFindAll();
        List<BeanCombo> list=new ArrayList<BeanCombo>();
        Iterator it=listaSedes.iterator();
        while(it.hasNext()){
            Sede entida= (Sede)it.next();
            BeanCombo bean=new BeanCombo();
            bean.setId(entida.getNidSede());
            bean.setDescripcion(entida.getDescripcionSede());
            list.add(bean);
        }
        return list;  
    }   
    
    
}
