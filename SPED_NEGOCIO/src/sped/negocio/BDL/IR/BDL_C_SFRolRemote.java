package sped.negocio.BDL.IR;

import java.util.List;

import javax.ejb.Remote;

import sped.negocio.entidades.sist.Rol;

@Remote
public interface BDL_C_SFRolRemote {
    List<Rol> getRolFindAll();
    Rol findConstrainById(int id);
    int getIdbyDescripcion(String descripcion);
}
