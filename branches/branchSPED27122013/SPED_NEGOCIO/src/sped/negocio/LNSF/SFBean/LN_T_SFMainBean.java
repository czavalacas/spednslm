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
import sped.negocio.BDL.IL.BDL_C_SFEvaluacionLocal;
import sped.negocio.BDL.IL.BDL_C_SFMainLocal;
import sped.negocio.BDL.IL.BDL_C_SFProfesorLocal;
import sped.negocio.BDL.IL.BDL_T_SFMainLocal;
import sped.negocio.LNSF.IL.LN_T_SFLoggerLocal;
import sped.negocio.LNSF.IL.LN_T_SFMainLocal;
import sped.negocio.LNSF.IR.LN_T_SFMainRemote;
import sped.negocio.Utils.Utiles;
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
    @EJB
    private BDL_C_SFEvaluacionLocal bdL_C_SFEvaluacionLocal;
    @EJB
    private LN_T_SFLoggerLocal ln_T_SFLoggerLocal;
    private static final String CLASE = "sped.negocio.LNSF.SFBean.LN_T_SFMainBean";

    public LN_T_SFMainBean() {
    }
    
    /**
     * Metodo que ingresa o modifica los atributos de main Tipo 1 = Registrar, 2 = modificar, 3  = modificar profesor
     * @param tipoEvento
     * @param nidMain
     * @param dniProfesor
     * @param nidAula
     * @param nidCurso
     * @param nDia
     * @param horaInicio
     * @param horaFin
     */
    public void gestionarMain_LN(int tipoEvento,
                                 int nidMain,
                                 String dniProfesor,
                                 int nidAula,
                                 int nidCurso,
                                 int nDia,
                                 Time horaInicio,
                                 Time horaFin){
        try{
            Main main = new Main();    
            if(tipoEvento == 2){
                main = bdL_C_SFMainLocal.findMainById(nidMain);
                if(main.getProfesor().getDniProfesor().compareTo(dniProfesor) == 0 &&
                   main.getAula().getNidAula() == nidAula &&
                   main.getCurso().getNidCurso() == nidCurso &&
                   main.getNDia() == nDia &&
                   main.getHoraInicio().equals(horaInicio) &&
                   main.getHoraFin().equals(horaFin) ){
                    /////valida si los datos son iguales  
                    System.out.println("Son igulillos");
                    return; 
                }else{
                    System.out.println("NO Son igulillos");
                    if(bdL_C_SFEvaluacionLocal.countEvaluacionByNidMain(nidMain) > 0){
                        main.setEstado("0");                        
                        bdL_T_SFMainLocal.mergeMain(main);
                        main = new Main();
                        tipoEvento = 1;
                    }                     
                }
            }
            main.setProfesor(bdL_C_SFProfesorLocal.getProfesorBydni(dniProfesor));
            main.setAula(bdL_C_SFAulaLocal.findAulaById(nidAula));
            main.setCurso(bdL_C_SFCursoLocal.findCursoById(nidCurso));
            main.setNDia(nDia);
            main.setHoraInicio(horaInicio);
            main.setHoraFin(horaFin);
            main.setEstado("1");
            if(tipoEvento == 1){
                bdL_T_SFMainLocal.persistMain(main);
            }
            if(tipoEvento == 2){                
                bdL_T_SFMainLocal.mergeMain(main);
            }
        }catch(Exception e){
            ln_T_SFLoggerLocal.registrarLogErroresSistema(0, "TRA", CLASE, "gestionarMain_LN(...)", 
                                                          "Error al guardar o actualizar MAIN. Tipo Evento "+tipoEvento, 
                                                          Utiles.getStack(e));
            e.printStackTrace();
        }        
    }
    
    /**
     * Metodo que elimina dependiendo si el main esta siendo usado en evaluacion.
     * @param nidMain
     * @param existe
     */
    public void eliminarMain_LN(int nidMain){
        try{
            Main main = main = bdL_C_SFMainLocal.findMainById(nidMain);
            if(bdL_C_SFEvaluacionLocal.countEvaluacionByNidMain(nidMain) > 0){
                main.setEstado("0");
                bdL_T_SFMainLocal.mergeMain(main);
            }else{
                bdL_T_SFMainLocal.removeMain(main);   
            }            
        }catch(Exception e){
            ln_T_SFLoggerLocal.registrarLogErroresSistema(0, "TRA", CLASE, "eliminarMain_LN(int nidMain)", 
                                                          "Error al eliminar MAIN. Tipo Evento ", 
                                                          Utiles.getStack(e));
            e.printStackTrace();
        }
    }
}
