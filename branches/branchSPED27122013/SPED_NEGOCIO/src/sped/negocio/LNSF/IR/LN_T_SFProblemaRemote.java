package sped.negocio.LNSF.IR;

import javax.ejb.Remote;

@Remote
public interface LN_T_SFProblemaRemote {
    String gestionarProblema(int evento, 
                             int nidProblema,
                             String descripcion);
}
