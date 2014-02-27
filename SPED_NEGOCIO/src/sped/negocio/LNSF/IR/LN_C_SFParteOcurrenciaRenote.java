package sped.negocio.LNSF.IR;

import java.util.Date;
import java.util.List;

import javax.ejb.Remote;

import sped.negocio.entidades.beans.BeanParteOcurrencia;

@Remote
public interface LN_C_SFParteOcurrenciaRenote {
    List<BeanParteOcurrencia> getListaPartesOcurrencia_LN(Date fechaMin,
                                                          Date fechaMax, 
                                                          Integer nidProblema,
                                                          String nombreProfesor, 
                                                          Integer nidSede,
                                                          Integer nidUsuario);
}
