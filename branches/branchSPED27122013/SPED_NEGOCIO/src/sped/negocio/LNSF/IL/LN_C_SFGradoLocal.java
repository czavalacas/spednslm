package sped.negocio.LNSF.IL;

import java.util.List;

import javax.ejb.Local;

import sped.negocio.entidades.beans.BeanGrado;

@Local
public interface LN_C_SFGradoLocal {
    List<BeanGrado> findGradoPorAreaAcademica(Integer nidAreaAcademica, String dia);
}
