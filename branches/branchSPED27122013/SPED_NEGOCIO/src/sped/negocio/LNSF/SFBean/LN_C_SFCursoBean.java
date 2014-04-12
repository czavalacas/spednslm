package sped.negocio.LNSF.SFBean;

import com.rsa.cryptoj.c.u;

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

import sped.negocio.BDL.IL.BDL_C_SFCursoLocal;
import sped.negocio.LNSF.IL.LN_C_SFCursoLocal;
import sped.negocio.LNSF.IR.LN_C_SFCursoRemoto;
import sped.negocio.entidades.admin.Curso;
import sped.negocio.entidades.admin.Main;
import sped.negocio.entidades.beans.BeanCombo;
import sped.negocio.entidades.beans.BeanCriterio;
import sped.negocio.entidades.beans.BeanCurso;
import sped.negocio.entidades.beans.BeanMain;
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
            BeanCurso bean = (BeanCurso)mapper.map(bdl_C_SFCursoLocal.findCursoById(id),BeanCurso.class);
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
        System.out.println("entrooo");
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
}
