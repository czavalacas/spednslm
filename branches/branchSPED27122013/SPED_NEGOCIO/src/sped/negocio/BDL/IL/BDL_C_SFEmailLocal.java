package sped.negocio.BDL.IL;

import java.util.List;

import javax.ejb.Local;

import sped.negocio.entidades.sist.Email;

@Local
public interface BDL_C_SFEmailLocal {
    Email getEmail();
}
