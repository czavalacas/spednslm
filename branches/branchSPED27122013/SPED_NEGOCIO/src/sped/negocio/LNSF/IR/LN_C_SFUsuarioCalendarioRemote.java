package sped.negocio.LNSF.IR;

import java.util.Date;

import javax.ejb.Remote;

@Remote
public interface LN_C_SFUsuarioCalendarioRemote {
    int getCantidadDiasLaborablesByUsuario(int nidUsuario, Date fecMin,Date fecMax);
}
