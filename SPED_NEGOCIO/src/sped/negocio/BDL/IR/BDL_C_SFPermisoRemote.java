package sped.negocio.BDL.IR;

import java.util.List;

import javax.ejb.Remote;

import sped.negocio.entidades.beans.BeanPermiso;
import sped.negocio.entidades.sist.Permiso;

@Remote
public interface BDL_C_SFPermisoRemote {
    
    List<Permiso> getByNidPermiso(int nidPermiso);
    int getNiveles();
    List<Permiso> getHijosByPadre(int nidPadre,
                                  int nidUsuario,
                                  int nidRol);
    List<Permiso> getHijosByPadre_WS(int nidUsuario,
                                     int nidRol);
    List<Permiso> getHijosByPadreGP(int nidPadre,
                                    int nidRol);
}
