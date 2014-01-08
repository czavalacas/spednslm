package sped.negocio.LNSF.IL;

import java.util.List;

import javax.ejb.Local;

import sped.negocio.entidades.beans.BeanSede;

@Local
public interface LN_C_SFSedeLocal {
    List<BeanSede> getSedeLN();
}
