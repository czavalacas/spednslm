package sped.negocio.BDL.IL;

import java.util.List;

import javax.ejb.Local;

import sped.negocio.entidades.beans.BeanPermiso;
import sped.negocio.entidades.sist.Permiso;

@Local
public interface BDL_C_SFPermisoLocal {
    
    List<Permiso> getByNidPermiso(int nidPermiso);
    int getNiveles();
    List<Permiso> getHijosByPadre(int nidPadre,
                                  int nidUsuario,
                                  int nidRol);
    List<Permiso> getHijosByPadre_WS(int nidUsuario,
                                     int nidRol);
    List<Permiso> getHijosByPadreGP(int nidPadre,
                                    int nidRol);
    int cantidadPermisos(int nidUsuario,
                         int nidRol,
                         String isWS);
}
