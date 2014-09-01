package sped.negocio.LNSF.SFBean;

import java.util.Date;

import javax.annotation.Resource;

import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import sped.negocio.BDL.IL.BDL_C_SFUsuarioCalendarioLocal;
import sped.negocio.LNSF.IL.LN_C_SFUsuarioCalendarioLocal;
import sped.negocio.LNSF.IR.LN_C_SFUsuarioCalendarioRemote;
import sped.negocio.Utils.Utiles;

@Stateless(name = "LN_C_SFUsuarioCalendario", mappedName = "mapLN_C_SFUsuarioCalendario")
public class LN_C_SFUsuarioCalendarioBean implements LN_C_SFUsuarioCalendarioRemote, 
                                                     LN_C_SFUsuarioCalendarioLocal {
    @Resource
    SessionContext sessionContext;
    @EJB
    private BDL_C_SFUsuarioCalendarioLocal bdL_C_SFUsuarioCalendarioLocal;
    
    public LN_C_SFUsuarioCalendarioBean() {
    }
    
    public int getCantidadDiasLaborablesByUsuario(int nidUsuario, Date fecMin,Date fecMax){
        return bdL_C_SFUsuarioCalendarioLocal.getCantidadDiasLaborablesByUsuario(nidUsuario, fecMin, fecMax);
    }
}