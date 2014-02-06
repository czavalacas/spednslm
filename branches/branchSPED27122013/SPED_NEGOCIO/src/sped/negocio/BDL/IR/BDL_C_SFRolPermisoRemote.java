package sped.negocio.BDL.IR;

import java.util.List;

import javax.ejb.Remote;

import sped.negocio.entidades.sist.Rol;
import sped.negocio.entidades.sist.RolPermiso;

@Remote
public interface BDL_C_SFRolPermisoRemote {
    List<RolPermiso> getPermisosByRolBDL(Rol rol);
}
