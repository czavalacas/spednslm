package sped.negocio.BDL.IR;

import java.util.Date;

import java.util.List;

import javax.ejb.Remote;

import sped.negocio.entidades.admin.UsuarioCalendario;
import sped.negocio.entidades.admin.UsuarioCalendarioPK;
import sped.negocio.entidades.beans.BeanComboString;

@Remote
public interface BDL_C_SFUsuarioCalendarioRemote {
    UsuarioCalendario findUsuarioCalendarioById(Date nidFecha,int nidUsuario);
    int getCantidadDiasLaborablesByUsuario(int nidUsuario, Date fecMin,Date fecMax);
}
