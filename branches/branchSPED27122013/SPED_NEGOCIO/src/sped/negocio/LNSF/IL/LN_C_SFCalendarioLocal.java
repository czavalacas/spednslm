package sped.negocio.LNSF.IL;

import java.util.List;

import javax.ejb.Local;

import sped.negocio.entidades.beans.BeanCalendario;
import sped.negocio.entidades.beans.BeanComboInteger;
import sped.negocio.entidades.beans.BeanComboString;
import sped.negocio.entidades.beans.BeanComboStringCalend;

@Local
public interface LN_C_SFCalendarioLocal {
    List<BeanCalendario> getCalendarioActivo_LN(int mesNumero, int year);
    List<BeanCalendario> getCalendarioActivoByUsuario_LN(int mesNumero,int nidUsuario, int year);
    List<BeanComboStringCalend> getYearsCalendario();
}