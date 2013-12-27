package sped.negocio.BDL.IR;

import javax.ejb.Remote;

@Remote
public interface BDL_C_SFErrorRemote {
    
    sped.negocio.entidades.sist.Error getErrorByCodigo(String cidError);
}
