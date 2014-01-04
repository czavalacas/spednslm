package sped.negocio.LNSF.IL;

import javax.ejb.Local;

@Local
public interface LN_T_SFUsuarioLocal {
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
