package sped.negocio.LNSF.IR;

import java.util.List;

import javax.ejb.Remote;

import sped.negocio.entidades.beans.BeanComboString;
import sped.negocio.entidades.beans.BeanMain;
import sped.negocio.entidades.beans.BeanMainWS;
import sped.negocio.entidades.beans.BeanProfesor;

@Remote
public interface LN_C_SFMainRemote {
    List<BeanMain> llenarHorario(BeanMain beanMain);
    List<BeanComboString> findProfesoresPorAreaAcademica_LN(Integer nidAreaAcademica, String dia);
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
    List<BeanMainWS> getMainByAttr_LN_WS(Integer nidSede,
                                         String profesor,
                                         String curso,
                                         String aula);
}
