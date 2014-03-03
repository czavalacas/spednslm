package sped.negocio.LNSF.IL;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import sped.negocio.entidades.beans.BeanParteOcurrencia;
import sped.negocio.entidades.beans.BeanPie;

@Local
public interface LN_C_SFParteOcurrenciaLocal {
    List<BeanParteOcurrencia> getListaPartesOcurrencia_LN(Date fechaMin,
                                                          Date fechaMax, 
                                                          Integer nidProblema,
                                                          String nombreProfesor, 
                                                          Integer nidSede,
                                                          Integer nidUsuario);
    List<BeanPie> getPiePO_ByProfesor_LN_WS(Date fechaMin,
                                            Date fechaMax,
                                            String dniProfesor,
                                            Integer nidSede,
                                            Integer nidUsuario);
}
