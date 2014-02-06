package sped.negocio.BDL.IL;

import java.util.List;

import javax.ejb.Local;

import sped.negocio.entidades.eval.Valor;

@Local
public interface BDL_C_SFValorLocal {
    
    List<Valor> getValoresAll_BDL(int valMin,int valMax);
    Valor findValorById(int id);
    String getRangoValorByFicha(int nidFicha);
}
