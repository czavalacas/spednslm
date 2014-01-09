package sped.negocio.BDL.IR;

import java.util.List;

import javax.ejb.Remote;

import sped.negocio.entidades.eval.Ficha;

@Remote
public interface BDL_C_SFFichaRemote {
    List<Ficha> getFichaFindAll();
    List<Ficha> getFichaByAttr_BDL();
}
