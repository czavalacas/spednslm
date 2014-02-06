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

import sped.negocio.BDL.IL.BDL_C_SFFichaCriterioLocal;
import sped.negocio.BDL.IR.BDL_C_SFValorRemote;
import sped.negocio.LNSF.IL.LN_C_SFCriterioIndicadorLocal;
import sped.negocio.LNSF.IL.LN_C_SFFichaCriterioLocal;
import sped.negocio.LNSF.IL.LN_C_SFResultadoCriterioLocal;
import sped.negocio.LNSF.IR.LN_C_SFFichaCriterioRemote;
import sped.negocio.Utils.Utiles;
import sped.negocio.entidades.beans.BeanCriterio;
import sped.negocio.entidades.beans.BeanFichaCriterio;
import sped.negocio.entidades.beans.BeanLeyenda;
import sped.negocio.entidades.eval.Criterio;
import sped.negocio.entidades.eval.CriterioIndicador;
import sped.negocio.entidades.eval.FichaCriterio;
import sped.negocio.entidades.eval.Leyenda;

@Stateless(name = "LN_C_SFFichaCriterio", mappedName = "mapLN_C_SFFichaCriterio")
public class LN_C_SFFichaCriterioBean implements LN_C_SFFichaCriterioRemote, 
                                                    LN_C_SFFichaCriterioLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SPED_NEGOCIO")
    private EntityManager em;
    @EJB
    private BDL_C_SFFichaCriterioLocal bdL_C_SFFichaCriterioLocal;
    @EJB
    private LN_C_SFResultadoCriterioLocal ln_C_SFResultadoCriterioLocal;
    @EJB
    private LN_C_SFCriterioIndicadorLocal ln_C_SFCriterioIndicadorLocal;
    @EJB
    private BDL_C_SFValorRemote bdL_C_SFValorRemote;
    private MapperIF mapper = new DozerBeanMapper();
    
    public LN_C_SFFichaCriterioBean() {
    }
    
    public List<BeanCriterio> getListaCriteriosByFicha(int nidFicha){
        return this.transformLista(bdL_C_SFFichaCriterioLocal.getFichaCriteriosByFicha(nidFicha));
    }
    
    public List<BeanCriterio> transformLista(List<FichaCriterio> lstFichaCriterio){
        List<BeanCriterio> lstBeanCriterio = new ArrayList<BeanCriterio>();
        int indx = 1;
        for(FichaCriterio fichaCriterio : lstFichaCriterio){
            BeanCriterio criterio = new BeanCriterio();
            Criterio crit = fichaCriterio.getCriterio();
            criterio = (BeanCriterio) mapper.map(crit, BeanCriterio.class);
            criterio.setDisplay("display:none;");//Mostrar el Ojo que abre el popUp de leyendas
            criterio.setEsIndicador(0);
            criterio.setLstIndicadores(this.getListaIndicadores(fichaCriterio.getCriterioIndicadorLista()));
            criterio.setMostrarBoton(true);//lupita para agregar indicadores
            criterio.setMostrarUpDown(true);
            criterio.setSelected(true);
            criterio.setCantidadValoresWS(fichaCriterio.getFicha().getFichaValorLista().size());
            criterio.setOrden(fichaCriterio.getOrden());
            boolean bool = indx == lstFichaCriterio.size();
            if(bool){
                criterio.setNoMostrarDown(true);
            }
            lstBeanCriterio.add(criterio);
            indx++;
        }
        return lstBeanCriterio;
    }
    
    public List<BeanCriterio> getListaIndicadores(List<CriterioIndicador> lstCrisIndis){
        List<BeanCriterio> lstIndis = new ArrayList<BeanCriterio>();
        int indx = 1;
        for(CriterioIndicador critIndi : lstCrisIndis){
            BeanCriterio crit = new BeanCriterio();
            crit.setDescripcionCriterio(critIndi.getIndicador().getDescripcionIndicador());
            crit.setDisplay("display:block;");
            crit.setEsIndicador(1);
            crit.setLstLeyenda(this.getLstLeyendas(critIndi.getLeyendaLista()));
            crit.setMostrarBoton(true);
            crit.setMostrarUpDown(true);
            crit.setNidCriterio(critIndi.getIndicador().getNidIndicador());
            crit.setOrden(critIndi.getOrden());
            crit.setSelected(true);
            boolean bool = indx == lstCrisIndis.size();
            if(bool){
                crit.setNoMostrarDown(true);
            }
            lstIndis.add(crit);
            indx++;
        }
        return lstIndis;
    }
    
    public List<BeanLeyenda> getLstLeyendas(List<Leyenda> lstLeys){
        List<BeanLeyenda> lstLeyendas = new ArrayList<BeanLeyenda>();
        for(Leyenda ley : lstLeys){
            BeanLeyenda bLey = (BeanLeyenda) mapper.map(ley, BeanLeyenda.class);
            bLey.setHeader(ley.getFichaValor().getValor().getDescripcionValor());
            lstLeyendas.add(bLey);
        }
        return lstLeyendas;
    }
    
    public List<BeanFichaCriterio> getLstFichaCriterioByEvaluacion(int nidEvaluacion){
        List<BeanFichaCriterio> lstBeanFC = new ArrayList();
        List<FichaCriterio> lstFC = bdL_C_SFFichaCriterioLocal.getLstFichaCriteriosByEvaluacion(nidEvaluacion);
        for(FichaCriterio fc : lstFC){
            BeanFichaCriterio bean = (BeanFichaCriterio) mapper.map(fc, BeanFichaCriterio.class);
            bean.setResultadoCriterio(ln_C_SFResultadoCriterioLocal.getResCriByFichaEvaLN(nidEvaluacion, 
                                                                                          fc));
            bean.setLstcriterioIndicador(ln_C_SFCriterioIndicadorLocal.
                                         transformLstCriterioIndicador(fc.getCriterioIndicadorLista(),
                                                                       nidEvaluacion));
            lstBeanFC.add(bean);
        }
        return lstBeanFC;
    }
    
}