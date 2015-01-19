package sped.negocio.BDL.IR;

import javax.ejb.Remote;

import sped.negocio.entidades.sist.RestriccionHorario;

@Remote
public interface BDL_T_SFRestriccionHorarioRemote {
    RestriccionHorario persistRestriccionHorario(RestriccionHorario restriccionHorario);

    RestriccionHorario mergeRestriccionHorario(RestriccionHorario restriccionHorario);
    
    void removeRestriccionHorario(RestriccionHorario restriccionHorario);
}
