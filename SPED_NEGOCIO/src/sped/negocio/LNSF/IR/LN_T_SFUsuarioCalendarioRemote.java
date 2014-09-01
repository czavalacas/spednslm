package sped.negocio.LNSF.IR;

import java.util.Date;

import javax.ejb.Remote;

@Remote
public interface LN_T_SFUsuarioCalendarioRemote {
    String registrarDiaNoLaborableUsuario_LN(Date nidFecha,int nidUsuario,
                                                        String descripDia,String tipo);
    String actualizarDiaNoLaborableUsuarioDescripcionTipo_LN(Date nidFecha,int nidUsuario,
                                                                        String descripDia,String tipo);
    String regresarADiaNoLaborableUsuarioCalendario_LN(Date nidFecha,int nidUsuario);
}
