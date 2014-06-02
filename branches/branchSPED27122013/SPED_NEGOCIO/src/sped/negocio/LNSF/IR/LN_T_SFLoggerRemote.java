package sped.negocio.LNSF.IR;

import javax.ejb.Remote;

@Remote
public interface LN_T_SFLoggerRemote {
    void registrarLogErroresSistema(int nidLogeo,
                                    String tipo,
                                    String clase_java,
                                    String metodo_java,
                                    String comentario, 
                                    String stackTrace);
    void registrarLogErroresSistema_nidEvento(int nidLogeo,
                                              String tipo,
                                              String clase_java,
                                              String metodo_java,
                                              String comentario, 
                                              String stackTrace,
                                              int nidEvento);
}