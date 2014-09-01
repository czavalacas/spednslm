package sped.negocio.LNSF.IR;

import java.util.Date;

import javax.ejb.Remote;

@Remote
public interface LN_T_SFCalendarioRemote {
    String registrarDiaNoLaborable(Date nidFecha,String descripcionDia);
    String registrarDiaLaborable(Date nidFecha);
}
