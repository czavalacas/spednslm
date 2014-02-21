package sped.negocio.BDL.IR;

import java.util.List;

import javax.ejb.Remote;

import sped.negocio.entidades.admin.Curso;
import sped.negocio.entidades.admin.Sede;

@Remote
public interface BDL_C_SFSedeRemote {
    List<Sede> getSedeFindAll();
    List<Sede> findSedePorAreaAcademica(Integer nidAreaAcademica, String dia);
    Sede findSedeById(int id);
}
