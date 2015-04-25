package sped.negocio.BDL.IL;

import java.sql.Time;

import java.util.List;

import javax.ejb.Local;

import sped.negocio.entidades.admin.Curso;
import sped.negocio.entidades.admin.Main;
import sped.negocio.entidades.admin.Profesor;
import sped.negocio.entidades.beans.BeanMain;
import sped.negocio.entidades.beans.BeanMainWS;

@Local
public interface BDL_C_SFMainLocal {
    List<Main> getMainFindAll();
    List<Main> findHorariosByAttributes(BeanMain beanMain);
    List<Profesor> findProfesoresPorAreaAcademica(Integer nidAreaAcademica, String dia);
    /**
      * Metodo que no utiliza mapper sino mapea defrente al bean desde el mismo query, este metodo busca en la entidad 
      * Main, se usa para el WS, movil, para poder buscar profesores y registrarles un PARTE DE OCURRENCIA
      * @author dfloresgonz
      * @since 24.02.2014
      * @param nidSede
      * @param profesor
      * @param curso
      * @param aula
      * @return List<BeanMainWS>
      */
     List<BeanMainWS> getMainByAttr_BDL_WS(Integer nidSede,
                                           String profesor,
                                           String curso,
                                           String aula);
    List<Main> getLstMainByAttr_BDL(BeanMain beanMain);
    List<Main> getHorariosPorDocente(String dniDocente);
    Main getMainByAtrubutes(String nidAula, String nidCurso, String dniProfesor);
    Main findMainById(int id);
    List<Main> countCruceLecionByProfesor(String dniProfesor, 
                                          int dia,
                                          Time inicio, 
                                          Time fin,
                                          int nidMain);
    int findMainBySedeYNivel(int nidSede, 
                             int nidNivel);
    List<Main> getlstMainByNidLeccion(int nidLeccion);
    int countMainByNidsEstado(String nidCurso, String nidAula, String dni);
}