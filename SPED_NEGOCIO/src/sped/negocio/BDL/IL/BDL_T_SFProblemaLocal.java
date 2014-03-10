package sped.negocio.BDL.IL;

import javax.ejb.Local;

import sped.negocio.entidades.admin.Problema;

@Local
public interface BDL_T_SFProblemaLocal {
    Problema persistProblema(Problema problema);

    void removeProblema(Problema problema);
}
