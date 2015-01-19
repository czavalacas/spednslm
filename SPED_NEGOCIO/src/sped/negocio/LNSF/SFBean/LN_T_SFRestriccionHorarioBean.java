package sped.negocio.LNSF.SFBean;

import java.util.List;

import javax.annotation.Resource;

import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import sped.negocio.BDL.IL.BDL_C_SFRestriccionHorarioLocal;
import sped.negocio.BDL.IL.BDL_T_SFRestriccionHorarioLocal;
import sped.negocio.LNSF.IL.LN_T_SFLoggerLocal;
import sped.negocio.LNSF.IL.LN_T_SFRestriccionHorarioLocal;
import sped.negocio.LNSF.IR.LN_T_SFRestriccionHorarioRemote;
import sped.negocio.Utils.Utiles;
import sped.negocio.entidades.beans.BeanRestriccionHorario;
import sped.negocio.entidades.sist.RestriccionHorario;

@Stateless(name = "LN_T_SFRestriccionHorario", mappedName = "mapLN_T_SFRestriccionHorario")
public class LN_T_SFRestriccionHorarioBean implements LN_T_SFRestriccionHorarioRemote, 
                                                      LN_T_SFRestriccionHorarioLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SPED_NEGOCIO")
    private EntityManager em;
    @EJB
    private BDL_T_SFRestriccionHorarioLocal bdL_T_SFRestriccionHorarioLocal;
    @EJB
    private LN_T_SFLoggerLocal ln_T_SFLoggerLocal;
    @EJB
    private BDL_C_SFRestriccionHorarioLocal bdL_C_SFRestriccionHorarioLocal;

    public LN_T_SFRestriccionHorarioBean() {
    }

    public void grabarRestricciones(List<BeanRestriccionHorario> list){
        try{
            for(BeanRestriccionHorario bean : list){
                if(bean.getNidReho() != 0){
                    bdL_T_SFRestriccionHorarioLocal.removeRestriccionHorario(bdL_C_SFRestriccionHorarioLocal.findRestriccionHorarioById(bean.getNidReho()));
                }else{
                    RestriccionHorario res = new RestriccionHorario();
                    res.setEstado("1");
                    res.setNDia(bean.getNDia());
                    res.setHora_ini(bean.getHora_ini());
                    res.setHora_fin(bean.getHora_fin());
                    res.setTipoRestr(bean.getTipoRestr());
                    res.setNid(bean.getNid());
                    bdL_T_SFRestriccionHorarioLocal.persistRestriccionHorario(res);
                }                
            }
        }catch(Exception ex){
            ex.printStackTrace();
            ln_T_SFLoggerLocal.registrarLogErroresSistema(0, "TRA", "LN_T_SFRestriccionHorarioBean",
                                                          "grabarRestricciones(List<BeanRestriccionHorario> list)",
                                                          "Error al guardar las restricciones",
                                                          Utiles.getStack(ex));
        }
    }   
    
}
