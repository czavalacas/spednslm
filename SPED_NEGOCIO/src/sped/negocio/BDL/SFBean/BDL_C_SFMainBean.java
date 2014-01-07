package sped.negocio.BDL.SFBean;

import java.util.List;

import javax.annotation.Resource;

import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import javax.persistence.TemporalType;

import sped.negocio.BDL.IL.BDL_C_SFMainLocal;
import sped.negocio.BDL.IR.BDL_C_SFMainRemote;
import sped.negocio.entidades.admin.Main;
import sped.negocio.entidades.admin.Profesor;
import sped.negocio.entidades.beans.BeanMain;

import utils.system;

/** Clase SFBDL SFMainBean.java
 * @author czavalacas
 * @since 29.12.2013
 */
@Stateless(name = "BDL_C_SFMain", mappedName = "map-BDL_C_SFMain")
public class BDL_C_SFMainBean implements BDL_C_SFMainRemote, 
                                         BDL_C_SFMainLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SPED_NEGOCIO")
    private EntityManager em;

    public BDL_C_SFMainBean() {
    }

    public List<Main> findHorariosByAttributes(BeanMain beanMain) {
        try {
            String ejbQl =  "SELECT ma FROM Main ma, " +
                            "Curso cur , " +
                            "Profesor prof, " +
                            "Aula au, " +
                            "Sede sed, " +
                            "Nivel niv, " +
                            "Grado gra" +
                            " WHERE ma.estado=1" + 
                            " and ma.curso.nidCurso=cur.nidCurso " + 
                            " and au.nidAula=ma.aula.nidAula " +
                            " and au.sede.nidSede=sed.nidSede" +
                            " and au.gradoNivel.nivel.nidNivel=niv.nidNivel" +
                            " and au.gradoNivel.grado.nidGrado=gra.nidGrado" + 
                            " and prof.dniProfesor=ma.profesor.dniProfesor";

            if (beanMain.getCurso().getAreaAcademica() != null) {
                if (beanMain.getCurso().getAreaAcademica().getNidAreaAcademica() != 0) {
                    ejbQl =
                        ejbQl.concat(" and ma.curso.areaAcademica.nidAreaAcademica= " +
                                     beanMain.getCurso().getAreaAcademica().getNidAreaAcademica());
                }
            }
            if (beanMain.getDia() != null) {
                ejbQl = ejbQl.concat(" and ma.dia= '" + beanMain.getDia() + "'");
            }
            if (beanMain.getProfesor() != null) {
                ejbQl = ejbQl.concat(" and ma.profesor.dniProfesor= '" + beanMain.getProfesor().getDniProfesor() + "'");
            }
            if (beanMain.getCurso().getNidCurso() != null) {
                if (beanMain.getCurso().getNidCurso() != 0) {
                    ejbQl = ejbQl.concat(" and ma.curso.nidCurso=" + beanMain.getCurso().getNidCurso());
                }
            }

            List<Main> lstMain = em.createQuery(ejbQl).getResultList();
            return lstMain;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Profesor> findProfesoresPorAreaAcademica(Integer nidAreaAcademica, String dia) {
        try {
            String ejbQl =    "SELECT distinct prof FROM Main ma, " +
                              " Curso cur , " +
                              " Profesor prof," +
                              " AreaAcademica ac" +
                              " WHERE ma.curso.nidCurso=cur.nidCurso " +
                              " and cur.areaAcademica.nidAreaAcademica=ac.nidAreaAcademica " +
                              " and prof.dniProfesor=ma.profesor.dniProfesor";

            if (nidAreaAcademica != null) {
                if (nidAreaAcademica != 0) {
                    ejbQl = ejbQl.concat(" and ac.nidAreaAcademica=" + nidAreaAcademica);
                }
            }

            if (dia != null) {
                ejbQl = ejbQl.concat(" and ma.dia='" + dia + "'");
            }

            List<Profesor> lstMain = em.createQuery(ejbQl).getResultList();
            return lstMain;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    /** <code>select o from Main o</code> */
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<Main> getMainFindAll() {
        return em.createNamedQuery("Main.findAll", Main.class).getResultList();
    }
}
