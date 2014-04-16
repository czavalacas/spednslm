package sped.negocio.BDL.IL;

import javax.ejb.Local;

import sped.negocio.entidades.sist.Email;

@Local
public interface BDL_T_SFEmailLocal {
    Email persistEmail(Email email);

    Email mergeEmail(Email email);
}
