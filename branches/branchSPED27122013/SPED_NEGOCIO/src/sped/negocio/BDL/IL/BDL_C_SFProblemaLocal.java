package sped.negocio.BDL.IL;

import java.util.List;

import javax.ejb.Local;

import sped.negocio.entidades.admin.Problema;

@Local
public interface BDL_C_SFProblemaLocal {
    List<Problema> getProblemaFindAll();
    String getDescripcionProblemaById(int idProblema);
    int getNidProblemaByDescripcion(String descripcion);
    Problema findConstrainById(int id);
    int countProblemaByDescripcion(String descripcion);
}
