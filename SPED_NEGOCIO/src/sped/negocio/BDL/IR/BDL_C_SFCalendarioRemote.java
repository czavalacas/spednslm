package sped.negocio.BDL.IR;

import java.util.Date;
import java.util.List;

import javax.ejb.Remote;

import sped.negocio.entidades.beans.BeanCalendario;
import sped.negocio.entidades.sist.Calendario;

@Remote
public interface BDL_C_SFCalendarioRemote {
    List<BeanCalendario> getCalendarioActivo_BDL(int mesNumero);
    Calendario findCalendarioById(Date id);
    List<Object[]> getCalendarioActivoByUsuario_BDL(int mesNumero,int nidUsuario);
}
