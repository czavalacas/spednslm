package sped.negocio.LNSF.IL;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import sped.negocio.entidades.beans.BeanParteOcurrencia;

@Local
public interface LN_C_SFParteOcurrenciaLocal {
    List<BeanParteOcurrencia> getListaPartesOcurrencia_LN(Date fechaMin,
                                                          Date fechaMax, 
                                                          Integer nidProblema,
                                                          String nombreProfesor, 
                                                          Integer nidSede,
                                                          Integer nidUsuario);
}
