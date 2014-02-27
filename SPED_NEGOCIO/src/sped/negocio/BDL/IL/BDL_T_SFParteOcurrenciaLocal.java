package sped.negocio.BDL.IL;

import javax.ejb.Local;

import sped.negocio.entidades.admin.ParteOcurrencia;

@Local
public interface BDL_T_SFParteOcurrenciaLocal {
    ParteOcurrencia persistParteOcurrencia(ParteOcurrencia parteOcurrencia);

    ParteOcurrencia mergeParteOcurrencia(ParteOcurrencia parteOcurrencia);

    void removeParteOcurrencia(ParteOcurrencia parteOcurrencia);
}
