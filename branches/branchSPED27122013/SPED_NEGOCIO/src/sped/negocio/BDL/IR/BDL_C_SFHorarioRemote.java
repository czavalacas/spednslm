package sped.negocio.BDL.IR;

import java.util.List;

import javax.ejb.Remote;

import sped.negocio.entidades.sist.Horario;

@Remote
public interface BDL_C_SFHorarioRemote {
    List<Horario> getHorarioFindAll();
}
