package sped.negocio.BDL.SFBean;

import javax.annotation.Resource;

import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import sped.negocio.BDL.IL.BDL_C_SFImagenLocal;
import sped.negocio.BDL.IR.BDL_C_SFImagenRemote;
import sped.negocio.entidades.sist.Imagen;

@Stateless(name = "BDL_C_SFImagen", mappedName = "mapBDL_C_SFImagen")
public class BDL_C_SFImagenBean implements BDL_C_SFImagenRemote, 
                                           BDL_C_SFImagenLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SPED_NEGOCIO")
    private EntityManager em;

    public BDL_C_SFImagenBean() {
    }
    
    public int countImagen(){
        String ejbQL = "SELECT count(i) FROM Imagen i ";
        Object object = em.createQuery(ejbQL).getSingleResult();
        int cont = -1;
        if(object != null){
            cont = Integer.parseInt(object.toString());
        }
        return cont;
    }
    
    public Imagen findConstrainById(int id){
        try{
            Imagen instance = em.find(Imagen.class, id);
            return instance;
        }catch(RuntimeException re){
            throw re;
        }
    }
}
