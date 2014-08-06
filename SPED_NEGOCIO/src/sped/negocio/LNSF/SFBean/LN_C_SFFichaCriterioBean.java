package sped.negocio.LNSF.SFBean;

import java.util.ArrayList;
import java.util.Iterator;
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
import sped.negocio.BDL.IL.BDL_C_SFResultadoCriterioLocal;
import sped.negocio.BDL.IL.BDL_C_SFResultadoLocal;
import sped.negocio.BDL.IL.BDL_C_SFUtilsLocal;
import sped.negocio.BDL.IR.BDL_C_SFValorRemote;
import sped.negocio.LNSF.IL.LN_C_SFCriterioIndicadorLocal;
import sped.negocio.LNSF.IL.LN_C_SFFichaCriterioLocal;
import sped.negocio.LNSF.IL.LN_C_SFResultadoCriterioLocal;
import sped.negocio.LNSF.IR.LN_C_SFFichaCriterioRemote;
import sped.negocio.Utils.Utiles;
import sped.negocio.entidades.beans.BeanComboDouble;
import sped.negocio.entidades.beans.BeanComboString;
import sped.negocio.entidades.beans.BeanCriterio;
import sped.negocio.entidades.beans.BeanCriterioWS;
import sped.negocio.entidades.beans.BeanFicha;
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
    private BDL_C_SFResultadoLocal bdL_C_SFResultadoLocal;
    @EJB
    private BDL_C_SFResultadoCriterioLocal bdL_C_SFResultadoCriterioLocal;
    @EJB
    private BDL_C_SFUtilsLocal bdL_C_SFUtilsLocal;
    private MapperIF mapper = new DozerBeanMapper();
    
    public LN_C_SFFichaCriterioBean() {
    }
    
    public List<BeanCriterio> getListaCriteriosByFicha(int nidFicha){
        return this.transformLista(bdL_C_SFFichaCriterioLocal.getFichaCriteriosByFicha(nidFicha));
    }
    
    public List<BeanCriterio> getListaCriteriosByFichaConValores(int nidFicha,int nidEvaluacion){
        return this.transformListaConValores(bdL_C_SFFichaCriterioLocal.getFichaCriteriosByFicha(nidFicha),nidEvaluacion);
    }
    
    public List<BeanCriterioWS> getListaCriteriosByFicha_WS(int nidFicha){
        List<BeanCriterioWS> lstBeanCriterio = new ArrayList<BeanCriterioWS>();
        for(FichaCriterio fichaCriterio : bdL_C_SFFichaCriterioLocal.getFichaCriteriosByFicha(nidFicha)){
            BeanCriterioWS criterio = new BeanCriterioWS();
            Criterio crit = fichaCriterio.getCriterio();
            criterio.setCantidadValoresWS(fichaCriterio.getFicha().getFichaValorLista().size());
            criterio.setOrden(fichaCriterio.getOrden());
            criterio.setDescripcionCriterio(crit.getDescripcionCriterio());
            criterio.setNidCriterio(crit.getNidCriterio());
            criterio.setNidFicha(fichaCriterio.getFicha().getNidFicha());
            lstBeanCriterio.add(criterio);
        }
        return lstBeanCriterio;
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
            criterio.setLstIndicadores(this.getListaIndicadores(fichaCriterio.getCriterioIndicadorLista(),criterio.getNidCriterio()));
            criterio.setMostrarBoton(true);//lupita para agregar indicadores
            criterio.setMostrarUpDown(true);
            criterio.setSelected(true);
            criterio.setCantidadValoresWS(fichaCriterio.getFicha().getFichaValorLista().size());
            criterio.setOrden(fichaCriterio.getOrden());
            boolean bool = indx == lstFichaCriterio.size();
            if(bool){
                criterio.setNoMostrarDown(true);
            }
            criterio.setDisplayInput("true");
            criterio.setDisplaySpinBox("false");
            lstBeanCriterio.add(criterio);
            indx++;
        }
        return lstBeanCriterio;
    }
    
    public List<BeanCriterio> transformListaConValores(List<FichaCriterio> lstFichaCriterio,int nidEvaluacion){
        List<BeanCriterio> lstBeanCriterio = new ArrayList<BeanCriterio>();
        int indx = 1;
        for(FichaCriterio fichaCriterio : lstFichaCriterio){
            BeanCriterio criterio = new BeanCriterio();
            Criterio crit = fichaCriterio.getCriterio();
            criterio = (BeanCriterio) mapper.map(crit, BeanCriterio.class);
            criterio.setDisplay("display:none;");//Mostrar el Ojo que abre el popUp de leyendas
            criterio.setEsIndicador(0);
            double vigecimal = bdL_C_SFResultadoCriterioLocal.getValorByFichaEvaluacionCriterio(fichaCriterio.getFicha().getNidFicha(),
                                                                                                nidEvaluacion,
                                                                                                fichaCriterio.getCriterio().getNidCriterio());
            String estilo = "";
            if(vigecimal <= 10.49){
                estilo = "rojo";
            }else if(vigecimal >= 10.50 && vigecimal <= 15.49){
                estilo = "amarillo";
            }else{
                estilo = "verde";
            }
            criterio.setEstilo(estilo);
            criterio.setNotaVige(vigecimal);
            criterio.setValorInput(vigecimal+"");
            criterio.setLstIndicadores(this.getListaIndicadoresConValores(fichaCriterio.getCriterioIndicadorLista(),criterio.getNidCriterio(),nidEvaluacion));
            criterio.setMostrarBoton(true);//lupita para agregar indicadores
            criterio.setMostrarUpDown(true);
            criterio.setSelected(true);
            criterio.setCantidadValoresWS(fichaCriterio.getFicha().getFichaValorLista().size());
            criterio.setOrden(fichaCriterio.getOrden());
            boolean bool = indx == lstFichaCriterio.size();
            if(bool){
                criterio.setNoMostrarDown(true);
            }
            criterio.setDisplayInput("true");
            criterio.setDisplaySpinBox("false");
            lstBeanCriterio.add(criterio);
            indx++;
        }
        return lstBeanCriterio;
    }
    
    public List<BeanCriterio> getListaIndicadores(List<CriterioIndicador> lstCrisIndis,Integer nidPadre){
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
            crit.setNidCriterioIndicador(critIndi.getNidCriterioIndicador());
            crit.setMaxValor(critIndi.getMaxValor());
            //crit.setLstValoresPosCombo(Utiles.llenarCombo(this.getLstValoresPosibles(critIndi.getNidCriterioIndicador())));
            crit.setLstValoresPosibles(this.getLstValoresPosibles(critIndi.getNidCriterioIndicador()));
            //crit.setValorSpinBox(critIndi.get);
            boolean bool = false;
            if(indx == lstCrisIndis.size()){
                bool = true;
            }
            if(bool){
                crit.setNoMostrarDown(true);
            }
            crit.setDisplayInput("false");
            crit.setDisplaySpinBox("true");
            crit.setNidCriterioPadre(nidPadre);
            lstIndis.add(crit);
            indx++;
        }
        return lstIndis;
    }
    
    public List<BeanCriterio> getListaIndicadoresConValores(List<CriterioIndicador> lstCrisIndis,Integer nidPadre,int nidEvaluacion){
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
            crit.setNidCriterioIndicador(critIndi.getNidCriterioIndicador());
            crit.setMaxValor(critIndi.getMaxValor());
            crit.setValorSpinBox(bdL_C_SFResultadoLocal.getValorResultadoByNidCriterioIndicador_Evaluacion(critIndi.getNidCriterioIndicador(),nidEvaluacion));
            crit.setLstValoresPosibles(this.getLstValoresPosibles(critIndi.getNidCriterioIndicador()));
            //crit.setLstValoresPosCombo(Utiles.llenarCombo(this.getLstValoresPosibles(critIndi.getNidCriterioIndicador())));
            boolean bool = false;
            if(indx == lstCrisIndis.size()){
                bool = true;
            }
            if(bool){
                crit.setNoMostrarDown(true);
            }
            crit.setDisplayInput("false");
            crit.setDisplaySpinBox("true");
            crit.setNidCriterioPadre(nidPadre);
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
            BeanFichaCriterio bean = new BeanFichaCriterio();
            bean.setDescripcionCriterio(fc.getCriterio().getDescripcionCriterio());
            BeanFicha ficha = new BeanFicha();
            ficha.setNidFicha(fc.getFicha().getNidFicha());
            bean.setFicha(ficha);
            bean.setResultadoCriterio(ln_C_SFResultadoCriterioLocal.getResCriByFichaEvaLN(nidEvaluacion,fc));
            bean.setLstcriterioIndicador(ln_C_SFCriterioIndicadorLocal.transformLstCriterioIndicador(fc.getCriterioIndicadorLista(),
                                                                                                     nidEvaluacion));
            lstBeanFC.add(bean);
        }
        return lstBeanFC;
    }
    
    public List<BeanComboString> getLstValoresPosibles(int nidCritIndi){
        try {
            List<BeanComboString> lstVals = new ArrayList<BeanComboString>();
            List<BeanComboDouble> lstDoub = bdL_C_SFUtilsLocal.getPosibleListaValoresIndicador(nidCritIndi);
            Iterator it = lstDoub.iterator();
            while (it.hasNext()) {
                BeanComboDouble bcd = (BeanComboDouble) it.next();
                String val = String.valueOf(bcd.getDescripcion());
                lstVals.add(new BeanComboString(val, val));
            }
            return lstVals;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}