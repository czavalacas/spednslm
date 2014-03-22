package sped.negocio.BDL.IR;

import javax.ejb.Remote;

import sped.negocio.entidades.admin.Aula;

@Remote
public interface BDL_T_SFAulaRemoto {
    Aula persistAula(Aula aula);

    Aula mergeAula(Aula aula);

    void removeAula(Aula aula);
}
