package sped.negocio.BDL.IL;

import javax.ejb.Local;

@Local
public interface BDL_C_SFErrorLocal {
    
    sped.negocio.entidades.sist.Error getErrorByCodigo(String cidError);
}
