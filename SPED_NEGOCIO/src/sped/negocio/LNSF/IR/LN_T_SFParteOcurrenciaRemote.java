package sped.negocio.LNSF.IR;

import javax.ejb.Remote;

@Remote
public interface LN_T_SFParteOcurrenciaRemote {
    String registrarParteOcurrencia_LN(Integer nidMain,
                                       String comentario,
                                       Integer nidProblema,
                                       Integer nidUsuario,
                                       Integer nidSede);
}
