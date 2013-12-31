package sped.negocio.LNSF.IR;

import java.util.List;

import javax.ejb.Remote;

import sped.negocio.entidades.beans.BeanMain;

@Remote
public interface LN_C_SFMainRemote {
    List<BeanMain> llenarHorario(BeanMain beanMain);
}
