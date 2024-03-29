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
import sped.negocio.BDL.IL.BDL_C_SFCursoLocal;
import sped.negocio.LNSF.IL.LN_C_SFAreaAcademicaLocal;
import sped.negocio.LNSF.IL.LN_C_SFCursoLocal;
import sped.negocio.LNSF.IR.LN_C_SFCursoRemoto;
import sped.negocio.entidades.admin.Curso;
import sped.negocio.entidades.beans.BeanAreaAcademica;
import sped.negocio.entidades.beans.BeanCombo;
import sped.negocio.entidades.beans.BeanCurso;
/** Clase SFBDL SFMainBean.java
 * @author czavalacas
 * @since 30.12.2013
 */
@Stateless(name = "LN_C_SFCurso", mappedName = "map-LN_C_SFCurso")
public class LN_C_SFCursoBean implements LN_C_SFCursoRemoto, 
                                         LN_C_SFCursoLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SPED_NEGOCIO")
    private EntityManager em;
    @EJB
    BDL_C_SFCursoLocal bdl_C_SFCursoLocal;
    @EJB
    BDL_C_SFAreaAcademicaLocal bdl_C_SFAreaAcademicaLocal;
    MapperIF mapper = new DozerBeanMapper();
    
    public LN_C_SFCursoBean() {
    }
    
    /*public List<BeanCurso> findCursosPorAreaAcademica(Integer nidAreaAcademica, String dia){
        List<Curso> listaCursos=bdl_C_SFCursoLocal.findCursosPorAreaAcademica(nidAreaAcademica,dia);
        List<BeanCurso> list=new ArrayList<BeanCurso>();
        MapperIF mapper = new DozerBeanMapper();
        Iterator it=listaCursos.iterator();
        while(it.hasNext()){
            Curso entida= (Curso)it.next();
            BeanCurso bean = (BeanCurso)mapper.map(entida,BeanCurso.class);
            list.add(bean);
        }
        return list;
    }
    */
    public List<BeanCombo> findCursosPorAreaAcademica(Integer nidAreaAcademica, String dia){
        List<Curso> listaCursos=bdl_C_SFCursoLocal.findCursosPorAreaAcademica(nidAreaAcademica,dia);
        List<BeanCombo> list=new ArrayList<BeanCombo>();
        Iterator it=listaCursos.iterator();
        while(it.hasNext()){
            Curso entida= (Curso)it.next();
            BeanCombo bean=new BeanCombo();
            bean.setId(entida.getNidCurso());
            bean.setDescripcion(entida.getDescripcionCurso());
            list.add(bean);
        }
        return list;  
    }    
    
   public List<BeanCombo> findCursosPorAreaAcademica_ByOrden(String nidAreaAcademica, String nidSede){
           List<Curso> listaCursos=bdl_C_SFCursoLocal.findCursosPorAreaAcademica_ByOrden(nidAreaAcademica, nidSede);
           List<BeanCombo> list=new ArrayList<BeanCombo>();
           Iterator it=listaCursos.iterator();
           while(it.hasNext()){
               Curso entida= (Curso)it.next();
               BeanCombo bean=new BeanCombo();
               bean.setId(entida.getNidCurso());
               bean.setDescripcion(entida.getDescripcionCurso());
               list.add(bean);
           }
           return list;
       }
   
    public List<BeanCurso>  getlistaCursos(){
        List<Curso> listaCursos=bdl_C_SFCursoLocal.getCursoFindAll();
        List<BeanCurso> list=new ArrayList<BeanCurso>();
        MapperIF mapper = new DozerBeanMapper();
        Iterator it=listaCursos.iterator();
        while(it.hasNext()){
            Curso entida= (Curso)it.next();
            BeanCurso bean = (BeanCurso)mapper.map(entida,BeanCurso.class);
            list.add(bean);
        }
        return list;
    }

        
    public BeanCurso findConstrainByIdLN(int id){
        try{
            Curso cur=bdl_C_SFCursoLocal.findCursoById(id);            
            BeanCurso bean =new BeanCurso();
            bean.setNidCurso(cur.getNidCurso());
            bean.setDescripcionCurso(cur.getDescripcionCurso());
            bean.setNidAreaNativa(cur.getNidAreaNativa());
            BeanAreaAcademica area=new BeanAreaAcademica();
            area.setNidAreaAcademica(cur.getAreaAcademica().getNidAreaAcademica());
            area.setDescripcionAreaAcademica(cur.getAreaAcademica().getDescripcionAreaAcademica());
            bean.setAreaAcademica(area);            
            return bean;
        } catch(Exception e){
            e.printStackTrace();
            return null;
        }        
    }
    
    public int getNidCursoByDescripcion_LN(String descripcion){
        return bdl_C_SFCursoLocal.getNidCursoByDescripcion(descripcion);
    }
    
    public List<BeanCombo> findCursosByArea(String nidAreaAcademica){
            List<Curso> listaCursos=bdl_C_SFCursoLocal.getCursosbyAreas(nidAreaAcademica);
            List<BeanCombo> list=new ArrayList<BeanCombo>();
            Iterator it=listaCursos.iterator();
            while(it.hasNext()){
                Curso entida= (Curso)it.next();
                BeanCombo bean=new BeanCombo();
                bean.setId(entida.getNidCurso());
                bean.setDescripcion(entida.getDescripcionCurso());
                list.add(bean);
            }
            return list;
        }
    
    public List<BeanCombo> getCursoPorSedeNivelyPofesor(String nidSede, String nidNivel, String dniProfesor, int nidAreaAcademica){
            List<Curso> listaCursos=bdl_C_SFCursoLocal.getCursoPorSedeNivelProfesorYArea(nidSede, nidNivel, dniProfesor, nidAreaAcademica);
            List<BeanCombo> list=new ArrayList<BeanCombo>();
            Iterator it=listaCursos.iterator();
            while(it.hasNext()){
                Curso entida= (Curso)it.next();
                BeanCombo bean=new BeanCombo();
                bean.setId(entida.getNidCurso());
                bean.setDescripcion(entida.getDescripcionCurso());
                list.add(bean);
            }
            return list;
        }
    
    public List<BeanCurso> findCursosByAreaAcademica(String nidArea,String nidNativa){
        try{
            List<Curso> listaCursos=bdl_C_SFCursoLocal.getCursosbyAreaYNativa(nidArea, nidNativa);    
            List<BeanCurso> lstCur=new ArrayList<BeanCurso>();
            Iterator it=listaCursos.iterator();
            while(it.hasNext()){
            Curso cur= (Curso)it.next();
            BeanCurso bean =new BeanCurso();
            bean.setNidCurso(cur.getNidCurso());
            bean.setDescripcionCurso(cur.getDescripcionCurso());
            bean.setNidAreaNativa(cur.getNidAreaNativa());
            bean.setDescAreaNativa(bdl_C_SFAreaAcademicaLocal.findEvaluadorById(cur.getNidAreaNativa()).getDescripcionAreaAcademica());
            BeanAreaAcademica area=new BeanAreaAcademica();
            area.setNidAreaAcademica(cur.getAreaAcademica().getNidAreaAcademica());
            area.setDescripcionAreaAcademica(cur.getAreaAcademica().getDescripcionAreaAcademica());
            bean.setAreaAcademica(area);
            if(cur.getFlgActi()==0){                         
                             bean.setStyleColor("color:white; font-weight:bold;text-align:center; background-color:red");
                             bean.setFlgActi(cur.getFlgActi());
               }
            if(cur.getFlgActi()==1){
                              bean.setStyleColor("color:White; font-weight:bold;text-align:center; background-color:green");
                              bean.setFlgActi(cur.getFlgActi());
               }                
            lstCur.add(bean);   
            }
            return lstCur;
        } catch(Exception e){
            e.printStackTrace();
            return null;
        }        
    }
}
