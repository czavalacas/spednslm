package sped.negocio.BDL.IR;

import javax.ejb.Remote;

import sped.negocio.entidades.admin.Problema;

@Remote
public interface BDL_T_SFProblemaRemote {
    Problema persistProblema(Problema problema);

    void removeProblema(Problema problema);
}
