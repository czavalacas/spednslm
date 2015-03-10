package sped.negocio.LNSF.SFBean;

import java.util.List;

import javax.annotation.Resource;

import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import sped.negocio.BDL.IL.BDL_C_SFLeccionLocal;
import sped.negocio.BDL.IL.BDL_C_SFMainLocal;
import sped.negocio.BDL.IL.BDL_T_SFLeccionBeanLocal;
import sped.negocio.BDL.IR.BDL_T_SFLeccionBeanRemote;
import sped.negocio.LNSF.IL.LN_T_SFLeccionLocal;
import sped.negocio.LNSF.IL.LN_T_SFLoggerLocal;
import sped.negocio.LNSF.IL.LN_T_SFMainLocal;
import sped.negocio.LNSF.IR.LN_T_SFLeccionRemote;
import sped.negocio.Utils.Utiles;
import sped.negocio.entidades.admin.Aula;
import sped.negocio.entidades.admin.Curso;
import sped.negocio.entidades.admin.Main;
import sped.negocio.entidades.admin.Profesor;
import sped.negocio.entidades.beans.BeanLeccion;
import sped.negocio.entidades.sist.Leccion;

@Stateless(name = "LN_T_SFLeccion", mappedName = "map-LN_T_SFLeccion")
public class LN_T_SFLeccionBean implements LN_T_SFLeccionRemote, 
                                           LN_T_SFLeccionLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SPED_NEGOCIO")
    private EntityManager em;
    
    @EJB
    private BDL_T_SFLeccionBeanLocal  bdL_T_SFLeccionBeanLocal;
    @EJB
    private BDL_C_SFLeccionLocal bdL_C_SFLeccionLocal;
    @EJB
    private BDL_C_SFMainLocal bdL_C_SFMainLocal;
    @EJB
    private LN_T_SFLoggerLocal ln_T_SFLoggerLocal;
    @EJB
    private LN_T_SFMainLocal ln_T_SFMainLocal;
    
    private static final String CLASE = "sped.negocio.LNSF.SFBean.LN_T_SFLeccionBean";

    public LN_T_SFLeccionBean() {
    }
    
    public List<BeanLeccion> gestionarLecciones(boolean valida, List<BeanLeccion> lst){
        for(BeanLeccion l : lst){
            l = gestionarLeccion(valida, l);
        }
        return lst;
    }
    
    public BeanLeccion gestionarLeccion(boolean valida, BeanLeccion l){
        if(l.getNidLecc() == 0){
            Leccion lec = new Leccion();
            Aula a = new Aula();
            a.setNidAula(l.getAula().getNidAula());
            lec.setAula(a);
            Profesor p = new Profesor();
            p.setDniProfesor(l.getProfesor().getDniProfesor());
            lec.setProfesor(p);
            Curso c = new Curso();
            c.setNidCurso(l.getCurso().getNidCurso());
            lec.setCurso(c);
            lec.setNroDuracion(l.getNroDuracion());
            lec.setNroHoras(l.getNroHoras());
            lec.setNroHoras_aux(l.getNroHoras_aux());
            lec.setEstado("1");
            bdL_T_SFLeccionBeanLocal.persistLecciones(lec);           
            l.setNidLecc(lec.getNidLecc());
        }else if(valida){
            Leccion lec = bdL_C_SFLeccionLocal.findLeccionById(l.getNidLecc());
            lec.setNroHoras(l.getNroHoras());
            lec.setNroHoras_aux(l.getNroHoras_aux());
            bdL_T_SFLeccionBeanLocal.mergeLecciones(lec);
        }
        return l;
    }
    
    /**
     * Metodo para eliminar los datos de las lecciones. Primero elimina en main y luego en lecciones
     * @param lec
     */
    public void eliminarLeccion(BeanLeccion lec){
        try{
            ln_T_SFMainLocal.eliminarMainByLecc(lec.getNidLecc(), lec.getNroHoras_aux() * lec.getNroDuracion());
            removeLeccion(lec.getNidLecc());
        }catch(Exception e){
            ln_T_SFLoggerLocal.registrarLogErroresSistema(0, "TRA", CLASE, "eliminarLeccion(BeanLeccion lec)", 
                                                          "Error al eliminar la leccion", 
                                                          Utiles.getStack(e)); 
        }
    }
    
    public void removeLeccion(int nidLeccion){
        Leccion leccion = bdL_C_SFLeccionLocal.findLeccionById(nidLeccion);
        bdL_T_SFLeccionBeanLocal.removeLecciones(leccion);
    }
    
    /**
     * Metodo para eliminar las entidades lecciones con su respectivo main.
     * @param lst
     */
    public void eliminarLecciones(List<BeanLeccion> lst){
        for(BeanLeccion lec : lst){
            eliminarLeccion(lec);
        }
    }
}
