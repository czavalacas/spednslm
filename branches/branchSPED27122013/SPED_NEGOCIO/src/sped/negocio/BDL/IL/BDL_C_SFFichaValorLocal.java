package sped.negocio.BDL.IL;

import java.util.List;

import javax.ejb.Local;

import sped.negocio.entidades.eval.FichaValor;

@Local
public interface BDL_C_SFFichaValorLocal {
    List<FichaValor> getFichaValorFindAll();
    FichaValor findFichaValorById(int id);
    List<FichaValor> getFichaValorByFicha(int nidFicha);
}
