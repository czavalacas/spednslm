package sped.negocio.LNSF.IL;

import java.util.Date;

import javax.ejb.Local;

@Local
public interface LN_T_SFCalendarioLocal {
    String registrarDiaNoLaborable(Date nidFecha,String descripcionDia);
    String registrarDiaLaborable(Date nidFecha);
}
