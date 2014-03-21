package sped.negocio.LNSF.IR;

import javax.ejb.Remote;

@Remote
public interface LN_C_SFAulaRemote {
    int getAulaByDescripcion_LN(int nidSede, 
                                int nidNivel, 
                                String descripcion);
}
