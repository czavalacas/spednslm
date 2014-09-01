package sped.negocio.LNSF.IL;

import java.util.Date;

import javax.ejb.Local;

@Local
public interface LN_T_SFUsuarioCalendarioLocal {
    String registrarDiaNoLaborableUsuario_LN(Date nidFecha,int nidUsuario,
                                                        String descripDia,String tipo);
    String actualizarDiaNoLaborableUsuarioDescripcionTipo_LN(Date nidFecha,int nidUsuario,
                                                                        String descripDia,String tipo);
    String regresarADiaNoLaborableUsuarioCalendario_LN(Date nidFecha,int nidUsuario);
}
