package sped.negocio.BDL.IL;

import java.util.List;

import javax.ejb.Local;

import sped.negocio.entidades.sist.Rol;

@Local
public interface BDL_C_SFRolLocal {
    List<Rol> getRolFindAll();
    Rol findConstrainById(int id);
}
