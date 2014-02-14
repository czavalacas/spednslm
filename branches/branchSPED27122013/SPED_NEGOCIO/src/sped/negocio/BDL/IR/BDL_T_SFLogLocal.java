package sped.negocio.BDL.IR;

import javax.ejb.Local;
import sped.negocio.entidades.sist.Log;

@Local
public interface BDL_T_SFLogLocal {
    Log persistLog(Log log);

    Log mergeLog(Log log);

    void removeLog(Log log);
}
