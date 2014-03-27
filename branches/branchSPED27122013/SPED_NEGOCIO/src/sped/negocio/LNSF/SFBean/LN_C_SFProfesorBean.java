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
import sped.negocio.BDL.IL.BDL_C_SFProfesorLocal;
import sped.negocio.LNSF.IL.LN_C_SFProfesorLocal;
import sped.negocio.LNSF.IR.LN_C_SFProfesorRemote;
import sped.negocio.entidades.admin.AreaAcademica;
import sped.negocio.entidades.admin.Profesor;
import sped.negocio.entidades.beans.BeanAreaAcademica;
import sped.negocio.entidades.beans.BeanComboString;
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
    
    /*public List<BeanProfesor> getProfesoresLN(){        
        List<BeanProfesor> lstBean = new ArrayList();
        List<Profesor> lstProfesores = bdl_C_SFProfesorLocal.getProfesores();
        for(Profesor a : lstProfesores){
            BeanProfesor bean = (BeanProfesor) mapper.map(a, BeanProfesor.class);
            lstBean.add(bean);
        }
        return lstBean;
    }*/
    
    public List<BeanComboString> getProfesoresLN(){        
            List<BeanComboString> lstBean = new ArrayList<BeanComboString>();
            List<Profesor> lstProfesores = bdl_C_SFProfesorLocal.getProfesores();
          Iterator it=lstProfesores.iterator();
          while(it.hasNext()){
            Profesor entida=(Profesor)it.next();
            BeanComboString bean=new BeanComboString();
            bean.setId(entida.getDniProfesor());
            bean.setDescripcion(entida.getApellidos()+" "+entida.getNombres());
            lstBean.add(bean);          
          }
            return lstBean;
        }    
 
    public List<BeanComboString> getProfesoresLN_PorSede_ByOrden(Object nidSede, Object nidArea, Object nidCurso, Object nidNivel, Object nidGrado){        
            List<BeanComboString> lstBean = new ArrayList<BeanComboString>();
            String a=null; String b=null; String c=null; String d=null; String e=null;
            if(nidSede!=null){
                a=nidSede.toString();
            }
            if(nidArea!=null){
                b=nidArea.toString();
            }
            if(nidCurso!=null){
                c=nidCurso.toString();
            }
            if(nidNivel!=null){
                d=nidNivel.toString();
            }
            if(nidGrado!=null){
                e=nidGrado.toString();
            }
            List<Profesor> lstProfesores = bdl_C_SFProfesorLocal.findProfesorPorSede_ByOrden(a,b,c,d,e);
            Iterator it=lstProfesores.iterator();
            while(it.hasNext()){
              Profesor entida=(Profesor)it.next();
              BeanComboString bean=new BeanComboString();
              bean.setId(entida.getDniProfesor());
              bean.setDescripcion(entida.getApellidos()+" "+entida.getNombres());
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
    
    public boolean exiteDni_LN(String dni){
        return bdl_C_SFProfesorLocal.existeDni(dni) > 0 ? true : false;
    }
    
}
