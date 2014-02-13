package sped.negocio.LNSF.IR;

import java.util.List;

import javax.ejb.Remote;

import sped.negocio.entidades.beans.BeanRol;

@Remote
public interface LN_C_SFRolRemote {
    List<BeanRol> getRolLN();
    boolean validaRolbyDescripcion(int nidRol, String descripcion);
    List<BeanRol> getListRolbyNombreLN(String descripcion);
}
