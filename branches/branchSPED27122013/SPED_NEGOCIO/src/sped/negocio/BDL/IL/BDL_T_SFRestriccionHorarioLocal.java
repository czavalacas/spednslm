package sped.negocio.BDL.IL;

import javax.ejb.Local;

import sped.negocio.entidades.sist.RestriccionHorario;

@Local
public interface BDL_T_SFRestriccionHorarioLocal {
    RestriccionHorario persistRestriccionHorario(RestriccionHorario restriccionHorario);

    RestriccionHorario mergeRestriccionHorario(RestriccionHorario restriccionHorario);
    
    void removeRestriccionHorario(RestriccionHorario restriccionHorario);
}
