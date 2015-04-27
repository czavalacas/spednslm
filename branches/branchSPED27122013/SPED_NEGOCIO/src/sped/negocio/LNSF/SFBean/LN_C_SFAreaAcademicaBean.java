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

import sped.negocio.BDL.IL.BDL_C_SFAreaAcademicaLocal;
import sped.negocio.LNSF.IL.LN_C_SFAreaAcademicaLocal;
import sped.negocio.LNSF.IR.LN_C_SFAreaAcademicaRemote;
import sped.negocio.entidades.admin.AreaAcademica;
import sped.negocio.entidades.admin.Profesor;
import sped.negocio.entidades.beans.BeanAreaAcademica;
import sped.negocio.entidades.beans.BeanCombo;
import sped.negocio.entidades.beans.BeanComboString;
import sped.negocio.entidades.beans.BeanUsuario;

@Stateless(name = "LN_C_SFAreaAcademica", mappedName = "SPED_APP-SPED_NEGOCIO-LN_C_SFAreaAcademica")
public class LN_C_SFAreaAcademicaBean implements LN_C_SFAreaAcademicaRemote, 
                                                    LN_C_SFAreaAcademicaLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SPED_NEGOCIO")
    private EntityManager em;
    
    @EJB
    private BDL_C_SFAreaAcademicaLocal bdL_C_SFAreaAcademicaLocal;
    private MapperIF mapper = new DozerBeanMapper();

    public LN_C_SFAreaAcademicaBean() {
    }
    
    public List<BeanAreaAcademica> getAreaAcademicaLN(){        
        List<BeanAreaAcademica> lstBean = new ArrayList();
        List<AreaAcademica> lstAreaAcd = bdL_C_SFAreaAcademicaLocal.getAreaAcademicaFindAll();       
        for(AreaAcademica a : lstAreaAcd){
            BeanAreaAcademica bean = (BeanAreaAcademica) mapper.map(a, BeanAreaAcademica.class);
            lstBean.add(bean);
        }
        return lstBean;
    }
    
    public List<BeanCombo> getAreaAcademicaLNPorSede_byOrden(String nidSede){        
        List<BeanCombo> lstBean = new ArrayList<BeanCombo>();
        List<AreaAcademica> lstAreaAcd = bdL_C_SFAreaAcademicaLocal.findAreasPorSede_ByOrden(nidSede);       
        Iterator it=lstAreaAcd.iterator();
        while(it.hasNext()){
            AreaAcademica entida= (AreaAcademica)it.next();
            BeanCombo bean=new BeanCombo();
            bean.setId(entida.getNidAreaAcademica());
            bean.setDescripcion(entida.getDescripcionAreaAcademica());
            lstBean.add(bean);
        }
        return lstBean;
    }
    
    public BeanAreaAcademica findConstrainByIdLN(int id){
        try{
            BeanAreaAcademica bean = (BeanAreaAcademica)mapper.map(bdL_C_SFAreaAcademicaLocal.findEvaluadorById(id),BeanAreaAcademica.class);
            return bean;
        } catch(Exception e){
            e.printStackTrace();
            return null;
        }        
    }
    
    public List<BeanCombo> getAreaAcademicasAll(int opc){        
            List<BeanCombo> lstBean = new ArrayList<BeanCombo>();
            List<AreaAcademica> listaArea = bdL_C_SFAreaAcademicaLocal.getAreaNativasByArea(opc);
          Iterator it=listaArea.iterator();
          while(it.hasNext()){
            AreaAcademica entida=(AreaAcademica)it.next();
            BeanCombo bean=new BeanCombo();
            bean.setId(entida.getNidAreaAcademica());
            bean.setDescripcion(entida.getDescripcionAreaAcademica());
            lstBean.add(bean);          
          }
            return lstBean;
        } 
}
