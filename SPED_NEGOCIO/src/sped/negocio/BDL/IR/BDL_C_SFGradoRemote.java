package sped.negocio.BDL.IR;

import java.util.List;

import javax.ejb.Remote;

import sped.negocio.entidades.admin.Grado;

@Remote
public interface BDL_C_SFGradoRemote {
    List<Grado> getGradoFindAll();
}
