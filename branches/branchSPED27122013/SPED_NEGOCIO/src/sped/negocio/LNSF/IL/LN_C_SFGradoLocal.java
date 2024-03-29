package sped.negocio.LNSF.IL;

import java.util.List;

import javax.ejb.Local;

import sped.negocio.entidades.beans.BeanCombo;
import sped.negocio.entidades.beans.BeanGrado;

@Local
public interface LN_C_SFGradoLocal {
    List<BeanCombo> findGradoPorAreaAcademica(Integer nidAreaAcademica, String dia);
    List<BeanGrado> getGradoLN();
    BeanGrado findConstrainByIdLN(int id);
    List<BeanCombo> getGradoLN_PorNivelByOrden(String nidNivel);
}
