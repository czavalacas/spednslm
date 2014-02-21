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

import sped.negocio.BDL.IL.BDL_C_SFAreaAcademicaLocal;
import sped.negocio.BDL.IL.BDL_C_SFProfesorLocal;
import sped.negocio.LNSF.IL.LN_C_SFProfesorLocal;
import sped.negocio.LNSF.IR.LN_C_SFProfesorRemote;
import sped.negocio.entidades.admin.AreaAcademica;
import sped.negocio.entidades.admin.Profesor;
import sped.negocio.entidades.beans.BeanAreaAcademica;
import sped.negocio.entidades.beans.BeanGrado;
import sped.negocio.entidades.beans.BeanProfesor;

@Stateless(name = "LN_C_SFProfesor", mappedName = "map-LN_C_SFProfesor")
public class LN_C_SFProfesorBean implements LN_C_SFProfesorRemote,
                                            LN_C_SFProfesorLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SPED_NEGOCIO")
    private EntityManager em;

    @EJB
    private BDL_C_SFProfesorLocal bdl_C_SFProfesorLocal;
    private MapperIF mapper = new DozerBeanMapper();
    
    public LN_C_SFProfesorBean() {
    }
    
    public List<BeanProfesor> getProfesoresLN(){        
        List<BeanProfesor> lstBean = new ArrayList();
        List<Profesor> lstProfesores = bdl_C_SFProfesorLocal.getProfesorFindAll();
        System.out.println("SIZE AREAS  "+lstProfesores.size());
        for(Profesor a : lstProfesores){
            BeanProfesor bean = (BeanProfesor) mapper.map(a, BeanProfesor.class);
            lstBean.add(bean);
        }
        return lstBean;
    }
    
    public BeanProfesor findConstrainByDni(String dni){
        try{
            BeanProfesor bean = (BeanProfesor)mapper.map(bdl_C_SFProfesorLocal.getProfesorBydni(dni),BeanProfesor.class);
            return bean;
        } catch(Exception e){
            e.printStackTrace();
            return null;
        }        
    }
}
