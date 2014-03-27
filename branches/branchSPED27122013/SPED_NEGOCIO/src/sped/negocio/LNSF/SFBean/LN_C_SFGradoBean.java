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

import sped.negocio.BDL.IL.BDL_C_SFGradoLocal;
import sped.negocio.LNSF.IL.LN_C_SFGradoLocal;
import sped.negocio.LNSF.IR.LN_C_SFGradoRemote;
import sped.negocio.entidades.admin.Curso;
import sped.negocio.entidades.admin.Grado;
import sped.negocio.entidades.admin.Nivel;
import sped.negocio.entidades.beans.BeanCombo;
import sped.negocio.entidades.beans.BeanCurso;
import sped.negocio.entidades.beans.BeanGrado;
import sped.negocio.entidades.beans.BeanNivel;
import sped.negocio.entidades.beans.BeanSede;

@Stateless(name = "LN_C_SFGrado", mappedName = "map-LN_C_SFGrado")
public class LN_C_SFGradoBean implements LN_C_SFGradoRemote, 
                                         LN_C_SFGradoLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SPED_NEGOCIO")
    private EntityManager em;
    @EJB
    BDL_C_SFGradoLocal bdl_C_SFGradoLocal;
    private MapperIF mapper = new DozerBeanMapper();
    public LN_C_SFGradoBean() {
    }   

    public List<BeanCombo> findGradoPorAreaAcademica(Integer nidAreaAcademica, String dia){
            List<Grado> listaGrados=bdl_C_SFGradoLocal.findGradpPorAreaAcademica(nidAreaAcademica,dia);
            List<BeanCombo> list=new ArrayList<BeanCombo>();
            Iterator it=listaGrados.iterator();
            while(it.hasNext()){
                Grado entida= (Grado)it.next();
                BeanCombo bean = new BeanCombo();
                bean.setId(entida.getNidGrado());
                bean.setDescripcion(entida.getDescripcionGrado());
                list.add(bean);
            }
            return list;
        }
    
    public List<BeanGrado> getGradoLN(){
        List<Grado> listaGrados=bdl_C_SFGradoLocal.getGradoFindAll();
        List<BeanGrado> list=new ArrayList<BeanGrado>();
        MapperIF mapper = new DozerBeanMapper();
        Iterator it=listaGrados.iterator();
        while(it.hasNext()){
            Grado entida= (Grado)it.next();
            BeanGrado bean = (BeanGrado)mapper.map(entida,BeanGrado.class);
            list.add(bean);
        }
        return list;
    }
    
    public BeanGrado findConstrainByIdLN(int id){
        try{
            BeanGrado bean = (BeanGrado)mapper.map(bdl_C_SFGradoLocal.findGradoById(id),BeanGrado.class);
            return bean;
        } catch(Exception e){
            e.printStackTrace();
            return null;
        }        
    }
    
    /* public List<BeanGrado> getGradoLN_PorNivelByOrden(String nidNivel){
        List<BeanGrado> lstBean = new ArrayList();
        List<Grado> lstNivel = bdl_C_SFGradoLocal.findGradosPorNivel_ByOrden(nidNivel);
        for(Grado n : lstNivel){
            BeanGrado bean = (BeanGrado) mapper.map(n, BeanGrado.class);
            lstBean.add(bean);
        }
        return lstBean;
    }*/
    public List<BeanCombo> getGradoLN_PorNivelByOrden(String nidNivel){
            List<BeanCombo> lstBean = new ArrayList<BeanCombo>();
            List<Grado> lstNivel = bdl_C_SFGradoLocal.findGradosPorNivel_ByOrden(nidNivel);
           Iterator it=lstNivel.iterator();
           while(it.hasNext()){
              Grado entida= (Grado)it.next();
               BeanCombo bean=new BeanCombo();
               bean.setId(entida.getNidGrado());
               bean.setDescripcion(entida.getDescripcionGrado());
               lstBean.add(bean);
           }
            return lstBean;
    }    
}
