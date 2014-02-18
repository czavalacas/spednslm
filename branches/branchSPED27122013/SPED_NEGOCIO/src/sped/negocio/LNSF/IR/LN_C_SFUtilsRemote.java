package sped.negocio.LNSF.IR;

import java.util.List;

import javax.ejb.Remote;

import sped.negocio.entidades.beans.BeanCombo;
import sped.negocio.entidades.beans.BeanConstraint;

@Remote
public interface LN_C_SFUtilsRemote {
    List<BeanConstraint> getListaConstraintsLN(String nombreCampo,
                                               String nombreTabla);
    List<BeanCombo> getPlanificadores_LN_WS();
    List<BeanCombo> getEvaluadores_LN_WS();
}
