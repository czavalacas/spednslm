package sped.negocio.BDL.IL;

import java.util.Date;

import java.util.List;

import javax.ejb.Local;

import sped.negocio.entidades.admin.UsuarioCalendario;
import sped.negocio.entidades.admin.UsuarioCalendarioPK;
import sped.negocio.entidades.beans.BeanComboString;

@Local
public interface BDL_C_SFUsuarioCalendarioLocal {
    UsuarioCalendario findUsuarioCalendarioById(Date nidFecha,int nidUsuario);
    int getCantidadDiasLaborablesByUsuario(int nidUsuario, Date fecMin,Date fecMax);
}
