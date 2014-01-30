package sped.negocio.BDL.IR;

import java.util.List;

import javax.ejb.Remote;

import sped.negocio.entidades.eval.ResultadoCriterio;

@Remote
public interface BDL_C_SFResultadoCriterioRemote {
    List<ResultadoCriterio> getResultadoCriterioFindAll();
}
