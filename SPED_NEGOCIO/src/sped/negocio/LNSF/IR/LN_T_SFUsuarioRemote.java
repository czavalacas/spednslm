package sped.negocio.LNSF.IR;

import java.util.List;

import javax.ejb.Remote;

import sped.negocio.entidades.beans.BeanProfesor;

@Remote
public interface LN_T_SFUsuarioRemote {
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
