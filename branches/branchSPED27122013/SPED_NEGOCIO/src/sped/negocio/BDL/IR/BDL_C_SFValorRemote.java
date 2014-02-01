package sped.negocio.BDL.IR;

import java.util.List;

import javax.ejb.Remote;

import sped.negocio.entidades.eval.Valor;

@Remote
public interface BDL_C_SFValorRemote {
    
    List<Valor> getValoresAll_BDL(int valMin,int valMax);
    Valor findValorById(int id);
}
