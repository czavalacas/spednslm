package sped.negocio.LNSF.SFBean;

import java.util.ArrayList;
import java.util.Collections;
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
import sped.negocio.entidades.beans.BeanCombo;
import sped.negocio.entidades.beans.BeanComboString;
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
    
    public List<BeanComboString> findProfesoresPorAreaAcademica_LN(Integer nidAreaAcademica, String dia){
        List<BeanComboString> lstBean = new ArrayList();
        List<Profesor> lstAreaAcd = bdl_C_SFMainLocal.findProfesoresPorAreaAcademica(nidAreaAcademica,dia);       
        Iterator it=lstAreaAcd.iterator();
        while(it.hasNext()){
          Profesor entida=(Profesor)it.next();
          BeanComboString bean=new BeanComboString();
          bean.setId(entida.getDniProfesor());
          String nombreCompleto=entida.getApellidos()+" "+entida.getNombres();
          bean.setDescripcion(nombreCompleto);        
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

    /**
     * Metodo que trae lista main dependiendo del aula
     * @author angeles
     * @param nidAula
     * @return
     */
    public List<BeanMain> getLstMainByAttr_LN(String nidAula) {
        BeanMain beanMain = new BeanMain();
        beanMain.setNidAula(Integer.parseInt(nidAula));//paso el nidAula a un bean por si despues vuelvo utilizar este metodo
        List<BeanMain> lstBean = new ArrayList();
        for(Main main : bdl_C_SFMainLocal.getLstMainByAttr_BDL(beanMain)){
            BeanMain bean = new BeanMain();
            bean.setHoraInicio(main.getHoraInicio());
            bean.setHoraFin(main.getHoraFin());
            bean.setNDia(main.getNDia());
            lstBean.add(bean);
        }
        return lstBean;
    }
    
    public List<BeanMain> transformListBeanMain(List<Main> lstMain){
        try{
            List<BeanMain> lstBean = new ArrayList();
            for(Main m : lstMain){
                BeanMain beanMain = (BeanMain) mapper.map(m, BeanMain.class);
                lstBean.add(beanMain);
            }
            return lstBean;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
    
    public Main getMainPorSedeNivelYCurso(String nidAula, String nidCurso, String dniProfesor){
        return bdl_C_SFMainLocal.getMainByAtrubutes(nidAula, nidCurso, dniProfesor);
    }
    
}
