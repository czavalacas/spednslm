package sped.negocio.LNSF.IL;

import javax.ejb.Local;

@Local
public interface LN_T_SFLoggerLocal {
    void registrarLogErroresSistema(int nidLogeo,
                                    String tipo,
                                    String clase_java,
                                    String metodo_java,
                                    String comentario, 
                                    String stackTrace);
}
