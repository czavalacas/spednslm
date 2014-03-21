package sped.negocio.BDL.IR;

import javax.ejb.Remote;

import sped.negocio.entidades.beans.BeanAula;

@Remote
public interface BDL_C_SFAulaRemote {
    int getAulaByDescripcion(BeanAula beanAula);
}
