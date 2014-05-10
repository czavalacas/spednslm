package sped.negocio.LNSF.SFBean;

import java.sql.Time;

import javax.annotation.Resource;

import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import sped.negocio.BDL.IL.BDL_T_SFDuracionHorarioLocal;
import sped.negocio.BDL.IR.BDL_T_SFConfiguracionHorarioRemoto;
import sped.negocio.LNSF.IL.LN_C_SFErrorLocal;
import sped.negocio.LNSF.IL.LN_T_SFDuracionHorarioLocal;
import sped.negocio.LNSF.IR.LN_T_SFDuracionHorarioRemoto;
import sped.negocio.entidades.beans.BeanError;
import sped.negocio.entidades.sist.ConfiguracionEventoHorario;
import sped.negocio.entidades.sist.ConfiguracionHorario;
import sped.negocio.entidades.sist.DuracionHorario;
/** Clase que implementa la logica para poder invocar e insertar la duracion de horario
 * @author czavalacas
 * @since 07.05.2014
 */
@Stateless(name = "LN_T_SFDuracionHorario", mappedName = "map-LN_T_SFDuracionHorario")
public class LN_T_SFDuracionHorarioBean implements LN_T_SFDuracionHorarioRemoto, 
                                                    LN_T_SFDuracionHorarioLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SPED_NEGOCIO")
    private EntityManager em;
    @EJB
    private BDL_T_SFDuracionHorarioLocal bdl_T_SFDuracionHorarioLocal;
    @EJB
    private LN_C_SFErrorLocal ln_C_SFErrorLocal;
    public LN_T_SFDuracionHorarioBean() {
    }
    
    /**
     * Metodo para registrar la duracion de horario.
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
    public String registrarDuracionHorario_LN(int nidSede, 
                                         int nidNivel, 
                                         Time hora_inicio, 
                                         Time duracion, 
                                         int maxBloque,
                                         int numBloque) {
        BeanError beanError = new BeanError();
        String error = "000";
        try {
            DuracionHorario dura=new DuracionHorario();
            dura.setNidSede(nidSede);
            dura.setNidNivel(nidNivel);
            dura.setHora_inicio(hora_inicio);
            dura.setDuracion(duracion);
            dura.setMax_bloque(maxBloque);
            dura.setNro_bloque(numBloque);
            bdl_T_SFDuracionHorarioLocal.persistDuracionHorario(dura);
        } catch (Exception e) {
            e.printStackTrace();
            error = "111";
            beanError = ln_C_SFErrorLocal.getCatalogoErrores(error);
            error = beanError.getDescripcionError();
        }
        return error;
    }
}
