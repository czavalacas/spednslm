package sped.negocio.LNSF.SFBean;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
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
import sped.negocio.BDL.IL.BDL_C_SFFichaCriterioLocal;
import sped.negocio.BDL.IL.BDL_C_SFFichaLocal;
import sped.negocio.BDL.IL.BDL_C_SFFichaValorLocal;
import sped.negocio.BDL.IL.BDL_C_SFValorLocal;
import sped.negocio.BDL.IL.BDL_T_SFFichaCriterioLocal;
import sped.negocio.BDL.IL.BDL_T_SFFichaLocal;
import sped.negocio.BDL.IL.BDL_T_SFFichaValorLocal;
import sped.negocio.LNSF.IL.LN_C_SFErrorLocal;
import sped.negocio.LNSF.IL.LN_T_SFFichaLocal;
import sped.negocio.LNSF.IR.LN_T_SFFichaRemote;
import sped.negocio.entidades.beans.BeanCriterio;
import sped.negocio.entidades.beans.BeanCriterioValor;
import sped.negocio.entidades.beans.BeanError;
import sped.negocio.entidades.beans.BeanFicha;
import sped.negocio.entidades.beans.BeanLeyenda;
import sped.negocio.entidades.eval.Criterio;
import sped.negocio.entidades.eval.CriterioIndicador;
import sped.negocio.entidades.eval.CriterioValor;
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
    private BDL_C_SFFichaLocal bdL_C_SFFichaLocal;
    @EJB
    private BDL_T_SFFichaValorLocal bdL_T_SFFichaValorLocal;
    @EJB
    private BDL_C_SFFichaValorLocal bdL_C_SFFichaValorLocal;
    @EJB
    private BDL_T_SFFichaCriterioLocal bdL_T_SFFichaCriterioLocal;
    @EJB
    private BDL_C_SFFichaCriterioLocal bdL_C_SFFichaCriterioLocal;
    private MapperIF mapper = new DozerBeanMapper();

    public LN_T_SFFichaBean() {
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public BeanFicha registrarFicha(String tipFicha,
                                    String tipFichaCurso,
                                    String version,
                                    int numVal,
                                    List<BeanCriterio> listaCritsIndis,
                                    int evento,
                                    int nidFicha){//1 INSERTAR, 2 ACTUALIZAR
        BeanError beanError = new BeanError();
        Ficha ficha = new Ficha();
        BeanFicha bFicha = new BeanFicha();
        List<Integer> lstValores = this.getListaIdValorUnicos(listaCritsIndis);
        if(evento == 2){
            ficha = bdL_C_SFFichaLocal.findFichaById(nidFicha);
        }
        String error = "000";
        try{
            ficha.setDescripcionVersion(version);
            ficha.setEstadoFicha("0");
            if(evento == 2){
                ficha.setEstadoFicha("1".equals(ficha.getEstadoFicha()) ? "1" : "0");
            }
            ficha.setFechaFicha(new Timestamp(new Date().getTime()));
            ficha.setTipoFicha(tipFicha);
            ficha.setTipoFichaCurso(tipFichaCurso);
            if (evento == 1) {
                ficha.setFichaValorLista(this.setFichaValor(lstValores, ficha));
                ficha.setFichaCriterioLista(this.setFichaCriteriosLista(listaCritsIndis,ficha,evento));
            }
            if (evento == 2) {
                ficha = bdL_T_SFFichaLocal.mergeFicha(ficha);
                if (numVal < ficha.getCantidadValores()) { //QUITARON VALORES
                    int idx = 0;
                    Iterator it = ficha.getFichaValorLista().iterator();
                    while (it.hasNext()) {
                        FichaValor fichVal = (FichaValor) it.next();
                        //idx = fichVal.getValor().getValor() + 1;
                        idx++;
                        if (idx > numVal) {
                            FichaValor fichValor = bdL_C_SFFichaValorLocal.findFichaValorById(fichVal.getNidFichaValor());
                            bdL_T_SFFichaValorLocal.removeFichaValor(fichValor);
                            it.remove();
                        }
                    }
                } else if(numVal > ficha.getCantidadValores()){ //AGREGARON VALORES
                    ficha.setFichaValorLista(this.setFichaValor(lstValores, ficha));
                }
                List<FichaCriterio> lstFichas = this.setFichaCriteriosLista(listaCritsIndis,ficha,evento);
                for(FichaCriterio fc : lstFichas){
                    bdL_T_SFFichaCriterioLocal.persistFichaCriterio(fc);
                }
            } else if (evento == 1) {
                bdL_T_SFFichaLocal.persistFicha(ficha);
            }
        }catch(Exception e){
            error = "111";
            e.printStackTrace();
        }finally{
            beanError = ln_C_SFErrorLocal.getCatalogoErrores(error);
            bFicha.setBeanError(beanError);
            return bFicha;
        }
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public BeanFicha desactivarActivarFicha(int nidFicha,String actDesac){
        BeanError beanError = new BeanError();
        BeanFicha bFicha = new BeanFicha();
        String error = "000";
        try {
            Ficha ficha = bdL_C_SFFichaLocal.findFichaById(nidFicha);
            ficha.setEstadoFicha(actDesac);
            bdL_T_SFFichaLocal.mergeFicha(ficha);
        } catch (Exception e) {
            error = "111";
            e.printStackTrace();
        } finally{
            beanError = ln_C_SFErrorLocal.getCatalogoErrores(error);
            bFicha.setBeanError(beanError);
            return bFicha;
        }
    }
    
    public Leyenda getLeyendaByValor(List<Leyenda> lstLeys, int val){
        for(Leyenda ley : lstLeys){
            if(ley.getFichaValor().getValor().getValor() == val){
                return ley;
            }
        }
        return null;
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void eliminarFichaCriterio(int nidFicha){
        for(FichaCriterio ffc : bdL_C_SFFichaCriterioLocal.getFichaCriteriosByFicha(nidFicha)){
            bdL_T_SFFichaCriterioLocal.removeFichaCriterio(ffc);
        }
    }
    
    public List<FichaCriterio> setFichaCriteriosLista(List<BeanCriterio> listaCritsIndis,Ficha ficha,int evento){
        List<FichaCriterio> lstFichaCriterio = new ArrayList<FichaCriterio>();
        for(BeanCriterio bCrit : listaCritsIndis){
            Criterio criterio = (Criterio) mapper.map(bCrit, Criterio.class);
       //     Criterio criterio = bdL_C_SFCriterioLocal.findCriterioById(bCrit.getNidCriterio()); 
            FichaCriterio fichCriterio = new FichaCriterio();
            fichCriterio.setCriterio(criterio);
            fichCriterio.setFicha(ficha);
            fichCriterio.setOrden(bCrit.getOrden());
            fichCriterio.setCriterioIndicadorLista(this.setCriterioIndicadorLista(bCrit.getLstIndicadores(),fichCriterio,evento));
            fichCriterio.setLstCriterioValores(this.setListCriterioValor(bCrit.getLstCriteriosValor(),fichCriterio));
            lstFichaCriterio.add(fichCriterio);
        }
        return lstFichaCriterio;
    }
    
    public List<CriterioIndicador> setCriterioIndicadorLista(List<BeanCriterio> lstCritIndicadores,FichaCriterio fichaCriterio,int evento){
        List<CriterioIndicador> lstCriterioIndicador = new ArrayList<CriterioIndicador>();
        for(BeanCriterio bCritIndi : lstCritIndicadores){
            CriterioIndicador critIndi = new CriterioIndicador();//(CriterioIndicador) mapper.map(bCritIndi, CriterioIndicador.class);
            Indicador indicador = new Indicador();
            indicador.setDescripcionIndicador(bCritIndi.getDescripcionCriterio());
            indicador.setNidIndicador(bCritIndi.getNidCriterio());
            critIndi.setFichaCriterio(fichaCriterio);
            critIndi.setIndicador(indicador);
            critIndi.setOrden(bCritIndi.getOrden());
            critIndi.setLeyendaLista(this.setListaLeyendas(bCritIndi.getLstLeyenda(),critIndi,fichaCriterio.getFicha(),evento));
            lstCriterioIndicador.add(critIndi);
        }
        return lstCriterioIndicador;
    }
    
    public List<Leyenda> setListaLeyendas(List<BeanLeyenda> lstLeyends,CriterioIndicador criterioIndicador,Ficha ficha,int evento){
        List<Leyenda> lstLeys = new ArrayList<Leyenda>();
        for(BeanLeyenda ley : lstLeyends){
            Leyenda leyenda = new Leyenda();
            leyenda.setCriterioIndicador(criterioIndicador);
            leyenda.setDescripcionLeyenda(ley.getDescripcionLeyenda());
            int indx = Integer.parseInt(ley.getHeader().substring(ley.getHeader().indexOf(" ") + 1,
                                                                  ley.getHeader().length())       );
            FichaValor fichaValor = new FichaValor();
            if(evento == 1){
                fichaValor = this.getFichaValorBy((indx),ficha.getFichaValorLista());
            }else{
                fichaValor = this.getFichaValorBy((indx),bdL_C_SFFichaValorLocal.getFichaValorByFicha(ficha.getNidFicha()));
            }
            leyenda.setFichaValor(fichaValor);
            lstLeys.add(leyenda);
        }
        return lstLeys;
    }
    
    public List<FichaValor> setFichaValor(List<Integer> lstValores,Ficha ficha){
        List<FichaValor> lstaFichaValor = new ArrayList<FichaValor>();
        for(Integer idValor : lstValores){
            Valor val = bdL_C_SFValorLocal.findValorById(idValor);
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
    
    public void reactivarFichaYDesactivarElResto(String tipFicha,
                                                  String tipCursoFicha,
                                                  int nidFicha){
        bdL_T_SFFichaLocal.reactivarFichaYDesactivarElResto(tipFicha, tipCursoFicha, nidFicha);                                                 
    }
    
    public List<Integer> getListaIdValorUnicos(List<BeanCriterio> listaCritsIndis){
        Iterator it = listaCritsIndis.iterator();
        List<Integer> valoresUnicos = new ArrayList<Integer>();
        while(it.hasNext()){
            BeanCriterio crit = (BeanCriterio) it.next();
            for(int i = 0; i < crit.getLstCriteriosValor().size(); i++){
                if(!valoresUnicos.contains(crit.getLstCriteriosValor().get(i).getIdValoracion())){
                    valoresUnicos.add(crit.getLstCriteriosValor().get(i).getIdValoracion());
                }
            }
        }
        return valoresUnicos;
    }
    
    public List<CriterioValor> setListCriterioValor(List<BeanCriterioValor> valoresCriterio,FichaCriterio fc){
        List<CriterioValor> lstReturn = new ArrayList<CriterioValor>();
        CriterioValor v = null;
        for(BeanCriterioValor bcv : valoresCriterio){
            v = new CriterioValor();
            v.setDescripcionValor(bcv.getDescripcionValor());
            v.setFichaCriterio(fc);
            v.setIdValoracion(bcv.getIdValoracion());
            v.setOrden(bcv.getOrden());
            v.setValor(bcv.getValor());
            lstReturn.add(v);
        }
        return lstReturn;
    }
}