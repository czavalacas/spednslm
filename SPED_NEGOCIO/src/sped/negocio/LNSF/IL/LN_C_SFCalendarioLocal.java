package sped.negocio.LNSF.IL;

import java.util.List;

import javax.ejb.Local;

import sped.negocio.entidades.beans.BeanCalendario;

@Local
public interface LN_C_SFCalendarioLocal {
    List<BeanCalendario> getCalendarioActivo_LN(int mesNumero);
    List<BeanCalendario> getCalendarioActivoByUsuario_LN(int mesNumero,int nidUsuario);
}
