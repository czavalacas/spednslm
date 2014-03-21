package sped.negocio.LNSF.IL;

import javax.ejb.Local;

@Local
public interface LN_C_SFAulaLocal {
    int getAulaByDescripcion_LN(int nidSede, 
                                int nidNivel, 
                                String descripcion);
}
