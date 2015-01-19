package sped.negocio.LNSF.SFBean;

import java.sql.Time;

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

import sped.negocio.BDL.IL.BDL_C_SFRestriccionHorarioLocal;
import sped.negocio.LNSF.IL.LN_C_SFRestriccionHorarioLocal;
import sped.negocio.LNSF.IR.LN_C_SFRestriccionHorarioRemote;
import sped.negocio.entidades.beans.BeanRestriccionHorario;
import sped.negocio.entidades.sist.RestriccionHorario;

@Stateless(name = "LN_C_SFRestriccionHorario", mappedName = "SPED_APP-SPED_NEGOCIO-LN_C_SFRestriccionHorario")
public class LN_C_SFRestriccionHorarioBean implements LN_C_SFRestriccionHorarioRemote, 
                                                      LN_C_SFRestriccionHorarioLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SPED_NEGOCIO")
    private EntityManager em;
    MapperIF mapper = new DozerBeanMapper();
    @EJB
    private BDL_C_SFRestriccionHorarioLocal bdL_C_SFRestriccionHorarioLocal;

    public LN_C_SFRestriccionHorarioBean() {
    }
    
    public List<BeanRestriccionHorario> listRestriccionHorarioByNidExcepcion(String tipo, String nid){
        try{
            List<BeanRestriccionHorario> listBean = new ArrayList();
            for(RestriccionHorario r : bdL_C_SFRestriccionHorarioLocal.listRestriccionHorarioByNidExcepcion(tipo, nid)){
                listBean.add((BeanRestriccionHorario)mapper.map(r, BeanRestriccionHorario.class));
            }
            return listBean;
        }catch(Exception ex){
            ex.printStackTrace();
            return new ArrayList();
        }
    }
    
    public List<BeanRestriccionHorario> countCruceRestriccionHorario(String codigo, 
                                                                     int dia,
                                                                     Time inicio, 
                                                                     Time fin){
        try{
            List<BeanRestriccionHorario> listBean = new ArrayList();
            for(RestriccionHorario r : bdL_C_SFRestriccionHorarioLocal.countCruceRestriccionHorario(codigo, dia, inicio, fin)){
                listBean.add((BeanRestriccionHorario)mapper.map(r, BeanRestriccionHorario.class));
            }
            return listBean;
        }catch(Exception e){
            e.printStackTrace();
            return new ArrayList();
        }
    }
}
