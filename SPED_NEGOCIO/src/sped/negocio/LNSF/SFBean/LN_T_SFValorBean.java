package sped.negocio.LNSF.SFBean;

import javax.annotation.Resource;

import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import sped.negocio.BDL.IL.BDL_C_SFValorLocal;
import sped.negocio.BDL.IL.BDL_T_SFValorLocal;
import sped.negocio.LNSF.IL.LN_T_SFValorLocal;
import sped.negocio.LNSF.IR.LN_T_SFValorRemote;
import sped.negocio.entidades.eval.Valor;

@Stateless(name = "LN_T_SFValor", mappedName = "mapLN_T_SFValor")
public class LN_T_SFValorBean implements LN_T_SFValorRemote, 
                                         LN_T_SFValorLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SPED_NEGOCIO")
    private EntityManager em;
    private final static String NO_ERROR = "000";
    private final static String ERROR = "111";
    @EJB
    private BDL_C_SFValorLocal bdL_C_SFValorLocal;
    @EJB
    private BDL_T_SFValorLocal bdL_T_SFValorLocal;

    public LN_T_SFValorBean() {
    }

    public String registrarNuevoValor_LN(Double valor) {
        try {
            int cantValor = bdL_C_SFValorLocal.cantidadValoresByValor(valor);
            if(cantValor == 0){
                Valor eValor = new Valor();
                eValor.setDescripcionValor("Valor "+valor);
                eValor.setValor(valor);
                bdL_T_SFValorLocal.persistValor(eValor);
                return NO_ERROR;
            }else{
                return ERROR;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
    }
}