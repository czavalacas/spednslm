package sped.negocio.BDL.IL;

import javax.ejb.Local;

import sped.negocio.entidades.sist.Calendario;

@Local
public interface BDL_T_SFCalendarioLocal {
    Calendario persistCalendario(Calendario calendario);

    Calendario mergeCalendario(Calendario calendario);

    void removeCalendario(Calendario calendario);
}
