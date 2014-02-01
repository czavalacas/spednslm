package sped.negocio.BDL.IR;

import java.util.List;

import javax.ejb.Remote;

import sped.negocio.entidades.eval.FichaValor;

@Remote
public interface BDL_C_SFFichaValorRemote {
    List<FichaValor> getFichaValorFindAll();
    FichaValor findFichaValorById(int id);
    List<FichaValor> getFichaValorByFicha(int nidFicha);
}
