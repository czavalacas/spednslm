package sped.negocio.BDL.IR;

import javax.ejb.Remote;

import sped.negocio.entidades.sist.Email;

@Remote
public interface BDL_T_SFEmailRemote {
    Email persistEmail(Email email);

    Email mergeEmail(Email email);
}
