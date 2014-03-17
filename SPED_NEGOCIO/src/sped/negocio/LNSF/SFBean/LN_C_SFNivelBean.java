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

import sped.negocio.BDL.IL.BDL_C_SFNivelLocal;
import sped.negocio.LNSF.IL.LN_C_SFNivelLocal;
import sped.negocio.LNSF.IR.LN_C_SFNivelRemote;
import sped.negocio.entidades.admin.Grado;
import sped.negocio.entidades.admin.Nivel;
import sped.negocio.entidades.beans.BeanGrado;
import sped.negocio.entidades.beans.BeanNivel;

@Stateless(name = "LN_C_SFNivel", mappedName = "SPED_APP-SPED_NEGOCIO-LN_C_SFNivel")
public class LN_C_SFNivelBean implements LN_C_SFNivelRemote, 
                                         LN_C_SFNivelLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SPED_NEGOCIO")
    private EntityManager em;
    private MapperIF mapper = new DozerBeanMapper();
    
    @EJB
    private BDL_C_SFNivelLocal bdL_C_SFNivelLocal;

    public LN_C_SFNivelBean() {
    }
    
    public List<BeanNivel> getNivelLN(){
        List<BeanNivel> lstBean = new ArrayList();
        List<Nivel> lstNivel = bdL_C_SFNivelLocal.getNivelFindAll();
        for(Nivel n : lstNivel){
            BeanNivel bean = (BeanNivel) mapper.map(n, BeanNivel.class);
            lstBean.add(bean);
        }
        return lstBean;
    }
    
    public List<BeanNivel> findNivelPorAreaAcademica(Integer nidAreaAcademica, String dia){
        List<Nivel> listaNiveles=bdL_C_SFNivelLocal.findGradpPorAreaAcademica(nidAreaAcademica,dia);
        List<BeanNivel> list=new ArrayList<BeanNivel>();
        MapperIF mapper = new DozerBeanMapper();
        Iterator it=listaNiveles.iterator();
        while(it.hasNext()){
            Nivel entida= (Nivel)it.next();
            BeanNivel bean = (BeanNivel)mapper.map(entida,BeanNivel.class);
            list.add(bean);
        }
        return list;
    }
    
    public BeanNivel findConstrainByIdLN(int id){
        try{
            BeanNivel bean = (BeanNivel)mapper.map(bdL_C_SFNivelLocal.findNivelById(id),BeanNivel.class);
            return bean;
        } catch(Exception e){
            e.printStackTrace();
            return null;
        }        
    }
    
    public List<BeanNivel> getNivelLNPorSede_ByOrden(Object nidSede, Object nidArea, Object nidCurso){
        List<BeanNivel> lstBean = new ArrayList();
        String a=null;String b=null;String c=null;
        if(nidSede!=null){
            a=nidSede.toString();;
        }
        if(nidArea!=null){
            b=nidArea.toString();;
        }
        if(nidCurso!=null){
            c=nidCurso.toString();;
        }
        List<Nivel> lstNivel = bdL_C_SFNivelLocal.findNivelesPorSede_ByOrden(a, b, c);
        for(Nivel n : lstNivel){
            BeanNivel bean = (BeanNivel) mapper.map(n, BeanNivel.class);
            lstBean.add(bean);
        }
        return lstBean;
    }
}