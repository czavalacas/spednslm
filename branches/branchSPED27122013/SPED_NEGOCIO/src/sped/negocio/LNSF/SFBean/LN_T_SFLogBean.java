package sped.negocio.LNSF.SFBean;

import java.sql.Timestamp;

import java.util.Date;

import javax.annotation.Resource;

import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import sped.negocio.BDL.IR.BDL_T_SFLogLocal;
import sped.negocio.LNSF.IL.LN_T_SFLogLocal;
import sped.negocio.LNSF.IR.LN_T_SFLogRemote;
import sped.negocio.Utils.Utiles;
import sped.negocio.entidades.sist.Log;

@Stateless(name = "LN_T_SFLog", mappedName = "mapLN_T_SFLog")
public class LN_T_SFLogBean implements LN_T_SFLogRemote, 
                                       LN_T_SFLogLocal {
    @Resource
    SessionContext sessionContext;
    @EJB
    private BDL_T_SFLogLocal bdL_T_SFLogLocal;

    public LN_T_SFLogBean() {
    }
    
    public Integer grabarLogLogInWS_LN(String cadenaPhoneData,Integer nidUsuario){
        try{
            Log log = new Log();
            String array1[] = cadenaPhoneData.split("\\|");
            log.setNid_evento(new Integer(1));
            log.setDevice_name(array1[0]);
            log.setDevice_platform(array1[1]);
            log.setDevice_version(array1[2]);
            log.setDevice_os(array1[3]);
            log.setDevice_model(array1[4]);
            log.setDevice_geolocation(array1[5]);
            log.setDevice_networkstatus(array1[6]);
            log.setDevice_screenwidth(array1[7]);
            log.setDevice_screenheight(array1[8]);
            log.setDevice_avai_scrwidth(array1[9]);
            log.setDevice_avai_scrheight(array1[10]);
            log.setDevice_screendpi(array1[11]);
            log.setDevice_diagonalsize(array1[12]);
            log.setNid_usuario(nidUsuario);
            log.setFechaConexion(new Timestamp(new Date().getTime()));
            log.setFechaEvento(new Timestamp(new Date().getTime()));
            log = bdL_T_SFLogLocal.persistLog(log);
            return log.getNidLog();
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
    
    public Integer grabarLogLogInWeb_LN(String[] cadenaData,Integer nidUsuario){
        try{
            Log log = new Log();
            log.setNid_evento(new Integer(3));
            log.setNavegadorWeb(cadenaData[0]);
            log.setSistemaOperativo(cadenaData[1]);
            log.setDevice_avai_scrheight(cadenaData[2]);
            log.setDevice_avai_scrwidth(cadenaData[3]);
            log.setDevice_screenheight(cadenaData[4]);
            log.setDevice_screenwidth(cadenaData[5]);
            log.setIpPublica(cadenaData[6]);
            log.setIpPrivada(cadenaData[7]);
            log.setNid_usuario(nidUsuario);
            log.setFechaConexion(new Timestamp(new Date().getTime()));
            log.setFechaEvento(new Timestamp(new Date().getTime()));
            log = bdL_T_SFLogLocal.persistLog(log);
            return log.getNidLog();
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
}