package sped.negocio.BDL.IL;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import sped.negocio.entidades.admin.ParteOcurrencia;
import sped.negocio.entidades.beans.BeanParteOcurrencia;
import sped.negocio.entidades.beans.BeanProblemaProfesor;

@Local
public interface BDL_C_SFParteOcurrenciaLocal {
    List<ParteOcurrencia> getParteOcurrenciaFindAll();
    List<BeanParteOcurrencia> getListaPartesOcurrencia_BDL(Date fechaMin,
                                                           Date fechaMax,
                                                           Integer nidProblema,
                                                           String nombreProfesor,
                                                           Integer nidSede,
                                                           Integer nidUsuario);
    List<BeanProblemaProfesor> getListaProblemas_ByProfesor_BDL(Date fechaMin,
                                                                Date fechaMax,
                                                                String dniProfesor,
                                                                Integer nidSede,
                                                                Integer nidUsuario);
}
