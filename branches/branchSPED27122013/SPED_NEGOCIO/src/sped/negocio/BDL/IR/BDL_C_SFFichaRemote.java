package sped.negocio.BDL.IR;

import java.util.List;

import javax.ejb.Remote;

import sped.negocio.entidades.beans.BeanComboString;
import sped.negocio.entidades.eval.Ficha;

@Remote
public interface BDL_C_SFFichaRemote {
    Ficha findFichaById(int id);
    List<Ficha> getFichaFindAll();
    List<Ficha> getFichaByAttr_BDL();
    Object[] getLastestFichaVersionByAttr_BDL(int year,
                                            int mes,
                                            String tipFicha,
                                            String tipFichaCurso);
    int hayFichasActivas(String tipFicha,String tipCursoFicha);
    Ficha getFichaEvaluacion(String tipoFicha,String tipoFichaCurso);
    List<BeanComboString> getListaTiposFichaByTipoRol(String subDirector);
    Integer getNidFichaByTipoFichaCurso(String tipFichaCurso,
                                        String tipoFicha);
}
