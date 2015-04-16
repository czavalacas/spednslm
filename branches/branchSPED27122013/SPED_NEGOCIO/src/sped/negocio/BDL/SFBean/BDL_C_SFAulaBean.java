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

import sped.negocio.BDL.IL.BDL_C_SFAulaLocal;
import sped.negocio.BDL.IR.BDL_C_SFAulaRemote;
import sped.negocio.entidades.admin.AreaAcademica;
import sped.negocio.entidades.admin.Aula;
import sped.negocio.entidades.admin.Curso;
import sped.negocio.entidades.beans.BeanAula;

@Stateless(name = "BDL_C_SFAula", mappedName = "map-BDL_C_SFAula")
public class BDL_C_SFAulaBean implements BDL_C_SFAulaRemote, 
                                         BDL_C_SFAulaLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SPED_NEGOCIO")
    private EntityManager em;

    public BDL_C_SFAulaBean() {
    }

    /** <code>select o from Aula o</code> */
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<Aula> getAulaFindAll() {
        return em.createNamedQuery("Aula.findAll", Aula.class).getResultList();
    }
    
    public Aula findAulaById(int nidAula){
        try{
            Aula instance = em.find(Aula.class, nidAula);
            return instance;
        }catch(RuntimeException re){
            throw re;
        }
    }
    
    /**
     * Metodo para buscar el id aula
     * @param beanAula
     * @return nidAula
     */
    public int getAulaByDescripcion(BeanAula beanAula) {
        String ejbQL = "SELECT  a.nidAula FROM Aula a " + 
                       "WHERE a.descripcionAula = :descripcion " +
                       "AND a.sede.nidSede = :nidSede ";
        if (beanAula.getNidNivel() != 0) {
            ejbQL = ejbQL.concat("AND a.gradoNivel.nivel.nidNivel = :nidNivel ");
        }
        Query query = em.createQuery(ejbQL);
        query.setParameter("descripcion", beanAula.getDescripcionAula()).setParameter("nidSede", beanAula.getNidSede());
        if (beanAula.getNidNivel() != 0) {
            query.setParameter("nidNivel", beanAula.getNidNivel());
        }
        List<Object> lstObject = query.getResultList();
        int nid = 0;
        if (lstObject.size() > 0) {
            nid = Integer.parseInt(lstObject.get(0).toString());
        }
        return nid;
    }

    public List<Aula> getAulaPorSedeNivelYGrado(String nidSede, String nidGrado, String nidNivel) {
        try {
            String ejbQl = " SELECT distinct au FROM Aula au" + 
                           " WHERE 1=1 ";
            if (nidSede != null) {
                ejbQl = ejbQl.concat(" and au.sede.nidSede=  " + nidSede);
            }
            if (nidGrado != null) {
                ejbQl = ejbQl.concat(" and au.gradoNivel.grado.nidGrado = " + nidGrado);
            }
            if (nidNivel != null) {
                ejbQl = ejbQl.concat(" and au.gradoNivel.nivel.nidNivel = " + nidNivel);
            }
            ejbQl = ejbQl.concat(" ORDER by au.descripcionAula");
            return em.createQuery(ejbQl).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Aula> getAulaPorSedeNivelProfesorYArea(String nidSede, String nidNivel, String dniProfesor,
                                                       Integer nidAreaAcademica, String nidCurso) {
        try {
            String ejbQl =
                " SELECT distinct au FROM Main ma, " + " Curso cur , " + " Profesor prof," + " Aula au" +
                " WHERE prof.flgActi = 1 And prof.dniProfesor=ma.profesor.dniProfesor  " + 
                " AND ma.aula.nidAula=au.nidAula " +
                " AND au.gradoNivel.nivel.nidNivel=" + nidNivel +
                " AND au.sede.nidSede=" + nidSede +
                " AND ma.curso.nidCurso=cur.nidCurso" + 
                " AND prof.dniProfesor = :dniProfesor" + " AND cur.nidCurso = " +nidCurso +
                " AND ma.estado=1";
            
            if (nidAreaAcademica != null) {
                if (nidAreaAcademica != 0) {
                    if (nidAreaAcademica == 12 || nidAreaAcademica == 13) { //12 = Primer Ciclo 13 = Inicial
                        ejbQl = ejbQl.concat(" and cur.areaAcademica.nidAreaAcademica=" + nidAreaAcademica);
                    } else {
                        ejbQl = ejbQl.concat(" and cur.nidAreaNativa =" + nidAreaAcademica);
                    }
                }
            }
            return em.createQuery(ejbQl).setParameter("dniProfesor", dniProfesor).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}