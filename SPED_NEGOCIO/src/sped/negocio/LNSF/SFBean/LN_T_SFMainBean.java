package sped.negocio.LNSF.SFBean;

import java.sql.Time;

import javax.annotation.Resource;

import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import sped.negocio.BDL.IL.BDL_C_SFAulaLocal;
import sped.negocio.BDL.IL.BDL_C_SFCursoLocal;
import sped.negocio.BDL.IL.BDL_C_SFMainLocal;
import sped.negocio.BDL.IL.BDL_C_SFProfesorLocal;
import sped.negocio.BDL.IL.BDL_T_SFMainLocal;
import sped.negocio.LNSF.IL.LN_T_SFMainLocal;
import sped.negocio.LNSF.IR.LN_T_SFMainRemote;
import sped.negocio.entidades.admin.Main;

@Stateless(name = "LN_T_SFMain", mappedName = "map-LN_T_SFMain")
public class LN_T_SFMainBean implements LN_T_SFMainRemote, 
                                        LN_T_SFMainLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SPED_NEGOCIO")
    private EntityManager em;
    @EJB
    private BDL_C_SFMainLocal bdL_C_SFMainLocal;
    @EJB
    private BDL_C_SFProfesorLocal bdL_C_SFProfesorLocal;
    @EJB
    private BDL_C_SFAulaLocal bdL_C_SFAulaLocal;
    @EJB
    private BDL_C_SFCursoLocal bdL_C_SFCursoLocal;
    @EJB
    private BDL_T_SFMainLocal bdL_T_SFMainLocal;

    public LN_T_SFMainBean() {
    }
    
    public void gestionarMain_LN(int tipoEvento,
                                 int nidMain,
                                 String dniProfesor,
                                 int nidAula,
                                 int nidCurso,
                                 int nDia,
                                 Time horaInicio,
                                 Time horaFin){
        Main main = new Main();    
        if(tipoEvento > 1){
            main = bdL_C_SFMainLocal.findMainById(nidMain);
        }
        if(tipoEvento == 1 || tipoEvento == 2){
            main.setProfesor(bdL_C_SFProfesorLocal.getProfesorBydni(dniProfesor));
            main.setAula(bdL_C_SFAulaLocal.findAulaById(nidAula));
            main.setCurso(bdL_C_SFCursoLocal.findCursoById(nidCurso));
            main.setNDia(nDia);
            main.setHoraInicio(horaInicio);
            main.setHoraFin(horaFin);
        }
        if(tipoEvento == 3){
            main.setEstado("0");
        }
        if(tipoEvento == 1){
            bdL_T_SFMainLocal.persistMain(main);
        }
        if(tipoEvento > 1){
            bdL_T_SFMainLocal.mergeMain(main);
        }
        
    }
}
