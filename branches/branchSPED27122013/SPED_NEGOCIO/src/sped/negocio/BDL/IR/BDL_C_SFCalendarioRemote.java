package sped.negocio.BDL.IR;

import java.util.Date;
import java.util.List;

import javax.ejb.Remote;

import sped.negocio.entidades.beans.BeanCalendario;
import sped.negocio.entidades.beans.BeanComboInteger;
import sped.negocio.entidades.beans.BeanComboString;
import sped.negocio.entidades.sist.Calendario;

@Remote
public interface BDL_C_SFCalendarioRemote {
    List<BeanCalendario> getCalendarioActivo_BDL(int mesNumero, int year);
    Calendario findCalendarioById(Date id);
    List<Object[]> getCalendarioActivoByUsuario_BDL(int mesNumero,int nidUsuario, int year);
    List<BeanComboString> getYearsCalendario();
}
