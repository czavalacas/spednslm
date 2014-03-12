package sped.negocio.LNSF.IL;

import javax.ejb.Local;

@Local
public interface LN_T_SFUsuarioLocal {
    void gestionUsuarioLN(int tipoEvento,
                          String nombres,
                          String dni,
                          String correo,
                          int nidRol,
                          int nidAreaA,
                          String usuario,
                          String clave,
                          int idUsuario,
                          String rutaImg,
                          int nidSede,
                          int nidNivel);
    void configuracionCuentaUsuario(int nidUsuario,
                                    String clave,
                                    String correo,
                                    String rutaImg);
}
