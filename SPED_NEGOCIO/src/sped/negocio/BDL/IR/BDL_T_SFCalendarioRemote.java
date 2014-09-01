package sped.negocio.BDL.IR;

import javax.ejb.Remote;

import sped.negocio.entidades.sist.Calendario;

@Remote
public interface BDL_T_SFCalendarioRemote {
    Calendario persistCalendario(Calendario calendario);

    Calendario mergeCalendario(Calendario calendario);

    void removeCalendario(Calendario calendario);
}
