package sped.negocio.BDL.IR;

import javax.ejb.Remote;

import sped.negocio.entidades.admin.ParteOcurrencia;

@Remote
public interface BDL_T_SFParteOcurrenciaRemote {
    ParteOcurrencia persistParteOcurrencia(ParteOcurrencia parteOcurrencia);

    ParteOcurrencia mergeParteOcurrencia(ParteOcurrencia parteOcurrencia);

    void removeParteOcurrencia(ParteOcurrencia parteOcurrencia);
}
