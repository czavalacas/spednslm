package sped.negocio.LNSF.IR;

import java.util.Date;

import java.util.List;

import javax.ejb.Remote;

import sped.negocio.entidades.beans.BeanComboString;

@Remote
public interface LN_C_SFUsuarioCalendarioRemote {
    int getCantidadDiasLaborablesByUsuario(int nidUsuario, Date fecMin,Date fecMax);
}
