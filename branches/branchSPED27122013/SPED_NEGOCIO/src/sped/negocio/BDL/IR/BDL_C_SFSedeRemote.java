package sped.negocio.BDL.IR;

import java.util.List;

import javax.ejb.Remote;

import sped.negocio.entidades.admin.Sede;

@Remote
public interface BDL_C_SFSedeRemote {
    List<Sede> getSedeFindAll();
}
