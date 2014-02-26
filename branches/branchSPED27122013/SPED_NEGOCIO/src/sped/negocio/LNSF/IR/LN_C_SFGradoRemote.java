package sped.negocio.LNSF.IR;

import java.util.List;

import javax.ejb.Remote;

import sped.negocio.entidades.beans.BeanGrado;

@Remote
public interface LN_C_SFGradoRemote {
    List<BeanGrado> findGradoPorAreaAcademica(Integer nidAreaAcademica, String dia);
    List<BeanGrado> getGradoLN();
    BeanGrado findConstrainByIdLN(int id);
    List<BeanGrado> getGradoLN_PorNivelByOrden(String nidNivel);
}
