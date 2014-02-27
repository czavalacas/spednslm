package sped.negocio.LNSF.IL;

import javax.ejb.Local;

@Local
public interface LN_T_SFParteOcurrenciaLocal {
    String registrarParteOcurrencia_LN(Integer nidMain,
                                       String comentario,
                                       Integer nidProblema,
                                       Integer nidUsuario);
}
