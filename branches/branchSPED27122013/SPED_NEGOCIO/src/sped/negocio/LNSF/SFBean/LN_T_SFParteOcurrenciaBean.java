package sped.negocio.LNSF.SFBean;

import java.sql.Timestamp;

import java.util.Date;

import javax.annotation.Resource;

import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import sped.negocio.BDL.IL.BDL_T_SFParteOcurrenciaLocal;
import sped.negocio.LNSF.IL.LN_C_SFErrorLocal;
import sped.negocio.LNSF.IL.LN_T_SFParteOcurrenciaLocal;
import sped.negocio.LNSF.IR.LN_T_SFParteOcurrenciaRemote;
import sped.negocio.entidades.admin.ParteOcurrencia;

/**
 * Metodo de Logica de Negocio que realiza el seteo de la entidad a persistir ParteOcurrencia - admpaoc
 * @author dfloresgonz
 * @since 26.02.2014
 */
@Stateless(name = "LN_T_SFParteOcurrencia", mappedName = "mapLN_T_SFParteOcurrencia")
public class LN_T_SFParteOcurrenciaBean implements LN_T_SFParteOcurrenciaRemote,
                                                      LN_T_SFParteOcurrenciaLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SPED_NEGOCIO")
    private EntityManager em;
    @EJB
    private BDL_T_SFParteOcurrenciaLocal bdL_T_SFParteOcurrenciaLocal;
    @EJB
    private LN_C_SFErrorLocal ln_C_SFErrorLocal;

    public LN_T_SFParteOcurrenciaBean() {
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public String registrarParteOcurrencia_LN(Integer nidMain,
                                               String comentario,
                                               Integer nidProblema,
                                               Integer nidUsuario,
                                               Integer nidSede){
        String error = "000";
       try {
           ParteOcurrencia parteOcurrencia = new ParteOcurrencia();
           parteOcurrencia.setComentario(comentario);
           parteOcurrencia.setFechaRegistro(new Timestamp(new Date().getTime()));
           parteOcurrencia.setNidMain(nidMain);
           parteOcurrencia.setNidProblema(nidProblema);
           parteOcurrencia.setNidUsuario(nidUsuario);
           parteOcurrencia.setNidSede(nidSede);
           bdL_T_SFParteOcurrenciaLocal.persistParteOcurrencia(parteOcurrencia);
       } catch (Exception e) {
            e.printStackTrace();
            error = "111";
            ln_C_SFErrorLocal.getCatalogoErrores(error).getDescripcionError();
        }
        return error;
    }
}