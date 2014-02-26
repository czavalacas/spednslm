package sped.negocio.BDL.SFBean;

import java.util.List;

import javax.annotation.Resource;

import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import javax.persistence.Query;

import sped.negocio.BDL.IL.BDL_C_SFProfesorLocal;
import sped.negocio.BDL.IR.BDL_C_SFProfesorRemote;
import sped.negocio.entidades.admin.AreaAcademica;
import sped.negocio.entidades.admin.Profesor;
import sped.negocio.entidades.beans.BeanCriterio;
import sped.negocio.entidades.eval.Criterio;
import sped.negocio.entidades.eval.Evaluacion;

@Stateless(name = "BDL_C_SFProfesor", mappedName = "map-BDL_C_SFProfesor")
public class BDL_C_SFProfesorBean implements BDL_C_SFProfesorRemote, 
                                             BDL_C_SFProfesorLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SPED_NEGOCIO")
    private EntityManager em;

    public BDL_C_SFProfesorBean() {
    }

    /** <code>select o from Profesor o</code> */
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<Profesor> getProfesorFindAll() {
        return em.createNamedQuery("Profesor.findAll", Profesor.class).getResultList();
    }
    
    public Profesor getProfesorBydni(String dni) {
        try{
            String ejbQl = " SELECT ma " +
                           " FROM Profesor ma " +
                           " WHERE ma.dniProfesor='"+dni+"'";   
                Profesor eva = (Profesor)em.createQuery(ejbQl).getSingleResult();           
                return eva;         
        }catch(Exception e){
            e.printStackTrace();  
            return null;
        }   
        }
    
    public List<Profesor> findProfesorPorSede_ByOrden(String nidSede, String nidArea, String nidCurso, String nidNivel, String nidGrado) {
        try {
            String ejbQl =    " SELECT distinct pro from Profesor pro, Sede sed, Aula au, Main ma, Curso cu, AreaAcademica aca, Nivel niv, Grado gra"+
                              " where pro.dniProfesor=ma.profesor.dniProfesor "+ 
                              " and ma.aula.nidAula=au.nidAula "+ 
                              " and au.sede.nidSede=sed.nidSede "+
                              " and ma.curso.nidCurso=cu.nidCurso" +
                              " and cu.areaAcademica.nidAreaAcademica=aca.nidAreaAcademica" +
                              " and au.gradoNivel.grado.nidGrado=gra.nidGrado" +
                              " and au.gradoNivel.nivel.nidNivel=niv.nidNivel" ;
            if (nidSede != null) {               
                    ejbQl = ejbQl.concat(" and sed.nidSede="+nidSede);               
            }
            if (nidArea != null) {               
                    ejbQl = ejbQl.concat(" and aca.nidAreaAcademica="+nidArea);               
            }
            if(nidCurso != null){
                    ejbQl = ejbQl.concat(" and cu.nidCurso="+nidCurso);  
            }
            if(nidNivel != null){
                    ejbQl = ejbQl.concat(" and niv.nidNivel="+nidNivel);  
            }
            if(nidGrado != null){
                    ejbQl = ejbQl.concat(" and gra.nidGrado="+nidGrado);  
            }
            
            ejbQl = ejbQl.concat(" ORDER by pro.apellidos");
            
            List<Profesor> lstMain = em.createQuery(ejbQl).getResultList();
            return lstMain;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }}

}
