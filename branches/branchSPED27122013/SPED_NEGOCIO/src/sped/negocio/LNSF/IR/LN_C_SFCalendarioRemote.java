package sped.negocio.LNSF.IR;

import java.util.List;

import javax.ejb.Remote;

import sped.negocio.entidades.beans.BeanCalendario;

@Remote
public interface LN_C_SFCalendarioRemote {
    List<BeanCalendario> getCalendarioActivo_LN(int mesNumero);
    List<BeanCalendario> getCalendarioActivoByUsuario_LN(int mesNumero,int nidUsuario);
}
