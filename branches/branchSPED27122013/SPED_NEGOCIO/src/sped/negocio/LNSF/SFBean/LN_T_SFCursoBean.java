package sped.negocio.LNSF.SFBean;

import java.util.List;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import sped.negocio.BDL.IL.BDL_C_SFCursoLocal;
import sped.negocio.BDL.IL.BDL_T_SFCursoLocal;
import sped.negocio.LNSF.IL.LN_C_SFErrorLocal;
import sped.negocio.LNSF.IL.LN_T_SFCursoLocal;
import sped.negocio.LNSF.IR.LN_T_SFCursoRemoto;
import sped.negocio.entidades.admin.AreaAcademica;
import sped.negocio.entidades.admin.Curso;
import sped.negocio.entidades.beans.BeanCurso;
import sped.negocio.entidades.beans.BeanError;

@Stateless(name = "LN_T_SFCurso", mappedName = "map-LN_T_SFCurso")
public class LN_T_SFCursoBean implements LN_T_SFCursoRemoto, 
                                         LN_T_SFCursoLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SPED_NEGOCIO")
    private EntityManager em;

    @EJB 
    private BDL_T_SFCursoLocal bdl_T_SFCursoLocal;
    @EJB
    private LN_C_SFErrorLocal LN_C_SFErrorLocal;
    @EJB
    private BDL_C_SFCursoLocal bdL_C_SFCursoLocal;
    
    public LN_T_SFCursoBean() {
    }
    
    public String grabarCursosNuevos(List<BeanCurso> listaCursos){
        BeanError beanError = new BeanError();
        String error = "000";
        try {            
            for(int i=0; i<listaCursos.size(); i++){
            Curso cur=new Curso();
            AreaAcademica area=new AreaAcademica();
                area.setNidAreaAcademica(listaCursos.get(i).getAreaAcademica().getNidAreaAcademica());
                cur.setAreaAcademica(area);
                cur.setDescripcionCurso(listaCursos.get(i).getDescripcionCurso());
                cur.setNidCurso(listaCursos.get(i).getNidCurso());
                //cur.setTipoFichaCurso(listaCursos.get(i).getTipoFichaCurso());//dfloresgonz 12.04.2014 se comenta x modificacion en BD              
                bdl_T_SFCursoLocal.persistCurso(cur);
            }
        }catch (Exception e) {            
        e.printStackTrace();
        error = "111";
        beanError = LN_C_SFErrorLocal.getCatalogoErrores(error);
        error = beanError.getDescripcionError();
        }
        return error;
    }
    
    public String grabarCurso(BeanCurso curso){
        BeanError beanError = new BeanError();
        String error = "000";
        try {            
            Curso cur=new Curso();
            AreaAcademica area=new AreaAcademica();
                area.setNidAreaAcademica(curso.getAreaAcademica().getNidAreaAcademica());
                cur.setAreaAcademica(area);
                cur.setDescripcionCurso(curso.getDescripcionCurso());
                cur.setNidCurso(curso.getNidCurso());
                //cur.setTipoFichaCurso(curso.getTipoFichaCurso());//dfloresgonz 12.04.2014 se comenta x modificacion en BD  
                bdl_T_SFCursoLocal.persistCurso(cur);            
        }catch (Exception e) {            
        e.printStackTrace();
        error = "111";
        beanError = LN_C_SFErrorLocal.getCatalogoErrores(error);
        error = beanError.getDescripcionError();
        }
        return error;
    }
    
    /**
     * Metodo para cambiar el color de un curso
     * @param nidCurso
     */
    public void modificarColor(int nidCurso, String color){
        try{
            Curso curso = bdL_C_SFCursoLocal.findCursoById(nidCurso);
            curso.setColor(color);
            bdl_T_SFCursoLocal.mergeCurso(curso);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
}
