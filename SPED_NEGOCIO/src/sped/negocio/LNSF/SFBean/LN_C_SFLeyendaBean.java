package sped.negocio.LNSF.SFBean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import net.sf.dozer.util.mapping.DozerBeanMapper;
import net.sf.dozer.util.mapping.MapperIF;

import sped.negocio.BDL.IL.BDL_C_SFLeyendaLocal;
import sped.negocio.LNSF.IL.LN_C_SFLeyendaLocal;
import sped.negocio.LNSF.IR.LN_C_SFLeyendaRemote;
import sped.negocio.Utils.Utiles;
import sped.negocio.entidades.beans.BeanLeyenda;
import sped.negocio.entidades.beans.BeanLeyendaWS;
import sped.negocio.entidades.eval.CriterioIndicador;
import sped.negocio.entidades.eval.Leyenda;

@Stateless(name = "LN_C_SFLeyenda", mappedName = "mapLN_C_SFLeyenda")
public class LN_C_SFLeyendaBean implements LN_C_SFLeyendaRemote, 
                                           LN_C_SFLeyendaLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SPED_NEGOCIO")
    private EntityManager em;
    @EJB
    private BDL_C_SFLeyendaLocal bdL_C_SFLeyendaLocal;
    private MapperIF mapper = new DozerBeanMapper();

    public LN_C_SFLeyendaBean() {
    }
    
    public BeanLeyenda getLeyendabyEvaluacion(int nidCriterioIndicador,
                                              int nidFicha, 
                                              double valor){
        return (BeanLeyenda) mapper.map(bdL_C_SFLeyendaLocal.getLeyendabyEvaluacion(nidCriterioIndicador, nidFicha, valor), BeanLeyenda.class);
    }
    
    /**
     * Metodo que retorna la lista de leyendas segun el indicador, usado para mostrar en el aplicativo Movil
     * @param nidCriterioIndicador id del criterioIndicador
     * @author dfloresgonz
     * @since 07.02.2014
     * @return List<BeanLeyendaWS>
     */
    public List<BeanLeyendaWS> getLeyendasByCriterioIndicador_LN_WS(int nidCriterioIndicador){
        List<BeanLeyendaWS> lstLeys = new ArrayList<BeanLeyendaWS>();
        for(Leyenda ley : bdL_C_SFLeyendaLocal.getLeyendasByCriterioIndicador_BDL_WS(nidCriterioIndicador)){
            BeanLeyendaWS beanLey = new BeanLeyendaWS();
            beanLey.setDescripcionLeyenda(ley.getDescripcionLeyenda());
            beanLey.setValor(ley.getFichaValor().getValor().getValor());
            lstLeys.add(beanLey);
        }
        return lstLeys;
    }
}
