package sped.negocio.BDL.IL;

import javax.ejb.Remote;
import sped.negocio.entidades.sist.Log;

@Remote
public interface BDL_T_SFLogRemote {
    Log persistLog(Log log);

    Log mergeLog(Log log);

    void removeLog(Log log);
}
