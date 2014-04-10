package sped.negocio.BDL.IR;

import java.util.List;

import javax.ejb.Remote;

import sped.negocio.entidades.admin.Curso;
import sped.negocio.entidades.admin.Main;
import sped.negocio.entidades.admin.Profesor;
import sped.negocio.entidades.beans.BeanMain;
import sped.negocio.entidades.beans.BeanMainWS;

@Remote
public interface BDL_C_SFMainRemote {
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
}
