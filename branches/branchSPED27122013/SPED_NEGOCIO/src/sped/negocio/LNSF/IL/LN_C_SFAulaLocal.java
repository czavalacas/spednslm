package sped.negocio.LNSF.IL;

import java.util.List;

import javax.ejb.Local;

import sped.negocio.entidades.beans.BeanAula;

@Local
public interface LN_C_SFAulaLocal {
    int getAulaByDescripcion_LN(int nidSede, 
                                int nidNivel, 
                                String descripcion);
    List<BeanAula> getAreaAulaLN();
}
