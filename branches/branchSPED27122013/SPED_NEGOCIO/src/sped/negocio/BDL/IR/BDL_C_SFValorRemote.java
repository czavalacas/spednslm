package sped.negocio.BDL.IR;

import java.util.List;

import javax.ejb.Remote;

import sped.negocio.entidades.eval.Valor;

@Remote
public interface BDL_C_SFValorRemote {
    
    List<Valor> getValoresAll_BDL(int valMin,int valMax);
    Valor findValorById(int id);
    String getRangoValorByFicha(int nidFicha);
    String getValoresByCriterio(int nidCriterio,int nidFicha);
    int cantidadValoresByValor(double valor);
}
