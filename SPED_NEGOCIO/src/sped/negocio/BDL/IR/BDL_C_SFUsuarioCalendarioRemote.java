package sped.negocio.BDL.IR;

import java.util.Date;

import javax.ejb.Remote;

import sped.negocio.entidades.admin.UsuarioCalendario;
import sped.negocio.entidades.admin.UsuarioCalendarioPK;

@Remote
public interface BDL_C_SFUsuarioCalendarioRemote {
    UsuarioCalendario findUsuarioCalendarioById(Date nidFecha,int nidUsuario);
    int getCantidadDiasLaborablesByUsuario(int nidUsuario, Date fecMin,Date fecMax);
}
