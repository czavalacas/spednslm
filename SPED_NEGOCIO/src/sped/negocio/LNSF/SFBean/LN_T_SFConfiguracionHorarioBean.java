package sped.negocio.LNSF.SFBean;

import java.sql.Time;

import java.util.Date;

import javax.annotation.Resource;

import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import sped.negocio.BDL.IR.BDL_T_SFConfiguracionHorarioRemoto;
import sped.negocio.LNSF.IL.LN_C_SFErrorLocal;
import sped.negocio.LNSF.IL.LN_T_SFConfiguracionHorarioLocal;
import sped.negocio.LNSF.IR.LN_T_SFConfiguracionHorarioRemoto;
import sped.negocio.entidades.beans.BeanConfiguracionHorario;
import sped.negocio.entidades.beans.BeanDuracionHorario;
import sped.negocio.entidades.beans.BeanError;
import sped.negocio.entidades.eval.Evaluacion;
import sped.negocio.entidades.sist.ConfiguracionEventoHorario;
import sped.negocio.entidades.sist.ConfiguracionHorario;
import sped.negocio.entidades.sist.DuracionHorario;

/** Clase que implementa la logica para poder invocar e insertar la configuracion de horario
 * @author czavalacas
 * @since 07.05.2014
 */
@Stateless(name = "LN_T_SFConfiguracionHorario", mappedName = "map-LN_T_SFConfiguracionHorario")
public class LN_T_SFConfiguracionHorarioBean implements LN_T_SFConfiguracionHorarioRemoto,
                                                        LN_T_SFConfiguracionHorarioLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SPED_NEGOCIO")
    private EntityManager em;
    @EJB
    private BDL_T_SFConfiguracionHorarioRemoto BDL_T_SFConfiguracionHorarioRemoto;
    @EJB
    private LN_C_SFErrorLocal ln_C_SFErrorLocal;
    public LN_T_SFConfiguracionHorarioBean() {
    }
    
    /**
     * Metodo para registrar las configuraciones de horario.
     * @author czavalacas
     * @since 04.05.2014
     * @param nidSede
     * @param nidNivel
     * @param hora_inicio
     * @param hora_fin
     * @param nidConfev
     * @return
     */
    
    
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public String registrarConfiguracionHorario_LN(int nidSede, 
                                         int nidNivel, 
                                         Time hora_inicio, 
                                         Time hora_fin, 
                                         int nidConfev) {
        BeanError beanError = new BeanError();
        String error = "000";
        try {
            ConfiguracionHorario conf=new ConfiguracionHorario();
            conf.setNidSede(nidSede);
            conf.setNidNivel(nidNivel);
            conf.setHora_inicio(hora_inicio);
            conf.setHora_fin(hora_fin);
            ConfiguracionEventoHorario confEv=new ConfiguracionEventoHorario();
            confEv.setNidConfev(nidConfev);
            conf.setStmconfev(confEv);
            BDL_T_SFConfiguracionHorarioRemoto.persistConfiguracionHorario(conf);
        } catch (Exception e) {
            e.printStackTrace();
            error = "111";
            beanError = ln_C_SFErrorLocal.getCatalogoErrores(error);
            error = beanError.getDescripcionError();
        }
        return error;
    }
    
    public String actualizarConfiguracionHorario_LN(BeanConfiguracionHorario beanConfi, 
                                                    int accion){
        BeanError beanError = new BeanError();
        String error = "000";
        try {
            ConfiguracionHorario confi=new ConfiguracionHorario();
            confi.setNidConfig(beanConfi.getNidConfig());
            confi.setHora_inicio(beanConfi.getHora_inicio());
            confi.setHora_fin(beanConfi.getHora_fin());
            confi.setNidNivel(beanConfi.getNidNivel());
            confi.setNidSede(beanConfi.getNidSede());
            ConfiguracionEventoHorario conEv=new ConfiguracionEventoHorario();
            conEv.setNidConfev(beanConfi.getStmconfev().getNidConfev());
            confi.setStmconfev(conEv);                   
            if(accion==1){           
            BDL_T_SFConfiguracionHorarioRemoto.mergeConfiguracionHorario(confi);
            }
            if(accion==2){
            BDL_T_SFConfiguracionHorarioRemoto.removeConfiguracionHorario(confi);
            }            
        } catch (Exception e) {
            e.printStackTrace();
            error = "111";
            beanError = ln_C_SFErrorLocal.getCatalogoErrores(error);
            error = beanError.getDescripcionError();
        }
        return error;
    }    
    
}
