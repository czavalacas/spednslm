package sped.negocio.LNSF.IR;

import java.util.List;

import javax.ejb.Remote;

import sped.negocio.entidades.beans.BeanAreaAcademica;

@Remote
public interface LN_C_SFAreaAcademicaRemote {
    List<BeanAreaAcademica> getAreaAcademicaLN();
    BeanAreaAcademica findConstrainByIdLN(int id);
    List<BeanAreaAcademica> getAreaAcademicaLNPorSede_byOrden(String nidSede);
}
