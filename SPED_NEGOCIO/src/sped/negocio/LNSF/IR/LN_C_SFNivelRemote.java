package sped.negocio.LNSF.IR;

import java.util.List;

import javax.ejb.Remote;

import sped.negocio.entidades.beans.BeanNivel;

@Remote
public interface LN_C_SFNivelRemote {
    List<BeanNivel> getNivelLN();
    List<BeanNivel> findNivelPorAreaAcademica(Integer nidAreaAcademica, String dia);
    BeanNivel findConstrainByIdLN(int id);
    List<BeanNivel> getNivelLNPorSede_ByOrden(Object nidSede, Object nidArea, Object nidCurso);
}
