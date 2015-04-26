package sped.negocio.BDL.IR;

import javax.ejb.Remote;
import sped.negocio.entidades.admin.Constraint;

@Remote
public interface BDL_T_SFUtilsRemote {
    void cambiarALeidoNotificacion(String tabla,Integer nidUsuario);
    void actualizarConstraint(String newValor,String tabla, String campo, String oldValor);
    Constraint mergeConstraint(Constraint constraint);
    void desactivarMainByAula(String nidAula);
}
