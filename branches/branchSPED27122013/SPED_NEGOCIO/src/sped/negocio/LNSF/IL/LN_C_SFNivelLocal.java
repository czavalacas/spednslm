package sped.negocio.LNSF.IL;

import java.util.List;

import javax.ejb.Local;

import sped.negocio.entidades.beans.BeanNivel;

@Local
public interface LN_C_SFNivelLocal {
    List<BeanNivel> getNivelLN();
}
