package sped.negocio.LNSF.SFBean;

import java.util.ArrayList;
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
import sped.negocio.Utils.Utiles;
import sped.negocio.entidades.beans.BeanBar;
import sped.negocio.entidades.beans.BeanParteOcurrencia;
import sped.negocio.entidades.beans.BeanPie;
import sped.negocio.entidades.beans.BeanProblemaProfesor;

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
        List<BeanParteOcurrencia> lstPOs = bdL_C_SFParteOcurrenciaLocal.getListaPartesOcurrencia_BDL(fechaMin, fechaMax, nidProblema,
                                                                                                         nombreProfesor, nidSede, nidUsuario);
        try {
            if (lstPOs != null) {
                if (lstPOs.size() > 0) {
                    BeanParteOcurrencia last = lstPOs.get(lstPOs.size() - 1);
                    List<BeanPie> lstPies = new ArrayList<BeanPie>();
                    List<BeanBar> lstBeanBar = new ArrayList<BeanBar>();
                    BeanPie pie = null;
                    BeanBar bar = null;
                    for (BeanParteOcurrencia po : lstPOs) {
                        pie = new BeanPie();
                        pie.setSerie(po.getDescProblema());
                        bar = new BeanBar();
                        bar.setSerie(po.getDescProblema());
                        bar.setGroup(po.getProfesor());
                        if (lstPies.contains(pie)) {
                            pie = this.getPieBySerie(po.getDescProblema(), lstPies);
                            pie.setCantSlice((pie.getCantSlice() + 1));
                        } else {
                            pie.setCantSlice(1);
                            lstPies.add(pie);
                        }
                        if(lstBeanBar.contains(bar)){
                            bar = this.getBarByGroupProblema(po.getProfesor(),po.getDescProblema(),lstBeanBar);
                            bar.setCantidad(bar.getCantidad() + 1);
                        }else{
                            bar.setCantidad(1);
                            lstBeanBar.add(bar);
                        }
                    }
                    BeanBar[] barrasArray = lstBeanBar.toArray(new BeanBar[lstBeanBar.size()]);
                    BeanPie[] pieArray = lstPies.toArray(new BeanPie[lstPies.size()]);
                    last.setLstBars(barrasArray);
                    last.setLstPies(pieArray);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lstPOs;
    }
    
    public List<BeanPie> getPiePO_ByProfesor_LN_WS(Date fechaMin,
                                                    Date fechaMax,
                                                    String dniProfesor,
                                                    Integer nidSede,
                                                    Integer nidUsuario){
        List<BeanProblemaProfesor> lstProbs = bdL_C_SFParteOcurrenciaLocal.getListaProblemas_ByProfesor_BDL(fechaMin,fechaMax,dniProfesor,nidSede,nidUsuario);    
        List<BeanPie> lstPies = new ArrayList<BeanPie>();
        List<BeanBar> lstBeanBar = new ArrayList<BeanBar>();
        if(lstProbs != null){
            if(lstProbs.size() > 0){
                BeanPie pie = null;
                BeanBar bar = null;
                for(BeanProblemaProfesor prob : lstProbs){
                    pie = new BeanPie();
                    bar = new BeanBar();
                    pie.setSerie(prob.getProblema());
                    bar.setSerie(prob.getProblema());
                    bar.setGroup(prob.getProfesor());
                    if(lstPies.contains(pie)){
                        pie = this.getPieBySerie(prob.getProblema(),lstPies);
                        pie.setCantSlice((pie.getCantSlice() + 1));
                    }else{
                        pie.setCantSlice(1);
                        lstPies.add(pie);
                    }
                    if(lstBeanBar.contains(bar)){
                        bar = this.getBarByGroupProblema(prob.getProfesor(),prob.getProblema(),lstBeanBar);
                        bar.setCantidad(bar.getCantidad() + 1);
                    }else{
                        bar.setCantidad(1);
                        lstBeanBar.add(bar);
                    }
                }
                BeanPie last = lstPies.get(lstPies.size() - 1);
                BeanBar[] barrasArray = lstBeanBar.toArray(new BeanBar[lstBeanBar.size()]);
                last.setLstBar(barrasArray);
            }
        }
        return lstPies;
    }
    
    public BeanPie getPieBySerie(String serie,List<BeanPie> lstPies){
        for(int i = 0; i < lstPies.size(); i++){
            BeanPie pie = lstPies.get(i);
            if(pie.getSerie().equalsIgnoreCase(serie)){
                return pie;
            }
        }
        return null;
    }
    
    public BeanBar getBarByGroupProblema(String group,String problema,List<BeanBar> lstBeanBar){//group profe, serie:problema
        for(int i = 0; i < lstBeanBar.size(); i++){
            BeanBar barra = lstBeanBar.get(i);
            if(barra.getGroup().equalsIgnoreCase(group) && barra.getSerie().equalsIgnoreCase(problema)){
                return barra;
            }
        }
        return null;
    }
}
