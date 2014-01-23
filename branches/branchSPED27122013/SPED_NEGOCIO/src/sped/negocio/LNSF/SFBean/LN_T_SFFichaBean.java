package sped.negocio.LNSF.SFBean;

import java.sql.Timestamp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.ejb.TransactionAttribute;

import javax.ejb.TransactionAttributeType;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import net.sf.dozer.util.mapping.DozerBeanMapper;
import net.sf.dozer.util.mapping.MapperIF;

import sped.negocio.BDL.IL.BDL_C_SFCriterioLocal;
import sped.negocio.BDL.IL.BDL_C_SFValorLocal;
import sped.negocio.BDL.IL.BDL_T_SFFichaLocal;
import sped.negocio.LNSF.IL.LN_C_SFErrorLocal;
import sped.negocio.LNSF.IL.LN_T_SFFichaLocal;
import sped.negocio.LNSF.IR.LN_T_SFFichaRemote;
import sped.negocio.Utils.Utiles;
import sped.negocio.entidades.beans.BeanCriterio;
import sped.negocio.entidades.beans.BeanCriterioIndicador;
import sped.negocio.entidades.beans.BeanError;
import sped.negocio.entidades.beans.BeanFicha;
import sped.negocio.entidades.beans.BeanLeyenda;
import sped.negocio.entidades.eval.Criterio;
import sped.negocio.entidades.eval.CriterioIndicador;
import sped.negocio.entidades.eval.Ficha;
import sped.negocio.entidades.eval.FichaCriterio;
import sped.negocio.entidades.eval.FichaValor;
import sped.negocio.entidades.eval.Indicador;
import sped.negocio.entidades.eval.Leyenda;
import sped.negocio.entidades.eval.Valor;

@Stateless(name = "LN_T_SFFicha", mappedName = "mapLN_T_SFFicha")
public class LN_T_SFFichaBean implements LN_T_SFFichaRemote, 
                                            LN_T_SFFichaLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SPED_NEGOCIO")
    private EntityManager em;
    @EJB
    private BDL_C_SFValorLocal bdL_C_SFValorLocal;
    @EJB
    private BDL_T_SFFichaLocal bdL_T_SFFichaLocal;
    @EJB
    private LN_C_SFErrorLocal ln_C_SFErrorLocal;
    @EJB
    private BDL_C_SFCriterioLocal bdL_C_SFCriterioLocal;
    private MapperIF mapper = new DozerBeanMapper();

    public LN_T_SFFichaBean() {
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public BeanFicha registrarFicha(String tipFicha,
                                     String tipFichaCurso,
                                     String version,
                                     int numVal,
                                     List<BeanCriterio> listaCritsIndis){
        BeanError beanError = new BeanError();
        Ficha ficha = new Ficha();
        BeanFicha bFicha = new BeanFicha();
        String error = "000";
        try{
            ficha.setDescripcionVersion(version);
            ficha.setEstadoFicha("1");
            ficha.setFechaFicha(new Timestamp(new Date().getTime()));
            ficha.setTipoFicha(tipFicha);
            ficha.setTipoFichaCurso(tipFichaCurso);
            ficha.setFichaValorLista(this.setFichaValor(numVal, ficha));
            ficha.setFichaCriterioLista(this.setFichaCriteriosLista(listaCritsIndis, ficha));
            bdL_T_SFFichaLocal.persistFicha(ficha);
        }catch(Exception e){
            error = "111";
        }finally{
            beanError = ln_C_SFErrorLocal.getCatalogoErrores(error);
            bFicha.setBeanError(beanError);
            return bFicha;
        }
    }
    
    public List<FichaCriterio> setFichaCriteriosLista(List<BeanCriterio> listaCritsIndis,Ficha ficha){
        List<FichaCriterio> lstFichaCriterio = new ArrayList<FichaCriterio>();
        for(BeanCriterio bCrit : listaCritsIndis){
            Criterio criterio = (Criterio) mapper.map(bCrit, Criterio.class);
       //     Criterio criterio = bdL_C_SFCriterioLocal.findCriterioById(bCrit.getNidCriterio()); 
            FichaCriterio fichCriterio = new FichaCriterio();
            fichCriterio.setCriterio(criterio);
            fichCriterio.setFicha(ficha);
            fichCriterio.setCriterioIndicadorLista(this.setCriterioIndicadorLista(bCrit.getLstIndicadores(),fichCriterio));
            lstFichaCriterio.add(fichCriterio);
        }
        return lstFichaCriterio;
    }
    
    public List<CriterioIndicador> setCriterioIndicadorLista(List<BeanCriterio> lstCritIndicadores,FichaCriterio fichaCriterio){
        List<CriterioIndicador> lstCriterioIndicador = new ArrayList<CriterioIndicador>();
        for(BeanCriterio bCritIndi : lstCritIndicadores){
            CriterioIndicador critIndi = new CriterioIndicador();//(CriterioIndicador) mapper.map(bCritIndi, CriterioIndicador.class);
            Indicador indicador = new Indicador();
            indicador.setDescripcionIndicador(bCritIndi.getDescripcionCriterio());
            indicador.setNidIndicador(bCritIndi.getNidCriterio());
            critIndi.setFichaCriterio(fichaCriterio);
            critIndi.setIndicador(indicador);
            critIndi.setLeyendaLista(this.setListaLeyendas(bCritIndi.getLstLeyenda(),critIndi,fichaCriterio.getFicha()));
            lstCriterioIndicador.add(critIndi);
        }
        return lstCriterioIndicador;
    }
    
    public List<Leyenda> setListaLeyendas(List<BeanLeyenda> lstLeyends,CriterioIndicador criterioIndicador,Ficha ficha){
        List<Leyenda> lstLeys = new ArrayList<Leyenda>();
        for(BeanLeyenda ley : lstLeyends){
            Leyenda leyenda = new Leyenda();
            leyenda.setCriterioIndicador(criterioIndicador);
            leyenda.setDescripcionLeyenda(ley.getDescripcionLeyenda());
            int indx = Integer.parseInt(ley.getHeader().substring(ley.getHeader().indexOf(" ") + 1,
                                                                  ley.getHeader().length())       );
            FichaValor fichaValor = this.getFichaValorBy((indx),ficha.getFichaValorLista());
            leyenda.setFichaValor(fichaValor);
            lstLeys.add(leyenda);
        }
        return lstLeys;
    }
    
    public List<FichaValor> setFichaValor(int numVal,Ficha ficha){
        List<FichaValor> lstaFichaValor = new ArrayList<FichaValor>();
        for(Valor val : bdL_C_SFValorLocal.getValoresAll_BDL(numVal)){
            FichaValor fichaValor = new FichaValor();
            fichaValor.setFicha(ficha);
            fichaValor.setValor(val);
            lstaFichaValor.add(fichaValor);
        }
        return lstaFichaValor;
    }
    
    public FichaValor getFichaValorBy(int valor,List<FichaValor> lstFichasValor){
        for(FichaValor fichVal : lstFichasValor){
            if(valor == fichVal.getValor().getValor()){
                return fichVal;
            }
        }
        return null;
    }
}