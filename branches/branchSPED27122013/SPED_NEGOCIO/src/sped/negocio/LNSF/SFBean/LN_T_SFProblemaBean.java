package sped.negocio.LNSF.SFBean;

import javax.annotation.Resource;

import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import sped.negocio.BDL.IL.BDL_C_SFEvaluacionLocal;
import sped.negocio.BDL.IL.BDL_C_SFProblemaLocal;
import sped.negocio.BDL.IL.BDL_T_SFProblemaLocal;
import sped.negocio.BDL.IR.BDL_C_SFEvaluacionRemoto;
import sped.negocio.BDL.IR.BDL_T_SFProblemaRemote;
import sped.negocio.LNSF.IL.LN_T_SFProblemaLocal;
import sped.negocio.LNSF.IR.LN_T_SFProblemaRemote;
import sped.negocio.entidades.admin.Problema;
import sped.negocio.entidades.beans.BeanProblema;

@Stateless(name = "LN_T_SFProblema", mappedName = "mapLN_T_SFProblema")
public class LN_T_SFProblemaBean implements LN_T_SFProblemaRemote, 
                                            LN_T_SFProblemaLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SPED_NEGOCIO")
    private EntityManager em;
    @EJB
    private BDL_T_SFProblemaLocal bdL_T_SFProblemaLocal;
    @EJB
    private BDL_C_SFEvaluacionLocal bdL_C_SFEvaluacionLocal;
    @EJB
    private BDL_C_SFProblemaLocal bdL_C_SFProblemaLocal;

    public LN_T_SFProblemaBean() {
    }
    
    public String gestionarProblema(int evento, 
                                    int nidProblema,
                                    String descripcion){
        if(evento == 1){
            Problema problema = new Problema();
            problema.setDesc_problema(descripcion);
            problema.setEstado("1");
            bdL_T_SFProblemaLocal.persistProblema(problema);
        }
        if(evento == 2){
            int count = bdL_C_SFEvaluacionLocal.countNidProblema(nidProblema);
            if(count == 0){
                bdL_T_SFProblemaLocal.removeProblema(
                                bdL_C_SFProblemaLocal.findConstrainById(nidProblema));
            }else{
                return "Este Problema no puede ser eliminado";
            }
        }
        return null;        
    }
}
