package sped.negocio.LNSF.SFBean;

import java.util.List;

import javax.annotation.Resource;

import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import sped.negocio.BDL.IL.BDL_T_SFAreaAcademicaLocal;
import sped.negocio.LNSF.IL.LN_C_SFErrorLocal;
import sped.negocio.LNSF.IL.LN_T_SFAreaAcademicaLocal;
import sped.negocio.LNSF.IR.LN_T_SFAreaAcademicaRemoto;
import sped.negocio.entidades.admin.AreaAcademica;
import sped.negocio.entidades.admin.Curso;
import sped.negocio.entidades.beans.BeanAreaAcademica;
import sped.negocio.entidades.beans.BeanCurso;
import sped.negocio.entidades.beans.BeanError;

@Stateless(name = "LN_T_SFAreaAcademica", mappedName = "map-LN_T_SFAreaAcademica")
public class LN_T_SFAreaAcademicaBean implements LN_T_SFAreaAcademicaRemoto,
                                                 LN_T_SFAreaAcademicaLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SPED_NEGOCIO")
    private EntityManager em;
    
    @EJB
    private BDL_T_SFAreaAcademicaLocal bdl_T_SFAreaAcademicaLocal;
    @EJB
    private LN_C_SFErrorLocal LN_C_SFErrorLocal;
    public LN_T_SFAreaAcademicaBean() {
    }
    
    public String grabarAreasNuevas(List<BeanAreaAcademica> listaAreas){
        BeanError beanError = new BeanError();
        String error = "000";
        try {
            
            for(int i=0; i<listaAreas.size(); i++){           
            AreaAcademica area=new AreaAcademica();
                area.setNidAreaAcademica(listaAreas.get(i).getNidAreaAcademica());
                area.setDescripcionAreaAcademica(listaAreas.get(i).getDescripcionAreaAcademica());                
                bdl_T_SFAreaAcademicaLocal.persistAreaAcademica(area);
            }
        }catch (Exception e) {            
        e.printStackTrace();
        error = "111";
        beanError = LN_C_SFErrorLocal.getCatalogoErrores(error);
        error = beanError.getDescripcionError();
        }
        return error;
    }
    
    public String grabarArea(BeanAreaAcademica areas){
        BeanError beanError = new BeanError();
        String error = "000";
        try {
                AreaAcademica area=new AreaAcademica();
                area.setNidAreaAcademica(areas.getNidAreaAcademica());
                area.setDescripcionAreaAcademica(areas.getDescripcionAreaAcademica());                
                bdl_T_SFAreaAcademicaLocal.persistAreaAcademica(area);
           
        }catch (Exception e) {            
        e.printStackTrace();
        error = "111";
        beanError = LN_C_SFErrorLocal.getCatalogoErrores(error);
        error = beanError.getDescripcionError();
        }
        return error;
    }
    
}
