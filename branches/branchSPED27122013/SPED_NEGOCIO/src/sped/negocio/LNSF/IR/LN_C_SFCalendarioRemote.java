package sped.negocio.LNSF.IR;

import java.util.List;

import javax.ejb.Remote;

import sped.negocio.entidades.beans.BeanCalendario;
import sped.negocio.entidades.beans.BeanComboInteger;
import sped.negocio.entidades.beans.BeanComboString;

@Remote
public interface LN_C_SFCalendarioRemote {
    List<BeanCalendario> getCalendarioActivo_LN(int mesNumero, int year);
    List<BeanCalendario> getCalendarioActivoByUsuario_LN(int mesNumero,int nidUsuario, int year);
    List<BeanComboString> getYearsCalendario();
}
