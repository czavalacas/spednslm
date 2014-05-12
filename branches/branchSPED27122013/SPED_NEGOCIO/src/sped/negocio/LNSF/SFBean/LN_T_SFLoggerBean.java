package sped.negocio.LNSF.SFBean;

import java.sql.Timestamp;

import java.util.Date;

import javax.annotation.Resource;

import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import sped.negocio.BDL.IL.BDL_T_SFLoggerLocal;
import sped.negocio.LNSF.IL.LN_T_SFLoggerLocal;
import sped.negocio.LNSF.IR.LN_T_SFLoggerRemote;
import sped.negocio.entidades.sist.Logger;

/**
 * LN Transaccional que maneja la logica de negocio para registrar los errores del sistema
 * controlados y no controlados
 * @author dfloresgonz
 * @since 12.05.2014
 */
@Stateless(name = "LN_T_SFLogger", mappedName = "mapLN_T_SFLogger")
public class LN_T_SFLoggerBean implements LN_T_SFLoggerRemote, 
                                          LN_T_SFLoggerLocal {
    @Resource
    SessionContext sessionContext;
    @EJB
    private BDL_T_SFLoggerLocal bdL_T_SFLoggerLocal;

    public LN_T_SFLoggerBean() {
    }
    
    public void registrarLogErroresSistema(int nidLogeo,
                                           String tipo,
                                           String clase_java,
                                           String metodo_java,
                                           String comentario, 
                                           String stackTrace){
       try {
           Logger logger = new Logger();
           logger.setClase_java(clase_java);
           logger.setComentario(comentario);
           logger.setFecha_error(new Timestamp(new Date().getTime()));
           logger.setMetodo_java(metodo_java);
           if(nidLogeo > 0){
               logger.setNid_log(nidLogeo);
           }
           logger.setStacktrace(stackTrace);
           logger.setTipo_error(tipo);
           bdL_T_SFLoggerLocal.persistLogger(logger);
       } catch (Exception e) {
            e.printStackTrace();
       }
    }
}