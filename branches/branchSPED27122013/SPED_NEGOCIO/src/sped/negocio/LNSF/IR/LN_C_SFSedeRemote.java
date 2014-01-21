package sped.negocio.LNSF.IR;

import java.util.List;

import javax.ejb.Remote;

import sped.negocio.entidades.beans.BeanSede;

@Remote
public interface LN_C_SFSedeRemote {
    List<BeanSede> getSedeLN();
    List<BeanSede> findSedePorAreaAcademica(Integer nidAreaAcademica, String dia);
}
