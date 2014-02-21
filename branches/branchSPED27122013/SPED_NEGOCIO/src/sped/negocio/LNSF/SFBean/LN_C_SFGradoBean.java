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
import sped.negocio.entidades.beans.BeanCurso;
import sped.negocio.entidades.beans.BeanGrado;
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
    
    public List<BeanGrado> findGradoPorAreaAcademica(Integer nidAreaAcademica, String dia){
        List<Grado> listaGrados=bdl_C_SFGradoLocal.findGradpPorAreaAcademica(nidAreaAcademica,dia);
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
}
