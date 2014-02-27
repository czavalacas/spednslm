package sped.negocio.LNSF.SFBean;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import sped.negocio.BDL.IL.BDL_C_SFParteOcurrenciaLocal;
import sped.negocio.LNSF.IL.LN_C_SFParteOcurrenciaLocal;
import sped.negocio.LNSF.IR.LN_C_SFParteOcurrenciaRenote;
import sped.negocio.entidades.beans.BeanParteOcurrencia;

/**
 * Clase que gestiona la logica de negocio para las consultas invoca a los BDL, mas no se
 * escriben queries aqui
 * @author dfloresgonz
 * @since 26.02.2014
 */
@Stateless(name = "LN_C_SFParteOcurrencia", mappedName = "mapLN_C_SFParteOcurrencia")
public class LN_C_SFParteOcurrenciaBean implements LN_C_SFParteOcurrenciaRenote, 
                                                      LN_C_SFParteOcurrenciaLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SPED_NEGOCIO")
    private EntityManager em;
    @EJB
    private BDL_C_SFParteOcurrenciaLocal bdL_C_SFParteOcurrenciaLocal;

    public LN_C_SFParteOcurrenciaBean() {
    }

    public List<BeanParteOcurrencia> getListaPartesOcurrencia_LN(Date fechaMin, Date fechaMax, Integer nidProblema,
                                                                  String nombreProfesor, Integer nidSede,
                                                                  Integer nidUsuario) {
        return bdL_C_SFParteOcurrenciaLocal.getListaPartesOcurrencia_BDL(fechaMin, fechaMax, nidProblema,
                                                                              nombreProfesor, nidSede, nidUsuario);
    }
}
