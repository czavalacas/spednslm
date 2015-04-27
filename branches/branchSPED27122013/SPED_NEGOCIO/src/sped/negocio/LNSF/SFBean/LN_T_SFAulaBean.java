package sped.negocio.LNSF.SFBean;

import java.util.List;

import javax.annotation.Resource;

import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import sped.negocio.BDL.IL.BDL_C_SFAulaLocal;
import sped.negocio.BDL.IL.BDL_T_SFAulaLocal;
import sped.negocio.LNSF.IL.LN_C_SFErrorLocal;
import sped.negocio.LNSF.IL.LN_T_SFAulaLocal;
import sped.negocio.LNSF.IR.LN_T_SFAulaRemoto;
import sped.negocio.entidades.admin.AreaAcademica;
import sped.negocio.entidades.admin.Aula;
import sped.negocio.entidades.admin.Grado;
import sped.negocio.entidades.admin.GradoNivel;
import sped.negocio.entidades.admin.Nivel;
import sped.negocio.entidades.admin.Sede;
import sped.negocio.entidades.beans.BeanAreaAcademica;
import sped.negocio.entidades.beans.BeanAula;
import sped.negocio.entidades.beans.BeanError;

@Stateless(name = "LN_T_SFAula", mappedName = "map-LN_T_SFAula")
public class LN_T_SFAulaBean implements LN_T_SFAulaRemoto, 
                                        LN_T_SFAulaLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SPED_NEGOCIO")
    private EntityManager em;
    @EJB    
    private BDL_T_SFAulaLocal bdl_T_SFAulaLocal;
    @EJB    
    private BDL_C_SFAulaLocal bdl_C_SFAulaLocal;
    @EJB
    private LN_C_SFErrorLocal LN_C_SFErrorLocal;
    public LN_T_SFAulaBean() {
    }
    
    public String grabarAulasNuevas(List<BeanAula> listaAulas){
        BeanError beanError = new BeanError();
        String error = "000";
        try {
            
            for(int i=0; i<listaAulas.size(); i++){           
            Aula aula=new Aula();
            GradoNivel grani=new GradoNivel();
            Grado gra=new Grado();
            Nivel nive=new Nivel();
            Sede sed=new Sede();
                aula.setNidAula(listaAulas.get(i).getNidAula());
                aula.setDescripcionAula(listaAulas.get(i).getDescripcionAula());
                gra.setNidGrado(listaAulas.get(i).getGradoNivel().getGrado().getNidGrado());
                nive.setNidNivel(listaAulas.get(i).getGradoNivel().getNivel().getNidNivel());
                sed.setNidSede(listaAulas.get(i).getSede().getNidSede());
                grani.setGrado(gra);
                grani.setNivel(nive);
                aula.setGradoNivel(grani);
                aula.setSede(sed);                
                bdl_T_SFAulaLocal.persistAula(aula);
            }
        }catch (Exception e) {            
        e.printStackTrace();
        error = "111";
        beanError = LN_C_SFErrorLocal.getCatalogoErrores(error);
        error = beanError.getDescripcionError();
        }
        return error;
    }
    
    public String grabarAula(BeanAula aulaNueva){
        BeanError beanError = new BeanError();
        String error = "000";
        try {
            Aula aula=new Aula();
            GradoNivel grani=new GradoNivel();
            Grado gra=new Grado();
            Nivel nive=new Nivel();
            Sede sed=new Sede();
            if(aulaNueva.getNidAula()!=null){
                aula.setNidAula(aulaNueva.getNidAula());  
            }               
                aula.setDescripcionAula(aulaNueva.getDescripcionAula());
                gra.setNidGrado(aulaNueva.getGradoNivel().getGrado().getNidGrado());
                nive.setNidNivel(aulaNueva.getGradoNivel().getNivel().getNidNivel());
                sed.setNidSede(aulaNueva.getSede().getNidSede());
                grani.setGrado(gra);
                grani.setNivel(nive);
                aula.setGradoNivel(grani);
                aula.setSede(sed); 
                aula.setFlgActi(aulaNueva.getFlgActi());
                bdl_T_SFAulaLocal.persistAula(aula);
            
        }catch (Exception e) {            
        e.printStackTrace();
        error = "111";
        beanError = LN_C_SFErrorLocal.getCatalogoErrores(error);
        error = beanError.getDescripcionError();
        }
        return error;
    }
    
    public String actualizarAula(BeanAula aulaNueva){
        BeanError beanError = new BeanError();
        String error = "000";
        try {
            Aula aula=bdl_C_SFAulaLocal.findAulaById(aulaNueva.getNidAula());
            Grado grado=new Grado();
            grado.setNidGrado(aulaNueva.getGradoNivel().getGrado().getNidGrado());
            Nivel nivel=new Nivel();
            nivel.setNidNivel(aulaNueva.getGradoNivel().getNivel().getNidNivel());
            GradoNivel grani= new GradoNivel();
            grani.setGrado(grado);
            grani.setNivel(nivel);
            aula.setGradoNivel(grani);
            aula.setDescripcionAula(aulaNueva.getDescripcionAula());
            aula.setFlgActi(aulaNueva.getFlgActi());
            bdl_T_SFAulaLocal.mergeAula(aula);
            
        }catch (Exception e) {            
        e.printStackTrace();
        error = "111";
        beanError = LN_C_SFErrorLocal.getCatalogoErrores(error);
        error = beanError.getDescripcionError();
        }
        return error;
    }
    
}
