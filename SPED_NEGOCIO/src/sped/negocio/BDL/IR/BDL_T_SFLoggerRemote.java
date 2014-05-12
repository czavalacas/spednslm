package sped.negocio.BDL.IR;

import javax.ejb.Remote;

import sped.negocio.entidades.sist.Logger;

@Remote
public interface BDL_T_SFLoggerRemote {
    Logger persistLogger(Logger logger);

    Logger mergeLogger(Logger logger);

    void removeLogger(Logger logger);
}
