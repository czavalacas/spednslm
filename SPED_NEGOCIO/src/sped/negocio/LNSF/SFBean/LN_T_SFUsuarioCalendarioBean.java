package sped.negocio.LNSF.SFBean;

import java.util.Date;

import javax.annotation.Resource;

import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import sped.negocio.BDL.IL.BDL_C_SFUsuarioCalendarioLocal;
import sped.negocio.BDL.IL.BDL_T_SFUsuarioCalendarioLocal;
import sped.negocio.LNSF.IL.LN_T_SFUsuarioCalendarioLocal;
import sped.negocio.LNSF.IR.LN_T_SFUsuarioCalendarioRemote;
import sped.negocio.entidades.admin.UsuarioCalendario;

@Stateless(name = "LN_T_SFUsuarioCalendario", mappedName = "mapLN_T_SFUsuarioCalendario")
public class LN_T_SFUsuarioCalendarioBean implements LN_T_SFUsuarioCalendarioRemote, 
                                                     LN_T_SFUsuarioCalendarioLocal {
    @Resource
    SessionContext sessionContext;
    @EJB
    private BDL_T_SFUsuarioCalendarioLocal bdL_T_SFUsuarioCalendarioLocal;
    @EJB
    private BDL_C_SFUsuarioCalendarioLocal bdL_C_SFUsuarioCalendarioLocal;

    public LN_T_SFUsuarioCalendarioBean() {
    }
    
    public String registrarDiaNoLaborableUsuario_LN(Date nidFecha,int nidUsuario,
                                                    String descripDia,String tipo){
        try{
            UsuarioCalendario usuCalen = bdL_C_SFUsuarioCalendarioLocal.findUsuarioCalendarioById(nidFecha, nidUsuario);
            if(usuCalen == null){
                usuCalen = new UsuarioCalendario();
                usuCalen.setDescripcionDiaLibre(descripDia);
                usuCalen.setNidFecha(nidFecha);
                usuCalen.setNidUsuario(nidUsuario);
                usuCalen.setTipoFalta(tipo);
                usuCalen.setEstilo("color:white; font-weight:bold;text-align:center; background-color:red");
                bdL_T_SFUsuarioCalendarioLocal.persistUsuarioCalendario(usuCalen);
                return "000";
            }else{
                return "111";
            }
        }catch(Exception e){
            e.printStackTrace();
            return "111";
        }
    }
    
    public String actualizarDiaNoLaborableUsuarioDescripcionTipo_LN(Date nidFecha,int nidUsuario,
                                                                    String descripDia,String tipo){
        try{
            UsuarioCalendario usuCalen = bdL_C_SFUsuarioCalendarioLocal.findUsuarioCalendarioById(nidFecha, nidUsuario);
            if(usuCalen != null){
                usuCalen.setDescripcionDiaLibre(descripDia);
                usuCalen.setTipoFalta(tipo);
                usuCalen.setEstilo("color:white; font-weight:bold;text-align:center; background-color:red");
                bdL_T_SFUsuarioCalendarioLocal.mergeUsuarioCalendario(usuCalen);
                return "000";
            }else{
                return "111";
            }
        }catch(Exception e){
            e.printStackTrace();
            return "111";
        }
    }
    
    public String regresarADiaNoLaborableUsuarioCalendario_LN(Date nidFecha,int nidUsuario){
        try{
            UsuarioCalendario usuCalen = bdL_C_SFUsuarioCalendarioLocal.findUsuarioCalendarioById(nidFecha, nidUsuario);
            if(usuCalen != null){
                bdL_T_SFUsuarioCalendarioLocal.removeUsuarioCalendario(usuCalen);
                return "000";
            }else{
                return "111";
            }
        }catch(Exception e){
            e.printStackTrace();
            return "111";
        }
    }
}