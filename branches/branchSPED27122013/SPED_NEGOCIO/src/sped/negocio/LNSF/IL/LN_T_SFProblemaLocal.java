package sped.negocio.LNSF.IL;

import javax.ejb.Local;

@Local
public interface LN_T_SFProblemaLocal {
    String gestionarProblema(int evento, 
                             int nidProblema,
                             String descripcion);
}
