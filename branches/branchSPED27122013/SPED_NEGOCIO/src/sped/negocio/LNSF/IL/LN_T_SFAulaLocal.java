package sped.negocio.LNSF.IL;

import java.util.List;

import javax.ejb.Local;

import sped.negocio.entidades.beans.BeanAula;

@Local
public interface LN_T_SFAulaLocal {
    String grabarAulasNuevas(List<BeanAula> listaAulas);
    String grabarAula(BeanAula aulaNueva);
    String actualizarAula(BeanAula aulaNueva);
}
