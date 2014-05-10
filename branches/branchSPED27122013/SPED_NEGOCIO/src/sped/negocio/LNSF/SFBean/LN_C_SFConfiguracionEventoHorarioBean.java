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

import sped.negocio.BDL.IL.BDL_C_SFConfiguracionEventoHorarioLocal;
import sped.negocio.LNSF.IL.LN_C_SFConfiguracionEventoHorarioLocal;
import sped.negocio.LNSF.IR.LN_C_SFConfiguracionEventoHorarioRemoto;
import sped.negocio.entidades.admin.Nivel;
import sped.negocio.entidades.beans.BeanCombo;
import sped.negocio.entidades.beans.BeanConfiguracionEventoHorario;
import sped.negocio.entidades.sist.ConfiguracionEventoHorario;

@Stateless(name = "LN_C_SFConfiguracionEventoHorario", mappedName = "map-LN_C_SFConfiguracionEventoHorario")
public class LN_C_SFConfiguracionEventoHorarioBean implements LN_C_SFConfiguracionEventoHorarioRemoto,
                                                              LN_C_SFConfiguracionEventoHorarioLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SPED_NEGOCIO")
    private EntityManager em;
 
    @EJB
    BDL_C_SFConfiguracionEventoHorarioLocal bdl_C_SFConfiguracionEventoHorarioLocal;
    public LN_C_SFConfiguracionEventoHorarioBean() {
    }
    
    /**Metodo Para llenar combo de Eventos de Horario
     * @param listaSedes
     * @author czavalacas **/
    public List<BeanCombo> getAllEventosDeHorario() {
        List<ConfiguracionEventoHorario> listaNiveles=bdl_C_SFConfiguracionEventoHorarioLocal.getConfiguracionEventoHorarioFindAll();
        List<BeanCombo> list=new ArrayList<BeanCombo>();
        Iterator it=listaNiveles.iterator();
        while(it.hasNext()){
            ConfiguracionEventoHorario entida= (ConfiguracionEventoHorario)it.next();
            BeanCombo bean=new BeanCombo();
            bean.setId(entida.getNidConfev());
            bean.setDescripcion(entida.getDescripcion());
            list.add(bean);
        }
        return list;  
    }   
    
    public BeanConfiguracionEventoHorario getEventoHorarioByID(int id) {
           ConfiguracionEventoHorario entida=bdl_C_SFConfiguracionEventoHorarioLocal.finEventoById(id);   
           BeanConfiguracionEventoHorario bean=new BeanConfiguracionEventoHorario();
           bean.setNidConfev(entida.getNidConfev());
           bean.setDescripcion(entida.getDescripcion());
           return bean;  
    }           
}
