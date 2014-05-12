package sped.negocio.BDL.IL;

import javax.ejb.Local;

import sped.negocio.entidades.sist.Logger;

@Local
public interface BDL_T_SFLoggerLocal {
    Logger persistLogger(Logger logger);

    Logger mergeLogger(Logger logger);

    void removeLogger(Logger logger);
}
