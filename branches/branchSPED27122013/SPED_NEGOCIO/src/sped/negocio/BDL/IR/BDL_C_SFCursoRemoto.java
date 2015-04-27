package sped.negocio.BDL.IR;

import java.util.List;

import javax.ejb.Remote;

import sped.negocio.entidades.admin.Curso;

@Remote
public interface BDL_C_SFCursoRemoto {
    List<Curso> getCursoFindAll();
    List<Curso> findCursosPorAreaAcademica(Integer nidAreaAcademica, String dia);
    Curso findCursoById(int id);
    List<Curso> findCursosPorAreaAcademica_ByOrden(String nidAreaAcademica, String nidSede);
    int getNidCursoByDescripcion(String descripcion);
    List<Curso> getCursosbyAreas(String nidAreaAcademica) ;
    List<Curso> getCursoPorSedeNivelProfesorYArea(String nidSede, String nidNivel, String dniProfesor, Integer nidAreaAcademica);
    List<Curso> getCursosbyAreaYNativa(String nidAreaAcademica, String nidAreaNativa);
}
