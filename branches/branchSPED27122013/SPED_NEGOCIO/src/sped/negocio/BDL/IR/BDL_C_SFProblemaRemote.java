package sped.negocio.BDL.IR;

import java.util.List;

import javax.ejb.Remote;

import sped.negocio.entidades.admin.Problema;

@Remote
public interface BDL_C_SFProblemaRemote {
    List<Problema> getProblemaFindAll();
    String getDescripcionProblemaById(int idProblema);
    int getNidProblemaByDescripcion(String descripcion);
    Problema findConstrainById(int id);
}
