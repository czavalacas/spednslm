package sped.negocio.BDL.IR;

import java.util.List;

import javax.ejb.Remote;

import sped.negocio.entidades.sist.Email;

@Remote
public interface BDL_C_SFEmailRemote {
    Email getEmail();
}
