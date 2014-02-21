package sped.negocio.LNSF.IL;

import java.util.List;

import javax.ejb.Local;

import sped.negocio.entidades.beans.BeanAreaAcademica;

@Local
public interface LN_C_SFAreaAcademicaLocal {
    List<BeanAreaAcademica> getAreaAcademicaLN();
    BeanAreaAcademica findConstrainByIdLN(int id);
}