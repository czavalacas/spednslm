package sped.negocio.LNSF.IL;

import java.util.List;

import javax.ejb.Local;

import sped.negocio.entidades.beans.BeanProfesor;

@Local
public interface LN_T_SFUsuarioLocal {
    void gestionUsuarioLN(int tipoEvento,
                          String nombres,
                          String dni,
                          String correo,
                          int nidRol,
                          int nidAreaA,
                          String usuario,
                          int idUsuario,
                          String rutaImagenes,
                          String rutaImg,
                          int nidSede,
                          boolean isSupervisor);
    void configuracionCuentaUsuario(int nidUsuario,
                                    String clave,
                                    String correo,
                                    String rutaImg);
    void cambiarPrimeraClave(int nidUsuario,
                             String clave);
    String cambiarEstadoUsuarioProfesores(List<BeanProfesor> listprofesores);
}
