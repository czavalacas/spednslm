package sped.negocio.BDL.IL;

import javax.ejb.Local;

import sped.negocio.entidades.beans.BeanAula;

@Local
public interface BDL_C_SFAulaLocal {
    int getAulaByDescripcion(BeanAula beanAula);
}
