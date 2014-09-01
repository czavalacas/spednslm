package sped.negocio.LNSF.IL;

import java.util.Date;

import javax.ejb.Local;

@Local
public interface LN_C_SFUsuarioCalendarioLocal {
    int getCantidadDiasLaborablesByUsuario(int nidUsuario, Date fecMin,Date fecMax);
}
