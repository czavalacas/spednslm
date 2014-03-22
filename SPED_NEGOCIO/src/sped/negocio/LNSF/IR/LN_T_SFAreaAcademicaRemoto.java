package sped.negocio.LNSF.IR;

import java.util.List;

import javax.ejb.Remote;

import sped.negocio.entidades.beans.BeanAreaAcademica;

@Remote
public interface LN_T_SFAreaAcademicaRemoto {
    String grabarAreasNuevas(List<BeanAreaAcademica> listaAreas);
    String grabarArea(BeanAreaAcademica areas);
}
