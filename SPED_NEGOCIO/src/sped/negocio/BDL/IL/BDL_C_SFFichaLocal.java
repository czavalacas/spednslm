package sped.negocio.BDL.IL;

import java.util.List;

import javax.ejb.Local;

import sped.negocio.entidades.eval.Ficha;

@Local
public interface BDL_C_SFFichaLocal {
    Ficha findFichaById(int id);
    List<Ficha> getFichaFindAll();
    List<Ficha> getFichaByAttr_BDL();
    Object[] getLastestFichaVersionByAttr_BDL(int year,
                                            int mes,
                                            String tipFicha,
                                            String tipFichaCurso);
    int hayFichasActivas(String tipFicha,String tipCursoFicha);
    Ficha getFichaEvaluacion(String tipoFicha,String tipoFichaCurso);
}
