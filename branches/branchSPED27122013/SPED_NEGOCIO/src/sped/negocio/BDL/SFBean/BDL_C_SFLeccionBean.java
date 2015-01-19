package sped.negocio.BDL.SFBean;

import java.util.List;

import javax.annotation.Resource;

import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import sped.negocio.BDL.IL.BDL_C_SFLeccionLocal;
import sped.negocio.BDL.IR.BDL_C_SFLeccionRemote;
import sped.negocio.entidades.admin.Aula;
import sped.negocio.entidades.admin.Profesor;
import sped.negocio.entidades.sist.Leccion;

@Stateless(name = "BDL_C_SFLeccion", mappedName = "map-BDL_C_SFLeccion")
public class BDL_C_SFLeccionBean implements BDL_C_SFLeccionRemote, 
                                            BDL_C_SFLeccionLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SPED_NEGOCIO")
    private EntityManager em;

    public BDL_C_SFLeccionBean() {
    }
    
    public Leccion findLeccionById(int nidLeccion){
        try{
            Leccion instance = em.find(Leccion.class, nidLeccion);
            return instance;
        }catch(RuntimeException re){
            throw re;
        }
    }
    
    public List<Leccion> getLeccionesbyAula(Aula nidAula){
        try {
            String ejbQl = " SELECT distinct lc FROM Leccion lc" + 
                           " WHERE lc.estado = '1' AND lc.aula = :aula";
            return em.createQuery(ejbQl).setParameter("aula", nidAula).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }        
    }
    
    public List<Leccion> getLeccionesbyDNI(Profesor nidDni, int nidSede, int nidNivel){
        try {
            String ejbQl = " SELECT distinct lc FROM Leccion lc" + 
                           " WHERE lc.estado = '1' AND lc.profesor = :dni " +
                           " AND lc.aula.sede.nidSede = :nidSede " +
                           " AND lc.aula.gradoNivel.nivel.nidNivel = :nidNivel ";
            return em.createQuery(ejbQl).setParameter("dni", nidDni)
                                        .setParameter("nidSede", nidSede)
                                        .setParameter("nidNivel", nidNivel).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }        
    }
}
