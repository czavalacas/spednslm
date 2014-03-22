package sped.negocio.LNSF.IR;

import java.util.List;

import javax.ejb.Remote;

import sped.negocio.entidades.beans.BeanAula;

@Remote
public interface LN_T_SFAulaRemoto {
    String grabarAulasNuevas(List<BeanAula> listaAulas);
    String grabarAula(BeanAula aulaNueva);
}
