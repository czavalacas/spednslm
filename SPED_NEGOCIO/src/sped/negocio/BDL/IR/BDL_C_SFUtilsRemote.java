package sped.negocio.BDL.IR;

import java.util.List;

import javax.ejb.Remote;

import sped.negocio.entidades.admin.Constraint;
import sped.negocio.entidades.beans.BeanConstraint;

@Remote
public interface BDL_C_SFUtilsRemote {
    
    BeanConstraint getCatalogoConstraints(String nombreCampo, 
                                          String nombreTabla, 
                                          String valorCampo);
    List<Constraint> getListaConstraintsBDL(String nombreCampo, 
                                         String nombreTabla);
    int findCountByProperty(String atributoDesc, 
                            Object atributoValor, 
                            String entidad, 
                            boolean changeCase,
                            boolean isUpdate);
}

