package sped.negocio.LNSF.IR;

import java.util.List;

import javax.ejb.Remote;

import sped.negocio.entidades.beans.BeanAula;

@Remote
public interface LN_C_SFAulaRemote {
    int getAulaByDescripcion_LN(int nidSede, 
                                int nidNivel, 
                                String descripcion);
    List<BeanAula> getAreaAulaLN();
}
