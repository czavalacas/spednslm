package sped.negocio.LNSF.IR;

import javax.ejb.Remote;

@Remote
public interface LN_T_SFUsuarioRemote {
    void gestionUsuarioLN(int tipoEvento,
                                     String nombre,
                                     String apellido,
                                     String dni,
                                     int nidRol,
                                     int nidAreaA,
                                     String usuario,
                                     String clave,
                                     int idUsuario);
}
