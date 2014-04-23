package sped.negocio.LNSF.IL;

import java.util.List;

import javax.ejb.Local;

import sped.negocio.entidades.beans.BeanRol;

@Local
public interface LN_C_SFRolLocal {
    List<BeanRol> getRolLN();
    boolean validaRolbyDescripcion(int nidRol, String descripcion);
    List<BeanRol> getListRolbyNombreLN(String descripcion);
    boolean isSubDirectorByNidUsuario_LN(Integer nidUsuario);
}
