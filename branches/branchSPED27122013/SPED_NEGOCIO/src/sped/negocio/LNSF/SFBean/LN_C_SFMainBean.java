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
import sped.negocio.entidades.admin.AreaAcademica;
import sped.negocio.entidades.admin.Main;
import sped.negocio.entidades.admin.Profesor;
import sped.negocio.entidades.beans.BeanAreaAcademica;
import sped.negocio.entidades.beans.BeanAula;
import sped.negocio.entidades.beans.BeanMain;
import sped.negocio.entidades.beans.BeanMainWS;
import sped.negocio.entidades.beans.BeanProfesor;

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
    private MapperIF mapper = new DozerBeanMapper();
    
    public LN_C_SFMainBean() {
    }
    
    public List<BeanMain> llenarHorario(BeanMain beanMain){ 
        List<Main>listaMain = bdl_C_SFMainLocal.findHorariosByAttributes(beanMain);        
        List<BeanMain> listaBean = new ArrayList<BeanMain>();       
        Iterator it = listaMain.iterator();
        while(it.hasNext()){
            Main entida= (Main) it.next();
            BeanMain bean = (BeanMain) mapper.map(entida,BeanMain.class);
            listaBean.add(bean);
        }
        return listaBean;
      }
    
    public List<BeanProfesor> findProfesoresPorAreaAcademica_LN(Integer nidAreaAcademica, String dia){
        List<BeanProfesor> lstBean = new ArrayList();
        List<Profesor> lstAreaAcd = bdl_C_SFMainLocal.findProfesoresPorAreaAcademica(nidAreaAcademica,dia);       
        for(Profesor a : lstAreaAcd){
            BeanProfesor bean = (BeanProfesor) mapper.map(a, BeanProfesor.class);
            lstBean.add(bean);
        }
        return lstBean; 
    }
    
    /**
      * Metodo que no utiliza mapper sino mapea defrente al bean desde el mismo query, este metodo busca en la entidad 
      * Main, se usa para el WS, movil, para poder buscar profesores y registrarles un PARTE DE OCURRENCIA
      * @author dfloresgonz
      * @since 24.02.2014
      * @param nidSede
      * @param profesor
      * @param curso
      * @param aula
      * @return List<BeanMainWS>
      */
    public List<BeanMainWS> getMainByAttr_LN_WS(Integer nidSede,
                                                 String profesor,
                                                 String curso,
                                                 String aula){
        return bdl_C_SFMainLocal.getMainByAttr_BDL_WS(nidSede,profesor,curso,aula);
    }
}
