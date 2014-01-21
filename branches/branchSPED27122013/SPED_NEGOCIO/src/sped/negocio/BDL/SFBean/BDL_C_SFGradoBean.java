package sped.negocio.BDL.SFBean;

import java.util.List;

import javax.annotation.Resource;

import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import sped.negocio.BDL.IL.BDL_C_SFGradoLocal;
import sped.negocio.BDL.IR.BDL_C_SFGradoRemote;
import sped.negocio.entidades.admin.Grado;
import sped.negocio.entidades.admin.Sede;

@Stateless(name = "BDL_C_SFGrado", mappedName = "SPED_APP-SPED_NEGOCIO-BDL_C_SFGrado")
public class BDL_C_SFGradoBean implements BDL_C_SFGradoRemote, 
                                          BDL_C_SFGradoLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SPED_NEGOCIO")
    private EntityManager em;

    public BDL_C_SFGradoBean() {
    }

    /** <code>select o from Grado o</code> */
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<Grado> getGradoFindAll() {
        return em.createNamedQuery("Grado.findAll", Grado.class).getResultList();
    }
    
    public List<Grado> findGradpPorAreaAcademica(Integer nidAreaAcademica, String dia) {
        try {
            String ejbQl =    "SELECT distinct grad FROM Main ma, " +
                              " Curso cur , " +
                              " Profesor prof," +
                              " Grado grad," +
                              " AreaAcademica ac" +
                              " WHERE ma.curso.nidCurso=cur.nidCurso " +
                              " and cur.areaAcademica.nidAreaAcademica=ac.nidAreaAcademica " +
                              " and prof.dniProfesor=ma.profesor.dniProfesor "+
                              " and ma.aula.gradoNivel.grado.nidGrado=grad.nidGrado";

            if (nidAreaAcademica != null) {
                if (nidAreaAcademica != 0) {
                    ejbQl = ejbQl.concat(" and ac.nidAreaAcademica=" + nidAreaAcademica);
                }
            }

            if (dia != null) {
                ejbQl = ejbQl.concat(" and ma.dia='" + dia + "'");
            }

            List<Grado> lstMain = em.createQuery(ejbQl).getResultList();
            return lstMain;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }}
}
