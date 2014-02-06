package sped.negocio.BDL.IL;

import java.util.List;

import javax.ejb.Local;

import sped.negocio.entidades.sist.Rol;
import sped.negocio.entidades.sist.RolPermiso;

@Local
public interface BDL_C_SFRolPermisoLocal {
    List<RolPermiso> getPermisosByRolBDL(Rol rol);
}
