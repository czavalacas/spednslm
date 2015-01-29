package sped.negocio.LNSF.IL;

import java.util.Date;

import java.util.List;

import javax.ejb.Local;

import sped.negocio.entidades.beans.BeanComboString;

@Local
public interface LN_C_SFUsuarioCalendarioLocal {
    int getCantidadDiasLaborablesByUsuario(int nidUsuario, Date fecMin,Date fecMax);
}
