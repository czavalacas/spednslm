package sped.negocio.LNSF.SFBean;

import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import sped.negocio.BDL.IL.BDL_C_SFUtilsLocal;
import sped.negocio.BDL.IL.BDL_T_SFUtilsLocal;
import sped.negocio.LNSF.IL.LN_T_SFUtilsLocal;
import sped.negocio.LNSF.IR.LN_T_SFUtilsRemote;
import sped.negocio.Utils.Utiles;
import sped.negocio.entidades.admin.Constraint;
import sped.negocio.entidades.beans.BeanConstraint;

@Stateless(name = "LN_T_SFUtils", mappedName = "mapLN_T_SFUtils")
public class LN_T_SFUtilsBean implements LN_T_SFUtilsRemote,
                                         LN_T_SFUtilsLocal {
    @Resource
    SessionContext sessionContext;
    @EJB
    private BDL_T_SFUtilsLocal bdL_T_SFUtilsLocal;
    @EJB
    private BDL_C_SFUtilsLocal bdL_C_SFUtilsLocal;

    public LN_T_SFUtilsBean() {
    }
    
    public void cambiarALeidoNotificacion_LN(String tabla,Integer nidUsuario){
        bdL_T_SFUtilsLocal.cambiarALeidoNotificacion(tabla, nidUsuario);
    }
    
    public String actualizarConstraintConfigEvasXDia(List<BeanConstraint> lstConstraConfig){
        try{
            Iterator it = lstConstraConfig.iterator();
            BeanConstraint bc = null;
            int valMin = 0;
            int valMax = 0;
            int cantUpt = 0;
            while(it.hasNext()){
                bc = (BeanConstraint) it.next();
                if("cantEvasMinXDia".equalsIgnoreCase(bc.getNombreCampo())){
                    if(Utiles.isNumeric(bc.getValorCampo())){
                        valMin = Integer.parseInt(bc.getValorCampo());   
                    }else{
                        return "111";
                    }
                }
                if("cantEvasMaxXDia".equalsIgnoreCase(bc.getNombreCampo())){
                    if(Utiles.isNumeric(bc.getValorCampo())){
                        valMax = Integer.parseInt(bc.getValorCampo());   
                    }else{
                        return "111";
                    }
                }
            }
            if(valMin <= 0){
                return "111";
            }
            if(valMin < valMax){
                Iterator it2 = lstConstraConfig.iterator();
                while(it2.hasNext()){
                    bc = (BeanConstraint) it2.next();
                    Constraint cons = bdL_C_SFUtilsLocal.getConstraintByAttr_LN("configuracion", bc.getNombreCampo(), bc.getDescripcionAMostrar()).get(0);
                    if(!cons.getValorCampo().equalsIgnoreCase(bc.getValorCampo())){
                        bdL_T_SFUtilsLocal.actualizarConstraint(bc.getValorCampo(),bc.getNombreTabla(),bc.getNombreCampo(),cons.getValorCampo());
                        cantUpt++;
                    }
                }
                if(cantUpt > 0){
                    return "000";
                }
                return "222";
            }
            return "111";
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
}