package sped.negocio.BDL.IL;

import javax.ejb.Local;

import sped.negocio.entidades.admin.Aula;

@Local
public interface BDL_T_SFAulaLocal {
    Aula persistAula(Aula aula);

    Aula mergeAula(Aula aula);

    void removeAula(Aula aula);
}
