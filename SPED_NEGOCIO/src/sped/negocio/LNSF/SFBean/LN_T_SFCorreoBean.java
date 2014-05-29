package sped.negocio.LNSF.SFBean;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import sped.negocio.BDL.IL.BDL_C_SFEmailLocal;
import sped.negocio.BDL.IL.BDL_T_SFEmailLocal;
import sped.negocio.LNSF.IL.LN_T_SFCorreoLocal;
import sped.negocio.LNSF.IL.LN_T_SFLoggerLocal;
import sped.negocio.LNSF.IR.LN_T_SFCorreoRemote;
import sped.negocio.Utils.Utiles;
import sped.negocio.entidades.sist.Email;

@Stateless(name = "LN_T_SFCorreo", mappedName = "mapLN_T_SFCorreo")
public class LN_T_SFCorreoBean implements LN_T_SFCorreoRemote, 
                                          LN_T_SFCorreoLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SPED_NEGOCIO")
    private EntityManager em;
    @EJB
    private BDL_C_SFEmailLocal bdL_C_SFEmailLocal;
    @EJB
    private BDL_T_SFEmailLocal bdL_T_SFEmailLocal;
    @EJB
    private LN_T_SFLoggerLocal ln_T_SFLoggerLocal;

    public LN_T_SFCorreoBean() {
    }
    
    /**
     * Metodo para guardar la configuracion del correo
     * @param correo
     * @param clave
     * @param host
     * @param puerto
     */
    public void guardarParametros(String correo,
                                  String clave,
                                  String host,
                                  String puerto){
        try{
            int num = -1;
            Email email = bdL_C_SFEmailLocal.getEmail();
            if(email == null){
                num = 0;
                email = new Email();
                email.setNidEmail(1);
            }
            email.setCorreo(correo);
            email.setClave(clave);
            email.setHost(host);
            email.setPuerto(puerto);
            if(num == 0){
                bdL_T_SFEmailLocal.persistEmail(email);            
            }else{
                bdL_T_SFEmailLocal.mergeEmail(email);
            }
        }catch(Exception e){
            ln_T_SFLoggerLocal.registrarLogErroresSistema(0, "TRA", "sped.negocio.LNSF.SFBean.LN_T_SFCorreoBean", 
                                                          "guardarParametros(String correo,String clave,String host,String puerto)", 
                                                          "Error al guardar la configuracion del correo", 
                                                          Utiles.getStack(e));
            e.printStackTrace();
        }
    }
    
}
