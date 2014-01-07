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

import sped.negocio.BDL.IL.BDL_C_SFMainLocal;
import sped.negocio.LNSF.IL.LN_C_SFMainLocal;
import sped.negocio.LNSF.IR.LN_C_SFMainRemote;
import sped.negocio.entidades.admin.Main;
import sped.negocio.entidades.beans.BeanAula;
import sped.negocio.entidades.beans.BeanMain;
/** Clase SFLN SFMainBean.java
 * @author czavalacas 
 * @since 29.12.2013
 */
@Stateless(name = "LN_C_SFMain", mappedName = "SPED_APP-SPED_NEGOCIO-LN_C_SFMain")
public class LN_C_SFMainBean implements LN_C_SFMainRemote, 
                                        LN_C_SFMainLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SPED_NEGOCIO")
    private EntityManager em;
    @EJB
    BDL_C_SFMainLocal bdl_C_SFMainLocal;
        
    public LN_C_SFMainBean() {
    }
    
    public List<BeanMain> llenarHorario(BeanMain beanMain){
  //      System.out.println("Entro a llenarhorario");
        List<Main>listaMain=bdl_C_SFMainLocal.findHorariosByAttributes(beanMain);        
        List<BeanMain> listaBean=new ArrayList<BeanMain>();
        MapperIF mapper = new DozerBeanMapper();
        Iterator it=listaMain.iterator();
        while(it.hasNext()){
            Main entida= (Main)it.next();
            BeanMain bean = (BeanMain)mapper.map(entida,BeanMain.class);
            listaBean.add(bean);
        }
        return listaBean;
      }
}
