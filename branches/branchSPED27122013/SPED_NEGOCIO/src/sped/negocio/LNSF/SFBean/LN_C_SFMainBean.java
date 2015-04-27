package sped.negocio.LNSF.SFBean;

import java.sql.Time;

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
import sped.negocio.Utils.Utiles;
import sped.negocio.entidades.admin.AreaAcademica;
import sped.negocio.entidades.admin.Main;
import sped.negocio.entidades.admin.Profesor;
import sped.negocio.entidades.admin.Sede;
import sped.negocio.entidades.beans.BeanAreaAcademica;
import sped.negocio.entidades.beans.BeanAula;
import sped.negocio.entidades.beans.BeanCombo;
import sped.negocio.entidades.beans.BeanComboString;
import sped.negocio.entidades.beans.BeanConsDesem;
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
    public List<BeanMain> getLstMainByAttr_LN_Aula(String dato) {
        BeanMain beanMain = new BeanMain();
        beanMain.setNidAula(Integer.parseInt(dato));        
        return getLstMainByAttr_LN(beanMain);
    }
    
    /**
     * Metodo que busca las lecciones de los profesores segun el sede y nivel
     * @param dato
     * @param nidSede
     * @param nidNivel
     * @param tipoBusqueda // para saber los horarios de esa sede y nivel y cuales no son.
     * @return
     */
    public List<BeanMain> getLstMainByAttr_LN_Profesor(String dato, int nidSede, int nidNivel, boolean tipoBusqueda) {
        BeanMain beanMain = new BeanMain();
        beanMain.setDniProfesor(dato); 
        beanMain.setNidSede(nidSede);
        beanMain.setNidNivel(nidNivel);
        beanMain.setSelec(tipoBusqueda);        
        return getLstMainByAttr_LN(beanMain);
    }
    
    public List<BeanMain> getLstMainByAttr_LN(BeanMain beanMain){
        List<BeanMain> lstBean = new ArrayList();
        for(Main main : bdl_C_SFMainLocal.getLstMainByAttr_BDL(beanMain)){
            BeanMain bean = trasnferMainNoMapper(main);
            bean.setNidCurso(main.getCurso().getNidCurso());           
            bean.setColor(main.getCurso().getColor() == null || main.getCurso().getColor().length() != 6 ? "647687" : main.getCurso().getColor());
            bean.setColor_prof(main.getProfesor().getColor() == null || main.getProfesor().getColor().length() != 6 ? "647687" : main.getProfesor().getColor());
            lstBean.add(bean);
        }
        return lstBean;
    }
    
    public Main getMainPorSedeNivelYCurso(String nidAula, 
                                          String nidCurso, 
                                          String dniProfesor){
        return bdl_C_SFMainLocal.getMainByAtrubutes(nidAula, nidCurso, dniProfesor);
    }
    
    public int buscarHorariosBySedeYNivel(int nidSede, 
                                          int nidNivel){
        return bdl_C_SFMainLocal.findMainBySedeYNivel(nidSede, nidNivel);
    }
    
    public List<BeanMain> CruceLecionByProfesor(String dniProfesor, 
                                                int dia,
                                                Time inicio, 
                                                Time fin,
                                                int nidMain){
        try{
            List<BeanMain> lstBean = new ArrayList();
            int cont = 0;
            for(Main main : bdl_C_SFMainLocal.countCruceLecionByProfesor(dniProfesor, dia, inicio, fin, nidMain)){
                if(cont == 1){
                    break;
                }
                BeanMain bean = trasnferMainNoMapper(main);
                bean.setNombreAula(main.getAula().getDescripcionAula());
                lstBean.add(bean);
                cont++;
            }
            return lstBean;
        }catch(Exception e){
            e.printStackTrace();
            return new ArrayList();
        }
    }
    
    /**
     * Metodo para pasar los datos de la entidad Main a BeanMain
     * @param main
     * @return
     */
    public BeanMain trasnferMainNoMapper(Main main){
        BeanMain bean = new BeanMain();
        bean.setNidMain(main.getNidMain());
        bean.setNidLecc(main.getNidLeccion());
        bean.setHoraInicio(main.getHoraInicio());
        bean.setHoraFin(main.getHoraFin());
        bean.setNDia(main.getNDia());
        bean.setNombreCurso(main.getCurso().getDescripcionCurso());
        bean.setNidCurso(main.getCurso().getNidCurso());
        bean.setNombreProfesor(main.getProfesor().getApellidos()+", "+main.getProfesor().getNombres());
        bean.setDniProfesor(main.getProfesor().getDniProfesor());
        bean.setNombreAula(main.getAula().getDescripcionAula());
        bean.setNidAula(main.getAula().getNidAula());
        return bean;
    }
    
    public int countMainByNidsEstado_LN(String nidCurso, String nidAula, String dni){
        return bdl_C_SFMainLocal.countMainByNidsEstado(nidCurso, nidAula, dni);
    }
    
    public List<BeanMainWS> getListaMain_Activos(String cidSede,String cidAula,
                                                 String dniProf,String cidCurso){
        List<Object[]> c = bdl_C_SFMainLocal.getMainActivos(cidSede == null ? null : new Integer(cidSede),
                                                            cidAula == null ? null : new Integer(cidAula),dniProf,
                                                            cidCurso == null ? null : new Integer(cidCurso));
        Iterator it = c.iterator();
        List<BeanMainWS> lstMain = new ArrayList<BeanMainWS>();
        while(it.hasNext()){
            Object[] b = (Object[]) it.next();
            BeanMainWS m = new BeanMainWS();
            m.setNidMain( (Integer) b[0]);
            m.setSede( (String) b[1]);
            m.setDniProfesor( (String) b[2] );
            m.setNidAula( (Integer) b[3]);
            m.setNidCurso( (Integer) b[4]);
            m.setProfesor( (String) b[5]);
            m.setAula( (String) b[6]);
            m.setCurso( (String) b[7]);
            m.setNidSede( (Integer) b[8]);
            lstMain.add(m);
        }
        c = null;
        return lstMain;
    }
}