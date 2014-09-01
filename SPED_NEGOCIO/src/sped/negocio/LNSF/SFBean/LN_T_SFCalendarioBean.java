package sped.negocio.LNSF.SFBean;

import java.util.Date;

import javax.annotation.Resource;

import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import sped.negocio.BDL.IL.BDL_C_SFCalendarioLocal;
import sped.negocio.BDL.IL.BDL_T_SFCalendarioLocal;
import sped.negocio.LNSF.IL.LN_T_SFCalendarioLocal;
import sped.negocio.LNSF.IR.LN_T_SFCalendarioRemote;
import sped.negocio.entidades.sist.Calendario;

@Stateless(name = "LN_T_SFCalendario", mappedName = "mapLN_T_SFCalendario")
public class LN_T_SFCalendarioBean implements LN_T_SFCalendarioRemote, 
                                              LN_T_SFCalendarioLocal {
    @Resource
    SessionContext sessionContext;
    @EJB
    private BDL_T_SFCalendarioLocal bdL_T_SFCalendarioLocal;
    @EJB
    private BDL_C_SFCalendarioLocal bdL_C_SFCalendarioLocal;

    public LN_T_SFCalendarioBean() {
    }
    
    public String registrarDiaNoLaborable(Date nidFecha,String descripcionDia){
        try{
            Calendario calen = bdL_C_SFCalendarioLocal.findCalendarioById(nidFecha);
            calen.setDescripcionDia(descripcionDia);
            calen.setEsLaborable(0);
            calen.setEstilo("color:white; font-weight:bold;text-align:center; background-color:red");
            bdL_T_SFCalendarioLocal.mergeCalendario(calen);
            return "000";
        }catch(Exception e){
            e.printStackTrace();
            return "111";
        }
    }
    
    public String registrarDiaLaborable(Date nidFecha){
        try{
            Calendario calen = bdL_C_SFCalendarioLocal.findCalendarioById(nidFecha);
            calen.setDescripcionDia(null);
            calen.setEsLaborable(1);
            calen.setEstilo(null);
            bdL_T_SFCalendarioLocal.mergeCalendario(calen);
            return "000";
        }catch(Exception e){
            e.printStackTrace();
            return "111";
        }
    }
}