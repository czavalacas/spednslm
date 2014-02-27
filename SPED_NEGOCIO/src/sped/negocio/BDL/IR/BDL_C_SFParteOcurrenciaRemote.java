package sped.negocio.BDL.IR;

import java.util.Date;
import java.util.List;

import javax.ejb.Remote;

import sped.negocio.entidades.admin.ParteOcurrencia;
import sped.negocio.entidades.beans.BeanParteOcurrencia;

@Remote
public interface BDL_C_SFParteOcurrenciaRemote {
    List<ParteOcurrencia> getParteOcurrenciaFindAll();
    List<BeanParteOcurrencia> getListaPartesOcurrencia_BDL(Date fechaMin,
                                                           Date fechaMax,
                                                           Integer nidProblema,
                                                           String nombreProfesor,
                                                           Integer nidSede,
                                                           Integer nidUsuario);
}
