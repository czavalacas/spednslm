package sped.negocio.LNSF.SFBean;

import javax.annotation.Resource;

import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import sped.negocio.BDL.IL.BDL_C_SFImagenLocal;
import sped.negocio.BDL.IL.BDL_T_SFImagenLocal;
import sped.negocio.LNSF.IL.LN_T_SFImagenLocal;
import sped.negocio.LNSF.IR.LN_T_SFImagenRemote;
import sped.negocio.Utils.Utiles;
import sped.negocio.entidades.sist.Imagen;

@Stateless(name = "LN_T_SFImagen", mappedName = "mapLN_T_SFImagen")
public class LN_T_SFImagenBean implements LN_T_SFImagenRemote, 
                                          LN_T_SFImagenLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SPED_NEGOCIO")
    private EntityManager em;
    @EJB
    private BDL_C_SFImagenLocal bdL_C_SFImagenLocal;
    @EJB
    private BDL_T_SFImagenLocal bdL_T_SFImagenLocal;

    public LN_T_SFImagenBean() {
    }
    
    public void guardarImagen(String rutaImg){
        if(bdL_C_SFImagenLocal.countImagen() > 0){
            Imagen img = bdL_C_SFImagenLocal.findConstrainById(1);
            img.setFoto(Utiles.Imagen(rutaImg));
            bdL_T_SFImagenLocal.mergeImagen(img);
        }else{
            Imagen img = new Imagen();
            img.setIdImagen(1);
            img.setDescripcion("imgdefecto");
            img.setFoto(Utiles.Imagen(rutaImg));
            bdL_T_SFImagenLocal.persistImagen(img);
        }
        
    }
}
