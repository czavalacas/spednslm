package sped.negocio.BDL.IR;

import java.util.Date;
import java.util.List;

import javax.ejb.Remote;

import sped.negocio.entidades.admin.ParteOcurrencia;
import sped.negocio.entidades.beans.BeanParteOcurrencia;
import sped.negocio.entidades.beans.BeanProblemaProfesor;

@Remote
public interface BDL_C_SFParteOcurrenciaRemote {
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
