package sped.negocio.BDL.IL;

import java.util.List;

import javax.ejb.Local;

import sped.negocio.entidades.admin.Curso;
import sped.negocio.entidades.admin.Sede;

@Local
public interface BDL_C_SFSedeLocal {
    List<Sede> getSedeFindAll();
    List<Sede> findSedePorAreaAcademica(Integer nidAreaAcademica, String dia);
    Sede findSedeById(int id);
}
