package sped.negocio.LNSF.SFBean;

import java.util.ArrayList;
import java.util.HashSet;
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

import sped.negocio.BDL.IL.BDL_C_SFIndicadorLocal;
import sped.negocio.LNSF.IL.LN_C_SFIndicadorLocal;
import sped.negocio.LNSF.IR.LN_C_SFIndicadorRemote;
import sped.negocio.entidades.beans.BeanCriterio;
import sped.negocio.entidades.beans.BeanIndicador;
import sped.negocio.entidades.eval.Indicador;

@Stateless(name = "LN_C_SFIndicador", mappedName = "mapLN_C_SFIndicador")
public class LN_C_SFIndicadorBean implements LN_C_SFIndicadorRemote,
                                                LN_C_SFIndicadorLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SPED_NEGOCIO")
    private EntityManager em;
    MapperIF mapper = new DozerBeanMapper();
    @EJB
    private BDL_C_SFIndicadorLocal bdL_C_SFIndicadorLocal;

    public LN_C_SFIndicadorBean() {
    }
    
    public List<BeanIndicador> getIndicadoresByAttr_LN(String descIndicador, 
                                                       int nidIndicador,// HashSet<BeanCriterio> lstCritsArbol
                                                       List<BeanCriterio> lstCritsArbol){
        try{
            BeanIndicador beanIndicador = new BeanIndicador();
            beanIndicador.setDescripcionIndicador(descIndicador);
            beanIndicador.setNidIndicador(nidIndicador);
            if(lstCritsArbol != null){
                if(lstCritsArbol.size() > 0){
                    beanIndicador.setLstCritsArbol(this.toListInteger(lstCritsArbol));
                }
            }
            return transformLstIndicadores(bdL_C_SFIndicadorLocal.getIndicadoresByAttr_BD(beanIndicador));
        }catch(Exception e){
            return new ArrayList<BeanIndicador>();
        }
    }  
    
    public List<BeanIndicador> transformLstIndicadores(List<Indicador> lstIndis){
        try{
            BeanIndicador beanIndicador = new BeanIndicador();
            List<BeanIndicador> lstIndicadores = new ArrayList<BeanIndicador>();
            for(Indicador indicador : lstIndis){
                beanIndicador = (BeanIndicador) mapper.map(indicador,BeanIndicador.class);
                lstIndicadores.add(beanIndicador);
            }
            return lstIndicadores;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    
    public List<Integer> toListInteger(/*HashSet<BeanCriterio> lstCritsArbol*/List<BeanCriterio> lstCritsArbol){
        List<Integer> lstNidsANoBuscar = new ArrayList<Integer>();
        Iterator it = lstCritsArbol.iterator();
        while(it.hasNext()){
            BeanCriterio crit = (BeanCriterio) it.next();
            if(crit.getLstIndicadores() != null){
                if(crit.getLstIndicadores().size() > 0){
                    Iterator iti = crit.getLstIndicadores().iterator();
                    while(iti.hasNext()){
                        BeanCriterio crit2 = (BeanCriterio) iti.next();
                        lstNidsANoBuscar.add(crit2.getNidCriterio());
                    }
                }
            }
        }
        return lstNidsANoBuscar;
    }
    
    public List<BeanIndicador> getIndicadoresByDescripcion_LN(String Indicador){        
        return transformLstIndicadores(bdL_C_SFIndicadorLocal.getIndicadoresByDescripcion(Indicador));
    }
    
    public List getNombreIndicadores_LN(){
        return bdL_C_SFIndicadorLocal.getNombreIndicadores();
    }
    public BeanIndicador getIndicadorByDescripcion(String descripcion){
        Indicador indicador=bdL_C_SFIndicadorLocal.getIndicadorByDescripcion(descripcion);
        BeanIndicador beanIndi=new BeanIndicador();
        beanIndi = (BeanIndicador) mapper.map(indicador,BeanIndicador.class);
        return beanIndi;
    }
}