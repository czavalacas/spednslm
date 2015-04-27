package sped.negocio.BDL.IL;

import javax.ejb.Local;
import sped.negocio.entidades.admin.Constraint;

@Local
public interface BDL_T_SFUtilsLocal {
    void cambiarALeidoNotificacion(String tabla,Integer nidUsuario);
    void actualizarConstraint(String newValor,String tabla, String campo, String oldValor);
    Constraint mergeConstraint(Constraint constraint);
    void desactivarMainByAula(String nidAula);
    void desactivarMainByCurso(String nidCurso);
}
